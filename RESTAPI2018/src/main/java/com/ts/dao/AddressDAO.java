package com.ts.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.rest.dto.Address;
import com.rest.dto.User;
import com.ts.db.HibernateTemplate;

public class AddressDAO {
private SessionFactory factory = null;
	
	public int register(Address dept) {		
		return HibernateTemplate.addObject(dept);
	}
	public Address viewProfile(int userId){
		return (Address) HibernateTemplate.getObjectByEmail(userId);
	}
	//representative
	public Address viewProfile1(int userId) {
		// TODO Auto-generated method stub
		return (Address)HibernateTemplate.getObjectById(userId);
		
	}
	public List<Address> getAllAddress() {
		List<Address> addressList=(List)HibernateTemplate.getObjectListByQuery1("From Address");
		return addressList;	
	}



}
