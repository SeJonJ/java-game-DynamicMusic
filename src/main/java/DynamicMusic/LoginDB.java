package DynamicMusic;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDB extends DB_info {

    // DB 연결을 위한 statement, resultSet
    Statement stmt = null;
    ResultSet rs = null;
    String sql;

    // 사용자가 입력한 ID, PW
    private String loginID;
    private String loginPW;

    // DB에서 가져온 ID PW
    private String getID;
    private String getPW;
    private int getMemberCODE;

    public boolean loginStart(){
        try {
            conn = DriverManager.getConnection(getHjDB(), getUser(), getPasswd());
            sql = "SELECT * FROM MEMBER WHERE MID = '"+loginID+"' AND MPASSWD = '"+loginPW+"'";

            try {

                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();

                getID = rs.getString("MID");
                getPW = rs.getString("MPASSWD");
                getMemberCODE = rs.getInt("MEMBER_CODE");

//                System.out.println("현재 멤버 코드 : "+getMemberCODE);
//                System.out.println("현재 아이디 : "+getID);
//                System.out.println("현재 패스워드 : "+getPW);


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

    public void DataInsert(){
        int highScore;
        int highCombo;
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

    public String getGetID(){
        return getID;
    }

//    public static void main(String[] args) {
//        new LoginDB().loginStart();
//    }

}
