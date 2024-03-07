package com.evconnect.evcloud.reporting.cache.network.configuration;

import com.evconnect.evcloud.rest.network.data.sync.configuration.NetworkDataSyncConfiguration;
import com.evconnect.evcloud.rest.network.data.sync.task.TimeToExecute;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class NetworkCacheConfiguration extends NetworkDataSyncConfiguration {

    private static final long EXECUTION_FIXED_RATE = Duration.ofDays(1).toMillis();
    private static final String IDENTITY = "NetworkDataSyncIdentity";
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