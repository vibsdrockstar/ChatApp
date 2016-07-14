import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.lang.Math;
public class ChatBot extends JFrame implements KeyListener{
JPanel p1=new JPanel();
JTextArea dia=new JTextArea(30,60);
JTextArea input=new JTextArea(1,60);
JScrollPane scroll=new JScrollPane(
		dia,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		);
String[][] chatBot={
		//standard greetings
		{"namaste","hello","bonjour","konchiwa"},
		{"hey","hola","hallo"},
		//question greetings
		{"how r u","Cómo estás","kaisa hai","wie geht's","comment ça va"},
		{"good","vais bien","bueno "},
		//default
		{"Shut up","you r irritating","Cállate","que son irritantes",
"(Vinayak is unavailable,due to LOL)"}			

		
};
public static void main(String[] args){
	new ChatBot();
}
public ChatBot(){
	super("Chat Bot");
	setSize(900,600);
	setResizable(false);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	dia.setEditable(false);
	input.addKeyListener(this);
	p1.add(scroll);
	p1.add(input);
	p1.setBackground(new Color(255,200,0));
	add(p1);
	setVisible(true);
}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(false);	
			//-----grab quote-----
			String quote=input.getText();
			input.setText("");
			addText("-->You:\t"+quote); 
			quote.trim();
			while(
				quote.charAt(quote.length()-1)=='!' ||
				quote.charAt(quote.length()-1)=='.' ||
				quote.charAt(quote.length()-1)=='?' 
				){
				
				quote=quote.substring(0, quote.length()-1);
			}
			quote.trim();
			byte response=0;
		/*
		 0:W r searching through chatBot[][] 4 matches
		 1:we didn't find anything in chatBot[][] 
		 2:we did find something in chatBot[][] 
		 */
			//-----check for matches---
			int j=0;//which group we r checking
			while(response==0){
				if(inArray(quote.toLowerCase(),chatBot[j*2])){
					response=2;
					int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
					addText("\n--->Vinayak\t"+chatBot[(j*2)+1][r]);
				}
				j++;
				if(j*2==chatBot.length-1 && response==0){
					response=1;
				}
			}
			
			//-----default-------
			if(response==1){
				int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
				addText("\n--->Vinayak\t"+chatBot[chatBot.length-1][r]);
			}
			addText("\n");
		}
		
	}
	
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(true);
			
		}	
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
public void addText(String str) {
	dia.setText(dia.getText()+str);
}

public boolean inArray(String in,String[] str ){
	boolean match=false;
	for(int i=0;i<str.length;i++){
		if(str[i].equals(in)){
			match=true;
			
		}
	}
	return match;
}
}