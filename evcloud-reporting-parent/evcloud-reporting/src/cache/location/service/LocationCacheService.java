package com.evconnect.evcloud.reporting.cache.location.service;

import com.evconnect.evcloud.common.exception.NotFoundException;
import com.evconnect.evcloud.reporting.cache.location.data.domain.Location;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface LocationCacheService {
    List<Location> findByExternalIdIn(Collection<String> locationExternalIds);

    Optional<Location> getByExternalId(String externalId);

    List<Location> findByNetworkIdAndUtilityId(String networkId, String utilityId);

    @Retryable(value = NotFoundException.class, maxAttempts = 2, backoff = @Backoff(delay = 500))
    Location findByExternalIdWithRetryOrDefault(String locationId);

    @Recover
    Location recoverLocationNotFound(NotFoundException ex, String locationId);

    Stream<Location> streamOfLocationsByNetworkIdWhereStateInAndNotHidden(String networkId, List<String> states);
}