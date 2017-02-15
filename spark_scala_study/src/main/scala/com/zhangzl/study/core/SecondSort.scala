package com.zhangzl.study.core

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description  : 二次排序
  * Class   Path : com.zhangzl.study.core
  * Create  User : zhangzuolong
  * Create  Time : 2017/2/14 17:55
  * Project Name : technology_sophisticated_study
  */
object SecondSort {

    def main(args: Array[String]): Unit = {

        val conf = new SparkConf().setAppName("SecondSort").setMaster("local")
        val sc  = new SparkContext(conf)
        val lines = sc.textFile("D:\\workspaces\\idea\\technology_sophisticated_study\\spark_scala_study\\src\\main\\resources\\conf\\sort.txt",1)
        lines.map{ line => (new SecondSortKey(line.split(" ")(0).toInt,line.split(" ")(1).toInt),line)}
                    .sortByKey()
                    .map(tuple => tuple._2)
                    .foreach(line => println(line))



    }

}
