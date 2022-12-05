package Draughts.Scene;

import OBSIDIAN.VolcanoRenderer.utils.Textures.TextureCache;
import OBSIDIAN.utils.Camara.Camara;
import OBSIDIAN.utils.Camara.Projection;
import OBSIDIAN.utils.GUI.GUInterface;
import OBSIDIAN.utils.models.GameObject;
import OBSIDIAN.utils.models.Model;

import java.util.*;



public class Scene {
    private Map<String, Model> modelMap;
    private Projection projection;
    private TextureCache textureCache;
    private Camara camara;
    private GUInterface guiInstance;

    public Scene(int width, int height) {
        modelMap = new HashMap<>();
        projection = new Projection(width, height);
        textureCache = new TextureCache();
        camara = new Camara();
    }
    public GUInterface getGuiInstance() {
        return guiInstance;
    }
    public void addGameObject(GameObject entity) {
        String modelId = entity.getModelId();
        Model model = modelMap.get(modelId);
        if (model == null) {
            throw new RuntimeException("Could not find model [" + modelId + "]");
        }
        model.getEntitiesList().add(entity);
    }

    public void addModel(Model model) {
        modelMap.put(model.getId(), model);
    }
    public Camara getCamera() {
        return camara;
    }

    public void cleanup() {
        modelMap.values().stream().forEach(Model::cleanup);
    }

    public Map<String, Model> getModelMap() {
        return modelMap;
    }

    public Projection getProjection() {
        return projection;
    }

    public TextureCache getTextureCache() {
        return textureCache;
    }


    public void resize(int width, int height) {
        projection.updateProjMatrix(width, height);
    }

    public void setGuiInstance(GUInterface guiInstance) {
        this.guiInstance = guiInstance;
    }

}
