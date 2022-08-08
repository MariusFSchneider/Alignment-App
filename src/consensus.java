import java.util.Arrays;

public class consensus {
	public String[][] Result;
	public int[][] Distance;
	public int amount;
	public int[] position;
	public String[] Result2;
	public consensus(String inputA, String inputB, int threshold){
		
		inputA = inputA.replace(" ", "");
		inputA = inputA.replace("\\n", "");
		inputA = inputA.replace("\\s", "");
		inputB = inputB.replace(" ", "");
		inputB = inputB.replace("\\n", "");
		inputB = inputB.replace("\\s", "");
		
		String[] InputA = null;
		String[] InputB = null;
		
		if (inputA.length() > inputB.length()){
			InputA = inputA.split("");
			InputB = inputB.split("");
		} else{
			InputA = inputB.split("");
			InputB = inputA.split("");	
		}

		
		

		
		
	// initialize Distance Matrix according to Watermann Smith Algorithmn: set 1st column and Row values to 1
		Distance = new int [InputA.length][InputB.length];
		for (int i = 0; i < InputA.length; i++){
			Distance[i][0] = 0;
		}
		for (int i = 0; i < InputB.length; i++){
			Distance[0][i] = 0;
		}
		
	// calculation of Distance Matrix, no Gap Penalties	
		for (int i= 1; i < InputB.length; i++){
			for (int j=1; j < InputA.length; j ++){
				Distance[j][i] = Distance[j-1][i-1] + distance(InputA[j], InputB[i]);
			}
		}
		
	// 
		int Max = 0;
		for (int i=0; i < InputA.length; i++){
			if (Distance[i][(InputB.length)-1] > Max){
				Max = Distance[i][(InputB.length)-1];
			}
		}
		
		if (threshold == 0){
			threshold = Max;
		}
		
	position = new int[0];
	

	for (int i=0; i < InputA.length; i++){
		if (Distance[i][(InputB.length)-1] >= threshold){
			amount = amount +1;
			position = addElement(position, i);
		}
	}
	String PositionA = "";
	for (int i = 0; i < InputA.length; i++){
		int Z = i+1;
		PositionA = PositionA + "\t"+ Z;
	}
	Result = new String[2][amount+1];
	Result[0][0] = inputA;
	Result[1][0] = PositionA;
	
	for (int j = 0; j < position.length; j++){
		int Pos = position[j];
		String sequence = "";
		String positions = "";
		for (int i = InputA.length; i > 0; i--){
			if ((i-1) > Pos){
				sequence =  "-" + sequence;
				positions = InputB.length + "\t" + positions ;
		} else if ((i-1) <= Pos & (i-1) > (Pos-InputB.length) ){
			int B1 = Pos - (i-1);
			int A1 = InputB.length - 1;
			int C1 = A1 - (B1);
			int D1 = C1 +1;
			sequence =  InputB[C1] + sequence;
			positions = D1 + "\t" + positions;
		} else {
			sequence =  "-" + sequence;
			positions = 0 + "\t"+ positions ;
		}
			Result[0][j+1] = sequence;
			Result[1][j+1] = positions;
	}

	}
	int le = 60;
	String[] sequences = Result[0];
	String[] positions = Result[1];
	positions = addElement(positions, "");
	sequences = addElement(sequences, "");
	int j1 = (inputA.length()/le)+1;
	int i1 = sequences.length;

	
	int [] subpositionsA = new int [0];
	int [] subpositionsB = new int [0];
	String[] subsequencesA = new String[0];
	String[] subsequencesB = new String[0];
	
	int b1;
	for (int j=0; j < j1; j++){
		int a1 = (le*j);
		if ((le*(j+1)) <= (inputA.length())) {
			b1 = (le*(j+1));
		} else {
			b1 = (inputA.length());
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
    Result2 = new String[subpositionsA.length];
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
	
	

	
	}
	public int distance(String a, String b){
		int distance = 0;
		if (a.equals(b)){
			distance = 3;
		}else if (a.equals("A") & b.equals("R")){
			distance = 1;
		}else if (a.equals("G") & b.equals("R")){
			distance = 1;	
		}else if (a.equals("T") & b.equals("Y")){
			distance = 1;	
		}else if (a.equals("C") & b.equals("Y")){
			distance = 1;	
		}else if (b.equals("N")){
			distance = 1;	
		}else{
			distance = 0;
		}
		return distance;
		
	}
	public int[] addElement(int[] a, int e) {
	    a  = Arrays.copyOf(a, a.length + 1);
	    a[a.length - 1] = e;
	    return a;
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
