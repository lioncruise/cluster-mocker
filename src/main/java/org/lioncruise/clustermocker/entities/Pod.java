package org.lioncruise.clustermocker.entities;

/**
 * @Author Shijun Qin qinshijun16@otcaix.iscas.ac.cn
 * @Date 2018/2/28 18:36
 */
public class Pod {
    private int id = seq;
    private int cpuRequest;
    private double memRequest;
    private boolean isScheduled = false;
    private int machineId = 0;
    private int deploymentId = 0;

    public void setId(int id) {
        this.id = id;
    }

    public void setCpuRequest(int cpuRequest) {
        this.cpuRequest = cpuRequest;
    }

    public void setMemRequest(double memRequest) {
        this.memRequest = memRequest;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
        this.isScheduled = true;
    }

    public int getId() {
        return id;
    }

    public int getCpuRequest() {
        return cpuRequest;
    }

    public double getMemRequest() {
        return memRequest;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public int getMachineId() {
        return machineId;
    }

    public int getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(int deploymentId) {
        this.deploymentId = deploymentId;
    }

    public Pod(int cpuRequest, double memRequest) {
        this.cpuRequest = cpuRequest;
        this.memRequest = memRequest;
        seq++;
    }

    private static int seq = 1;
}
