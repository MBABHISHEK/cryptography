import java.util.Scanner;

public class autokey{

    private static String encrypt(String plaintext, String key) {
        plaintext = plaintext.toUpperCase();
        key = key.toUpperCase();
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char p = plaintext.charAt(i);
            char k = key.charAt(i % key.length());
            int shift = (k - 'A');
            char encryptedChar = (char) ((p - 'A' + shift) % 26 + 'A');
            encryptedText.append(encryptedChar);
            key += encryptedChar;
        }
        return encryptedText.toString();
    }

    private static String decrypt(String ciphertext, String key) {
        ciphertext = ciphertext.toUpperCase();
        key = key.toUpperCase();
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            char k = key.charAt(i % key.length());
            int shift = (k - 'A');
            char decryptedChar = (char) ((c - 'A' - shift + 26) % 26 + 'A');
            decryptedText.append(decryptedChar);
            key += decryptedChar;
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the plaintext: ");
        String plaintext = scanner.nextLine();
        System.out.println("Enter the key: ");
        String key = scanner.nextLine();

        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
