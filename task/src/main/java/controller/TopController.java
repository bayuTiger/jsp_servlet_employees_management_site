package controller;



import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.EmployeeBean;
import bean.PagingBean;
import model.EmployeeModel;

/**
 * Servlet implementation class servletSample
 */
@WebServlet("/top")
public class TopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
    	
		HttpSession session = request.getSession();
		
		ArrayList<EmployeeBean> beanList = new ArrayList<EmployeeBean>();
		PagingBean paging = new PagingBean();
		
		// sort関係の変数
		String sort_name = "";
		String sort_type = "";
		int click_count = 0;
		int mod3_click_count = 0;
		String sort_icon = "";
		
		// 検索関係の変数
		String like_family_name = null;
		String like_last_name = null;
		String after_joined_day = null;
		String like_d_name = null;
		String not_null_left_day = null;
		
		
		// 検索値を取得
		if(request.getParameter("like_family_name") != null) {
			like_family_name = request.getParameter("like_family_name").toString();
		}
		if(request.getParameter("like_last_name") != null) {
			like_last_name = request.getParameter("like_last_name").toString();
		}
		if(request.getParameter("after_joined_day") != null) {
			after_joined_day = request.getParameter("after_joined_day").toString();
		}
		if(request.getParameter("like_d_name") != null) {
			like_d_name = request.getParameter("like_d_name").toString();
		}
		if(request.getParameter("not_null_left_day") != null) {
			not_null_left_day = request.getParameter("not_null_left_day");
		}
		
		
		    	
		// ページングの初期値
    	paging.setLimit(5);
    	paging.setOffset(1);
    	    	
    		// 「前へ」ボタンが押されたときの処理
    		if(request.getParameter("prev") != null) {
	    		paging.setOffset((Integer)request.getSession().getAttribute("offset") - 5);
    		}
    		// 「次へ」ボタンが押されたときの処理
    		if(request.getParameter("next") != null) {
	    		paging.setOffset((Integer)request.getSession().getAttribute("offset") + 5);
    		}

    		

    	// 処理クラスをインスタンス化
    	EmployeeModel model = new EmployeeModel();

    	// ソート機能用の条件分岐
    	// 状態が三種類あるので、mod3で条件分岐する
    	if(request.getSession().getAttribute("click_count") != null) {
    		click_count = Integer.parseInt(request.getSession().getAttribute("click_count").toString());
    		mod3_click_count = click_count % 3;
    	}
    	
    	//アイコンの表示切替
    	switch(mod3_click_count) {
    		case 0:
    			sort_icon = "";
    			break;
    		case 1:
    			sort_icon = "△";
    			break;
    		case 2:
    			sort_icon = "▽";
    			break;
    	}
    	
    	// sort_nameの設定
    	if(request.getParameter("sort_family_name") != null) {
    		sort_name = "e.family_name";
    	}
    	if(request.getParameter("sort_last_name") != null) {
    		sort_name = "e.last_name";
    	}
    	if(request.getParameter("sort_birth") != null) {
    		sort_name = "e.birth";
    	}
    	if(request.getParameter("sort_joined_day") != null) {
    		sort_name = "e.joined_day";
    	}
    	if(request.getParameter("sort_d_name") != null) {
    		sort_name = "d.name";
    	}
    	if(request.getParameter("sort_left_day") != null) {
    		sort_name = "e.left_day";
    	}
    	
    	// sort項目がクリックされた回数で処理を分岐
    		if(mod3_click_count == 1) {
    			if(!sort_name.equals("")) {
    				sort_type = "ASC";
    			}
    			beanList = model.getEmployeesList(paging.getLimit(), paging.getOffset(), sort_name, sort_type, like_family_name, like_last_name, after_joined_day, like_d_name, not_null_left_day);
    		}
    		else if(mod3_click_count == 2) {
    			if(!sort_name.equals("")) {
    				sort_type = "DESC";
    			}
    			beanList = model.getEmployeesList(paging.getLimit(), paging.getOffset(), sort_name, sort_type, like_family_name, like_last_name, after_joined_day, like_d_name, not_null_left_day);
    		}
    		else if(mod3_click_count == 0 || click_count == 0) {
    			sort_type = "";
    			sort_name = "";
    			beanList = model.getEmployeesList(paging.getLimit(), paging.getOffset(), sort_name, sort_type, like_family_name, like_last_name, after_joined_day, like_d_name, not_null_left_day);
    		}		
    	
    	

    	
    	// フォワードの準備
    	request.setAttribute("beanList", beanList);
    	int count = model.gerRecordCount();
    	request.setAttribute("count", count);
    	session.setAttribute("click_count", click_count);
    	session.setAttribute("sort_icon", sort_icon);
    	session.setAttribute("sort_name", sort_name);
    	session.setAttribute("limit", paging.getLimit());
    	session.setAttribute("offset", paging.getOffset());

    	// 社員一覧画面に移動
    	RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/top.jsp");
    	rd.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request,
    	      HttpServletResponse response) throws ServletException, IOException {


    	    request.setCharacterEncoding("UTF-8");

    	    // 処理クラスをインスタンス化
    	    EmployeeModel model = new EmployeeModel();

    	    // フォームから送信されるデータは文字列扱いなので、
    	    // 一旦 String 型で受け取ります
    	    String s_id = request.getSession().getAttribute("id").toString();
    	    String d_value = request.getSession().getAttribute("d_value").toString();
    	    String family_name = request.getSession().getAttribute("family_name").toString();
    	    String last_name = request.getSession().getAttribute("last_name").toString();
    	    String s_birth = request.getSession().getAttribute("birth").toString();
    	    String s_joined_day = request.getSession().getAttribute("joined_day").toString();
    	    String s_left_day = null;
    	    if(request.getSession().getAttribute("left_day") != null) {
    	    s_left_day = request.getSession().getAttribute("left_day").toString();
    	    }

    	    // String型から変換が必要なもの
    	    int id = 0;
    	    int d_id = 0;
    	    Date birth;
            Date joined_day;
            Date start_date;
            Date left_day = null;

            // DateTime系データの処理
            LocalDateTime nowDate = LocalDateTime.now();
            DateTimeFormatter dtf1 =
                    DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String created_at = dtf1.format(nowDate);
            String updated_at = dtf1.format(nowDate);
            String b_created_at = dtf1.format(nowDate);
            String b_updated_at = dtf1.format(nowDate);
            
            // id系の処理
            if(s_id != null) {
            	id = Integer.parseInt(s_id);
            }
            
            switch(d_value) {
            	case "a":
            		d_id = 1;
            		break;
            	case "b":
            		d_id = 2;
            		break;
            	case "c":
            		d_id = 3;
            		break;
            	case "d":
            		d_id = 4;
            		break;
            	case "e":
            		d_id = 5;
            		break;
            	case "f":
            		d_id = 6;
            		break;
            }


            // Date系の処理
    	    birth = Date.valueOf(s_birth);
            joined_day = Date.valueOf(s_joined_day);
            start_date = Date.valueOf(s_joined_day);
            if(s_left_day !=null){
            left_day = Date.valueOf(s_left_day);
            }
  
            

    	    // 押されたボタンを判定して分岐
    	    if (request.getParameter("addEmployee") != null) {
    	      // 登録ボタンを押された場合
    	      model.insertEmployeeData(family_name, last_name, birth, joined_day, created_at, updated_at);
    	      
    	      // belongsテーブルにinsertする準備
    	      int latest_id = model.getLatestId();
    	      model.insertBelongsData(latest_id, d_id, start_date, b_created_at, b_updated_at);
    	    
    	    } else if(request.getParameter("updateEmployee") != null){
    	      // 更新ボタンを押された場合

    	      model.updateEmployee(id, family_name, last_name, birth, joined_day, left_day, created_at, updated_at, d_id, start_date, b_created_at, b_updated_at);
    	    }


    	    // 従業員一覧画面に移動
    	    String url = "./top";
    	    response.sendRedirect(url);
    	  }
}