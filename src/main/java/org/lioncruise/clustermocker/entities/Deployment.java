package org.lioncruise.clustermocker.entities;

/**
 * @Author Shijun Qin qinshijun16@otcaix.iscas.ac.cn
 * @Date 2018/4/10 19:16
 */
public class Deployment {
    private int id;

    public Deployment(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
