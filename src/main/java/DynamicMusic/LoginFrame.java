package DynamicMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    // DB 접속 클래스
    LoginDB loginDB = new LoginDB();
    // DynamicMusic 클래스
    DynamicMusic dynamicMusic = new DynamicMusic(loginDB);

    JButton jbtnLogin;
    JButton jbtnRegister;

    TextField tfID;
    TextField tfPW;
    Label lb;

    private boolean loginResult;

    public void loginWindow(){
        setTitle("Login");
        setSize(600,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        jbtnLogin = new JButton("login");
        jbtnLogin.setBounds(150,175,100,50);
        jbtnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                loginDB.setLoginID(tfID.getText());
                loginDB.setLoginPW(tfPW.getText());

                if (loginDB.loginStart()){

                    // 한개의 창만 닫기 : 현재 창만 닫음
                    dispose();

                    // DynamicMusic 게임 시작
                    dynamicMusic.musicStart();

                }else{
                    System.out.println("로그인 실패");
                    JOptionPane.showConfirmDialog(lb,"로그인 정보를 확인해주세요","로그인 에러",JOptionPane.DEFAULT_OPTION);

                }
            }
        });
        add(jbtnLogin);

        jbtnRegister = new JButton("회원가입");
        jbtnRegister.setBounds(300,175,100,50);
        add(jbtnRegister);

        tfID = new TextField();
        tfID.setBounds(230,50,100,20);
        add(tfID);

        tfPW = new TextField();
        tfPW.setBounds(230,100,100,20);
        tfPW.setEchoChar('*');
        add(tfPW);


        lb = new Label("ID");
        lb.setBounds(180,50,50,20);
        add(lb);

        lb = new Label("Password");
        lb.setBounds(150,100,60,20);
        add(lb);

    }


}
