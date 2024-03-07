package com.evconnect.evcloud.reporting.cache.manufacturer.service.impl;

import com.evconnect.evcloud.reporting.cache.manufacturer.mapper.ManufacturerCacheMapper;
import com.evconnect.evcloud.reporting.cache.manufacturer.repository.ManufacturerCacheRepository;
import com.evconnect.evcloud.rest.manufacturer.data.sync.service.ManufacturerDataSyncService;
import com.evconnect.rest.api.manufacturer.EvCloudManufacturer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
class ManufacturerDataSyncServiceImpl implements ManufacturerDataSyncService {
    private final ManufacturerCacheRepository cacheRepository;
    private final ManufacturerCacheMapper cacheMapper;

    @Override
    public void syncFullManufacturerData(Stream<EvCloudManufacturer> manufacturerStream) {
        manufacturerStream
                .map(cacheMapper::toCacheModel)
                .forEach(cacheRepository::save);
    }

    @Override
    public void addManufacturer(EvCloudManufacturer manufacturer) {
        cacheRepository.insert(cacheMapper.toCacheModel(manufacturer));
    }

    @Override
    public void updateManufacturer(EvCloudManufacturer manufacturer) {
        cacheRepository.save(cacheMapper.toCacheModel(manufacturer));
    }
}