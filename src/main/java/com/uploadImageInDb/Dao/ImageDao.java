package com.uploadImageInDb.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.uploadImageInDb.Model.EmployeeDetails;
import com.uploadImageInDb.Model.LoginBean;

public class ImageDao {

	public void storeImageInDataBase(EmployeeDetails userImage) {

		Configuration configuration = new Configuration().configure().addAnnotatedClass(EmployeeDetails.class).addAnnotatedClass(LoginBean.class);
		
		SessionFactory sessionFactory2 = configuration.buildSessionFactory();
		
		Session session = sessionFactory2.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		session.save(userImage);
		
		transaction.commit();
	}

	
}
