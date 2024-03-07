package com.evconnect.evcloud.reporting.cache.manufacturer.repository;

import com.evconnect.evcloud.data.cleanup.manufacturer.ManufacturerDataCleaner;
import com.evconnect.evcloud.reporting.cache.manufacturer.domain.Manufacturer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.stream.Stream;

public interface ManufacturerCacheRepository extends MongoRepository<Manufacturer, String>, ManufacturerDataCleaner {
    Stream<Manufacturer> streamAllBy();

    @Override
    void deleteByManufacturerId(String manufacturerId);
}