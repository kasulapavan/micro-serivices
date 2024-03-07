package com.evconnect.evcloud.reporting.cache.location.service.impl;

import com.evconnect.evcloud.reporting.cache.location.mapper.LocationCacheMapper;
import com.evconnect.evcloud.reporting.cache.location.repository.LocationCacheRepository;
import com.evconnect.evcloud.rest.location.data.sync.service.LocationDataSyncService;
import com.evconnect.rest.api.location.EvCloudLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
class LocationDataSyncServiceImpl implements LocationDataSyncService {
    private final LocationCacheRepository cacheRepository;
    private final LocationCacheMapper cacheMapper;

    @Override
    public void syncFullLocationData(Stream<EvCloudLocation> locationStream, long syncTimeStamp) {
        locationStream
                .map(cacheMapper::toCacheModel)
                .forEach(cacheRepository::save);
        cacheRepository.deleteAllByLastUpdatedBefore(new Date(syncTimeStamp));
    }

    @Override
    public void addLocation(EvCloudLocation location) {
        cacheRepository.insert(cacheMapper.toCacheModel(location));
    }

    @Override
    public void updateLocation(EvCloudLocation location) {
        cacheRepository.save(cacheMapper.toCacheModel(location));
    }

    @Override
    public void deleteLocation(EvCloudLocation location) {
        cacheRepository.deleteById(location.getExternalId());
    }
}