package moon_lander;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Create extends JFrame implements ActionListener  {

    FirebaseData firebaseData = new FirebaseData();
    private static boolean isFirstRun = false;
    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField userName_text;
    JPasswordField password_text;
    JButton Save;

    Create() {

        // User Label
        user_label = new JLabel();
        user_label.setText("User Name :");
        userName_text = new JTextField();




        // Password

        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField();

        // Login Create



        panel = new JPanel(new GridLayout(3, 1));

        Save = new JButton("Save");



        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);


        panel.add(Save);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding the listeners to components..
       Save.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
            String n = userName_text.getText();

            String p = password_text.getText();
            if(!firebaseData.CheckIsIdIsExists(n)) {
                JOptionPane.showMessageDialog( null, "you have created in successfully" );
                firebaseData.InputDBData(n, p); // 아이디 패스워드 입력
            }
            else
            {
                JOptionPane.showMessageDialog( null , " Not Create ");
            }
           }
       });

        add(panel, BorderLayout.CENTER);



        setTitle("Create");
        setSize(500, 300);

        setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent ae) {
        String userName = userName_text.getText();
        String password = password_text.getText();

    }

}