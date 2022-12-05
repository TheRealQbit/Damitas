package OBSIDIAN.utils;

import Draughts.Scene.Scene;
import OBSIDIAN.VolcanoRenderer.Render;

public interface IGameLogic {

    void cleanup();

    void init(Window window, Scene scene, Render render);

    void input(Window window, Scene scene, long diffTimeMillis, boolean inputConsumed);

    void update(Window window, Scene scene, long diffTimeMillis);

}
