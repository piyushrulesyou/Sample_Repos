package com.uploadImageInDb.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uploadImageInDb.Model.EmployeeDetails;
import com.uploadImageInDb.Model.LoginBean;
import com.uploadImageInDb.services.DeleteServices;

/**
 * Servlet implementation class DeleteController
 */
public class DeleteControllerInDb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		HttpSession session = request.getSession(false);

		int imageIdToBeDeleted = Integer.parseInt(request.getParameter("imageId"));

		DeleteServices deleteServices = new DeleteServices();
		EmployeeDetails deleteImageInfo = deleteServices.deleteImage(imageIdToBeDeleted);

		LoginBean userLoginImageList = (LoginBean) session.getAttribute("dbUser");

		userLoginImageList.getImages().removeIf(t -> t.getImage_id() == deleteImageInfo.getImage_id());

		response.sendRedirect("ImageUpload.jsp");
	}

}
