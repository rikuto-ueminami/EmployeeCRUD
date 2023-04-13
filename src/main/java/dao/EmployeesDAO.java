package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

// EMPLOYEESテーブルを担当するDAO
public class EmployeesDAO {
	// DBに接続する情報定義（定数で）
	private static final String URL = "jdbc:mysql://116.80.77.161/OJT_C008";
	private static final String USER_NAME = "c008";
	private static final String PASSWORD = "hB6W8LnfJ5fW";
	
	// EMPLOYEESテーブルから全レコードを取得するメソッド
	public List<Employee> findAll() {
		// レコードを格納できる変数を生成
		List<Employee> empList = new ArrayList<>();
		
		// JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		// データベースに接続
		try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
			// 全レコードを取得するSELECT文を記述
			String sql = "SELECT * FROM OJT_C008.EMPLOYEES";
			// SQLを実行するオブジェクトを取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表に格納されたレコードの内容をEmployeeインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				
				// Employeeインスタンスを生成し、初期値に取得したレコードの情報を格納する
				Employee employee = new Employee(id, name, age);
				// Arraylistに上で生成したインスタンスを追加する
				empList.add(employee);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO nullならどうしたいのかまだ決めかねている。（とりあえずでおいてしまってます
			return null;
		}
		return empList;
	}
	
	// 従業員の情報を登録するメソッドcreate()を定義
	public void create(Employee employee) {
		// JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		// DBへ接続
		try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
			// INSERT文の準備（idは自動連番なので指定しなくてもいい)
			String sql = "INSERT INTO OJT_C008.EMPLOYEES(ID, NAME, AGE) VALUES(?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// INSERT文中に「?」に使用する値を設定してSQL文を完成
			// 1つ目の?に入る値を入力
			pStmt.setString(1, employee.getId());
			// 2つ目の?に入る値を入力
			pStmt.setString(2, employee.getName());
			// 3つ目の?に入る値を入力
			pStmt.setInt(3, employee.getAge());
			
			// INSERT文を実行
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
	
	// 従業員１人を探すメソッド
	public Employee find(String empId) {
		// Employee型のインスタンスemployeeを初期化
		Employee employee = null;
		
		// JDBCドライバに接続する
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		// DBへ接続
		try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
			// 引数で指定された情報から探す人を特定するSELECT文を作成
			String sql = "SELECT * FROM OJT_C008.EMPLOYEES WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SELECTのWHEREの？に入る値を設定してSQL文を完成
			// 引数で送られてきた値を設定
			pStmt.setString(1, empId);
			
			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			
			// 取得したテーブルのカラムを取得
			while (rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				
				// 取得したカラムでEmployeeインスタンスを生成
				employee = new Employee(id, name, age);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 戻り値はDBから検索した値を各フィールドにセットされたインスタンス
		return employee;
	}
	
	// DBの情報を更新するメソッド
	public void update(Employee employee, String empId) {
		// JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		// DBに接続する
		try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
			// UPDATE文の準備
			String sql = "UPDATE OJT_C008.EMPLOYEES SET ID = ?, NAME = ?, AGE = ? WHERE ID = ?";
			// 変数sqlをセットして実行する準備
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// UPDATE文の「?」に入る文字を入力設定してSQL文を完成
			pStmt.setString(1, employee.getId());
			pStmt.setString(2, employee.getName());
			pStmt.setInt(3, employee.getAge());
			pStmt.setString(4, empId);
			
			// UPDATE文を実行
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 従業員を削除するメソッド
	public void delete(String empId) {
		// JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		// DBに接続する
		try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
			// DELETE文の準備
			String sql = "DELETE FROM OJT_C008.EMPLOYEES WHERE ID = ?";
			
			// 変数sqlをセットして実行する準備
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// DELETE文の「?」に入る文字を入力設定してSQL文を完成
			pStmt.setString(1, empId);
			
			// SQLを実行する
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
