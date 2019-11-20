<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Login Page</title>

</head>
<body>
	<form action="LoginControllerInDb" method=post>
		<hr>
		LOGIN
		<hr>

		<fieldset>
			<legend>User Login</legend>
			<br> Username: <br> <input type="text" name="username"
				placeholder="Enter your username" value="Piyush"> <br>
			<br> Password: <br> <input type="password" name="password"
				placeholder="Enter your password" value="Agarwal"> <br>
			<br>

			<p style="color: red"><%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></p>

			<br> <br> <input type="submit" name="submit"
				value="Sign In">
		</fieldset>
	</form>
</body>
</html>