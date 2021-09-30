package javaNote.encrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

public class Encrypt {

    public static void main(String[] args){

        try{
            //编码算法
                //测试URL编码
                testUTF8();
                //测试base64
                //testBase64();
            //hash算法
                //测试hash (MD5)算法
                //testhash();
                //测试SHA-1
                //testsha1();
                //测试BouncyCastle
                //testBouncyCastle();
                //Hmac 算法
                //testHmac();
            //秘钥交换算法原理
            // secretExchange();
        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }
    }


    private static void testHmac() throws  Exception{
        //随机生成key
        KeyGenerator key = KeyGenerator.getInstance("HmacMD5");
        SecretKey secretKey = key.generateKey();//生成key(salt值)de73741ef91e48557b23b5a0de8283566d4baa9a464cec82de96d86b6f7360fd93a475b2caab14a38ed1f05f1593af6cbce5779072c2f7e07f0752e1c0f5f320
        byte[] encoded = secretKey.getEncoded();
        System.out.println("HmacKey"+new BigInteger(1,encoded).toString(16));
        Mac hmacMd5 = Mac.getInstance("HmacMd5");
        hmacMd5.init(secretKey);  //设置盐值
        hmacMd5.update("hash".getBytes());
        byte[] bytes = hmacMd5.doFinal(); //获取hash值
        System.out.println("Hmac Hash值"+new BigInteger(1,bytes).toString(16));//d194a40e497839a82481219811e8be8e
        //自定义key
//        byte[] hkey = new byte[] { 106, 70, -110, 125, 39, -20, 52, 56, 85, 9, -19, -72, 52, -53, 52, -45, -6, 119, -63,
//                30, 20, -83, -28, 77, 98, 109, -32, -76, 121, -106, 0, -74, -107, -114, -45, 104, -104, -8, 2, 121, 6,
//                97, -18, -13, -63, -30, -125, -103, -80, -46, 113, -14, 68, 32, -46, 101, -116, -104, -81, -108, 122,
//                89, -106, -109 };
//
//        SecretKey customkey = new SecretKeySpec(hkey, "HmacMD5");
//        Mac mac = Mac.getInstance("HmacMD5");
//        mac.init(customkey);
//        mac.update("HelloWorld".getBytes("UTF-8"));
//        byte[] result = mac.doFinal();
//        System.out.println(new BigInteger(1,customkey.getEncoded()).toString(16)+"--->"+new BigInteger(1,result).toString(16));

    }

    static{
        Security.addProvider(new BouncyCastleProvider());//注册BouncyCastle
    }
    private static void testBouncyCastle() throws Exception{
        //RipeMD160哈希算法
        MessageDigest md = MessageDigest.getInstance("RipeMD160");
        md.update("hash".getBytes());
        byte[] digest = md.digest();
        System.out.println("RipeMD160---->>"+new BigInteger(1,digest).toString(16));//73045e2e25b9531d5cb676cf73fff291d4a1ee6d
    }

    private static void testsha1() throws Exception{
        MessageDigest md5 = MessageDigest.getInstance("SHA-1");
        md5.update("hash".getBytes());//原数据
        byte[] digest = md5.digest();
        System.out.println("hash"+new BigInteger(1,digest).toString(16));//2346ad27d7568ba9896f1b7da6b5991251debdf2
    }

    private static void testhash() throws  Exception{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update("hash".getBytes());//原数据
        byte[] digest = md5.digest();
        System.out.println("hash"+new BigInteger(1,digest).toString(16));//800fc577294c34e0b28ad2839435945

    }

    private static void testBase64() {
        byte[] input = new byte[] { (byte) 0xe4, (byte) 0xb8, (byte) 0xad ,(byte) 0xad,(byte) 0xfd};
        String b64encoded = Base64.getEncoder().encodeToString(input); //字节编码成字符
        System.out.println("base64编码"+b64encoded);
        byte[] output = Base64.getDecoder().decode("5Lit");//字符解码成字节
        System.out.println("base64 解码"+Arrays.toString(output));
        //以上base64 编码后的字符串不适合放在url中，因为包含了+/= 。
        //针对URL的base64 编码，把+变成-，/变成_
        byte[] inputn = new byte[] { 0x01, 0x02, 0x7f, 0x00 };
        String b64encodedn = Base64.getUrlEncoder().encodeToString(inputn);
        System.out.println(b64encodedn);
        byte[] outputn = Base64.getUrlDecoder().decode(b64encodedn);
        System.out.println(Arrays.toString(outputn));
    }

