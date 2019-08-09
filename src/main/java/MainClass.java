import signature.ErrorSignature;
import signature.Signature;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClass {

    private static final Logger logger = Logger.getLogger(MainClass.class.getName());

    private MainClass() {
    }

    public static void main(String[] args) {

        final String word = "theEndOfTheWorld :) ?";
        try {

            final String signature = Signature.make(word);
            final boolean isOk = Signature.verify(word, signature);
            final boolean isKo = !Signature.verify("NoNonAndNo !", signature);
            logger.info("isOk = " + isOk + " and isKo = " + isKo);

        } catch (ErrorSignature ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
