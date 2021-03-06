package ug.awolak.techut.zad04.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.awolak.techut.zad04.domain.Address;
import ug.awolak.techut.zad04.domain.Bed;
import ug.awolak.techut.zad04.domain.Producer;

@Component
@Transactional
public class ProducerManagerHibernateImpl implements ProducerManager {

	@Autowired
    SessionFactory hsf;

    @Override
    public void addProducer(Producer producer) {
        hsf.getCurrentSession().save(producer);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Producer> getAllProducers() {
        return hsf.getCurrentSession().getNamedQuery("producer.all").list();
    }

    @Override
    public Producer findById(long id) {
        return (Producer) hsf.getCurrentSession().get(Producer.class, id);

    }

    @Override
    public void updateProducer(Producer producer) {
        hsf.getCurrentSession().update(producer);
    }

    @Override
    public void deleteProducer(Producer producer) {
        hsf.getCurrentSession().delete(producer);
    }

    @Override
    public void assignBed(Long bedId, Long producerId) {
        Bed bed = (Bed) hsf.getCurrentSession().get(Bed.class, bedId);
        Producer producer = (Producer) hsf.getCurrentSession().get(Producer.class, producerId);
        producer.getBeds().add(bed);
    }

	@Override
	public void assignAddress(Long addressId, Long producerId) {
		Address address = (Address) hsf.getCurrentSession().get(Address.class, addressId);
        Producer producer = (Producer) hsf.getCurrentSession().get(Producer.class, producerId);
        producer.getAddresses().add(address);
		
	}

	@Override
	public void deleteAddress(Long addressId, Long producerId) {
		Address address = (Address) hsf.getCurrentSession().get(Address.class, addressId);
        Producer producer = (Producer) hsf.getCurrentSession().get(Producer.class, producerId);
        producer.getAddresses().remove(address);
		
	}

	@Override
	public Producer findByName(String name) {
		Query query = hsf.getCurrentSession().
                createQuery("from Producer where name=:name");
        query.setParameter("name", name);
        Producer producer = (Producer) query.uniqueResult();

        return producer;
	}

	@Override
	public List<Address> getProducerAddresses(Producer producer) {
		producer = (Producer) hsf.getCurrentSession().get(Producer.class, producer.getId());

        List<Address> addresses = new ArrayList<Address>(producer.getAddresses());

        return addresses;
	}

}
