package com.hibernateinfo.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
@Entity
@Table(name="employee_table")
//Update the given value not all fields.  
//https://stackoverflow.com/questions/41633250/how-dynamic-update-true-works-internally-in-hibernate
//https://www.mkyong.com/hibernate/hibernate-dynamic-update-attribute-example/
@DynamicUpdate   
public class Employee 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="employee_id")
	private Integer employeeId;
	
	@Column(name="employee_name",length=100, nullable=false )
	private String employeeName;
	
	@Column(name="email", unique=true)
	private String email;
	
	@Column(name="date_of_join")
	private Date doj;
	
	//Cascade attribute transfers operations done on one object onto its related 
	//child objects. if we write cascade = �all� then all operations like insert, 
	//delete, update at parent object will also be effected to child object 
	//If you don't set the cascadeType.ALL for the parents,  
	//You have to save employee(parent) and address(child) separately
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="employee_address_table", 
			joinColumns= {@JoinColumn(name="employee_id", referencedColumnName="employee_id")},
			inverseJoinColumns= {@JoinColumn(name="address_id", referencedColumnName="address_id")}
	)
	private List<Address> addressList = new ArrayList<>();
	
	@Column(name="salary")
	private Double salary;
	
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public List<Address> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email + ", doj="
				+ doj + ", addressList=" + addressList + ", salary=" + salary + "]";
	}		
}

