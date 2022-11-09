package com.bilgeadam.service;

import com.bilgeadam.repository.IFollowerRepository;
import com.bilgeadam.repository.entity.Follower;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class FollowerService extends ServiceManager<Follower, String> {


    private final IFollowerRepository followerRepository;

    public FollowerService(IFollowerRepository followerRepository) {
        super(followerRepository);
        this.followerRepository = followerRepository;
    }
}
