package de.nuttercode.math.matrix;

public interface Matrix {

	/**
	 * sets all values to their default value. the actual behavior depends on the
	 * implementation.
	 */
	void reset();

	/**
	 * @return number of rows
	 */
	int getRowCount();

	/**
	 * @return number of columns
	 */
	int getColumnCount();

	/**
	 * swaps two rows
	 * 
	 * @param row1
	 * @param row2
	 */
	public void swapRows(int row1, int row2);

}