    private static void testUTF8() throws  Exception{
        String encoded = URLEncoder.encode("中文", StandardCharsets.UTF_8.toString());
        System.out.println("URL编码"+encoded);
        String decode = URLDecoder.decode(encoded, StandardCharsets.UTF_8.toString());
        System.out.println("URL解码"+decode);
    }

    private static void secretExchange() {
        //甲
        int p = 166;
        int g = 5;
        int a = 2; //甲的私钥
        int A=g*a%p; //甲的公钥
        System.out.println(A);

        //乙
        int b = 3;   //乙的私钥
        int B = g*b%p;  //乙的公钥
        System.out.println(B);

        int Sb = A*b%p;   //g*a%p*b%p SS=Sb
        System.out.println(Sb);//秘钥
        //甲
        int SS = B*a%p;  //g*b%p*a%p
        System.out.println(SS);//秘钥
    }
}

/**
 * 对称加密
 * ECB :
 *     指定秘钥长度(16/32/...)，简单的通过秘钥进行加密解密
 * CBC :
 *     指定秘钥长度(16/32/...)，通过秘钥和系统随机生成的IV参数 进行加密解密
 */
class SymmetryEncrypt{
    public static void main(String[] args){
        try{
            //加密算法
            //测试对称加密算法AES算法ECB模式：ECB是最简单的模式
            testSymmetryEncryptByAESECB();
            //测试对称加密算法AES算法CBC模式:
            //testSymmetryEncryptByAESCBC();

        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }
    }
    private static void testSymmetryEncryptByAESCBC() throws  Exception{
        String message = "消息";
        //消息
        byte[] messageBytes = message.getBytes("UTF-8");
        //指定秘钥256位密钥 = 32 bytes Key:
        byte[] keyBytes = "1234567890abcdef1234567890abcdef".getBytes("UTF-8");
        //加密
        byte[] encryptResult = encryptByCBC(keyBytes, messageBytes);//传入明文得到密文
        System.out.println(Base64.getEncoder().encodeToString(encryptResult));//转base64 显示
        System.out.println(new BigInteger(1,encryptResult).toString(16));//转16进制显示密文
        //解密
        byte[] bytes1 = decryptByCBC(keyBytes, encryptResult);//传入密文得到明文
        System.out.println(new String(bytes1,StandardCharsets.UTF_8)); //输出原文
    }

    private static byte[] encryptByCBC(byte[] keyBytes, byte[] messageBytes)throws  Exception {
//        Objects.requireNonNull(messageBytes);
        //CBC加密
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(keyBytes,"AES");
        //生成IV随机数
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = secureRandom.generateSeed(16);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bytes);
        cipher.init(Cipher.ENCRYPT_MODE,key,ivParameterSpec);
        byte[] bytes1 = cipher.doFinal(messageBytes);
        return join(bytes,bytes1);
    }

    private static byte[] join(byte[] bs1, byte[] bs2) {
        byte[] r = new byte[bs1.length + bs2.length];
        System.arraycopy(bs1, 0, r, 0, bs1.length);
        System.arraycopy(bs2, 0, r, bs1.length, bs2.length);
        return r;
    }

    private static byte[] decryptByCBC(byte[] keyBytes, byte[] encryptResult) throws  Exception{
        //CBC解密
        // 把input分割成IV和密文:
        byte[] iv = new byte[16];
        byte[] data = new byte[encryptResult.length - 16];
        System.arraycopy(encryptResult, 0, iv, 0, 16);
        System.arraycopy(encryptResult, 16, data, 0, data.length);
        // 解密:
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivps = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivps);
        return cipher.doFinal(data);

    }

    /**
     * 对称加密必须指定秘钥长度，16/32/... 长度不符会抛出Exception in thread "main" java.security.InvalidKeyException: Invalid AES key length: 12 bytes
     * 不推荐使用
     * @throws Exception
     */
    private static void testSymmetryEncryptByAESECB() throws  Exception{
        String message = "消息";
        //消息
        byte[] messageBytes = message.getBytes("UTF-8");
        //指定秘钥128位密钥 = 16 bytes Key:
        byte[] keyBytes = "1234567890abcdef".getBytes("UTF-8");
        //加密
        byte[] encryptResult = encryptByECB(keyBytes, messageBytes);//传入明文得到密文
        System.out.println(Base64.getEncoder().encodeToString(encryptResult));//转base64 显示
        System.out.println(new BigInteger(1,encryptResult).toString(16));//转16进制显示密文
        //解密
        byte[] bytes1 = decryptByECB(keyBytes, encryptResult);//传入密文得到明文
        System.out.println(new String(bytes1,StandardCharsets.UTF_8)); //输出原文


    }

    private static byte[] encryptByECB(byte[] keyBytes, byte[] messageBytes) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//根据算法名称/工作模式/填充模式获取Cipher实例
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");//根据算法名称初始化一个SecretKey实例，密钥必须是指定长度
        cipher.init(Cipher.ENCRYPT_MODE,key);//使用SerectKey初始化Cipher实例，并设置加密或解密模式
        return  cipher.doFinal(messageBytes);
    }
    private static byte[] decryptByECB(byte[] keyBytes, byte[] messageBytes) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");//设置key
        cipher.init(Cipher.DECRYPT_MODE,key);
        return  cipher.doFinal(messageBytes);



    }


}

