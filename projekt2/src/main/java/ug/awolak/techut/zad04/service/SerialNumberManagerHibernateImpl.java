package ug.awolak.techut.zad04.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.awolak.techut.zad04.domain.SerialNumber;

@Component
@Transactional
public class SerialNumberManagerHibernateImpl implements SerialNumberManager {

	@Autowired
	SessionFactory hsf;
	
	
	@Override
	public void addSerialNumber(SerialNumber serialnumber) {
		hsf.getCurrentSession().save(serialnumber);
	}

}
