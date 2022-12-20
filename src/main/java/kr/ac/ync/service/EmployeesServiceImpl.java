package kr.ac.ync.service;

import java.util.List;

import kr.ac.ync.dao.EmployeesDAO;
import kr.ac.ync.dao.EmployeesDAOImpl;
import kr.ac.ync.domain.Criteria;
import kr.ac.ync.domain.EmployeeVO;

public class EmployeesServiceImpl implements EmployeesService {

	private EmployeesDAO employeesDAO;

	public EmployeesServiceImpl() {
		employeesDAO = new EmployeesDAOImpl();
	}

	@Override
	public List<EmployeeVO> getList(Criteria cri) {
//		return employeesDAO.getList(cri);
		return employeesDAO.getListWithPaging(cri);
	}
	
	@Override
	public int getTotal(Criteria cri) {
		return employeesDAO.getTotalCount(cri);
	}
}
