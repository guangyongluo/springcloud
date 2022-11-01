package com.vilin.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.vilin.springcloud.alibaba.service.FlowLimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @Autowired
    private FlowLimitService flowLimitService;

    @GetMapping("/testA")
    public String testA(){
        try {
            TimeUnit.MICROSECONDS.sleep(800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "----return testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "----return testB";
    }

    @GetMapping("/testC")
    public String testC(){
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info(Thread.currentThread().getName() + " called testC method");
        return Thread.currentThread().getName() + " called testC method";
    }

    @GetMapping("/testD")
    public String testD(){
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("testD test fall back service...");
        return "testD test fall back service...";
    }

    @GetMapping("/testE")
    public String testE(){
        log.info("testE test exception rate");
        int age = 10 / 0;
        return "testE test exception rate...";
    }

    @GetMapping("/testF")
    public String testF(){
        log.info("testE test exception number");
        int age = 10 / 0;
        return "testE test exception number...";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "dealHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1, @RequestParam(value = "p2", required = false) String p2){
        log.info("testHotKey test hotKey");
        return "testHotKey test hotKey...";
    }

    public String dealHotKey(String p1, String p2, BlockException exception){
        return "dealHotKey test fall back";
    }


    @GetMapping("/test1")
    public String test1(){
        flowLimitService.common();
        return "test1 return";
    }

    @GetMapping("/test2")
    public String test2(){
        flowLimitService.common();
        return "test2 return";
    }
}
