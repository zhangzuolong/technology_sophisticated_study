package com.zhangzl.study.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;

/**
 * Description  : load,save 使用方式
 * Class   Path : com.zhangzl.study.sql
 * Create  User : zhangzuolong
 * Create  Time : 2017/3/25 8:43
 * Project Name : technology_sophisticated_study
 *
//---------------------------------------------------------------------------------
 对于Spark SQL的DataFrame来说，无论是从什么数据源创建出来的DataFrame，都有一些共同的load和save操作。
 load操作主要用于加载数据，创建出DataFrame；save操作，主要用于将DataFrame中的数据保存到文件中。

 Java版本
 DataFrame df = sqlContext.read().load("users.parquet");
 df.select("name", "favorite_color").write().save("namesAndFavColors.parquet");

 Scala版本
 val df = sqlContext.read.load("users.parquet")
 df.select("name", "favorite_color").write.save("namesAndFavColors.parquet")
//---------------------------------------------------------------------------------
 也可以手动指定用来操作的数据源类型。数据源通常需要使用其全限定名来指定，
 比如parquet是org.apache.spark.sql.parquet。
 但是Spark SQL内置了一些数据源类型，比如json，parquet，jdbc等等。实际上，通过这个功能，
 就可以在不同类型的数据源之间进行转换了。比如将json文件中的数据保存到parquet文件中。
 默认情况下，如果不指定数据源类型，那么就是parquet。

 Java版本
 DataFrame df = sqlContext.read().format("json").load("people.json");
 df.select("name", "age").write().format("parquet").save("namesAndAges.parquet");

 Scala版本
 val df = sqlContext.read.format("json").load("people.json")
 df.select("name", "age").write.format("parquet").save("namesAndAges.parquet")
 *
 */
public class LoadAndSaveOperation {
    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setAppName("loadAndSave").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);

        DataFrame studentDF = sqlContext.read().format("json").load("D:\\workspaces\\idea\\technology_sophisticated_study\\spark_java_study\\src\\main\\resources\\conf\\students.json");

        //默认是保存为parquet方式
        //studentDF.write().save("C:\\Users\\Administrator\\Desktop\\student.json");
        /**
         *
         * SaveMode.ErrorIfExists (默认)   如果目标位置已经存在数据，那么抛出一个异常
           SaveMode.Append                 如果目标位置已经存在数据，那么将数据追加进去
           SaveMode.Overwrite              如果目标位置已经存在数据，那么就将已经存在的数据删除，用新数据进行覆盖
           SaveMode.Ignore                 如果目标位置已经存在数据，那么就忽略，不做任何操作。





         */
        studentDF.save("C:\\\\Users\\\\Administrator\\\\Desktop\\\\student.json", "json", SaveMode.Append);




    }
}
