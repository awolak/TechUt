package ug.awolak.techut.zad03;

import java.sql.*;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;

import ug.awolak.techut.zad03.domain.Bed;
import ug.awolak.techut.zad03.service.BedService;

public class Main {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws SQLException, ParseException {

			Bed b1 = new Bed("Bed1", Double.parseDouble("200.85"), new Date(118, 12, 30), true);
	    	Bed b2 = new Bed("Bed2", Double.parseDouble("450.90"), new Date(115, 12, 30), false);
	    	
	        BedService ws = new BedService();
	
	        ws.addBed(b1);
	        ws.addBed(b2);
	        
	        List<Bed> beds = new ArrayList<Bed>();
	        beds.add(b1);
	        beds.add(b2);
	        ws.addBeds(beds);
	        
	        Bed bed = ws.getBedById(2);
	}

}
