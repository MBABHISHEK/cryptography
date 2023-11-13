public class AffineCipher {
    // Function to find the modular multiplicative inverse of a with respect to m
    private static int modInverse(int a, int m) {
        for (int i = 0; i < m; i++) {
            if ((a * i) % m == 1) {
                return i;
            }
        }
        return -1; // If the inverse doesn't exist
    }

    // Function to check if two numbers are coprime
    private static boolean areCoprime(int a, int b) {
        return gcd(a, b) == 1;
    }

    // Function to find the greatest common divisor of two numbers
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    // Function to encrypt a message using the Affine Cipher
    public static String encrypt(String message, int a, int b) {
        StringBuilder encryptedText = new StringBuilder();

        for (char character : message.toCharArray()) {
            if (Character.isLetter(character)) {
                int x = character - 'a';
                int encryptedChar = (a * x + b) % 26;
                encryptedText.append((char) (encryptedChar + 'a'));
            } else {
                encryptedText.append(character);
            }
        }

        return encryptedText.toString();
    }

    // Function to decrypt a message using the Affine Cipher
    public static String decrypt(String encryptedMessage, int a, int b) {
        int aInverse = modInverse(a, 26);

        StringBuilder decryptedText = new StringBuilder();

        for (char character : encryptedMessage.toCharArray()) {
            if (Character.isLetter(character)) {
                int y = character - 'a';
                int decryptedChar = (aInverse * (y - b + 26)) % 26;
                decryptedText.append((char) (decryptedChar + 'a'));
            } else {
                decryptedText.append(character);
            }
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        int a = 3;
        int b = 7;

        // Check if 'a' is coprime with 26
        if (!areCoprime(a, 26)) {
            System.out.println("'a' must be coprime with 26 for the Affine Cipher.");
            return;
        }

        String message = "hello";
        System.out.println("Original Message: " + message);

        String encryptedMessage = encrypt(message, a, b);
        System.out.println("Encrypted Message: " + encryptedMessage);

        String decryptedMessage = decrypt(encryptedMessage, a, b);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
