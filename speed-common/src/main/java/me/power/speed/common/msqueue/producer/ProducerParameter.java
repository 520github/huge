package me.power.speed.common.msqueue.producer;

public class ProducerParameter {
	private String data;
	private KestrelProductParameter kpp = null;
	
	public ProducerParameter setData(String data) {
		this.data = data;
		return this;
	}
	
	public String getData() {
		return this.data;
	}
	
	public ProducerParameter setKestrelProductParameter(KestrelProductParameter kpp) {
		this.kpp = kpp;
		return this;
	}
	
	public KestrelProductParameter getKestrelProductParameter() {
		return this.kpp;
	}
	
}
