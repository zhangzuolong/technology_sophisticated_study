package com.zhangzl.study.domain;

import java.io.Serializable;

/**
 * Description  :  学生JaveBean
 * Class   Path : com.zhangzl.study.domain
 * Create  User : zhangzuolong
 * Create  Time : 2017/3/24 17:08
 * Project Name : technology_sophisticated_study
 */
public class Student implements Serializable {

    private int     id;
    private String  name;
    private int     age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
