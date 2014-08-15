package semester_iii.other;

public class Recursion {

    private static int count(int n, int count) {
        if (n >= 2) {
            count = count(n / 2, count);
        } else if (n % 2 == 1) {
            count++;
        }
        return count;
    }

    private static boolean prime(int p) {
        if (p % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= p; i += 2) {
            if (p % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int[] getKeys(int key) {
        for (int i = 0; i < 999; i++) {
            if (!prime(i)) {
                continue;
            }
            for (int j = 0; j < 999; j++) {
                if (prime(j) && i * j == key) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

	/*
     *  Four problems to solve for RSA (Rivest, Shamir, Adelman)
	 *  - Modular exponentiation: compute x^n (mod p)
	 *  - GCD
	 *  - Multiplicative inverse: solve AX = 1 (mod p) for X
	 *  - Primality test
	 *
	 *  Modular exponentiation: raise x to the n power, then mod by p
	 *  GCD: greatest common divisor; largest integer that divides both a and b evenly
	 *  Multiplicative inverse: basically ax mod p and l mod p are equal
	 *  Primality testing: test if an integer is prime
	 *
	 *  Modular exponentiation:
	 *      Solution I: do the calculation straight up. Problem: both x and n are 100 digit numbers
	 *              II: start with a result, multiply by x, then mod by p - repeat n times. Problem: x that's 100 digits and n that's 1,000,000
	 *             III: observe is n is even: x^n = (x*x)^floor(n/2). if n is odd: x^n = x*(x*x)^floor(n/2)
	 *             apply a mod after every exponentiation and we're good - notice, this division makes recursion an ideal option
	 *
	 *             public static long modpower(long x, long n, long p) {
	 *                  if (n == 0) return 1;
	 *                  long tmp = power ((x*x) % p, n/2, p);
	 *                  if (n % 2 != 0) tmp = (temp * x) % p;
	 *                  return tmp;
	 *             }
	 *
	 *             GCD:
	  *
	  *                 I: Euclid's algorithm: efficient, recursive, 2300 years old. Subtract b from a continuously until a becomes less than by, then switch
	  *                 Continue until b becomes 0. a is the gcd
	  *
	  *                 II: Leverage modulus
	  *
	  *                 public static long gcd(long a, long b) {
	  *                     if (b == 0) return 0;
	  *                     return gcd(b, a%b);
	  *                 }
	  *
	  *             Multiplicative inverse: AX = 1 (mod n), given A and N, solve for X
	  *             Alternatively, AX % n == 1 % n
	  *
	  *             3i = 7(mod 13): if I know the multiplicative
	  *             inverse of 3 mod 13 (9), I can solve for i by removing 3 from the left side and multiplying the right by the multiplicative inverse
	  *
	  *             i = 63 (mod 13)
	  *             means one possible solution for i is 11
	  *
	  *             private long x;
	  *             private long y;
	  *
	  *             public void fullGcd(long a, long b) {
	  *                 long x1, y1;
	  *                 if (b == 0) {
	  *                     x = 1;
	  *                     y = 0;
	  *                 } else {
	  *                     fullGcd(b, a % b);
	  *                     x1 = x;
	  *                     y1 = y;
	  *                     x = y1;
	  *                     y = x1 - (a / b) * y1;
	  *                 }
	  *             }
	  *
	  *             public long inverse(long a, long n) {
	  *                 fullGcd(a, n);
	  *                 return x > 0 ? x : x + n;
	  *             }
	  *
	  *             Primality testing: very difficult
	  *             How it is traditionally solved is through a randomized algorithm
	  *
	  *             Putting it all together:
	  *                 1: receiver chooses two large primes: p and q
	  *                     a: p = 127, q = 211
	  *                 2: compute n = pq and n' = (p - 1)(q - 1)
	  *                     example: n = 26797, n' = 26460
	  *                 3: choose some e > 1 so that gcd(e, n') = 1 (e is relatively prime to n')
	  *                     example: e = 13379
	  *                 4: compute d, the multiplicative inverse of e, mod n'
	  *                     example: d = 11099
	  *                 5: receiver then destroys p, q, and n'. transmit the values of e and n, keep d a secret
	  *                 6: encrypting: sender computes m^e(mod n) and sends the result
	  *                     example: m = 10237, value sent is 8422
	  *                 7: decrypting: receiver takes message, computes r^d (mod n)
	  *
	  *                 p = 137, q = 263, n = 36031, n' = 35632, e = 3, d = 1/3
						p dstry, q dstry, n = 36031, n' dstry, e = 3, d = 1/3
						transmits n = 36031, e = 3, hides d = 1/3
						m = 5, sends 125, computes 125^(1/3) = 5 mod 36031 = 5 = message
	  *
	  *
	 */

    public static void main(String[] args) {
        int[] keys = getKeys(94021);
        System.out.println(keys[0] + ", " + keys[1]);
    }
}