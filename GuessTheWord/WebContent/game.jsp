<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>

<head>
<title>Guess the Word</title>

<style >

 table, th {

  border: 5px solid black;
 
  border-collapse: collapse;
  }
  
th {
  padding: 15px;
}

</style>


</head>
<body bgcolor="#1ABC9C">


<h1 align="center" style="color:white" id="count">${displayCounter}</h1><hr>

<table style="width:100%" >


	<c:forEach items="${booleanList}" var="letter" varStatus="i">
	
	<c:if test="${letter}">
	<th style="background-color:WhiteSmoke;" ><h1 style="color:black">${letterList[i.index]}</h1></th>
	
	</c:if>
	
	<c:if test="${!letter}">
	<th style="background-color:WhiteSmoke;" ><h1 style="color:WhiteSmoke">${letterList[i.index]}</h1></th>
	
	</c:if>

  	
  	</c:forEach>

</table><br>


<form action="Game" accept-charset="UTF-8">

<input type="hidden" id="counter" name="counter" value="">
<input type="text" name="tipp">
<input type="submit" value="Send">
<input  style="float: right" type="submit" name="show" value="Show the Word">  

</form>

<hr>

<button onclick="startTimer()">Start</button>

<button id="stop" onclick="stopTimer()">Stop</button>


<button style="float: right" onclick="exit()">Exit</button><br><br>

<form action="Game">
<input  type="submit" name="new" value="New Game"> 
</form><br>

<form id="start" method="POST" action="Game">

<input type="hidden" name="start" value="Start the Game">

</form>


<h1 align="center" style="color:white">${addedChars}</h1>


<c:if test="${finish}">

<script >

	if(confirm("Would you like to play a new game?")){
		
		document.getElementById("start").submit();
		
	}
	else{
		
		
		if(confirm("Would you like to exit?")){

	        close();

		}    
		else{
			
			location.href= 'start.jsp';
		}
		
		
	}

</script>
	

</c:if>



<script>

	var result;
	var seconds=${counter};
	
function run() {
	
	var min=Math.floor(seconds/60);
	var sec=Math.floor(seconds%60);
	result=sec<10 ? min+":0"+sec : min+":"+sec;	

	document.getElementById("count").innerHTML = result;
	document.getElementById("counter").value = seconds;
	
	seconds++;
	
}
	
	var start=setInterval(run, 1000);
	
	
	function startTimer() {
		
		 start=setInterval(run, 1000);
		
		}
	
	
	function stopTimer() {
		
		  clearInterval(start);
			
		}
	

	function exit() {
		
		if(confirm("Would you like to exit?")){

	        close();

		}    
		
	}
	
</script>



</body>
</html>