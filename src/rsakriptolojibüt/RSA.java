/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsakriptolojibüt;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Pohenix
 */
public class RSA {
    private final static BigInteger one=new BigInteger("1");
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger t;
    private BigInteger e;
    private BigInteger d;
    public final static int maxLength=1024;
    private Random R;

    public RSA() {
        
        R = new Random();
        p = BigInteger.probablePrime(maxLength, R);
        q = BigInteger.probablePrime(maxLength, R);
        e = BigInteger.probablePrime(maxLength / 2, R);
        n = p.multiply(q);//n=(p*q)
        t = p.subtract(one).multiply(q.subtract(one));// Totient fonksiyonu Φ(n) = (p-1)(q-1)
        
        while (t.gcd(e).compareTo(one) > 0 && e.compareTo(t) < 0) {//1 < e < Φ(n)---1<d< Φ(n)---d ≡ 1 mod ϕ(n)/e
            
            e.add(one);
        }
        d = e.modInverse(t);
        

    }

    public RSA(BigInteger e, BigInteger d, BigInteger n) {
        
        this.e = e;
        this.d = d;
        this.n = n;

    }
    
    //Public key genel anahtar [n,e]
    //Private key özel anahtar [n,d]
    
    public byte[] Encryption(byte[] message) {// C ≡ m ^ e (mod n) (1) 
        System.out.println("q:" + q + "\n" + "p:" + p + "\n" + "e:" + e + "\n" + "n:" + n + "\n" + "d:" + d + "\n"  );
        return (new BigInteger(message)).modPow(e, n).toByteArray();

    }

    // Decrypting the message
    public byte[] Decryption(byte[] message) {// M ≡ C ^ d (mod n ) (1)
        return (new BigInteger(message)).modPow(d, n).toByteArray();
    }

}
