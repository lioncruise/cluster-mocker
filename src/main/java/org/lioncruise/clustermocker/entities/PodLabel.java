package org.lioncruise.clustermocker.entities;

/**
 * @Author Shijun Qin qinshijun16@otcaix.iscas.ac.cn
 * @Date 2018/4/10 19:37
 */
public class PodLabel {
    private int id = seq;
    private int labelId;
    private int podId;

    public static final int KEY_POD = 1;
    public static final int NON_KEY_POD = 2;

    // 用下面的4个label来表示pod和pod之间的硬约束下的亲和性互斥性和软约束下的亲和性互斥性
    public static final int POD_TYPE_A = 3;
    public static final int POD_TYPE_B = 4;
    public static final int POD_TYPE_C = 5;
    public static final int POD_TYPE_D = 6;

    public PodLabel(int labelId, int podId) {
        this.labelId = labelId;
        this.podId = podId;
    }

    {
        seq++;
    }

    public static int seq = 1;
}
