package com.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.BillItemDTO;
import com.model.BillNoDTO;

@Repository
public class BillDAOImpl extends BillDAO{
	@Autowired
	SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	
	public void maxCustomer() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("select max(CustomerMobile) from BillNos  ");
		
		List<String> list=query.list();
		for(String emp:list) {
			System.out.println((String)emp);
		}
	}
	public void AllProducts() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from ItemBill");
		//query.setParameter("c",itemid);
		List<BillItemDTO> list=query.list();
		for(BillItemDTO emp:list) {
			System.out.println(emp.getItemId()+":"+emp.getQuantity()+":"+emp.getSno());
		}
	}
	public Long BillNo() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("select count(BillNo) from BillNos ");
		List<Long> list=query.list();
		for(Long emp:list) {
			System.out.println(emp);
			return emp;
		}
		return (long) 0;
	}
	public void ItemNo() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("select count(sno) from ItemBill ");
		List<Long> list=query.list();
		for(Long emp:list) {
			System.out.println(emp);
		}
	}
	
	public void AddItemsInvoice(BillNoDTO bill,List<BillItemDTO> items) {
		Session session=sessionFactory.getCurrentSession();
		session.merge(bill);
		for(BillItemDTO o:items) {
			session.merge(o);
			
		}
		
	}
}
