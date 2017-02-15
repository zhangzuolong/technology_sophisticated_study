package com.zhangzl.study.core;

import scala.math.Ordered;

import java.io.Serializable;

/**
 * Description  : 二次排序的key
 * Class   Path : com.zhangzl.study.core
 * Create  User : zhangzuolong
 * Create  Time : 2017/2/14 16:28
 * Project Name : technology_sophisticated_study
 */
public class SecondarySortKey implements Ordered<SecondarySortKey>,Serializable {

    /**
     * 首先在自定义key里面定义需要排序的列
     */
    private int first;
    private int second;

    public SecondarySortKey(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int compare(SecondarySortKey other) {
        if(this.first - other.getFirst()!=0){
            return this.first - other.getFirst();
        }else{
            return this.second - other.getSecond();
        }
    }

    /**
     * 判断小于
     * @param other
     * @return
     */
    public boolean $less(SecondarySortKey other) {
        if(this.first < other.getFirst()){
            return true;
        }else if(this.first == other.getFirst() && this.second< other.getSecond()){
            return true;
        }
        return false;
    }

    /**
     * 判断大于
     * @param other
     * @return
     */
    public boolean $greater(SecondarySortKey other) {
        if(this.first>other.getFirst()){
            return true;
        }else if(this.first == other.getFirst() && this.second > other.getSecond()){
            return true;
        }
        return false;
    }

    /**
     * 判断小于等于
     * @param other
     * @return
     */
    public boolean $less$eq(SecondarySortKey other) {
        if(this.$less(other)){
            return true;
        }else if(this.first == other.getFirst() && this.second == other.getSecond()){
            return true;
        }
        return false;
    }

    /**
     * 判断大于等于
     * @param other
     * @return
     */
    public boolean $greater$eq(SecondarySortKey other) {
        if(this.$greater(other)){
            return true;
        }else if(this.first == other.getFirst() && this.second == other.getSecond()){
            return true;
        }
        return false;
    }

    public int compareTo(SecondarySortKey other) {
        if(this.first - other.getFirst()!=0){
            return this.first - other.getFirst();
        }else{
            return this.second - other.getSecond();
        }
    }


    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecondarySortKey that = (SecondarySortKey) o;

        if (first != that.first) return false;
        return second == that.second;

    }

    @Override
    public int hashCode() {
        int result = first;
        result = 31 * result + second;
        return result;
    }
}
