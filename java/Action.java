import com.opensymphony.xwork2.ActionSupport;

import java.sql.*;
public class Action extends ActionSupport {
  Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

  PreparedStatement preparedStatement = connection.prepareStatement("select * from profiles");
  private String firstname = "";
  private String lastname = "";
  private int age = 0;
  private String profiles = "{ \"data\": [";

  public String getProfiles() {
    return profiles;
  }

  public void setProfiles(String profiles) {
    this.profiles = profiles;
  }

  public Action() throws SQLException {
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String addData() {
    try {
      PreparedStatement preparedStatement1 = connection.prepareStatement("insert into profiles values (?,?,?)");

      System.out.println(firstname + " " + lastname + " " + age);

      if (!firstname.equals("") && !lastname.equals("") && age != 0) {
        preparedStatement1.setString(1, firstname);
        preparedStatement1.setString(2, lastname);
        preparedStatement1.setInt(3, age);
      }
      preparedStatement1.execute();

      ResultSet resultSet = preparedStatement.executeQuery();

      int count = 0;
      while (resultSet.next()) {
//        System.out.println("*");

        profiles += ("[\"" + resultSet.getObject(1) + "\", \"" + resultSet.getObject(2) + "\", \"" + resultSet.getObject(3) + "\"],");
//        System.out.println(resultSet.getObject(1) + " " + resultSet.getObject(2) + " " + resultSet.getObject(3));
        count++;
      }
      if (count == 0) {
        profiles = "{\"data\": []}";
      } else {
        profiles = profiles.substring(0, profiles.length() - 1);
        profiles += "]}";
      }
      System.out.println("--->" + profiles);
    } catch (SQLException e) {
      System.out.println(e);
    }
    return "index";
  }

  public String fetchData() {
    try {
//      PreparedStatement preparedStatement = connection.prepareStatement("select * from profiles");

      ResultSet resultSet = preparedStatement.executeQuery();

      int count = 0;
      while (resultSet.next()) {
//        System.out.println("*");

        profiles += ("[\"" + resultSet.getObject(1) + "\", \"" + resultSet.getObject(2) + "\", \"" + resultSet.getObject(3) + "\"],");
//        System.out.println(resultSet.getObject(1) + " " + resultSet.getObject(2) + " " + resultSet.getObject(3));
        count++;
      }
      if (count == 0) {
        profiles = "{\"data\": []}";
      } else {
        profiles = profiles.substring(0, profiles.length() - 1);
        profiles += "]}";
      }
      System.out.println("->" + profiles);
    } catch (SQLException e) {
      System.out.println(e);
    }
    return "index";
  }
}

