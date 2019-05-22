package main.java.solution;

import java.util.HashSet;
import java.util.Random;
import java.util.Set; 	

public class Solution {

//	final static String[] murder = {"Donald","Tokio","Trezoitão"};
	final static int[] murder = {1,3,2};

	public static void main(String[] args) {
		
		
		String[] suspects = {"Charles", "Donald", "Ada", "Alan", "Ivar", "Ras"};
		String[] locations = {"Redmond", "Palo Alto", "San Francisco", "Tokio", "Restaurante", "São Paulo", "Cupertino", "Helsinki", "Maida Vale", "Toronto"};
		String[] weapons = {"Peixeira", "DynaTAC 8000X", "Trezoitão", "Trebuchet", "Maça", "Gládio"};
		
		Set<Integer> wrongSuspects = new HashSet<Integer>();
		Set<Integer> wrongLocations = new HashSet<Integer>();
		Set<Integer> wrongWeapons = new HashSet<Integer>();
		
		int answer = Integer.MAX_VALUE;
		int[] guess = null;
		
		while(answer != 0) {
			guess = nextGuess(guess, answer, suspects, locations, weapons, wrongSuspects, wrongLocations, wrongWeapons);
			answer = question(guess);
			printAnswer(answer);
		}
		
		printSolution(guess, suspects, locations, weapons);
	}

	private static int[] nextGuess(int[] guess, int answer, String[] suspects, String[] locations, String[] weapons, Set<Integer> wrongSuspects, Set<Integer> wrongLocations, Set<Integer> wrongWeapons) {
		Random r = new Random();
		
		if(guess == null) {
			guess = getFirstGuess(r, suspects, locations, weapons);
		}
		
		if(answer == 1) {
			wrongSuspects.add(guess[0]);
			int nextSuspect = r.nextInt(suspects.length);
			while(wrongSuspects.contains(new Integer(nextSuspect))){
				nextSuspect = r.nextInt(suspects.length);
			}
			guess[0] = nextSuspect;
		} else if(answer == 2) {
			wrongLocations.add(guess[1]);
			int nextLocation = r.nextInt(locations.length);
			while(wrongLocations.contains(new Integer(nextLocation))){
				nextLocation = r.nextInt(locations.length);
			}
			guess[1] = nextLocation;
		} else if(answer == 3) {
			wrongWeapons.add(guess[2]);
			int nextWeapon = r.nextInt(weapons.length);
			while(wrongWeapons.contains(new Integer(nextWeapon))){
				nextWeapon = r.nextInt(weapons.length);
			}
			guess[2] = nextWeapon;
		}
		
		printGuess(guess, suspects, locations, weapons);
		
		return guess;
	}

	private static int question(int[] guess) {
		for (int i = 0; i < guess.length; i++) {
			if(guess[i] != murder[i]) {
				return i+1;
			}
		}
		return 0;
	}

	private static int[] getFirstGuess(Random r, String[] suspects, String[] locations, String[] weapons) {
		return new int[] {r.nextInt(suspects.length), r.nextInt(locations.length), r.nextInt(weapons.length)};
	}

	private static void printGuess(int[] guess, String[] suspects, String[] locations, String[] weapons) {
		System.out.println("Trying: ("+suspects[guess[0]]+", "+locations[guess[1]]+", "+weapons[guess[2]]+")");
	}
	
	private static void printAnswer(int answer) {
		System.out.println("Answer: "+ answer);
	}
	
	private static void printSolution(int[] solution, String[] suspects, String[] locations, String[] weapons) {
		System.out.println("Murderer: " + suspects[solution[0]]);
		System.out.println("Location: " + locations[solution[1]]);
		System.out.println("Weapon: " + weapons[solution[2]]);
	}
}
