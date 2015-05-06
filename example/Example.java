import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import java.util.Date;

public class Example extends NanoHTTPD  { 

  public static String success = "Never tried."; 

  public Example() {

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
    Example httpServer = new Example();

    try {
      httpServer.start();
    } catch (IOException ioe) {
        System.err.println("Couldn't start server:\n" + ioe);
        System.exit(-1);
    }

    System.out.println("Type [CTRL]+[C] to quit!");

    connect();
  }

  public static void connect() throws InterruptedException {

    while (true) {
      Date date = new Date();

      Connection connection = null;

      try {
        DriverManager.setLoginTimeout(2);
        connection = DriverManager
          .getConnection("jdbc:" + System.getenv("EXAMPLE_DATABASE"), 
              System.getenv("EXAMPLE_USER"), System.getenv("EXAMPLE_ROOT_PASSWORD"));

      } catch (SQLException e) {
        success = date.toString() + ": Failed to make connection!";
      }

      if (connection != null) {
        success = date.toString() + ": Connection to database " + 
          System.getenv("EXAMPLE_DATABASE") + " established!";
      }

      System.out.println(success);
      Thread.sleep(5000);
    }
  }

  @Override public Response serve(IHTTPSession session) {

    Method method = session.getMethod();
    System.out.println(method + " '" + session.getUri() + "' ");

    String msg = success;

    return new NanoHTTPD.Response(success);
  }

}
