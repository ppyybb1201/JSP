package kr.ac.ync.service;

import java.util.List;

import kr.ac.ync.dao.DepartmentDAO;
import kr.ac.ync.dao.DepartmentDAOImpl;
import kr.ac.ync.domain.DepartmentVO;
import kr.ac.ync.domain.DeptTitleVO;

public class DepartmentServiceImpl implements DepartmentService {
	private DepartmentDAO departmentDAO;

	public DepartmentServiceImpl() {
		departmentDAO = new DepartmentDAOImpl();
	}

	@Override
	public List<DepartmentVO> getList() {
		return departmentDAO.getList();
	}

	@Override
	public List<DeptTitleVO> getDeptTitleList(String dept_no) {
		return departmentDAO.getDeptTitleList(dept_no);
	}
}
