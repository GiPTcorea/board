package DTO;

public class ContentDTO {
	private int ID;//�ۼ���
	private String logID;
	
	private String Content;//����
	private String PicturePath;//������
	private int Number;//Primary key -auto_increment
	private int ReadCnt;//���� ��
	private int MeTooCnt;//���� ��
	private int CommentCnt;//��� ��
	private String CreatedAt;//�ۼ���
	private String UpdatedAt;//�ۼ���
	
	public String getLogID() {
		return logID;
	}
	public void setLogID(String logID) {
		this.logID = logID;
	}
	public String getUpdatedAt() {
		return UpdatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		UpdatedAt = updatedAt;
	}
	public String getPicturePath() {
		return PicturePath;
	}
	public void setPicturePath(String picturePath) {
		PicturePath = picturePath;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getNumber() {
		return Number;
	}
	public void setNumber(int number) {
		Number = number;
	}
	public int getReadCnt() {
		return ReadCnt;
	}
	public void setReadCnt(int readCnt) {
		ReadCnt = readCnt;
	}
	public int getMeTooCnt() {
		return MeTooCnt;
	}
	public void setMeTooCnt(int meTooCnt) {
		MeTooCnt = meTooCnt;
	}
	public int getCommentCnt() {
		return CommentCnt;
	}
	public void setCommentCnt(int commentCnt) {
		CommentCnt = commentCnt;
	}
	public String getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(String createdAt) {
		CreatedAt = createdAt;
	}
}
