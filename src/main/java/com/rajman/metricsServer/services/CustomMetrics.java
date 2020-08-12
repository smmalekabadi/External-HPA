package com.rajman.metricsServer.services;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import javax.annotation.PostConstruct;


@Component
@RestController
@Slf4j
public class CustomMetrics {
    private final MeterRegistry meterRegistry;
    FetchData fetchData;

    public CustomMetrics(MeterRegistry meterRegistry, FetchData fetchData) {
        this.meterRegistry = meterRegistry;
        this.fetchData = fetchData;
    }
//    @Scheduled(fixedRate = 25000)
    public String test(){
        Counter.builder("tvts-request-per-sec").description("this is for tvts").register(meterRegistry).increment(fetchData.tvts());
        return meterRegistry.get("tvts-request-per-sec").toString();
    }
}
