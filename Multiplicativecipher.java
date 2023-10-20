import java.util.Scanner;

public class MultiplicativeCipher {

    // Function to encrypt the message
    public static String encrypt(String plainText, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char ch = plainText.charAt(i);
            if (Character.isAlphabetic(ch)) {
                if (Character.isUpperCase(ch)) {
                    ch = (char) ((ch - 'A' + key) % 26 + 'A');
                } else {
                    ch = (char) ((ch - 'a' + key) % 26 + 'a');
                }
            }
            encryptedText.append(ch);
        }
        return encryptedText.toString();
    }

    // Function to decrypt the message
    public static String decrypt(String encryptedText, int key) {
        StringBuilder decryptedText = new StringBuilder();
        int modInverse = 0;
        for (int i = 0; i < 26; i++) {
            if ((key * i) % 26 == 1) {
                modInverse = i;
                break;
            }
        }
        for (int i = 0; i < encryptedText.length(); i++) {
            char ch = encryptedText.charAt(i);
            if (Character.isAlphabetic(ch)) {
                if (Character.isUpperCase(ch)) {
                    ch = (char) ((ch - 'A' - key + 26) % 26 + 'A');
                } else {
                    ch = (char) ((ch - 'a' - key + 26) % 26 + 'a');
                }
            }
            decryptedText.append(ch);
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plainText = scanner.nextLine();

        System.out.print("Enter the key (an integer coprime to 26): ");
        int key = scanner.nextInt();

        String encryptedText = encrypt(plainText, key);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}
