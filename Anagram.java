/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		
		boolean same = false;
		str1 = preProcess(str1);
		str2 = preProcess(str2);
		String charCheck = "";

		// Iterates through letters of the first word and compares to letters of the second word
		// If they are the same, the letter is taken out to make sure it is 1:1 match
		for (int i = 0; i < str1.length(); i++) {

			char letterStr1 = str1.charAt(i);
				
				for (int j = 0; j < str2.length(); j++) {
					
					char letterStr2 = str2.charAt(j);

					if (letterStr1 == letterStr2) {

						same = true;

						// Takes out the letter we checked
						str2 = str2.substring(0, j) + str2.substring(j + 1);

						// Adds that letter to a new string
						charCheck += letterStr1;
						break;
					
					// If the last letter of the second word has been reached, and the letters aren't equal, they aren't anagrams
					} else if (j == (str2.length() - 1)) {
						return false;
					} else {
						same = false;
					}
			}
		}

		// Checks that they are the same length and not just same letters
		if (charCheck.length() == str1.length() && str2.length() == 0){
			return same;
		} else {
			return false;
		}
	}

	
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		
		String lowerStr = "";

		for (int i = 0; i < str.length(); i++) {

			char letterStr = str.charAt(i);

			if (letterStr == ' ') {
				// nothing, iterates to next letter
			} else if ((letterStr >= 'A' && letterStr <= 'Z') || 
						letterStr >= 'a' && letterStr <= 'z' ||
						(letterStr >= '0' && letterStr <= '9')) {
				letterStr = Character.toLowerCase(str.charAt(i));
				lowerStr += letterStr;
			} 
		}

		return lowerStr;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		
		String newWord = "";

		// Generates random number in a given range,
		while (str.length() > 0) {
			
			// Generates location of the random letter
			int randomPlace = (int)(Math.random() * str.length());

			// Adds the randomly chosen letter to the new word
			newWord += str.charAt(randomPlace);

			// Removes it as an option for future iterations
			str = str.substring(0, randomPlace) + str.substring(randomPlace + 1);

		}
		return newWord;
	}
}
