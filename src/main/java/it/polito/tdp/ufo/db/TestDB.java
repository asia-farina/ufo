package it.polito.tdp.ufo.db;

import java.sql.*;
import java.util.LinkedList;

public class TestDB {

	public static void main(String[] args) {
		String jdbcURL="jdbc:mysql://localhost/ufo_sightings?user=root&password=Wideawake1234";
		try {
		     Connection conn=DriverManager.getConnection(jdbcURL);
		     String sql="SELECT DISTINCT shape "
			     		+ "FROM sighting "
			     		+ "WHERE shape <>'' "
			     		+ "ORDER BY shape ASC ";
		     PreparedStatement st=conn.prepareStatement(sql);
		     ResultSet res=st.executeQuery();
		     LinkedList <String> formeUFO=new LinkedList <String>();
		     while (res.next())
		     {
		    	 String forma=res.getString("shape");
		    	 formeUFO.add(forma);
		     }
		     st.close();
		     System.out.println(formeUFO);
		     String sql2="SELECT COUNT(*) AS cnt FROM sighting WHERE shape=?";
		     PreparedStatement st2=conn.prepareStatement(sql2);
		     String shapeScelta="circle";
		     st2.setString(1, shapeScelta);
		     ResultSet res2=st2.executeQuery();
		     res2.first();
		     int count=res2.getInt("cnt"); 
		     st2.close();
		     System.out.println ("Numero UFO di forma circle: "+count);
		     conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
