package org.lioncruise.clustermocker.algorithm;

import java.util.*;
import org.lioncruise.clustermocker.entities.Cluster;
import org.lioncruise.clustermocker.entities.Machine;
import org.lioncruise.clustermocker.entities.Pod;

/**
 * @Author Shijun Qin qinshijun2016@otcaix.iscas.ac.cn
 * @Date 2018/2/28 18:53
 */
public class DefaultScheduling {
    public static void schedule(Cluster cluster, List<Pod> podList) {
        for (Pod pod : podList) {
            List<Machine> availableMachines = predicate(cluster, pod);
            priority(availableMachines, pod);
        }

        System.out.println("Simulation scheduling has finished!");
    }

    public static List<Machine> predicate(Cluster cluster, Pod pod) {
        List<Machine> machines = cluster.getMachines();

        List<Machine> availableMachines = new ArrayList<Machine>();

        for (Machine machine : machines) {
            if (machine.getCpuUsed() + pod.getCpuRequest() <= machine.getCpuMax() &&
                    machine.getMemUsed() + pod.getMemRequest() <= machine.getMemMax()) {
                availableMachines.add(machine);
            }
        }

        return availableMachines;
    }

    public static void priority(List<Machine> machines, Pod pod) {
        double maxScore = 0;
        int selectedMachineId = -1;
        Machine selectedMachine = null;

        for (Machine machine : machines) {
            int availableCPU = machine.getCpuMax() - machine.getCpuUsed();
            double availableMem = machine.getMemMax() - machine.getMemUsed();
            int factor1 = 1;
            int factor2 = 64;
            double score = factor1 * availableCPU + factor2 * availableMem;
            if (score > maxScore) {
                maxScore = score;
                selectedMachineId = machine.getId();
                selectedMachine = machine;
            }
        }

        if (selectedMachineId > 0) {
            pod.setMachineId(selectedMachineId);
            selectedMachine.setCpuUsed(selectedMachine.getCpuUsed() + pod.getCpuRequest());
            selectedMachine.setMemUsed(selectedMachine.getMemUsed() + pod.getMemRequest());
        }

    }
}
