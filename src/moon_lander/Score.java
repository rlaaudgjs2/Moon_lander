package moon_lander;

public class Score {
	
	public static int score  = 10;
	public static int total  = 0;

	public static int SumScore() {
		if (score > total)
			total = score;
		return total;
	}

	
	public static int PlusScore() {
		return total;
	}

	public static void SetScore(int a) {
		score = a;
	}
	public static void total(int a) {
		score = a;
	}
}
