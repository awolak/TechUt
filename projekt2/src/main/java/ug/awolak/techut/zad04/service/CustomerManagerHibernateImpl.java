package ug.awolak.techut.zad04.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.awolak.techut.zad04.domain.Customer;

@Component
@Transactional
public class CustomerManagerHibernateImpl implements CustomerManager {
	
	@Autowired
	private SessionFactory hsf;

	@Override
	public void addCustomer(Customer customer) {
		hsf.getCurrentSession().save(customer);
		
	}
	

}
