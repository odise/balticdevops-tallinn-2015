import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import java.io.*;
import javax.xml.ws.*;
import javax.xml.ws.http.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;


@WebServiceProvider
@ServiceMode(value = Service.Mode.PAYLOAD)
public class JDBCExample implements Provider<Source> { 

  public static String success = null;

  public static void main(String[] argv) throws InterruptedException {

    System.out.println("-------- MySQL JDBC Connection Testing ------------");

    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("Where is your MySQL JDBC Driver?");
      e.printStackTrace();
      return;
    }

    System.out.println("MySQL JDBC Driver Registered!");
    Connection connection = null;

    try {
      connection = DriverManager
      .getConnection("jdbc:mysql://db:3306/test","root", "password");

    } catch (SQLException e) {
      System.out.println("Connection Failed! Check output console");
      e.printStackTrace();
      //success = "Connection Failed! Check output console";
      //return;
    }

    if (connection != null) {
      System.out.println("You made it, take control your database now!");
      success = "<p>You made it, take control your database now!</p>";
    } else {
      System.out.println("Failed to make connection!");
      success = "<p>Failed to make connection!</p>";
    }

    String address = "http://127.0.0.1:8080/";
    Endpoint.create(HTTPBinding.HTTP_BINDING, new JDBCExample()).publish(address);

    System.out.println("Service running at " + address);
    System.out.println("Type [CTRL]+[C] to quit!");

    Thread.sleep(Long.MAX_VALUE);

  }

  public Source invoke(Source request) {
    return  new StreamSource(new StringReader(success));
  }
  
}
