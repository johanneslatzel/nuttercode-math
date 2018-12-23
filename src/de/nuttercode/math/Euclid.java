package de.nuttercode.math;

public class Euclid {

	/**
	 * modern euclids algorithm for greatest common denominator in iterative version
	 * 
	 * @param a
	 * @param b
	 * @return greatest common denominator
	 * @see <a href=
	 *      "https://de.wikipedia.org/wiki/Euklidischer_Algorithmus#Iterative_Variante">wikipedia
	 *      entry</a>
	 */
	public static long gcd(long a, long b) {
		long h;
		while (b != 0) {
			h = a % b;
			a = b;
			b = h;
		}
		return a;
	}

}
