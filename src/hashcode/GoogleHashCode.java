package hashcode;

public class GoogleHashCode {

	public static void main(String[] args) {
		
		//TrialRound.trialV1();
		
		long startTime = System.currentTimeMillis();		
		
		TrialRound.trialV2();
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Time: " + ((endTime - startTime) / 1000) + " seconds");
	}
}
