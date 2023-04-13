package servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeesDAO;

/**
 * Servlet implementation class EmployeeDelete
 */
@WebServlet("/EmployeeDelete")
public class EmployeeDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストで送られてきた削除される従業員のIDのリクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String empId = request.getParameter("empId");
		
		// 従業員を削除してくれるDAOをインスタンス化してメソッドを呼び出す。
		EmployeesDAO dao = new EmployeesDAO();
		// empIdに紐づいた従業員をDBから削除する
		dao.delete(empId);
		
		// 一覧を表示するためにEmployeeServletにリダイレクトする
		response.sendRedirect("EmployeeServlet");
		
	}

}
