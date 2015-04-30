import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import java.io.IOException;

public class JDBCExample  extends NanoHTTPD  { 

  public static String success = null;

  public JDBCExample() {
    super(8089);
  }

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
      success = "Failed to make connection!";
    }

    if (connection != null) {
      success = "You made it, take control your database now!";
    }

    System.out.println(success);

    JDBCExample server = new JDBCExample();
    try {
      server.start();
    } catch (IOException ioe) {
        System.err.println("Couldn't start server:\n" + ioe);
        System.exit(-1);
    }

    try {
      System.in.read();
    } catch (Throwable ignored) {
    }

    server.stop();
    System.out.println("Server stopped.\n");
  }

  @Override public Response serve(IHTTPSession session) {
        Method method = session.getMethod();
        System.out.println(method + " '" + session.getUri() + "' ");

        String msg = success;

        return new NanoHTTPD.Response(success);
    }

}
