package com.uploadImageInDb.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.uploadImageInDb.Model.EmployeeDetails;
import com.uploadImageInDb.Model.LoginBean;

public class DeleteDao {

	public EmployeeDetails deleteImageFromDb(int imageIdToBeDeleted) {

		EmployeeDetails deletedImageBean = new EmployeeDetails();

		Configuration configuration = new Configuration().configure().addAnnotatedClass(LoginBean.class)
				.addAnnotatedClass(EmployeeDetails.class);

		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		deletedImageBean = (EmployeeDetails) session.get(EmployeeDetails.class, imageIdToBeDeleted);

		session.delete(deletedImageBean);

		transaction.commit();

		return deletedImageBean;

	}

}
