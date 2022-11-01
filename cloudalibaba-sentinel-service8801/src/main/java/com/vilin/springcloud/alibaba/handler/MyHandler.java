package com.vilin.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.vilin.springcloud.entities.CommonResult;

public class MyHandler {

    public static CommonResult handleException1(BlockException exception){
        return new CommonResult(400, exception.getClass().getCanonicalName() + "global exception handler1");
    }

    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(400, exception.getClass().getCanonicalName() + "global exception handler2");
    }
}
