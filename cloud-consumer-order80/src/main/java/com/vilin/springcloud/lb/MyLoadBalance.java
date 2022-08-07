package com.vilin.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLoadBalance implements LoadBalance {

  private AtomicInteger atomicInteger = new AtomicInteger(0);

  public final int getAndIncrement() {
    int current;
    int next;
    do {
      current = atomicInteger.get();
      next = current > Integer.MAX_VALUE ? 0 : current + 1;
    }while(!this.atomicInteger.compareAndSet(current, next));
    System.out.println("******* access number ********:" + next);
    return next;
  }

  @Override
  public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
    final int i = getAndIncrement() % serviceInstances.size();
    return serviceInstances.get(i);
  }
}
