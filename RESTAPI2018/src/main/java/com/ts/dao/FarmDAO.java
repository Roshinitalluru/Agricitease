package com.ts.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.rest.dto.Address;
import com.rest.dto.Farm;
import com.rest.dto.Product;
import com.ts.db.HibernateTemplate;

public class FarmDAO {
private SessionFactory factory = null;
	
	public int register(Farm farm) {		
		return HibernateTemplate.addObject(farm);
	}
	public List<Farm> getAllFarms(){
		return HibernateTemplate.getObjectListByQuery();
		
	}
	public List<Farm> getFarmsById(int userId){
		return HibernateTemplate.getObjectByUserId(userId);
	}
	public int updateEmp(Farm farm){
		return HibernateTemplate.updateObject(farm);
	}
	public List<Farm> getFarmDetailsById(int addressId) {
		return (List<Farm>)HibernateTemplate.getObjectByAddressId(addressId);
	}
	//consumer
	

	public Farm getSingleFarmbyId(int userId) {
		return HibernateTemplate.getFarmbyId(userId);
	}
	public static List<Farm> getFarmsbyKind(String kind){
		return HibernateTemplate.getFarmsbyKind(kind);
	}
	public Farm getEmployee(int farmId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
	    //Employee employee = (Employee)session.load(Employee.class, empId);
		Farm employee = (Farm) session.get(Farm.class, farmId);		
		System.out.println(employee); 
		return employee;
	}
	
	public void deletefarm(Farm farm) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.delete(farm);
		
		Transaction tx = session.beginTransaction();
		tx.commit();
		session.close();
	}
	//count
	public long getCountY() {
		// TODO Auto-generated method stub
		return (long)HibernateTemplate.getCountY();
	} 
	
	public long getCountYA() {
		// TODO Auto-generated method stub
		return (long)HibernateTemplate.getCountYA();
	}
	public long getCropCount(String crop){
		return HibernateTemplate.getCropCount(crop);
	}
}
