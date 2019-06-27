package com.example.Activities.Login;

import com.google.gson.annotations.SerializedName;


public class UserData{

	@SerializedName("auth")
	private int auth;

	@SerializedName("id_companyUser")
	private int idCompanyUser;

	@SerializedName("Email")
	private String email;

	@SerializedName("apiMapBox")
	private String apiMapBox;

	@SerializedName("idCC")
	private int idCC;

	@SerializedName("apiGoogle")
	private String apiGoogle;

	@SerializedName("NameCompany")
	private String nameCompany;

	@SerializedName("idUser")
	private int idUser;

	@SerializedName("YearNow")
	private int yearNow;

	@SerializedName("see_all")
	private boolean seeAll;

	@SerializedName("apiBing")
	private String apiBing;

	@SerializedName("Worker")
	private String worker;

	@SerializedName("id_manager")
	private int idManager;

	@SerializedName("user_hierarchy")
	private String userHierarchy;


	public void setAuth(int auth){
		this.auth = auth;
	}

	public int getAuth(){
		return auth;
	}

	public void setIdCompanyUser(int idCompanyUser){
		this.idCompanyUser = idCompanyUser;
	}

	public int getIdCompanyUser(){
		return idCompanyUser;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setApiMapBox(String apiMapBox){
		this.apiMapBox = apiMapBox;
	}

	public String getApiMapBox(){
		return apiMapBox;
	}

	public void setIdCC(int idCC){
		this.idCC = idCC;
	}

	public int getIdCC(){
		return idCC;
	}

	public void setApiGoogle(String apiGoogle){
		this.apiGoogle = apiGoogle;
	}

	public String getApiGoogle(){
		return apiGoogle;
	}

	public void setNameCompany(String nameCompany){
		this.nameCompany = nameCompany;
	}

	public String getNameCompany(){
		return nameCompany;
	}

	public void setIdUser(int idUser){
		this.idUser = idUser;
	}

	public int getIdUser(){
		return idUser;
	}

	public void setYearNow(int yearNow){
		this.yearNow = yearNow;
	}

	public int getYearNow(){
		return yearNow;
	}

	public void setSeeAll(boolean seeAll){
		this.seeAll = seeAll;
	}

	public boolean isSeeAll(){
		return seeAll;
	}

	public void setApiBing(String apiBing){
		this.apiBing = apiBing;
	}

	public String getApiBing(){
		return apiBing;
	}

	public void setWorker(String worker){
		this.worker = worker;
	}

	public String getWorker(){
		return worker;
	}

	public void setIdManager(int idManager){
		this.idManager = idManager;
	}

	public int getIdManager(){
		return idManager;
	}

	public void setUserHierarchy(String userHierarchy){
		this.userHierarchy = userHierarchy;
	}

	public String getUserHierarchy(){
		return userHierarchy;
	}

	@Override
 	public String toString(){
		return 
			"UserData{" + 
			"id_companyUser = '" + idCompanyUser + '\'' + 
			",email = '" + email + '\'' + 
			",apiMapBox = '" + apiMapBox + '\'' + 
			",idCC = '" + idCC + '\'' + 
			",apiGoogle = '" + apiGoogle + '\'' + 
			",nameCompany = '" + nameCompany + '\'' + 
			",idUser = '" + idUser + '\'' + 
			",yearNow = '" + yearNow + '\'' + 
			",see_all = '" + seeAll + '\'' + 
			",apiBing = '" + apiBing + '\'' + 
			",worker = '" + worker + '\'' + 
			",id_manager = '" + idManager + '\'' + 
			",user_hierarchy = '" + userHierarchy + '\'' + 
			"}";
		}
}