/**
 * 口令加密(通过口令和盐salt 进行加密解密)
 */
class CommandPassword{
    public static void main(String[] args) throws Exception {
        // 把BouncyCastle作为Provider添加到java.security:
        Security.addProvider(new BouncyCastleProvider()); //需要引入BouncyCastle
        // 原文:
        String message = "Hello, world!";
        // 加密口令:
        String password = "hello12345";
        // 16 bytes随机Salt:
        byte[] salt = SecureRandom.getInstanceStrong().generateSeed(16);
        System.out.printf("salt: %032x\n", new BigInteger(1, salt));
        // 加密:
        byte[] data = message.getBytes("UTF-8");
        byte[] encrypted = encrypt(password, salt, data);
        System.out.println("encrypted: " + Base64.getEncoder().encodeToString(encrypted));
        // 解密:
        byte[] decrypted = decrypt(password, salt, encrypted);
        System.out.println("decrypted: " + new String(decrypted, "UTF-8"));
    }

    // 加密:
    public static byte[] encrypt(String password, byte[] salt, byte[] input) throws GeneralSecurityException {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory skeyFactory = SecretKeyFactory.getInstance("PBEwithSHA1and128bitAES-CBC-BC");//指定算法是PBEwithSHA1and128bitAES-CBC-BC
        SecretKey skey = skeyFactory.generateSecret(keySpec);
        PBEParameterSpec pbeps = new PBEParameterSpec(salt, 1000); //指定了循环次数1000，循环次数越多，暴力破解需要的计算量就越大
        Cipher cipher = Cipher.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
        cipher.init(Cipher.ENCRYPT_MODE, skey, pbeps);//真正的AES密钥是调用Cipher的init()方法时同时传入SecretKey和PBEParameterSpec实现
        return cipher.doFinal(input);
    }

    // 解密:
    public static byte[] decrypt(String password, byte[] salt, byte[] input) throws GeneralSecurityException {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory skeyFactory = SecretKeyFactory.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
        SecretKey skey = skeyFactory.generateSecret(keySpec);
        PBEParameterSpec pbeps = new PBEParameterSpec(salt, 1000);
        Cipher cipher = Cipher.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
        cipher.init(Cipher.DECRYPT_MODE, skey, pbeps);
        return cipher.doFinal(input);
    }
}

/**
 * 秘钥交换算法(通过对方的公钥和自己的私钥来协商真正的秘钥)
 *
 */
class SecretExchange{
    public static class Main {
        public static void main(String[] args) {
            // Bob和Alice:
            Person bob = new Person("Bob");
            Person alice = new Person("Alice");

            // 各自生成KeyPair:
            bob.generateKeyPair();
            alice.generateKeyPair();

            // 双方交换各自的PublicKey:
            // Bob根据Alice的PublicKey生成自己的本地密钥:
            bob.generateSecretKey(alice.publicKey.getEncoded());
            // Alice根据Bob的PublicKey生成自己的本地密钥:
            alice.generateSecretKey(bob.publicKey.getEncoded());

            // 检查双方的本地密钥是否相同:
            bob.printKeys();
            alice.printKeys();
            // 双方的SecretKey相同，后续通信将使用SecretKey作为密钥进行AES加解密...
        }
    }

