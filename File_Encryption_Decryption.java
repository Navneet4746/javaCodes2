import java.io.*;
import java.util.Scanner;

public class File_Encryption_Decryption {

    private static final int SHIFT = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to (E)ncrypt or (D)ecrypt a file?");
        String choice = scanner.nextLine().trim().toUpperCase();

        System.out.println("Enter the file path:");
        String filePath = scanner.nextLine().trim();

        System.out.println("Enter the output file path:");
        String outputFilePath = scanner.nextLine().trim();

        if (choice.equals("E")) {
            processFile(filePath, outputFilePath, true);
            System.out.println("File encrypted successfully.");
        } else if (choice.equals("D")) {
            processFile(filePath, outputFilePath, false);
            System.out.println("File decrypted successfully.");
        } else {
            System.out.println("Invalid choice. Please select E for encryption or D for decryption.");
        }

        scanner.close();
    }

    private static void processFile(String inputFilePath, String outputFilePath, boolean isEncrypt) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String processedLine = isEncrypt ? encrypt(line) : decrypt(line);
                writer.write(processedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
        }
    }

    private static String encrypt(String text) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char shifted = (char) (c + SHIFT);
                if (Character.isLowerCase(c) && shifted > 'z' || Character.isUpperCase(c) && shifted > 'Z') {
                    shifted -= 26;
                }
                encrypted.append(shifted);
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    private static String decrypt(String text) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char shifted = (char) (c - SHIFT);
                if (Character.isLowerCase(c) && shifted < 'a' || Character.isUpperCase(c) && shifted < 'A') {
                    shifted += 26;
                }
                decrypted.append(shifted);
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
   }
}

