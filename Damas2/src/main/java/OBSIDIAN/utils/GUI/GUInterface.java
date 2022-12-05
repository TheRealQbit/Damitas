package OBSIDIAN.utils.GUI;

import Draughts.Scene.Scene;
import OBSIDIAN.utils.Window;

public interface GUInterface {
    void drawGui();

    boolean handleGuiInput(Scene scene, Window window);
}
