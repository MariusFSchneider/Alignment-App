
public class GlobalAlignment {

	public String A = "";
	public String B= "";
	public int [][] Distance;
	
	public int [][]TraceBack;
	
	public String[] PositionA;
	public String[] PositionB;
	public String[] Result;
	

	GlobalAlignment(String inputA, String inputB){

		inputA = inputA.replace(" ", "");
		inputA = inputA.replace("\\n", "");
		inputA = inputA.replace("\\s", "");
		inputB = inputB.replace(" ", "");
		inputB = inputB.replace("\\n", "");
		inputB = inputB.replace("\\s", "");
		
		String[] InputA = null;
		String[] InputB = null;
		
		String positionA = "";
		String positionB = "";
		
		
		InputA = inputA.split("");
		InputB = inputB.split("");
	
		
		Distance = new int[(InputB.length)+1][(InputA.length)+1];
		TraceBack = new int[(InputB.length)+1][(InputA.length)+1];
		
		for (int i = 1; i < (InputA.length+1);i++){
			Distance[0][i] = i*-1;
		}
		for (int j = 1; j < (InputB.length+1);j++){
			Distance[j][0] = j*-1;
		}
		
		for (int i = 1; i < (InputA.length+1);i++){
			TraceBack[0][i] = 1;
		}
		for (int j = 1; j < (InputB.length+1);j++){
			TraceBack[j][0] = -1;
		}
		
		
		
		for (int i = 1; i < (InputB.length+1);i++){
			for (int j = 1; j < (InputA.length+1);j++){
				int vert = Distance[i][j-1]+ distance("-", InputA[j-1]);
				int diag = Distance[i-1][j-1]+ distance(InputB[i-1],InputA[j-1]);
				int hor =  Distance[i-1][j]+ distance(InputB[i-1], "-");
				Distance[i][j] = Math.max((Math.max(vert, diag)), hor);
						
				if (Distance[i][j] == diag ){
					
					TraceBack[i][j] = 0;
				}else if(Distance[i][j] == hor){
					
					TraceBack[i][j] = -1;				
				}else{
					
					TraceBack[i][j] = +1;
				}
			}
		}
		int k = TraceBack.length-1;
		int l = TraceBack[0].length-1;
		
		while (k >= 0 && l >= 0 ){

			if (TraceBack[k][l] == 0 && k > 0 && l > 0){
				A =  inputA.charAt(l-1) + A;
				B =  inputB.charAt(k-1) + B;
				positionA = l + "\t" + positionA;
				positionB = k + "\t" + positionB;
				k--;
				l--;
			} else if(TraceBack[k][l]==-1 && k > 0){
				A = "-" + A;
				B =  inputB.charAt(k-1) + B;
				positionA = k + "\t" + positionA;
				positionB = l + "\t" + positionB;
				k--;
			} else if (TraceBack[k][l] == 1 && l > 0){
				A = inputA.charAt(l-1) + A;
				B = "-" +B;
				positionA = l + "\t" + positionA;
				positionB = k + "\t" + positionB;
				l--;
			} else {
				break;
			}
			
			PositionA = positionA.split("\t");
			PositionB = positionB.split("\t");
		
		}
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
			
			
	}
	public int distance(String a, String b){
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
