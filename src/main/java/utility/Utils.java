package utility;

import com.jme3.system.AppSettings;

import java.awt.*;

public class Utils {
    public static DisplayMode getNextSmallestScreen(AppSettings settings) {
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode[] modes = device.getDisplayModes();

            /* int s = 0;
            for (DisplayMode mode : modes) {
                System.out.println(s + ": " + mode.getWidth() + ", " + mode.getHeight());
                s++;
            } */

        DisplayMode bestRes = modes[0];
        if (!settings.isFullscreen()) {
            DisplayMode nextRes = null;
            for (int i = 1; i < modes.length; i++) {
                if (bestRes.getWidth() != modes[i].getWidth() &&
                        bestRes.getHeight() != modes[i].getHeight()) {
                    if (nextRes != null) {
                        if (modes[i].getWidth() > nextRes.getWidth() &&
                                modes[i].getHeight() > nextRes.getHeight()) {
                            nextRes = modes[i];
                        }
                    } else {
                        nextRes = modes[i];
                    }
                }
            }

            if (nextRes != null)
                bestRes = nextRes;
        }
        return bestRes;
    }
}
