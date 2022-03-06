package DynamicMusic;

import javax.swing.*;
import java.awt.*;

// 스레들는 프로그램 안에 있는 작은 프로그램
public class Game extends Thread {
    // Main 메소드를 통해 게임이 실행되면 지금 이 클래스 - game - 에서
    // 만들어진 인스턴스 변수를 이용해서 게임 컨트롤 가능하도록
    // 즉 게임 클래스의 인스턴스가 생성되어 실행되면 아래 run() 메소드가 실행됨


    //노트 이미지
    private Image noteBasicImage = new ImageIcon(getClass().getResource("/menu_images/noteBasic.png")).getImage();

    // ingame 노트 경로별 경계선(라인)
    private Image noteRouteLine = new ImageIcon(getClass().getResource("/menu_images/noteRouteLine.png")).getImage();

    // ingame 음악 노트 판정 배경
    private Image judgementLineImage = new ImageIcon(getClass().getResource("/menu_images/judgementLine.png")).getImage();

    // Ingame 게임 정보 표시를 위한 이미지
    private Image gameInfoImage = new ImageIcon(getClass().getResource("/menu_images/gameInfo.png")).getImage();

    // ingame 노트 경로 배경
    private Image noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();

    // 각 버튼별 이미지
    // 아래 space 이미지가 2개인 이유는 다른 노트보다 길기 때문에 2개의 이미지를 하나로 합쳐서 길게 만들기 위함
    private Image A_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    private Image S_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    private Image D_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    private Image Space1_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    private Image Space2_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    private Image J_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    private Image K_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    private Image L_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();


    public void screeenDraw(Graphics2D g){
        // INGAME 노트 경로 배경, 노트 경로별 경계선(라인), 어떤키 사용하는지, 점수 등 => 총 7키
        // 뒷부분에 그려지는 이미지 - 코드 일수록 - 앞쪽으로 튀어나와서 그려지게 됨, 마치 모드처럼

        // ingame 시 게임 정보 표시를 위한 파란 줄
        g.drawImage(gameInfoImage, 0, 660, null);

        g.drawImage(noteRouteLine, 224, 30, null);
        g.drawImage(A_noteRouteImage, 228, 30, null);

        g.drawImage(noteRouteLine, 328, 30, null);
        g.drawImage(S_noteRouteImage, 332, 30, null);

        g.drawImage(noteRouteLine, 432, 30, null);
        g.drawImage(D_noteRouteImage, 436, 30, null);

        g.drawImage(noteRouteLine, 536, 30, null);
        g.drawImage(Space1_noteRouteImage, 540, 30, null);

        g.drawImage(noteRouteLine, 740, 30, null);
        g.drawImage(Space2_noteRouteImage, 640, 30, null);

        g.drawImage(noteRouteLine, 844, 30, null);
        g.drawImage(J_noteRouteImage, 744, 30, null);

        g.drawImage(noteRouteLine, 948, 30, null);
        g.drawImage(K_noteRouteImage, 848, 30, null);

        g.drawImage(noteRouteLine, 1052, 30, null);
        g.drawImage(L_noteRouteImage, 952, 30, null);

        g.drawImage(noteBasicImage, 228, 120, null);
        g.drawImage(noteBasicImage, 332, 580, null);
        g.drawImage(noteBasicImage, 436, 500, null);
        g.drawImage(noteBasicImage, 540, 340, null);
        g.drawImage(noteBasicImage, 640, 340, null);
        g.drawImage(noteBasicImage, 744, 325, null);
        g.drawImage(noteBasicImage, 848, 305, null);
        g.drawImage(noteBasicImage, 952, 305, null);

        // 글씨 색깔 설정
        g.setColor(Color.white);
        // Graphics2D 설정 : 이 설정을 통해서 TEXT에 ANTIALIASING 설정을 할 수 있고, 글씨가 좀더 뚜렷하게 보임
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


        // 폰트설정 : 아래 나오는 텍스트에 폰트 적용
        g.setFont(new Font("Arial",Font.BOLD,30));
        // ingame 에서 노래 제목 출력
        g.drawString("DAYBREAK FRONTLINE",20,702);
        // ingame 에서 노래 난이도 출력
        g.drawString("EASY",1190,700);
        // 게임 점수 출력
        g.drawString("000000",565,702);

        // 폰트 설정2: 아래 나오는 텍스트에 폰트 적용용
        g.setFont(new Font("Elephant",Font.BOLD,30));

        // ingame 키패드 확인 출력
        g.drawString("A",270,609);
        g.drawString("S",374,609);
        g.drawString("D",470,609);
        g.drawString("SPACE",580,609);
        g.drawString("J",784,609);
        g.drawString("K",889,609);
        g.drawString("L",993,609);


        // 음악 노트판정을 위한 배경
        g.drawImage(judgementLineImage, 0, 580, null);
    }

    // press 메서드 : 해당 버튼을 눌렀을 때 이미지변경
    // release 메서드 : 해당 버튼에서 손을 뗐을 때 이미지 변경
    public void pressA(){
        A_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoutePressed.png")).getImage();
        // 키보드 눌렀을 때 음악
        new Music("drumSmall1.mp3", false, "menu").start();

    }
    public void releaseA(){
        A_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    }

    public void pressS(){
        S_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoutePressed.png")).getImage();
        new Music("drumSmall22.mp3", false, "menu").start();
    }
    public void releaseS(){
        S_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    }

    public void pressD(){
        D_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoutePressed.png")).getImage();
        new Music("drumSmall33.mp3", false, "menu").start();
    }
    public void releaseD(){
        D_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    }

    public void pressSpace(){
        Space1_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoutePressed.png")).getImage();
        Space2_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoutePressed.png")).getImage();

        new Music("drumBig1.mp3", false, "menu").start();

    }
    public void releaseSpace(){
        Space1_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
        Space2_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    }

    public void pressJ(){
        J_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoutePressed.png")).getImage();
        new Music("drumSmall33.mp3", false, "menu").start();
    }
    public void releaseJ(){
        J_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    }

    public void pressK(){
        K_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoutePressed.png")).getImage();
        new Music("drumSmall22.mp3", false, "menu").start();
    }
    public void releaseK(){
        K_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    }

    public void pressL(){
        L_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoutePressed.png")).getImage();
        new Music("drumSmall1.mp3", false, "menu").start();
    }
    public void releaseL(){
        L_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    }

    @Override
    public void run(){

    }
}
