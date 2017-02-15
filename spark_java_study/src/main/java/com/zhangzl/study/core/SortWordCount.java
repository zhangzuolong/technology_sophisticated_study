package com.zhangzl.study.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;

/**
 * Description  : 排序的wordCount程序
 * Class   Path : com.zhangzl.study.core
 * Create  User : zhangzuolong
 * Create  Time : 2017/2/13 16:42
 * Project Name : technology_sophisticated_study
 */
public class SortWordCount {

    public static void main(String[] args) {
        /**
         * 初始化配置
         */
        SparkConf conf = new SparkConf().setAppName("sortWordCount").setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);
        /**
         * 先进行单词计数
         */
        JavaRDD<String> lines = sc.textFile("D:\\workspaces\\idea\\technology_sophisticated_study\\spark_java_study\\src\\main\\resources\\conf\\wordcount.txt");
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            public Iterable<String> call(String line) throws Exception {
                return Arrays.asList(line.split(" "));
            }
        });
        JavaPairRDD<String,Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s,1);
            }
        });
        JavaPairRDD<String,Integer> wordCounts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1+v2;
            }
        });

        /**
         * 键值对互换后，进行排序
         */
        JavaPairRDD<Integer,String> countWords = wordCounts.mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {
            public Tuple2<Integer, String> call(Tuple2<String, Integer> tuple) throws Exception {
                return new Tuple2<Integer, String>(tuple._2,tuple._1);
            }
        });
        JavaPairRDD<Integer,String> sortCountWords = countWords.sortByKey(false);

        JavaPairRDD<String,Integer> sortWordCounts = sortCountWords.mapToPair(new PairFunction<Tuple2<Integer, String>, String, Integer>() {
            public Tuple2<String, Integer> call(Tuple2<Integer, String> tuple2) throws Exception {
                return new Tuple2<String, Integer>(tuple2._2,tuple2._1);
            }
        });
        sortWordCounts.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            public void call(Tuple2<String, Integer> t) throws Exception {
                System.out.println("word == "+ t._1+"; count == "+t._2);
            }
        });
        sc.close();

    }

}
