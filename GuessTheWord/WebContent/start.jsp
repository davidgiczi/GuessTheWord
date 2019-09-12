<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Guess the Word</title>
</head>
<body bgcolor="#1ABC9C">



<h1 align="center" style="color:white" >Would you like to play a game?</h1><hr>

<form method="POST" action="Game">

<input type="submit" name="start" value="Start the Game">

</form>

<button type="button" onclick="exit()" style="float:right">Exit</button><br>

<script>
			function exit() {
							
				if(confirm("Would you like to exit?")){

			        close();
	
				}    
				
			}
		
		</script>	

</body>
</html>