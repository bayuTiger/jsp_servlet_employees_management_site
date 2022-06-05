package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.EmployeeBean;

public class EmployeeDao {

	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	private String url = "jdbc:mysql://localhost/jsp_task";
	private String user = "root";
	private String password = "";

	// ./top 社員一覧表示用	
	public ResultSet selectEmployees(int limit, int offset, String sort_name, String sort_type, String like_family_name, String like_last_name, String after_joined_day, String like_d_name, String not_null_left_day) throws SQLException {

		try {

			// JDBCドライバのロード
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			// DB 接続
			con = DriverManager.getConnection(url, user, password);
			
			// 並び替えの準備
			String order_by = "";
			if(!sort_name.equals("")) {
				order_by = "ORDER BY";
			}
			
			// 検索系の設定
			String where = " WHERE ";
			
			// カラム名が必要か
			String family_name_column = "";
			String last_name_column = "";
			String joined_day_column = "";
			String d_name_column = "";
			String left_day_column = "e.left_day";
			
			// 入力された検索値を修正
			String family_name = "";
			String last_name = "";
			String joined_day = "";
			String d_name = "";
			String left_day = " IS NULL";
			
			// left_day_andはwhere句の最後なので、andは不要
			String family_name_and = "";
			String last_name_and = "";
			String joined_day_and = "";
			String left_day_and = "";
			
			// andが必要か left_day_countはe.left_dayが必ず存在するので不要
			int family_name_count = 0;
			int last_name_count = 0;
			int joined_day_count = 0;
			int d_name_count = 0;
			int left_day_count = 0;
			
			if(not_null_left_day != null) {
				// left_dayは修正必須
				where = " ";
				left_day_column = "";
				left_day = "";
				left_day_count++;
			}
			if(like_family_name != null) {
				if(!like_family_name.equals("")) {
					where = " WHERE ";
					family_name_column = "e.family_name";
					family_name = "LIKE" + " \"" + "%" + like_family_name + "%\"";
					family_name_count++;
				}
			}
			if(like_last_name != null) {
				if(!like_last_name.equals("")) {
					where = " WHERE ";
					last_name_column = "e.last_name";
					last_name = "LIKE" + " \"" + "%" + like_last_name + "%\"";
					last_name_count++;
				}
			}
			if(after_joined_day != null) {
				if(!after_joined_day.equals("")) {
					where = " WHERE ";
					joined_day_column = "e.joined_day";
					joined_day = " >= \"" + after_joined_day + "\"";
					joined_day_count++;
				}
			}
			if(like_d_name != null) {
				if(!like_d_name.equals("")) {
					where = " WHERE ";
					d_name_column = "d.name";
					d_name = "= \"" + like_d_name + "\"";
					d_name_count++;
				}
			}
			
			
			// ANDが必要かどうかの条件分岐
			if(left_day_count == 0) {
				if( family_name_count != 0  || last_name_count != 0 || joined_day_count != 0 || d_name_count != 0 ) {
					left_day_and = "AND"; 
				}
			}
			if(family_name_count == 1) {
				if(last_name_count != 0 || joined_day_count != 0 || d_name_count != 0 ) {
					family_name_and = "AND";
				}
			}
			if(last_name_count == 1) {
				if(joined_day_count != 0 || d_name_count != 0 ) {
					last_name_and = "AND";
				}
			}
			if(joined_day_count == 1) {
				if(d_name_count != 0) {
					joined_day_and = "AND";
				}	
			}
			
			
	
			// SQL文を生成
			ps = con.prepareStatement("SELECT"
									+ "  e.id,"
									+ "  e.family_name,"
									+ "  e.last_name,"
									+ "  e.birth,"
									+ "  e.joined_day,"
									+ "  d.id,"
									+ "  d.name,"
									+ "  e.left_day "
									+ " FROM"
									+ "  belongs as b"
									+ " JOIN"
									+ "  employees as e"
									+ " ON"
									+ "  b.employee_id = e.id"
									+ " JOIN"
									+ "  departments as d"
									+ " ON"
									+ "  b.department_id = d.id "
										+ where + " "
										+ left_day_column + " "
										+ left_day + " "
										+ left_day_and+ " "
										+ family_name_column + " "
										+ family_name + " "
										+ family_name_and + " "
										+ last_name_column + " "
										+ last_name + " "
										+ last_name_and + " "
										+ joined_day_column + " "
										+ joined_day + " "
										+ joined_day_and + " "
										+ d_name_column + " "
										+ d_name + " "
									+ order_by + " " + sort_name + " " + sort_type
									+ " LIMIT ? OFFSET ?;");
			
			ps.setInt(1,limit);
			ps.setInt(2,offset - 1);
System.out.println(order_by);

			// SQLを実行
			rs = ps.executeQuery();
	
			}	catch(ClassNotFoundException e) {
				e.printStackTrace();
				}
		return rs;
	}
	
