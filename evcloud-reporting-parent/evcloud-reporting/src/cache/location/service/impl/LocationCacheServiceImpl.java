package com.evconnect.evcloud.reporting.cache.location.service.impl;

import com.evconnect.evcloud.common.exception.NotFoundException;
import com.evconnect.evcloud.reporting.cache.CacheConstant;
import com.evconnect.evcloud.reporting.cache.location.data.domain.Location;
import com.evconnect.evcloud.reporting.cache.location.repository.LocationCacheRepository;
import com.evconnect.evcloud.reporting.cache.location.service.LocationCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.evconnect.evcloud.common.exception.NotFoundException.MessageKey.LOCATION_NOT_FOUND_WITH_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationCacheServiceImpl implements LocationCacheService {
    private final LocationCacheRepository repository;

    @Override
    public List<Location> findByExternalIdIn(Collection<String> locationExternalIds) {
        return repository.findByExternalIdIn(locationExternalIds);
    }

    @Override
    public Optional<Location> getByExternalId(String externalId) {
        return repository.findByExternalId(externalId);
    }

    @Override
    public List<Location> findByNetworkIdAndUtilityId(String networkId, String utilityId) {
        return repository.findByNetworkIdAndUtilityProgramUtilityId(networkId, utilityId);
    }

    @Override
    public Location findByExternalIdWithRetryOrDefault(String locationId) {
        return repository.findByExternalId(locationId)
                .orElseThrow(() -> new NotFoundException(LOCATION_NOT_FOUND_WITH_ID, locationId));
    }

    @Override
    public Location recoverLocationNotFound(NotFoundException ex, String locationId) {
        log.warn(ex.getMessage());
        return getDeletedLocation(locationId);
    }

    @Override
    public Stream<Location> streamOfLocationsByNetworkIdWhereStateInAndNotHidden(String networkId, List<String> states) {
        return repository.findAllByNetworkIdAndAddressStateInAndHideOnMapFalse(networkId, states);
    }

    private Location getDeletedLocation(String externalId) {
        Location location = new Location();
        location.setExternalId(externalId);
        location.setName(CacheConstant.DELETED);
        return location;
    }
}