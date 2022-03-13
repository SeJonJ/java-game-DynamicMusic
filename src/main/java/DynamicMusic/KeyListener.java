package DynamicMusic;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// 키보드 이벤트 감지
public class KeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        // e.getKeyCode 는 사용자가 누르는 키
        // KeyEvent.VK_@ 컴퓨터가 갖는 키
        // static 으로 설정된 game 이 null 값을 갖는다면 => 어떤 게임도 실행되고 있지 않는다면
        // return 해서 아래 키보드 이벤트 작업 실행되지 않도록 함
        if(DynamicMusic.game == null){
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            DynamicMusic.game.pressA();
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            DynamicMusic.game.pressS();
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            DynamicMusic.game.pressD();
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            DynamicMusic.game.pressSpace();

        }
        else if(e.getKeyCode() == KeyEvent.VK_J){
            DynamicMusic.game.pressJ();
        }
        else if(e.getKeyCode() == KeyEvent.VK_K){
            DynamicMusic.game.pressK();
        }
        else if(e.getKeyCode() == KeyEvent.VK_L){
            DynamicMusic.game.pressL();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        if(DynamicMusic.game == null){
            return;
        }
        
        // 여기서 사용되는 e.getKeyCode() 는 키보드의 아스키 코드값을 가져옴
        // KeyEvent.VK_% 도 출력해보면 해당 글자, 숫자의 아스키 코드값이 출력됨
        if(e.getKeyCode() == KeyEvent.VK_A){
            DynamicMusic.game.releaseA();
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            DynamicMusic.game.releaseS();
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            DynamicMusic.game.releaseD();
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            DynamicMusic.game.releaseSpace();
        }
        else if(e.getKeyCode() == KeyEvent.VK_J){
            DynamicMusic.game.releaseJ();
        }
        else if(e.getKeyCode() == KeyEvent.VK_K){
            DynamicMusic.game.releaseK();
        }
        else if(e.getKeyCode() == KeyEvent.VK_L){
            DynamicMusic.game.releaseL();
        }
    }
}
