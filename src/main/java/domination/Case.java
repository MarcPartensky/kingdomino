package domination;
import java.util.Random;

public class Case {
	public int n = 0;
	public int crown = 0;
	public char type;
	public final char types = { 'c', 'f', 'i', 'm', 'p', 'o' };

	public Case() {}

	public static Case random() {
		 return new Case(
				(int)(49 * Math.random()),
				(int)(49 * Math.random()),
				types[new Random().nextInt(types.length)]
		 );
	}

	public Case(int n, int crown, char type) {
		this.n = n;
		this.crown = crown;
		this.type = type;
	}
}
