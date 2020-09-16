package com.ts.dao;

import java.util.List;

import com.rest.dto.Orders;
import com.ts.db.HibernateTemplate;

public class OrdersDAO {
	public int register(Orders order) {		
		return HibernateTemplate.addObject(order);
	}
	public int updateEmp(Orders ord){
		return HibernateTemplate.updateObject(ord);
	}

	public List<Orders> getOrdersbyId(int id) {
		return HibernateTemplate.getOrdersbyId(id);
	}
	public List<Orders> getOrdersbyConsumerId(int id){
		return HibernateTemplate.getOrdersbyConsumerId(id);
	}


}
