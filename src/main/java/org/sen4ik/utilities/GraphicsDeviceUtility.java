package org.sen4ik.utilities;

import java.awt.*;

public class GraphicsDeviceUtility {

	private static GraphicsDevice[] getGraphicsDevice() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gd = ge.getScreenDevices();
		return gd;
	}

	public static int getNumberOfDisplays() {
		GraphicsDevice[] gd = getGraphicsDevice();
		int displaysCount = gd.length;
		return displaysCount;
	}

	public static int getTotalDisplayWidth() {
		int totalWidth = 0;
		GraphicsDevice[] gd = getGraphicsDevice();
		for (GraphicsDevice current : gd) {
			DisplayMode dm = current.getDisplayMode();
			totalWidth += dm.getWidth();
		}
		return totalWidth;
	}

	public static int getTotalDisplayHeight() {
		int totalHeight = 0;
		GraphicsDevice[] gd = getGraphicsDevice();
		for (GraphicsDevice current : gd) {
			DisplayMode dm = current.getDisplayMode();
			totalHeight = dm.getHeight();
		}
		return totalHeight;
	}

	/**
	 * Returns the bounds of the display in the device coordinates.
	 *
	 * @param displayIndex
	 * @return Returns null when no display is present for the provided index. Otherwise, returns the bounds.
	 */
	public static Rectangle getDisplayBounds(int displayIndex) {
		GraphicsDevice[] gd = getGraphicsDevice();
		if (displayIndex > (gd.length - 1)) {
			return null;
		}
		GraphicsDevice d = gd[displayIndex];
		GraphicsConfiguration[] gc = d.getConfigurations();
		Rectangle r = gc[0].getBounds();
		return r;
	}

}