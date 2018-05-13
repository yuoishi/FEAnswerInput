package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.RegistrationLogic;
import bo.UserLogic;
import dao.PasswordDAO;
import dto.User;

/**
 * Servlet implementation class PwResetServlet
 */
@WebServlet("/PwReset")
public class PwResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwResetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/view/pwReset.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		/*再設定パスワード*/
		String password = request.getParameter("password");
		password = PasswordDAO.hashCode(password);

		/*確認用パスワード*/
		String pw = request.getParameter("pw");
		pw = PasswordDAO.hashCode(pw);

		String view = "/WEB-INF/view";

		if(password.equals(pw)){
			String reset = (String)session.getAttribute("reset");
			session.removeAttribute("reset");

			if(reset != null){

				/*パスワード再設定しようとしているユーザの再確認*/
				User user = (User)session.getAttribute("user");
				boolean preFlg = UserLogic.execute(user);

				if(preFlg){

					/*パスワード再設定実行*/
					boolean flg = RegistrationLogic.execute(user.getUserID(), pw);

					if(flg){
						view += "/resetCompleting.jsp";
						session.invalidate();
					}else{
						view += "/resetFailing.jsp";
					}

				}else{
					view += "/resetFailing.jsp";
				}

			}else{
				view += "/resetFailing.jsp";
			}

		}else{
			/*元も画面に戻る*/
			view += "/pwReset.jsp";
			request.setAttribute("flg", "0");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}