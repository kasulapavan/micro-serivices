package com.evconnect.evcloud.reporting.cache.manufacturer.service;

import com.evconnect.evcloud.reporting.cache.manufacturer.domain.Manufacturer;

import java.util.List;
import java.util.stream.Stream;

public interface ManufacturerCacheService {
    List<Manufacturer> getAllManufacturers();

    Stream<Manufacturer> streamAll();
}