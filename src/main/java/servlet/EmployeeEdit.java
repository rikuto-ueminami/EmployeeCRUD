package servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeesDAO;
import model.Employee;

/**
 * Servlet implementation class EmployeeEdit
 */
@WebServlet("/EmployeeEdit")
public class EmployeeEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータで編集したい従業員のempIdを取得
		request.setCharacterEncoding("UTF-8");
		String empId = request.getParameter("empId");
		
		// 更新される従業員を取得してその情報をリクエストスコープに保存する
		// 従業員を探してくれるDAOをインスタンス化してメソッドを呼び出す。
		EmployeesDAO dao = new EmployeesDAO();
		Employee employee = dao.find(empId);
		
		// リクエストスコープにセットする
		request.setAttribute("employee", employee);
		
		// リクエストスコープにセットした内容をwebapp内のWEB-INF/jsp/edit.jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/edit.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		// 送られてきた情報を受けとってそれぞれの変数に入れる
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String empId = request.getParameter("empId");
		
		// 送られてきた値で従業員インスタンスを作成する
		Employee employee = new Employee(id, name, Integer.parseInt(age));
		
		// daoのupdateメソッドを使用するためにDAOインスタンスの生成
		EmployeesDAO dao = new EmployeesDAO();
		// 上で生成したインスタンスでDBの情報を更新
		// daoインスタンスのupdateメソッドを使用してDBを更新する
		dao.update(employee, empId);
		
		// 一覧画面に移動したいのでEmployeeServletにリダイレクトする
		// 同じアプリケーション内なのでhttp://から書かない
		response.sendRedirect("EmployeeServlet");
	}

}
