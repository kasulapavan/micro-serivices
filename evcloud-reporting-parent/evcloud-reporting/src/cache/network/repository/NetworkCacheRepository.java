package com.evconnect.evcloud.reporting.cache.network.repository;

import com.evconnect.evcloud.data.cleanup.network.NetworkDataCleaner;
import com.evconnect.evcloud.reporting.cache.network.data.domain.Network;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NetworkCacheRepository extends MongoRepository<Network, String>, NetworkDataCleaner {

    Optional<Network> findByNetworkId(String networkId);

    @Override
    void deleteByNetworkId(String networkId);
}
