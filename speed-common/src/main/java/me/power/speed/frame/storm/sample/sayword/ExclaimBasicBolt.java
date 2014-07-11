/**
 * 
 */
package me.power.speed.frame.storm.sample.sayword;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * @author xuehui.miao
 *
 */
public class ExclaimBasicBolt extends BaseBasicBolt {

	private static final long serialVersionUID = -6343789934413886833L;

	/* (non-Javadoc)
	 * @see backtype.storm.topology.IBasicBolt#execute(backtype.storm.tuple.Tuple, backtype.storm.topology.BasicOutputCollector)
	 */
	@Override
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		String sentence = (String) tuple.getValue(0);
		String out = sentence + "!";
		collector.emit(new Values(out));
	}

	/* (non-Javadoc)
	 * @see backtype.storm.topology.IComponent#declareOutputFields(backtype.storm.topology.OutputFieldsDeclarer)
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("excl_sentence"));
	}

}
