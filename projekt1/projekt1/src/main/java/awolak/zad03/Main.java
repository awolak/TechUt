package awolak.zad03;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import awolak.zad03.domain.Bed;
import awolak.zad03.service.BedService;

public class Main {
	public static void main(String[] args) throws SQLException {

        BedService bedService = new BedService();
        

        List<Bed> beds = new ArrayList<>();
        
        
        Bed Bed1 = new Bed("B1", "1997-02-11", true, 1.2, 5);
        Bed Bed2 = new Bed("B2", "2017-03-02", false, 2.2, 7);	
        Bed Bed3 = new Bed("B3", "2008-01-11", true, 1.7, 2);	
        /*bedservice.addBed(Bed1);
        bedservice.addBed(Bed2);
        bedservice.addBed(Bed3);*/
        bedService.deleteAllBeds();
        /*bedservice.deleteBed(Bed1);
        bedservice.deleteBed(Bed2);
        bedservice.deleteBed(Bed3);*/
        beds.add(Bed1);
        beds.add(Bed2);
        beds.add(Bed3);
        
        System.out.println(bedService.addAllBeds(beds));
        System.out.println(bedService.findByName("B1").toString());
        /*
        Bed Bed4= new Bed("Panaceum:", "1997-02-11", false, 1.2, 5);	// 
        Bed Bed5 = new Bed("Krowa:", "2017-03-02", true, 2.2, 7);	
        Bed Bed6 = new Bed("Bambol:", "2008-01-11", false, 1.7, 2);	
        
        List<Bed> beds2 = new ArrayList<>();
        beds.add(Bed4);
        beds.add(Bed2);
        beds.add(Bed5);
        
        */
        //System.out.println(bedservice.deleteBed(Bed6));
        
}
}