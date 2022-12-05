package Draughts.logic;

import Draughts.Scene.Scene;
import OBSIDIAN.utils.models.GameObject;
import OBSIDIAN.utils.models.Model;

public class init {
    private GameObject B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, B13, B14, B15, B16, B17, B18, B19, B20;
    private GameObject W1, W2, W3, W4, W5, W6, W7, W8, W9, W10, W11, W12, W13, W14, W15, W16, W17, W18, W19, W20;
    int [][] whitePOS = new int[8][8];
    int [][] blackPOS = new int[8][8];
    void entityGen(Scene scene, Model damaModel){
        //Black
            B1 = new GameObject("B1", damaModel.getId());
            B1.setPosition(0, 0, 1);
            scene.addGameObject(B1);
            B2 = new GameObject("2", damaModel.getId());
            B2.setPosition(0, 0, 1);
            scene.addGameObject(B2);
            B3 = new GameObject("B2", damaModel.getId());
            B3.setPosition(0, 0, 1);
            scene.addGameObject(B3);
            B4 = new GameObject("B3", damaModel.getId());
            B4.setPosition(0, 0, 1);
            scene.addGameObject(B4);

            B5 = new GameObject("B4", damaModel.getId());
            B5.setPosition(0, 0, 1);
            scene.addGameObject(B5);
            B6 = new GameObject("B4", damaModel.getId());
            B6.setPosition(0, 0, 1);
            scene.addGameObject(B6);
            B7 = new GameObject("B4", damaModel.getId());
            B7.setPosition(0, 0, 1);
            scene.addGameObject(B7);
            B8 = new GameObject("B5", damaModel.getId());
            B8.setPosition(0, 0, 1);
            scene.addGameObject(B8);
            B9 = new GameObject("B6", damaModel.getId());
            B9.setPosition(0, 0, 1);
            scene.addGameObject(B9);
            B10 = new GameObject("B7", damaModel.getId());
            B10.setPosition(0, 0, 1);
            scene.addGameObject(B10);
            B11 = new GameObject("B8", damaModel.getId());
            B11.setPosition(0, 0, 1);
            scene.addGameObject(B11);
            B12 = new GameObject("B9", damaModel.getId());
            B12.setPosition(0, 0, 1);
            scene.addGameObject(B12);
            B13 = new GameObject("B10", damaModel.getId());
            B13.setPosition(0, 0, 1);
            scene.addGameObject(B13);
            B14 = new GameObject("dama-entity", damaModel.getId());
            B14.setPosition(0, 0, 1);
            scene.addGameObject(B14);
            B15 = new GameObject("dama-entity", damaModel.getId());
            B15.setPosition(0, 0, 1);
            scene.addGameObject(B15);
            B16 = new GameObject("dama-entity", damaModel.getId());
            B16.setPosition(0, 0, 1);
            scene.addGameObject(B16);
            B17 = new GameObject("dama-entity", damaModel.getId());
            B17.setPosition(0, 0, 1);
            scene.addGameObject(B17);
            B18 = new GameObject("dama-entity", damaModel.getId());
            B18.setPosition(0, 0, 1);
            scene.addGameObject(B18);
            B19 = new GameObject("dama-entity", damaModel.getId());
            B19.setPosition(0, 0, 1);
            scene.addGameObject(B19);
            B20 = new GameObject("dama-entity", damaModel.getId());
            B20.setPosition(0, 0, 1);
            scene.addGameObject(B20);
    }
    public void allocate(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                blackPOS[i][j] = 0;
            }
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 8; j++){
                if(i % 2 == 1){
                    if(j % 2 == 0){
                        blackPOS[i][j] = 1;
                    }
                }else{
                    if(j % 2 != 0){
                        blackPOS[i][j] = 1;
                    }
                }
            }
        }
        for(int i = 5; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i % 2 == 0){
                    if(j % 2 == 0){
                        blackPOS[i][j] = 2;
                    }
                }else{
                    if(j % 2 != 0){
                        blackPOS[i][j] = 2;
                    }
                }
            }
        }
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                whitePOS[i][j] = 0;
            }
        }
        for(int i = 5; i < 7; i++){
            for(int j = 0; j < 8; j++){
                if(i % 2 == 1){
                    if(j % 2 == 0){
                        whitePOS[i][j] = 1;
                    }
                }else{
                    if(j % 2 != 0){
                        whitePOS[i][j] = 1;
                    }
                }
            }
        }
        for(int i = 5; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i % 2 == 0){
                    if(j % 2 == 0){
                        whitePOS[i][j] = 2;
                    }
                }else{
                    if(j % 2 != 0){
                        whitePOS[i][j] = 2;
                    }
                }
            }
        }
        for(int i = 7; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(j % 2 == 0){
                    whitePOS[i][j] = 1;
                }
            }
        }
    }
    public void positioning(){

    }
}
