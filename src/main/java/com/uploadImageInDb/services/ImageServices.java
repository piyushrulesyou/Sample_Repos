package com.uploadImageInDb.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.fileupload.FileItem;

import com.uploadImageInDb.Dao.ImageDao;
import com.uploadImageInDb.Model.EmployeeDetails;
import com.uploadImageInDb.Model.LoginBean;

public class ImageServices {

	public boolean loadEachImageAndStore(LoginBean userLoginInfo, List<FileItem> listOfFiles,
			LoginBean userLoginImageList, Boolean errorImage) throws Exception {

		try {

			for (FileItem eachFile : listOfFiles) {

				long imageSize = eachFile.getSize();

				if (imageSize > 1024 * 1024) {
					throw new FileNotFoundException();
				}

				errorImage = false;

				String eachImageName = eachFile.getName();

				eachFile.write(new File("C:\\Users\\Public\\" + eachFile.getName()));

				String pathOfFile = "C:\\Users\\Public\\" + eachFile.getName();

				File file = new File(pathOfFile);

				byte[] imageByteArray = new byte[(int) file.length()];

				FileInputStream inputStream = null;

				try {

					inputStream = new FileInputStream(file);
					inputStream.read(imageByteArray);

				} catch (IOException e) {
					throw new Exception("Unable to convert file to byte array. " + e.getMessage());
				}

				finally {
					if (inputStream != null)
						inputStream.close();
				}

				try {
					Blob blobImage = new SerialBlob(imageByteArray);
					EmployeeDetails imageBean = new EmployeeDetails();
					imageBean.setImagename(eachImageName);
					imageBean.setImage(blobImage);
					imageBean.setUser_info(userLoginInfo);

					ImageDao imageDao = new ImageDao();
					imageDao.storeImageInDataBase(imageBean);

					userLoginImageList.getImages().add(imageBean);

					file.delete();

				} catch (Exception e) {
					System.out.println(e);
				}

			}
		} catch (FileNotFoundException e) {
			errorImage = true;
		}
		return errorImage;
	}
}
