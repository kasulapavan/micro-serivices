package com.evconnect.evcloud.reporting.benchmark.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class BenchmarkPage {
    private BenchmarkPeriod current;
    private BenchmarkPeriod previous;
}