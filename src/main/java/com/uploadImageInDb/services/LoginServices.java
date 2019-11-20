package com.uploadImageInDb.services;

import com.uploadImageInDb.Dao.LoginDao;
import com.uploadImageInDb.Model.LoginBean;

public class LoginServices {

	String iUsername;
	String iPassword;

	LoginDao loginDao = new LoginDao();

	public LoginBean LoginService(LoginBean loginBean) {

		String username = loginBean.getUsername();
		String password = loginBean.getPassword();

		iUsername = username;
		iPassword = password;

		LoginBean dbLoginBean = loginDao.LoginDaos(iUsername);
		
		if(dbLoginBean == null) {
			return dbLoginBean;
		}
		
		
		if (iUsername.equalsIgnoreCase(dbLoginBean.getUsername())) {
			if (iPassword.equalsIgnoreCase(dbLoginBean.getPassword())) {
				System.out.println("Details Matched!!");
			} else {
				System.out.println("Wrong Password");
				return null;
			}
		} else {
			System.out.println("Wrong Username");
			return null;

		}
		return dbLoginBean;

	}

}
