import java.util.Scanner;

public class autokey{

    private static String encrypt(String plaintext, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char p = plaintext.charAt(i);
            char encryptedChar = (char) ((p - 'A' + key) % 26 + 'A');
            encryptedText.append(encryptedChar);
            key = p - 'A';
        }
        return encryptedText.toString();
    }

    private static String decrypt(String ciphertext, int key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            char decryptedChar = (char) ((c - 'A' - key + 26) % 26 + 'A');
            decryptedText.append(decryptedChar);
            key = decryptedChar - 'A';
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the plaintext: ");
        String plaintext = scanner.nextLine().toUpperCase();
        System.out.println("Enter the key (as an integer): ");
        int key = scanner.nextInt();

        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
