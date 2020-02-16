import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

import java.awt.*;

public class Main extends SimpleApplication {
    public static Main app;
    public static AppSettings settings;

    public static void main(String[] args) {
        app = new Main();
        app.setShowSettings(false);

        settings = new AppSettings(true);
        settings.setTitle("3D Playground");
        settings.setVSync(true);

        // Dimension 16:9 res... (100 / 16) * 9 = 56
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        settings.setResolution(dimension.width - 100, dimension.height - 56);
        //settings.setResolution(dimension.width, dimension.height);
        //settings.setFullscreen(true);

        app.setSettings(settings);

        app.start();
    }

    @Override
    public void simpleInitApp() {
        getFlyByCamera().setMoveSpeed(10f);

        // Template
        getInputManager().addMapping("Test", new KeyTrigger(KeyInput.KEY_SPACE));
        getInputManager().addListener((ActionListener) (name, isPressed, tpf) -> {
            if (!isPressed)
                return;

            System.out.println("Here1");
        }, "Test");

        getInputManager().addMapping("Fullscreen", new KeyTrigger(KeyInput.KEY_F11));
        getInputManager().addListener((ActionListener) (name, isPressed, tpf) -> {
            if (!isPressed)
                return;

            settings.setFullscreen(!settings.isFullscreen());

            GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            DisplayMode[] modes = device.getDisplayModes();

            int s = 0;
            for (DisplayMode mode : modes) {
                System.out.println(s + ": " + mode.getWidth() + ", " + mode.getHeight());
                s++;
            }

            DisplayMode bestRes = modes[0];
            if (!settings.isFullscreen()) {
                DisplayMode nextRes = null;
                for (int i = 1; i < modes.length; i++) {
                    if (bestRes.getWidth() != modes[i].getWidth() &&
                        bestRes.getHeight() != modes[i].getWidth()) {
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
            System.out.println("Selected next res: " + bestRes.getWidth() + ", " + bestRes.getHeight());
            settings.setResolution(bestRes.getWidth(), bestRes.getHeight());

            app.setSettings(settings);
            app.restart();
        }, "Fullscreen");


        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        ColorRGBA yellow = new ColorRGBA(255, 255,0,0);
        mat.setColor("Color", yellow);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }
}
