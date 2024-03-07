package com.evconnect.evcloud.reporting.cache.location.configuration;

import com.evconnect.evcloud.rest.location.data.sync.configuration.LocationDataSyncConfiguration;
import com.evconnect.evcloud.rest.location.data.sync.task.TimeToExecute;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class LocationCacheConfiguration extends LocationDataSyncConfiguration {

    private static final long EXECUTION_FIXED_RATE = Duration.ofDays(1).toMillis();
    private static final String IDENTITY = "LocationDataSyncIdentity";
    private static final TimeToExecute TIME_TO_EXECUTE = new TimeToExecute(1, 0);

    @Override
    public String getIdentityForSyncTask() {
        return IDENTITY;
    }

    @Override
    public long getExecutionFixedRate() {
        return EXECUTION_FIXED_RATE;
    }

    @Override
    public TimeToExecute getTimeToExecuteSyncTask() {
        return TIME_TO_EXECUTE;
    }
}