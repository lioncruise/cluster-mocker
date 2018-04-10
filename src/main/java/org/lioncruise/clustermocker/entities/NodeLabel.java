package org.lioncruise.clustermocker.entities;

/**
 * @Author Shijun Qin qinshijun16@otcaix.iscas.ac.cn
 * @Date 2018/4/10 19:16
 */
public class NodeLabel {
    private int id;
    private int labelId;
    private int nodeId;

    public static final int KEY_NODE = 1;  // 关键node能够容纳更多的关键pod
    public static final int NON_KEY_NODE = 2;

    // 下面4种node表示pod和node之间硬约束下的亲和性、互斥性和软约束下的亲和性、互斥性
    public static final int NODE_TYPE_A = 3;
    public static final int NODE_TYPE_B = 4;
    public static final int NODE_TYPE_C = 5;
    public static final int NODE_TYPE_D = 6;
}
