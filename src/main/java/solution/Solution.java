package main.java.solution;

import java.util.Random;

import org.apache.commons.lang.ArrayUtils; 	

public class Solution {

	final static String[] murder = {"Donald","Tokio","Trezoitão"};

	public static void main(String[] args) {
		
		
		String[] suspects = {"Charles", "Donald", "Ada", "Alan", "Ivar", "Ras"};
		String[] locations = {"Redmond", "Palo Alto", "San Francisco", "Tokio", "Restaurante", "São Paulo", "Cupertino", "Helsinki", "Maida Vale", "Toronto"};
		String[] weapons = {"Peixeira", "DynaTAC 8000X", "Trezoitão", "Trebuchet", "Maça", "Gládio"};
		
		int answer = Integer.MAX_VALUE;
		String[] guess = null;
		
		while(answer != 0) {
			guess = nextGuess(guess, answer, suspects, locations, weapons);
			answer = question(guess);
			printAnswer(answer);
		}
		
		printSolution(guess);
	}

	private static String[] nextGuess(String[] guess, int answer, String[] suspects, String[] locations, String[] weapons) {
		Random r = new Random();
		
		if(guess == null) {
			guess = getFirstGuess(r, suspects, locations, weapons);
		}
		
		if(answer == 1) {
			suspects = (String[]) ArrayUtils.removeElement(suspects, guess[0]);
			guess[0] = suspects[r.nextInt(suspects.length)];
		} else if(answer == 2) {
			locations = (String[]) ArrayUtils.removeElement(locations, guess[1]);
			guess[1] = locations[r.nextInt(locations.length)];
		} else if(answer == 3) {
			weapons = (String[]) ArrayUtils.removeElement(weapons, guess[2]);
			guess[2] = weapons[r.nextInt(weapons.length)];
		}
		
		printGuess(guess);
		
		return guess;
	}

	private static int question(String[] guess) {
		for (int i = 0; i < guess.length; i++) {
			if(!guess[i].equals(murder[i])) {
				return i+1;
			}
		}
		return 0;
	}

	private static String[] getFirstGuess(Random r, String[] suspects, String[] locations, String[] weapons) {
		String[] rv = {suspects[r.nextInt(suspects.length)], locations[r.nextInt(locations.length)], weapons[r.nextInt(weapons.length)]};
		return rv;
	}

	private static void printGuess(String[] guess) {
		System.out.println("Trying: ("+guess[0]+", "+guess[1]+", "+guess[2]+")");
	}
	
	private static void printAnswer(int answer) {
		System.out.println("Answer: "+ answer);
	}
	
	private static void printSolution(String[] solution) {
		System.out.println("Murderer: " + solution[0]);
		System.out.println("Location: " + solution[1]);
		System.out.println("Weapon: " + solution[2]);
	}
}
