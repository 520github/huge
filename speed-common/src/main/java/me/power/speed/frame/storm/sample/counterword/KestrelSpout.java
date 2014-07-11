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

	@Override
	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
	}

	@Override
	public void close() {
	}

	@Override
	public void nextTuple() {
	}

	@Override
	public void ack(Object msgId) {
	}

	@Override
	public void fail(Object msgId) {
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
