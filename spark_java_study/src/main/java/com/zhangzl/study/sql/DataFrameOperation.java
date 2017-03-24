package com.zhangzl.study.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

/**
 * Description  : dataFrame常见操作
 * Class   Path : com.zhangzl.study.sql
 * Create  User : zhangzuolong
 * Create  Time : 2017/3/24 15:53
 * Project Name : technology_sophisticated_study
 */
public class DataFrameOperation {

    public static void main(String[] args) {


        SparkConf conf = new SparkConf().setMaster("local").setAppName("DataFrameOperation");
        JavaSparkContext sc = new JavaSparkContext(conf);

        SQLContext sqlContext = new SQLContext(sc);
        DataFrame df = sqlContext.read().json("D:\\workspaces\\idea\\technology_sophisticated_study\\spark_java_study\\src\\main\\resources\\conf\\students.json");

        //打印DataFrame中所有的数据
        df.show();
        //打印DataFrame中的元数据 schema
        df.printSchema();
        //查询某列所有的数据
        df.select("name").show();
        //查询某几列的所有数据，并对列进行计算
        df.select(df.col("name"),df.col("age").plus(1)).show();
        //根据某一列的值进行过滤
        df.filter(df.col("age").gt(18)).show();
        //根据某一列进行分组，然后聚合
        df.groupBy(df.col("age")).count().show();


    }
}
