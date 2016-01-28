This repository holds the work conducted when researching under Dr. Charles Colburn. This was an unpaid, unofficial project I had done freshman year, involving the exploration of combinatorics in the form of Sequence Covering Arrays (abbreviated SeqCA). More information about the SeqCA can be found here:
	http://www.csse.monash.edu.au/~gfarr/research/slides/Colbourn-monashbeamer.pdf

Purpose
-------
The code in this repository works to determine whether a seqCA is valid--that is, whether it covers all combinations of length 't' in the set of variables 'v'. 

The program works by recieving a text file of a seqCA (with the format demonstrated in the 'Test Cases' folder), and returning a 'true' if the seqCA has full coverage, and 'false' if it does not. The file path in the main method will need to be changed for the program to work.

Room for Improvement
--------------------
I have taken time off from this project to begin work on FURI, but find that if I do return to the research, a couple improvements can be made to the verifier to improve speed and performance:

1. Implement Hashmaps, instead of arrays, to index the rows. Doing so would allow the program to access the information faster, and would clarify the difference between a values position in the column and the value itself. 

2. Possible multithreading, for extra long seqCAs.

3. Simplifying & cleaning the code. Since I had made this program only a few months after learning to program, I would expect to find some of the code to be confusing and verbose.