import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class timeTest extends JFrame implements ActionListener{
    JLabel timerLabel = null;
    JLabel secondLabel = null;
    int end;

    public timeTest()
    {
        setTitle("Timer Test");
        setSize(800, 300);
        setLayout(new BorderLayout());
        
        JPanel timer = new JPanel();
        timer.setLayout(new FlowLayout());
        
        timerLabel = new JLabel("0");
        timerLabel.setFont(new Font("Gothic",Font.ITALIC,40));
        secondLabel = new JLabel("0");
        secondLabel.setFont(new Font("Gothic",Font.ITALIC,40));

        timer.add(timerLabel);
        timer.add(secondLabel);

        timer.setSize(100,100);
        add(timer, BorderLayout.NORTH);
        setVisible(true);
        
        JPanel menuPanel = new JPanel();
        menuPanel.setSize(100, 300);
        menuPanel.setLayout(new GridLayout(2, 3));
        
        JButton b1 = new JButton("삼각김밥");
        b1.addActionListener(this);
        menuPanel.add(b1);
        
        JButton b2 = new JButton("핫바");
        b2.addActionListener(this);
        menuPanel.add(b2);
        
        JButton b3 = new JButton("라면");
        b3.addActionListener(this);
        menuPanel.add(b3);
        
        JButton b4 = new JButton("피자");
        b4.addActionListener(this);
        menuPanel.add(b4);
        
        JButton b5 = new JButton("삶은계란");
        b5.addActionListener(this);
        menuPanel.add(b5);
        
        JButton b6 = new JButton("밥");
        b6.addActionListener(this);
        menuPanel.add(b6);
        add(menuPanel, BorderLayout.SOUTH);
        
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new FlowLayout());
        
        JButton tarotButton = new JButton("타로 게임");
        ImageIcon tarot = new ImageIcon("tarot.png");
        tarotButton.setIcon(tarot);
        tarotButton.setBackground(Color.RED);
        tarotButton.addActionListener(this);
        gamePanel.add(tarotButton);
        
        JButton othelloButton = new JButton("오셀로 게임");
        ImageIcon othello = new ImageIcon("othello.png");
        othelloButton.setIcon(othello);
        othelloButton.setBackground(Color.YELLOW);
        othelloButton.addActionListener(this);
        gamePanel.add(othelloButton);
        
        JButton tictactoButton = new JButton("틱택토 게임");
        ImageIcon tictacto = new ImageIcon("tictacto.png");
        tictactoButton.setIcon(tictacto);
        tictactoButton.setBackground(Color.BLUE);
        tictactoButton.addActionListener(this);
        gamePanel.add(tictactoButton);
        
        add(gamePanel, BorderLayout.CENTER);
        
        
        while(true)
        {
        	timerLabel.setText(end / 60 + " : ");
        	secondLabel.setText(end % 60 + "");
        
            try {
                Thread.sleep(1000);
            } 
            
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            if(end > 0) end--;
            else {
            	timerLabel.setText("OVER!!");
            }
        }
        
    }
    
    public void actionPerformed(ActionEvent e)
    {
    	String Command = e.getActionCommand();
    	
    	if(Command.equals("삼각김밥"))
    		end = 30;
    	
    	else if(Command.equals("핫바"))
    		end = 60;
    	
    	else if(Command.equals("라면"))
    		end = 60 * 3;
    	
    	else if(Command.equals("피자"))
    		end = 60 * 4;
    	
    	else if(Command.equals("삶은계란"))
    		end = 60 * 8;
    	
    	else if(Command.equals("밥"))
    		end = 60 * 15;
    }
 
    public static void main(String[] args) {
    	new timeTest();
    }
}