package org.sen4ik.utils;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;

@Slf4j
public class GraphicsDeviceUtil {

    private static GraphicsDevice[] getGraphicsDevice() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        return gd;
    }

    public static int getNumberOfDisplays() {
        GraphicsDevice[] gd = getGraphicsDevice();
        int displaysCount = gd.length;
        log.debug("Number of displays: " + displaysCount);
        return displaysCount;
    }

    public static int getTotalDisplayWidth() {
        int totalWidth = 0;
        GraphicsDevice[] gd = getGraphicsDevice();
        for (GraphicsDevice current : gd) {
            DisplayMode dm = current.getDisplayMode();
            totalWidth += dm.getWidth();
        }
        log.debug("Total width: " + totalWidth);
        return totalWidth;
    }

    public static int getTotalDisplayHeight() {
        int totalHeight = 0;
        GraphicsDevice[] gd = getGraphicsDevice();
        for (GraphicsDevice current : gd) {
            DisplayMode dm = current.getDisplayMode();
            totalHeight = dm.getHeight();
        }
        log.debug("Total height: " + totalHeight);
        return totalHeight;
    }

    public static Rectangle getDisplayBounds(int displayIndex) {
        GraphicsDevice[] gd = getGraphicsDevice();
        if(displayIndex > (gd.length-1)){
            return null;
        }
        GraphicsDevice d = gd[displayIndex];
        GraphicsConfiguration[] gc = d.getConfigurations();
        return gc[0].getBounds();
    }



}
