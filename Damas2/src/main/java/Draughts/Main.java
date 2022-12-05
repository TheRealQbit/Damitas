package Draughts;

import Draughts.Scene.Scene;
import OBSIDIAN.OBSIDIAN;
import OBSIDIAN.VolcanoRenderer.Render;
import OBSIDIAN.utils.Camara.Camara;
import OBSIDIAN.utils.GUI.GUInterface;
import OBSIDIAN.utils.IGameLogic;
import OBSIDIAN.utils.Window;
import OBSIDIAN.utils.listeners.MouseListener;
import OBSIDIAN.utils.listeners.MousePicker;
import OBSIDIAN.utils.models.GameObject;
import OBSIDIAN.utils.models.Model;
import OBSIDIAN.utils.models.ModelLoader;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiCond;
import org.joml.Vector2f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.*;

public class Main implements IGameLogic, GUInterface {

    private GameObject damaEntity;
    private GameObject tableroEntity;

    private Vector4f displInc = new Vector4f();
    private static final float MOUSE_SENSITIVITY = 0.1f;
    private static final float MOVEMENT_SPEED = 0.005f;
    private float rotation;

    public static void main(String[] args) {
        Main main = new Main();
        OBSIDIAN gameEng = new OBSIDIAN("Draughts Game", new Window.WindowOptions(), main);
        gameEng.start();

    }

    @Override
    public void cleanup() {

    }
    @Override
    public void drawGui() {
        ImGui.newFrame();
        ImGui.setNextWindowPos(0, 0, ImGuiCond.Always);
        ImGui.showDemoWindow();
        ImGui.endFrame();
        ImGui.render();
    }

    @Override
    public boolean handleGuiInput(Scene scene, Window window) {
        ImGuiIO imGuiIO = ImGui.getIO();
        MouseListener mouseInput = window.getMouseInput();
        Vector2f mousePos = mouseInput.getCurrentPos();
        imGuiIO.setMousePos(mousePos.x, mousePos.y);
        imGuiIO.setMouseDown(0, mouseInput.isLeftButtonPressed());
        imGuiIO.setMouseDown(1, mouseInput.isRightButtonPressed());

        return imGuiIO.getWantCaptureMouse() || imGuiIO.getWantCaptureKeyboard();
    }
    @Override
    public void init(Window window, Scene scene, Render render) {
        Camara camara = scene.getCamera();
        camara.moveUp(5f);
        Model tableroModel = ModelLoader.loadModel("tablero-model", "assets/models/tablero.obj",
                scene.getTextureCache());
        scene.addModel(tableroModel);

        tableroEntity = new GameObject("tablero-entity", tableroModel.getId());
        tableroEntity.setPosition(0, 0, 0);
        scene.addGameObject(tableroEntity);

        Model damaModel = ModelLoader.loadModel("dama-model", "assets/models/dama.obj",
                scene.getTextureCache());
        scene.addModel(damaModel);

        damaEntity = new GameObject("dama-entity", damaModel.getId());
        damaEntity.setPosition(0, 0, 1);
        scene.addGameObject(damaEntity);

    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis, boolean inputConsumed) {
        float move = diffTimeMillis * MOVEMENT_SPEED;
        Camara camera = scene.getCamera();
        if (window.isKeyPressed(GLFW_KEY_W)) {
            damaEntity.setPosition(damaEntity.getPosition().x()+1,0,0);

        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            damaEntity.setPosition(damaEntity.getPosition().x()-1,0,0);
        }
        MouseListener mouseInput = window.getMouseInput();
        if (mouseInput.isRightButtonPressed()) {
            Vector2f displVec = mouseInput.getDisplVec();
            camera.addRotation((float) Math.toRadians(-displVec.x * MOUSE_SENSITIVITY),
                    (float) Math.toRadians(-displVec.y * MOUSE_SENSITIVITY));
        }
        MousePicker picker = new MousePicker(scene.getCamera(), scene.getProjection().getProjMatrix());
        System.out.println(picker.getCurrentRay());
    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {
        rotation += 1.5;
        if (rotation > 360) {
            rotation = 0;
        }

        tableroEntity.updateModelMatrix();
        damaEntity.updateModelMatrix();
    }


}
