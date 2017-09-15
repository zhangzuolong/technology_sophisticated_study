package com.zhangzl.study.storm.wordcount;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

/**
 * Description  : 单词计数topology
 * Create  User : zhangzuolong
 * Create  Time : 2017/8/9 9:53
 */
public class WordCountTopology {


    private static final String SENTENCE_SPOUT_ID="sentences-spout";
    private static final String SPLIT_SENTENCES_BOLT_ID="split-bolt";
    private static final String WORDCOUNT_BOLT="wordcount-bolt";
    private static final String REPORT_BOLT="report-bolt";
    private static final String WORD_COUNT_TOPOLOGY="word-count-topology";

    public static void main(String[] args) {

        SentenceSpout sentenceSpout = new SentenceSpout();
        SplitSentenceBolt splitSentenceBolt = new SplitSentenceBolt();
        WordCountBolt  wordCountBolt = new WordCountBolt();
        ReportBolt reportBolt = new ReportBolt();

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout(SENTENCE_SPOUT_ID,sentenceSpout);
        builder.setBolt(SPLIT_SENTENCES_BOLT_ID,splitSentenceBolt).shuffleGrouping(SENTENCE_SPOUT_ID);
        builder.setBolt(WORDCOUNT_BOLT,wordCountBolt).fieldsGrouping(SPLIT_SENTENCES_BOLT_ID,new Fields("word"));
        builder.setBolt(REPORT_BOLT,reportBolt).globalGrouping(WORDCOUNT_BOLT);

        Config config = new Config();
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology(WORD_COUNT_TOPOLOGY,config,builder.createTopology());
        try{
            Thread.sleep(10000);
            System.err.println("=====================执行结束。。。。。。");
            cluster.killTopology(WORD_COUNT_TOPOLOGY);
            cluster.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
