import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GUI_Frame extends JFrame {
		private static final String[][] String = null;
		JTextField seq1;
		JTextField seq2;
		JLabel inputA;
		JLabel inputB;
		JButton seqA;
		JButton seqB;
		JButton save;
		JCheckBox global;
		JCheckBox local;
		JCheckBox consensus;
		JCheckBox reverse;
		JButton button;
		JLabel thr;
		JTextField Threshold;
		JFileChooser fc;
		public String[] output;
		public String a;
		public String b;
		public String c;
		public String headerInput1;
		public String sequenceInput1 =null;
		public String sequenceInputA =null;
		public String sequenceInputB =null;
		public String headerInput2;
		public String sequenceInput2 = null;
		public boolean Global;
		public boolean Local;
		public boolean Consensus;
		public boolean Reverse;
		public String titel;
		public String titelA;
		public String titelB;
		public String titelC;
	
		public GUI_Frame(){
			setVisible(true);
			setSize(650, 650);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Alignment Tool");
			setResizable(true);
			fc = new JFileChooser();

			
			
			
			this.getContentPane().setLayout(null);

			this.initWindow();

			this.addWindowListener(new WindowListener() {

				public void windowClosed(WindowEvent arg0) {


				}

				public void windowActivated(WindowEvent e) {


				}

				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}

				public void windowDeactivated(WindowEvent e) {


				}

				public void windowDeiconified(WindowEvent e) {


				}

				public void windowIconified(WindowEvent e) {


				}

				public void windowOpened(WindowEvent e) {


				}



			});

		}
		protected void initWindow() 
		{
			// Initiate:
			inputA = new JLabel ("paste sequence 1 or choose FASTA");
			inputB = new JLabel ("paste sequence 2 or choose FASTA");
			seq1 = new JTextField();
			seq2 = new JTextField();
			seqA = new JButton("open FASTA");
			
			seqB = new JButton("open FASTA");
			save = new JButton("save");
			global = new JCheckBox("global");
			local = new JCheckBox("local");
			reverse = new JCheckBox("reverse");
			consensus = new JCheckBox("consensus");
			button = new JButton("Get Aligment");
			thr = new JLabel("Threshold");
			Threshold = new JTextField();
			
			
			reverse.addItemListener(new ItemListener(){

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED){	
					Reverse = true;
				} else {
					Reverse = false;
				}
				}
				
			});
			
			global.addItemListener(new ItemListener(){

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED){	
					Global = true;
				} else {
					Global = false;
				}
				}
				
			});
			local.addItemListener(new ItemListener(){

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED){	
					Local = true;
				} else {
					Local = false;
				}
				}
				
			});
			
			consensus.addItemListener(new ItemListener(){

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED){	
					Consensus = true;
				} else {
					Consensus = false;
				}
				}
				
			});
			
			seqA.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == seqA) {
				        int returnVal = fc.showOpenDialog(GUI_Frame.this);

				        if (returnVal == JFileChooser.APPROVE_OPTION) {
				            File file = fc.getSelectedFile();
				            a = file.getAbsolutePath();
				            ReadFasta OpenFile1 = null;
							try {
								OpenFile1 = new ReadFasta(a);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				            String seqInputOpen1 = OpenFile1.sequence;
				            if (seqInputOpen1 != null){
				            	sequenceInputA = seqInputOpen1;
				            	seq1.setText(sequenceInputA);
				            	titelB = OpenFile1.header;
				            } else {
				            	titelB = "sequence A";
				            }
				        } 
				
				        }
				}
			});
			
			seqB.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == seqB) {
				        int returnVal = fc.showOpenDialog(GUI_Frame.this);

				        if (returnVal == JFileChooser.APPROVE_OPTION) {
				            File file2 = fc.getSelectedFile();
				            b = file2.getAbsolutePath();
				            ReadFasta OpenFile2 = null;
							try {
								OpenFile2 = new ReadFasta(b);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				            String seqInputOpen2 = OpenFile2.sequence;
				            if (seqInputOpen2 != null){
				            	sequenceInputB = seqInputOpen2;
				            	seq2.setText(sequenceInputB);
				            	titelC = OpenFile2.header;
				            } else {
				            	titelC = "sequence B";
				            }
				        } 
				
				        }
				}
			});
			
			save.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == save) {
				        int returnVal = fc.showSaveDialog(GUI_Frame.this);

				        if (returnVal == JFileChooser.APPROVE_OPTION) {
				            File file3 = fc.getSelectedFile();
				            c = file3.getAbsolutePath();
				            try {
								PrintTXT SAVE = new PrintTXT(output, c);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				        }
				
				        }
				}
			});

			button.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					buttonBerechneClicked();
				}		

			});
			

			// add Elements

			inputA.setBounds(5,10, 590, 25);
			seq1.setBounds(5, 40, 590, 100);
			seqA.setBounds(5, 145, 590, 25);
			inputB.setBounds(5,175, 590, 25);
			seq2.setBounds(5, 205, 590, 100);
			seqB.setBounds(5, 310, 590, 25);
			global.setBounds(5, 340, 200, 25);
			local.setBounds(5, 370, 200,25);
			consensus.setBounds(5, 400, 200, 25);
			reverse.setBounds(305, 340, 200,25);
			thr.setBounds(305,370, 80, 25);
			Threshold.setBounds(395, 370, 200, 25);
			button.setBounds(5, 430, 280, 25);
			save.setBounds(315, 430, 280, 25);
		
			
			this.getContentPane().add(seq1);
			this.getContentPane().add(seqA);
			this.getContentPane().add(seq2);
			this.getContentPane().add(seqB);
			this.getContentPane().add(global);
			this.getContentPane().add(local);
			this.getContentPane().add(consensus);
			this.getContentPane().add(reverse);
			this.getContentPane().add(Threshold);
			this.getContentPane().add(save);
			this.getContentPane().add(button);
			
		
		
			add(inputA);
			add(inputB);
			add(thr);

			this.pack();
		}
	

		protected void buttonBerechneClicked() {
			// TODO Auto-generated method stub
			// decide between FileChooser and pasted sequence and convert all letters to capital letter
			sequenceInput1 = null;
			sequenceInput2 = null;
			sequenceInput1 = seq1.getText();
			sequenceInput2 = seq2.getText();
			
			
			sequenceInput1 = CapitalLetters(sequenceInput1);
			sequenceInput2 = CapitalLetters(sequenceInput2);
			String sequenceInput3 = null;
			if (Reverse == true){
				sequenceInput3 = reverse(sequenceInput2);
			} else{
				sequenceInput3 = sequenceInput2;
			}
			
			// get threshold integer from GUI

		
			
			
			// choosing between different methods
			if (Global == true){
				GlobalAlignment glob = new GlobalAlignment(sequenceInput1, sequenceInput3);
				output = glob.Result;
				titelA = "Global Alignment of";
			}
			if (Local == true){
				LocalAlignment2 loc = new LocalAlignment2(sequenceInput1, sequenceInput3);
				output = loc.Result;
				titelA = "Local Alignment of";
				//output = new String[1];
				// output[0]= "local alignment";
				
			}

			if (Consensus == true){
				
				String thr = Threshold.getText();
				int thresholdField;
				if (!thr.equals("Max")){
					thresholdField = Integer.parseInt(thr);
				} else {
					thresholdField = 0;
				}
				consensus con = new consensus (sequenceInput1, sequenceInput3, thresholdField);
				output = con.Result2;
				titelA = "Consensus Sequence Aligment of";
			}
			
			titel = titelA + " " + titelB + " and" + titelC;
			OutputFrame ApplicationOut = new OutputFrame(output, titel);
			
		
			
		}
		
		public static String[] concat(String[] a, String[] b) {
			   int aLen = a.length;
			   int bLen = b.length;
			   String[] c= new String[aLen+bLen];
			   System.arraycopy(a, 0, c, 0, aLen);
			   System.arraycopy(b, 0, c, aLen, bLen);
			   return c;
			}
		public static String CapitalLetters(String B){
			String C = "";
			for (int i= 0; i < B.length(); i++){
				if(B.charAt(i) == 'a'){
					C = C + 'A';
				} else if (B.charAt(i) == 't'){
					C = C + 'T';
				} else if(B.charAt(i) == 'g'){
					C = C + 'G';
				} else if (B.charAt(i) == 'c'){
					C = C + 'C';
				} else if (B.charAt(i) == 'y'){
					C = C + 'Y';
				}else if (B.charAt(i) == 'r'){
					C = C + 'R';
				} else{
					C = C + B.charAt(i);
				}
			}
			return C;
		}
		public static String reverse(String B){
			String C ="";
			for (int i = B.length()-1; i >= 0; i--){
				if(B.charAt(i) == 'A'){
					C = C + 'T';
				} else if (B.charAt(i) == 'T'){
					C = C + 'A';
				} else if(B.charAt(i) == 'G'){
					C = C + 'C';
				} else if (B.charAt(i) == 'C'){
					C = C + 'G';
				} else if (B.charAt(i) == 'Y'){
					C = C + 'R';
				}else if (B.charAt(i) == 'R'){
					C = C + 'Y';
				} else{
					C = C + B.charAt(i);
				}
			}

		
		C = C.replace(" ", "");
			return C;
			
		}
	}


