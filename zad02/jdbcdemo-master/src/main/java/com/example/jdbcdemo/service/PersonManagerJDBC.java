package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcdemo.domain.Person;

public class PersonManagerJDBC implements PersonManager {

	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTablePerson = "CREATE TABLE Person(id bigint GENERATED BY DEFAULT AS IDENTITY, name varchar(20) UNIQUE, yob integer)";

	private PreparedStatement addPersonStmt;
	private PreparedStatement deleteAllPersonsStmt;
	private PreparedStatement getAllPersonsStmt;

	private Statement statement;

	public PersonManagerJDBC() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Person".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTablePerson);

			addPersonStmt = connection.prepareStatement("INSERT INTO Person (name, yob) VALUES (?, ?)");
			deleteAllPersonsStmt = connection.prepareStatement("DELETE FROM Person");
			getAllPersonsStmt = connection.prepareStatement("SELECT id, name, yob FROM Person");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}

	void clearPersons() {
		try {
			deleteAllPersonsStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addPerson(Person person) {
		int count = 0;
		try {
			addPersonStmt.setString(1, person.getName());
			addPersonStmt.setInt(2, person.getYob());

			count = addPersonStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Person> getAllPersons() {
		List<Person> persons = new ArrayList<Person>();

		try {
			ResultSet rs = getAllPersonsStmt.executeQuery();

			while (rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setYob(rs.getInt("yob"));
				persons.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persons;
	}

	@Override
	public void addAllPersons(List<Person> persons) {

		try {
			connection.setAutoCommit(false);
			for (Person person : persons) {
				addPersonStmt.setString(1, person.getName());
				addPersonStmt.setInt(2, person.getYob());
				addPersonStmt.executeUpdate();
			}
			connection.commit();
			
		} catch (SQLException exception) {
			
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
				//!!!! ALARM
			}
		}

	}

}
