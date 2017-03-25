package com.zhangzl.study.sql;

import com.zhangzl.study.domain.Student;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import java.util.List;

/**
 * Description  : 通过反射机智，使得RDD转换为dataFrame
 * Class   Path : com.zhangzl.study.sql
 * Create  User : zhangzuolong
 * Create  Time : 2017/3/24 17:03
 * Project Name : technology_sophisticated_study
 *
 * Java版本：Spark SQL是支持将包含了JavaBean的RDD转换为DataFrame的。
 * JavaBean的信息，就定义了元数据。
 * Spark SQL现在是不支持将包含了嵌套JavaBean或者List等复杂数据的JavaBean，
 * 作为元数据的。只支持一个包含简单数据类型的field的JavaBean。
 *
 */
public class RDD2DataFrameReflection {

    public static void main(String[] args) {

        SparkConf  conf = new SparkConf().setMaster("local").setAppName("rdd2DataFrameReflaction");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);

        JavaRDD<String> students = sc.textFile("D:\\workspaces\\idea\\technology_sophisticated_study\\spark_java_study\\src\\main\\resources\\conf\\students.txt");


        JavaRDD<Student> studentsRdd = students.map(new Function<String, Student>() {
            public Student call(String line) throws Exception {
                String[]   lineSplits= line.split(",");
                Student student = new Student();
                student.setId(Integer.valueOf(lineSplits[0].trim()));
                student.setName(lineSplits[1]);
                student.setAge(Integer.valueOf(lineSplits[2].trim()));
                return student;
            }
        });

        /**
         * 使用反射方式，将RDD转换为DataFrame
         // 将Student.class传入进去，其实就是用反射的方式来创建DataFrame
         // 因为Student.class本身就是反射的一个应用
         // 然后底层还得通过对Student Class进行反射，来获取其中的field
         // 这里要求，JavaBean必须实现Serializable接口，是可序列化的
         */
        DataFrame  studentDataFrame = sqlContext.createDataFrame(studentsRdd,Student.class);

        //利用dataFrame 建立一个临时表
        studentDataFrame.registerTempTable("student");
        //利用万能的sql来过滤数据
        DataFrame teenegersDataFrame = sqlContext.sql("select * from student where age >=17 ");
        //将DataFrame 在转换为rdd
        JavaRDD<Row> teenegerRdd = teenegersDataFrame.toJavaRDD();

        JavaRDD<Student> teenegers = teenegerRdd.map(new Function<Row, Student>() {
            public Student call(Row row) throws Exception {

                Student student = new Student();
                student.setAge(Integer.valueOf(row.getAs("age").toString()));
                student.setName(row.getAs("name").toString());
                student.setId(Integer.valueOf(row.getAs("id").toString()));
                return  student;
            }
        });

        List<Student> list = teenegers.collect();
        for(Student s : list){
            System.out.println(s.toString());
        }

    }
}
