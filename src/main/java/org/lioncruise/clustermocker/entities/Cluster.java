package org.lioncruise.clustermocker.entities;

import java.util.*;

/**
 * @Author Shijun Qin qinshijun2016@otcaix.iscas.ac.cn
 * @Date 2018/2/28 18:36
 */
public class Cluster {
    private int initCapacity = 0;
    private int currCapacity = 0;
    private List<Machine> machines = new ArrayList<Machine>();

    public void setInitCapacity(int initCapacity) {
        this.initCapacity = initCapacity;
    }

    public void setCurrCapacity(int currCapacity) {
        this.currCapacity = currCapacity;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public int getInitCapacity() {
        return initCapacity;
    }

    public int getCurrCapacity() {
        return currCapacity;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public Cluster() {}

    public Cluster(int n) {
        this.initCapacity = n;
        this.currCapacity = n;

        for (int i = 0; i < n; i++) {
            this.machines.add(new Machine());
        }
    }

    public Cluster(int n, int cpu, int mem) {
        this.initCapacity = n;
        this.currCapacity = n;

        for (int i = 0; i < n; i++) {
            this.machines.add(new Machine(cpu, mem));
        }
    }
}
