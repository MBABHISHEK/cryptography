import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RSAAlgorithm {

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int findCoprime(int phi) {
        int e = 2;
        while (e < phi) {
            if (gcd(e, phi) == 1) {
                return e;
            }
            e++;
        }
        return -1; // No coprime found
    }

    public static int findPrivateKey(int e, int phi) {
        int d;
        int k = 1;
        while (true) {
            d = (1 + k * phi) / e;
            if (d * e == 1 + k * phi) {
                return d;
            }
            k++;
        }
    }

    public static int modularExp(int b, int e, int m) {
        int res = 1;
        for (int i = 0; i < e; i++) {
            res = (res * b) % m;
        }
        return res;
    }

    public static List<Integer> rsaEncrypt(String p, int e, int n) {
        List<Integer> ciphertext = new ArrayList<>();
        for (char c : p.toCharArray()) {
            ciphertext.add(modularExp((int) c, e, n));
        }
        return ciphertext;
    }

    public static String rsaDecrypt(List<Integer> c, int d, int n) {
        StringBuilder plaintext = new StringBuilder();
        for (int i : c) {
            plaintext.append((char) modularExp(i, d, n));
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int p = 17;
        int q = 23;

        int n = p * q;
        int phi = (p - 1) * (q - 1);

        int e = findCoprime(phi);

        int d = findPrivateKey(e, phi);

        System.out.print("Enter plaintext: ");
        String plainText = sc.nextLine();

        List<Integer> ct = rsaEncrypt(plainText, e, n);
        System.out.println("Ciphertext: " + ct);

        String pt = rsaDecrypt(ct, d, n);
        System.out.println("Plaintext: " + pt);
    }
}
