package com.vilin.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
    RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
    routes
        .route(
            "com.vilin.outer.net.baidu.guonei",
            route -> route.path("/guonei").uri("http://news.baidu.com"))
        .route(
            "com.vilin.outer.nei.baidu.gouji",
            route -> route.path("/guoji").uri("http://news.baidu.com"))
        .build();
    return routes.build();
  }
}
