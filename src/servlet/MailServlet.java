package servlet;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.UserLogic;
import dao.UserDAO;
import dto.User;

/**
 * Servlet implementation class MailServlet
 */
@WebServlet("/Mail")
public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailServlet() {
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
		request.setCharacterEncoding("UTF-8");

		/*どこから遷移してきたか確認用*/
		 String flg = request.getParameter("hidden");

		 HttpSession pcSession = request.getSession();

		 if(flg.equals("0")){
			 User user = new User();

			 String userID = request.getParameter("userid");
			 String email = request.getParameter("email");
			 String sq = request.getParameter("sq");

			 user.setUserID(userID);
			 user.setEmail(email);
			 user.setSq(sq);

			 pcSession.setAttribute("user", user);
		 }

		 if(flg.equals("0") || flg.equals("1")){
			 Random random = new Random();
			 int passCode = random.nextInt(9000000) + 1000000;

			 /*パスコード入力失敗時に表示するための
			  * スコープの内容を合わせるために必要
			  * */
			 String remove = "<p hidden></p><br>";
			 request.setAttribute("remove", remove);

			 pcSession.setAttribute("passcode", passCode);

			 User user = (User)pcSession.getAttribute("user");

			 String[] s = {user.getUserID()};
			 String[] userName = UserDAO.serchUserName(s);

			 if(UserLogic.execute(user)){

				 /*下の3つの値を適切なものに書き換えてください。*/
				 String mail_address = "送信元のメールアドレス";
				 String account_password = "送信元のアカウントのパスワード";
				 String account_name = "送信元のアカウント名";

				 /*メールに記載される内容*/
				 String title = "パスワード再設定";
				 String message = "パスコード:" + passCode;

				 try{
					 /*メール送信に必要な処理ここから*/
					 Properties property = new Properties();

					 property.put("mail.smtp.host","smtp.gmail.com");

					 //GmailのSMTPを使う場合
					 property.put("mail.smtp.auth", "true");
					 property.put("mail.smtp.starttls.enable", "true");
					 property.put("mail.smtp.host", "smtp.gmail.com");
					 property.put("mail.smtp.port", "587");
					 property.put("mail.smtp.debug", "true");

					 Session session = Session.getInstance(property, new javax.mail.Authenticator(){
						 protected PasswordAuthentication getPasswordAuthentication(){
							 return new PasswordAuthentication(mail_address, account_password);
						 }
					 });

					 MimeMessage mimeMessage = new MimeMessage(session);

					 /*メール宛先*/
					 InternetAddress toAddress =
							 new InternetAddress(user.getEmail(), userName[0]);

					 mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);

					 /*メール送信元*/
					 InternetAddress fromAddress =
							 new InternetAddress(mail_address, account_name);

					 mimeMessage.setFrom(fromAddress);

					 mimeMessage.setSubject(title, "ISO-2022-JP");

					 mimeMessage.setText(message, "ISO-2022-JP");

					 Transport.send(mimeMessage);
					 /*メール送信に必要な処理ここまで*/

					 String view = "/WEB-INF/view/mailCompleting.jsp";
					 RequestDispatcher dispatcher = request.getRequestDispatcher(view);
					 dispatcher.forward(request, response);
				 }
				 catch(Exception e){
					 /*メール送信ロジックでエラーがあった場合*/
					 request.setAttribute("flg", "0");

					 String view = "/WEB-INF/view/mailFailing.jsp";
					 RequestDispatcher dispatcher = request.getRequestDispatcher(view);
					 dispatcher.forward(request, response);
				 }
			 }else{
				 /*ユーザの入力値と登録値が異なった場合*/
				 request.setAttribute("flg", "1");

				 String view = "/WEB-INF/view/mailFailing.jsp";
				 RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				 dispatcher.forward(request, response);
			 }
		 }else{
			 /*入力された値と送信したパスコードが一致するかチェック*/

			 /*入力された値取得*/
			 String passCode = request.getParameter("passcode");
			 int pc = Integer.parseInt(passCode);

			 /*送信したパスコード取得*/
			 HttpSession session = request.getSession();
			 int cPc = (Integer)session.getAttribute("passcode");

			 String view = "";
			 if(pc == cPc){
				 /*パスワード再設定画面に遷移*/
				 view = "/WEB-INF/view/path.jsp";
				 session.setAttribute("reset", " ");
			 }else{
				 /*元の画面に戻る*/
				 view = "/WEB-INF/view/mailCompleting.jsp";
				 String remove = "<p>もう一度入力してください</p>";
				 request.setAttribute("remove", remove);
			 }
			 RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			 dispatcher.forward(request, response);
		 }
	}
}