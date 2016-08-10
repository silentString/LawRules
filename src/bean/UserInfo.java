package bean;

import java.util.Date;

public class UserInfo {
	
	private String phoneNumber;
	private String password;
	private Date endDate;
	private double points;
	private Date registerDate;
	private String nickName;
	private StatusEnum status;
	
	public enum StatusEnum{
		normal, expired;
	}
	
	public UserInfo(){}
	
	public UserInfo(String phoneNumber, String password) {
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	public UserInfo(String phoneNumber, String password, Date endDate, double points, Date registerDate,
			String nickName, StatusEnum status) {
	
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.endDate = endDate;
		this.points = points;
		this.registerDate = registerDate;
		this.nickName = nickName;
		this.status = status;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	

}
