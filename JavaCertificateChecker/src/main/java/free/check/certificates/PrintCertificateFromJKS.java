package free.check.certificates;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;

public class PrintCertificateFromJKS {

    public static void main(String[] args) {
        //new PrintCertificateFromJKS().showCertificateFromJKSFile("my.keystore", "myalias");
        new PrintCertificateFromJKS().showCertificateFromJKSFile("C:\\Program Files\\Java\\jdk-11.0.3\\lib\\security\\cacerts", "myalias");
    }

    private void showCertificateFromJKSFile(String keystoreFilename, String alias) {

        try {
            FileInputStream fIn = new FileInputStream(keystoreFilename);
            KeyStore keystore = KeyStore.getInstance("JKS");
            keystore.load(fIn, "password".toCharArray());
            Certificate cert = keystore.getCertificate(alias);
            System.out.println(cert);
        } catch (Exception e) {
            System.out.println("Exception occurred while reading keystore");
            e.printStackTrace();
        }
    }

}
