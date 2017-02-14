package com.zhangzl.study.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.broadcast.Broadcast;

import java.util.Arrays;
import java.util.List;

/**
 * Description  : 共享变量之广播变量
 *                -----只能读，不能写
 * Class   Path : com.zhangzl.study.core
 * Create  User : zhangzuolong
 * Create  Time : 2017/2/13 17:02
 * Project Name : technology_sophisticated_study
 */
public class BroadcastVariable {

    public static void main(String[] args) {
        /**
         * 第一步：创建SparkConf对象，设置Spark应用的配置信息
         * 使用SetMaster()可以设置Spark应用程序要连接的Spark集群的master节点的url
         * 但是如果设置为local，则代表在本地运行。
         */
        SparkConf conf = new SparkConf().setAppName("BroadcastVariable").setMaster("local");

        /**
         * 第二步：创建JavaSparkContext对象
         * 在spark中，SparkContext是Spark所有功能的一个入口，你无论是java，scala，甚至是python编写
         * 都必须要有一个SparkContext，它的主要作用，包括初始化Spark应用程序所需的一些核心组件，包括调度器
         * （DAGScheduler，TaskScheduler）还会去到Spark Master节点上进行注册，等等
         * 一句话，SparkContext是Spark应用中，可以说是最最重要的一个对象。
         * 但是呢，在Spark中，编写不同类型的Spark应用程序，使用的SparkContext是不同的，如果使用scala，则是原生的SparkContext
         * 使用java 则是JavaSparkContext
         * 如果开发Spark SQL程序，那么就是SqlCoontext，HiveContext
         * 如果开发Spark Streaming程序，那么就是它独有的SparkContext
         *
         */
        JavaSparkContext sc = new JavaSparkContext(conf);

        /**
         * 在java中，创建共享变量，就是调用SparkContext的broadcast（）方法
         * 获取的返回结果是Broadcast<T>类型
         */
        final int factor = 3;
        final Broadcast<Integer> factorBroadcast = sc.broadcast(factor);

        List<Integer> numberList = Arrays.asList(1,2,3,4,5,6);

        JavaRDD<Integer> numbers = sc.parallelize(numberList);
        JavaRDD<Integer> factorNumbers = numbers.map(new Function<Integer, Integer>() {
            public Integer call(Integer v1) throws Exception {
                return v1 * factorBroadcast.value();
            }
        });

        factorNumbers.foreach(new VoidFunction<Integer>() {
            public void call(Integer v1) throws Exception {
                System.out.println(v1+" ");
            }
        });
        sc.close();
    }

}
