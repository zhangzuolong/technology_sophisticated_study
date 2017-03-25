package com.zhangzl.study.sql

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext


/**
  * Description  : RDD转为DataFrame
  * Class   Path : com.zhangzl.study.sql
  * Create  User : zhangzuolong
  * Create  Time : 2017/3/24 21:10
  * Project Name : technology_sophisticated_study
  *
  * Scala版本：而Scala由于其具有隐式转换的特性，所以Spark SQL的Scala接口，
  * 是支持自动将包含了case class的RDD转换为DataFrame的。
  * case class就定义了元数据。Spark SQL会通过反射读取传递给case class的参数的名称，
  * 然后将其作为列名。与Java不同的是，Spark SQL是支持将包含了嵌套数据结构的case class作为元数据的，
  * 比如包含了Array等
  */
object RDD2DataFrameReflection extends App{

    //建立基本属性
    val conf =  new SparkConf().setMaster("local").setAppName("rdd2DataFrame")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    // 在Scala中使用反射方式，进行RDD到DataFrame的转换，需要手动导入一个隐式转换
    import sqlContext.implicits._

    case class Student(id:Int,name:String,age:Int)

    //
    val studentDF = sc.textFile("D:\\workspaces\\idea\\technology_sophisticated_study\\spark_scala_study\\src\\main\\resources\\conf\\students.txt",1)
                      .map{line => line.split(",")} //按逗号切割
                      .map{arrys => Student(arrys(0).trim.toInt,arrys(1),arrys(2).trim.toInt)}
                      .toDF()  //转换为dataframe
    studentDF.registerTempTable("student")

    val teenagerDF = sqlContext.sql("select * from student where age>17")
    teenagerDF.rdd
      .map{row => Student(row.getAs("id").toString.toInt,row.getAs("name").toString,row.getAs("age").toString.toInt)}
      .foreach{student => println(student.id+":"+student.name+"："+student.age)}
















}
