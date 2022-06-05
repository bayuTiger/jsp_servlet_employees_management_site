package bean;

import java.io.Serializable;
import java.sql.Date;

public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int departmentId;
    private String familyName;
    private String lastName;
    private Date birth;
    private Date joinedDay;
    private String departmentName;
    private Date leftDay;
    private Date startDate;
    private String employeeCreatedAt;
    private String employeeUpdatedAt;
    private String belongCreatedAt;
    private String belongUpdatedAt;
    
    public EmployeeBean(){}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Date getJoinedDay() {
		return joinedDay;
	}
	public void setJoinedDay(Date joinedDay) {
		this.joinedDay = joinedDay;
	}
	public Date getLeftDay() {
		if(leftDay != null) {
			return leftDay;
		}else {
			leftDay = null;
			return leftDay;
		}
	}
	public void setLeftDay(Date leftDay) {
		if(leftDay != null) {
			this.leftDay = leftDay;
		}else {
			this.leftDay = null;
		}
	}


	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getEmployeeCreatedAt() {
		return employeeCreatedAt;
	}

	public void setEmployeeCreatedAt(String employeeCreatedAt) {
		this.employeeCreatedAt = employeeCreatedAt;
	}

	public String getEmployeeUpdatedAt() {
		return employeeUpdatedAt;
	}

	public void setEmployeeUpdatedAt(String employeeUpdatedAt) {
		this.employeeUpdatedAt = employeeUpdatedAt;
	}


	public String getBelongCreatedAt() {
		return belongCreatedAt;
	}

	public void setBelongCreatedAt(String belongCreatedAt) {
		this.belongCreatedAt = belongCreatedAt;
	}


	public String getBelongUpdatedAt() {
		return belongUpdatedAt;
	}

	public void setBelongUpdatedAt(String belongUpdatedAt) {
		this.belongUpdatedAt = belongUpdatedAt;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



    
}
