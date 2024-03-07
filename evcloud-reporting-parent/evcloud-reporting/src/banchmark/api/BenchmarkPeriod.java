package com.evconnect.evcloud.reporting.benchmark.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class BenchmarkPeriod {
    private long totalChargingSessions = 0;
    private long averageDailyChargingSessions = 0;
    private long averageConnectionTimePerSession = 0;
    private long averageChargeTimePerSession = 0;
}