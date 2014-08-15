package me.power.speed.common.stream.file;

public interface FileHandleAndFilter {
	public void handleOneLineData(String data);
	public boolean filterOneLineData(String data);
}
