package org.lioncruise.clustermocker.utils;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.Quantity;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.lioncruise.clustermocker.entities.Pod;
import org.lioncruise.clustermocker.entities.PodLabel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Shijun Qin qinshijun16@otcaix.iscas.ac.cn
 * @Date 2018/4/9 22:13
 */
public class ClusterDataLabelOperation {
    public static void main(String[] args) {
        List<Pod> podList = CSVOperation.readInfo();

        // 假设一个deployment有4个pod。实际情况是一个deployment具有不定的
        int did = 4;
        for (Pod pod : podList) {
            pod.setDeploymentId(did++/4);
            System.out.println(pod.getDeploymentId());
        }

        List<PodLabel> podLabels = new ArrayList<PodLabel>();

        for (int i = 0; i < podList.size(); i++) {
            // 给pod打上互斥性亲和性的标签
            int podId = podList.get(i).getId();
            if (podId <= 1000) {
                podLabels.add(new PodLabel(PodLabel.POD_TYPE_A, podId));
            } else if (podId <= 2000) {
                podLabels.add(new PodLabel(PodLabel.POD_TYPE_B, podId));
            } else if (podId <= 3000) {
                podLabels.add(new PodLabel(PodLabel.POD_TYPE_C, podId));
            } else if (podId <= 4000) {
                podLabels.add(new PodLabel(PodLabel.POD_TYPE_D, podId));
            }

            // 标识关键应用。暂定3000个关键应用
            if (podId >= 2501 && podId <= 5500) {
                podLabels.add(new PodLabel(PodLabel.KEY_POD, podId));
            }

            Pod pod = podList.get(0);

            Config config = new ConfigBuilder().withMasterUrl("http://47.104.16.133:8080/").build();
            final KubernetesClient client = new DefaultKubernetesClient(config);

            Map<String, Quantity> requestsMap = new HashMap<String, Quantity>();
            requestsMap.put("cpu", new Quantity(pod.getCpuRequest() * 250 + "m"));
            requestsMap.put("memory", new Quantity((int) (16 * 1024 * pod.getMemRequest()) + "Mi"));

            Map<String, Quantity> limitsMap = new HashMap<String, Quantity>();
            limitsMap.put("cpu", new Quantity(pod.getCpuRequest() * 250 + "m"));
            limitsMap.put("memory", new Quantity((int) (16 * 1024 * pod.getMemRequest()) + "Mi"));

            Container container = new ContainerBuilder().withImage("busybox").
                    withNewResources().withRequests(requestsMap).withLimits(limitsMap).endResources().
                    withCommand("sleep", "3600").withImagePullPolicy("IfNotPresent").
                    withName("test-" + pod.getDeploymentId() + "-" + pod.getId()).build();

            Map<String, String> podLabelMap = new HashMap<String, String>();
            podLabelMap.put("podType", "A");

            io.fabric8.kubernetes.api.model.Pod pod1 = new PodBuilder().
                    withApiVersion("v1").
                    withNewMetadata().
                    withName("test-" + pod.getDeploymentId() + "-" + pod.getId()).
                    withNamespace("default").
                    withLabels(podLabelMap).
                    endMetadata().
                    withNewSpec().
                    withContainers(container).
                    withRestartPolicy("Always").
                    endSpec().build();

            client.pods().create(pod1);
        }
    }
}
