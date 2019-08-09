package signature;

import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

final class KeyGenerator {

    private static final Logger logger = Logger.getLogger(KeyGenerator.class.getName());

    private static final String KEYSTORE = "keyGeneratorTest";
    private static final String STOREPASS = "Why?#yIsWhy!";
    private static final String KEYPASS = "Th@K#yIsAWhy!";

    private static KeyPair keyPair = null;

    static {
        initKeyPair();
    }

    private KeyGenerator() {
        // classe utilitaire
        // file keystore RSA 2048 with regroup public key and private key with no expire date
        // keytool -genkeypair -alias keyGeneratorTest -storepass Why?#yIsWhy! -keypass Th@K#yIsAWhy! -keyalg RSA -keystore keyGenerator.jks
    }

    private static void initKeyPair() {

        try (InputStream ins = KeyGenerator.class.getClassLoader().getResourceAsStream("keyGenerator.jks")) {

            final KeyStore keyStore = KeyStore.getInstance("JCEKS");
            keyStore.load(ins, STOREPASS.toCharArray());
            final KeyStore.PasswordProtection keyPassword = new KeyStore.PasswordProtection(KEYPASS.toCharArray());

            final KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(KEYSTORE, keyPassword);

            final java.security.cert.Certificate cert = keyStore.getCertificate(KEYSTORE);
            final PublicKey publicKey = cert.getPublicKey();
            final PrivateKey privateKey = privateKeyEntry.getPrivateKey();

            keyPair = new KeyPair(publicKey, privateKey);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    static KeyPair getKeyPair() {
        // only if null on first call
        if (keyPair == null)
            initKeyPair();
        return keyPair;
    }
}
