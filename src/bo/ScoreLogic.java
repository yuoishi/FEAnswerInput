package bo;

import java.util.ArrayList;

import dao.AnswerDAO;
import dao.ScoreDAO;
import dto.Answer;
import dto.CorrectAnswer;
import dto.PreScore;
import dto.Score;

public class ScoreLogic {

	/*午前問題採点処理*/
	public static Score execute(Answer answer, String userID, String date){
		CorrectAnswer correctAnswer = AnswerDAO.serchCorrectAnswer(answer);
		StringBuilder sb = new StringBuilder();
		String cAns = correctAnswer.getAnswer();
		String ans = answer.getAnswer();
		String sOrF = "";
		int cnt = 0;
		for(int i = 0; i < cAns.length(); i++){
			String c = cAns.substring(i, i + 1);
			String a = ans.substring(i, i + 1);
			if(a.equals(c)){
				sb.append("○");
				cnt++;
			}else if(a.equals("n")){
				sb.append("N");
			}else{
				sb.append("×");
			}
			sb.append(",");
		}
		int border = cAns.length() / cAns.length() * 60;
		double work = cnt * 100.0 / cAns.length();
		int score = (int)(Math.ceil(work));
		if(score >= border){
			sOrF = "合格";
		}else{
			sOrF = "不合格";
		}
		return new Score(answer.getId(), answer.getAmpmID(), userID, score, date, sOrF, sb.toString());
	}

	/*午後問題採点処理*/
	public static Score execute(Answer answer, int[] s, String userID, String date){
		StringBuilder sb = new StringBuilder();
		String ans = answer.getAnswer();
		String sOrF = "";
		int num = 0;
		int cnt = 0;
		int cor = 0;
		int[] cntNum = new int[s.length];
		int[] corNum = new int[s.length];

		for(int i = 0; i < s.length; i++){
			if(s[i] == 0){
				continue;
			}
			CorrectAnswer correctAnswer = AnswerDAO.serchCorrectAnswer(answer, s[i]);
			String cAns = correctAnswer.getAnswer();
			for(int j = 0; j < cAns.length(); j++){
				String c = cAns.substring(j, j + 1);
				/*"j"から"k"までが順不同の解答というマーク*/
				if(c.equals("j")){
					ArrayList<String> list = new ArrayList<String>();
					j++;
					c = cAns.substring(j, j + 1);
					while(!c.equals("k")){
						list.add(c);
						j++;
						c = cAns.substring(j, j + 1);
					}

					String[] aa = new String[list.size()];
					for(int k = 0; k < list.size(); k++){
						aa[k] = ans.substring(num + k, num + k + 1);
					}

					for(int k = 0; k < aa.length; k++){
						int idx = list.indexOf(aa[k]);
						if(idx != -1){
							sb.append("○");
							cor++;
							list.remove(idx);
						}else if(aa[k].equals("n")){
							sb.append("N");
						}else{
							sb.append("×");
						}
						cnt++;
						sb.append(",");
					}
				/*"m"から"n"までが複数解答というマーク*/
				}else if(c.equals("m")){
					ArrayList<String> list = new ArrayList<String>();
					j++;
					c = cAns.substring(j, j + 1);
					while(!c.equals("n")){
						list.add(c);
						j++;
						c = cAns.substring(j, j + 1);
					}

					String[] aa = new String[list.size()];
					for(int k = 0; k < list.size(); k++){
						aa[k] = ans.substring(num + k, num + k + 1);
					}

					int cc = 0;
					for(int k = 0; k < aa.length; k++){
						int idx = list.indexOf(aa[k]);
						if(aa[k].equals("n")){
							sb.append("N");
						}else if(idx == -1){
							sb.append("×");
							break;
						}else{
							cc++;
						}
					}
					if(aa.length == cc){
						sb.append("○");
						cor++;
					}
					cnt++;
					sb.append(",");
				}else{
					String a = ans.substring(num, num + 1);
					if(a.equals(c)){
						sb.append("○");
						cor++;
					}else if(a.equals("n")){
						sb.append("N");
					}else{
						sb.append("×");
					}
					num++;
					cnt++;
					sb.append(",");
				}
				cntNum[i] = cnt;
				corNum[i] = cor;
				cnt = 0;
				cor = 0;
			}
		}

		final int BORDER = 60;
		double work = 0.0;
		for(int i = 0; i < s.length; i++){
			if(s[i] == 0){
				continue;
			}else{
				if(i < 5){
					work += corNum[i] * 12.0 / cntNum[i];
				}else{
					work += corNum[i] * 20.0 / cntNum[i];
				}
			}
		}
		int score = (int)(Math.ceil(work));
		if(score >= BORDER){
			sOrF = "合格";
		}else{
			sOrF = "不合格";
		}
		return new Score(answer.getId(), answer.getAmpmID(), userID, score, date, sOrF, sb.toString());
	}

	/*前回の得点を取得、今回の得点をDBに反映する処理*/
	public static PreScore execute(Score score){
		PreScore preScore = ScoreDAO.serchScore(score);
		if(preScore.isFlg()){
			ScoreDAO.updateScore(score);
		}else{
			ScoreDAO.addScore(score);
		}
		return preScore;
	}
}