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
import dao.HistoryOfExamDAO;
import dto.Exam;

/**
 * Servlet implementation class ExamSelecting
 */
@WebServlet("/Exam")
public class ExamSelecting extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamSelecting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute("login");

		String view = "/WEB-INF/view/";

		if(login != null){
			String userID = (String)session.getAttribute("userid");

			StringBuilder all = new StringBuilder();
			StringBuilder am = new StringBuilder();
			StringBuilder pm = new StringBuilder();

			ArrayList<Exam> examList = ExamDAO.serchAllExam();

			/*年度別*/
			for(int i = 0; i < examList.size(); i++){
				Exam exam = examList.get(i);
				all.append("<tr>");
				all.append("<td>");

				/*未/済を表示*/
				all.append(HistoryOfExamDAO.serchHOE(exam.getId(), userID, 0, true));
				all.append("</td>");

				/*問題年度を表示したリンクを表示*/
				all.append("<td><a href=\"/FEAnswerInput/ExamByYear?id=" + exam.getId() + "\">");
				all.append(exam.getExamName() + "</a></td>");
				all.append("<td>");

				/*合格率を表示*/
				double ap = HistoryOfExamDAO.serchSPercent(exam.getId(), 0, true);
				all.append(String.format("%.1f%%", ap));
				all.append("</td>");

				all.append("</tr>");
			}

			/*午前*/
			for(int i = 0; i < examList.size(); i++){
				Exam exam = examList.get(i);
				am.append("<tr>");
				am.append("<td>");

				/*未/済を表示*/
				am.append(HistoryOfExamDAO.serchHOE(exam.getId(), userID, 0, false));
				am.append("</td>");

				/*問題年度を表示したリンクを表示*/
				am.append("<td><a href=\"/FEAnswerInput/Answer?id=" + exam.getId() + "&ampmid=0\">");
				am.append(exam.getExamName() + "</a></td>");
				am.append("<td>");

				/*合格率を表示*/
				double a = HistoryOfExamDAO.serchSPercent(exam.getId(), 0, false);
				am.append(String.format("%.1f%%", a));
				am.append("</td>");

				am.append("</tr>");
			}

			/*午後*/
			for(int i = 0; i < examList.size(); i++){
				Exam exam = examList.get(i);
				pm.append("<tr>");
				pm.append("<td>");

				/*未/済を表示*/
				pm.append(HistoryOfExamDAO.serchHOE(exam.getId(), userID, 1, false));
				pm.append("</td>");

				/*問題年度を表示したリンクを表示*/
				pm.append("<td><a href=\"/FEAnswerInput/Answer?id=" + exam.getId() + "&ampmid=1\">");
				pm.append(exam.getExamName() + "</a></td>");
				pm.append("<td>");

				/*合格率を表示*/
				double p= HistoryOfExamDAO.serchSPercent(exam.getId(), 1, false);
				pm.append(String.format("%.1f%%", p));
				pm.append("</td>");

				pm.append("</tr>");
			}

			request.setAttribute("all", all.toString());
			request.setAttribute("am", am.toString());
			request.setAttribute("pm", pm.toString());

			view += "/examSelecting.jsp";
		}else{
			session.invalidate();
			view += "movingTop.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
