package OBSIDIAN.VolcanoRenderer;

import Draughts.Scene.Scene;
import OBSIDIAN.VolcanoRenderer.utils.SceneRenderer;
import OBSIDIAN.utils.GUI.GUIRender;
import OBSIDIAN.utils.Window;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;

public class Render {
    private SceneRenderer sceneRender;
    private GUIRender guiRender;
    public Render(Window window) {
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        sceneRender = new SceneRenderer();
        guiRender = new GUIRender(window);
    }

    public void cleanup() {
        sceneRender.cleanup();
        guiRender.cleanup();
    }

    public void render(Window window, Scene scene) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glViewport(0, 0, window.getWidth(), window.getHeight());
        sceneRender.render(scene);
    }
    public void resize(int width, int height) {
        guiRender.resize(width, height);
    }
}
