package org.sen4ik.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaiterUtil {

    public static void pause(int seconds) {
        log.info("CALLED: pause("  + seconds + ")");
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) { }
    }

    public static void pauseMilliseconds(int milliseconds) {
        log.info("CALLED: pauseMilliseconds("  + milliseconds + ")");
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) { }
    }

}
