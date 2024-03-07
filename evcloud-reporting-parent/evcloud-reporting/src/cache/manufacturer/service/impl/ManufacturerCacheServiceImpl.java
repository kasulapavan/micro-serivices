package com.evconnect.evcloud.reporting.cache.manufacturer.service.impl;

import com.evconnect.evcloud.reporting.cache.manufacturer.domain.Manufacturer;
import com.evconnect.evcloud.reporting.cache.manufacturer.repository.ManufacturerCacheRepository;
import com.evconnect.evcloud.reporting.cache.manufacturer.service.ManufacturerCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ManufacturerCacheServiceImpl implements ManufacturerCacheService {

    private final ManufacturerCacheRepository repository;

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return repository.findAll();
    }

    @Override
    public Stream<Manufacturer> streamAll() {
        return repository.streamAllBy();
    }
}