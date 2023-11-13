public class VigenereCipher {

    // Function to encrypt a message using the Vigenère Cipher
    public static String encrypt(String message, String key) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0, j = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                int shift = key.charAt(j) - 'A';

                char encryptedChar = (char) ((currentChar + shift - base) % 26 + base);
                encryptedText.append(encryptedChar);

                j = (j + 1) % key.length();
            } else {
                encryptedText.append(currentChar);
            }
        }

        return encryptedText.toString();
    }

    // Function to decrypt a message using the Vigenère Cipher
    public static String decrypt(String encryptedMessage, String key) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0, j = 0; i < encryptedMessage.length(); i++) {
            char currentChar = encryptedMessage.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                int shift = key.charAt(j) - 'A';

                char decryptedChar = (char) ((currentChar - shift - base + 26) % 26 + base);
                decryptedText.append(decryptedChar);

                j = (j + 1) % key.length();
            } else {
                decryptedText.append(currentChar);
            }
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        String key = "KEY";
        String message = "HELLO";

        System.out.println("Original Message: " + message);

        String encryptedMessage = encrypt(message, key);
        System.out.println("Encrypted Message: " + encryptedMessage);

        String decryptedMessage = decrypt(encryptedMessage, key);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
