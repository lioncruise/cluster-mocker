package org.lioncruise.clustermocker.entities;

/**
 * @Author Shijun Qin qinshijun16@otcaix.iscas.ac.cn
 * @Date 2018/4/10 19:15
 */
public class PodPodConstraint {
    private int id;
    private int mandatory;  // 强制性：软约束、硬约束
    private int affinity;  // 亲和性：亲和、互斥

    private int podLabelId1;
    private int podLabelId2;

    public static final int HARD = 1;
    public static final int SOFT = 2;
    public static final int AFFINITY = 1;
    public static final int MUTEX = 2;

    public static int seq = 1;

    {
        seq++;
    }

    public PodPodConstraint(int mandatory, int affinity, int podLabelId1, int podLabelId2) {
        this.mandatory = mandatory;
        this.affinity = affinity;
        this.podLabelId1 = podLabelId1;
        this.podLabelId2 = podLabelId2;
    }
}
