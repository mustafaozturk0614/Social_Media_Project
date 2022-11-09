package com.bilgeadam.service;

import com.bilgeadam.repository.IOnlineRepository;
import com.bilgeadam.repository.entity.Online;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class OnlineService extends ServiceManager<Online, String> {


    private final IOnlineRepository onlineRepository;

    public OnlineService(IOnlineRepository onlineRepository) {
        super(onlineRepository);
        this.onlineRepository = onlineRepository;
    }
}
