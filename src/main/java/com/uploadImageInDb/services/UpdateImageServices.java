package com.uploadImageInDb.services;

import com.uploadImageInDb.Dao.UpdateDao;
import com.uploadImageInDb.Model.EmployeeDetails;

public class UpdateImageServices {

	public EmployeeDetails updateImage(int imageIdToBeUpdated, String newImageName) {

		UpdateDao updateDao = new UpdateDao();
		EmployeeDetails imageToBeUpdated = updateDao.getDetailsOfImageAndDeleteOldImage(imageIdToBeUpdated);

		imageToBeUpdated.setImagename(newImageName);

		updateDao.addUpdateImagesToDatabase(imageToBeUpdated);

		return imageToBeUpdated;

	}

}
