package com.evconnect.evcloud.reporting.cache.network.service.impl;

import com.evconnect.evcloud.reporting.cache.network.mapper.NetworkCacheMapper;
import com.evconnect.evcloud.reporting.cache.network.repository.NetworkCacheRepository;
import com.evconnect.evcloud.rest.network.data.sync.service.NetworkDataSyncService;
import com.evconnect.rest.api.network.EvCloudNetwork;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
class NetworkDataSyncServiceImpl implements NetworkDataSyncService {
    private final NetworkCacheRepository cacheRepository;
    private final NetworkCacheMapper cacheMapper;

    @Override
    public void syncFullNetworkData(Stream<EvCloudNetwork> networkStream) {
        networkStream
                .map(cacheMapper::toCacheModel)
                .forEach(cacheRepository::save);
    }

    @Override
    public void addNetwork(EvCloudNetwork network) {
        cacheRepository.insert(cacheMapper.toCacheModel(network));
    }

    @Override
    public void updateNetwork(EvCloudNetwork network) {
        cacheRepository.save(cacheMapper.toCacheModel(network));
    }
}