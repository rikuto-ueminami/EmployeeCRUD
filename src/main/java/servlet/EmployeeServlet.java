package servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeesDAO;
import model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
// 従業員に関するリクエストを処理をするモデル
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 従業員リストを取得して、リクエストスコープに保存
		EmployeesDAO dao = new EmployeesDAO();
		List<Employee> empList = dao.findAll();
		// 属性名empListの値empListインスタンスをリクエストスコープにセット
		request.setAttribute("empList", empList);
		
		// webapp内のWEB-INF/jsp/index.jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		// 送られてきた情報を受け取ってそれそれの変数に入れる
		String id = "EMP0" + request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		// 送られてきた値で従業員インスタンスを作成する
		Employee employee = new Employee(id, name, Integer.parseInt(age));
		
		// daoのcreateメソッドを使用するためにDAOインスタンスの生成
		EmployeesDAO dao = new EmployeesDAO();
		// 上で生成したインスタンスでDBに情報を登録
		// daoインスタンスのcreateメソッドを使用してDBに登録する
		dao.create(employee);
		
		// 最後に追加したものが反映された一覧を表示したい
		List<Employee> empList = dao.findAll();
		// JSPファイルで使えるようにリクエストスコープに保存
		request.setAttribute("empList", empList);
		
		// webapp内のWEB-INF/jsp/index.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);
	}

}
