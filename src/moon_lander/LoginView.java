package moon_lander;

import org.apache.commons.logging.Log;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class LoginView extends JFrame implements ActionListener {
    FirebaseData firebaseData = new FirebaseData();
    private static boolean isFirstRun = false;
    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField userName_text;
    JPasswordField password_text;
    JButton Login;
    JButton  Create;
    LoginView() {

        // User Label
        user_label = new JLabel();
        user_label.setText("User Name :");
        userName_text = new JTextField();
        final String id = userName_text.getText();


        // Password

        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField();


        // Login Create



        panel = new JPanel(new GridLayout(3, 1));

        Login = new JButton("Login");

        Create = new JButton("Create");

        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);


        panel.add(Login);
        panel.add(Create);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding the listeners to components..
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginView();
                String n = userName_text.getText();
                String p = password_text.getText();
                if(firebaseData.CheckIsIdPwIsExists(n,p)) {
                    JOptionPane.showMessageDialog( null, "you have logined in successfully" );

                }
                else
                {
                    JOptionPane.showMessageDialog( null , " Not Login ");
                }
            }
//                    isFirstRun = true;

        });
        Create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Create();
            }
        });
        add(panel, BorderLayout.CENTER);

        setTitle("Please Login Here !");

        setVisible(true);


        setTitle("Login");
        setSize(500, 300);

        setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent ae) {
        String userName = userName_text.getText();
        String password = password_text.getText();

        }

    }

