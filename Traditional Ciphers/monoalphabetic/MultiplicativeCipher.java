public class MultiplicativeCipher {

    // Encryption function
    public static String encrypt(String plaintext, int key) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            if (Character.isUpperCase(ch)) {
                // Encrypt only uppercase letters
                int encryptedChar = (ch - 'A') * key % 26;
                ciphertext.append((char) (encryptedChar + 'A'));
            } else {
                // Keep non-alphabetic characters unchanged
                ciphertext.append(ch);
            }
        }

        return ciphertext.toString();
    }

    // Decryption function
    public static String decrypt(String ciphertext, int key) {
        // Find the modular multiplicative inverse of the key
        int inverseKey = findMultiplicativeInverse(key, 26);

        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            char ch = ciphertext.charAt(i);
            if (Character.isUpperCase(ch)) {
                // Decrypt only uppercase letters
                int decryptedChar = (ch - 'A') * inverseKey % 26;
                // Ensure the result is positive
                decryptedChar = (decryptedChar + 26) % 26;
                plaintext.append((char) (decryptedChar + 'A'));
            } else {
                // Keep non-alphabetic characters unchanged
                plaintext.append(ch);
            }
        }

        return plaintext.toString();
    }

    // Helper function to find the modular multiplicative inverse
    private static int findMultiplicativeInverse(int a, int m) {
        for (int i = 1; i < m; i++) {
            if ((a * i) % m == 1) {
                return i;
            }
        }
        return -1; // Inverse does not exist
    }

    public static void main(String[] args) {
        // Example usage
        String plaintext = "HELLO";
        int key = 7;

        // Encryption
        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted: " + encryptedText);

        // Decryption
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted: " + decryptedText);
    }
}
