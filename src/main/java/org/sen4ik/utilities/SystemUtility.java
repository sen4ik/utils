package org.sen4ik.utilities;

public class SystemUtility {

	private static String operatingSystem = System.getProperty("os.name").toLowerCase();

	public static boolean isWindows() {
		boolean result = (operatingSystem.indexOf("win") >= 0);
		return result;
	}

	public static boolean isMac() {
		boolean result = (operatingSystem.indexOf("mac") >= 0);
		return result;
	}

	public static boolean isUnix() {
		boolean result = (operatingSystem.indexOf("nix") >= 0 || operatingSystem.indexOf("nux") >= 0 || operatingSystem.indexOf("aix") > 0);
		return result;
	}

}