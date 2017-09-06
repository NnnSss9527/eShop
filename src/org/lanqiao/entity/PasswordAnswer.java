package org.lanqiao.entity;

public class PasswordAnswer {
	private String answerid;
	private String question;
	private String answer;
	private String email;
	private String userid;
	public PasswordAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PasswordAnswer(String answerid, String question, String answer, String email, String userid) {
		super();
		this.answerid = answerid;
		this.question = question;
		this.answer = answer;
		this.email = email;
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "PasswordAnswer [answerid=" + answerid + ", question=" + question + ", answer=" + answer + ", email="
				+ email + ", userid=" + userid + "]";
	}
	public String getAnswerid() {
		return answerid;
	}
	public void setAnswerid(String answerid) {
		this.answerid = answerid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
