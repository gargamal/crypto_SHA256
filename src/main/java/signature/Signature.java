package signature;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.Base64;
import java.util.logging.Logger;

public final class Signature {

    static final Logger logger = Logger.getLogger(Signature.class.getName());

    /**
     * Encript in base 64
     *
     * @param word text to ecript
     * @return La chaine signé en Base 64
     */
    public static String make(String word) {

        final PrivateKey privateKey = KeyGenerator.getKeyPair().getPrivate();

        try {
            final java.security.Signature privateSignature = java.security.Signature.getInstance("SHA256withRSA");
            privateSignature.initSign(privateKey);
            privateSignature.update(word.getBytes(StandardCharsets.UTF_8));

            byte[] signature = privateSignature.sign();

            return Base64.getEncoder().encodeToString(signature);

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex) {
            throw new ErrorSignature(ex);
        }
    }

    /**
     * Permet de vérifier que la signature et chaine sont cohérent
     *
     * @param word      who is encript
     * @param signature signature maked
     * @return true is returned if signature has the same base that word
     */
    public static boolean verify(String word, String signature) {

        final PublicKey publicKey = KeyGenerator.getKeyPair().getPublic();
        logger.info("b64PublicKey=" + Base64.getEncoder().encodeToString(publicKey.getEncoded()));

        try {
            final java.security.Signature publicSignature = java.security.Signature.getInstance("SHA256withRSA");
            publicSignature.initVerify(publicKey);
            publicSignature.update(word.getBytes(StandardCharsets.UTF_8));

            byte[] signatureBytes = Base64.getDecoder().decode(signature);

            return publicSignature.verify(signatureBytes);

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex) {
            throw new ErrorSignature(ex);
        }
    }
}
