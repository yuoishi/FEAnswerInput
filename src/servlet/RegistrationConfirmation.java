package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PasswordDAO;
import dto.User;

/**
 * Servlet implementation class RegistrationConfirmation
 */
@WebServlet("/RegistrationConfirmation")
public class RegistrationConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationConfirmation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/view/reRegistration.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String userID = request.getParameter("userid");

		String password = request.getParameter("password");
		password = PasswordDAO.hashCode(password);

		String email = request.getParameter("email");
		String userName = request.getParameter("username");
		String name = request.getParameter("name");
		String phoneticName = request.getParameter("phoneticname");

		String year = request.getParameter("年");
		String month = request.getParameter("月");
		String date = request.getParameter("日");
		int birthday = Integer.parseInt(year + month + date);

		String sq = request.getParameter("sq");

		User user = new User(userID, password, email, userName, name, phoneticName, birthday, sq);

		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		session.setAttribute("rc", "rc");

		/*登録内容確認画面へ遷移*/
		String view = "/WEB-INF/view/registrationConfirmation.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
