package com.backbase.dbs.capabilities.extended.text.messaging.service.impl;

import com.backbase.dbs.capabilities.extended.text.messaging.service.OneTimePasswordStrategyService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class OneTimePasswordStrategyServiceImpl implements OneTimePasswordStrategyService {
    @Override
    public Integer generateOpt() {
        return ThreadLocalRandom.current().nextInt(1000, 10000);
    }
}
