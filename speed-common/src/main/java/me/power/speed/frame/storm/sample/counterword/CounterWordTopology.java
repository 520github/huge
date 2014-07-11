/**
 * 
 */
package me.power.speed.frame.storm.sample.counterword;

import clojure.main;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;

/**
 * @author xuehui.miao
 *
 */
public class CounterWordTopology {
	public void createAndExecuteTopology() {
		try {
			TopologyBuilder builder = new TopologyBuilder();
			//KestrelThriftSpout kestrelSpout = new KestrelThriftSpout("10.10.32.102", 11211, "push", new StringScheme());
			
			KestrelSpout kestrelSpout = new KestrelSpout();
//			builder.setSpout(1, kestrelSpout);
//			
//			builder.setBolt(2, new SplitSentence(), 10).shuffleGrouping(1);
//			builder.setBolt(3, new WordCount(), 20).fieldsGrouping(2, new Fields("word"));
//			
			
			Config conf = new Config();
			conf.setDebug(true);
			conf.setNumWorkers(2);
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("test", conf, builder.createTopology());
			Utils.sleep(1000);
			cluster.killTopology("test");
			cluster.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
