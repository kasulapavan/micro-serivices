package com.evconnect.evcloud.reporting.cache.location.mapper;

import com.evconnect.evcloud.reporting.cache.location.data.domain.Location;
import com.evconnect.rest.api.location.EvCloudLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LocationCacheMapper {

    @Mapping(target = "lastUpdated", expression = "java(new java.util.Date())")
    Location toCacheModel(EvCloudLocation location);
}