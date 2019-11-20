<%@ page import=" com.uploadImageInDb.Model.LoginBean"%>
<%@ page import="com.uploadImageInDb.Model.EmployeeDetails"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.Blob"%>
<%@ page import="java.awt.image.BufferedImage"%>
<%@ page import="javax.imageio.ImageIO"%>
<%@ page import="java.io.ByteArrayInputStream"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.math.RoundingMode"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<script>
	function refreshPage() {
		window.location.reload();
	}
</script>
<style>
table, th, td {
	border: 3px solid black;
	border-collapse: collapse;
	padding: 15px;
	text-align: center;
}

table {
	border-spacing: 15px;
	border-collapse: collapse;
}
</style>
<meta charset="ISO-8859-1">
<title>Upload Image</title>
</head>
<body>

	<%
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("userLoginInfo") == null) {
		request.setAttribute("error", "Please Login First");
		request.getRequestDispatcher("/LoginPage.jsp").forward(request, response);

			} else {
	%>

	<form action="ImageControllerInDb" method="post"
		enctype="multipart/form-data">
		<fieldset>
			<center>IMAGE MANAGEMENT UTILITY</center>
			<hr>
			<p align="right">

				Welcome
				<%
				LoginBean userDetails = (LoginBean) session.getAttribute("userLoginInfo");

							String username = userDetails.getUsername();

							out.println(username);
			%>
				
				<br>
				<a href="LogoutControllerInDb">Sign Out</a>
			
			</p>
			Please select an image file to upload(Max Size 1 MB) <br> <br>
			<input type="text" id="image-div" disabled> <input
				type="file" id="image" name="image" accept="image/*" multiple
				required> <br> <br> <br>

			<p style="color: red"><%=(request.getAttribute("errorSizeExceeding") == null) ? ""
						: request.getAttribute("errorSizeExceeding")%></p>


			<button type="button" onClick="refreshPage()" style="float: right">Cancel</button>

			<input type="submit" style="float: right" value="Upload">
		</fieldset>
	</form>
	<%
		}

			LoginBean imageListTemp1 = (LoginBean) session.getAttribute("dbUser");

			List<EmployeeDetails> imageListTemp = imageListTemp1.getImages();

			out.println("Total image(s) uploaded = " + imageListTemp.size());

			if (imageListTemp.size() != 0) {
	%>
	<fieldset>
		<table style="width: 100%">

			<tr>
				<th>S.No</th>
				<th>Name</th>
				<th>Size</th>
				<th>Preview</th>
				<th>Action</th>
			</tr>
			<%
				if (session.getAttribute("dbUser") != null) {

							LoginBean imageList1 = (LoginBean) session.getAttribute("dbUser");

							List<EmployeeDetails> imageList = imageList1.getImages();

							int i = 1;
							for (EmployeeDetails eachImageAll : imageList) {
			%>
			<tr>
				<td>
					<%
						int imageId = eachImageAll.getImage_id();
									out.println(i);
					%> <input type="hidden" name="imgID" value="<%=imageId%>" />

				</td>

				<%
					Blob eachImageBlob = eachImageAll.getImage();
				%>
				<td>
					<%
						String imageName = eachImageAll.getImagename();
									out.println(imageName);
					%>
				</td>

				<%
					int blobImageLength = (int) eachImageBlob.length();
								byte[] eachImageByte = eachImageBlob.getBytes(1, blobImageLength);

								String imgStringBase64 = Base64.getEncoder().encodeToString(eachImageByte);
				%>

				<td>
					<%
						DecimalFormat decimalFormat = new DecimalFormat("#.###");
						decimalFormat.setRoundingMode(RoundingMode.CEILING); 
						
					
						double sizeImageBytes = (imgStringBase64.length());
									double sizeImageKB = sizeImageBytes / 1024 / 1024;
									out.println(decimalFormat.format(sizeImageKB) + " MB");
					%>
				</td>


				<td>
					<%
						out.println("<img src='data:image/jpg;base64," + imgStringBase64 + "' width='100' height='100'/>");
					%>
				</td>

				<td><a href="UpdateImageControllerInDb?imageId=<%=imageId%>"><button
							value="Edit">Edit</button></a> <a
					href="DeleteControllerInDb?imageId=<%=imageId%>"><button
							value="Delete">Delete</button></a></td>

				<%
					i++;
							}
						}
				%>
			</tr>
		</table>
	</fieldset>
	<%
		}
	%>
</body>
</html>