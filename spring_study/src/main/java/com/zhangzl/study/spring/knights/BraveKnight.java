package com.zhangzl.study.spring.knights;

/**
 * Description  : 勇士
 * Class   Path : com.zhangzl.study.spring.knights
 * Create  User : zhangzuolong
 * Create  Time : 2017/6/27 10:27
 * Project Name : technology_sophisticated_study
 */
public class BraveKnight implements  Knight{

    private Quest quest;

    /**
     * 构造器注入
     * @param quest
     */
    public BraveKnight(Quest quest){
        this.quest = quest;
    }

    @Override
    public void embarkOnQuest() {
        quest.embark();
    }
}
