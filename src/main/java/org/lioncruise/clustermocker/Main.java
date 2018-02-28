package org.lioncruise.clustermocker;

import org.lioncruise.clustermocker.algorithm.DefaultScheduling;
import org.lioncruise.clustermocker.entities.Cluster;
import org.lioncruise.clustermocker.entities.Pod;
import org.lioncruise.clustermocker.utils.CSVOperation;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Pod> podList = CSVOperation.readInfo();

        Cluster cluster = new Cluster(1300, 64, 1);
        DefaultScheduling.schedule(cluster, podList);

        CSVOperation.writeInfo(cluster, podList);
    }
}
