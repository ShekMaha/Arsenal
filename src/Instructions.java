import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Instructions extends JPanel implements ActionListener{
	Scanner reader;
	String message="";
	JButton back;
	ImageIcon background= new ImageIcon("insback.png");
	ArrayList <String> messArr = new ArrayList<String>();
	public Instructions(){
		try{
			reader = new Scanner(new File("Instruction.txt"));
			while (reader.hasNextLine()){
				message=reader.nextLine()+"\n";
				messArr.add(message);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		reader.close();
		setLayout(new BorderLayout());
		back = new JButton("Back");
		add(back,BorderLayout.SOUTH);
		back.addActionListener(this);
		back.setFocusable(false);
		//System.out.print(message);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		int x=200;
		int y=160;
		g.drawImage(background.getImage(),0,0,null);
		g.setColor(Color.white);
		g.fillRect(145,100, 450, 270);
		g.setColor(Color.black);
		g.setFont(new Font("Impact", Font.BOLD, 60));
		g.drawString("INSTRUCTIONS", 175, 70);
		g.setFont(new Font("Verdana", Font.PLAIN, 20));
		for (int i=0; i<messArr.size(); i++){
			g.drawString(messArr.get(i), x, y);
			
			y+=20;
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)(e.getSource());
		if (b.getText().equals("Back")){
			//Menu.menu.loop();
			Game.gameFrame.cl.show(Game.gameFrame.c, "Menu");
			
		}
		// TODO Auto-generated method stub
		
	}
}
