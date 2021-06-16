package domination;

import java.util.Set;
import java.util.HashSet;

import java.util.Random;

public class Case {
	public static final char[] types = { 'c', 'f', 'i', 'm', 'p', 'o' };
	public static int count = 0;
	final public static int crownMaxNumber = 2;
	public int n = 0;
	public int crown = 0;
	public char type;
	public boolean isNull = false;
	public int tile = 1;

	/*
	 * Return a random monotile filename based on the monotiles names.
	 */
	public static String getRandomMonotileName(char type, int crown, Set<String> monotilesNames) {
		Set<String> filteredNames = new HashSet<String>();
		String startName = String.valueOf(type)+String.valueOf(crown);
		for (String monotileName: monotilesNames) {
			if (monotileName.startsWith(startName))
				filteredNames.add(monotileName);
		}
		int n = new Random().nextInt(filteredNames.size());
		int i = 0;
		String randomMonotileName = "";
		for (String filteredName : filteredNames) {
			if (i == n) randomMonotileName = filteredName;
			i++;
		}
		return randomMonotileName;
	}


	/*
	 * Return a null case.
	 */
	public Case() {
		isNull = true;
	}

	/*
	 * Return a random case.
	 */
	public static Case random() {
		 return new Case(
				types[new Random().nextInt(types.length-1)],
				(int)(crownMaxNumber * Math.random())
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
}
