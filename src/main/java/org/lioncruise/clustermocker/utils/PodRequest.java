package org.lioncruise.clustermocker.utils;

import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

import java.util.*;

public class PodRequest {
    public void createPod() {
        Config config = new ConfigBuilder().withMasterUrl("http://47.104.16.133:8080/").build();
        final KubernetesClient client = new DefaultKubernetesClient(config);

        Map<String, Quantity> requestsMap = new HashMap<String, Quantity>();
        requestsMap.put("cpu", new Quantity("250m"));
        requestsMap.put("memory", new Quantity("64Mi"));

        Map<String, Quantity> limitsMap = new HashMap<String, Quantity>();
        limitsMap.put("cpu", new Quantity("500m"));
        limitsMap.put("memory", new Quantity("128Mi"));

        Container container = new ContainerBuilder().withImage("busybox").
                withNewResources().withRequests(requestsMap).withLimits(limitsMap).endResources().
                withCommand("sleep", "3600").withImagePullPolicy("IfNotPresent").withName("busybox").build();

        Pod pod = new PodBuilder().
                withApiVersion("v1").
                withNewMetadata().
                  withName("busybox").
                  withNamespace("default").
                endMetadata().
                withNewSpec().
                  withContainers(container).
                  withRestartPolicy("Always").
                endSpec().build();

        client.pods().create(pod);
    }

    public static void main(String[] args) {
        PodRequest p = new PodRequest();
        p.createPod();
    }
}
