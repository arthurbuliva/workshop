/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author bulivaa
 */
public class Workshop
{

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        Connection connection = DriverManager.getConnection("jdbc:mysql://weblab.unon.org:3306/unon_intranet?"
                + "verifyServerCertificate=false"
                + "&useSSL=true"
                + "&requireSSL=true",
                "ssluser", "pass");
        PreparedStatement gre = connection.prepareStatement("SELECT VERSION() AS version");

        ResultSet rs = gre.executeQuery();

        while (rs.next())
        {
            System.out.println("Version " + rs.getString("version"));
        }

        connection.close();
    }

}
