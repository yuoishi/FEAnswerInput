package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ExamDAO;
import dao.ScoreDAO;
import dao.UserDAO;
import dto.Exam;
import dto.Ranking;

/**
 * Servlet implementation class Ranking
 */
@WebServlet("/Ranking")
public class RankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankingServlet() {
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
			StringBuilder sb = new StringBuilder();

			ArrayList<Exam> list = ExamDAO.serchAllExam();

			String[] s = new String[10];

			for(int i = 0; i < 3; i++){

				/*午前と午後の区別を見分けるため*/
				if(i == 1){
					sb.append("<h2 id=\"am\" class=\"inlineblock\">午前</h2><a href=\"#pm\">午後</a>");
				}else if(i == 2){
					sb.append("<h2 id=\"pm\" class=\"inlineblock\">午後</h2><a href=\"#am\">午前</a>");
				}

				for(Exam exam : list){

					ArrayList<Ranking> ranking = ScoreDAO.serchScore(exam.getId(), i);
					for(int j = 0; j < ranking.size(); j++){
						s[j] = ranking.get(j).getUserID();
					}

					s = UserDAO.serchUserName(s);

					sb.append("<h2>" + exam.getExamName() + "</h2>");

					sb.append("<table>");
					sb.append("<tr>");
					sb.append("<th>順位</th><th>ユーザ名</th><th>得点</th>");
					sb.append("</tr>");

					for(int j = 0; j < ranking.size(); j++){
						sb.append("<tr>");
						sb.append("<td>" + (j + 1) + "位</td>");
						sb.append("<td>" + s[j] + "</td>");
						sb.append("<td>" + ranking.get(j).getScore() + "点</td>");
						sb.append("</tr>");
					}

					sb.append("</table>");
				}
			}
			String str = sb.toString();

			request.setAttribute("str", str);

			view += "/ranking.jsp";
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
