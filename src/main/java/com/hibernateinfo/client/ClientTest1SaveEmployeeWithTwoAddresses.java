package com.hibernateinfo.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hibernateinfo.entities.Address;
import com.hibernateinfo.entities.Employee;
import com.hibernateinfo.service.EmployeeService;
import com.hibernateinfo.service.impl.EmployeeServiceImpl;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
public class ClientTest1SaveEmployeeWithTwoAddresses {

	public static void main(String[] args) 
	{
		EmployeeService employeeService = new EmployeeServiceImpl();
		createEmployeeWithTwoAddresses(employeeService);		
		
	}	
	private static void createEmployeeWithTwoAddresses(EmployeeService employeeService) {
		employeeService.createEmployee(getEmployee());
	}
	private static Employee getEmployee(){
		Employee employee= new Employee();
		employee.setEmployeeName("Pasha Sadi");
		employee.setEmail("pasha.sn@gmail.com");
		employee.setSalary(65001.00);
		employee.setDoj(new Date());
		
		Address address1 = new Address();
		address1.setStreet("Guy St");
		address1.setCity("Montreal");
		address1.setState("Quebec");
		address1.setPostalcode(19317l);
		
		Address address2 = new Address();
		address2.setStreet("Peel St");
		address2.setCity("Montreal");
		address2.setState("Quebec");
		address2.setPostalcode(19318l);
		
		List<Address> addressList = new ArrayList<>();
		addressList.add(address1);
		addressList.add(address2);
		
		employee.setAddressList(addressList);		
		
		return employee;
	}	
}
/*
<property name="hibernate.hbm2ddl.auto">create</property>
Hibernate: 
    
    drop table address_table cascade constraints
Hibernate: 
    
    drop table employee_address_table cascade constraints
Hibernate: 
    
    drop table employee_table cascade constraints
Hibernate: 
    
    drop sequence hibernate_sequence
Hibernate: create sequence hibernate_sequence start with 1 increment by  1
Hibernate: 
    
    create table address_table (
       address_id number(10,0) not null,
        city_name varchar2(50 char),
        postal_code number(19,0),
        state_name varchar2(255 char),
        street_name varchar2(50 char),
        primary key (address_id)
    )
Hibernate: 
    
    create table employee_address_table (
       employee_id number(10,0) not null,
        address_id number(10,0) not null
    )
Hibernate: 
    
    create table employee_table (
       employee_id number(10,0) not null,
        date_of_join timestamp,
        email varchar2(255 char),
        employee_name varchar2(100 char) not null,
        salary double precision,
        primary key (employee_id)
    )
Hibernate: 
    
    alter table employee_address_table 
       add constraint UK_82ypxil54gfsgm9leblfceekm unique (address_id)
Hibernate: 
    
    alter table employee_table 
       add constraint UK_2casspobvavvi9s23312f9mhm unique (email)
Hibernate: 
    
    alter table employee_address_table 
       add constraint FKhfepimyny20rkf7syat5aek4y 
       foreign key (address_id) 
       references address_table
Hibernate: 
    
    alter table employee_address_table 
       add constraint FKsdltnwu6d8hy4t4uvmlju2lt4 
       foreign key (employee_id) 
       references employee_table
Hibernate: 
    select
        hibernate_sequence.nextval 
    from
        dual
Hibernate: 
    select
        hibernate_sequence.nextval 
    from
        dual
Hibernate: 
    select
        hibernate_sequence.nextval 
    from
        dual
Employee is created with Id:: 1
Hibernate: 
    insert 
    into
        employee_table
        (date_of_join, email, employee_name, salary, employee_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        address_table
        (city_name, postal_code, state_name, street_name, address_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        address_table
        (city_name, postal_code, state_name, street_name, address_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        employee_address_table
        (employee_id, address_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        employee_address_table
        (employee_id, address_id) 
    values
        (?, ?)
*/