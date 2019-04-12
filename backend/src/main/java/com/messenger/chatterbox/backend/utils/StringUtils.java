package com.messenger.chatterbox.backend.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import com.opencsv.CSVReader;

public class StringUtils {

	public Set<String> palindromes(final String input) {
		final Set<String> result = new HashSet<String>();
		for (int i = 0; i < input.length(); i++) {
			// expanding even length palindromes:
			expandPalindromes(result, input, i, i + 1);
			// expanding odd length palindromes:
			expandPalindromes(result, input, i, i);
		}
		return result;
	}

	public void expandPalindromes(final Set<String> result, final String s, int i, int j) {
		while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
			result.add(s.substring(i, j + 1));
			i--;
			j++;
		}
	}


    public Set<String> isPalindromeUsingCenter(String input) {
        final Set<String> palindromes = new HashSet<>();
        if (input == null || input.isEmpty()) {
            return palindromes;
        }
        if (input.length() == 1) {
            palindromes.add(input);
            return palindromes;
        }
        for (int i = 0; i < input.length(); i++) {
            findPalindromes(palindromes, input, i, i + 1);
            findPalindromes(palindromes, input, i, i);
        }
        return palindromes;
    }

    private void findPalindromes(final Set<String> result, final String input, int low, int high) {
        while (low >= 0 && high < input.length() && input.charAt(low) == input.charAt(high)) {
            result.add(input.substring(low, high + 1));
            low--;
            high++;
        }
    }

    public Set<String> isPalindromeUsingSubstring(String input) {
        Set<String> palindromes = new HashSet<>();
        if (input == null || input.isEmpty()) {
            return palindromes;
        }
        if (input.length() == 1) {
            palindromes.add(input);
            return palindromes;
        }
        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j <= input.length(); j++)
                if (isPalindrome(input.substring(i, j))) {
                    palindromes.add(input.substring(i, j));
                }
        }
        return palindromes;
    }

    private boolean isPalindrome(String input) {
        StringBuilder plain = new StringBuilder(input);
        StringBuilder reverse = plain.reverse();
        return (reverse.toString()).equals(input);
    }

    public Set<String> isPalindromeUsingManachersAlgo(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }


    public static final String COMMA_DELIMITER = ",";
    public static final String CSV_FILE = "src/test/resources/book.csv";
    public static final List<List<String>> EXPECTED_ARRAY = Collections.unmodifiableList(new ArrayList<List<String>>() {
        {
            add(new ArrayList<String>() {
                {
                    add("Mary Kom");
                    add("Unbreakable");
                }
            });
            add(new ArrayList<String>() {
                {
                    add("Kapil Isapuari");
                    add("Farishta");
                }
            });
        }
    });

    public void givenCSVFile_whenBufferedReader_thenContentsAsExpected() throws IOException {
        List<List<String>> records = new ArrayList<List<String>>();
        //try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
          //  String line = "";
            //while ((line = br.readLine()) != null) {
              //  String[] values = line.split(COMMA_DELIMITER);
                //records.add(Arrays.asList(values));
            //}
        //} catch (Exception e) {
           // e.printStackTrace();
        //}
       // for (int i = 0; i < EXPECTED_ARRAY.size(); i++) {
           /* Assert.assertArrayEquals(EXPECTED_ARRAY.get(i)
                .toArray(),
                records.get(i)
                    .toArray());*/
        //}
    }

    public void givenCSVFile_whenScanner_thenContentsAsExpected() throws IOException {
       /* List<List<String>> records = new ArrayList<List<String>>();
        try (Scanner scanner = new Scanner(new File(CSV_FILE));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < EXPECTED_ARRAY.size(); i++) {
            /*Assert.assertArrayEquals(EXPECTED_ARRAY.get(i)
                .toArray(),
                records.get(i)
                    .toArray());
        }*/
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        /*try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }*/
        return values;
    }

    public void givenCSVFile_whenOpencsv_thenContentsAsExpected() throws IOException {
        List<List<String>> records = new ArrayList<List<String>>();
       /* try (CSVReader csvReader = new CSVReader(new FileReader(CSV_FILE));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        for (int i = 0; i < EXPECTED_ARRAY.size(); i++) {
           /* Assert.assertArrayEquals(EXPECTED_ARRAY.get(i)
                .toArray(),
                records.get(i)
                    .toArray());*/
        }
    }

    public Set<String> isPalindromeUsingManachersAlgo1(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    
    public Set<String> isPalindromeUsingManachersAlgo2(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    
    public Set<String> isPalindromeUsingManachersAlgo3(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    
    public Set<String> isPalindromeUsingManachersAlgo4(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    
    public Set<String> isPalindromeUsingManachersAlgo5(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    
    public Set<String> isPalindromeUsingManachersAlgo6(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    
    public Set<String> isPalindromeUsingManachersAlgo7(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    
    public Set<String> isPalindromeUsingManachersAlgo8(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo9(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo10(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo11(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo12(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo13(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo14(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo15(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo16(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo17(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo18(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo19(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo20(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo21(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlg022(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo23(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo24(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo25(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo26(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo27(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo28(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo29(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo30(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo31(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo32(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo33(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo34(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
    public Set<String> isPalindromeUsingManachersAlgo35(String input) {
        Set<String> palindromes = new HashSet<>();
        String formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int rp;
        int radius[][] = new int[2][input.length() + 1];
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = rp = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - rp - 1] == inputCharArr[i + j + rp])
                    rp++;
                radius[j][i] = rp;
                int k = 1;
                while ((radius[j][i - k] != rp - k) && (k < rp)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], rp - k);
                    k++;
                }
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (rp = radius[j][i]; rp > 0; rp--) {
                    palindromes.add(input.substring(i - rp - 1, (2 * rp + j) + (i - rp - 1)));
                }
            }
        }
        return palindromes;
    }
   }
