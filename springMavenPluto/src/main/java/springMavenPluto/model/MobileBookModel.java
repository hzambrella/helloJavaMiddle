package springMavenPluto.model;

import java.io.Serializable;

public class MobileBookModel implements Serializable{
	private static final long serialVersionUID = 4259113025487907436L;
	
	public Integer Id;
	public Integer UserId;
	public String Name;
	public String Number;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}

	
	public Integer getUserId() {
		return UserId;
	}
	public void setUserId(Integer userId) {
		UserId = userId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
}
