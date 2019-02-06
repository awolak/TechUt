package awolak.zad03.service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import awolak.zad03.domain.Bed;

public class BedService {
	private Connection connection;

	private List<Bed> beds = new ArrayList<Bed>();
    private String url = "jdbc:hsqldb:hsql://localhost/workdb";

    private String createTableBed = "CREATE TABLE Bed(id bigint GENERATED BY DEFAULT AS IDENTITY, name varchar(30) UNIQUE , date_of_birth date, is_soft boolean, weight double, count_of_sheets int)";

    private PreparedStatement addBedSt;
    private PreparedStatement deleteBedByIdSt;
    private PreparedStatement deleteAllBedsSt;
    private PreparedStatement getAllBedsSt;
    private PreparedStatement getBedByNameSt;
    private Statement statement;

    public BedService(){
        try{
           connection = DriverManager.getConnection(url);
           statement = connection.createStatement();

            ResultSet resultSet = connection.getMetaData().getTables(null,null,null,null);
            boolean tableExists = false;
            while (resultSet.next()){
                if("Bed".equalsIgnoreCase(resultSet.getString("table_name"))){
                    tableExists = true;
                    break;
                }
            }
            if(!tableExists){
                statement.executeUpdate(createTableBed);
            }
            addBedSt = connection.prepareStatement("INSERT INTO Bed (name, date_of_birth, is_soft, weight, count_of_sheets) VALUES (?, ?, ?, ?, ?)");
            deleteBedByIdSt = connection.prepareStatement("DELETE FROM Bed WHERE name=?");
            deleteAllBedsSt = connection.prepareStatement("DELETE FROM Bed");
            getAllBedsSt = connection.prepareStatement("SELECT id, name, date_of_birth, is_soft, weight, count_of_sheets FROM Bed");
            getBedByNameSt = connection.prepareStatement("SELECT id, name, date_of_birth, is_soft, weight, count_of_sheets FROM Bed WHERE name=?");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public Bed findByName(String name) throws SQLException {
    	Bed b = new Bed();
    	try {
    		//connection.setAutoCommit(false);
    		getBedByNameSt.setString(1,name);
    		ResultSet resultSet = getBedByNameSt.executeQuery();

    		while (resultSet.next()) {
                b.setId(resultSet.getInt("id"));
                b.setSoft(resultSet.getBoolean("is_soft"));
                b.setName(resultSet.getString("name"));
                b.setDateOfBirth(resultSet.getString("date_of_birth"));
                b.setCountOfSheets(resultSet.getInt("count_of_sheets"));
                b.setWeight(resultSet.getDouble("weight"));
            }

    		
    		//connection.commit();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
    	return b;
    }
    
    public boolean addAllBeds(List<Bed> beds) throws SQLException {
    	try {
    		connection.setAutoCommit(false);
    		for(Bed bed: beds) {
    			addBedSt.setString(1, bed.getName());
            	addBedSt.setString(2, bed.getDateOfBirth());
            	addBedSt.setBoolean(3, bed.isSoft());
            	addBedSt.setDouble(4, bed.getWeight());
            	addBedSt.setInt(5, bed.getCountOfSheets());

                addBedSt.executeUpdate();
        	}
    		connection.commit();
    		return true;
		} catch (Exception e) {
			System.out.println("Wycofanie transakcji");
			try {
				connection.rollback();
			} catch (Exception e2) {
				System.out.println("Ratunku!");
			}
		}
    	
    	return false;
    }
    
    public int addBed(Bed bed) throws SQLException {
    	int count = 0;
        try {
        	addBedSt.setString(1, bed.getName());
        	addBedSt.setString(2, bed.getDateOfBirth());
        	addBedSt.setBoolean(3, bed.isSoft());
        	addBedSt.setDouble(4, bed.getWeight());
        	addBedSt.setInt(5, bed.getCountOfSheets());

            count = addBedSt.executeUpdate();

        } catch (SQLException e) {
        	System.out.println("Wycofanie transakcji addBed");
			try {
				connection.rollback();
			} catch (Exception e2) {
				System.out.println("Ratunku!");
			}
            //e.printStackTrace();
        }
        return count;
    }
    
    public List<Bed> getAllBeds(){
    	List<Bed> beds = new ArrayList<>();
        try {
            ResultSet resultSet = getAllBedsSt.executeQuery();

            while (resultSet.next()) {
                Bed b = new Bed();
                b.setId(resultSet.getInt("id"));
                b.setSoft(resultSet.getBoolean("is_soft"));
                b.setName(resultSet.getString("name"));
                b.setDateOfBirth(resultSet.getString("date_of_birth"));
                b.setCountOfSheets(resultSet.getInt("count_of_sheets"));
                b.setWeight(resultSet.getDouble("weight"));
                beds.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    	return beds;
    }

    public int deleteAllBeds(){
        int count = 0;
        try {
            count = deleteAllBedsSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public int deleteBed(Bed bed) throws SQLException {
    	int count = 0;
        try {
        	deleteBedByIdSt.setString(1, bed.getName());

            count = deleteBedByIdSt.executeUpdate();

        } catch (SQLException e) {
        	System.out.println("Wycofanie transakcji deleteBed");
			try {
				connection.rollback();
			} catch (Exception e2) {
				System.out.println("Ratunku!");
			}
            //e.printStackTrace();
        }
        return count;
    	
    }
}