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
    public static void main(String[] args) {

        Main app = new Main();
        app.setShowSettings(false);

        AppSettings settings = new AppSettings(true);
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

        getInputManager().addMapping("Test", new KeyTrigger(KeyInput.KEY_SPACE));
        getInputManager().addListener((ActionListener) (name, isPressed, tpf) -> {
            System.out.println(tpf);
        }, "Test");


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
