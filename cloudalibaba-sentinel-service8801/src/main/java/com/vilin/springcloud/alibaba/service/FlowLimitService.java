package com.vilin.springcloud.alibaba.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FlowLimitService {

    @SentinelResource("common")
    public void common(){
        log.info("called common method.");
    }
}
