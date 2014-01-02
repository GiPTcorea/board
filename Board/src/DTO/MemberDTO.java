package DTO;

public class MemberDTO {

	private int ID;
	private String Created_at;
	private String Updated_at;
	private String NickName;
	private String Password;
	private String picturePath;
	private String name;
	private String logID;
	
	private String Phone;
	private String Area;
	private String Car;
	private String CarYear;
	private String ton;
	private String Year;
	private String Mention;
	private String Age;
	
	public String getLogID() {
		return logID;
	}
	public void setLogID(String logID) {
		this.logID = logID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getCreated_at() {
		return Created_at;
	}
	public void setCreated_at(String created_at) {
		Created_at = created_at;
	}
	public String getUpdated_at() {
		return Updated_at;
	}
	public void setUpdated_at(String updated_at) {
		Updated_at = updated_at;
	}
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public String getCar() {
		return Car;
	}
	public void setCar(String car) {
		Car = car;
	}
	public String getCarYear() {
		return CarYear;
	}
	public void setCarYear(String carYear) {
		CarYear = carYear;
	}
	public String getTon() {
		return ton;
	}
	public void setTon(String ton) {
		this.ton = ton;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getMention() {
		return Mention;
	}
	public void setMention(String mention) {
		Mention = mention;
	}
	
}
