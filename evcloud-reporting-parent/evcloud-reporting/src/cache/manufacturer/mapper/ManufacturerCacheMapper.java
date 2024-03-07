package com.evconnect.evcloud.reporting.cache.manufacturer.mapper;

import com.evconnect.evcloud.reporting.cache.manufacturer.domain.Manufacturer;
import com.evconnect.rest.api.manufacturer.EvCloudManufacturer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ManufacturerCacheMapper {
    Manufacturer toCacheModel(EvCloudManufacturer manufacturer);
}