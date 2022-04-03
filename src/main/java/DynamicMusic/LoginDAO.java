package DynamicMusic;

import java.sql.*;

public class LoginDAO extends DB_info {

    // DB 연결을 위한 statement, resultSet
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql;

    // 사용자가 입력한 ID, PW
    private String loginID;
    private String loginPW;

    // DB에서 가져온 ID PW
    private int getMemberCODE;
    private String getID;
    private String getPW;


    public LoginDAO() {

        try {
            conn = DriverManager.getConnection(getHjDB(), getUser(), getPasswd());
        } catch (SQLException e) {
            System.out.println("DB conn 에러 : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean loginStart() {
        sql = "SELECT MEMBER_CODE, MID, MPASSWD FROM MEMBER WHERE MID = ? AND MPASSWD = ?";


        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, loginID);
            pstmt.setString(2, loginPW);

            rs = pstmt.executeQuery();

//                System.out.println("현재 멤버 코드 : "+getMemberCODE);
//                System.out.println("현재 아이디 : "+getID);
//                System.out.println("현재 패스워드 : "+getPW);


            if (rs.next()) {
                getMemberCODE = rs.getInt("MEMBER_CODE");
                getID = rs.getString("MID");

                System.out.println("로그인 정보 확인 로그인 완료");

                return true;
            }

            return false;

        } catch (Exception e) {
            System.out.println("SQL 에러 발생 : " + e.getMessage());

            return false;
        }

    }


    public void scoreUpdate(int highscore, int highcombo, String musicName) {

//        System.out.println("최고 스코어 : "+highscore);
//        System.out.println("최고 콤보 : "+highcombo);

        try{

            if (musicName.equals("DAYBREAK FRONTLINE")) {
                sql = "UPDATE DMUSIC SET DF_SCORE = ?, DF_COMBO = ? WHERE MEMBER_CODE = ?";


                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, highscore);
                pstmt.setInt(2, highcombo);
//                pstmt.setInt(3, getMemberCODE);
                pstmt.setInt(3, getMemberCODE);


                pstmt.executeUpdate();
//                conn.commit();
                System.out.println("점수 입력 완료");

            } else if (musicName.equals("Lose Yourself - Eminem")) {
                sql = "UPDATE DMUSIC SET LYS_SCORE = ?, LYS_COMBO = ? WHERE MEMBER_CODE = ?";


                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, highscore);
                pstmt.setInt(2, highcombo);
                pstmt.setInt(3, getMemberCODE);

                pstmt.executeUpdate();
//                conn.commit();
            } else if (musicName.equals("TheFatRat - The Calling")) {
                sql = "UPDATE DMUSIC SET TFR_SCORE = ?, TFR_COMBO = ? WHERE MEMBER_CODE = ?";


                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, highscore);
                pstmt.setInt(2, highcombo);
                pstmt.setInt(3, getMemberCODE);

                pstmt.executeUpdate();
//                conn.commit();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    private void closeDB() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("자원 반납 완료");
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

    public String getGetID() {
        return getID;
    }

//    public static void main(String[] args) {
//        LoginDAO dao = new LoginDAO();
//        dao.scoreUpdate(50,50,"DAYBREAK FRONTLINE");
//
//    }

}
