package bo;

import java.util.ArrayList;

import dao.AmPmDAO;
import dao.ExamDAO;
import dao.HistoryOfExamDAO;
import dto.Score;
import dto.Sort;

public class HistoryOfExamLogic {

	/*受験履歴表示*/
	public static String execute(Sort sort, String userID){
		ArrayList<Score> list = HistoryOfExamDAO.serchAllHOE(sort, userID);

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < list.size(); i++){
			String date = list.get(i).getDate();
			String id = list.get(i).getId();
			String examName = ExamDAO.serchExam(id).getExamName();
			int ampmID = list.get(i).getAmpmID();
			String ampmName = AmPmDAO.serchAmPm(ampmID).getAmpmName();
			int score = list.get(i).getScore();
			String sOrF = list.get(i).getsOrF();

			sb.append("<tr>");
			sb.append("<td>" + date + "</td>");
			sb.append("<td>" + examName + "</td>");
			sb.append("<td>" + ampmName + "</td>");
			sb.append("<td>" + score + "点</td>");
			sb.append("<td>" + sOrF + "</td>");
			sb.append("</tr>");
		}
		return sb.toString();
	}
}
