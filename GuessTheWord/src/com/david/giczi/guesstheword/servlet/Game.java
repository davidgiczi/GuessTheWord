package com.david.giczi.guesstheword.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.david.giczi.guesstheword.exception.GuessedTheWordException;
import com.david.giczi.guesstheword.model.WordAdmin;


@WebServlet("/Game")
public class Game extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private WordAdmin admin;
	private int turn=0;
	
    public Game() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String start=request.getParameter("start");
		String newGame=request.getParameter("new");
		String tipp=request.getParameter("tipp");
		String counter=request.getParameter("counter");
		String showTheWord=request.getParameter("show");
		
	if(start!=null) {
		
		playGame(request, response);
	}
	else if(newGame!=null) {
		
		
		playGame(request, response);
		
	}
	else if(tipp!=null && showTheWord==null)	{
		
		
		inputData(tipp, counter, request, response);
		
	}
	else if(showTheWord!=null) {
		
		showTheWord(counter, request, response);
	}
	
	}

	
	private void playGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		turn=0;
		
		admin=new WordAdmin();
		admin.addWord();
		admin.initTheWordLists();
		
		request.setAttribute("finish", false);
		request.setAttribute("counter", 0);
		request.setAttribute("letterList", admin.getLetters());
		request.setAttribute("booleanList", admin.getBooleanList());
		request.setAttribute("displayCounter", convertCounter("0"));
		
		request.getRequestDispatcher("game.jsp").forward(request, response);
		
	}
	
	private void inputData(String tipp, String counter, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter pw=response.getWriter();
		turn++;
		
	if("".equals(tipp)) {
		
		RequestDispatcher rq=request.getRequestDispatcher("game.jsp");
		
		
		request.setAttribute("counter", Integer.parseInt(counter));
		request.setAttribute("letterList", admin.getLetters());
		request.setAttribute("booleanList", admin.getBooleanList());
		request.setAttribute("addedChars", admin.getAddedChars());
		request.setAttribute("displayCounter", convertCounter(counter));
		
		pw.println("<h4 style=color:red>Invalid input value, add it again!</h4>");
		
		rq.include(request, response);
		
	}
	
	 
	
	try {
		
		admin.addCharToAddedCharList(tipp);
		admin.processTheWord(tipp);
		admin.evaluateInputData(tipp);
		
		request.setAttribute("counter", Integer.parseInt(counter));
		request.setAttribute("letterList", admin.getLetters());
		request.setAttribute("booleanList", admin.getBooleanList());
		request.setAttribute("addedChars", admin.getAddedChars());
		request.setAttribute("displayCounter", convertCounter(counter));
		
		request.getRequestDispatcher("game.jsp").forward(request, response);
		
		
	} catch (GuessedTheWordException e) {
		
		
		request.setAttribute("letterList", admin.getLetters());
		request.setAttribute("booleanList", admin.getBooleanList());
		request.setAttribute("addedChars", admin.getAddedChars());
		
		request.setAttribute("finish", true);
		
		pw.println("<h3 style=color:red>Congratulations, you have guessed the word in "+turn+" steps!</h3>");
		
		pw.println("<h1 align=center style=color:white>"+convertCounter(counter)+"</h1>");
		
	   request.getRequestDispatcher("game.jsp").include(request, response);
	
		
	}
	
	
	}
	
	
	private void showTheWord(String counter, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		
		try {
			
			admin.processTheWord(admin.getWord());
			admin.evaluateInputData(admin.getWord());
			
			
		} catch (GuessedTheWordException e) {
			
			request.setAttribute("letterList", admin.getLetters());
			request.setAttribute("booleanList", admin.getBooleanList());
			request.setAttribute("addedChars", admin.getAddedChars());
			request.setAttribute("finish", true);
			request.setAttribute("displayCounter", convertCounter(counter));
			
			
			request.getRequestDispatcher("game.jsp").forward(request, response);
		}
		
	}
	
	private String convertCounter(String counter) {
		
		int min=(int) (Integer.parseInt(counter)/60);
		int sec=(int) (Integer.parseInt(counter)%60);
		
		return sec<10 ? min+":0"+sec : min+":"+sec;	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
