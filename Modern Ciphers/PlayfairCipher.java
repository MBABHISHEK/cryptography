import java.util.Scanner;

public class PlayfairCipher{

    private static final int SIZE = 5;
    private static char[][] matrix;

    private static String prepareInputText(String input) {
        input = input.toUpperCase().replaceAll("[^A-Z]", "");
        input = input.replace("J", "I");
        StringBuilder preparedText = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            preparedText.append(input.charAt(i));
            if (i < input.length() - 1 && input.charAt(i) == input.charAt(i + 1)) {
                preparedText.append('X');
            }
        }
        if (preparedText.length() % 2 != 0) {
            preparedText.append('X');
        }
        return preparedText.toString();
    }

    private static void generateMatrix(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        matrix = new char[SIZE][SIZE];
        boolean[] used = new boolean[26];
        int row = 0, col = 0;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!used[ch - 'A']) {
                matrix[row][col] = ch;
                used[ch - 'A'] = true;
                col++;
                if (col == SIZE) {
                    col = 0;
                    row++;
                }
            }
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J' && !used[c - 'A']) {
                matrix[row][col] = c;
                col++;
                if (col == SIZE) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    private static String encrypt(String preparedText) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < preparedText.length(); i += 2) {
            char a = preparedText.charAt(i);
            char b = preparedText.charAt(i + 1);
            int row1 = 0, col1 = 0, row2 = 0, col2 = 0;
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    if (matrix[j][k] == a) {
                        row1 = j;
                        col1 = k;
                    }
                    if (matrix[j][k] == b) {
                        row2 = j;
                        col2 = k;
                    }
                }
            }
            if (row1 == row2) {
                encryptedText.append(matrix[row1][(col1 + 1) % SIZE]);
                encryptedText.append(matrix[row2][(col2 + 1) % SIZE]);
            } else if (col1 == col2) {
                encryptedText.append(matrix[(row1 + 1) % SIZE][col1]);
                encryptedText.append(matrix[(row2 + 1) % SIZE][col2]);
            } else {
                encryptedText.append(matrix[row1][col2]);
                encryptedText.append(matrix[row2][col1]);
            }
        }
        return encryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the plaintext: ");
        String plaintext = scanner.nextLine();
        System.out.println("Enter the key: ");
        String key = scanner.nextLine();

        String preparedText = prepareInputText(plaintext);
        generateMatrix(key);
        String encryptedText = encrypt(preparedText);
        System.out.println("Encrypted Text: " + encryptedText);
    }
}
