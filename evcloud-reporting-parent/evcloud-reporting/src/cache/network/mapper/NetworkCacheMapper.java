package com.evconnect.evcloud.reporting.cache.network.mapper;

import com.evconnect.evcloud.reporting.cache.network.data.domain.Network;
import com.evconnect.rest.api.network.EvCloudNetwork;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface NetworkCacheMapper {

    Network toCacheModel(EvCloudNetwork network);
}