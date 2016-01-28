package SeqCAVerifier;

public class VerifyCoverage {
	
	private int[][] seqCA;
	private static boolean seqCAIsValid;
	private static int param_t, param_v;
	private static EssentialGeneraIndex uniqueGenera;
	
	public VerifyCoverage(int[][] seqCA_arrays, int[] parameters, boolean ofOneVariable)
	{
		seqCA = seqCA_arrays;
		param_t = parameters[1];
		param_v = parameters[2];
		uniqueGenera = new EssentialGeneraIndex(param_t, param_v, ofOneVariable);
		seqCAIsValid = false;
	}
	
	public boolean verify() 
	{
		for (int singleArray = 0; singleArray < seqCA.length; singleArray++)
		{	//verify will take each array individually and put them through the verifySingleArray method, 
			//which will return true if all essential genera have been found
			int[] slides = new int[param_t];
			verifySingleArray(seqCA[singleArray], 0, 0, slides);
			if (seqCAIsValid)
				return true;
		}
		return false; //if it does not return true by the end of the seqCA, it is thereby a false seqCA
	}
	
	/*********************************************************************************************
	 verifySingleArray is a recursive algorithm intended to check every permutation in an array.
	 It finds permutation through "slides", which, like an Abacus, moves left to right along the 
	 array without surpassing the slide in front of it. The amount of slides is determined through 
	 the t-parameter. Slides start on the left side of the array (ex: array 0 1 2 3 4 with t=3 
	 will start slides on 0, 1, 2, respectively) and work their way to the right.
	 ********************************************************************************************/
	private static void verifySingleArray(int[] array, int startIndex, int previousSlideIndex, int[] slides)
	{
		if (param_t == 1) { //any array with a strength of one will certainly have all permutations
			seqCAIsValid = true;
			return;
		}
		
		if (startIndex == 0) //will check for the leftmost(first) slide
		{
			for (int i = startIndex; i < array.length - (param_t - (startIndex + 1)); i++)
			{
				slides[startIndex] = array[i];
				verifySingleArray(array, startIndex + 1, i, slides);
			}
			return; //will return false if there are still undiscovered EG's 
		}
		else if (startIndex == param_t - 1) //will check for the rightmost(last) slide
		{
			for (int i = startIndex; i < array.length - (param_t - (startIndex + 1)); i++)
			{
				if (i <= previousSlideIndex)
					i = previousSlideIndex + 1;
				slides[startIndex] = array[i];
				
				overrideMethod(slides);
			}
		}
		else	//will deal with intermediary slides
		{
			for (int i = startIndex; i < array.length - (param_t - (startIndex + 1)); i++)
			{
				if (i <= previousSlideIndex)
					i = previousSlideIndex + 1;
				slides[startIndex] = array[i];
				verifySingleArray(array, startIndex + 1, i, slides);
			}
		}
		return;
	}
	
	public static void overrideMethod(int[] slides) {
		if (uniqueGenera.checkForEG(slides))//this will send the permutation over to EssentialGeneraIndex class.
			seqCAIsValid = true;			//will return true if all essential genera are found
	}
}
