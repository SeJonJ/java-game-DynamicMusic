package DynamicMusic;

import javax.swing.*;
import java.awt.*;

// 각각의 떨어지는 노트 모두 Thread
public class Note extends Thread{
    private Image noteBasicImage = new ImageIcon(getClass().getResource("/menu_images/noteBasic.png")).getImage();

    // 현재 노트 위치 확인을 위한 x, y 좌표
    // 이 중 y 값의 시작위치 - 120 - 으로 고정으로 해두어서
    // 노트가 생성된 지 1초 뒤 노트 판정위치인 580에 도달 할 수 있도록 함
    private int x, y = 580 - 1000 / Main.SLEEP_TIME * Main.NOTE_SPEED;

    private String noteType;

    public Note(int x, String noteType){
        this.x = x;

        this.noteType = noteType;
    }

    // 노트가 내려오는 그래픽을 그리기 위한 screenDraw
    public void screenDraw(Graphics2D g){

        // 짧은 노트라면 현재 x, y 좌표에 노트를 그려냄
        if(noteType.equals("short")){
            g.drawImage(noteBasicImage, x, y, null);

            // 긴 노트라면 노트 이미지 2개를 그려냄, 이때 겹치면 안되니까 한쪽은 x+100
        }else if(noteType.equals("long")){
            g.drawImage(noteBasicImage, x, y, null);
            g.drawImage(noteBasicImage, x+100, y, null);
        }
    }

    // 노트가 떨어지도록 만드는 함수
    public void drop(){
        // 노트가 떨어진다 -> 노트가 아래로 움직인다 -> y 축으로 일정한 좌표만큼 움직인다
        y += Main.NOTE_SPEED;
    }

    // 스레드 실행 함수
   @Override
    public void run(){
        try{
            // 노트 떨어지는것은 무한반복
            // 1초에 Main.NOTE_SPEED * 100 정도만큼 움직임
            while(true){
                drop();
                // 떨어질때 Main.Sleep_Time 에 설정된 시간만큼 딜레이를 주면서 떨어짐
                Thread.sleep(Main.SLEEP_TIME);
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }


}
