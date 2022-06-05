package controller;


import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.EmployeeBean;
import model.EmployeeModel;

/**
 * Servlet implementation class ValidateController
 */
@WebServlet("/valid")
public class ValidateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateController() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        MandatoryCheck(request,response);
    }
    //##### ArrayList to store error messages
    //##### エラーメッセージを格納するためのArrayList
    public ArrayList<String> errs = new ArrayList<String>();
    
    //[Common] Transform the error ArrayList to a <ul> list
    //##### getErrorList()メソッド：【必須入力チェック】
    //##### エラーメッセージを格納したArrayList「errs」のメッセージをHTML形式に整形（タグを付与）したり、文字色 を赤くする処理をしています。
    //##### StringBuffer型の変数に格納していき、その結果を戻り値にしています。
    public String getErrorList() {
                    StringBuffer buf = new StringBuffer();
                    buf.append("<ul style='color:Red'>");
                    if(errs.size()>0) {
                                    for(int i=0; i<errs.size(); i++) {
                                                    buf.append("<li>"+errs.get(i)+"</li>");
                                    }
                    }
                    buf.append("</ul>");
                    System.out.println("LLLL="+buf.toString());
                    return buf.toString();
    }

    //[Common] Mandatory Check
    //#####　requiredCheck()メソッド
    //#####　必須入力チェックを行うメソッドです。
    public void requiredCheck(String value, String name) {
                    //もし「引数（value）がnull」OR「引数（value）の空白削除値が空」なら
                    if(value==null || value.trim().isEmpty()) {
                                    //入力値がブランクであると見なし、入力を求めるメッセージを出力
                                    errs.add(name+" は必須入力です");
                    }
    }
    //##### 必須入力チェックの全体処理
    //##### 入力値の取得→必須チェック→エラーメッセージのセット→画面遷移までの一連の処理を行うメソッド
    public void MandatoryCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    errs = new ArrayList<String>();
                    String v_family_name = request.getParameter("family_name");
                    String v_last_name = request.getParameter("last_name");
                    String v_birth = request.getParameter("birth");
                    String v_joined_day = request.getParameter("joined_day");

                    //#####　Forward back to the "Engeki_GroupMaintenance" with Error message
                    //#####　もし必須入力のいずれかが「null」OR「ブランク」の場合
                    if(v_family_name==null || v_family_name.trim().isEmpty() ) {
                                    //##### 必須入力項目のチェックを実行
                                    //##### （requiredCheckを実行して空なら「[項目名] is a mandatory～」のエラーメッセージがArrayListに格納される）
                                    requiredCheck(request.getParameter("family_name"),"[姓]");
                                    //##### エラーメッセージをリクエストの属性としてセット
                                    request.setAttribute("error_msg", getErrorList());

                    }
                    if(v_last_name==null || v_last_name.trim().isEmpty() ) {
                        //##### 必須入力項目のチェックを実行
                        //##### （requiredCheckを実行して空なら「[項目名] is a mandatory～」のエラーメッセージがArrayListに格納される）
                        requiredCheck(request.getParameter("last_name"),"[名]");
                        //##### エラーメッセージをリクエストの属性としてセット
                        request.setAttribute("error_msg", getErrorList());

                    }
                    if(v_birth==null || v_birth.trim().isEmpty() ) {
                        //##### 必須入力項目のチェックを実行
                        //##### （requiredCheckを実行して空なら「[項目名] is a mandatory～」のエラーメッセージがArrayListに格納される）
                        requiredCheck(request.getParameter("birth"),"[生年月日]");
                        //##### エラーメッセージをリクエストの属性としてセット
                        request.setAttribute("error_msg", getErrorList());

                    }
                    if(v_joined_day==null || v_joined_day.trim().isEmpty() ) {
                        //##### 必須入力項目のチェックを実行
                        //##### （requiredCheckを実行して空なら「[項目名] is a mandatory～」のエラーメッセージがArrayListに格納される）
                        requiredCheck(request.getParameter("joined_day"),"[入社日]");
                        //##### エラーメッセージをリクエストの属性としてセット
                        request.setAttribute("error_msg", getErrorList());
                    }
                    
                    if(errs.size()>0) {
                        // フォワード準備
                    	ServletContext context = this.getServletContext();
                    	
                    	if (request.getParameter("addEmployee") != null) {
	                    	// フォワード先の JSP を context にセット
	                    	RequestDispatcher dispatcher = context
                    			.getRequestDispatcher("/WEB-INF/view/create.jsp");
	                    	// フォワード処理
	                    	dispatcher.forward(request, response);
                    	}
                    	
                    	if (request.getParameter("updateEmployee") != null) {
                    		// 処理クラスをインスタンス化
                        	EmployeeModel model = new EmployeeModel();
                        	
                        	int id = Integer.parseInt(request.getParameter("id"));

                        	EmployeeBean bean = model.getOneEmployee(id);

                        	// フォワードの準備
                        	request.setAttribute("bean", bean);
                        	RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/update.jsp");
                        	rd.forward(request, response);	
                    	}


                    }
                    
            		request.setCharacterEncoding("UTF-8");

            		// セッションの開始
            		HttpSession session = request.getSession();

            		// フォームに入力されたデータを取得
            		String s_id = request.getParameter("id");
            	    String family_name = request.getParameter("family_name");
            	    String last_name = request.getParameter("last_name");
            	    String d_value = request.getParameter("d_value");
            	    String s_birth = request.getParameter("birth");
            	    String s_joined_day = request.getParameter("joined_day");
            	    String s_left_day = request.getParameter("left_day");
            	    

            	   
            	    int id = 0;
            	    Date birth;
                    Date joined_day;
                    Date left_day = null;
                    LocalDateTime nowDate = LocalDateTime.now();
                    DateTimeFormatter dtf1 =
                            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    String created_at = dtf1.format(nowDate);
                    String updated_at = dtf1.format(nowDate);
                    String b_created_at = dtf1.format(nowDate);
                    String b_updated_at = dtf1.format(nowDate);
                    
                    if(s_id != null) {
                    	id =  Integer.parseInt(s_id);
                    }


            	    birth = Date.valueOf(s_birth);
                    joined_day = Date.valueOf(s_joined_day);
                    if(s_left_day != null && !s_left_day.equals("")){
                    left_day = Date.valueOf(s_left_day);
                    }
                    
                    session.setAttribute("id", id);
                    session.setAttribute("family_name", family_name);
                    session.setAttribute("last_name", last_name);
                    session.setAttribute("birth", birth);
                    session.setAttribute("d_value", d_value);
                    session.setAttribute("joined_day", joined_day);
                    session.setAttribute("left_day", left_day);
                    session.setAttribute("created_at", created_at);
                    session.setAttribute("updated_at", updated_at);
                    session.setAttribute("d_value", d_value);
                    session.setAttribute("b_create_at", b_created_at);
                    session.setAttribute("b_updated_at", b_updated_at);
                    
                    
                    // フォワード準備
                	ServletContext context = this.getServletContext();

                	if (request.getParameter("addEmployee") != null) {
                    	// フォワード先の JSP を context にセット
                	    	RequestDispatcher dispatcher = context
                    			.getRequestDispatcher("/WEB-INF/view/create_confirm.jsp");
                        	// フォワード処理
                        	dispatcher.forward(request, response);
                	    }
            	    if (request.getParameter("updateEmployee") != null) {
                	// フォワード先の JSP を context にセット
            	    	RequestDispatcher dispatcher = context
                			.getRequestDispatcher("/WEB-INF/view/update_confirm.jsp");
                    	// フォワード処理
                    	dispatcher.forward(request, response);
            	    }

            	}

                    
                    
         }
