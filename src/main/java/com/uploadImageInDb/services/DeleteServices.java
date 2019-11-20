package com.uploadImageInDb.services;

import com.uploadImageInDb.Dao.DeleteDao;
import com.uploadImageInDb.Model.EmployeeDetails;

public class DeleteServices {

	public EmployeeDetails deleteImage(int imageIdToBeDeleted) {

		DeleteDao deleteDao = new DeleteDao();
		EmployeeDetails deletedImageBean = deleteDao.deleteImageFromDb(imageIdToBeDeleted);
		
		return deletedImageBean;
		
	}

}
