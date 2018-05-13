package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.ExamPmLogic;
import dao.AmPmDAO;
import dao.ExamDAO;
import dto.AmPm;
import dto.Exam;

/**
 * Servlet implementation class AnswerInput
 */
@WebServlet("/Answer")
public class AnswerInput extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerInput() {
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
			int ampmID = Integer.parseInt(request.getParameter("ampmid"));
			AmPm ampm = AmPmDAO.serchAmPm(ampmID);
			session.setAttribute("ampm", ampm);

			/*examByYear.jspから遷移してきたかどうか確認*/
			Exam exam = (Exam)session.getAttribute("exam");
			if(exam == null){
				/*違う場合問題年度を取得*/
				String id = request.getParameter("id");
				exam = ExamDAO.serchExam(id);
				session.setAttribute("exam", exam);
			}

			StringBuilder sb = new StringBuilder();

			/*午前問題の解答欄を作成*/
			if(ampmID == 0){
				for(int i = 1; i <= 80; i++){
					if(i < 10){
						sb.append("<label class=\"toi\">問0" + i + "</label>&nbsp;");
					}else{
						sb.append("<label class=\"toi\">問" + i + "</label>&nbsp;");
					}
					sb.append("<input type=\"radio\" name=\"toi" + i + "\" value=\"0\" class=\"toi\">ア&nbsp;");
					sb.append("<input type=\"radio\" name=\"toi" + i + "\" value=\"1\" class=\"toi\">イ&nbsp;");
					sb.append("<input type=\"radio\" name=\"toi" + i + "\" value=\"2\" class=\"toi\">ウ&nbsp;");
					sb.append("<input type=\"radio\" name=\"toi" + i + "\" value=\"3\" class=\"toi\">エ");
					sb.append("<br>");
				}
			}else{
				/*午後問題の解答欄を作成*/
				for(int i = 2; i < 8; i++){
					sb.append("<input type=\"checkbox\" name=\"sentaku\" value=\"" + i + "\" onchange=\"checkSentaku('" + i + "');\">" + i);
				}
				sb.append("<br>");

				for(int i = 9; i <= 13; i++){
					sb.append("<input type=\"checkbox\" name=\"gengo\" value=\"" + i + "\" onchange=\"checkGengo('" + i + "');\">" + i);
				}
				sb.append("<br>");

				sb.append(ExamPmLogic.execute(exam.getId()));
			}

			request.setAttribute("str", sb.toString());

			view += "/answerInput.jsp";
		}else{
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
