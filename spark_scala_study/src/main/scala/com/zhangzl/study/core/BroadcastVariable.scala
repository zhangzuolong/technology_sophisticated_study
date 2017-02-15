package com.zhangzl.study.core

import org.apache.spark.{SparkConf, SparkContext}


/**
  * Description  : 共享变量之广播变量（只读）
  * Class   Path : com.zhangzl.study.core
  * Create  User : zhangzuolong
  * Create  Time : 2017/2/14 11:16
  * Project Name : technology_sophisticated_study
  */
object BroadcastVariable {

    def main(args: Array[String]): Unit = {

        val conf = new SparkConf().setAppName("BroadcastVariable").setMaster("local")
        val sc   = new SparkContext(conf)
        /**
          * 定义广播变量
          */
        val factor = sc.broadcast(3);
        val initRdds = sc.parallelize(Array(1,2,3,4,5,6,7),1)
        val result = initRdds.map{number => number * factor.value}
        result.foreach(v => print(v + " "))

    }
}
