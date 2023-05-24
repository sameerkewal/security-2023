import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class get_signature {

    public static void main(final String[] args) throws Exception{
        String message = System.getProperty("mod");
        String privateKey = System.getProperty("key");


        final PrivateKey key = getPrivateKeyFromPEM(privateKey);
        final Signature instance = Signature.getInstance("SHA256withRSA");
        instance.initSign(key);
        instance.update(message.getBytes(StandardCharsets.UTF_8));


        final String signature = new String(Base64.getEncoder().encode(instance.sign()), Charset.forName("UTF-8"));

                String header= "";
                for(int i =0; i < 20; i++){
                    header = header + "-";
                }

        System.out.println();
        System.out.println(header + "New Signature" + header + "\n" + signature + "\n" + header + "End Signature" + header);

    }

    public static PrivateKey getPrivateKeyFromPEM(String privateKeyPem)throws Exception{

        privateKeyPem = privateKeyPem.replace("-----BEGIN PRIVATE KEY-----","");
        privateKeyPem = privateKeyPem.replace("-----END PRIVATE KEY-----", "");
        privateKeyPem = privateKeyPem.replace("\n", "").replace("\r", "");


        final byte [] decoded = Base64.getDecoder().decode(privateKeyPem);

        final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        final  KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);





    }

}