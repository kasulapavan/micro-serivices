package com.evconnect.evcloud.reporting.cache.location.repository;

import com.evconnect.evcloud.data.cleanup.network.NetworkDataCleaner;
import com.evconnect.evcloud.reporting.cache.location.data.domain.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface LocationCacheRepository extends MongoRepository<Location, String>, NetworkDataCleaner {

    List<Location> findByExternalIdIn(Collection<String> locationExternalIds);

    Optional<Location> findByExternalId(String externalId);

    List<Location> findByNetworkIdAndUtilityProgramUtilityId(String networkId, String utilityId);

    void deleteAllByLastUpdatedBefore(Date eventTimeStamp);

    Stream<Location> findAllByNetworkIdAndAddressStateInAndHideOnMapFalse(String networkId, List<String> states);

    @Query("{ $or: [ {'utilityRate.isFlatCost': true}, {'utilityRate.tariffId': {$exists:true} } ]}")
    Stream<Location> streamAllForCostCalculation();

    @Override
    void deleteByNetworkId(String networkId);
}