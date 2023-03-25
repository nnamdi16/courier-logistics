package com.logistics.courierLogistics.model.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

public enum ID {
    INSTANCE;

    private static final Random RANDOM1;
    private static final Random RANDOM2;
    private static final Random RANDOM3;
    private static final String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final long GLOBAL_PROCESS_ID;
    static Logger log = LoggerFactory.getLogger(ID.class);

    static {
        long time = System.currentTimeMillis();
        long nanoTime = System.nanoTime();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long addressHashCode;
        try {
            InetAddress inetAddress;
            inetAddress = InetAddress.getLocalHost();
            addressHashCode = inetAddress.getHostName().hashCode()
                    ^ inetAddress.getHostAddress().hashCode();
        } catch (Exception err) {
            log.warn("Unable to get local host information.", err);
            addressHashCode = ID.class.hashCode();
        }
        GLOBAL_PROCESS_ID = time ^ nanoTime ^ freeMemory ^ addressHashCode;
        RANDOM1 = new Random(time);
        RANDOM2 = new Random(nanoTime);
        RANDOM3 = new Random(addressHashCode ^ freeMemory);
    }




    public static long generateLong() {
        return Math.abs(RANDOM1.nextLong() ^ RANDOM2.nextLong() ^ RANDOM3.nextLong());
    }

    public static int generateInt() {
        return (int) generateLong();
    }

    public static String generateUUIDString() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString().replaceAll("-", "").toUpperCase();



    }


    public static String generateRandomCharacters(int num, String characterSampleSpace) {

        String generatedString = "";
        for (int i = 0; i < num; i++) {

            char letter = (characterSampleSpace).charAt(RANDOM2.nextInt(characterSampleSpace.length()));
            generatedString.concat(String.valueOf(letter));

        }
        log.debug("Random generatedString:- {}", generatedString);
        return generatedString;

    }
}
