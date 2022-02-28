package DynamicMusic;

public class Main {
    // FINAL 은 한번 선언되면 변경X 변수 => 이런 상수들은 대문자로 정의

    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;

    public static void main(String[] args){

        DynamicMusic dynamicMusic = new DynamicMusic();
        dynamicMusic.start();


        System.out.println("실행 완료");
    }
}
