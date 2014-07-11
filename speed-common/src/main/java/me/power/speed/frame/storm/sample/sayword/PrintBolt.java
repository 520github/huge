/**
 * 
 */
package me.power.speed.frame.storm.sample.sayword;

import java.util.Map;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

/**
 * @author xuehui.miao
 *
 */
public class PrintBolt extends BaseBasicBolt {

	private static final long serialVersionUID = -49887018501719419L;
	private int indexId;
	
	public void prepare(Map stormConf, TopologyContext context) {
		this.indexId = context.getThisTaskIndex();
    }
	
	/* (non-Javadoc)
	 * @see backtype.storm.topology.IBasicBolt#execute(backtype.storm.tuple.Tuple, backtype.storm.topology.BasicOutputCollector)
	 */
	@Override
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		String rec = tuple.getString(0);
		System.err.println(String.format("Bolt[%d] String recieved: %s", this.indexId, rec));
	}

	/* (non-Javadoc)
	 * @see backtype.storm.topology.IComponent#declareOutputFields(backtype.storm.topology.OutputFieldsDeclarer)
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
	}

}
