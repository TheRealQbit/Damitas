package OBSIDIAN.utils.models;


import java.util.*;

import OBSIDIAN.VolcanoRenderer.utils.Mesh;
import OBSIDIAN.VolcanoRenderer.utils.Textures.Material;

public class Model {
    private final String id;
    private List<GameObject> entitiesList;
    private List<Mesh> meshList;

    private List<Material> materialList;

    public Model(String id, List<Material> materialList) {
        this.id = id;
        entitiesList = new ArrayList<>();
        this.materialList = materialList;
    }

    public void cleanup() {

        meshList.stream().forEach(Mesh::cleanup);
        materialList.stream().forEach(Material::cleanup);
    }

    public List<GameObject> getEntitiesList() {

        return entitiesList;
    }

    public String getId() {

        return id;
    }

    public List<Mesh> getMeshList() {

        return meshList;
    }

    public List<Material> getMaterialList() {
        return materialList;
    }
}
