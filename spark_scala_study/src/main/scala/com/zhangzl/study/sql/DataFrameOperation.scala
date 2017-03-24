package com.zhangzl.study.sql

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description  : DataFrame 的常用操作
  * Class   Path : com.zhangzl.study.sql
  * Create  User : zhangzuolong
  * Create  Time : 2017/3/24 16:15
  * Project Name : technology_sophisticated_study
  */
object DataFrameOperation {

    def main(args: Array[String]): Unit ={

        val conf = new SparkConf().setMaster("local").setAppName("dataFrameCreate")
        val sc = new SparkContext(conf)
        val sqlContext = new SQLContext(sc)

        val df = sqlContext.read.json("D:\\workspaces\\idea\\technology_sophisticated_study\\spark_scala_study\\src\\main\\resources\\conf\\students.json")

        //打印所有
        df.show()
        //打印元数据
        df.printSchema()
        //打印某一列的所有数据
        df.select(df.col("name")).show()
        //打印某些列，并对某些列做计算
        df.select(df.col("name"),df.col("age").plus(2)).show()
        //根据某些列过滤数据
        df.filter(df.col("age").>=(17)).show()
        //根据某一列进行分组，然后聚合
        df.groupBy(df.col("age")).count().show()
    }

}
