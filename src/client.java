import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

import javax.swing.border.*;

import javax.swing.*;

public class client implements ActionListener {
    JTextField textfield;
     static JFrame  f = new JFrame();
     static JPanel a1;
     static Box vertical=Box.createVerticalBox();
    static DataOutputStream dout;
    client(){


        JPanel panel= new JPanel();
        f.setSize(450,700);
        f.setLocation(800,50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.white);

        JButton send= new JButton("Send");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        send.addActionListener(this);//current object will handle action events(action listner call action performed method)
        f.add(send);


        f.setLayout(null);
        panel.setLayout(null);
        panel.setBackground(new Color(7,94,84));
        panel.setBounds(0,0,450,70);
           f.add(panel);
 
 
 
        ImageIcon  ic= new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image img=ic.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon ic2 = new ImageIcon(img);
        JLabel back = new JLabel(ic2);
        back.setBounds(5,20,20,25);
        panel.add(back);




        //functionality of back button
        back.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent ae){
            System.exit(0);

           } 
        });

        ImageIcon  ic3= new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
        Image img2=ic3.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon ic4 = new ImageIcon(img2);
        JLabel profile = new JLabel(ic4);
        profile.setBounds(40,10,50,50);
        panel.add(profile);

        ImageIcon  ic5= new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image img3=ic5.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon ic6 = new ImageIcon(img3);
        JLabel video = new JLabel(ic6);
        video.setBounds(300,20,30,30);
        panel.add(video);

        
        ImageIcon  ic7= new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image img4=ic7.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
        ImageIcon ic8 = new ImageIcon(img4);
        JLabel phone= new JLabel(ic8);
        phone.setBounds(360,20,35,30);
        panel.add(phone);

       

        ImageIcon  ic9= new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image img6=ic9.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
        ImageIcon ic10 = new ImageIcon(img6);
        JLabel more = new JLabel(ic10);
        more.setBounds(415,20,10,25);
        panel.add(more);

        JLabel name = new JLabel("Bunty");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        panel.add(name);

        JLabel status= new JLabel("Active Now");
        status.setBounds(110,35,100,18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,14));
        panel.add(status);


        a1= new JPanel();
        a1.setBounds(5,75,440,570);
        f.add(a1);


        textfield = new JTextField();
        textfield.setBounds(5,655,310,40);
        textfield.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(textfield);












        f.setVisible(true);


    }


    public void actionPerformed(ActionEvent e){
        try{
            String text = textfield.getText();
        
            JPanel p2= formatLabel(text);
        
            a1.setLayout(new BorderLayout());
             JPanel right = new JPanel( new BorderLayout());
             right.add(p2,BorderLayout.LINE_END);
             vertical.add(right);
             vertical.add(Box.createVerticalStrut(15));
             a1.add(vertical,BorderLayout.PAGE_START);
             dout.writeUTF(text);
             textfield.setText("");
             f.repaint();
             f.invalidate();
            f.validate();

        }
        catch(Exception eb){

            eb.printStackTrace();



        }
      
        



    }
    public static JPanel formatLabel(String out ){
        JPanel panel2= new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        JLabel output= new JLabel("<html><p style=\"width:150px\">"+ out+ "</html>");
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        panel2.add(output);
        Calendar cal= Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time= new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel2.add(time);




        return panel2;
        

    }
    public static void main(String [] args){
        new client();
        try{
            Socket s= new Socket("127.0.0.1",6001);
            DataInputStream din= new DataInputStream(s.getInputStream());
            dout=new DataOutputStream(s.getOutputStream());

            while(true){
                a1.setLayout( new BorderLayout());
                String msg= din.readUTF();
                JPanel panel2= formatLabel(msg);


                JPanel left = new JPanel(new BorderLayout());
                left.add(panel2,BorderLayout.LINE_START);
                vertical.add(left);
                 vertical.add(Box.createVerticalStrut(15));
                 a1.add(vertical,BorderLayout.PAGE_START);


                f.validate();

            }

        }
        catch(Exception e ){
            e.printStackTrace();



        }
    }

    
}
