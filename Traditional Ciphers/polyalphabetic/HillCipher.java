import java.util.Scanner;

public class HillCipher {

    private static final int MATRIX_SIZE = 2; // Change this to the desired matrix size (e.g., 2 for a 2x2 matrix)

    // Function to calculate the matrix determinant (used to check if the matrix is invertible)
    private static int determinant(int[][] matrix) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    // Function to calculate the modular inverse of a number
    private static int modInverse(int a, int m) {
        for (int i = 0; i < m; i++) {
            if ((a * i) % m == 1) {
                return i;
            }
        }
        return -1; // If the inverse doesn't exist
    }

    // Function to calculate the matrix inverse (if it exists)
    private static int[][] matrixInverse(int[][] matrix, int mod) {
        int det = determinant(matrix);

        // Check if the matrix is invertible
        if (det == 0 || modInverse(det, mod) == -1) {
            System.out.println("Matrix is not invertible. Choose a different key.");
            System.exit(1);
        }

        int[][] inverse = new int[MATRIX_SIZE][MATRIX_SIZE];

        // Calculate the matrix of minors and cofactors
        inverse[0][0] = matrix[1][1];
        inverse[0][1] = -matrix[0][1];
        inverse[1][0] = -matrix[1][0];
        inverse[1][1] = matrix[0][0];

        // Calculate the adjugate (transpose of the cofactor matrix)
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                inverse[i][j] = (inverse[i][j] + mod) % mod;
            }
        }

        return inverse;
    }

    // Function to encrypt a block of plaintext using the Hill Cipher
    private static String encryptBlock(String block, int[][] keyMatrix, int mod) {
        int[] blockVector = new int[MATRIX_SIZE];
        int[] encryptedVector = new int[MATRIX_SIZE];

        // Convert characters to numerical values
        for (int i = 0; i < MATRIX_SIZE; i++) {
            blockVector[i] = block.charAt(i) - 'A';
        }

        // Matrix multiplication
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                encryptedVector[i] += keyMatrix[i][j] * blockVector[j];
                encryptedVector[i] %= mod;
            }
        }

        // Convert numerical values back to characters
        StringBuilder encryptedText = new StringBuilder();
        for (int value : encryptedVector) {
            encryptedText.append((char) (value + 'A'));
        }

        return encryptedText.toString();
    }

    // Function to decrypt a block of ciphertext using the Hill Cipher
    private static String decryptBlock(String block, int[][] keyMatrix, int mod) {
        int[] blockVector = new int[MATRIX_SIZE];
        int[] decryptedVector = new int[MATRIX_SIZE];

        // Convert characters to numerical values
        for (int i = 0; i < MATRIX_SIZE; i++) {
            blockVector[i] = block.charAt(i) - 'A';
        }

        // Matrix multiplication with the inverse of the key matrix
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                decryptedVector[i] += keyMatrix[i][j] * blockVector[j];
                decryptedVector[i] %= mod;
            }
        }

        // Convert numerical values back to characters
        StringBuilder decryptedText = new StringBuilder();
        for (int value : decryptedVector) {
            decryptedText.append((char) (value + 'A'));
        }

        return decryptedText.toString();
    }

    // Function to pad the plaintext with 'X' if its length is not a multiple of the matrix size
    private static String padPlaintext(String plaintext) {
        int padding = MATRIX_SIZE - (plaintext.length() % MATRIX_SIZE);
        StringBuilder paddedText = new StringBuilder(plaintext);
        for (int i = 0; i < padding; i++) {
            paddedText.append('X');
        }
        return paddedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the key matrix
        int[][] keyMatrix = new int[MATRIX_SIZE][MATRIX_SIZE];
        System.out.println("Enter the key matrix:");
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                keyMatrix[i][j] = scanner.nextInt();
            }
        }

        // Check if the key matrix is invertible
        int mod = 26;
        if (determinant(keyMatrix) == 0 || modInverse(determinant(keyMatrix), mod) == -1) {
            System.out.println("Key matrix is not invertible. Choose a different key.");
            System.exit(1);
        }

        scanner.nextLine(); // Consume newline
        System.out.println("Enter the plaintext:");
        String plaintext = scanner.nextLine().toUpperCase();

        // Pad the plaintext if needed
        plaintext = padPlaintext(plaintext);

        StringBuilder ciphertext = new StringBuilder();

        // Encrypt the plaintext in blocks
        for (int i = 0; i < plaintext.length(); i += MATRIX_SIZE) {
            String block = plaintext.substring(i, i + MATRIX_SIZE);
            ciphertext.append(encryptBlock(block, keyMatrix, mod));
        }

        System.out.println("Encrypted Text: " + ciphertext.toString());

        // Decrypt the ciphertext in blocks
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += MATRIX_SIZE) {
            String block = ciphertext.substring(i, i + MATRIX_SIZE);
            decryptedText.append(decryptBlock(block, matrixInverse(keyMatrix, mod), mod));
        }

        System.out.println("Decrypted Text: " + decryptedText.toString());
    }
}
