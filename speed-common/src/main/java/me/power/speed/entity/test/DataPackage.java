package me.power.speed.entity.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class DataPackage {
	
	public static final Map<EventEnum, String> eventType = new HashMap<DataPackage.EventEnum, String>();
	
	static {
		// eventType.put(EventEnum.T1, "Init");
		eventType.put(EventEnum.T2, "Launch");
		eventType.put(EventEnum.T3, "Terminate");
		// eventType.put(EventEnum.T4, "Activity");
		eventType.put(EventEnum.T5, "AppEvent");
		eventType.put(EventEnum.T6, "Exception");
		eventType.put(EventEnum.G1, "Init");
		eventType.put(EventEnum.G2, "Install");
		eventType.put(EventEnum.G3, "Login");
		eventType.put(EventEnum.G4, "Logout");
		eventType.put(EventEnum.G5, "Levelup");
		eventType.put(EventEnum.G6, "Mission");
		eventType.put(EventEnum.G7, "UpdateUserProfile");
		eventType.put(EventEnum.G8, "CustomEvent");
		eventType.put(EventEnum.G9, "Charge");
		eventType.put(EventEnum.G10, "Purchase");
		eventType.put(EventEnum.G11, "NewUser");
		eventType.put(EventEnum.G12, "User");
		eventType.put(EventEnum.G13, "Exception");
		eventType.put(EventEnum.G14, "AppList");
		eventType.put(EventEnum.G15, "Reward");
	}
	
	private AppProfile appProfile;
	private DeviceProfile deviceProfile;
	private UserProfile userProfile;
	private Event[] events;
	private String data;
	private Map<String, Map<String, String>> users = new HashMap<String, Map<String,String>>();
	private Map<String, String> user;
	/**
	 * collector鎺ユ敹鍒皊dk鏁版嵁鐨勬椂闂�	 */
	private long receiveTime;
	/**
	 * 鏁版嵁鏉ユ簮锛�涓烘湇鍔″櫒锛�涓簊dk
	 */
	private int source;
	//璇ヨ澶囨槸鍚︽潵婧愮煭閾炬笭閬�	
	private boolean isShortUrlPartner = false;
	//鐭摼娓犻亾瀵瑰簲鐨刬d
	private int shortUrlPartnerId = 0;
	
	public boolean isShortUrlPartner() {
		return isShortUrlPartner;
	}

	public void setShortUrlPartner(boolean isShortUrlPartner) {
		this.isShortUrlPartner = isShortUrlPartner;
	}

	public int getShortUrlPartnerId() {
		return shortUrlPartnerId;
	}

	public void setShortUrlPartnerId(int shortUrlPartnerId) {
		this.shortUrlPartnerId = shortUrlPartnerId;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public long getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(long receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Map<String, String> getUser() {
		return user;
	}

	public void setUser(Map<String, String> user) {
		this.user = user;
	}

	public Map<String, Map<String, String>> getUsers() {
		return users;
	}
	
	public Map<String, String> getUserByUserId (String userId) {
		if (userId == null || userId.length() == 0) {
			return Collections.emptyMap();
		}
		return users.get(userId);
	}

	public void setUsers(Map<String, Map<String, String>> users) {
		this.users = users;
	}

	public void addEvent(Event event) {
		events = Arrays.copyOf(events, events.length + 1);
		events[events.length - 1] = event;
	}

	public static Map<EventEnum, String> getEventtype() {
		return eventType;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public DataPackage() {
		
	}


	@Override
	public String toString() {
		return "DataPackage [appProfile=" + appProfile + ", deviceProfile="
				+ deviceProfile + ", userProfile=" + userProfile + ", events="
				+ Arrays.toString(events) + ", data=" + data + ", users="
				+ users + ", user=" + user + "]";
	}

	public AppProfile getAppProfile() {
		return appProfile;
	}

	public DeviceProfile getDeviceProfile() {
		return deviceProfile;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public Event[] getEvents() {
		return events;
	}

	public void setAppProfile(AppProfile appProfile) {
		this.appProfile = appProfile;
	}

	public void setDeviceProfile(DeviceProfile deviceProfile) {
		this.deviceProfile = deviceProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public void setEvents(Event[] events) {
		this.events = events;
	}

	public enum EventEnum {
		T1("T1"), T2("T2"), T3("T3"), T4("T4"), T5("T5"), T6("T6"), G1("G1"), G2("G2"), G3("G3"), G4("G4"), G5("G5"), G6("G6"), G7("G7"), G8("G8"), G9(
				"G9"), G10("G10"), G11("G11"), G12("G12"), G13("G13"), G14("G14"), G15("G15"),G16("G16"),G17("G17");

		private static final Map<String, EventEnum> lookup = new HashMap<String, EventEnum>();
		static {
			for (EventEnum t : EnumSet.allOf(EventEnum.class)) {
				lookup.put(t.value, t);
			}

		}

		public static EventEnum get(String code) {
			return lookup.get(code);
		}

		private final String value;

		private EventEnum(String value) {
			this.value = value;
		}

		public String value() {
			return this.value;
		}

	}
}
