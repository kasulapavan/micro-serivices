package banchmark.controller;

import com.evconnect.evcloud.reporting.benchmark.api.BenchmarkPage;
import banchmark.service.BenchmarksService;
import com.evconnect.evcloud.reporting.controller.ReportingApi;
import com.evconnect.plugin.enums.PluginName;
import com.evconnect.evcloud.security.export.Authorized;
import com.evconnect.evcloud.security.plugin.PluginEnabled;
import com.evconnect.security.enums.PermissionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.evconnect.evcloud.security.common.PathVariables.NETWORK_ID;
import static com.evconnect.evcloud.security.common.PathVariables.ORGANIZATION_ID;

@RestController
@RequiredArgsConstructor
public class BenchmarkController {
    private final BenchmarksService benchmarksService;

    @Authorized(PermissionType.VIEW_REPORTS)
    @PluginEnabled(PluginName.DASHBOARD_BENCHMARK)
    @GetMapping(path = ReportingApi.PATH_NETWORK_DASHBOARD_BENCHMARKS, produces = MediaType.APPLICATION_JSON_VALUE)
    public BenchmarkPage getChargingAttemptDashboardReportForNetwork(@PathVariable(NETWORK_ID) String networkId,
                                                                     @ModelAttribute BenchmarkPageRequest request) {
        return benchmarksService.getBenchmarkPage(networkId, null, request);
    }

    @Authorized(PermissionType.VIEW_REPORTS)
    @PluginEnabled(PluginName.DASHBOARD_BENCHMARK)
    @GetMapping(path = ReportingApi.PATH_ORGANIZATION_DASHBOARD_BENCHMARKS, produces = MediaType.APPLICATION_JSON_VALUE)
    public BenchmarkPage getChargingAttemptDashboardReportForOrganization(@PathVariable(NETWORK_ID) String networkId,
                                                                          @PathVariable(ORGANIZATION_ID) String organizationId,
                                                                          @ModelAttribute BenchmarkPageRequest request) {
        return benchmarksService.getBenchmarkPage(networkId, organizationId, request);
    }
}