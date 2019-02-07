package ug.awolak.techut.zad05.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


import ug.awolak.techut.zad05.domain.Bed;
import ug.awolak.techut.zad05.domain.Producer;
import ug.awolak.techut.zad05.domain.Type;
import ug.awolak.techut.zad05.domain.Pillow;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class BedManagerTest {

	@Autowired
	BedManager bm;

	private final String NAME_PILLOW_1 = "Pillow1";
	private final String NAME_PILLOW_2 = "Pillow2";
	private final String NAME_PILLOW_3 = "Pillow3";
	
	private final String NAME_Type_1 = "Type1";
	private final String NAME_Type_2 = "Type2";
	private final String NAME_Type_3 = "Type3";
	private final String DESCRIPTION_Type_1 = "WhiteXL";
	private final String DESCRIPTION_Type_2 = "BlueL";
	private final String DESCRIPTION_Type_3 = "YellowM";
	
	private final String NAME_Producer_1 = "BRW";
	private final String NAME_Producer_2 = "IKEA";
	private final String NAME_Producer_3 = "POLO";
	
	private final String NAME_BED_1 = "Bed1";
	private final String NAME_BED_2 = "Bed2";
	private final String NAME_BED_3 = "Bed3";
	private final double WEIGHT_BED_1 = 68.5;
	private final double WEIGHT_BED_2 = 130.0;
	private final double WEIGHT_BED_3 = 180.5;
	private final Date DATE_BED_1 = new Date(2017, 12, 14);
	private final Date DATE_BED_2 = new Date(2000, 12, 14);
	private final Date DATE_BED_3 = new Date(2005, 12, 14);
	private final int Beds_BED_1 = 5;
	private final int Beds_BED_2 = 7;
	private final int Beds_BED_3 = 4;
	
	
	
	//____________________________________________________________________________PILLOWS TESTS:
	@Test
	public void addPillowsTest() {
		Pillow t = new Pillow(NAME_PILLOW_1);
		bm.addPillow(t);
		Pillow retrievedPillow = bm.findPillowById(t.getId());
		assertEquals(NAME_PILLOW_1, retrievedPillow.getName());
	}
    
	@Test
	public void getAllPillowsTest() {
		Pillow t1 = new Pillow(NAME_PILLOW_1);
		Pillow t2 = new Pillow(NAME_PILLOW_2);
		Pillow t3 = new Pillow(NAME_PILLOW_3);
		bm.addPillow(t1);
		bm.addPillow(t2);
		bm.addPillow(t3);
		assertEquals(3, bm.getAllPillows().size());
	}
	
//	@Test
//	public void deletePillowTest() {
//		Pillow t1 = new Pillow(NAME_PILLOW_1);
//		bm.addPillow(t1);
//		int count = bm.getAllPillows().size();
//		bm.deletePillow(t1);
//		assertEquals(count-1, bm.getAllPillows().size());
//	}
	
//	//_______________________________________________________________________________Type TESTS:
//	
//
//	@Test
//	public void addTypeTest() {
//		Type t = new Type(NAME_Type_1, DESCRIPTION_Type_1);
//		bm.addType(t);
//		Type retrievedType = bm.findTypeById(t.getId());
//		assertEquals(NAME_Type_1, retrievedType.getName());
//	}
//    
//	@Test
//	public void getAllTypeTest() {
//		Type t1 = new Type(NAME_Type_1, DESCRIPTION_Type_1);
//		Type t2 = new Type(NAME_Type_2, DESCRIPTION_Type_2);
//		Type t3 = new Type(NAME_Type_3, DESCRIPTION_Type_3);
//		bm.addType(t1);
//		bm.addType(t2);
//		bm.addType(t3);
//		assertEquals(3, bm.getAllType().size());
//	}
//	
//	@Test
//	public void deleteTailTest() {
//		Type t = new Type(NAME_Type_1, DESCRIPTION_Type_1);
//		bm.addType(t);
//		int count = bm.getAllType().size();
//		bm.deleteType(t);
//		assertEquals(count-1, bm.getAllType().size());}
	
	//____________________________________________________________________________________Producer TESTS:
	
//	@Test
//	public void addProducerTest() {
//		Producer o = new Producer(NAME_Producer_1);
//		bm.addProducer(o);
//		Producer retrievedProducer = bm.findProducerById(o.getId());
//		assertEquals(NAME_Producer_1, retrievedProducer.getFirstName());
//	}
//
//	@Test
//	public void getAllProducersTest() {
//		Producer o1 = new Producer(NAME_Producer_1);
//		Producer o2 = new Producer(NAME_Producer_2);
//		Producer o3 = new Producer(NAME_Producer_3);
//		bm.addProducer(o1);
//		bm.addProducer(o2);
//		bm.addProducer(o3);
//		assertEquals(3, bm.getAllProducers().size());
//	}
//
//	
//	
	

	@Test
	public void addBedToOwnerTest(){
		Bed b = new Bed(NAME_BED_1, DATE_BED_1, true, WEIGHT_BED_1, Beds_BED_1);
		bm.addBed(b);
		Producer o = new Producer(NAME_Producer_1);
		bm.addProducer(o);
		int count = bm.getProducerBeds(o).size();
		bm.addBedToProducer(b, o);
		assertEquals(count+1, bm.getProducerBeds(o).size());
	}
	
	


	@Test
	public void findBedPillowTest(){ 
		Bed b = new Bed(NAME_BED_1, DATE_BED_1, true, WEIGHT_BED_1, Beds_BED_1);
		bm.addBed(b);

		Pillow t1 = new Pillow(NAME_PILLOW_1);
		Pillow t2 = new Pillow(NAME_PILLOW_2);
		Pillow t3 = new Pillow(NAME_PILLOW_3);
		bm.addPillow(t1);
		bm.addPillow(t2);
		bm.addPillow(t3);

		bm.addPillowToBed(t1, b);
		bm.addPillowToBed(t2, b);
		bm.addPillowToBed(t3, b);

		int count = bm.findBedPillows(b).size();

		assertEquals(1, count);
	}
	
	
}