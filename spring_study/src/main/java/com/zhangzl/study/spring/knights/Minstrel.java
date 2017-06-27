package com.zhangzl.study.spring.knights;

import java.io.PrintStream;

/**
 * Description  : 吟游诗人
 * Class   Path : com.zhangzl.study.spring.knights
 * Create  User : zhangzuolong
 * Create  Time : 2017/6/27 14:33
 * Project Name : technology_sophisticated_study
 */
public class Minstrel {

    private PrintStream printStream;

    public Minstrel(PrintStream printStream){
        this.printStream = printStream;
    }

    /**
     * 探险前调用
     */
    public void singBeforeQuest(){
        printStream.println("Fa la la,this knight is so brave!");
    }

    /**
     * 探险后调用
     */
    public void singAfterQuest(){
        printStream.println("Tee hee hee,this brave knight did embrak on a quest!!!");
    }
}
