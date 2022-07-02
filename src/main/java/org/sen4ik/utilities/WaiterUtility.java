package org.sen4ik.utilities;

public class WaiterUtility {

	public static void pause(int seconds) {
		pauseMilliseconds(seconds * 1000);
	}

	public static void pauseMilliseconds(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}