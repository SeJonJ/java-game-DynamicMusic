package DynamicMusic;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class DynamicMusic extends JFrame {

    // 더블버퍼링 기술 사용
    private Image screenImage;
    private Graphics screenGraphic;

    // ImageIcon 생성자를 이용해서 Main.class 에 있는 위치에서 리소스를 가져옴
    // 이후 다시 getter 를 이용해서 해당 이미지를 IntroBackground에 넣어줌
    // jar 파일로 만들때는 getClass.getResource 를 사용한다!

    // Background 객체는 이후 아래에서 화면이 전환될때 이곳에 다른 사진을 넣어서 배경경 사진 바뀔 수 있도록 함
    // 시작화면 background
    private Image Background = new ImageIcon(getClass().getResource("/menu_images/intro_background.jpg")).getImage();

    // memuBar 객체 안에 memuBar 이미지가 들어가게 됨
    private JLabel menuBar = new JLabel(new ImageIcon(getClass().getResource("/menu_images/menuBar.png")));

    // 오른쪽 위 eixtButton 의 기본 이미지와 버튼에 마우스를 올렸을때 이미지가 변하도록
    private ImageIcon exitButtonImage = new ImageIcon(getClass().getResource("/menu_images/exit.png"));
    private ImageIcon exitButtonEnteredImage = new ImageIcon(getClass().getResource("/menu_images/exit_entered.png"));


    // 시작버튼과 종료버튼 생성 startButtonEntered
    private ImageIcon startButtonBasic = new ImageIcon(getClass().getResource("/menu_images/startButtonBasic.png"));
    private ImageIcon startButtonEntered = new ImageIcon(getClass().getResource("/menu_images/startButtonEntered.png"));

    private ImageIcon quitButtonBasic = new ImageIcon(getClass().getResource("/menu_images/quitButtonBasic.png"));
    private ImageIcon quitButtonEntered = new ImageIcon(getClass().getResource("/menu_images/quitButtonEntered.png"));

    // 게임 화면에서 곡을 선택을 위한 화살표 버튼 이미지
    private ImageIcon leftButtonBasic = new ImageIcon(getClass().getResource("/menu_images/leftBasic.png"));
    private ImageIcon leftButtonEntered = new ImageIcon(getClass().getResource("/menu_images/leftEntered.png"));

    private ImageIcon rightButtonBasic = new ImageIcon(getClass().getResource("/menu_images/rightBasic.png"));
    private ImageIcon rightButtonEntered = new ImageIcon(getClass().getResource("/menu_images/rightEntered.png"));

    // 게임 난이도 설정 버튼
    private ImageIcon easyButtonBasic = new ImageIcon(getClass().getResource("/menu_images/easyButtonBasic.png"));
    private ImageIcon easyButtonEntered = new ImageIcon(getClass().getResource("/menu_images/easyButtonEntered.png"));
    private ImageIcon hardButtonBasic = new ImageIcon(getClass().getResource("/menu_images/hardButtonBasic.png"));
    private ImageIcon hardButtonEntered = new ImageIcon(getClass().getResource("/menu_images/hardButtonEntered.png"));

    // backButton : 메인 화면으로 돌아가는 버튼
    private ImageIcon backButtonBasic = new ImageIcon(getClass().getResource("/menu_images/backButtonBasic.png"));
    private ImageIcon backButtonEntered = new ImageIcon(getClass().getResource("/menu_images/backButtonEntered.png"));

    // 노트 찍기 모드
    private ImageIcon noteWriteMod = new ImageIcon(getClass().getResource("/menu_images/noteWirteMod.png"));


    // Button 생성
    private JButton exitButton = new JButton(exitButtonImage);
    private JButton startButton = new JButton(startButtonBasic);
    private JButton quitButton = new JButton(quitButtonBasic);
    private JButton leftButton = new JButton(leftButtonBasic);
    private JButton rightButton = new JButton(rightButtonBasic);
    private JButton easyButton = new JButton(easyButtonBasic);
    private JButton hardButton = new JButton(hardButtonBasic);
    private JButton backButton = new JButton(backButtonBasic);
    private JButton noteWriteButton = new JButton(noteWriteMod);



    // 윈도우 창 위치를 메뉴바를 끌어서 옮길 수 있도록 마우스 좌표 int
    private int MouseX, MouseY;

    // 게임에 맞춰 화면을 표시하기 위한 변수
    private boolean isMainScreen = false;

    // Ingame 으로 넘어왔는지 확인하기 위한 변수수
   private boolean isGameScreen = false;

    // ArrayList 어떠한 변수를 담을 수 있는 이미 만들어진 배열? => 하나의 음악의 정보를 배열로 담음
    ArrayList<Track> trackList = new ArrayList<Track>();

    // trackList 안에 있는 값에 따라서 아래 변수들의 값이 달라짐
    // nowSelected 값은 현재 선택된 트랙의 번호이자 arraylist 의 index 를 의미한다.
    private int nowSelected = 0;
    private Image selectedImage;
    private Image titleImage;
    private Music selectedMusic;

    // 인트로 음악 정의
    private Music Intromusic = new Music("Intro_Elektronomia_Energy.mp3", true, "menu");

    // Game 인스턴스 생성 && 초기화 :
    // 이때 game 변수는 단 하나의 게임만 진행가능하며 game 변수자체가 프로젝트 전체에서 사용되어야하기 때문에
    // static 으로 만들어줌
    public static Game game;

    public void start() {
        // trackList 에 track 정보 넣기
        trackList.add(new Track("DAYBREAK_FRONTLINE_title.png",
                "DAYBREAK_FRONTLINE_menu.jpg",
                "DAYBREAK_FRONTLINE_ingame.jpg",
                "DAYBREAK_FRONTLINE_selected.mp3",
                "DAYBREAK_FRONTLINE.mp3",
                "DAYBREAK FRONTLINE"));

        trackList.add(new Track("Eminem_Lose_Yourself_title.jpg",
                "Eminem_Lose_Yourself_menu.jpg",
                "Eminem_Lose_Yourself_ingame.jpg",
                "Eminem_Lose_Yourself_selected.mp3",
                "Eminem_Lose_Yourself.mp3",
                "Lose Yourself - Eminem"));

        trackList.add(new Track("TheFatRat - The Calling_title.png",
                "TheFatRat - The Calling_menu.jpg",
                "TheFatRat - The Calling_ingame.jpg",
                "TheFatRat - The Calling_selected.mp3",
                "TheFatRat - The Calling.mp3",
                "TheFatRat - The Calling"));

        setUndecorated(true); // 기본 메뉴바 삭제
        setTitle("Dynamic Music");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false); // 한번 창이 생성되면 임의적으로 창 크기 변경 불가
        setLocationRelativeTo(null); // 창이 정중앙에 위치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임 창을 종료 시 프로그램 전체 종료
        setVisible(true); // 창 보이는 여부 => 반드시 true
        setBackground(new Color(0, 0, 0, 0)); // paintcomponent 했을때 배경이 회색이 아니라 휜색으로 됨
        setLayout(null); // 버튼이나 JLabel 을 넣었을 때 그 위치 그대로 넣어짐

        // add해서 KeyListener 에 내가 만든 KeyListen 인식
        addKeyListener(new KeyListener());

        // 게임 시작시 인트로 음악 재생
       Intromusic.start();

        // exitButton
        exitButton.setBounds(1245, 0, 30, 30); // 왼쪽부터 x, y , 길이, 높이
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);

        // 아래처럼 추상 클래스, 인터페이스등을 상속, 구현하는 클래스를 직접 생성한는 것 대신
        // { ~~~ } 를 통해 생성하는 것을 익명 클래스라고 함
        // 즉 new MouseAdapter() 뒤에 부분은 익명클래스로 MouseAdapter() 를 구현한 클래스로 취급됨
        exitButton.addMouseListener(new MouseAdapter() {
            @Override // 버튼에 마무스 올렸을 때 이벤트
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(exitButtonEnteredImage);
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override // 버튼에 마우스 뗐을 때 이벤트
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(exitButtonImage);
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override // 마우스 눌렀을 때 이벤트
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });

        // 게임 시작 버튼
        startButton.setBounds(800, 200, 400, 100);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new MouseAdapter() {
            @Override // 버튼에 마무스 올렸을 때 이벤트
            public void mouseEntered(MouseEvent e) {
                startButton.setIcon(startButtonEntered);
                startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override // 버튼에 마우스 뗐을 때 이벤트
            public void mouseExited(MouseEvent e) {
                startButton.setIcon(startButtonBasic);
                startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override // 마우스 눌렀을 때 이벤트 => 즉 게임 시작버튼 눌렀을 때의 이벤트
            public void mousePressed(MouseEvent e) {
                // 게임 메뉴 시작
                game_menu();
            }
        });

        // 게임 종료 버튼
        quitButton.setBounds(800, 330, 400, 100);
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setFocusPainted(false);
        quitButton.addMouseListener(new MouseAdapter() {
            @Override // 버튼에 마우스 올렸을 때 이벤트
            public void mouseEntered(MouseEvent e) {
                quitButton.setIcon(quitButtonEntered);
                quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override // 버튼에 마우스 뗐을 때 이벤트
            public void mouseExited(MouseEvent e) {
                quitButton.setIcon(quitButtonBasic);
                quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override // 마우스 눌렀을 때 이벤트
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });

        // 왼쪽 이동 버튼
        leftButton.setVisible(false);
        leftButton.setBounds(140, 310, 60, 60);
        leftButton.setBorderPainted(false);
        leftButton.setContentAreaFilled(false);
        leftButton.setFocusPainted(false);
        leftButton.addMouseListener(new MouseAdapter() {
            @Override // 버튼에 마무스 올렸을 때 이벤트
            public void mouseEntered(MouseEvent e) {
                leftButton.setIcon(leftButtonEntered);
                leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override // 버튼에 마우스 뗐을 때 이벤트
            public void mouseExited(MouseEvent e) {
                leftButton.setIcon(leftButtonBasic);
                leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override // 마우스 눌렀을 때 이벤트
            public void mousePressed(MouseEvent e) {
                selectLelft();
            }
        });

        // 오른쪽 이동 버튼 : 시작햇을 때는 왼쪽, 오른쪽 버튼은 보일 필요가 없음 =>setVisble(false)
        rightButton.setVisible(false);
        rightButton.setBounds(1040, 310, 60, 60);
        rightButton.setBorderPainted(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setFocusPainted(false);
        rightButton.addMouseListener(new MouseAdapter() {
            @Override // 버튼에 마무스 올렸을 때 이벤트
            public void mouseEntered(MouseEvent e) {
                rightButton.setIcon(rightButtonEntered);
                rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override // 버튼에 마우스 뗐을 때 이벤트
            public void mouseExited(MouseEvent e) {
                rightButton.setIcon(rightButtonBasic);
                rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override // 마우스 눌렀을 때 이벤트
            public void mousePressed(MouseEvent e) {
                selectRight();
            }
        });

        // easy 난이도 버튼
        // 시작화면에서는 난이도버튼 보일 필요 없음
        easyButton.setVisible(false);
        easyButton.setBounds(375, 580, 250, 67);
        easyButton.setBorderPainted(false);
        easyButton.setContentAreaFilled(false);
        easyButton.setFocusPainted(false);
        easyButton.addMouseListener(new MouseAdapter() {
            @Override // 버튼에 마무스 올렸을 때 이벤트
            public void mouseEntered(MouseEvent e) {
                easyButton.setIcon(easyButtonBasic);
                easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override // 버튼에 마우스 뗐을 때 이벤트
            public void mouseExited(MouseEvent e) {
                easyButton.setIcon(easyButtonEntered);
                easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override // 마우스 눌렀을 때 이벤트
            public void mousePressed(MouseEvent e) {
                // 쉬움 난이도 이벤트
                gameStart(nowSelected, "Easy");
            }
        });

        // hard 난이도 버튼
        hardButton.setVisible(false);
        hardButton.setBounds(655, 580, 250, 67);
//        hardButton.setBounds(900, 350, 250, 67);
        hardButton.setBorderPainted(false);
        hardButton.setContentAreaFilled(false);
        hardButton.setFocusPainted(false);
        hardButton.addMouseListener(new MouseAdapter() {
            @Override // 버튼에 마무스 올렸을 때 이벤트
            public void mouseEntered(MouseEvent e) {
                hardButton.setIcon(hardButtonBasic);
                hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override // 버튼에 마우스 뗐을 때 이벤트
            public void mouseExited(MouseEvent e) {
                hardButton.setIcon(hardButtonEntered);
                hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override // 마우스 눌렀을 때 이벤트
            public void mousePressed(MouseEvent e) {
                // 어려움 난이도 이벤트
                gameStart(nowSelected, "Hard");
            }
        });

        // back 버튼
        backButton.setVisible(false);
        backButton.setBounds(20,50,60,60);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addMouseListener(new MouseAdapter() {
            @Override // 버튼에 마무스 올렸을 때 이벤트
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(backButtonBasic);
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override // 버튼에 마우스 뗐을 때 이벤트
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(backButtonEntered);
                backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override // 마우스 눌렀을 때 이벤트
            public void mousePressed(MouseEvent e) {
                // 메인 화면으로 돌아가는 이벤트
                bakMain();
            }
        });

        // 노트 모드 시 찍기 버튼
        noteWriteButton.setVisible(false);
        noteWriteButton.setBounds(1070,150,200,70);

        // 화면에 버튼 컴포넌트 추가
        add(exitButton);
        add(quitButton);
        add(startButton);
        add(rightButton);
        add(leftButton);
        add(easyButton);
        add(hardButton);
        add(backButton);
        add(noteWriteButton);

        menuBar.setBounds(0, 0, 1280, 30); // menuBar 의 위치와 크기
        menuBar.addMouseListener(new MouseAdapter() {

            @Override // 마우스 버튼을 눌렀을 때 발생하는 이벤트
            public void mouseClicked(MouseEvent e) {
                MouseX = e.getX();
                MouseY = e.getY();
            }
        });

        menuBar.addMouseMotionListener(new MouseMotionAdapter() {

            @Override // 마우스를 드래그 했을 때 발생하는 이벤트
            public void mouseDragged(MouseEvent e) {
                // 현재 스크린에서 마우스 위치 가져옴
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();

                // 드래그할때 그 순간순간마다 x좌표와 y 좌표를 얻어와서 JFrame 위치를 바꿔줌
                setLocation(x - MouseX, y - MouseY);
            }
        });

        add(menuBar); // JFrame 에 메뉴바 추가

    }

    // paint 메서드는 JFrame 에서 상속받아서 화면을 그릴때 가장 먼저 실행되는 함수
    // 즉 아래 순서대로 실행된다고 생각하면 됨
    // 1. JFrame 를 실행하면 updated(Graphics g) 가 가장 먼저 실행됨
    // 2. 다음으로 paint(Graphics g) 가 실행됨
    // 3. 이때 paint 함수 안에서 repaint() 를 실행함으로써 paint() -> repaint() -> paint() 식으로 반복됨
    // 실행시켜보면 실제로 a -> c -> d -> b 순서로 반복됨
    // 이렇게 화면을 계속 띄워주는 이유는 한번만 화면을 띄워주게 되면 버퍼링 현상이 심해서 느려지는 문제가 있음
    // -> 이에 paint 와 repaint 를 통해 계속해서 introbackground 이미지를 띄워주게 됨
    // 이런 기법을 더블 버퍼링 기법!! 이라고 함 : 버퍼에 이미지를 담아서 계속해서 갱신해줌
    public void paint(Graphics g) {
        screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 윈도우 화면 크기 만큼 생성
        screenGraphic = screenImage.getGraphics(); // screenImage 를 이용해서 그래픽 객체를 얻어옴
//        System.out.println("a");
        // screenGraphic 에 그림을 그려주게됨 , 그래픽에 화면에 이미지를 그린다? 라는 느낌? => 이때 screenDraw 메서드를 통해 화면을 그림(Draw)
        screenDraw((Graphics2D) screenGraphic); // 그래픽스 2D 로 형변환
//        System.out.println("b");
        // 0,0 인 이유는 이미 screenImage 가 이미 화면 크기 그대로 이기 때문에 0,0에 띄워주는 것
        g.drawImage(screenImage, 0, 0, null); // 윈도우 창에 screenImage 를 뿌려줌
    }

    public void screenDraw(Graphics2D g) { // 매개변수를 Graphics 에서 Graphics2D 로 변경
//        System.out.println("c");
        // drawImage 메서드를 Introbackground 를  x, y 좌표에 그려줌
        // g.drawImage 부분은 paintComponents 처럼 화면에 추가된 요소를 그려주는 것이 아닌 단순히
        // 이미지를 그림 그리는 것
        g.drawImage(Background, 0, 0, null);

        // isMainScreen = true 면 selectedImage 를 보여줌
        if (isMainScreen) {
            g.drawImage(selectedImage, 350, 100, null);
            g.drawImage(titleImage, 340, 85, null);
        }

        // isGameScreen = true 인게임 화면에서의 그래픽
        // ingame 에 관한 그래픽 내용은 Game 클래스에서 관리
        if(isGameScreen){
            game.screeenDraw(g);
        }

        // paintComponents 는 이미지를 단순히 그려주는 것 이외에 JLabel 처럼 추가된 요소를 그리는 것
        // 즉 JFrame 위에 button 이나 라벨처럼 add() 된 부분에 대한 것
        paintComponents(g);
        try{
            Thread.sleep(5);
        }catch(Exception e){
            e.printStackTrace();
        }
        this.repaint();
//        System.out.println("d");
    }

    // 현재 선택된 곡의 번호를 넣어줌으로써 해당 곡이 선택됨을 알림
    public void selectTrack(int nowSelected) {
        // 선택한 곡이 null 이 아니면 , 즉 어떠한 곡이라도 하나가 실행되고 있다면 해당 음악을 종료
        if (selectedMusic != null) {
            selectedMusic.close();
        }

        // 현재 선택된 곡이 갖고 있는 noewSelected 번호를 갖고 아래의 각 정보를 가져옴
        // 예를 들어서 arraylist 의 index 가 1이면 1에 해당하는 title, start, music 를 가져와서 뿌려줌
        titleImage = new ImageIcon(getClass().getResource("/game_images/" +
                trackList.get(nowSelected).getTitleImage())).getImage();
        selectedImage = new ImageIcon(getClass().getResource("/game_images/" +
                trackList.get(nowSelected).getMenuImage())).getImage();


        selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true, "game");
        selectedMusic.start();
    }

    public void game_menu(){
        // 시작, 종료버튼 없애기
        startButton.setVisible(false);
        quitButton.setVisible(false);

        // 여기는 게임 메인 화면에 들어갔을 때 배경화면
        Background = new ImageIcon(getClass().getResource("/menu_images/main_Bakground.jpg")).getImage();

        // 게임 시작을 누르면 isMainScreen 을 true 로
        isMainScreen = true;

        //  left 와 right 버튼이 보이기
        leftButton.setVisible(true);
        rightButton.setVisible(true);

        // 난이도 버튼 표시
        easyButton.setVisible(true);
        hardButton.setVisible(true);

        // nowselected 번째 index 재생
        selectTrack(0);

        // 인트로 음악 종료
        Intromusic.close();
    }

    // 왼쪽 버튼 메서드
    public void selectLelft() {
        // 0번째 곡일때는 전체 trackList 크기에서 -1 한다.
        // 이는 0번째 곡일때 왼쪽을 누르면 track 에 있는 마지막 곡이 나오게 됨
        if (nowSelected == 0) {
            nowSelected = trackList.size() - 1;
        } else { // 가장 왼쪽 아닐때는 현재 nowSelected 에서 -1
            nowSelected--;
        }
        selectTrack(nowSelected);
    }

    // 오른쪽 버튼 메서드
    public void selectRight() {
        // 현재 곡이 track 의 가장 오른쪽에, 즉 마지막에 있는 곡이라면
        // 가장 처음으로 돌아가도록
        if (nowSelected == trackList.size() - 1) {
            nowSelected = 0;
        } else { // 가장 오른쪽이 아닌 경우는 +1
            nowSelected++;
        }
        selectTrack(nowSelected);
    }

    public void gameStart(int nowSelected, String difficulty) {
        // 현재 재생되고 있는 음악이 있다면 음악 종료
        if (selectedMusic != null) {
            selectedMusic.close();
        }

        // 메인 스크린을 false 로 => 이렇게되면 screenDraw 함수에서 isMainScreen 부분을 멈추게됨
        isMainScreen = false;

        // Ingame 전환 확인
        isGameScreen = true;

        // 게임 시작 시 해당 선택된 곡 이름과 난이도 가져옴
        game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());

        // game 인스턴스 안에 있는 run 함수 실행
        game.start();

        // 버튼 안보이게
        leftButton.setVisible(false);
        rightButton.setVisible(false);
        easyButton.setVisible(false);
        hardButton.setVisible(false);

        // 뒤로 돌아가기 버튼
        backButton.setVisible(true);

        // 백그라운드 이미지가 ingame 이미지로 바뀌어야함
        Background = new ImageIcon(getClass().getResource("/game_images/"+trackList.get(nowSelected).getIngameImage())).getImage();

        // 노트 찍기 모드 일때는 해당 이미지 추가
        // noteMaker = true 일 때만 버튼 보이게
        if(Game.noteMaker) {
            noteWriteButton.setVisible(true);
        }

        // 게임 시작 시 점수 & 콤보 초기화
        Game.combo = 0;
        Game.score = 0;

        // 키보드 이벤트 동작을 위한 메서드
        // 이는 Main 클래스에 포커스가 맞춰져있어야 키보드 이벤트가 정상적으로 동작하기 때문
        setFocusable(true);
        requestFocus();
    }

    public void bakMain(){
        // 메인 화면일때는 isMainScreen 이 true, GameScreen 은 false
        isMainScreen = true;
        isGameScreen = false;


        // 메인화면으로 돌아오면 다시 버튼 보이게
        leftButton.setVisible(true);
        rightButton.setVisible(true);
        easyButton.setVisible(true);
        hardButton.setVisible(true);

        // 뒤로 돌아가기 버튼
        backButton.setVisible(false);

        // 노트 찍기 버튼
        if(Game.noteMaker){
            noteWriteButton.setVisible(false);
        }

        // 다시 트랙 선택
        selectTrack(nowSelected);

        // 백그라운드 이미지가 track 의 nowSelected 에 맞는 이미지로
        Background = new ImageIcon(getClass().getResource("/menu_images/main_Bakground.jpg")).getImage();

        // 메뉴로 돌아왔을 때 게임 종료
        game.Close();
    }
}

