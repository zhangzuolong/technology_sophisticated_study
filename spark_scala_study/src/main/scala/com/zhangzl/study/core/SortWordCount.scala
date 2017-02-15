package com.zhangzl.study.core

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description  : 排序的wordcount
  * Class   Path : com.zhangzl.study.core
  * Create  User : zhangzuolong
  * Create  Time : 2017/2/14 15:35
  * Project Name : technology_sophisticated_study
  */
object SortWordCount {

    def main(args: Array[String]): Unit = {

        val conf  = new SparkConf().setAppName("sortWordCount").setMaster("local")
        val sc    = new SparkContext(conf)
        val lines = sc.textFile("D:\\workspaces\\idea\\technology_sophisticated_study\\spark_scala_study\\src\\main\\resources\\conf\\wordcount.txt",1);

        val words = lines.flatMap(line => line.split(" "))
        val pairs = words.map(word => (word,1))
        val wordcounts = pairs.reduceByKey( _ + _)
        val countword = wordcounts.map{wordCount => (wordCount._2,wordCount._1)}

        val sortCountWords = countword.sortByKey(false)
        val sortWordCounts = sortCountWords.map(sortCountWord => (sortCountWord._2,sortCountWord._1))
        sortWordCounts.foreach(sortWordCount => println("word == "+sortWordCount._1+";count == "+sortWordCount._2))


    }
}
