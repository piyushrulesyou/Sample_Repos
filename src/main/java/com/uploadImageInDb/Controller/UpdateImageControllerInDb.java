package com.uploadImageInDb.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uploadImageInDb.Model.EmployeeDetails;
import com.uploadImageInDb.Model.LoginBean;
import com.uploadImageInDb.services.UpdateImageServices;

/**
 * Servlet implementation class UpdateImageController
 */
public class UpdateImageControllerInDb extends HttpServlet {

	UpdateImageServices updateImageServices;

	private static final long serialVersionUID = 1L;

	String imageId = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		imageId = request.getParameter("imageId");
		response.sendRedirect("UpdateImage.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		int imageIdToBeUpdated = Integer.parseInt(imageId);

		String newImageName = request.getParameter("newImageName");

		updateImageServices = new UpdateImageServices();
		EmployeeDetails updatedImage = updateImageServices.updateImage(imageIdToBeUpdated, newImageName);

		LoginBean userLoginImageList = (LoginBean) session.getAttribute("dbUser");

		List<EmployeeDetails> allImages = userLoginImageList.getImages();

		int count = 0;

		for (EmployeeDetails eachImage : allImages) {
			if (eachImage.getImage_id() != imageIdToBeUpdated)
				count++;
			else
				break;
		}

		userLoginImageList.getImages().set(count, updatedImage);

		response.sendRedirect("ImageUpload.jsp");
	}
}
