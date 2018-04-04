package org.lioncruise.clustermocker.entities;

/**
 * @Author Shijun Qin qinshijun16@otcaix.iscas.ac.cn
 * @Date 2018/2/28 18:36
 */
public class Machine {
    private int id = seq;
    private int cpuMax = 64;
    private double memMax = 1;
    private int cpuUsed = 0;
    private double memUsed = 0;

    public void setId(int id) {
        this.id = id;
    }

    public void setCpuMax(int cpuMax) {
        this.cpuMax = cpuMax;
    }

    public void setMemMax(double memMax) {
        this.memMax = memMax;
    }

    public void setCpuUsed(int cpuUsed) {
        this.cpuUsed = cpuUsed;
    }

    public void setMemUsed(double memUsed) {
        this.memUsed = memUsed;
    }

    public int getId() {
        return id;
    }

    public int getCpuMax() {
        return cpuMax;
    }

    public double getMemMax() {
        return memMax;
    }

    public int getCpuUsed() {
        return cpuUsed;
    }

    public double getMemUsed() {
        return memUsed;
    }

    public Machine() {
        seq++;
    }

    public Machine(int cpuMax, double memMax) {
        this.cpuMax = cpuMax;
        this.memMax = memMax;
        seq++;
    }

    private static int seq = 1;
}
