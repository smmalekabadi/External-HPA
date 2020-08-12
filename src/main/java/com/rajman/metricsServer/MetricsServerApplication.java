package com.rajman.metricsServer;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MetricsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetricsServerApplication.class, args);
    }

}
