<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    
	    <title>Levi9</title>
	    
	    <!-- Styles -->
	    <link rel="stylesheet" type="text/css" href="styles/bootstrap.min.css" />
	    <link rel="stylesheet" type="text/css" href="styles/style.css" />
	</head>
	<body>
		<header id="header">
			<img src="images/logo.png" />
			<h2>Levi9</h2>
   		</header>
		<section class="container">
		    <section id="content">
		       <form action="oauth/security_check" method="POST" class="panel panel-default form-signin" role="form" >
					<div class="form-group">
						<label for="email">E-mail</label>
						<input name='j_username' type="email" class="form-control" placeholder="E-mail" required="" autofocus="" />
					</div>
					<div class="form-group">
						<label for="password">Password</label>
						<input name='j_password' type="password" class="form-control" placeholder="Password" required="" />
					</div>
					<c:if test="${not empty param.failed}">
						<div class="alert alert-danger">Sorry, the e-mail and password you entered do not match. Please try again.</div>
					</c:if>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Sign In</button>
				</form>
		    </section>
		</section>
	</body>
</html>