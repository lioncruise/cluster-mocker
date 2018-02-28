package org.lioncruise.clustermocker;

import org.lioncruise.clustermocker.algorithm.DefaultScheduling;
import org.lioncruise.clustermocker.entities.Cluster;
import org.lioncruise.clustermocker.entities.Pod;
import org.lioncruise.clustermocker.utils.CSVOperation;

import java.util.List;

/**
 * @Author Shijun Qin qinshijun2016@otcaix.iscas.ac.cn
 * @Date 2018/2/28 18:54
 */
public class Main {
    public static void main(String[] args) {
        List<Pod> podList = CSVOperation.readInfo();

        Cluster cluster = new Cluster(1300, 64, 1);
        DefaultScheduling.schedule(cluster, podList);

        CSVOperation.writeInfo(cluster, podList);
    }
}
