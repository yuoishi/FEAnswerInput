package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.ScoreLogic;
import dao.ExamPmDAO;
import dao.HistoryOfExamDAO;
import dto.AmPm;
import dto.Answer;
import dto.Exam;
import dto.PreScore;
import dto.Score;

/**
 * Servlet implementation class Score
 */
@WebServlet("/Score")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/view/movingTop.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute("login");

		String view = "/WEB-INF/view";

		if(login != null){
			Calendar cal = Calendar.getInstance();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			String date = sdf.format(cal.getTime());

			Exam exam = (Exam)session.getAttribute("exam");
			AmPm ampm = (AmPm)session.getAttribute("ampm");

			String id = exam.getId();
			int ampmID = ampm.getAmpmID();
			String userID = (String)session.getAttribute("userid");

			int[] s = new int[7];
			Score score = new Score();

			StringBuilder sb = new StringBuilder();

			ArrayList<String> examList = ExamPmDAO.serchExam(id);

			if(ampmID == 0){
				for(int i = 1; i <= 80; i++){
					String str = request.getParameter("toi" + i);
					if(str == null){
						str = "n";
					}
					sb.append(str);
				}
			}else{
				String[] sentaku = request.getParameterValues("sentaku");
				String gengo = request.getParameter("gengo");

				s[0] = 1;	//問１

				//選択問題　問２～問７
				for(int i = 0; sentaku != null && i < sentaku.length; i++){
					s[i + 1] = Integer.parseInt(sentaku[i]);
				}

				s[5] = 8;	//問８

				if(gengo != null){
					s[6] = Integer.parseInt(gengo);  //プログラム言語問題
				}

				for(int i = 0; i < s.length; i++){

					/*要素の中身が空の場合*/
					if(s[i] == 0){
						continue;
					}

					/*個々の設問名に切り出す*/
					String[] e = examList.get(s[i] - 1).split(",");

					for(int j = 0; j < e.length; j++){
						String[] st = request.getParameterValues("toi" + s[i] + ":" + e[j]);
						for(String str : st){
							if(str.equals("-")){
								str = "n";
							}
							sb.append(str);
						}
					}
				}
			}

			Answer answer = new Answer(id, ampmID, sb.toString());

			/*採点処理を呼び出す*/
			if(ampmID == 0){
				score = ScoreLogic.execute(answer, userID, date);
			}else{
				score = ScoreLogic.execute(answer, s, userID, date);
			}

			/*前回の得点を取得*/
			PreScore preScore = ScoreLogic.execute(score);

			/*受験履歴に追加*/
			HistoryOfExamDAO.addHOE(score);

			session.setAttribute("score", score);
			session.setAttribute("preScore", preScore);

			/*○×Nを表示する上に出す設問名などをまとめる処理*/
			StringBuilder mon = new StringBuilder();
			if(answer.getAmpmID() == 0){
				for(int i = 1; i <= 80; i++){
					mon.append(i);
					mon.append(",");
				}
			}else{
				for(int i = 0; i < s.length; i++){

					/*要素の中身が空の場合*/
					if(s[i] == 0){
						continue;
					}
					mon.append("問" + s[i] + ":");

					String[] e = examList.get(s[i] - 1).split(",");

					for(int j = 0; j < e.length; j++){
						mon.append(e[j]);
						mon.append(",");
					}
				}
			}
			request.setAttribute("mon", mon.toString());

			view += "/score.jsp";
		}else{
			session.invalidate();
			view += "/movingTop.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}