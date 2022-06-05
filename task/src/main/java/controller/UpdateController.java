package controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.EmployeeBean;
import model.EmployeeModel;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet("/update")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
