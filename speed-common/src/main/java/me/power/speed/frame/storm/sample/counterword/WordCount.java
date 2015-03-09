/**
 * 
 */
package me.power.speed.frame.storm.sample.counterword;

import java.util.HashMap;
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
public class WordCount implements IBasicBolt {
	
	private static final long serialVersionUID = -4872050429108966740L;
	private Map<String, Integer> _counts = new HashMap<String, Integer>();

	/* (non-Javadoc)
	 * @see backtype.storm.topology.IComponent#declareOutputFields(backtype.storm.topology.OutputFieldsDeclarer)
	 */
	
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word", "count"));
	}

	/* (non-Javadoc)
	 * @see backtype.storm.topology.IBasicBolt#prepare(java.util.Map, backtype.storm.task.TopologyContext)
	 */
	
	public void prepare(Map stormConf, TopologyContext context) {
		
	}

	/* (non-Javadoc)
	 * @see backtype.storm.topology.IBasicBolt#execute(backtype.storm.tuple.Tuple, backtype.storm.topology.BasicOutputCollector)
	 */
	
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		String word = tuple.getString(0); 
        int count; 
        if(_counts.containsKey(word)) { 
               count = _counts.get(word); 
        } else { 
              count = 0; 
} 
        count++; 
        _counts.put(word, count); 
        collector.emit(new Values(word, count));
	}

	/* (non-Javadoc)
	 * @see backtype.storm.topology.IBasicBolt#cleanup()
	 */
	
	public void cleanup() {
		
	}

	
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

}
