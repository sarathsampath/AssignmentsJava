package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity(name="UserDataUtility")
@Table(name="LoginUsers")
public class Login {
	@Size(min=3, max=50)
	private String Name;
	@Size(min=3, max=50)
	private String Password;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int Id;
	
	
	private int Status;
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	
}