    static class Person {
        public final String name;

        public PublicKey publicKey;
        private PrivateKey privateKey;
        private byte[] secretKey;

        public Person(String name) {
            this.name = name;
        }

        // 生成本地KeyPair:
        public void generateKeyPair() {
            try {
                KeyPairGenerator kpGen = KeyPairGenerator.getInstance("DH");
                kpGen.initialize(512);
                KeyPair kp = kpGen.generateKeyPair();
                this.privateKey = kp.getPrivate();
                this.publicKey = kp.getPublic();
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            }
        }

        public void generateSecretKey(byte[] receivedPubKeyBytes) {
            try {
                // 从byte[]恢复PublicKey:
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(receivedPubKeyBytes);
                KeyFactory kf = KeyFactory.getInstance("DH");
                PublicKey receivedPublicKey = kf.generatePublic(keySpec);
                // 生成本地密钥:
                KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
                keyAgreement.init(this.privateKey); // 自己的PrivateKey
                keyAgreement.doPhase(receivedPublicKey, true); // 对方的PublicKey
                // 生成SecretKey密钥:
                this.secretKey = keyAgreement.generateSecret();
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            }
        }

        public void printKeys() {
            System.out.printf("Name: %s\n", this.name);
            System.out.printf("Private key: %x\n", new BigInteger(1, this.privateKey.getEncoded()));
            System.out.printf("Public key: %x\n", new BigInteger(1, this.publicKey.getEncoded()));
            System.out.printf("Secret key: %x\n", new BigInteger(1, this.secretKey));
        }
    }
}

/**
 * 非对称加密：(通过公钥加密，私钥解密)
 *    明文可以定义为AES的秘钥，再通过AES方式加密通信
 *      byte[] pkData = ...
 *      byte[] skData = ...
 *      KeyFactory kf = KeyFactory.getInstance("RSA");
 *      // 恢复公钥:
 *      X509EncodedKeySpec pkSpec = new X509EncodedKeySpec(pkData);
 *      PublicKey pk = kf.generatePublic(pkSpec);
 *      // 恢复私钥:
 *      PKCS8EncodedKeySpec skSpec = new PKCS8EncodedKeySpec(skData);
 *      PrivateKey sk = kf.generatePrivate(skSpec);
 */
class NotSymmetryEncypt{
    public static void main(String[] args) throws Exception {
        // 明文:
        byte[] plain = "Hello, encrypt use RSA".getBytes("UTF-8");
        // 创建公钥／私钥对:
        Person alice = new Person("Alice");
        // 用Alice的公钥加密:
        byte[] pk = alice.getPublicKey();
        System.out.println(String.format("public key: %x", new BigInteger(1, pk)));
        byte[] encrypted = alice.encrypt(plain);
        System.out.println(String.format("encrypted: %x", new BigInteger(1, encrypted)));
        // 用Alice的私钥解密:
        byte[] sk = alice.getPrivateKey();
        System.out.println(String.format("private key: %x", new BigInteger(1, sk)));
        byte[] decrypted = alice.decrypt(encrypted);
        System.out.println(new String(decrypted, "UTF-8"));
    }
    static class Person {
        String name;
        // 私钥:
        PrivateKey sk;
        // 公钥:
        PublicKey pk;

        public Person(String name) throws GeneralSecurityException {
            this.name = name;
            // 生成公钥／私钥对:
            KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
            kpGen.initialize(1024);
            KeyPair kp = kpGen.generateKeyPair();
            this.sk = kp.getPrivate();
            this.pk = kp.getPublic();
        }

        // 把私钥导出为字节
        public byte[] getPrivateKey() {
            try{
                Path write = Files.write(Paths.get("./Notes/javaNote/encrypt/private.md"), this.sk.getEncoded());
                System.out.println(write+"------>");
            }catch(Exception e){
                e.printStackTrace();
            }finally{

            }

            return this.sk.getEncoded();
        }

        // 把公钥导出为字节
        public byte[] getPublicKey() {
            return this.pk.getEncoded();
        }

