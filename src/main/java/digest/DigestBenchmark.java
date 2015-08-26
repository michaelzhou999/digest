package digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** Benchmarking digest algorithms */
public class DigestBenchmark {

    /* Digest algorithms */
    private static MessageDigest sha1 = null;
    private static MessageDigest sha256 = null;
    private static MessageDigest sha512 = null;
    private static MessageDigest md5 = null;

    /* Initializing digest algorithms */
    static {
        try {
            sha1 = MessageDigest.getInstance("SHA-1");
            sha256 = MessageDigest.getInstance("SHA-256");
            sha512 = MessageDigest.getInstance("SHA-512");
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println(
                    "You are getting exceptions most likely because the hash algorithms are not available on this platform.");
        }
    }

    /**
     * Hash an array of bytes
     * 
     * @param bytes
     *            Bytes to be digested
     * @param digest
     *            Digest algorithm
     */
    public static byte[] hash(byte[] bytes, MessageDigest digest) throws Exception {
        digest.update(bytes);
        return digest.digest();
    }

    /**
     * Bench mark
     * 
     * @param input
     *            Input string
     * @param digest
     *            Digest algorithm instance
     * @param algoName
     *            Name of the algorithm
     * @param cycles
     *            How many cycles to compute the hash
     * @throws Exception
     */
    public static void benchmark(String input, MessageDigest digest, String algoName, int cycles) throws Exception {
        byte[] bytes = input.getBytes("UTF-8");
        long start = System.currentTimeMillis();
        for (int i = 0; i < cycles; i++) {
            hash(bytes, digest);
        }
        long end = System.currentTimeMillis();
        System.out.println(algoName + ": " + (end - start) + " ms to compute " + cycles + " cycles for string length "
                + input.length());
    }

    /** Convert bytes to hex string */
    public static String encodeHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
            sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    /** Entry point */
    public static void main(String[] args) throws Exception {
        String input = "your strin";
        int cycles = 1 * 1000 * 1000;

        benchmark(input, sha1, "SHA-1", cycles);
        benchmark(input, sha256, "SHA-256", cycles);
        benchmark(input, sha512, "SHA-512", cycles);
        benchmark(input, md5, "MD5", cycles);
        System.out.println();

        input = "your stringyour stringyour stringyour stringyour stringyour stringyour stringyour stringyour stringg";
        benchmark(input, sha1, "SHA-1", cycles);
        benchmark(input, sha256, "SHA-256", cycles);
        benchmark(input, sha512, "SHA-512", cycles);
        benchmark(input, md5, "MD5", cycles);
        System.out.println();

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < 100; i++) {
            buf.append("your strin");
        }
        benchmark(buf.toString(), sha1, "SHA-1", cycles);
        benchmark(buf.toString(), sha256, "SHA-256", cycles);
        benchmark(buf.toString(), sha512, "SHA-512", cycles);
        benchmark(buf.toString(), md5, "MD5", cycles);
        System.out.println();

        System.out.println("Sample digests of the last string:");
        byte[] hash = hash(buf.toString().getBytes("UTF-8"), sha1);
        System.out.println("SHA-1 hash: " + encodeHexString(hash));
        hash = hash(buf.toString().getBytes("UTF-8"), sha256);
        System.out.println("SHA-256 hash: " + encodeHexString(hash));
        hash = hash(buf.toString().getBytes("UTF-8"), sha512);
        System.out.println("SHA-512 hash: " + encodeHexString(hash));
        hash = hash(buf.toString().getBytes("UTF-8"), md5);
        System.out.println("MD5 hash: " + encodeHexString(hash));
    }

}

