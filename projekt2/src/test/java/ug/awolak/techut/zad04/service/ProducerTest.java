package ug.awolak.techut.zad04.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ug.awolak.techut.zad04.domain.Address;
import ug.awolak.techut.zad04.domain.Bed;
import ug.awolak.techut.zad04.domain.Producer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class ProducerTest {

	@Autowired
    ProducerManager pm;
	
	@Autowired
    AddressManager am;
	
    @Test
    public void addProducerCheck() {
        Producer p = new Producer("BRW");

        pm.addProducer(p);

        Producer retrieved = pm.findById(p.getId());

        assertEquals(p.getId(), retrieved.getId());
    }
    
    @Test
    public void findProducerByNameCheck() {
    	Producer p = new Producer("BRW");
        pm.addProducer(p);

        String name = p.getName();

        Producer foundProducer = pm.findByName(name);

        assertEquals(foundProducer.getId(), p.getId());
    }
    
    @Test 
    public void producerAndAddressesCheck() {
    	Producer p = new Producer("BRW");
    	Address a1 = new Address("Jagiellonska", "281", "80-366", "Gdansk");
    	Address a2 = new Address("Piastowska", "66", "80-366", "Gdansk");
    	
    	pm.addProducer(p);
    	
    	int beginningSize = pm.getProducerAddresses(p).size();
    	
    	am.addAddress(a1);
    	am.addAddress(a2);
    	
    	// Assign Test
    	pm.assignAddress(a1.getId(), p.getId());
    	pm.assignAddress(a2.getId(), p.getId());
    	
    	int afterAssignedSize = pm.getProducerAddresses(p).size();
    	
    	assertEquals(beginningSize + 2, afterAssignedSize);
    	
    	// Delete Test
    	pm.deleteAddress(a2.getId(), p.getId());
    	
    	int afterFirstDeleteSize = pm.getProducerAddresses(p).size();
    	
    	assertEquals(beginningSize + 1, afterFirstDeleteSize);
    	
    	pm.deleteAddress(a1.getId(), p.getId());
    	
    	int afterWholeDeleteSize = pm.getProducerAddresses(p).size();
    	
    	assertEquals(beginningSize, afterWholeDeleteSize);
    }
}
