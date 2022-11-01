package com.vilin.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.vilin.springcloud.alibaba.handler.MyHandler;
import com.vilin.springcloud.entities.CommonResult;
import com.vilin.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult<>(200, "current-limiting by resource name", new Payment(1l, "serial001"));
    }

    public CommonResult handleException(BlockException exception){
        return new CommonResult(400, exception.getClass().getCanonicalName());
    }

    @GetMapping("/byURL")
    @SentinelResource("byURL")
    public CommonResult byURL(){
        return new CommonResult<>(200, "current-limiting by URL", new Payment(2l, "serial002"));
    }

    @GetMapping("/customBlockHandler")
    @SentinelResource(value = "customBlockHandler", blockHandlerClass = MyHandler.class, blockHandler = "handleException2")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200, "custom defined current-limiting", new Payment(3l, "serial003"));
    }
}
