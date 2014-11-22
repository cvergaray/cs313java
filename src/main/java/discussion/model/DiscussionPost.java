package discussion.model;

import java.util.Date;

public class DiscussionPost {
	private String mText;
	private Date mTime;
	private String mUsername;

	public DiscussionPost(){
		setText("");
		setTime();
		mUsername = "Unauthenticated Guest";
	}
	
	public DiscussionPost(String pText, String pName){
		setText(pText);
		setTime();
		mUsername = pName;
	}
	
	public void setText(String pText){
		mText = pText;
	}
	
	public String getText(){
		return mText;
	}
	
	public String getUser(){
		return mUsername;
	}
	
	public void setTime(){
		mTime =	new Date();	
	}
	
	public Date getTime(){
		return mTime;
	}
	
	@Override
	 public String toString() {
	        return mTime.toString() + " : " + mText + " : " + mUsername;
	 }
	
	 public String toFileString() {
	     return mTime.toString() + "," + mText + "," + mUsername;
	}

	 public void loadFromFileString(String str) {
	     // TODO: Validation should be done here
	     String[] parts = str.split(",");


	     mTime.setTime(Date.parse(parts[0]));
	     mText = parts[1];
	     mUsername = parts[2];
	}
	 
}
