package org.sen4ik.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SysUtil {

    private static String operatingSystem = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {
        log.info("CALLED: isWindows()");
        boolean result = (operatingSystem.indexOf("win") >= 0);
        log.info("isWindows: " + result);
        return result;
    }

    public static boolean isMac() {
        log.info("CALLED: isMac()");
        boolean result = (operatingSystem.indexOf("mac") >= 0);
        log.info("isMac: " + result);
        return result;
    }

    public static boolean isUnix() {
        log.info("CALLED: isUnix()");
        boolean result = (operatingSystem.indexOf("nix") >= 0 || operatingSystem.indexOf("nux") >= 0 || operatingSystem.indexOf("aix") > 0 );
        log.info("isUnix: " + result);
        return result;
    }

}
