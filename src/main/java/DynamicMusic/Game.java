package DynamicMusic;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 스레들는 프로그램 안에 있는 작은 프로그램
public class Game extends Thread {
    // 1. 전반적인 처리 과정
    // Main 메소드를 통해 게임이 실행되면 지금 이 클래스 - game - 에서
    // 만들어진 인스턴스 변수를 이용해서 게임 컨트롤 가능하도록
    // 즉 게임 클래스의 인스턴스가 생성되어 실행되면 아래 run() 메소드가 실행됨

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

    // 노트 판정 이미지 - flare
    private Image blueFlareImage;
    // 노트 판정 이미지 - 글자
    private Image judgeImage;

    // 게임 실행 시 그 게임에 맞는 음악을 플레이하기 위한 변수
    private String titleName;
    private String diffiCulity;
    private String selectedMusic;
    private Music gameMusic;

    // Note maker 변수
    static boolean noteMaker = false;
    FileWriter fw;

    // 점수 출력
    int score = 0;
    int highScore = 0;

    // 콤보 출력
    int combo = 0;
    int highCombo = 0;
    private Image comboImage = new ImageIcon(getClass().getResource("/menu_images/combo.png")).getImage();

    public Game(String titleName, String diffiCulity, String selectedMusic) {
        this.titleName = titleName;
        this.diffiCulity = diffiCulity;
        this.selectedMusic = selectedMusic;
        gameMusic = new Music(this.selectedMusic, false, "game");
        gameMusic.start();

        // 노트 편하게 찍기
        // noteMaker 이 true 인 동안만 아래 내용 실행
        if (noteMaker) {
            try {
                // 작성자가 입력한 시간과 입력한 키를 노래명_난이도.txt 로 저장
                fw = new FileWriter(".\\src\\main\\resources\\readNote\\" + titleName + "_" + diffiCulity + ".txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // 음악 노트를 개별적으로 관리할 ArrayList
    ArrayList<Note> noteList = new ArrayList<Note>();

    public void screenDraw(Graphics2D g) {
        // INGAME 노트 경로 배경, 노트 경로별 경계선(라인), 어떤키 사용하는지, 점수 등 => 총 7키
        // 뒷부분에 그려지는 이미지 - 코드 일수록 - 앞쪽으로 튀어나와서 그려지게 됨, 마치 모드처럼

        // ingame 시 게임 정보 표시를 위한 파란 줄
        g.drawImage(gameInfoImage, 0, 660, null);

        // A 버튼
        g.drawImage(noteRouteLine, 224, 30, null);
        g.drawImage(A_noteRouteImage, 228, 30, null);

        // S 버튼
        g.drawImage(noteRouteLine, 328, 30, null);
        g.drawImage(S_noteRouteImage, 332, 30, null);

        // D 버튼
        g.drawImage(noteRouteLine, 432, 30, null);
        g.drawImage(D_noteRouteImage, 436, 30, null);

        // Space1
        g.drawImage(noteRouteLine, 536, 30, null);
        g.drawImage(Space1_noteRouteImage, 540, 30, null);

        // Space2
        g.drawImage(noteRouteLine, 740, 30, null);
        g.drawImage(Space2_noteRouteImage, 640, 30, null);

        // J 버튼
        g.drawImage(noteRouteLine, 844, 30, null);
        g.drawImage(J_noteRouteImage, 744, 30, null);

        // K 버튼
        g.drawImage(noteRouteLine, 948, 30, null);
        g.drawImage(K_noteRouteImage, 848, 30, null);

        // L 버튼
        g.drawImage(noteRouteLine, 1052, 30, null);
        g.drawImage(L_noteRouteImage, 952, 30, null);


        // noteList 에는 note 위치 - x, y - 가 저장되어 있음
        // for 문을 통해 noteList 안에 있는 내용을 하나하나 꺼내오면서 반복 출력
        // 반복 출력되면서 Graph 으로 만듦
        for (int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);

            // 노트 판정의 마지노선이 620 이기 때문에
            // 620 이 넘어가는 note 들에 대해서는 miss 이미지 출력
            if (note.getY() > 620) {
                judgeImage = new ImageIcon(getClass().getResource("/menu_images/judgeMiss.png")).getImage();
                score -= 10;
                combo = 0;

            }
            // 현재 노트가 동작 상태가 아니라면 - Proceeded 가 false 라면 -
            // 사용되지 않은 노트는 화면에서 지워짐 -> 해당 i 번째 노트를 삭제
            if (!note.isProceeded()) {
                noteList.remove(i);
                i--;
            } else {
                note.screenDraw(g);
            }
        }


        // 글씨 색깔 설정
        g.setColor(Color.white);
        // Graphics2D 설정 : 이 설정을 통해서 TEXT에 ANTIALIASING 설정을 할 수 있고, 글씨가 좀더 뚜렷하게 보임
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


        // 폰트설정 : 아래 나오는 텍스트에 폰트 적용
        g.setFont(new Font("Arial", Font.BOLD, 30));

        // ingame 에서 노래 제목 출력
        g.drawString(titleName, 20, 702);

        // ingame 에서 노래 난이도 출력
        g.drawString(diffiCulity, 1190, 700);

        // 최고 점수, 최고 콤보 확인
        if(highScore < score){
            highScore = score;
        }

        if(highCombo < combo){
            highCombo = combo;
        }

        // 게임 점수 출력
        if(score<0){ // 점수가 0 미만으로 내려가면 그냥 0 출력
            g.drawString(String.valueOf(0), 620, 702);
        }else{ // 아니면 그대로 출력
            g.drawString(String.valueOf(score), 620, 702);
        }
        g.drawString(String.valueOf(highScore), 750, 702);

        // 게임 콤보 출력
        g.drawImage(comboImage, 1050,130,null);
        g.setColor(Color.CYAN);
        g.drawString(String.valueOf(combo), 1150, 270);
        g.drawString(String.valueOf(highCombo), 1150, 350);

        // 폰트 설정2: 아래 나오는 텍스트에 폰트 적용용
        g.setFont(new Font("Elephant", Font.BOLD, 30));
        g.setColor(Color.white);

        // ingame 키패드 확인 출력
        g.drawString("A", 270, 609);
        g.drawString("S", 374, 609);
        g.drawString("D", 470, 609);
        g.drawString("SPACE", 580, 609);
        g.drawString("J", 784, 609);
        g.drawString("K", 889, 609);
        g.drawString("L", 993, 609);


        // 음악 노트판정을 위한 배경
        g.drawImage(judgementLineImage, 0, 580, null);

        // 노트 판정 시 이미지 - flare
        g.drawImage(blueFlareImage, 250, 150, null);

        // 노트 판정 시 이미지 - 글자
        g.drawImage(judgeImage, 570, 290, null);
    }

    // press 메서드 : 해당 버튼을 눌렀을 때 이미지변경
    // release 메서드 : 해당 버튼에서 손을 뗐을 때 이미지 변경
    public void pressA() {
        A_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoutePressed.png")).getImage();
        // 키보드 눌렀을 때 음악
        new Music("drumSmall1.mp3", false, "menu").start();

        if (noteMaker == true) {
            System.out.println(gameMusic.getTime() + " A");
            noteWriter(gameMusic.getTime(), "A");
        }

        // note 판정 함수
        judge("A");

    }

    public void releaseA() {
        A_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    }

    public void pressS() {
        S_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoutePressed.png")).getImage();
        new Music("drumSmall22.mp3", false, "menu").start();

        if (noteMaker == true) {
            System.out.println(gameMusic.getTime() + " S");
            noteWriter(gameMusic.getTime(), "S");
        }
        // note 판정 함수
        judge("S");
    }

    public void releaseS() {
        S_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    }

    public void pressD() {
        D_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoutePressed.png")).getImage();
        new Music("drumSmall33.mp3", false, "menu").start();

        if (noteMaker == true) {
            System.out.println(gameMusic.getTime() + " D");
            noteWriter(gameMusic.getTime(), "D");

        }
        // note 판정 함수
        judge("D");
    }

    public void releaseD() {
        D_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    }

    public void pressSpace() {
        Space1_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoutePressed.png")).getImage();
        Space2_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoutePressed.png")).getImage();

        new Music("drumBig1.mp3", false, "menu").start();

        if (noteMaker == true) {
            System.out.println(gameMusic.getTime() + " Space");
            noteWriter(gameMusic.getTime(), "Space");

        }

        // note 판정 함수
        judge("Space");
    }

    public void releaseSpace() {
        Space1_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
        Space2_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    }

    public void pressJ() {
        J_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoutePressed.png")).getImage();
        new Music("drumSmall33.mp3", false, "menu").start();

        if (noteMaker == true) {
            System.out.println(gameMusic.getTime() + " J");
            noteWriter(gameMusic.getTime(), "J");

        }

        // note 판정 함수
        judge("J");
    }

    public void releaseJ() {
        J_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    }

    public void pressK() {
        K_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoutePressed.png")).getImage();
        new Music("drumSmall22.mp3", false, "menu").start();

        if (noteMaker == true) {
            System.out.println(gameMusic.getTime() + " K");
            noteWriter(gameMusic.getTime(), "K");

        }
        // note 판정 함수
        judge("K");
    }

    public void releaseK() {
        K_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    }

    public void pressL() {
        L_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoutePressed.png")).getImage();
        new Music("drumSmall1.mp3", false, "menu").start();

        if (noteMaker == true) {
            System.out.println(gameMusic.getTime() + " L");
            noteWriter(gameMusic.getTime(), "L");

        }
        // note 판정 함수
        judge("L");
    }

    public void releaseL() {
        L_noteRouteImage = new ImageIcon(getClass().getResource("/menu_images/noteRoute.png")).getImage();
    }

    @Override
    public void run() {
        dropNote();
    }

    public void close() {
        // 게임 음악 종료
        gameMusic.close();
        // 스레드 종료
        this.interrupt();

    }

    // 노트를 떨어뜨리게 - 내려오게 - 만드는 메소드

    // Beat[] 로 만들어진 비트 배열에 만들어진 beat를 넣어둠
    // 이후 for 문이 동작하면서 betas 의 시간과 gameMusic 를 비교하면서 노트를 출력함
    // 노트 출력시에는 Note 인스턴스 객체 사용
    // => note 에는 Thread 가 달려있고, note가 실행되면 Thread 가 같이 실행됨
    // => Dynamic Music 클래스에서 screenDraw 가 실행되면서 for문 noteList가 돌게 됨
    public void dropNote() {

        if (noteMaker == false) {

            Beat[] beats = null; // beat 객체를 배열로

            // time : 파일에서 가져온 시간 배열
            // noteType : 파일에서 가져온 noteType => 버튼 배열
            ArrayList<Integer> time = new ArrayList<>();
            ArrayList<String> noteType = new ArrayList<>();

            InputStream in; // 파일 위치 확인
            BufferedReader br; // 파일 읽어오기
            String readfile = ""; // str 선언

            // 곡 명
            try {

                // jar 파일로 만들었을 때 파일을 불러오기 위한 코드
                // InputStream 을 통해서 파일을 가져오고 이후 ButteredRead 를 통해서 파일을 읽는다.
                in = getClass().getResourceAsStream("/readNote/" + titleName + "_" + diffiCulity + ".txt");
                br = new BufferedReader(new InputStreamReader((in)));

                // 파일을 한줄씩 읽어옴. 이때 1줄에 대하여 " " 공백으로 나눠서 각각 time, noteType Arraylist 에 저장함
                // 1. 1줄 : 1110 A 로 찍혀있고 이를 1110 과 A 로 나눔.
                // 2. 그후 시간은 time 에 A는 노트 타입에 저장함
                while ((readfile = br.readLine()) != null) {

                    StringTokenizer note_stk = new StringTokenizer(readfile, " ");
                    time.add(Integer.parseInt(note_stk.nextToken()));
                    noteType.add(note_stk.nextToken());

                }

//                System.out.println(time.size());
//                System.out.println("time : " + time.get(0));
//                System.out.println("notetype : " + noteType.get(0));


                // 노트 떨어지는 시간 갭
//                int gap = 660/(Main.NOTE_SPEED) * (Main.SLEEP_TIME) - (Main.REACH_TIME);
                int gap = 125 - Main.REACH_TIME;

                // beat 객체로 만든 beats 배열
                // 배열의 사이즈는 time 배열의 크기만큼
                // 즉 이로인해서 내가 찍은 전체 노트의 사이즈 = time 배열 사이즈 = noteType 사이즈 = beats 배열 사이즈
                beats = new Beat[time.size()];

                for (int j = 0; j < time.size(); j++) {
                    // beat 배열의 생성자 매개변수로 time 과 noteType을 던져줌
                    beats[j] = new Beat(time.get(j)+gap, noteType.get(j));
//                        System.out.println("time : " + time.get(j));
//                        System.out.println("notetype : " + noteType.get(j));
//                        System.out.println("시간 : "+gameMusic.getTime());
                }


            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }


            // beats 배열안에 있는 노트에서의 시간 값(getTime)이
            // 즉 노트가 떨어지는 시간이 게임 시간보다 작다면 노트가 떨어짐
            // 현재 음악 시간을 실시간으로 파악해서 해당 시간에 알맞은 노트를 떨어뜨림
            int i = 0;

            while (true) {
                boolean dropped = false;

                if (beats[i].getTime() <= gameMusic.getTime()) {
//                    System.out.println("beat note: "+beats[i].getNoteName());
//                    System.out.println("i : "+i);
                    Note note = new Note(beats[i].getNoteName());
                    note.start();
                    noteList.add(note);
                    i++;

                    dropped = true;
                }
                if (dropped) {
                    try {
                        Thread.sleep(5);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (noteMaker == true) {
            System.out.println("노트 찍기 모드");
        }
    }

    // 노트 찍기 모드
    public void noteWriter(int getTime, String key) {
        try {

            BufferedWriter bw = new BufferedWriter(fw);

            bw.append(String.valueOf(getTime)).append(" ").append(key);
            bw.newLine();
            bw.flush();

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
    }


    public void judge(String input) {
        // for 문을 통해 전체 노트를 훑어보게됨
        for (int i = 0; i < noteList.size(); i++) {
            // 만약 noteList.get(i) 로 가져온 현재 노트 - nowNote.getNoteType - 가
            // 사용자가 입력한 input 과 일치한다면 note.judge() 메서드 실행
            // 만약 노트 입력이 안된거면 그냥 무시 => 추후 miss 판정
            Note nowNote = noteList.get(i);

            if (input.equals(nowNote.getNoteType())) {
                // judgeEvent 는 String 을 매개변수로 받음
                // Note 의 judge 메서드는 각 판정에 맞는 String 타입을 반환함
                judgeEvent(nowNote.judge());
                break;
            }
        }
    }

    public void judgeEvent(String judge) {
//        System.out.println(judge);
        blueFlareImage = new ImageIcon(getClass().getResource("/menu_images/blue_flare.png")).getImage();
//        if (!judge.equals("None")) {
//            blueFlareImage = new ImageIcon(getClass().getResource("/menu_images/blue_flare.png")).getImage();
//        }
        // Miss : 점수 -10, 콤보 초기화
        // 나머지는 점수 +, 콤보+1
        if (judge.equals("Miss")) {
            judgeImage = new ImageIcon(getClass().getResource("/menu_images/judgeMiss.png")).getImage();
            score -= 10;
            combo = 0;

        } else if (judge.equals("Late")) {
            judgeImage = new ImageIcon(getClass().getResource("/menu_images/judgeLate.png")).getImage();
            score += 5;
            combo +=1;
        } else if (judge.equals("Early")) {
            judgeImage = new ImageIcon(getClass().getResource("/menu_images/judgeEarly.png")).getImage();
            score += 10;
            combo +=1;

        } else if (judge.equals("Good")) {
            judgeImage = new ImageIcon(getClass().getResource("/menu_images/judgeGood.png")).getImage();
            score += 20;
            combo +=1;

        } else if (judge.equals("Great")) {
            judgeImage = new ImageIcon(getClass().getResource("/menu_images/judgeGreat.png")).getImage();
            score += 30;
            combo +=1;

        } else if (judge.equals("Perfect")) {
            judgeImage = new ImageIcon(getClass().getResource("/menu_images/judgePerfect.png")).getImage();
            score += 50;
            combo +=1;
        }
    }


    public Music musicTime(){
        return gameMusic;
    }

}

