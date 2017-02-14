package com.zhangzl.study.core;

import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.List;

/**
 * Description  : 共享变量之累加变量
 * Class   Path : com.zhangzl.study.core
 * Create  User : zhangzuolong
 * Create  Time : 2017/2/13 18:07
 * Project Name : technology_sophisticated_study
 */
public class AccumulatorVariable {

    public static void main(String[] args) {


        SparkConf conf = new SparkConf().setAppName("AccumulatorVariable").setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        final Accumulator<Integer>  total = sc.accumulator(0);
        List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9);

        JavaRDD<Integer> numbers = sc.parallelize(numberList);
        numbers.foreach(new VoidFunction<Integer>() {
            public void call(Integer v1) throws Exception {
                total.add(v1);
            }
        });

        System.out.println("total == "+total);
        sc.close();

    }
}
