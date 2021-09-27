package moon_lander;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;

import java.io.FileInputStream;


public class Music extends Thread {

    boolean isloop;//반복여부 정하기
    public String name;//음악파일이름(확장자까지) 넣기
    Player player;
    FileInputStream fis;
    BufferedInputStream bis;

    //생성자 부분
    public Music(String filename, boolean isloop) {
        try {
            this.isloop = isloop;
            name = filename;
            //프로그램내의 src아래 Musicfile패키지를 생성하고 그곳에 음악파일을 넣었을때의 경로
            // C:/ ~ src/패키지이름/음악파일이름.mp3 까지 해도 상관없음
            fis = new FileInputStream("src/Music/" + name);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //재생음악 변경
    
    public void change(String filename, boolean isloop) {
        player.close();
        this.interrupt();
        name = filename;
        this.isloop = isloop;
    }

    //음악종료
    
    public void close() {
        isloop = false;
        player.close();
        this.interrupt();
    }

    @Override
    public void run() {
        try {
            do {
                fis = new FileInputStream("src/Music/" + name);
                bis = new BufferedInputStream(fis);
                player = new Player(bis);
                player.play();
            } while (isloop);
        } catch (Exception e) {

        }
    }

}