package com.rajman.metricsServer.services;


import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import io.kubernetes.client.util.Config;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@NoArgsConstructor
@Service
public class FetchData {
    private int lastCount = 0;

    @SneakyThrows
    @Scheduled(fixedRate = 100_000)
    public int tvts() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "getData.sh");
        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println(Integer.valueOf(output.substring(1, output.length() - 2)));
                this.lastCount = Integer.parseInt(output.substring(1, output.length() - 2));
                test(this.lastCount);
                return lastCount;
            } else {
                //abnormal...
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return this.lastCount;
        }
        return this.lastCount;
    }

    @SneakyThrows
    public void test(int reqPerSec) {
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);


        CoreV1Api api = new CoreV1Api();
        V1ReplicationControllerList list = api.listReplicationControllerForAllNamespaces(null, null, null, null, null, null, null, 50, null);
        V1Scale v1Scale = new V1Scale();
        V1ScaleSpec v1ScaleSpec = new V1ScaleSpec();
//        api.replaceNamespacedReplicationControllerScale("nginx","default",);
//        V1ServiceList list = api.listServiceForAllNamespaces(null,null,null,null,10,null,null,50,Boolean.TRUE);

        V1HorizontalPodAutoscaler v1HorizontalPodAutoscaler = new V1HorizontalPodAutoscaler();
//        v1HorizontalPodAutoscaler.
        V1HorizontalPodAutoscalerSpec v1HorizontalPodAutoscalerSpec = new V1HorizontalPodAutoscalerSpec();
//        v1HorizontalPodAutoscalerSpec.
        for (V1ReplicationController item : list.getItems()) {
//            Objects.requireNonNull(item.getSpec()).setReplicas(5);
//

            System.out.println(item.getSpec().getReplicas());
            v1ScaleSpec.setReplicas((int) Math.ceil(reqPerSec / 300D));
            v1Scale.setSpec(v1ScaleSpec);
            v1Scale.setApiVersion(item.getApiVersion());
            v1Scale.setMetadata(item.getMetadata());
            v1Scale.setKind(item.getKind());
//            v1Scale.setStatus(item.getStatus());
            api.replaceNamespacedReplicationControllerScale("nginx", "default", v1Scale, null, null, null);
        }
    }
}
