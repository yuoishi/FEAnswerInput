package bo;

import java.util.ArrayList;

import dao.ExamPmDAO;

public class ExamPmLogic {

	/*午後問題解答欄作成*/
	public static String execute(String id){
		ArrayList<String> examList = ExamPmDAO.serchExam(id);
		ArrayList<String> answerList = ExamPmDAO.serchAnswer(id);

		StringBuilder sb = new StringBuilder();
		String toi = "toi";

		for(int i = 1; i <= 13; i++){
			String[] e = examList.get(i - 1).split(",");
			String[] a = answerList.get(i - 1).split(",");

			if(i != 1 && i != 8){
				sb.append("<div id=\"" + toi + i + "\" style=\"display: block;\">");
			}else{
				sb.append("<div id=\"" + toi + i + "\">");
			}

			sb.append("<table>");
			sb.append("<caption>問" + i + "</caption>");

			String s = "設問";

			for(int j = 0; j < e.length; j++){
				sb.append("<tr>");
				if(e[j].matches("[0-9]+[a-z]*")){
					sb.append("<th>" + s + e[j] + "</th>");
				}else{
					sb.append("<th>　　" + e[j] + "</th>");
				}
				sb.append("<td>");
				if(a[j].substring(0, 1).equals("m")){
					int cnt = 1;
					while(!a[j].substring(cnt, cnt + 1).equals("n")){
						sb.append(makeDDB(toi + i + ":" + e[j], a[j].substring(cnt, cnt + 1)));
						cnt++;
					}
				}else{
					sb.append(makeDDB(toi + i + ":" + e[j], a[j]));
				}
				sb.append("</td>");
				sb.append("</tr>");
			}

			sb.append("</table>");
			sb.append("</div>");
		}
		return sb.toString();
	}

	/*解答の選択肢作成*/
	public static String makeDDB(String str, String num){
		StringBuilder sb = new StringBuilder();

		String[] p = {"ア", "イ", "ウ", "エ", "オ", "カ", "キ", "ク", "ケ", "コ"};

		int n = Integer.parseInt(num);

		sb.append("<select name=" + str + ">");

		sb.append("<option>-</option>");
		for(int i = 0; i < n; i++){
			sb.append("<option value=\"" + i + "\">" + p[i] + "</option>");
		}

		sb.append("</select>");

		return sb.toString();
	}
}