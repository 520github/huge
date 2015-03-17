package me.power.speed.common.msqueue;

public enum MessageQueue {
	kestrel("ipAndPort,queueName,expire,sleepTime"),
	;
	
	private String parameter_column_name;
	
	private MessageQueue(String parameter_column_name) {
		this.parameter_column_name = parameter_column_name;
	}
	
	public String getParameterColumnName() {
		return this.parameter_column_name;
	}
}
