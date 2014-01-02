package DTO;

public class CommentDTO {
	private int ID; //�ۼ���
	private String logID;
	
	private String Comment;//����
	private String CreatedAt;//�ۼ���
	private int Number;//Primary key -auto_increment
	private int ContentNumber;// �� ��ȣ
	private String picturePath;
	
	public String getLogID() {
		return logID;
	}
	public void setLogID(String logID) {
		this.logID = logID;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}

	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	public String getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(String createdAt) {
		CreatedAt = createdAt;
	}
	public int getNumber() {
		return Number;
	}
	public void setNumber(int number) {
		Number = number;
	}
	public int getContentNumber() {
		return ContentNumber;
	}
	public void setContentNumber(int contentNumber) {
		ContentNumber = contentNumber;
	}
}