	// ./top ページング機能用
	public ResultSet countRecords() throws SQLException {

		try {

			// JDBCドライバのロード
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			// DB 接続
			con = DriverManager.getConnection(url, user, password);
	
			// SQL文を生成
			ps = con.prepareStatement("SELECT"
									+ "  COUNT(*) "
									+ "FROM"
									+ "  belongs as b "
									+ "JOIN"
									+ "  employees as e "
									+ "ON"
									+ "  b.employee_id = e.id "
									+ "JOIN"
									+ "  departments as d "
									+ "ON"
									+ "  b.department_id = d.id;");	
			// SQLを実行
			rs = ps.executeQuery();
	
			}	catch(ClassNotFoundException e) {
				e.printStackTrace();
				}
		return rs;
	}
	// ./update 一社員編集用
	public ResultSet selectOneEmployee(int id) throws SQLException {

		try {

			// JDBCドライバのロード
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			// DB 接続
			con = DriverManager.getConnection(url, user, password);
	
			// SQL文を生成
			ps = con.prepareStatement("SELECT"
									+ "  e.id,"
									+ "  e.family_name,"
									+ "  e.last_name,"
									+ "  e.birth,"
									+ "  e.joined_day,"
									+ "  d.id,"
									+ "  d.name,"
									+ "  e.left_day "
									+ "FROM"
									+ "  belongs as b "
									+ "JOIN"
									+ "  employees as e "
									+ "ON"
									+ "  b.employee_id = e.id "
									+ "JOIN"
									+ "  departments as d "
									+ "ON"
									+ "  b.department_id = d.id  "
									+ "WHERE e.id = ?;");
			
			ps.setInt(1, id);
	
			// SQLを実行
			rs = ps.executeQuery();
	
			}	catch(ClassNotFoundException e) {
				e.printStackTrace();
				}
		return rs;
	}
	
	// 中間テーブルinsert用 直前にautoincrementしたemployeeのidを取得する
	public ResultSet selectEmployeeId() throws SQLException {

		try {

			// JDBCドライバのロード
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			// DB 接続
			con = DriverManager.getConnection(url, user, password);
	
			// SQL文を生成
			ps = con.prepareStatement("SELECT MAX(id) FROM employees");
	
			// SQLを実行
			rs = ps.executeQuery();
	
			}	catch(ClassNotFoundException e) {
				e.printStackTrace();
				}
		return rs;
	}
	
