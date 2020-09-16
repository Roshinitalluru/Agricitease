package com.ts.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.rest.dto.Farm;
import com.rest.dto.Product;
import com.ts.db.HibernateTemplate;

public class ProductDAO {
	public int addproduct(Product product) {		
		return HibernateTemplate.addObject(product);
	}
	public List<Product> getProductss() {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("from Product p");
		List<Product> empList = q1.list();
		session.close();
		return empList;
	}
	//consumer
	public Product getProductbyId(int userId) {
		return HibernateTemplate.getProductbyId(userId);
	}
	public Product getEmployee(int productId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
	    //Employee employee = (Employee)session.load(Employee.class, empId);
		Product employee = (Product) session.get(Product.class, productId);		
		System.out.println(employee); 
		return employee;
	}
	public void deletefarm(Product product) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.delete(product);
		
		Transaction tx = session.beginTransaction();
		tx.commit();
		session.close();
	}

	

}
