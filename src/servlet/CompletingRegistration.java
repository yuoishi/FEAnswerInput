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
import dto.User;

/**
 * Servlet implementation class CompletingRegistration
 */
@WebServlet("/CompletingRegistration")
public class CompletingRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompletingRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String view = "/WEB-INF/view";

		String rc = (String)session.getAttribute("rc");
		if(rc != null){
			session.removeAttribute("rc");
			boolean flg = RegistrationLogic.execute((User)(session.getAttribute("user")));
			/*ユーザ登録の成功/失敗で遷移先を決める*/
			if(flg){
				view += "/completingRegistration.jsp";
			}else{
				session.invalidate();
				view += "/failingRegistration.jsp";
			}
		}else{
			session.invalidate();
			view += "/movingTop.jsp";
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
