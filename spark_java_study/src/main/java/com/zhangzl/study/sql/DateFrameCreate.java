package com.zhangzl.study.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

/**
 * Description  : DateFrame
 * Class   Path : com.zhangzl.study.sql
 * Create  User : zhangzuolong
 * Create  Time : 2017/3/23 17:39
 * Project Name : technology_sophisticated_study
 * Spark SQL是Spark中的一个模块，主要用于进行结构化数据的处理。它提供的最核心的编程抽象，就是DataFrame。
 * 同时Spark SQL还可以作为分布式的SQL查询引擎。Spark SQL最重要的功能之一，就是从Hive中查询数据。
   DataFrame，可以理解为是，以列的形式组织的，分布式的数据集合。它其实和关系型数据库中的表非常类似，但是底层做了很多的优化。
   DataFrame可以通过很多来源进行构建，包括：结构化的数据文件，Hive中的表，外部的关系型数据库，以及RDD。

 */
public class DateFrameCreate {

    public static void main(String[] args){
        SparkConf conf = new SparkConf().setAppName("DateFrameCreate").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sql = new SQLContext(sc);

        DataFrame df = sql.read().json("D:\\workspaces\\idea\\technology_sophisticated_study\\spark_java_study\\src\\main\\resources\\conf\\students.json");
        df.show();
    }



}
