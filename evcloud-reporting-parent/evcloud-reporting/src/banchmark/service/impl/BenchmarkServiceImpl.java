package banchmark.service.impl;

import banchmark.service.BenchmarksService;
import com.evconnect.evcloud.reporting.benchmark.api.BenchmarkPage;
import com.evconnect.evcloud.reporting.benchmark.api.BenchmarkPeriod;
import com.evconnect.evcloud.reporting.benchmark.api.time.BenchmarkTimePeriodsBuilder;
import banchmark.controller.BenchmarkPageRequest;
import com.evconnect.evcloud.reporting.domain.ChargeSessionQuery;
import com.evconnect.evcloud.reporting.domain.DriverChargeSessionAggregation;
import com.evconnect.evcloud.reporting.service.ChargeSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.evconnect.evcloud.schedule.service.TimeUtils.getDaysBetween;
import static com.evconnect.evcloud.schedule.service.TimeUtils.toDate;

@Service
@RequiredArgsConstructor
public class BenchmarkServiceImpl implements BenchmarksService {
    private final ChargeSessionService chargeSessionService;

    @Override
    public BenchmarkPage getBenchmarkPage(String networkId, String organizationId, BenchmarkPageRequest request) {
        BenchmarkTimePeriodsBuilder.InternalTimePeriods periods = BenchmarkTimePeriodsBuilder
                .builder()
                .fromRequest(request)
                .buildPeriods();

        BenchmarkPeriod current = getBenchmarkPeriod(networkId, organizationId, periods.getCurrentStart(), periods.getCurrentEnd());
        BenchmarkPeriod prev = getBenchmarkPeriod(networkId, organizationId, periods.getPreviousStart(), periods.getPreviousEnd());

        return new BenchmarkPage()
                .setCurrent(current)
                .setPrevious(prev);
    }

    private BenchmarkPeriod getBenchmarkPeriod(String networkId, String organizationId, LocalDateTime start, LocalDateTime end) {
        int days = Math.max(getDaysBetween(start, end), 1);
        long currentStart = toDate(start).getTime();
        long currentEnd = toDate(end).getTime();

        DriverChargeSessionAggregation aggregation = chargeSessionService.getDriverChargeSessionAggregation(
                ChargeSessionQuery
                        .findByNetworkId(networkId)
                        .andDisconnectedDateBetween(currentStart, currentEnd)
                        .returnValidOnly(true));

        long count = aggregation.getCount();

        return new BenchmarkPeriod()
                .setTotalChargingSessions(count)
                .setAverageDailyChargingSessions(count / days)
                .setAverageChargeTimePerSession(aggregation.getChargeDuration() / count)
                .setAverageConnectionTimePerSession(aggregation.getConnectedDuration() / count);
    }
}