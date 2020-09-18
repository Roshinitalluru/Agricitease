package com.ts.db;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.rest.dto.Address;
import com.rest.dto.Blog;
import com.rest.dto.Farm;
import com.rest.dto.Orders;
import com.rest.dto.Product;
import com.rest.dto.User;


public class HibernateTemplate {

	private static SessionFactory sessionFactory;
	
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static int addObject(Object obj){
		System.out.println("Inside Template...");
		int result=0;
		
		Transaction tx=null;
		
		try {
			
			Session session=sessionFactory.openSession();
			tx=session.beginTransaction();
			
			session.save(obj);
			
			tx.commit();
			
			result=1;
			session.close();
			
		} catch (Exception e) {
		
			tx.rollback();
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Object getObject(Class c,Serializable serializable)
	{
		Object obj=null;
		
		try {			
			return sessionFactory.openSession().get(c,serializable);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return obj;
	}
	
	public static Object getObjectByUserPass(String loginId,String password,String role) {
	
	String queryString = "from User where userName = :loginId and password =:password and role = :role";
	  Query query = sessionFactory.openSession().createQuery(queryString);
	  query.setString("loginId", loginId);
	  query.setString("password", password);
	  query.setString("role", role);
	  Object queryResult = query.uniqueResult();
	  User employee = (User)queryResult;
	  return employee; 
	}
	public static Object getObjectByEmail(int userId) {
		
		String queryString = "from Address where userId = :userId";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setLong("userId", userId);
		  Object queryResult = query.uniqueResult();
		  Address employee = (Address)queryResult;
		  return employee; 
		}

	public static List<Farm> getObjectByUserId(int userId) {
		
		String queryString = "from Farm where addressId = :userId";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setLong("userId", userId);
		  List queryResult = query.list();
		  List<Farm> employee = (List<Farm>)queryResult;
		  return employee; 
		}
	
	public static List<Farm> getObjectListByQuery()
	{
		String query = "from Farm" ;
		return sessionFactory.openSession().createQuery(query).list();
	}
	
	public static int updateObject(Object obj)
	{
		int result=0;
		
		Transaction tx=null;
		
		try {
			
			Session session=sessionFactory.openSession();
			tx=session.beginTransaction();
			
			session.saveOrUpdate(obj);
			
			tx.commit();
			
			result=1;
			
		} catch (Exception e) {
		
			tx.rollback();
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static int deleteObject(Class c,Serializable serializable)
	{
		int result=0;
		
		Session session=sessionFactory.openSession();
		
		Transaction tx=session.beginTransaction();
		
		try {
			
			Object obj=session.get(c,serializable);
			
			session.delete(obj);
			
			tx.commit();
			
			result=1;
						
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tx.rollback();
		}
		
		return result;
	}

	public static List<Object> getObjectListByName(Class c, String columName, String value) {
		Session session=sessionFactory.openSession();
		  Criteria criteria = session.createCriteria(c);
			Criterion nameCriterion = Restrictions.eq(columName, value);
			criteria.add(nameCriterion);
			return criteria.list();
	} 
	
	
	
	//representave
public static User getObjectByUserId1(int userId) {
		
		String queryString = "from User where userId = userId";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setInteger("userId", userId);
		  Object queryResult = query.uniqueResult();
		  User user = (User)queryResult;
		  return user; 
		
	}
	
	public static Object getObjectById(int userId){
		String queryString ="from Address where userId = :userId";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setLong("userId", userId);
		Object queryResult = query.uniqueResult();
		Address user = (Address)queryResult;
		return user;
	}

	public static List<User> getObjectByquali(String qualification) {
		// TODO Auto-generated method stub
		int userId = Integer.parseInt(qualification);
		String queryString ="from User where qualification = :userId";
		
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setString("userId", qualification);
		List queryResult = query.list();
		List<User> userList = (List<User>)queryResult;
		return userList;
	}
	
	
	public static List<Farm> getObjectByAddressId(int addressId) {
		// TODO Auto-generated method stub
		
		String queryString ="from Farm where addressId = :addressId";
		
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setLong("addressId", addressId);
		Object queryResult = query.list();
		List<Farm> farm = (List<Farm>)queryResult;
		return farm;
	}
	public static List<Object> getObjectListByQuery1(String query)
	{
		
		
		return sessionFactory.openSession().createQuery(query).list();
	}
	//consumer
	public static Product getProductbyId(int userId) {
		String queryString = "from Product where productId = :userId";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setLong("userId", userId);
		Object queryResult = query.uniqueResult();
		Product employee = (Product)queryResult;
		return employee; 
	}
	public static Farm getFarmbyId(int userId) {
		String queryString = "from Farm where farmId = :userId";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setLong("userId", userId);
		Object queryResult = query.uniqueResult();
		Farm employee = (Farm)queryResult;
		return employee; 
	}

	
	public static List<Farm> getFarmsbyKind(String kind) {
		String queryString = "from Farm where kindOfCrop = :kind";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setString("kind", kind);
		List queryResult = query.list();
		List<Farm> employee = (List<Farm>)queryResult;
		return employee; 
		}
	
	
	//orders
	public static List<Orders> getOrdersbyId(int id) {
		String queryString = "from Orders where addressId = :kind";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setLong("kind", id);
		List queryResult = query.list();
		List<Orders> employee = (List<Orders>)queryResult;
		return employee; 
		}
	public static List<Orders> getOrdersbyConsumerId(int id) {
		String queryString = "from Orders where consumerId = :kind";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setLong("kind", id);
		List queryResult = query.list();
		List<Orders> employee = (List<Orders>)queryResult;
		return employee; 
		}
//reviews
	public static List<Blog> getObjectListByQuery2()
	{
		String query = "from Blog" ;
		return sessionFactory.openSession().createQuery(query).list();
	}
	
	//count
	public static  long getCount(){
		String queryString ="select count(userId) from User";
		Query query = sessionFactory.openSession().createQuery(queryString);
		Object queryResult = query.uniqueResult();
		long count =(long)queryResult;
		return count;
		
	}
	
	public static  long getCountF(){
		String queryString ="select count(*) from User where role = 'farmer' ";
		Query query = sessionFactory.openSession().createQuery(queryString);
		Object queryResult = query.uniqueResult();
		long countF =(long)queryResult;
		return countF;
		
	}
	
	public static  long getCountC(){
		String queryString ="select count(*) from User where role = 'consumer' ";
		Query query = sessionFactory.openSession().createQuery(queryString);
		Object queryResult = query.uniqueResult();
		long countC =(long)queryResult;
		return countC;
		
	}
	
	public static  long getCountY(){
		String queryString ="select count(farmId) from Farm";
		Query query = sessionFactory.openSession().createQuery(queryString);
		Object queryResult = query.uniqueResult();
		long countY =(long)queryResult;
		return countY;
		
	}
	
	public static  long getCountYA(){
		String queryString ="select count(*) from Orders where status = 'Accepted' ";
		Query query = sessionFactory.openSession().createQuery(queryString);
		Object queryResult = query.uniqueResult();
		long countYA =(long)queryResult;
		return countYA;
		
	}


}
