package DynamicMusic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class Test extends JFrame {
//    JPanel jpDF;

    Test(){


        setVisible(true);
        setBounds(150,150,800,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        String[] header = {"name", "score"};

        Object[][] contents = {
                {"User", "High Score"},
                {"super", 1110},
                {"admin", 1500},
                {"haha", 1200},
                {"user1", 1600},
                {"test", 1780}
        };

//        jpDF = new JPanel();
//        jpDF.setLayout(null);

        DefaultTableModel tableModel = new DefaultTableModel(contents, header);
        JTable jtb = new JTable(tableModel);
        jtb.setFont(new Font("Blackadder ITC",Font.BOLD, 40));
        jtb.setBounds(100,100,500,400);
        jtb.setBackground(Color.CYAN);
        jtb.setRowHeight(35);



//        jpDF.add(jtb);
//        add(jpDF);
        add(jtb);
    }

    public static void main(String[] args) {
//        // 시간에 따른 작업은 Timer, Timertask 를 통해서 가능
//        // TimerTask 를 통해서 작업을 run 메소드 안에 작업 정의 한 후
//        // timer.schedule 메소드에 매개변수를 2개 넣는데, task 와 task 를 실행할 시간
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("check");
//            }
//        };
//
//        timer.schedule(task, 1000);
        new Test();

    }
}
