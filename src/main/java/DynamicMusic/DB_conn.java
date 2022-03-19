package DynamicMusic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DB_conn {



    public static void main(String[] args) throws Exception {

        Connection conn = null;
        String url = "jdbc:mysql://210.220.67.85:3361";
        String id = "jsj";
        String passwd = "639258";
        ResultSet rs;

        conn = DriverManager.getConnection(url, id, passwd);

        System.out.println(conn);


    }

}
