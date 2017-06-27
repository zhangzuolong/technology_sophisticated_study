package com.zhangzl.study.spring.knights;

import java.io.PrintStream;

/**
 * Description  : 杀死巨龙的探险
 * Class   Path : com.zhangzl.study.spring.knights
 * Create  User : zhangzuolong
 * Create  Time : 2017/6/27 10:59
 * Project Name : technology_sophisticated_study
 */
public class SlayDragonQuest implements Quest{

    private PrintStream printStream;

    public SlayDragonQuest(PrintStream printStream){
        this.printStream = printStream;
    }

    @Override
    public void embark() {
        printStream.println("Embarking on quest to slay the dragon！");
    }
}
