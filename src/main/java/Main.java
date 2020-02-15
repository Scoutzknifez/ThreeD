import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.util.SkyFactory;

import java.awt.*;

public class Main extends SimpleApplication {
    public static void main(String[] args) {

        Main app = new Main();
        app.setShowSettings(false);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        AppSettings settings = new AppSettings(true);
        settings.setTitle("3D Playground");
        settings.setVSync(true);

        settings.setResolution(dimension.width - 100, dimension.height - 56);
        //settings.setResolution(dimension.width, dimension.height);
        //settings.setFullscreen(true);

        app.setSettings(settings);

        app.start();
    }

    @Override
    public void simpleInitApp() {
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
}
