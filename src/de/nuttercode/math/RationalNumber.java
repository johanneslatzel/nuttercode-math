/**
 * 
 */
package de.nuttercode.math;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotEmpty;
import de.nuttercode.util.assurance.NotEqualLong;
import de.nuttercode.util.assurance.NotNull;

/**
 * represents a rational number with numerator and positive denominator. uses
 * long values - be aware of overflows!
 * 
 * @author Johannes B. Latzel
 *
 */
public class RationalNumber extends Number implements Comparable<RationalNumber> {

	private static final long serialVersionUID = 2329075945875397082L;

	/**
	 * 0 / 1
	 */
	public static final RationalNumber ZERO = new RationalNumber(0, 1);

	/**
	 * parses String s into a {@link RationalNumber} if s is in the form:
	 * "[numerator] / [denominator]" with numerator and denominator being
	 * long-values, e.g. "5 / 7"
	 * 
	 * @param s some not-empty string
	 * @return {@link RationalNumber} represented by s
	 * @throws IllegalArgumentException if s is null, empty, or does not meet the
	 *                                  specified format
	 */
	public static RationalNumber parse(@NotEmpty String s) {
		Assurance.assureNotEmpty(s);
		String[] split = s.split("/");
		if (split.length != 2)
			throw new IllegalArgumentException(
					"split along \"/\" gave " + split.length + " parts instead of 2 (\"numerator / denominator\")");
		return new RationalNumber(Long.parseLong(split[0]), Long.parseLong(split[1]));
	}

	private final long numerator, denominator;

	/**
	 * "[numerator] / 1"
	 * 
	 * @param numerator
	 */
	public RationalNumber(long numerator) {
		this(numerator, 1);
	}

	/**
	 * "[numerator] / [denominator]"
	 * 
	 * @param numerator
	 * @param denominator
	 */
	public RationalNumber(long numerator, @NotEqualLong(value = 0) long denominator) {
		Assurance.assureNotEqual(denominator, 0);
		if (numerator != 0) {
			if (denominator < 0) {
				numerator = -numerator;
				denominator = -denominator;
			}
			long gcd;
			while ((gcd = Euclid.gcd(numerator, denominator)) != 1) {
				numerator /= gcd;
				denominator /= gcd;
			}
			this.numerator = numerator;
			this.denominator = denominator;
		} else {
			this.numerator = 0;
			this.denominator = 1;
		}
	}

	/**
	 * @return numerator
	 */
	public long getNumerator() {
		return numerator;
	}

	/**
	 * @return denominator
	 */
	public long getDenominator() {
		return denominator;
	}

	/**
	 * @return this * -1
	 */
	public RationalNumber negate() {
		return new RationalNumber(-getNumerator(), getDenominator());
	}

	/**
	 * @return 1 / this
	 */
	public RationalNumber reciprocate() {
		return new RationalNumber(getDenominator(), getNumerator());
	}

	/**
	 * @param rationalNumber
	 * @return this + rationalNumber
	 */
	public RationalNumber add(@NotNull RationalNumber rationalNumber) {
		return new RationalNumber(
				rationalNumber.getNumerator() * getDenominator() + getNumerator() * rationalNumber.getDenominator(),
				rationalNumber.getDenominator() * getDenominator());
	}

	/**
	 * @param rationalNumber
	 * @return this - rationalNumber
	 */
	public RationalNumber subtract(@NotNull RationalNumber rationalNumber) {
		return new RationalNumber(
				rationalNumber.getNumerator() * getDenominator() - getNumerator() * rationalNumber.getDenominator(),
				rationalNumber.getDenominator() * getDenominator());
	}

	/**
	 * @param rationalNumber
	 * @return this * rationalNumber
	 */
	public RationalNumber multiply(RationalNumber rationalNumber) {
		return new RationalNumber(getNumerator() * rationalNumber.getNumerator(),
				getDenominator() * rationalNumber.getDenominator());
	}

	/**
	 * @param rationalNumber
	 * @return this / rationalNumber
	 */
	public RationalNumber divide(RationalNumber rationalNumber) {
		return new RationalNumber(getNumerator() * rationalNumber.getDenominator(),
				getDenominator() * rationalNumber.getNumerator());
	}

	/**
	 * scales this number by scalar and returns the result
	 * 
	 * @param scalar
	 * @return this number scaled by scalar
	 */
	public RationalNumber scale(long scalar) {
		return new RationalNumber(getNumerator() * scalar, getDenominator());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Number#intValue()
	 */
	@Override
	public int intValue() {
		return (int) (numerator / denominator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Number#longValue()
	 */
	@Override
	public long longValue() {
		return numerator / denominator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Number#floatValue()
	 */
	@Override
	public float floatValue() {
		return (float) numerator / denominator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Number#doubleValue()
	 */
	@Override
	public double doubleValue() {
		return (double) numerator / denominator;
	}

	@Override
	public int compareTo(RationalNumber o) {
		return (int) Math.signum(subtract(o).numerator);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (denominator ^ (denominator >>> 32));
		result = prime * result + (int) (numerator ^ (numerator >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RationalNumber other = (RationalNumber) obj;
		if (denominator != other.denominator)
			return false;
		if (numerator != other.numerator)
			return false;
		return true;
	}

	/**
	 * @return String-representation by "[numerator] / [denominator]", e.g. "5 / 7"
	 */
	@Override
	public String toString() {
		return numerator + " / " + denominator;
	}

}
