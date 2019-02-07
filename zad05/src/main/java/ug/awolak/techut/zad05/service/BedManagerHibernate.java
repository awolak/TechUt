package ug.awolak.techut.zad05.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.awolak.techut.zad05.domain.Bed;
import ug.awolak.techut.zad05.domain.Producer;
import ug.awolak.techut.zad05.domain.Type;
import ug.awolak.techut.zad05.domain.Pillow;


@Component
@Transactional
public class BedManagerHibernate implements BedManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addBed(Bed b) {
		//b.setId(null);
		//sessionFactory.getCurrentSession().persist(b);
		sessionFactory.getCurrentSession().save(b);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Bed> getAllBeds() {
        return sessionFactory.getCurrentSession().getNamedQuery("bed.findAll").list();
	}

	@Override
	public Bed findBedByName(String name) {
        return (Bed) sessionFactory.getCurrentSession().get(Bed.class, name);
	}

	@Override
	public Bed findBedById(Long id) {
        return (Bed) sessionFactory.getCurrentSession().get(Bed.class, id);
	}

	@Override
	public void addProducer(Producer producer) {
		sessionFactory.getCurrentSession().save(producer);
		
	}

	@Override
	public void deleteProducer(Producer producer) {
        producer = (Producer) sessionFactory.getCurrentSession().get(Producer.class,
                producer.getId());
        sessionFactory.getCurrentSession().delete(producer);
	}

	@Override
	public Producer findProducerById(Long id) {
        return (Producer) sessionFactory.getCurrentSession().get(Producer.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Producer> getAllProducers() {
        return sessionFactory.getCurrentSession().getNamedQuery("producer.findAll").list();
	}


	@Override
	public void addPillow(Pillow pillow) {
		sessionFactory.getCurrentSession().save(pillow);
		
	}

	@Override
	public void deletePillow(Pillow pillow) {
        pillow = (Pillow) sessionFactory.getCurrentSession().get(Pillow.class,
                pillow.getId());
        sessionFactory.getCurrentSession().delete(pillow);
		
	}

	@Override
	public Pillow findPillowById(Long id) {
        return (Pillow) sessionFactory.getCurrentSession().get(Pillow.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pillow> getAllPillows() {
        return sessionFactory.getCurrentSession().getNamedQuery("pillow.findAll").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pillow> findBedPillows(Bed bed) {
        return sessionFactory.getCurrentSession().getNamedQuery("Bed.findBedPillows").setLong("id", bed.getId()).list();
	}

	@Override
	public void addType(Type type) {
		sessionFactory.getCurrentSession().save(type);
		
	}

	@Override
	public void deleteType(Type type) {
        type = (Type) sessionFactory.getCurrentSession().get(Type.class,
                type.getId());
        sessionFactory.getCurrentSession().delete(type);
		
	}

	@Override
	public Type findTypeById(Long id) {
        return (Type) sessionFactory.getCurrentSession().get(Type.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getAllType() {
        return sessionFactory.getCurrentSession().getNamedQuery("type.findAll").list();
	}


	@Override
	public List<Bed> getProducerBeds(Producer producer) {
        producer = (Producer) sessionFactory.getCurrentSession().get(Producer.class,
                producer.getId());
        List<Bed> beds = new ArrayList<Bed>(producer.getBedList());
        return beds;
	}

	@Override
	public void deleteBed(Bed bed) {
        bed = (Bed) sessionFactory.getCurrentSession().get(Bed.class,
                bed.getId());
        sessionFactory.getCurrentSession().delete(bed);
		
	}

	@Override
	public void addBedToProducer(Bed b, Producer o) {
        List<Bed> beds = o.getBedList();
        beds.add(b);
        o.setBedList(beds);
        sessionFactory.getCurrentSession().save(o);
		
	}

	@Override
	public void removeBedFromProducer(Bed b, Producer o) {
        List<Bed> beds = o.getBedList();
        beds.remove(b);
        o.setBedList(beds);
        sessionFactory.getCurrentSession().save(o);
		
	}

	@Override
	public void addPillowToBed(Pillow t, Bed b) {
        List<Pillow> pillows = b.getPillowList();
        pillows.add(t);
        b.setPillowList(pillows);
        sessionFactory.getCurrentSession().save(b);
		
	}
	
}
