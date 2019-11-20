<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Image</title>
</head>
<body>
	<%
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		if (session.getAttribute("userLoginInfo") == null) {
			request.setAttribute("error", "Please Login First");
			request.getRequestDispatcher("/LoginPage.jsp").forward(request, response);

		} else {
	%>

	<fieldset>
		<form action="UpdateImageControllerInDb" method="post">
			Enter New Name of Image: <br> <br> <input type="text"
				name="newImageName"> <br> <br> <input
				type="submit" name="submit" value="Update">
		</form>
	</fieldset>
	<%
		}
	%>
</body>
</html>