import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import utility.Utils;

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

        DisplayMode res = Utils.getNextSmallestScreen(settings);
        settings.setResolution(res.getWidth(), res.getHeight());

        app.setSettings(settings);

        app.start();
    }

    @Override
    public void simpleInitApp() {
        initBoundKeys();

        getFlyByCamera().setMoveSpeed(10f);

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

    public void initBoundKeys() {
        getInputManager().addMapping("Fullscreen", new KeyTrigger(KeyInput.KEY_F11));
        getInputManager().addListener((ActionListener) (name, isPressed, tpf) -> {
            if (!isPressed)
                return;

            settings.setFullscreen(!settings.isFullscreen());
            DisplayMode bestRes = Utils.getNextSmallestScreen(settings);

            System.out.println("Selected next res: " + bestRes.getWidth() + ", " + bestRes.getHeight());
            settings.setResolution(bestRes.getWidth(), bestRes.getHeight());

            app.setSettings(settings);
            app.restart();
        }, "Fullscreen");
    }
}
