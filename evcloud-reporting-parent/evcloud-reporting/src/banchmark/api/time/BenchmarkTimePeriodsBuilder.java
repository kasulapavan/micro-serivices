package banchmark.api.time;

import banchmark.controller.BenchmarkPageRequest;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.IsoFields;

import static java.time.temporal.ChronoUnit.MILLIS;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class BenchmarkTimePeriodsBuilder {
    private Integer year;
    private Integer quarter;
    private Month month;

    private BenchmarkTimePeriodsBuilder() {
    }

    public static BenchmarkTimePeriodsBuilder builder() {
        return new BenchmarkTimePeriodsBuilder();
    }

    public InternalTimePeriods buildPeriods() {
        Assert.notNull(year, "Can't create time period without year.");
        if (month != null) {
            return new InternalTimePeriodsMonthly(year, month);
        }
        if (quarter != null) {
            Assert.state(!(quarter < 1 || quarter > 4), "Quarter must be in range [1, 4].");
            return new InternalTimePeriodsQuarter(year, quarter);
        }
        return new InternalTimePeriodsYear(year);

    }

    public BenchmarkTimePeriodsBuilder withYear(Integer year) {
        this.year = year;
        return this;
    }

    public BenchmarkTimePeriodsBuilder withMonth(Month month) {
        this.month = month;
        return this;
    }

    public BenchmarkTimePeriodsBuilder withQuarter(Integer quarter) {
        this.quarter = quarter;
        return this;
    }

    public BenchmarkTimePeriodsBuilder fromRequest(BenchmarkPageRequest request) {
        return this.withYear(request.getYear())
                .withQuarter(request.getQuarter())
                .withMonth(request.getMonth());
    }

    @Getter
    private static class InternalTimePeriodsYear extends InternalTimePeriods {
        private final int year;

        public InternalTimePeriodsYear(int year) {
            this.year = year;

            // start period at the first day of requested year
            currentStart = LocalDate.MAX.withYear(year).with(firstDayOfYear()).atStartOfDay();
            previousStart = currentStart.minusYears(ONE);

            // end period at the 23:59:59 of the requested year
            // (example:
            // 2018 + 1 year = 2019-01-01 00:00:00
            // 2019 - 1 second = 2018-01-01 23:59:59)
            // range [2018-01-01 -- 2018-12-31]
            calculateEnd(currentStart.plusYears(ONE).minus(ONE, MILLIS));
        }
    }

    @Getter
    private static class InternalTimePeriodsMonthly extends InternalTimePeriods {
        private final int year;
        private final Month month;

        public InternalTimePeriodsMonthly(int year, Month month) {
            this.year = year;
            this.month = month;

            currentStart = LocalDate
                    .MAX
                    .withYear(year)
                    .withMonth(month.getValue())
                    .with(firstDayOfMonth())
                    .atStartOfDay();

            previousStart = currentStart.minusMonths(1);

            calculateEnd(currentStart.with(lastDayOfMonth()).plusDays(ONE).minus(ONE, MILLIS));
        }
    }

    @Getter
    private static class InternalTimePeriodsQuarter extends InternalTimePeriods {
        private final int year;
        private final int quarter;

        public InternalTimePeriodsQuarter(int year, int quarter) {
            this.year = year;
            this.quarter = quarter;

            currentStart = LocalDate.MAX
                    .withYear(year)
                    .with(IsoFields.QUARTER_OF_YEAR, quarter)
                    .with(IsoFields.DAY_OF_QUARTER, FIRST)
                    .atStartOfDay();

            LocalDateTime previousDay = currentStart.minusSeconds(ONE); // change: month--; quarter--; // optional: year--

            previousStart = previousDay
                    .plusSeconds(ONE)
                    .withMonth(previousDay.getMonth().firstMonthOfQuarter().getValue()) // with the first month of prev. quarter
                    .with(IsoFields.DAY_OF_QUARTER, FIRST);

            calculateEnd(currentStart.plus(ONE, IsoFields.QUARTER_YEARS).minus(ONE, MILLIS));
        }
    }

    @Getter
    public abstract static class InternalTimePeriods {
        protected static final int ONE = 1; // [ONE smth.] -> ONE second/day...
        protected static final int FIRST = 1; // [FIRST smth. of smth.] -> FIRST day of month...

        protected LocalDateTime currentStart;
        protected LocalDateTime currentEnd;
        protected LocalDateTime previousStart;
        protected LocalDateTime previousEnd;

        protected void calculateEnd(LocalDateTime end) {
            LocalDateTime now = LocalDateTime.now();

            if (now.compareTo(end) < 0) {
                currentEnd = now;
                long between = currentStart.until(currentEnd, MILLIS);
                previousEnd = previousStart.plus(between, MILLIS);
            } else {
                currentEnd = end;
                previousEnd = currentStart.minus(ONE, MILLIS);
            }
        }
    }
}