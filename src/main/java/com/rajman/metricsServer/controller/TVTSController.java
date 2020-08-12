//package com.rajman.metricsServer.controller;
//
//import com.rajman.metricsServer.services.FetchData;
//import io.prometheus.client.Counter;
//import io.prometheus.client.Histogram;
//import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
//import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Controller
//public class TVTSController {
//    private FetchData fetchData;
//    public TVTSController(FetchData fetchData) {
//        this.fetchData = fetchData;
//    }
//    @GetMapping("/metrics")
//    private void tvtsCount(HttpServletResponse response) throws IOException {
////        return ResponseEntity.accepted().body("http_requests_total "+fetchData.tvts()+"\n");
//        response.sendRedirect("/actuator/prometheus");
////        return "forward:/actuator/prometheus";
//    }
//}
