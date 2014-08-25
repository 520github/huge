package me.power.speed.entity.test;

import java.io.Serializable;
import java.util.Map;

public class Event implements Serializable {
	public static final long serialVersionUID = 3162412283589533079L;
	public String eventID;
	public Long eventOccurTime;
	public Integer eventStatus;
	public String eventDesc;
	public Map eventData;
	public Long originalEventOccurTime;
	
//TEST
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setEventData(Map eventData) {
		this.eventData = eventData;
	}

	public String getEventID() {
		return eventID;
	}

	public Long getEventOccurTime() {
		return eventOccurTime;
	}

	public Integer getEventStatus() {
		return eventStatus;
	}

	public String getEventDesc() {
		return eventDesc;
	}

	public Map getEventData() {
		return eventData;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public void setEventOccurTime(Long eventOccurTime) {
		this.eventOccurTime = eventOccurTime;
	}

	public void setEventStatus(Integer eventStatus) {
		this.eventStatus = eventStatus;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	
	public Long getOriginalEventOccurTime() {
		return originalEventOccurTime;
	}

	public void setOriginalEventOccurTime(Long originalEventOccurTime) {
		this.originalEventOccurTime = originalEventOccurTime;
	}

	@Override
	public String toString() {
		return "Event [eventID=" + eventID + ", eventOccurTime="
				+ eventOccurTime + ", eventStatus=" + eventStatus
				+ ", eventDesc=" + eventDesc + ", eventData=" + eventData
				+ ", originalEventOccurTime=" + originalEventOccurTime + "]";
	}

	public Event() {
	}
}
