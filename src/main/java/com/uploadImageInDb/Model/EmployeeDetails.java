package com.uploadImageInDb.Model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "imageupload")
public class EmployeeDetails {

	@Id
	@GeneratedValue
	@Column(name = "image_id")
	private int image_id;

//	@Column(name = "username")
//	private String username;

	@Column(name = "imagename")
	private String imagename;

	@Column(name = "image")
	private Blob image;

	@ManyToOne
	private LoginBean user_info;

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public LoginBean getUser_info() {
		return user_info;
	}

	public void setUser_info(LoginBean user_info) {
		this.user_info = user_info;
	}

	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

}
