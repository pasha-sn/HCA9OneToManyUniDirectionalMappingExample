package com.hibernateinfo.client;

import org.hibernate.Session;

import com.hibernateinfo.entities.Employee;
import com.hibernateinfo.util.HibernateUtil;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
public class ClientTest2FetchDataUsingEmployeeId {

	public static void main(String[] args) 
	{
		getEmployeeAndAddressByEmployeeId();
	}	
	
	
	
	private static void getEmployeeAndAddressByEmployeeId() 
	{		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) 
		{
			Employee employee = session.get(Employee.class, 1);
			System.out.println(employee);
			System.out.println(employee.getAddressList());			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
/*
<property name="hibernate.hbm2ddl.auto">update</property>
Hibernate: 
    select
        employee0_.employee_id as employee_id1_2_0_,
        employee0_.date_of_join as date_of_join2_2_0_,
        employee0_.email as email3_2_0_,
        employee0_.employee_name as employee_name4_2_0_,
        employee0_.salary as salary5_2_0_ 
    from
        employee_table employee0_ 
    where
        employee0_.employee_id=?
Hibernate: 
    select
        addresslis0_.employee_id as employee_id1_1_0_,
        addresslis0_.address_id as address_id2_1_0_,
        address1_.address_id as address_id1_0_1_,
        address1_.city_name as city_name2_0_1_,
        address1_.postal_code as postal_code3_0_1_,
        address1_.state_name as state_name4_0_1_,
        address1_.street_name as street_name5_0_1_ 
    from
        employee_address_table addresslis0_ 
    inner join
        address_table address1_ 
            on addresslis0_.address_id=address1_.address_id 
    where
        addresslis0_.employee_id=?
Employee [employeeId=1, employeeName=Pasha Sadi, email=pasha.sn@gmail.com, doj=2011-06-1 10:00:00.000, addressList=[Address [addressId=2, street=Guy St, city=Montreal, state=Quebec, postalcode=19317], Address [addressId=3, street=Peel St, city=Montreal, state=Quebec, postalcode=19318]], salary=65001.0]
[Address [addressId=2, street=Guy St, city=Montreal, state=Quebec, postalcode=19317], Address [addressId=3, street=Peel St, city=Montreal, state=Quebec, postalcode=19318]]

*/