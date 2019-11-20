//No business logic here!!
//Only take data from jsp and pass to service layer
package com.uploadImageInDb.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uploadImageInDb.Model.LoginBean;
import com.uploadImageInDb.services.LoginServices;

/**
 * Servlet implementation class LoginController
 */
public class LoginControllerInDb extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String iUserName = request.getParameter("username");
		String iPassword = request.getParameter("password");

		
		///////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
		
		
		
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(iUserName);
		loginBean.setPassword(iPassword);

		LoginServices loginServices = new LoginServices();
		LoginBean dbLoginBeanWithImages = loginServices.LoginService(loginBean);

		if (dbLoginBeanWithImages != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userLoginInfo", loginBean);

			if (dbLoginBeanWithImages.getImages() != null) {
				
				session.setAttribute("dbUser", dbLoginBeanWithImages);	//request
				session.setAttribute("userImageList", dbLoginBeanWithImages.getImages());
				
			}
			request.setAttribute("username", iUserName);
			response.sendRedirect("ImageUpload.jsp");

		} else {
			request.setAttribute("error", "Please Try Again!!");
			request.getRequestDispatcher("LoginPage.jsp").forward(request, response); //use in jsp
		}
	}

}
