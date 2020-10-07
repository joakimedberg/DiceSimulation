import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * A simulation of a thousand dice throws.
 * Assignment 2, Javautveckling @ Nackademin -20.
 * 
 * @author Joakim Edberg
 * 
 *
 */
public class Dice {

	/**
	 * Simulates a thousand dice throws and stores the values in a text file.
	 * 
	 * @throws IOException If an I/O error occurs
	 */
	public static void createFile() throws IOException {
		BufferedWriter writer;
		Random random = new Random();

		writer = new BufferedWriter(new FileWriter("values.txt"));

		for (int i = 1; i <= 1000; i++) {
			int value = random.nextInt((6 - 1) + 1) + 1;
			writer.write(value + " ");
		}

		writer.close();

	}

	/**
	 * Returns the values of the simulated dice throws.
	 * <p>
	 * Fetches the data from the text file, converts it to numeric values and
	 * returns it as an array.
	 * 
	 * @return the values of the dice throws
	 * @throws IOException If an I/O error occurs
	 */
	public static int[] createArray() throws IOException {
		int[] values = new int[1000];
		int i = 0;

		BufferedReader reader = new BufferedReader(new FileReader("values.txt"));
		
		// loops through the text file. 
		// reads the character, converts it into numeric value and stores it in an array.
		// skips every other character since it's a space.
		while (i < 1000) {
			values[i++] = Character.getNumericValue((reader.read()));
			reader.skip(1);
			
		}

		reader.close();

		return values;
	}

	/**
	 * Returns a summary of the number of times each value have been simulated.
	 * <p>
	 * Receives the simulated dice throws as a parameter, calculates the number of times each value
	 * have been simulated and returns it as an array.
	 * 
	 * @param values the simulated dice values
	 * @return summary of the values
	 */
	public static int[] analyseArray(int[] values) {
		int[] summary = new int[6];

		for (int num : values) {
			if (num == 1) {
				++summary[0];
			} else if (num == 2) {
				++summary[1];
			} else if (num == 3) {
				++summary[2];
			} else if (num == 4) {
				++summary[3];
			} else if (num == 5) {
				++summary[4];
			} else if (num == 6) {
				++summary[5];
			}
		}
		return summary;
	}

}

class Main {

	public static void main(String args[]) {

		try {
			Dice.createFile();
			// int[] values = Dice.createArray();
			int[] stats = Dice.analyseArray(Dice.createArray());
			int total = 0;

			BufferedWriter writer = new BufferedWriter(new FileWriter("summary.txt"));

			for (int i = 1; i <= 6; i++) {
				System.out.println("Number of " + i + ": " + stats[i - 1]);
				writer.write("Number of " + i + ": " + stats[i - 1] + "\n");
				total += stats[i - 1];
			}
			
			System.out.println("Total: " + total);
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}