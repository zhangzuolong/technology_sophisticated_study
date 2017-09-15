package com.zhangzl.study.storm.wordcount;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

/**
 * Description  : 计数bolt
 * Create  User : zhangzuolong
 * Create  Time : 2017/8/8 16:03
 */
public class WordCountBolt extends BaseRichBolt{
    private OutputCollector outputCollector;
    private Map<String,Long> counts=null;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
        this.counts = new HashMap<String,Long>();
    }

    @Override
    public void execute(Tuple tuple) {
        String word = tuple.getStringByField("word");
        Long count = this.counts.get(word);
        if(null == count){
            count = 0L;
        }
        count++;
        this.counts.put(word,count);
        this.outputCollector.emit(new Values(word,count));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word","count"));
    }
}
