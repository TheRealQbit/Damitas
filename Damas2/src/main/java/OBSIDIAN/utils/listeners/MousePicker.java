package OBSIDIAN.utils.listeners;

import OBSIDIAN.utils.Camara.Camara;
import org.joml.*;
import org.lwjgl.system.windows.MOUSEINPUT;


public class MousePicker {
    private Vector3f currentRay;
    private Matrix4f projectionMatrix;
    private Matrix4f viewMatrix;
    private Camara camara;
    public MousePicker(Camara camara, Matrix4f projection) {
        this.camara = camara;
        this.projectionMatrix = projection;
        this.viewMatrix = camara.getViewMatrix();
        currentRay = new Vector3f();
    }
    public Vector3f getCurrentRay() {
        return currentRay;
    }

    public void update() {
        viewMatrix = camara.getViewMatrix();
        currentRay = calculateMouseRay();
    }
    private Vector3f calculateMouseRay(){
        float mouseX = MOUSEINPUT.DX;
        float mouseY = MOUSEINPUT.DY;
        Vector2f normalizedCoords = getNormalizedDeviceCoords(mouseX, mouseY);
        Vector4f clipCoords = new Vector4f(normalizedCoords.x, normalizedCoords.y, -1f, 1f);
        Vector4f eyeCoords = toEyeCoords(clipCoords);
        Vector3f worldRay = toWolrdCoords(eyeCoords);
        return worldRay;
    }
    private Vector3f toWolrdCoords(Vector4f eyeCoords){
        Matrix4f invertedView = viewMatrix.invert();
        Vector4f rayWorld = invertedView.transform(eyeCoords);
        Vector3f mouseRay = new Vector3f(rayWorld.x, rayWorld.y, rayWorld.z);
        mouseRay.normalize();
        return mouseRay;
    }
    private Vector4f toEyeCoords(Vector4f clipCoords){
        Matrix4f invertedProjection = projectionMatrix.invert();
        Vector4f eyeCoords = invertedProjection.transform(clipCoords, null);
        return new Vector4f(eyeCoords.x, eyeCoords.y, -1f, 0f);
    }
    private Vector2f getNormalizedDeviceCoords(float mouseX, float mouseY){
        float x = (2.0f * mouseX) / 800 - 1f;
        float y = (2.0f * mouseY) / 600 - 1f;
        return new Vector2f(x, y);
    }
}
