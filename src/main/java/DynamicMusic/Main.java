package DynamicMusic;

public class Main {
    // FINAL 은 한번 선언되면 변경X 변수 => 이런 상수들은 대문자로 정의

    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;

    // 떨어지는 노트 속도
    public static final int NOTE_SPEED = 10;

    // 노트가 떨어지는 시간 주기
    public static final int SLEEP_TIME = 10;

    // 노트가 생성 된 후 판정바에 도달하기까지 시간
    public static final int REACH_TIME = 1;

    public static void main(String[] args){
        new LoginFrame().loginWindow();

        System.out.println("실행 완료");

    }
}
