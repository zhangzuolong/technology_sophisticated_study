package com.zhangzl.study.sql

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description  : 以编程的方式构建元数据，将RDD转换为DataFrame
  * Class   Path : com.zhangzl.study.sql
  * Create  User : zhangzuolong
  * Create  Time : 2017/3/25 8:19
  * Project Name : technology_sophisticated_study
  */
object RDD2DataFrameProgrammatically {

    def main(args: Array[String]): Unit = {

        //创建SparkConf，SparkContext，SQLContext
        val conf = new SparkConf().setAppName("RDD2DataFrameProgrammatically")
          .setMaster("local") //本地运行
        val sc = new SparkContext(conf)
        val sqlContext = new SQLContext(sc)

        val studentsRDD = sc.textFile("D:\\workspaces\\idea\\technology_sophisticated_study\\spark_scala_study\\src\\main\\resources\\conf\\students.txt",1)
          .map(_.split(","))
          .map(arrys => Row(
              arrys(0).trim.toString.toInt,
              arrys(1).trim.toString,
              arrys(2).trim.toString.toInt
          ))

        //构建StructType
        val structType = StructType(
            Array(
                StructField("id",IntegerType,true),
                StructField("name",StringType,true),
                StructField("age",IntegerType,true)
            )
        )
        //通过structType 构造的元数据，将rdd转换为DataFrame
        val studentDF = sqlContext.createDataFrame(studentsRDD,structType)
        studentDF.registerTempTable("student")

        sqlContext.sql("select * from student where name='tom' ")
          .rdd.foreach(row => println(row))



    }


}
