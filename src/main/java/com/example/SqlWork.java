package com.example;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;

public class SqlWork {

    
  public void doWork(DataSource ds)
  {
    String sql = "INSERT INTO TEST VALUES(" + new Random().nextInt() + ", 'Hello');";
    Connection con=null;
    try
    {
	    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	    //DriverManager.registerDriver(new io.opentracing.contrib.jdbc.TracingDriver());

      //Reference to connection interface
      con = ds.getConnection();

      Statement st = con.createStatement();
      int m = st.executeUpdate(sql);
      if (m == 1)
        System.out.println("inserted successfully : "+sql);
      else
        System.out.println("insertion failed");
      con.close();
    }
    catch(Exception ex)
    {
      System.err.println(ex);
    }
  }
}
