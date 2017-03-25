package com.zhangzl.study.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.List;

/**
 * Description  : 以编程的方式动态指定元数据，将RDD转换为DataFrame,用
 * Class   Path : com.zhangzl.study.sql
 * Create  User : zhangzuolong
 * Create  Time : 2017/3/25 7:53
 * Project Name : technology_sophisticated_study
 */
public class RDD2DataFrameProgrammatically {
    public static void main(String[] args) {

        /**
         * 创建SparkConf，JavaSparkContext,SQLContext
         */
        SparkConf conf = new SparkConf()
                .setMaster("local")
                .setAppName("RDD2DataFrameProgrammatically");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);

        JavaRDD<String> lines = sc.textFile("D:\\workspaces\\idea\\technology_sophisticated_study\\spark_java_study\\src\\main\\resources\\conf\\students.txt");
        JavaRDD<Row>  rows = lines.map(new Function<String, Row>() {
            public Row call(String line) throws Exception {
                String[] lineSplint = line.split(",");
                return  RowFactory.create(
                        Integer.valueOf(lineSplint[0].trim()),
                        lineSplint[1].trim(),
                        Integer.valueOf(lineSplint[2].trim())
                );
            }
        });

        // 第二步，动态构造元数据
        // 比如说，id、name等，field的名称和类型，可能都是在程序运行过程中，动态从mysql db里
        // 或者是配置文件中，加载出来的，是不固定的
        // 所以特别适合用这种编程的方式，来构造元数据
        List<StructField> fieldList =  new ArrayList<StructField>();
        fieldList.add(DataTypes.createStructField("id",DataTypes.IntegerType,true));
        fieldList.add(DataTypes.createStructField("name",DataTypes.StringType,true));
        fieldList.add(DataTypes.createStructField("age",DataTypes.IntegerType,true));
        StructType structType = DataTypes.createStructType(fieldList);

        /**
         * 通过SqlContext 创建基于元数据的dataFrame
         */
        DataFrame studentDF = sqlContext.createDataFrame(rows,structType);
        studentDF.registerTempTable("student");

        /**
         * 利用sql查询
         */
        DataFrame teenagerDF = sqlContext.sql("select * from student where age=17 ");

        List<Row> teenagers = teenagerDF.javaRDD().collect();
       for(Row row:teenagers){
           System.out.println(row);
       }
    }
}
