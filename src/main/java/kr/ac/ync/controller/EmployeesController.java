package kr.ac.ync.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.ync.dao.EmployeesDAOImpl;
import kr.ac.ync.domain.Criteria;
import kr.ac.ync.domain.EmployeeVO;
import kr.ac.ync.domain.PageDTO;
import kr.ac.ync.service.EmployeesService;
import kr.ac.ync.service.EmployeesServiceImpl;

/**
 * Servlet implementation class EmployeesController
 */
@WebServlet("/employees/*")
public class EmployeesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeesService employeesService;

	// 생성될 때 호출되는 메소드
	@Override
	public void init() throws ServletException {
		employeesService = new EmployeesServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = null;
		String lastPath = request.getPathInfo();
		System.out.println(lastPath);
		if (lastPath == null || lastPath.equals("/list")) {
			// pageNum = 1, amount = 30
			String _pageNum = request.getParameter("pageNum");
			String _amount = request.getParameter("amount");
			String _type = request.getParameter("type");
			String _keyword = request.getParameter("keyword");

			int pageNum = Integer.valueOf((_pageNum == null) ? "1" : _pageNum);
			int amount = Integer.valueOf((_amount == null) ? "30" : _amount);

			Criteria cri = new Criteria(pageNum, amount);
			if (_type != null)
				cri.setType(_type);
			if (_keyword != null)
				cri.setKeyword(_keyword);

			request.setAttribute("list", employeesService.getList(cri));
			request.setAttribute("pageMaker", new PageDTO(cri, employeesService.getTotal(cri)));
			nextPage = "/WEB-INF/views/employees/list.jsp";

		} else if (lastPath.equals("/register") && request.getMethod().equals("GET")) {
		  nextPage = "/WEB-INF/views/employees/register.jsp";

		}

		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);

	}

}
