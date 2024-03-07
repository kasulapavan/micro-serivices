package banchmark.service;

import com.evconnect.evcloud.reporting.benchmark.api.BenchmarkPage;
import banchmark.controller.BenchmarkPageRequest;

public interface BenchmarksService {
    BenchmarkPage getBenchmarkPage(String networkId, String organizationId, BenchmarkPageRequest request);
}