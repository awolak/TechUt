package awolak.zad03;

import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import awolak.zad03.domain.Bed;
import awolak.zad03.service.BedService;

public class BedServiceTest {
	BedService bs = new BedService();
	private final String NAME_BED_1 = "Bed1";
	private final String NAME_BED_2 = "Bed2";
	private final String NAME_BED_3 = "Bed3";
	private final String NAME_BED_4 = "Bed4";
	private final String NAME_BED_5 = "Bed5";
	private final String NAME_BED_6 = "Bed6";
	private final double WEIGHT_BED_1 = 12.5;
	private final double WEIGHT_BED_2 = 10.0;
	private final double WEIGHT_BED_3 = 8.5;
	private final double WEIGHT_BED_4 = 12.5;
	private final double WEIGHT_BED_5 = 10.0;
	private final double WEIGHT_BED_6 = 12.5;
	private final String DATE_BED_1 = "2017-07-07";
	private final String DATE_BED_2 = "2016-06-06";
	private final String DATE_BED_3 = "2015-05-05";
	private final String DATE_BED_4 = "2015-05-04";
	private final String DATE_BED_5 = "2015-05-03";
	private final String DATE_BED_6 = "2015-05-02";
	private final int SHEETS_BED_1 = 5;
	private final int SHEETS_BED_2 = 7;
	private final int SHEETS_BED_3 = 4;
	private final int SHEETS_BED_4 = 6;
	private final int SHEETS_BED_5 = 1;
	private final int SHEETS_BED_6 = 2;
	
	/*@Test
	public void addBedTest() throws SQLException {
		Bed b = new Bed(NAME_BED_1, DATE_BED_1, true, WEIGHT_BED_1, SHEETS_BED_1);
		bs.addBed(b);

		Bed retrievedBed = bs.findByName(b.getName());
		
		assertEquals(NAME_BED_1, retrievedBed.getName());
		assertEquals(DATE_BED_1, retrievedBed.getDateOfBirth());
	}
	
	@Test
	public void getAllBedsTest() throws SQLException {
		Bed b1 = new Bed(NAME_BED_1, DATE_BED_1, true, WEIGHT_BED_1, SHEETS_BED_1);
		Bed b2 = new Bed(NAME_BED_2, DATE_BED_2, true, WEIGHT_BED_2, SHEETS_BED_2);
		Bed b3 = new Bed(NAME_BED_3, DATE_BED_3, false, WEIGHT_BED_3, SHEETS_BED_3);
		bs.addBed(b1);
		bs.addBed(b2);
		bs.addBed(b3);

		assertEquals(3, bs.getAllBeds().size());
	}*/
	
	/*@Test
	public void deleteBedTest() throws SQLException {
		Bed b = new Bed(NAME_BED_1, DATE_BED_1, true, WEIGHT_BED_1, SHEETS_BED_1);
		bs.addBed(b);

		int count = bs.getAllBeds().size();
		bs.deleteBed(b);

		assertEquals(count-1, bs.getAllBeds().size());
	}*/
	
	@Test
	public void addAllBedsTest() throws SQLException {
		List<Bed> beds = new ArrayList<Bed>();
		Bed b1 = new Bed(NAME_BED_1, DATE_BED_1, true, WEIGHT_BED_1, SHEETS_BED_1);
		Bed b2 = new Bed(NAME_BED_2, DATE_BED_2, true, WEIGHT_BED_2, SHEETS_BED_2);
		Bed b3 = new Bed(NAME_BED_3, DATE_BED_3, false, WEIGHT_BED_3, SHEETS_BED_3);
		Bed b4 = new Bed(NAME_BED_4, DATE_BED_4, false, WEIGHT_BED_4, SHEETS_BED_4);
		Bed b5 = new Bed(NAME_BED_5, DATE_BED_5, false, WEIGHT_BED_5, SHEETS_BED_5);
		Bed b6 = new Bed(NAME_BED_6, DATE_BED_6, false, WEIGHT_BED_6, SHEETS_BED_6);
		
		beds.add(b1);
		beds.add(b2);
		beds.add(b3);
		beds.add(b4);
		beds.add(b5);
		beds.add(b6);
		
		bs.addAllBeds(beds);

		assertEquals(6, bs.getAllBeds().size());
	}
	
	@Test
	public void deleteAllBedsTest() throws SQLException {
		bs.deleteAllBeds();
		assertEquals(0, bs.getAllBeds().size());
	}
}
