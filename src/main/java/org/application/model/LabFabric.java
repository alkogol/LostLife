package org.application.model;

import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public enum LabFabric {
    INSTANCE;

    public static LabFabric getInstance() {
        return INSTANCE;
    }


    public Queue<ArrayPairOfIndexes> valueOf(int[][] array) {
        Queue<ArrayPairOfIndexes> queue = new LinkedList<ArrayPairOfIndexes>();
        for (int field = 0; field < array.length; field++) {
            for (int column = 0; column < array[field].length; column++) {
                ArrayPairOfIndexes arrayPairOfIndexes = new ArrayPairOfIndexes(column, field);
                queue.add(arrayPairOfIndexes);
            }
        }
        Collections.shuffle((LinkedList)queue);
        return queue;
    }
    //Version 2 not work only for future
    public int[][] generateRandomLabBasedOnRules(int width, int height){
        int[][] labArray = new int[height][width];
        Queue<ArrayPairOfIndexes> queue = valueOf(labArray);
        System.out.println(queue.size());
        while (queue.size()>0){
            ArrayPairOfIndexes pair = queue.poll();
            int column = pair.getColumn();
            int field = pair.getField();
            System.out.println("poll "+column +"_" + field);
            if (LabGenUtil.isOnlyOne(labArray,column,field)
            ){
                    labArray[field][column]=1;
            }
        }
        return labArray;
    }
    //Version 1 do not remove
    public int[][] generateRandomLab(int width, int height) {
        int[][] labArray = new int[height][width];
        for (int field = 0; field < labArray.length; field++) {
            for (int column = 0; column < labArray[field].length; column++) {
                if (LabGenUtil.isAloneIncludeBorder(labArray, column, field)) {
                    labArray[field][column] = 1;
                }
            }
        }
        return labArray;
    }

    public int[][] getLabArrayMock() {
        int[][] labArray = new int[][]{
                {1, 0, 0, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1, 1, 1, 0, 0}};
        return labArray;
    }

    public void drawLab(GraphicsContext graphicsContext, int[][] labArray, int size) {
        for (int field = 0; field < labArray.length; field++) {
            for (int column = 0; column < labArray[field].length; column++) {
                if (labArray[field][column] == 1) {
                    graphicsContext.setFill(Color.BLACK);
                }
                else if (labArray[field][column] ==2){
                    graphicsContext.setFill(Color.GREEN);
                }else if (labArray[field][column] ==4) {
                    graphicsContext.setFill(Color.WHEAT);
                }else if (labArray[field][column] ==5){
                    graphicsContext.setFill(Color.RED);
                }
                else {
                    graphicsContext.setFill(Color.WHITE);
                }
                graphicsContext.fillRect(column * size, field * size, size, size);
            }
        }
    }

    public void drawLabMock(GraphicsContext graphicsContext, int size) {
        int[][] labArray = getLabArrayMock();
        for (int field = 0; field < labArray.length; field++) {
            for (int column = 0; column < labArray[field].length; column++) {
                if (labArray[field][column] == 1) {
                    graphicsContext.setFill(Color.BLACK);
                } else {
                    graphicsContext.setFill(Color.GREY);
                }
                graphicsContext.fillRect(column * size, field * size, size, size);
            }
        }
    }
    public ArrayPairOfIndexes isCollision(int[][] array, double x, double y, int size){
        double xIndex = x/size;
        double yIndex = y/size;
        ArrayPairOfIndexes arrayPairOfIndexes= new ArrayPairOfIndexes((int) Math.floor(xIndex), (int) Math.floor(yIndex));
        return LabGenUtil.isExist(array,arrayPairOfIndexes.getColumn(),arrayPairOfIndexes.getField()) ? arrayPairOfIndexes : null;
    }
    public void drawSquare(int size, int x, int y,GraphicsContext graphicsContext,Color color){
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x*size,y*size,size,size);
    }

}




