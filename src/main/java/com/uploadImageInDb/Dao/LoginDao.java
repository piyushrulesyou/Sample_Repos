package com.uploadImageInDb.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.uploadImageInDb.Model.EmployeeDetails;
import com.uploadImageInDb.Model.LoginBean;

public class LoginDao {

	public LoginBean LoginDaos(String iUsername) {

		LoginBean dbLoginBean = new LoginBean();

		Configuration configuration = new Configuration().configure().addAnnotatedClass(LoginBean.class)
				.addAnnotatedClass(EmployeeDetails.class);

		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		dbLoginBean = (LoginBean) session.get(LoginBean.class, iUsername);

		transaction.commit();

		return dbLoginBean;

	}
}
