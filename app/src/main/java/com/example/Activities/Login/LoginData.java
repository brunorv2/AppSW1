package com.example.Activities.Login;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class LoginData{

	@SerializedName("privileges")
	private List<String> privileges;

	@SerializedName("userData")
	private UserData userData;

	public void setPrivileges(List<String> privileges){
		this.privileges = privileges;
	}

	public List<String> getPrivileges(){
		return privileges;
	}

	public void setUserData(UserData userData){
		this.userData = userData;
	}

	public UserData getUserData(){
		return userData;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"privileges = '" + privileges + '\'' + 
			",userData = '" + userData + '\'' + 
			"}";
		}
}