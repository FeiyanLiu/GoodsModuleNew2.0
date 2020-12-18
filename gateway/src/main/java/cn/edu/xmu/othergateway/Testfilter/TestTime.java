package cn.edu.xmu.othergateway.Testfilter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class TestTime implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        return (exchange, chain) -> {
//            //调用请求之前统计时间
//            Long startTime = System.currentTimeMillis();
//            return chain.filter(exchange).then().then(Mono.fromRunnable(() -> {
//                //调用请求之后统计时间
//                Long endTime = System.currentTimeMillis();
//                System.out.println(
//                        exchange.getRequest().getURI().getRawPath() + ", cost time : " + (endTime - startTime) + "ms");
//            }));
//        };
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
