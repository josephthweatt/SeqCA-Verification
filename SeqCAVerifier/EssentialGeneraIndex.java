package SeqCAVerifier;

public class EssentialGeneraIndex {

	private int[][] essentialGenIndex;
	public int totalEssentialGen;
	public int essentialGenFound;
	private int param_t, param_v;
	public static int count = 1;
	
	public EssentialGeneraIndex(int param_t, int param_v, boolean ofOneVariable) //this will create an array to hold all essential genera
	{
		this.param_t = param_t;
		this.param_v = param_v;
		if (ofOneVariable) { //this object may be made to track the presence of only one variable, if needed
			totalEssentialGen = calcEGForOneVariable();
		} else {
			totalEssentialGen = calcAllEssentialGen(param_v);
		}
		essentialGenIndex = new int[totalEssentialGen][param_t];
		essentialGenFound = 1; //will not have found any EG's at first
	}

	private int calcAllEssentialGen(int param_v) {
		/************************************************************************************************ 
		 * This method will find how many essential genera are in an array. This implements the equation:
		 * (v!)/(v-t)!
		 * which gives the number for the total amount of permutations given a strength
		 ************************************************************************************************/
		int vFactorial = 1;
		int denominator = 1;
		for (int increment = 1; increment <= param_v; increment++) //sets vFactorial
			vFactorial *= increment;	
		for(int increment = 1; increment <= (param_v - param_t); increment++) //sets the denominator of the equation above
			denominator *= increment;

		totalEssentialGen = vFactorial / denominator;
		return totalEssentialGen;
	}
	
	private int calcEGForOneVariable() {
		//The amount of times one variable is present in the seqCA is found through: 
		// (TotalEG's with variable included) - (TotalEG's without variable)
		int totalEGWithVariable = calcAllEssentialGen(param_v);
		int totalEGWithoutVariable = calcAllEssentialGen(param_v - 1);
		return totalEGWithVariable - totalEGWithoutVariable;
	}

	public boolean checkForEG(int[] permutation) //determines whether a permutation is new. will return true only if all genera have been found
	{
		for (int index = 0; index < totalEssentialGen; index++)
		{
			if (essentialGenIndex[index][0] == 0 && essentialGenIndex[index][1] == 0) //two zeroes is proof that the sub-array has not been filled
				return addEG(permutation, index); //if it has reached this point, it means this was the first time finding that specific permutation

			if (essentialGenIndex[index][0] == permutation[0]) //verifies an array if the first numbers match
				if(investigateIndex(index, permutation)) //returns true when an array matches
					return false; //will effectively exit this "for" loop
			//if it does not match, it will then check to see if the first numbers of essentialGenIndex goes past the 
			//first int of "permutation". It will return false if this happens
			if (essentialGenIndex[index][0] > permutation[0])
				return addEG(permutation, index); //"permutation" will be added to the index once the  True will terminate verify()		
		}
		return false; //will likely never reach this
	}

	private boolean investigateIndex(int index, int[] permutation) { //this will determine if the permutation within the essentialGen
																		//index is the same as the permutation being considered
		for (int increment = 0; increment < param_t; increment++) //will go through all values at a given index to determine if the array matches
		{
			if (essentialGenIndex[index][increment] != permutation[increment])
				return false; //returns false if the permutations don't match
		}
		return true; //returns true if permutations do match
	}

	public boolean addEG(int[] essentialGen, int index) //adds essential genera to the #index wherein the first number of the essentialGen 
	{ 									 				 //was less than that of the essentialGenIndex
		int[] tempIndex = new int[param_t];
		for (int moveBack = totalEssentialGen - 2; moveBack >= index; moveBack--) //moveBack == (size of index - 1)
		{ //basic insertion algorithm to insert the new genera
			if (!(essentialGenIndex[moveBack][0] == 0 && essentialGenIndex[moveBack][1] == 0)) //will skip moving an array if it has 
			{																				   //not been given permutation
				for (int i = 0; i < param_t; i++) //enters the permutation into the tempIndex
					tempIndex[i] = essentialGenIndex[moveBack][i];
				for (int i = 0; i < param_t; i++) //enters the tempIndex into the index above it
					essentialGenIndex[moveBack + 1][i] = tempIndex[i];
			}
		}
		for (int i = 0; i < param_t; i++) //enters the permutation into the index
			essentialGenIndex[index][i] = essentialGen[i];
		essentialGenFound++; //updates the amount off essential generas already located
		return hasAllEssentialGen();
	}

	public boolean hasAllEssentialGen() 
	{ //this will ultimately decide if the seqCA is valid. It say true if all essential genera are covered.
		if (essentialGenFound == totalEssentialGen) {
			return true;
		}
		else {
			return false;
		}
	}
}
