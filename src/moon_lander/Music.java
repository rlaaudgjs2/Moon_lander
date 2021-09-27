package moon_lander;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;

import java.io.FileInputStream;


public class Music extends Thread {

    boolean isloop;//�ݺ����� ���ϱ�
    public String name;//���������̸�(Ȯ���ڱ���) �ֱ�
    Player player;
    FileInputStream fis;
    BufferedInputStream bis;

    //������ �κ�
    public Music(String filename, boolean isloop) {
        try {
            this.isloop = isloop;
            name = filename;
            //���α׷����� src�Ʒ� Musicfile��Ű���� �����ϰ� �װ��� ���������� �־������� ���
            // C:/ ~ src/��Ű���̸�/���������̸�.mp3 ���� �ص� �������
            fis = new FileInputStream("src/Music/" + name);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //������� ����
    
    public void change(String filename, boolean isloop) {
        player.close();
        this.interrupt();
        name = filename;
        this.isloop = isloop;
    }

    //��������
    
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