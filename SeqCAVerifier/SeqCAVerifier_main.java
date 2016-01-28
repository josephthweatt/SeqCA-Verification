//The verifySeqCA method within this class will verify that the given file is a valid
//seqCA. That is, it is structured around the three parameters (listed as
//the first three integers in the file) and provides all permutations of
//the t-strength within the given size (variable 'v').

package SeqCAVerifier;

import java.util.Scanner;
import java.io.*;

public class SeqCAVerifier_main { 
	private int[] parameters;
	private int[][] seqCA_arrays;
	
	public SeqCAVerifier_main(File fileInput) throws FileNotFoundException { 
		/*******************************************************************
		 First, the program will create the arrays and the parameters
		 with which to analyze the structure of the seqCA. If the 
		 file has faulty parameters of array sizes, it will exit prematurely
		 *******************************************************************/
		//first it creates a file from the file address
		Scanner fileScanner = new Scanner(fileInput); //...and then it puts it into a Scanner
		
		int[] parameters = {fileScanner.nextInt(),fileScanner.nextInt(), fileScanner.nextInt()}; //takes in the parameters of the SeqCA
		this.parameters = parameters;
		
		SetupUtilities setup = new SetupUtilities();
		if (setup.verifyParameters(parameters)) //will close the program if the params are not valid (verify will return false if they're valid)
		{										
			System.out.println("parameters do not work");
			fileScanner.close();
			System.exit(0);
		}
		//here, the code creates the seqCA array body using the given file
		seqCA_arrays = setup.createBody(fileInput, parameters);  //will be null if numbers in the file do not
																	//fill the specified dimensions
		if (seqCA_arrays == null) 
		{ //seqCA_arrays are null when it is not properly formatted. If so, the program will not continue.
			System.out.println("There are too many/too few integers in this file");
			fileScanner.close();
			System.exit(0);
		}
	}
	
	public boolean isValidSeqCA() {	
		/************************************************************
		 Now that the SeqCA has been created, we may begin verifying 
		 that the array covers all essential genera.
		 ************************************************************/
		boolean inspectOneVariable = false; //the EGIndex will hold permutations for all variables
		VerifyCoverage validateSeqCA = new VerifyCoverage(seqCA_arrays, parameters, inspectOneVariable); //VerifyCoverage returns a boolean value, determining
		if (validateSeqCA.verify())													 //the validity of the seqCA
			return true;
		else
			return false;
	}
	
	public int[] getParameters() {
		return parameters;
	}
	
	public int[][] getSeqCA() {
		return seqCA_arrays;
	}
}
