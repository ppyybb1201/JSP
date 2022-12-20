package kr.ac.ync.domain;

import java.util.Date;

public class EmployeeVO {
	private Long emp_no;
	private Date birth_date;
	private String first_name;
	private String last_name;
	private String gender;
	private Date hire_date;

	public Long getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(Long emp_no) {
		this.emp_no = emp_no;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	@Override
	public String toString() {
		return String.format("[%d, %s, %s]", emp_no, first_name, last_name);

	}
}
