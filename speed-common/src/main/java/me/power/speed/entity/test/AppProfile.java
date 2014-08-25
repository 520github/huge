package me.power.speed.entity.test;

import java.io.Serializable;

public class AppProfile implements Serializable {
	private static final long serialVersionUID = 7127994998872886384L;
	
	public String sequenceNumber;
	public String productName;
	public String appPackageName;
	public String appDisplayName;
	public String appVersionName;
	public String appVersionCode;
	public String sdkVersion;
	public String sdkType;
	public String partner;
	public Long appStartTime;
	public String isCracked;
	public Long installationTime;
	public Long purchaseTime;// 应用购买时间
	public Long appStoreID;

	public String version;
	public Integer platformid;
	public Integer partnerid;
	public Integer productid;
	public Integer developerid;
	public Integer groupid;

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public Integer getProductid() {
		return productid;
	}

	public Integer getDeveloperid() {
		return developerid;
	}

	public Long getAppStoreID() {
		return appStoreID;
	}

	public void setAppStoreID(Long appStoreID) {
		this.appStoreID = appStoreID;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public void setDeveloperid(Integer developerid) {
		this.developerid = developerid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public String getProductName() {
		return productName;
	}

	public String getAppPackageName() {
		return appPackageName;
	}

	public String getAppDisplayName() {
		return appDisplayName;
	}

	public String getAppVersionName() {
		return appVersionName;
	}

	public String getAppVersionCode() {
		return appVersionCode;
	}

	public String getSdkVersion() {
		return sdkVersion;
	}

	public String getSdkType() {
		return sdkType;
	}

	public String getPartner() {
		return partner;
	}

	public Long getAppStartTime() {
		return appStartTime;
	}

	public Long getInstallationTime() {
		return installationTime;
	}

	public Long getPurchaseTime() {
		return purchaseTime;
	}

	public String getVersion() {
		return version;
	}

	public Integer getPlatformid() {
		return platformid;
	}

	public Integer getPartnerid() {
		return partnerid;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setAppPackageName(String appPackageName) {
		this.appPackageName = appPackageName;
	}

	public void setAppDisplayName(String appDisplayName) {
		this.appDisplayName = appDisplayName;
	}

	public void setAppVersionName(String appVersionName) {
		this.appVersionName = appVersionName;
	}

	public void setAppVersionCode(String appVersionCode) {
		this.appVersionCode = appVersionCode;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	public void setSdkType(String sdkType) {
		this.sdkType = sdkType;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public void setAppStartTime(Long appStartTime) {
		this.appStartTime = appStartTime;
	}

	public String getIsCracked() {
		return isCracked;
	}

	public void setIsCracked(String isCracked) {
		this.isCracked = isCracked;
	}

	public void setInstallationTime(Long installationTime) {
		this.installationTime = installationTime;
	}

	public void setPurchaseTime(Long purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setPlatformid(Integer platformid) {
		this.platformid = platformid;
	}

	public void setPartnerid(Integer partnerid) {
		this.partnerid = partnerid;
	}

	@Override
	public String toString() {
		return "AppProfile [sequenceNumber=" + sequenceNumber + ", productName=" + productName + ", appPackageName=" + appPackageName
				+ ", appDisplayName=" + appDisplayName + ", appVersionName=" + appVersionName + ", appVersionCode=" + appVersionCode
				+ ", sdkVersion=" + sdkVersion + ", sdkType=" + sdkType + ", partner=" + partner + ", appStartTime=" + appStartTime + ", isCracked="
				+ isCracked + ", installationTime=" + installationTime + ", purchaseTime=" + purchaseTime + ", appStoreID=" + appStoreID
				+ ", version=" + version + ", platformid=" + platformid + ", partnerid=" + partnerid + ", productid=" + productid + ", developerid="
				+ developerid + ", groupid=" + groupid + "]";
	}

	public AppProfile() {
	}
}
