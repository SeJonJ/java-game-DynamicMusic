package DynamicMusic;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDB extends DB_info {

    // DB 연결을 위한 statement, resultSet
    Statement stmt = null;
    ResultSet rs = null;
    String sql;

    private String loginID;
    private String loginPW;



    public boolean loginStart(){
        try {
            conn = DriverManager.getConnection(getUrl(), getUser(), getPasswd());
            sql = "SELECT * FROM MEMBER WHERE MID = '"+loginID+"' AND MPASSWD = '"+loginPW+"'";

            try {

                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();

                String getID = rs.getString("MID");
                String getPW = rs.getString("MPASSWD");


                if (loginID.equals(getID) && loginPW.equals(getPW)) {

                    System.out.println("로그인 정보 확인 로그인 완료");


                    return true;
                }

                return false;

            } catch (Exception e) {
                System.out.println("SQL 에러 발생 : "+e.getMessage());
                return false;
            }

        }catch (Exception e){
            System.out.println("DB conn 에러 : "+e.getMessage());
            return false;
        }
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getLoginPW() {
        return loginPW;
    }

    public void setLoginPW(String loginPW) {
        this.loginPW = loginPW;
    }


//    public static void main(String[] args) {
//        new LoginDB().loginStart();
//    }

}
