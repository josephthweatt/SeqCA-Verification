import SeqCAVerifier.SeqCAVerifier_main;

import java.io.*;

public class TestSeqCA {

		public static void main(String[] args) throws FileNotFoundException {
			for (int i = 1; i <= 7; i++) {
				File testCase = new File("SeqCA's\\SeqCA(8;3,6) Variant No " + i + ".txt");
				SeqCAVerifier_main test = new SeqCAVerifier_main(testCase);
				System.out.println(test.isValidSeqCA());
			}
		}
}
