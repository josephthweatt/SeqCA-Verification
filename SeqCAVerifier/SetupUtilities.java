package SeqCAVerifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SetupUtilities {
	//SetupUtilities will verify whether or not the text file has the appropriate structure of a SeqCA.
	//If it does, then the seqCA body will be built and returned
	public boolean verifyParameters(int[] parameters) {
		/****************************************************************************
		 * Code will test the parameters on 3 conditions: 1. That the parameters
		 * are all integers (no code needed) 2. No SeqCA parameter is <= 0 3.
		 * That the strength (t) does not exceed the length of the array (v) If
		 * these parameters are false, we return true. If params are valid,
		 * return false
		 *****************************************************************************/
		if (parameters[0] <= 0 || parameters[1] <= 0 || parameters[2] <= 0) // will return true if 0's or neg's are detected
			return true;
		else if (parameters[1] > parameters[2]) // will return true if the
			return true;						// strength is greater than (v)
			
		return false;
	}

	public int[][] createBody(File fileInput, int[] parameters)
			throws FileNotFoundException // parameters included to make rigid
											// array
	{ // Rigid array will make errors in the .txt easier to spot
		Scanner readInputFile = new Scanner(fileInput);
		readInputFile.next(); // these will skip past the parameters and go to
								// the
		readInputFile.next(); // body of the text file
		readInputFile.next();

		int[][] seqCA_arrays = new int[parameters[0]][parameters[2]];

		try {
			for (int N = 0; N < parameters[0]; N++) {
				for (int v = 0; v < parameters[2]; v++)
					seqCA_arrays[N][v] = readInputFile.nextInt(); // loops will
																	// fill
																	// dimensions
																	// of SeqCA
																	// body
			}
		} catch (NoSuchElementException exception) {
			readInputFile.close(); // will return null if amount of integers do
									// not fill the SeqCA dimensions
			exception.printStackTrace();
			return null;
		}

		if (readInputFile.hasNextInt()) { // will return null if there are
											// excess integers. That should not
											// happen in
			readInputFile.close(); // in a proper SeqCA
			return null;
		}
		readInputFile.close();
		return seqCA_arrays;
	}
}
