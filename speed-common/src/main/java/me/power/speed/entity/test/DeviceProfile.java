package me.power.speed.entity.test;

import java.io.Serializable;

public class DeviceProfile implements Serializable {
	public static final long serialVersionUID = 3203684158784663510L;

	public String mobileModel;
	public String osSdkVersion;
	public Double lng;
	public Double lat;
	public String cpuABI;// CPU鍨嬪彿
	public String pixel;
	public String country;
	public String ip;
	public String language;
	public Integer timezone;
	public String osVersion;
	public Integer channel;
	public String m2G_3G;
	public Boolean isJailBroken;
	public String simOperator;
	public String networkOperator;
	public String hostName;
	public String deviceName;
	public Long kernBootTime;
	public String manufacture;
	public String carrier;
	public String deviceId;
	public String advertisingID;
	public String province;
	private String wap;// wifiAP
	private String cid;// cellID
	private String networkType;// 缃戠粶绫诲瀷;瀹㈡埛绔綉缁滅被鍨�濡俉IFI銆丠SDPA銆乄CDMA绛�	
	private String apnName;// apn鍚嶇О
	private String apnOperator; // apn mnc + mcc
	private String apnProxy;// apn 浠ｇ悊
	private String tdudid;//TD缁熶竴璁惧ID
	
	// add by hongliang.wang ------ start
	/**
	 * 璁惧绫诲瀷锛宨phone銆乮pad銆乮touch绛夛紝android鐨勮澶囩被鍨嬩负锛氭湭鐭�	 */
	private String deviceType;
	/**
	 * 璁惧鐨勫搧鐗岋細ios鐨勯粯璁や负鈥滆嫻鏋溾�
	 */
	private String brand;
	
	//璁惧鍋忕Щ閲�	
	private long offset;
	
	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	// add by hongliang.wang ------ end
	
	
	public String getAdvertisingID() {
		return advertisingID;
	}

	public void setAdvertisingID(String advertisingID) {
		this.advertisingID = advertisingID;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getManufacture() {
		return manufacture;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMobileModel() {
		return mobileModel;
	}

	public String getOsSdkVersion() {
		return osSdkVersion;
	}

	public Double getLng() {
		return lng;
	}

	public Double getLat() {
		return lat;
	}

	public String getCpuABI() {
		return cpuABI;
	}

	public String getPixel() {
		return pixel;
	}

	public String getCountry() {
		return country;
	}

	public String getIp() {
		return ip;
	}

	public String getLanguage() {
		return language;
	}

	public Integer getTimezone() {
		return timezone;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public Integer getChannel() {
		return channel;
	}

	public String getM2G_3G() {
		return m2G_3G;
	}

	public Boolean getIsJailBroken() {
		return isJailBroken;
	}

	public String getSimOperator() {
		return simOperator;
	}

	public String getNetworkOperator() {
		return networkOperator;
	}

	public String getHostName() {
		return hostName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public Long getKernBootTime() {
		return kernBootTime;
	}

	public void setMobileModel(String mobileModel) {
		this.mobileModel = mobileModel;
	}

	public void setOsSdkVersion(String osSdkVersion) {
		this.osSdkVersion = osSdkVersion;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public void setCpuABI(String cpuABI) {
		this.cpuABI = cpuABI;
	}

	public void setPixel(String pixel) {
		this.pixel = pixel;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public void setM2G_3G(String m2g_3g) {
		m2G_3G = m2g_3g;
	}

	public void setIsJailBroken(Boolean isJailBroken) {
		this.isJailBroken = isJailBroken;
	}

	public void setSimOperator(String simOperator) {
		this.simOperator = simOperator;
	}

	public void setNetworkOperator(String networkOperator) {
		this.networkOperator = networkOperator;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public void setKernBootTime(Long kernBootTime) {
		this.kernBootTime = kernBootTime;
	}

	public String getWap() {
		return wap;
	}

	public void setWap(String wap) {
		this.wap = wap;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}


	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getApnName() {
		return apnName;
	}

	public void setApnName(String apnName) {
		this.apnName = apnName;
	}

	public String getApnOperator() {
		return apnOperator;
	}

	public void setApnOperator(String apnOperator) {
		this.apnOperator = apnOperator;
	}

	public String getApnProxy() {
		return apnProxy;
	}

	public void setApnProxy(String apnProxy) {
		this.apnProxy = apnProxy;
	}
	
	public String getTdudid() {
		return tdudid;
	}

	public void setTdudid(String tdudid) {
		this.tdudid = tdudid;
	}
	
	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "DeviceProfile [mobileModel=" + mobileModel + ", osSdkVersion="
				+ osSdkVersion + ", lng=" + lng + ", lat=" + lat + ", cpuABI="
				+ cpuABI + ", pixel=" + pixel + ", country=" + country
				+ ", ip=" + ip + ", language=" + language + ", timezone="
				+ timezone + ", osVersion=" + osVersion + ", channel="
				+ channel + ", m2G_3G=" + m2G_3G + ", isJailBroken="
				+ isJailBroken + ", simOperator=" + simOperator
				+ ", networkOperator=" + networkOperator + ", hostName="
				+ hostName + ", deviceName=" + deviceName + ", kernBootTime="
				+ kernBootTime + ", manufacture=" + manufacture + ", carrier="
				+ carrier + ", deviceId=" + deviceId + ", advertisingID="
				+ advertisingID + ", province=" + province + ", wap=" + wap
				+ ", cid=" + cid + ", networkType=" + networkType
				+ ", apnName=" + apnName + ", apnOperator=" + apnOperator
				+ ", apnProxy=" + apnProxy + ", tdudid=" + tdudid + "]";
	}

	public DeviceProfile() {
	}
}
