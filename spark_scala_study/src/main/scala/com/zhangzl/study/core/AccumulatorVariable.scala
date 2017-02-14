package com.zhangzl.study.core

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description  : 共享变量之累加变量
  * Class   Path : com.zhangzl.study.core
  * Create  User : zhangzuolong
  * Create  Time : 2017/2/14 14:28
  * Project Name : technology_sophisticated_study
  */
object AccumulatorVariable {
    def main(args: Array[String]): Unit = {

        val conf = new SparkConf().setAppName("AccumulatorVariable").setMaster("local")
        val sc = new SparkContext(conf)

        /**
          * 定义共享变量之累加变量
          */
        val accumulator = sc.accumulator(0)
        val initRdds = sc.parallelize(Array(1,2,3,4,5,6,7),1)
        initRdds.foreach(number => accumulator.add(number))
        println("total == "+ accumulator)
    }

}
