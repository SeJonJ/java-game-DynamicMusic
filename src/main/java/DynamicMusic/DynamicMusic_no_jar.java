//package DynamicMusic;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseMotionAdapter;
//
//public class DynamicMusic extends JFrame {
//
//    // 더블버퍼링 기술 사용 :
//    private Image screenImage;
//    private Graphics screenGraphic;
//
//    // ImageIcon 생성자를 이용해서 Main.class 에 있는 위치에서 리소스를 가져옴
//    // 이후 다시 getter 를 이용해서 해당 이미지를 IntroBackground에 넣어줌
//    private Image IntroBackground = new ImageIcon(Main.class.getResource("../images/intro_background.jpg_old")).getImage();
//
//    // memuBar 객체 안에 memuBar 이미지가 들어가게 됨
//    private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
//
//    // eixtButton 에 마우스를 올렸을때 이미지가 변하도록
//    private ImageIcon exitButtonImage = new ImageIcon(Main.class.getResource("../images/exit.png"));
//    private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exit_entered.png"));
//
//
//    // exitButton 생성
//    private JButton exitButton = new JButton(exitButtonImage);
//
//    // 윈도우 창 위치를 메뉴바를 끌어서 옮길 수 있도록
//    private int MouseX, MouseY;
//
//
//
//    public void start(){
//        setUndecorated(true); // 기본 메뉴바 삭제
//        setTitle("Dynamic Music");
//        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT );
//        setResizable(false); // 한번 창이 생성되면 임의적으로 창 크기 변경 불가
//        setLocationRelativeTo(null); // 창이 정중앙에 위치
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임 창을 종료 시 프로그램 전체 종료
//        setVisible(true); // 창 보이는 여부 => 반드시 true
//        setBackground(new Color(0,0,0,0)); // paintcomponent 했을때 배경이 회색이 아니라 휜색으로 됨
//        setLayout(null); // 버튼이나 JLabel 을 넣었을 때 그 위치 그대로 넣어짐
//
//        exitButton.setBounds(1245,0,30,30); // exitButton 의 위치와 크기
//        exitButton.setBorderPainted(false);
//        exitButton.setContentAreaFilled(false);
//        exitButton.setFocusPainted(false);
//        exitButton.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                exitButton.setIcon(exitButtonEnteredImage);
//                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            }
//            @Override
//            public void mouseExited(MouseEvent e){
//                exitButton.setIcon(exitButtonImage);
//                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//            }
//            @Override
//            public void mousePressed(MouseEvent e){
//                System.exit(0);
//            }
//        });
//        add(exitButton);
//
//
//        menuBar.setBounds(0,0,1280,30); // menuBar 의 위치와 크기
//        menuBar.addMouseListener(new MouseAdapter() {
//
//            @Override // 마우스 버튼을 눌렀을 때 발생하는 이벤트
//            public void mouseClicked(MouseEvent e) {
//                MouseX = e.getX();
//                MouseY = e.getY();
//            }
//        });
//
//        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
//
//            @Override // 마우스를 드래그 했을 때 발생하는 이벤트
//            public void mouseDragged(MouseEvent e) {
//                // 현재 스크린에서 마우스 위치 가져옴
//                MouseX = e.getXOnScreen();
//                MouseY = e.getYOnScreen();
//
//                setLocation(MouseX, MouseY);
//            }
//        });
//
//        add(menuBar); // JFrame 에 메뉴바 추가
//
//
//        Music Intromusic = new Music("introMusic_Joakim.mp3", true);
//        Intromusic.start();
//
//    }
//
//
//    // paint 메서드는 JFrame 에서 상속받아서 화면을 그릴때 가장 먼저 실행되는 함수
//    // 즉 아래 순서대로 실행된다고 생각하면 됨
//    // 1. JFrame 를 실행하면 updated(Graphics g) 가 가장 먼저 실행됨
//    // 2. 다음으로 paint(Graphics g) 가 실행됨
//    // 3. 이때 paint 함수 안에서 repaint() 를 실행함으로써 paint() -> repaint() -> paint() 식으로 반복됨
//    // 실행시켜보면 실제로 a -> c -> d -> b 순서로 반복됨
//    // 이렇게 화면을 계속 띄워주는 이유는 한번만 화면을 띄워주게 되면 버퍼링 현상이 심해서 느려지는 문제가 있음
//    // -> 이에 paint 와 repaint 를 통해 계속해서 introbackground 이미지를 띄워주게 됨
//    // 이런 기법을 더블 버퍼링 기법!! 이라고 함 : 버퍼에 이미지를 담아서 계속해서 갱신해줌
//   public void paint(Graphics g){
//        screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 윈도우 화면 크기 만큼 생성
//        screenGraphic = screenImage.getGraphics(); // screenImage 를 이용해서 그래픽 객체를 얻어옴
////        System.out.println("a");
//       // screenGraphic 에 그림을 그려주게됨 , 그래픽에 화면에 이미지를 그린다? 라는 느낌? => 이때 screenDraw 메서드를 통해 화면을 그림(Draw)
//        screenDraw(screenGraphic);
////        System.out.println("b");
//       // 0,0 인 이유는 이미 screenImage 가 이미 화면 크기 그대로 이기 때문에 0,0에 띄워주는 것
//        g.drawImage(screenImage, 0, 0, null); // 윈도우 창에 screenImage 를 뿌려줌
//    }
//
//    public void screenDraw(Graphics g){
////        System.out.println("c");
//        // drawImage 메서드를 Introbackground 를  x, y 좌표에 그려줌
//        g.drawImage(IntroBackground,0,0,null);
//
//        // paintComponents 는 이미지를 단순히 그려주는 것 이외에 JLabel 처럼 그림위에 그림을 그리는
//        // 즉 JFrame 위에 JLabel 을 추가해서 그리거나 하는 것을 의미
//        paintComponents(g);
//        this.repaint();
////        System.out.println("d");
//    }
//
//}
