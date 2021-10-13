package moon_lander;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stage {
    JButton Stage1;
    JButton Stage2;
    JButton Stage3;
    JButton Stage4;
    JButton Stage5;
    JButton Information;
    private Framework framework;
    public int stage;

    Stage(int stage) {
        Stage1 = new JButton("Stage1");
        Stage2 = new JButton("Stage2");
        Stage3 = new JButton("Stage3");
        Stage4 = new JButton("Stage4");
        Stage5 = new JButton("Stage5");
        Information = new JButton("제작자 정보");

        new Framework();

        Stage1.setBounds(250, 400, 150, 30);
        Stage2.setBounds(250, 500, 150, 30);
        Stage3.setBounds(250, 600, 150, 30);
        Stage4.setBounds(250, 700, 150, 30);
        Stage5.setBounds(250, 800, 150, 30);
        Information.setBounds(250, 900, 150, 30);

        switch (stage) {

            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;

        }

    }
}

