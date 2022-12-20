package kr.ac.ync.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.ac.ync.service.DepartmentService;
import kr.ac.ync.service.DepartmentServiceImpl;

@WebServlet("/department/*")
public class DepartmentController extends HttpServlet {

	private DepartmentService departmentService;

	@Override
	public void init() throws ServletException {
		departmentService = new DepartmentServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String respMessage = "";
		String lastPath = request.getPathInfo();
		System.out.println(lastPath);
		try {
			if (lastPath == null || lastPath.equals("")) {
				respMessage = new Gson().toJson(departmentService.getList());
				response.setStatus(HttpServletResponse.SC_OK);
			} else if (lastPath.equals("/titles")) {
				String dept_no = request.getParameter("dept_no");
				respMessage = new Gson().toJson(departmentService.getDeptTitleList(dept_no));
				response.setStatus(HttpServletResponse.SC_OK);
			}
		} catch (Exception e) {
			respMessage = e.getMessage();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		response.setContentType("application/json");
		out.print(respMessage);

	}
}
