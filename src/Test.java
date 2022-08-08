import java.util.Arrays;

import javax.swing.JFileChooser;

public class Test {


	
	public static void main(String[] args) {
		
		 String A = "";
		 String B= "";
		 int [][] Distance;
		
	  int  [][]TraceBack;
		
		 String[] PositionA = null;
		String[] PositionB = null;
		 String[] Result;
		// TODO Auto-generated method stub
		String inputA = "ATGAAAACGTT";
		String inputB = "ATGACG";
		inputA = inputA.replace(" ", "");
		inputA = inputA.replace("\\n", "");
		inputA = inputA.replace("\\s", "");
		inputB = inputB.replace(" ", "");
		inputB = inputB.replace("\\n", "");
		inputB = inputB.replace("\\s", "");
		int al = inputA.length();
		int bl = inputB.length();
		String AA = "";
		String BB = "";
		// look for longer sequence
		if (al < bl){
			AA = inputB;
			BB = inputA;
		} else{
			BB = inputB;
			AA = inputA;
		}
		
		String[] InputA = null;
		String[] InputB = null;
		
		String positionA = "";
		String positionB = "";
		
		
		InputA = AA.split("");
		InputB = BB.split("");
	
		
		Distance = new int[(InputB.length)][InputA.length];
		TraceBack = new int[(InputB.length)][InputA.length];
		
		for (int i = 1; i < (InputA.length);i++){
			Distance[0][i] = 0;
		}
		for (int j = 1; j < (InputB.length);j++){
			Distance[j][0] = 0;
		}
		
		for (int i = 1; i < (InputA.length);i++){
			TraceBack[0][i] = -1;
		}
		for (int j = 1; j < (InputB.length);j++){
			TraceBack[j][0] = 0;
		}
		
		
		
		for (int i = 1; i < (InputB.length);i++){
			for (int j = 1; j < (InputA.length);j++){
				
				int diag = Distance[i-1][j-1]+ distance(InputB[i-1],InputA[j-1]);
				int hor =  Distance[i-1][j]+ distance(InputB[i-1], "-");
				Distance[i][j] = Math.max(hor, diag);
						
				if (Distance[i][j] == diag ){
					
					TraceBack[i][j] = 1;
				} else{
					
					TraceBack[i][j] = -1;
				}
			}
		}
for (int i = 0; i < Distance.length; i++){
	String AAA = "";
	for (int j = 0; j < Distance[0].length; j++){
		AAA = AAA + "\t"+ Distance[i][j];
	}
	System.out.println(AAA);
}
System.out.println("----------------------");
for (int i = 0; i < TraceBack.length; i++){
	String BBB = "";
	for (int j = 0; j < TraceBack[0].length; j++){
		BBB = BBB + "\t"+  TraceBack[i][j];
	}
	System.out.println(BBB);
}			

int k = TraceBack.length-1;
int l = TraceBack[0].length-1;

while (k >= 0 && l >= 0 && TraceBack[k][l] != 0){

	 if (TraceBack[k][l] == -1){
			A = InputA[l-1] + A;
			B = "-" +B;
			positionA = l + "\t" + positionA;
			positionB = k + "\t" + positionB;
			l--;
		} else if (TraceBack[k][l] == 1 ){
		A =  InputA[l-1]  + A;
		B =  InputB[k-1]  + B;
		positionA = l + "\t" + positionA;
		positionB = k + "\t" + positionB;
		k--;
		l--;
	 }  else {
		break;
	}
}
	
	PositionA = positionA.split("\t");
	PositionB = positionB.split("\t");
	
	System.out.println("--------------------");
	System.out.println(A);
	System.out.println(B);
	
	String C = "";
	for (int i = 0; i < A.length(); i++){
	 if (A.charAt(i) == (B.charAt(i)) ){
		C = C + "|"; 
	 } else {
		 C = C + " ";
	 }
	}
	
	Result = new String[(((A.length() /60)+1)*4)];


	for (int i = 0; i < (Result.length/4); i++){
		int a = A.length();
		int b = 60*i;
		int c = 60*(i+1);
		if(a >= c){
		Result[4*i] = PositionA[b] + "\t"+ A.substring(b,c) + "\t"+ PositionA[c-1] ; 
		Result[(4*i)+1] = "\t"+  C.substring(b,c) ; 
		Result[(4*i)+2] = PositionB[b] + "\t"+ B.substring(b,c) + "\t"+ PositionB[c-1];
		Result[(4*i)+3] = "";
		} else {
			Result[4*i] = PositionA[b] + "\t"+ A.substring(b,a) + "\t"+ PositionA[a-1] ; 
			Result[(4*i)+1] =  "\t"+ C.substring(b,a) ; 
			Result[(4*i)+2] = PositionB[b] + "\t"+ B.substring(b,a) + "\t"+ PositionB[a-1];
			Result[(4*i)+3] = "";
		}
	}		
	
	for (int i = 0; i< Result.length; i++){
		System.out.println(Result[i]);
	}
	
	System.out.println(Result.length);
	
	}
	public static int distance(String a, String b){
		int distance = 0;
		if (a.equals(b)){
			distance = 1;
		}else if (a.equals("-") == false && b.equals("-")== false){
			distance = -1;
		}else{
			distance = 0;
		}
		return distance;
		
	}
}
