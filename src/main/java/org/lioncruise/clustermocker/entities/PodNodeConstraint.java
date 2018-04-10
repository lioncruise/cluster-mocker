package org.lioncruise.clustermocker.entities;

/**
 * @Author Shijun Qin qinshijun16@otcaix.iscas.ac.cn
 * @Date 2018/4/10 19:15
 */
public class PodNodeConstraint {
    private int id = seq;
    private int mandatory = 0;  // 强制性：软约束、硬约束
    private int affinity = 0;  // 亲和性：亲和、互斥

    private int podLabelId;
    private int nodeLabelId;

    public static final int HARD = 1;
    public static final int SOFT = 2;
    public static final int AFFINITY = 1;
    public static final int MUTEX = 2;

    public static int seq = 1;

    {
        seq++;
    }

    public PodNodeConstraint(int mandatory, int affinity, int podLabelId, int nodeLabelId) {
        this.mandatory = mandatory;
        this.affinity = affinity;
        this.podLabelId = podLabelId;
        this.nodeLabelId = nodeLabelId;
    }
}
