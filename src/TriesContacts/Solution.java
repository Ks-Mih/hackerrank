package TriesContacts;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Solution {
    private static final String ADD_OPERATION = "add";
    private static final String FIND_OPERATION = "find";
    private static final String ERROR_ANOTHER_OPERATION = "I don't know this operation. Please, use 'add' or 'find'\n" +
            "For example:\n" +
            "add Helena\n" +
            "find Kin";
    private static final String SYMBOL_AFTER_LUST_LETTER = "{";

    private static TreeSet contacts = new TreeSet();
    private static String output = "";

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int operationsCount = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, operationsCount).forEach(nItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                String operation = firstMultipleInput[0];
                String contact = firstMultipleInput[1];

                switch (operation){
                    case ADD_OPERATION:
                        addContact(contact);
                        break;
                    case FIND_OPERATION:
                        findContact(contact);
                        break;
                    default:
                        System.out.println(ERROR_ANOTHER_OPERATION);
                        break;
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        System.out.println(output);
        bufferedReader.close();
    }

    private static void addContact(String contact){
        contacts.add(contact);
    }

    private static void findContact(String word){
        output = output + contacts.subSet(word, word + SYMBOL_AFTER_LUST_LETTER).size() + "\n";
    }
}
