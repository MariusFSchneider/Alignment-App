
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class AlignmentByThreshold extends JFrame {
	JTextField seq1;
	JTextField seq2;
	JLabel inputA;
	JLabel inputB;
	JFileChooser seqA;
	JFileChooser seqB;
	JFileChooser save;
	JCheckBox global;
	JCheckBox local;
	JCheckBox consensus;
	JCheckBox reverse;
	JButton button;
	JLabel thr;
	JTextField Threshold;

	
	
	
		public AlignmentByThreshold(){
			setVisible(true);
			setSize(600, 800);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Alignment of 2 sequences");
			setResizable(true);

			
			
			
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
			// Instanzieren:
			inputA = new JLabel ("paste sequence 1 or choose FASTA");
			inputA = new JLabel ("paste sequence 2 or choose FASTA");
			seq1 = new JTextField();
			seq2 = new JTextField();
			seqA = new JFileChooser();
			seqB = new JFileChooser();
			save = new JFileChooser();
			global = new JCheckBox("global");
			local = new JCheckBox("local");
			reverse = new JCheckBox("reverse");
			consensus = new JCheckBox("consensus");
			button = new JButton("Get Alignment");
			thr = new JLabel("Threshold");
			Threshold = new JTextField();
			;

			button.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						buttonBerechneClicked();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}		

			});


			
			
			seq1.setBounds(5, 40, 590, 100);
			seqA.setBounds(5, 145, 200, 25);
		
			seq2.setBounds(5, 205, 590, 100);
			seqB.setBounds(5, 310, 200, 25);
			global.setBounds(5, 340, 5, 5);
			local.setBounds(5, 370, 5,5);
			consensus.setBounds(5, 400, 5, 5);
			reverse.setBounds(305, 340, 5,5);
			thr.setBounds(305, 370, 200, 25);
			Threshold.setBounds(305, 400, 200, 25);
			button.setBounds(5, 430, 200, 25);
			save.setBounds(305, 430, 200, 25);
		
			
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
		
			


		


			this.pack();
		}
		protected void buttonBerechneClicked() throws IOException {
			// TODO Auto-generated method stub
			String r = textfield_vector.getText();
			String t = textfield_fw.getText();
			String Method = textfield_method.getText();
			String Path = resultPath.getText();
			String k1 = threshold.getText();
			int k = Integer.parseInt(k1);
			String C = "";
			String A = "";
			String B ="";
			
			
			A = r.replace("\\s", "");
			B = t.replace("\\s", "");
			
			A = A.replace(" ", "");
			B = B.replace(" ", "");
			
			A = A.replace("\\n", "");
			B = B.replace("\\n", "");
			
			
			if(Method.equals("reverse")){
				for (int i = B.length()-1; i >= 0; i--){
					if(B.charAt(i) == 'A'){
						C = C + 'T';
					} else if (B.charAt(i) == 'T'){
						C = C + 'A';
					} else if(B.charAt(i) == 'G'){
						C = C + 'C';
					} else if (B.charAt(i) == 'C'){
						C = C + 'G';
					} else{
						C = C + B.charAt(i);
					}
				}
			}else{
				C =B;
			}
			
			C = C.replace(" ", "");
			
			localAlignmentThreshold TTTest = new localAlignmentThreshold(A,C,k);

			
			int le = 55;
			String[][] Result = TTTest.Result;
			String[] sequences = Result[0];
			String[] positions = Result[1];
			positions = addElement(positions, "");
			sequences = addElement(sequences, "");
			int j1 = (A.length()/le)+1;
			int i1 = sequences.length;

			
			int [] subpositionsA = new int [0];
			int [] subpositionsB = new int [0];
			String[] subsequencesA = new String[0];
			String[] subsequencesB = new String[0];
			
			int b1;
			for (int j=0; j < j1; j++){
				int a1 = (le*j);
				if ((le*(j+1)) <= (A.length())) {
					b1 = (le*(j+1));
				} else {
					b1 = (A.length());
				}
				String meta1 = "";
				String meta2 = "";

					for (int i=0; i < i1; i++){
						
						subpositionsA = addElement2(subpositionsA, a1);
						subpositionsB = addElement2(subpositionsB, b1);
						meta1 = sequences[i];
						meta2 = positions[i];
						subsequencesA = addElement(subsequencesA, meta1);
						subsequencesB = addElement(subsequencesB, meta2);
					}
				} 
			
			String[] Local ;
			String[] Result2 = new String[subpositionsA.length];
			String meta7 = "";
			String meta3 = "";
			String meta4 = "";
			String meta5 = "";
			String meta6 = "";
			for (int i =0; i < subpositionsA.length; i++){
				meta7 = subsequencesA[i];
				if(meta7.length() > 0){
				int a2 = subpositionsA[i];
				int b2 = subpositionsB[i];
				int c2 = a2 +1;
				int d2 = b2 -1;
				meta3 = subsequencesB[i];
				Local = meta3.split("\t");
				meta4 = subsequencesB[i].substring(d2, b2);
				meta5 = subsequencesA[i].substring(a2, b2);
				meta6 = Local[a2] + "\t" + meta5 + "\t" + Local[d2];
				Result2[i]= meta6;
				} else {
					Result2[i]= "";	
				}
			}
			
			


			for (int i = 0; i < Result2.length; i++){
				result.append(Result2[i]+ "\n");
			}
			

			PrintTXT TestPrint = new PrintTXT(Result2, "LocalAlignment", Path);


		}			
		public static String[] addElement(String[] a, String e) {
		    a  = Arrays.copyOf(a, a.length + 1);
		    a[a.length - 1] = e;
		    return a;
		}
		public static int[] addElement2(int[] a, int e) {
		    a  = Arrays.copyOf(a, a.length + 1);
		    a[a.length - 1] = e;
		    return a;
		}
}
