
public class Test2 {

	public static void main(String[] args) {
		String inputA = "ATGAAAACGTT";
		String inputB = "ATGACG";
		// TODO Auto-generated method stub
		String[] output;
		int threshold = 0;
		consensus loc = new consensus(inputA, inputB, threshold);
		output = loc.Result2;
		
		for (int i = 0; i < output.length; i++){
		System.out.println(output[i]);
		}
		
	}
	
}
