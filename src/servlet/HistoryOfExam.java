package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.HistoryOfExamLogic;
import dto.Sort;

/**
 * Servlet implementation class HistoryOfExam
 */
@WebServlet("/HOE")
public class HistoryOfExam extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryOfExam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute("login");

		String view = "/WEB-INF/view";

		if(login != null){
			String userID = (String)session.getAttribute("userid");

			String sort = request.getParameter("sort");

			String str = "";

			if(sort == null){
				str = HistoryOfExamLogic.execute(new Sort(64), userID);
			}else{
				/*ソートをかけるための下準備-------------------------*/

				String d = request.getParameter("date");
				String y = request.getParameter("year");
				String s = request.getParameter("score");
				String ap = request.getParameter("ampm");

				int date = 0;
				int year = 0;
				int score = 0;
				int ampm = 0;

				if(d != null){
					date = Integer.parseInt(d);
				}
				if(y != null){
					year = Integer.parseInt(y);
				}
				if(s != null){
					score = Integer.parseInt(s);
				}
				if(ap != null){
					ampm = Integer.parseInt(ap);
				}

				int flg = date + year + score + ampm;

				/*ここまで---------------------------------------*/

				str = HistoryOfExamLogic.execute(new Sort(flg), userID);
			}

			request.setAttribute("str", str);

			view += "/historyOfExam.jsp";
		}else{
			session.invalidate();
			view += "/movingTop.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
