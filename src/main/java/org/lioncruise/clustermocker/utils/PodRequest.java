package org.lioncruise.clustermocker.utils;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.api.model.Pod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PodRequest {
    public void createPod() {
        Config config = new ConfigBuilder().withMasterUrl("http://47.104.16.133:8080/").build();
        final KubernetesClient client = new DefaultKubernetesClient(config);

        Container container = new ContainerBuilder().withImage("busybox").
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
