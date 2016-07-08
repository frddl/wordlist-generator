public class Main {
	static char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPRQSTUVWXYZ0123456789!@#$%^&*()".toCharArray();
	static int counter = 0;
	static int startLength = 6;	// min length
	static int upperBound = 15;	// max length
	static int total = 2000000000;	// total number of words
	static long start, end, time;

	static void generate(StringBuilder sb, int n) {
		if (counter == total) {
			end = System.nanoTime();
			time = ((end-start)/1000000);
			statReport();
			System.exit(0);
		}
			
		if (n == sb.length()) {
			System.out.println(sb.toString() + " | #" + counter);
			counter++;
			return;
		}
        
		for (char letter : alphabet) {
			sb.setCharAt(n, letter);
			generate(sb, n + 1);
		}
	}

	public static void startGen(StringBuilder sb, int l){
		if (l > upperBound) {
			end = System.nanoTime();
			time = ((end-start)/1000000);
			statReport();
			System.exit(0);
		}
		sb.setLength(l);
		generate(sb, 0);
		startGen(sb, l+1);
	}
	
	public static void statReport(){
		System.out.print("\nTook: " + time + " ms | approx: " + (time/1000) + "sec");
		System.out.print("\n" + counter + " passwords total\n");
		System.out.print("Average speed: ~" + (counter/time) + " passwords/ms\n");
	}

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		start = System.nanoTime();
		startGen(sb, startLength);
	}	
}
