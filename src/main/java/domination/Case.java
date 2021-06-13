package domination;
import java.util.Random;

public class Case {
	public static final char[] types = { 'c', 'f', 'i', 'm', 'p', 'o' };
	public static int count = 0;
	public int n = 0;
	public int crown = 0;
	public char type;

	public Case() {}

	/*
	 * Return a random case.
	 */
	public static Case random() {
		 return new Case(
				(int)(49 * Math.random()),
				types[new Random().nextInt(types.length)]
		 );
	}

	/*
	 * Initialize a case using the type and number of crowns.
	 */
	public Case(char type, int crown) {
		this.type = type;
		this.crown = crown;
		this.n = count++;
	}

	/*
	 * Return the name of the image of this case.
	 */
	public String getImageName() {
		return String.valueOf(type) + String.valueOf(crown) + "-" + String.valueOf(type2) + String.valueOf(crown2) + ".png";
	}
}
