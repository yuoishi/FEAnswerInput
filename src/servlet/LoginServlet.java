package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.LoginLogic;
import dao.PasswordDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/view/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*ログイン画面から遷移*/
		String userID = request.getParameter("userid");
		String password = request.getParameter("password");
		password = PasswordDAO.hashCode(password);
		boolean flg = LoginLogic.execute(userID, password);

		/*ログイン成否判定*/
		if(flg){
			HttpSession session = request.getSession();
			session.setAttribute("userid", userID);

			/*ログイン状態のセッション管理用*/
			session.setAttribute("login", "login");

			String view = "/WEB-INF/view/loginCompleting.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}else{
			/*ログインに失敗した旨を伝えるため*/
			request.setAttribute("flg", "0");

			String view = "/WEB-INF/view/login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}
}