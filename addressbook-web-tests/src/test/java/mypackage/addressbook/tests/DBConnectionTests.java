package mypackage.addressbook.tests;

import mypackage.addressbook.model.GroupData;
import mypackage.addressbook.model.Groups;
import org.testng.annotations.Test;

import java.sql.*;

public class DBConnectionTests {
    @Test
    public void testDBConnection() {
        Connection conn = null;
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3307/addressbook?serverTimezone=UTC&" +
                            "user=root&password=");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select group_id, group_name, group_header, group_footer " +
                    "from group_list");
            Groups groups = new Groups();
            while (rs.next()) {
                groups.add(new GroupData().withId(rs.getInt("group_id"))
                        .withName(rs.getString("group_name"))
                        .withHeader(rs.getString("group_header"))
                        .withFooter(rs.getString("group_footer")));
            }
            rs.close();
            statement.close();
            conn.close();
            System.out.println(groups);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
}
