/**
 * 
 */
package me.power.speed.frame.storm.sample.counterword;

import java.util.Map;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * @author xuehui.miao
 *
 */
public class SplitSentence implements IBasicBolt {

	private static final long serialVersionUID = 2731764714385727222L;

	/* (non-Javadoc)
	 * @see backtype.storm.topology.IComponent#declareOutputFields(backtype.storm.topology.OutputFieldsDeclarer)
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}

	/* (non-Javadoc)
	 * @see backtype.storm.topology.IBasicBolt#prepare(java.util.Map, backtype.storm.task.TopologyContext)
	 */
	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		
	}

	/* (non-Javadoc)
	 * @see backtype.storm.topology.IBasicBolt#execute(backtype.storm.tuple.Tuple, backtype.storm.topology.BasicOutputCollector)
	 */
	@Override
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		String sentence = tuple.getString(0); 
        for(String word: sentence.split(" ")) { 
             collector.emit(new Values(word)); 
        }
	}

	/* (non-Javadoc)
	 * @see backtype.storm.topology.IBasicBolt#cleanup()
	 */
	@Override
	public void cleanup() {
		
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

}
