package com.zhangzl.study.storm.wordcount;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

import java.util.Map;

/**
 * Description  :
 * Create  User : zhangzuolong
 * Create  Time : 2017/8/8 15:20
 */
public class SentenceSpout extends BaseRichSpout{

    private SpoutOutputCollector collector;

    private String[] sentences = {
            "my dog has fleas",
            "i like cole beverages",
            "the dog ate my homework",
            "dont have a cow man",
            "i don't like i like fleas"
    };
    private int index=0;

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = spoutOutputCollector;
    }

    @Override
    public void nextTuple() {
        this.collector.emit(new Values(sentences[index]));
        index++;
        if(index >=sentences.length){
            index=0;
        }
/*        try {
            Thread.sleep(1000);
            System.out.println("暂定一秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("sentence"));
    }

}