	public void close() {

		try {

			// データベースとの接続を切断
			if(con != null) {
				con.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(rs != null) {
				rs.close();
			}

		} catch (SQLException se) {

			// データベースからの切断に失敗した場合
			se.printStackTrace();
		}
	}
	
	  /**
	   *
	   * @param EmployeeBean bean
	   * @throws SQLException
	   */
	  public void userInsert(EmployeeBean bean) throws SQLException {
	    try {

	      // JDBCドライバのロード
	      Class.forName("com.mysql.cj.jdbc.Driver");

	      // DB 接続
	      con = DriverManager.getConnection(url, user, password);

	      // SQL文を生成
	      ps = con.prepareStatement(
	          "INSERT INTO "
	          + "employees(family_name, last_name, birth, joined_day, created_at, updated_at) "
	          + "VALUES(?, ?, ?, ?, ?, ?)");

	      ps.setString(1, bean.getFamilyName());
	      ps.setString(2, bean.getLastName());
	      ps.setDate(3, bean.getBirth());
	      ps.setDate(4, bean.getJoinedDay());
	      ps.setString(5, bean.getEmployeeCreatedAt());
	      ps.setString(6, bean.getEmployeeUpdatedAt());

	      // SQLを実行
	      ps.executeUpdate();

	    } catch (ClassNotFoundException ce) {

	      // JDBCドライバが見つからなかったときの処理
	      ce.printStackTrace();
	    }
	  }
	  /**
	   *
	   * @param EmployeeBean bean
	   * @throws SQLException
	   */
	  public void belongInsert(EmployeeBean bean) throws SQLException {
	    try {

	      // JDBCドライバのロード
	      Class.forName("com.mysql.cj.jdbc.Driver");

	      // DB 接続
	      con = DriverManager.getConnection(url, user, password);

	      // SQL文を生成
	      ps = con.prepareStatement(
	          "INSERT INTO "
	          + "belongs(employee_id, department_id, start_date, created_at, updated_at) "
	          + "VALUES(?, ?, ?, ?, ?)");

	      ps.setInt(1, bean.getId());
	      ps.setInt(2, bean.getDepartmentId());
	      ps.setDate(3, bean.getStartDate());
	      ps.setString(4, bean.getBelongCreatedAt());
	      ps.setString(5, bean.getBelongUpdatedAt());

	      // SQLを実行
	      ps.executeUpdate();

	    } catch (ClassNotFoundException ce) {

	      // JDBCドライバが見つからなかったときの処理
	      ce.printStackTrace();
	    }
	  }

	  /**
	   *
	   * @param bean
	   * @throws SQLException
	   */
	  public void update(EmployeeBean bean) throws SQLException {
	    try {

	      // JDBCドライバのロード
	      Class.forName("com.mysql.cj.jdbc.Driver");

	      // DB 接続
	      con = DriverManager.getConnection(url, user, password);

	      // SQL文を生成
	      ps = con.prepareStatement(
	    		"UPDATE"
	          + " belongs as b "
	          + "INNER JOIN"
	          + " employees as e "
	          + "ON"
	          + " b.employee_id = e.id "
	          + "INNER JOIN"
	          + " departments as d "
	          + "ON"
	          + " b.department_id = d.id "
	          + "SET"
	          + "  e.family_name = ?,"
	          + "  e.last_name = ?,"
	          + "  e.birth = ?,"
	          + "  e.joined_day = ?,"
	          + "  e.left_day = ?,"
	          + "  e.created_at = ?,"
	          + "  e.updated_at = ?,"
	          + "  b.employee_id = ?,"
	          + "  b.department_id = ?,"
	          + "  b.start_date = ?,"
	          + "  b.created_at = ?,"
	          + "  b.updated_at = ? "
	          + "WHERE"
	          + " e.id = ?;");
	      
	      ps.setString(1, bean.getFamilyName());
	      ps.setString(2, bean.getLastName());
	      ps.setDate(3, bean.getBirth());
	      ps.setDate(4, bean.getJoinedDay());
	      ps.setDate(5, bean.getLeftDay());
	      ps.setString(6, bean.getEmployeeCreatedAt());
	      ps.setString(7, bean.getEmployeeUpdatedAt());
	      ps.setInt(8, bean.getId());
	      ps.setInt(9, bean.getDepartmentId());
	      ps.setDate(10, bean.getStartDate());
	      ps.setString(11, bean.getBelongCreatedAt());
	      ps.setString(12, bean.getBelongUpdatedAt());
	      ps.setInt(13,bean.getId());

	      // SQLを実行
	      ps.executeUpdate();

	    } catch (ClassNotFoundException ce) {

	      // JDBCドライバが見つからなかったときの処理
	      ce.printStackTrace();
	    }
	  }

}
