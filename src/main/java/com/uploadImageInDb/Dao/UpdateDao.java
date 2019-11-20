package com.uploadImageInDb.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.uploadImageInDb.Model.EmployeeDetails;
import com.uploadImageInDb.Model.LoginBean;

public class UpdateDao {

	public EmployeeDetails getDetailsOfImageAndDeleteOldImage(int imageIdToBeUpdated) {

		EmployeeDetails dbImageBean = new EmployeeDetails();

		Configuration configuration = new Configuration().configure().addAnnotatedClass(LoginBean.class)
				.addAnnotatedClass(EmployeeDetails.class);

		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		dbImageBean = (EmployeeDetails) session.get(EmployeeDetails.class, imageIdToBeUpdated);

		session.delete(dbImageBean);
		
		transaction.commit();

		return dbImageBean;
	}

	public void addUpdateImagesToDatabase(EmployeeDetails updatedImageBean) {
		
		Configuration configuration = new Configuration().configure().addAnnotatedClass(EmployeeDetails.class)
				.addAnnotatedClass(LoginBean.class);

		SessionFactory sessionFactory2 = configuration.buildSessionFactory();

		Session session = sessionFactory2.openSession();

		Transaction transaction = session.beginTransaction();

		session.save(updatedImageBean);

		transaction.commit();
	}

}
