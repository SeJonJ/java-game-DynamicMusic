package DynamicMusic;

import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<UserVO> scorePanel(String musicName){
        ArrayList<UserVO> list = new ArrayList<UserVO>();

        try {
            // 노래에 맞춰 SQL 변경
            if(musicName.equals("DAYBREAK FRONTLINE")) {

                sql = "SELECT M.MNAME AS NAME, D.DF_SCORE AS SCORE FROM MEMBER M NATURAL JOIN DMUSIC D ORDER BY DF_SCORE DESC LIMIT 5";

            }else if(musicName.equals("Lose Yourself - Eminem")){

                sql = "SELECT M.MNAME AS NAME, D.LYS_SCORE AS SCORE FROM MEMBER M NATURAL JOIN DMUSIC D ORDER BY LYS_SCORE DESC LIMIT 5";

            }else if(musicName.equals("TheFatRat - The Calling")){

                sql = "SELECT M.MNAME AS NAME, D.TFR_SCORE AS SCORE FROM MEMBER M NATURAL JOIN DMUSIC D ORDER BY TFR_SCORE DESC LIMIT 5";
            }

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
//                rs.next();
//                System.out.println(rs.getString("D.MEMBER_CODE"));

            while (rs.next()) {
                String name = rs.getString("NAME");
                int score = rs.getInt("SCORE");

                list.add(new UserVO(name, score));
//                System.out.println(name+" : "+score);
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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

    public static void main(String[] args) {
        LoginDAO dao = new LoginDAO();
//        dao.scoreUpdate(50,50,"DAYBREAK FRONTLINE");

        ArrayList<UserVO> list = dao.scorePanel("TheFatRat - The Calling");
        for(UserVO user : list){
            System.out.println(user.getName() + " : "+user.getScoreTFR());
        }

    }

}
