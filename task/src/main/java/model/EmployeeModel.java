package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.EmployeeBean;
import dao.EmployeeDao;
/**
 *  処理を担当するクラス
 *
 */
public class EmployeeModel {
	
	public int gerRecordCount() {
		
		ResultSet rs = null;
		
		int count = 0;
		
		EmployeeDao dao = new EmployeeDao();
		
		try {
			
			rs = dao.countRecords();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			} catch (SQLException e) {
			// 例外処理
			e.printStackTrace();
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
	  return count;
	}

	/**
	 * 従業員一覧を取得して返却する
	 *
	 * @return beanList
	 */
	public ArrayList<EmployeeBean> getEmployeesList(int limit, int offset, String sort_name, String sort_type, String like_family_name, String like_last_name, String after_joined_day, String like_d_name, String not_null_left_day) {

		// ResultSet を初期化
		ResultSet rs = null;

		// bean を入れるためのリスト
		ArrayList<EmployeeBean> beanList = new ArrayList<EmployeeBean>();

		// データベース接続をするために DAO をインスタンス化
		EmployeeDao dao = new EmployeeDao();

		try {
			// データを取得
			rs = dao.selectEmployees(limit, offset, sort_name, sort_type, like_family_name, like_last_name, after_joined_day, like_d_name, not_null_left_day);

			// 取得したデータを beanList に入れるループ
			while (rs.next()) {
				EmployeeBean bean = new EmployeeBean();

				// 従業員情報 を bean にセット
				bean.setId(rs.getInt("id"));
				bean.setFamilyName(rs.getString("family_name"));
				bean.setLastName(rs.getString("last_name"));
				bean.setBirth(rs.getDate("birth"));
				bean.setDepartmentName(rs.getString("d.name"));
				bean.setJoinedDay(rs.getDate("joined_day"));
				bean.setLeftDay(rs.getDate("left_day"));

				// データをセットした bean を beanList に追加
				beanList.add(bean);
			}

		} catch (SQLException e) {
			// 例外処理
			e.printStackTrace();
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}


		return beanList;
	}
	
	public EmployeeBean getOneEmployee(int id) {

		// ResultSet を初期化
		ResultSet rs = null;

		// bean を入れるためのリスト
		EmployeeBean bean = new EmployeeBean();

		// データベース接続をするために DAO をインスタンス化
		EmployeeDao dao = new EmployeeDao();

		try {
			// データを取得
			rs = dao.selectOneEmployee(id);
			if (rs.next()) {

				// 従業員情報 を bean にセット
				bean.setId(id);
				bean.setFamilyName(rs.getString("family_name"));
				bean.setLastName(rs.getString("last_name"));
				bean.setBirth(rs.getDate("birth"));
				bean.setJoinedDay(rs.getDate("joined_day"));
				bean.setLeftDay(rs.getDate("left_day"));
				bean.setDepartmentName(rs.getString("d.name"));
				bean.setStartDate(rs.getDate("joined_day"));				
			}

		} catch (SQLException e) {
			// 例外処理
			e.printStackTrace();
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}

		return bean;
	}
	public int getLatestId() {

		// ResultSet を初期化
		ResultSet rs = null;

		// bean を入れるためのリスト
		int latest_id = 0;

		// データベース接続をするために DAO をインスタンス化
		EmployeeDao dao = new EmployeeDao();

		try {
			// データを取得
			rs = dao.selectEmployeeId();
			if (rs.next()) {
				latest_id = rs.getInt(1);			
			}

		} catch (SQLException e) {
			// 例外処理
			e.printStackTrace();
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}

		return latest_id;
	}
	 /**
	   * items テーブルに従業員のデータを追加する
	   *
	   * @param name
	   * @param price
	   * @param quantity
	   */
	  public void insertEmployeeData(String family_name, String last_name, Date birth, Date joined_day, String created_at, String updated_at) {
	    // データベース接続をするために DAO をインスタンス化
	    EmployeeDao dao = new EmployeeDao();

	    // bean を生成
	    EmployeeBean bean = new EmployeeBean();

	    // employeesテーブル用
		bean.setFamilyName(family_name);
		bean.setLastName(last_name);
		bean.setBirth(birth);
		bean.setJoinedDay(joined_day);
		bean.setEmployeeCreatedAt(created_at);
		bean.setEmployeeUpdatedAt(updated_at);
		
	    try {
	      // bean のデータをテーブルに追加
	      dao.userInsert(bean);
	    } catch (SQLException e) {
	      // 例外処理
	      e.printStackTrace();
	    } finally {
	      // データベースから切断
	      dao.close();
	    }
	  }
	  
	  public void insertBelongsData(int latest_id,int department_id, Date start_date, String b_created_at, String b_updated_at ) {
		    // データベース接続をするために DAO をインスタンス化
		    EmployeeDao dao = new EmployeeDao();

		    // bean を生成
		    EmployeeBean bean = new EmployeeBean();

			// belongsテーブル用
			bean.setId(latest_id);
			bean.setDepartmentId(department_id);
			bean.setStartDate(start_date);
			bean.setBelongCreatedAt(b_created_at);
			bean.setBelongUpdatedAt(b_updated_at);
			
		    try {
			      // bean のデータをテーブルに追加
			      dao.belongInsert(bean);
			    } catch (SQLException e) {
			      // 例外処理
			      e.printStackTrace();
			    } finally {
			      // データベースから切断
			      dao.close();
			    }
	  }

		  


	  public void updateEmployee(int id, String family_name, String last_name, Date birth, Date joined_day, Date left_day, String created_at, String updated_at, int d_id, Date start_date, String b_created_at, String b_updated_at) {
		// データベース接続をするために DAO をインスタンス化
	    EmployeeDao dao = new EmployeeDao();

		// bean を生成
	    EmployeeBean bean = new EmployeeBean();

	    bean.setId(id);
		bean.setFamilyName(family_name);
		bean.setLastName(last_name);
		bean.setBirth(birth);
		bean.setDepartmentId(d_id);
		bean.setJoinedDay(joined_day);
		bean.setLeftDay(left_day);
		bean.setEmployeeCreatedAt(created_at);
		bean.setEmployeeUpdatedAt(updated_at);
		bean.setDepartmentId(d_id);
		bean.setStartDate(start_date);
		bean.setBelongCreatedAt(b_created_at);
		bean.setBelongUpdatedAt(b_updated_at);

	    try {
	      // bean のデータをもとに情報を変更
	      dao.update(bean);
	    } catch (SQLException e) {
	      // 例外処理
	      e.printStackTrace();
	    } finally {
	      // データベースから切断
	      dao.close();
	    }

	  }
}