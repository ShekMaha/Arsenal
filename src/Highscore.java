import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Highscore extends JPanel implements ActionListener{
	Scanner reader;
	String message="";
	//ArrayList <String> messArr = new ArrayList<String>();
	String messArr[]=new String[11];
	int scores[] ;
	String names[];
	JButton back;
	ImageIcon background= new ImageIcon("insback.png");
	int pos=-1;
	public Highscore(){
		read();
		sort();
		setLayout(new BorderLayout());
		back = new JButton("Back");
		add(back,BorderLayout.SOUTH);
		back.addActionListener(this);
		back.setFocusable(false);
		//System.out.println(message);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int x=240;
		int y=120;
		g.drawImage(background.getImage(),0,0,null);
		g.setColor(Color.white);
		g.fillRect(225, 90, 220, 360);
		g.setColor(Color.black);
		g.setFont(new Font("Impact", Font.BOLD, 60));
		g.drawString("Highscores", 175, 70);
		g.setFont(new Font("Courier", Font.BOLD, 24));
		
		for (int i=0; i<messArr.length; i++){
			
			g.drawString(messArr[i], x, y);
			y+=35;
			
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
	public void input(GamePanel gp){
		String name = JOptionPane.showInputDialog(gp,"Enter your name: ");
		name=name.trim();
		if (name==null||name.length()==0){
			name="Unknown";
		}
		else if (name.length()>10){
			name=name.substring(0,10);
		}
		else{
			if (pos==-1){
			names[9]=name;
			scores[9]=Crate.score;
			}
			else{
				names[pos]=name;
				scores[pos]=Crate.score;
			}
//			if (messArr.size()==10){
//				names[messArr.size()-1]=name;
//				scores[messArr.size()-1]=Crate.score;
//			}
//			else{
//				names[messArr.size()]=name;
//				scores[messArr.size()]=Crate.score;
//			}
			sort();
			write();
			return;
		}
		
	}
	public void read(){
		try{
			reader = new Scanner(new File("Highscore.txt"));
			int count=0;
			while (reader.hasNext()){
				messArr[count]=reader.nextLine();
				count++;
				//messArr.add(reader.nextLine());
			}
			
			for (int i=0; i<messArr.length; i++){
				
				
				if (messArr[i]==null){
					//messArr[i]="null";
					pos=i;
					break;
				
				}
			}
//			for (int i=0; i<messArr.length; i++){
//				if (messArr[i].equals("null")){
//					pos=i;
//					break;
//				}
//			}
			System.out.println(pos);
			reader.close();
			//System.out.println(//messArr.size());
		
			scores=new int[10];
			names=new String[10];
			String arr[];
			//if (scores.length==0){
			//	return;
			//}
			for (int i=0;  i<pos; i++){
				reader = new Scanner(new File("Highscore.txt"));
				//System.out.println(scores[i]);
				names[i]=reader.nextLine().trim();
				names[i]=names[i].substring(0,names[i].indexOf(" "));
				String space="";
				for (int j =0; j<12-names[i].length(); j++){
					space+=" ";
				}
				reader.close();
				reader=new Scanner(new File("Highscore.txt"));
				arr = reader.nextLine().split(space);
				scores[i]=Integer.parseInt(arr[1]);
				reader.close();
				//names[i]=arr[0];
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void write() {
		
		try{
			//PrintStream p = new PrintStream(new FileOutputStream("Highscore.txt",true));
			PrintWriter p = new PrintWriter(new File("Highscore.txt"));
			for (int i=0; i<names.length;i++){
				p.format("%-10s%3s",names[i],scores[i]);
				p.println();
			}
			
			p.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void check(GamePanel gp){
		//messArr.clear();
		if (scores.length==0){
			input(gp);
		}
		if (pos==-1){
			if (Crate.score>scores[9]){
				input(gp);
			}
		}
		else{
			input(gp);
		}
//		if (Crate.score>scores[pos]){
//			input(gp);
//		}
		return;
	}
	
	public void sort(){
		Arrays.sort(scores);
		
		int num=scores.length-1;
		for (int i=0; i<scores.length/2; i++){
			
			scores[i]=scores[num];
			num--;
			
		}
	}
	
}


