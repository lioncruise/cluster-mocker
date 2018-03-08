# cluster-mocker

Cluster-mocker is a simulator for cluster scheduling, currently supporting long-running online service's simulation scheduling. Cluster-mocker will use [alibaba/clusterdata](https://github.com/alibaba/clusterdata) as input, which contains online service containers and batch jobs in Alibaba's production cluster during 12h. The basic workflow of the simulator is mainly 3 steps: 1. read information of the containers and batch jobs to be scheduled from the CSV file; 2. choose a scheduling model provided by cluster-mocker and run the input data in the selected scheduling model; 3. ouput the simulated scheduling result into some CSV files.

Currently, we just implement the basic workflow of simulated cluster scheduling. In the next step, we will implement features as the following list:

* multiple scheduling algorithm surpport
* scheduling result evaluation
* incremental scheduling
* scheduling based on graph model
* accessing Kubernetes as the third party scheduler plugin

The following diagram descirbes cluster-mocker's basic architecture:

![](http://7xpq8u.com1.z0.glb.clouddn.com/cluster-mocker.png)

If you have any other ideas or questions on cluster-mocker, you can write a GitHub issue on [isdream/cloudnativesim/issues](https://github.com/isdream/cloudnativesim/issues).