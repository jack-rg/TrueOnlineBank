package Util;


import java.security.MessageDigest;

public class MD5Util {

    /**
     * Several Security rules are:
     * 1. Only Manager has the authority to look throught the User Database / Logs
     * 2. Manager can only see a encrypted password
     *
     * Note: MD5 + salt encrypted
     */
    // HEX_DIGITS
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private Object salt;
    private String algorithm;

    public MD5Util(Object salt, String algorithm) {
        this.salt = salt;
        this.algorithm = algorithm;
    }


    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public String encode(String rawPass) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm); // encrypted String
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(
                    rawPass).getBytes("utf-8")));
        } catch (Exception ex) {
        }
        return result;
    }

    public boolean isPasswordValid(String encPass, String rawPass) {
        String pass1 = "" + encPass;
        String pass2 = encode(rawPass);

        return pass1.equals(pass2);
    }

    private String mergePasswordAndSalt(String password) {
        if (password == null) {
            password = "";
        }

        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }

//    public static void main(String[] args) {
//        // Salt
//        String salt = "helloworld";
//        MD5Util encoderMD5Util = new MD5Util(salt, "MD5");
//        String encode = encoderMD5Util.encode("test");
//        // MD5 with salt
//        System.out.println("MD5 with salt " + encode);
//        // MD5 without salt
//        System.out.println("MD5 without salt " + new MD5Util(null, "MD5").encode("test"));
//
//    }

}
