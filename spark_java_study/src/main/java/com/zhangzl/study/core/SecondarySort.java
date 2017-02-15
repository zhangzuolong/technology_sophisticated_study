package com.zhangzl.study.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

/**
 * Description  : 二次排序
 * 1. 实现自定义的key，要实现Orderd接口和Serializable接口，在key中实现自己对多个列的排序算法
 * 2. 将包含文本的RDD，映射成key为自定义key，value为文本的JavaPairRDD
 * 3. 使用sortByKey算子按照自定义的key进行排序
 * Class   Path : com.zhangzl.study.core
 * Create  User : zhangzuolong
 * Create  Time : 2017/2/14 16:56
 * Project Name : technology_sophisticated_study
 */
public class SecondarySort {

    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setAppName("secondarySort").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("D:\\workspaces\\idea\\technology_sophisticated_study\\spark_java_study\\src\\main\\resources\\conf\\sort.txt");

        JavaPairRDD<SecondarySortKey,String> pairs = lines.mapToPair(new PairFunction<String, SecondarySortKey, String>() {
            public Tuple2<SecondarySortKey, String> call(String line) throws Exception {
                String[] words = line.split(" ");
                SecondarySortKey key = new SecondarySortKey(Integer.valueOf(words[0]),Integer.valueOf(words[1]));
                return new Tuple2<SecondarySortKey, String>(key,line);
            }
        });
        JavaPairRDD<SecondarySortKey,String> sorts = pairs.sortByKey();
        JavaRDD<String> sortLines = sorts.map(new Function<Tuple2<SecondarySortKey, String>, String>() {
            public String call(Tuple2<SecondarySortKey, String> tuple) throws Exception {
                return tuple._2;
            }
        });
        sortLines.foreach(new VoidFunction<String>() {
            public void call(String s) throws Exception {
                System.out.println("sort == "+ s);
            }
        });

        sc.close();
    }
}
