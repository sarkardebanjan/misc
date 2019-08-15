package free.check.certificates;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.security.KeyStore;

public class ImportJavaCertTest {

    /*
    static {
        try {
            setTrustStore("sample.jks", "passphrase");
        } catch (Exception e) {
            System.out.println("Exception occurred while adding certificate.");
            e.printStackTrace();
        }
    }
    */

    public static void main(String[] args) {
        new ImportJavaCertTest().doWork();
    }

    private void doWork() {
        try {
            setTrustStore("sample.jks", "passphrase");
        } catch (Exception e) {
            System.out.println("Exception occurred while adding certificate.");
            e.printStackTrace();
        }

    }

    private static void setTrustStore(String trustStore, String password) throws Exception {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream keystoreStream = ImportJavaCertTest.class.getResourceAsStream(trustStore);
        keystore.load(keystoreStream, password.toCharArray());
        trustManagerFactory.init(keystore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustManagers, null);
        SSLContext.setDefault(sc);
    }

}
