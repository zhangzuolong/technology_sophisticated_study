package com.zhangzl.study.storm.wordcount;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.Map;

/**
 * Description  : 语句分割bolt
 * Create  User : zhangzuolong
 * Create  Time : 2017/8/8 15:35
 */
public class SplitSentenceBolt extends BaseRichBolt{

    private OutputCollector outputCollector;


    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        String sentence = tuple.getStringByField("sentence");
        //System.out.println("sentence===="+sentence);
        String[] words = sentence.split(" ");
        for(String word:words){
            this.outputCollector.emit(new Values(word));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word"));
    }
}