        // 用公钥加密:
        public byte[] encrypt(byte[] message) throws GeneralSecurityException {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, this.pk);
            return cipher.doFinal(message);
        }

        // 用私钥解密:
        public byte[] decrypt(byte[] input) throws GeneralSecurityException {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, this.sk);
            return cipher.doFinal(input);
        }
    }
}
/**
 * 签名算法
 *    数字签名就是用发送方的私钥对原始数据的hash值进行签名，只有用发送方公钥才能通过签名验证。
 *
 */
class Signatrue{

        public static void main(String[] args) throws GeneralSecurityException {
            // 生成RSA公钥/私钥:
            KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
            kpGen.initialize(1024);
            KeyPair kp = kpGen.generateKeyPair();
            PrivateKey sk = kp.getPrivate();
            PublicKey pk = kp.getPublic();

            // 待签名的消息:
            byte[] message = "Hello, I am Bob!".getBytes(StandardCharsets.UTF_8);

            // 用私钥签名:
            Signature s = Signature.getInstance("SHA1withRSA");
            s.initSign(sk);
            s.update(message);
            byte[] signed = s.sign();
            System.out.println(String.format("signature: %x", new BigInteger(1, signed)));

            // 用公钥验证:
            Signature v = Signature.getInstance("SHA1withRSA");
            v.initVerify(pk);
            v.update(message);
            boolean valid = v.verify(signed);
            System.out.println("valid? " + valid);
    }
}

/**
 * 数字证书
 *    把签名算法的公钥交给权威的CA机构保管，生成对应的CA证书。
 *    接受方，
 */
class DC{
    public static void main(String[] args) throws Exception {
        byte[] message = "Hello, use X.509 cert!".getBytes("UTF-8");
        // 读取KeyStore:
        KeyStore ks = loadKeyStore("/my.keystore", "123456");
        // 读取私钥:
        PrivateKey privateKey = (PrivateKey) ks.getKey("mycert", "123456".toCharArray());
        // 读取证书:
        X509Certificate certificate = (X509Certificate) ks.getCertificate("mycert");
        // 加密:
        byte[] encrypted = encrypt(certificate, message);
        System.out.println(String.format("encrypted: %x", new BigInteger(1, encrypted)));
        // 解密:
        byte[] decrypted = decrypt(privateKey, encrypted);
        System.out.println("decrypted: " + new String(decrypted, "UTF-8"));
        // 签名:
        byte[] sign = sign(privateKey, certificate, message);
        System.out.println(String.format("signature: %x", new BigInteger(1, sign)));
        // 验证签名:
        boolean verified = verify(certificate, message, sign);
        System.out.println("verify: " + verified);
    }

    static KeyStore loadKeyStore(String keyStoreFile, String password) {
        try (InputStream input = DC.class.getResourceAsStream(keyStoreFile)) {
            if (input == null) {
                throw new RuntimeException("file not found in classpath: " + keyStoreFile);
            }
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(input, password.toCharArray());
            return ks;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 公钥加密
     * @param certificate
     * @param message
     * @return
     * @throws GeneralSecurityException
     */
    static byte[] encrypt(X509Certificate certificate, byte[] message) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(certificate.getPublicKey().getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, certificate.getPublicKey());
        return cipher.doFinal(message);
    }

    /**
     * 私钥解密
     * @param privateKey
     * @param data
     * @return
     * @throws GeneralSecurityException
     */
    static byte[] decrypt(PrivateKey privateKey, byte[] data) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥签名
     * @param privateKey
     * @param certificate
     * @param message
     * @return
     * @throws GeneralSecurityException
     */
    static byte[] sign(PrivateKey privateKey, X509Certificate certificate, byte[] message)
            throws GeneralSecurityException {
        Signature signature = Signature.getInstance(certificate.getSigAlgName());
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    /**
     * 公钥验证签名
     * @param certificate
     * @param message
     * @param sig
     * @return
     * @throws GeneralSecurityException
     */
    static boolean verify(X509Certificate certificate, byte[] message, byte[] sig) throws GeneralSecurityException {
        Signature signature = Signature.getInstance(certificate.getSigAlgName());
        signature.initVerify(certificate);
        signature.update(message);
        return signature.verify(sig);
    }
}



