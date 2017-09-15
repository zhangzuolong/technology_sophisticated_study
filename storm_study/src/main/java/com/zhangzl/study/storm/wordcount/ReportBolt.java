package com.zhangzl.study.storm.wordcount;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import java.util.*;

/**
 * Description  :
 * Create  User : zhangzuolong
 * Create  Time : 2017/8/8 16:10
 */
public class ReportBolt extends BaseRichBolt{
    private HashMap<String,Long> counts = null;
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.counts = new HashMap<String,Long>();
    }

    @Override
    public void execute(Tuple tuple) {
        String word = tuple.getStringByField("word");
        Long count = tuple.getLongByField("count");
        this.counts.put(word,count);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    }

    @Override
    public void cleanup() {
        System.out.println("----Final counts -----");
        List<String> keys = new ArrayList<String>();
        keys.addAll(this.counts.keySet());
        Collections.sort(keys);
        for(String key:keys){
            System.out.println(key+":-------:"+this.counts.get(key));
        }
        System.out.println("==================================");
    }
}
