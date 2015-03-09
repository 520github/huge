/**
 * 
 */
package me.power.speed.frame.storm.sample.counterword;

import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;

/**
 * @author xuehui.miao
 *
 */
public class KestrelSpout implements IRichSpout {

	private static final long serialVersionUID = -3615773847187996115L;

	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
	}

	public void close() {
	}

	public void nextTuple() {
	}

	
	public void ack(Object msgId) {
	}

	
	public void fail(Object msgId) {
	}

	
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
	}

	
	public void activate() {
		// TODO Auto-generated method stub
		
	}

	
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}

	
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
