package com.ts.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.rest.dto.Blog;

import com.ts.db.HibernateTemplate;

public class BlogDAO {
	private SessionFactory factory = null;
	public int register(Blog blog) {
		return HibernateTemplate.addObject(blog);
	}
	public List<Blog> getAllReviews(){
		return HibernateTemplate.getObjectListByQuery2();
		
	}

}
