package com.zhangzl.study.core

/**
  * Description  : 自定义二次排序的key值
  * Class   Path : com.zhangzl.study.core
  * Create  User : zhangzuolong
  * Create  Time : 2017/2/14 17:40
  * Project Name : technology_sophisticated_study
  */
class SecondSortKey(val first:Int,val second:Int) extends Ordered[SecondSortKey] with Serializable{

    def compare(other: SecondSortKey): Int = {
        if(this.first - other.first!=0){
            return this.first - other.first
        }else{
            return this.second - other.second
        }

    }
}
