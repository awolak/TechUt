package ug.awolak.techut.zad04.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ug.awolak.techut.zad04.domain.Bed;
import ug.awolak.techut.zad04.domain.Customer;
import ug.awolak.techut.zad04.domain.SerialNumber;
import ug.awolak.techut.zad04.domain.Producer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class BedTest {

    @Autowired
    BedManager bm;
    
    @Autowired
    CustomerManager cm;
    
    @Autowired
    SerialNumberManager lm;
    
    @Autowired
    ProducerManager pm;
    
    private static final double DELTA = 1e-15;
    
    private static String sDate = "01-01-2015";
	private static Date dDate;
	static {
		try {
			dDate = new SimpleDateFormat("dd-MM-yyyy").parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    

    @Test
    public void addBedCheck() {
        Bed b = new Bed(180, dDate, true);

        bm.addBed(b);

        Bed retrieved = bm.findById(b.getId());

        assertEquals(b.getId(), retrieved.getId());
    }

    @Test
    public void getAllBedsCheck() {
        List<Bed> before = bm.getAllBeds();
    	
        Bed b1 = new Bed(1000, dDate, true);
        Bed b2 = new Bed(2000, dDate, false);

        bm.addBed(b1);
        bm.addBed(b2);

        List<Bed> after = bm.getAllBeds();

        assertEquals(before.size() + 2, after.size());
    }

    @Test
    public void findBedByIdCheck() {
    	Bed b = new Bed(999, dDate, true);
        bm.addBed(b);

        long id = b.getId();

        Bed foundBed = bm.findById(id);

        assertEquals(b.getId(), foundBed.getId());
    }

    @Test
    public void deleteBedCheck() {
        List<Bed> before = bm.getAllBeds();

    	Bed b = new Bed(999, dDate, true);
    	
        bm.addBed(b);
        bm.deleteBed(b);

        List<Bed> after = bm.getAllBeds();

        assertEquals(before.size(), after.size());
    }

    @SuppressWarnings("deprecation")
	@Test
    public void updateBedCheck() {
    	Bed b = new Bed(999, dDate, true);
        bm.addBed(b);

        double newWidth = 200;

        b.setWidth(newWidth);

        bm.updateBed(b);

        assertEquals(b.getWidth(), newWidth, DELTA);
    }
    
    @Test
    public void bedAndCustomerCheck() {
        Customer c1 = new Customer("Keanu", "Reeves", 1997);
        Customer c2 = new Customer("Alicja", "Wolak", 1997);

        cm.addCustomer(c1);
        cm.addCustomer(c2);
        
    	Bed b1 = new Bed(150, dDate, true);
    	Bed b2 = new Bed(190, dDate, false);


        bm.addBed(b1);
        bm.addBed(b2);

        List<Customer> customersOneBefore = bm.getBedCustomers(b1);
        List<Customer> customersTwoBefore = bm.getBedCustomers(b2);
        int beforeOne = customersOneBefore.size();
        int beforeTwo = customersTwoBefore.size();

        bm.relateBedAndCustomer(b1.getId(), c1.getId());
        bm.relateBedAndCustomer(b1.getId(), c2.getId());
        bm.relateBedAndCustomer(b2.getId(), c1.getId());
        bm.relateBedAndCustomer(b2.getId(), c2.getId());
        

        List<Customer> customersOneAfter = bm.getBedCustomers(b1);
        List<Customer> customersTwoAfter = bm.getBedCustomers(b2);
        int afterOne = customersOneAfter.size();
        int afterTwo = customersTwoAfter.size();

        assertEquals(beforeOne + 2, afterOne);
        assertEquals(beforeTwo + 2, afterTwo);
        assertEquals(b1.getCustomers().get(afterOne-2).getFirstName(), c1.getFirstName());
        assertEquals(b1.getCustomers().get(afterOne-1).getFirstName(), c2.getFirstName());
        assertEquals(b2.getCustomers().get(afterTwo-2).getFirstName(), c1.getFirstName());
        assertEquals(b2.getCustomers().get(afterTwo-1).getFirstName(), c2.getFirstName());
    }
    
    @Test
    public void giveSerialNumberTest() {
 
    	Bed bed = new Bed(210, dDate, true);
        bm.addBed(bed);

        SerialNumber serialnumber = new SerialNumber((int) Math.random());
        lm.addSerialNumber(serialnumber);

        bm.giveSerialNumber(bed.getId(), serialnumber.getId());

        assertEquals(bed.getSerialNumber().getId(), serialnumber.getId());
    }
    
    @Test
    public void bedAndProducersTest() {
 
    	Bed bed = new Bed(190, dDate, true);
        bm.addBed(bed);

        Producer producer = new Producer("BRW");
        pm.addProducer(producer);

        pm.assignBed(bed.getId(), producer.getId());

        assertTrue(producer.getBeds().contains(bed));
    }
}