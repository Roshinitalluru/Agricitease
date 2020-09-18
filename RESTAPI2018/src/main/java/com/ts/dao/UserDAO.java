package com.ts.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.rest.dto.Farm;
import com.rest.dto.User;
import com.ts.db.HibernateTemplate;

public class UserDAO {
	private SessionFactory factory = null;
	public int register(User user) {
		return HibernateTemplate.addObject(user);
	}
	public User login(String loginId,String password,String role){
		return (User) HibernateTemplate.getObjectByUserPass(loginId, password, role);
	}
	public int updateUser(User user){
		return HibernateTemplate.updateObject(user);
	}
	public List<User> getFarmers(String qualification) {
		// TODO Auto-generated method stub
		return (List<User>)HibernateTemplate.getObjectByquali(qualification);

}
	//count
	public long getCount() {
		// TODO Auto-generated method stub
		return (long)HibernateTemplate.getCount();
	}
	
	public long getCountC() {
		// TODO Auto-generated method stub
		return (long)HibernateTemplate.getCountC();
	}
	
	public long getCountF() {
		// TODO Auto-generated method stub
		return (long)HibernateTemplate.getCountF();
	}
	

}
