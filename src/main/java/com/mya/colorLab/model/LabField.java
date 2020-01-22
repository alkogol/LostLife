package com.mya.colorLab.model;

public class LabField {
    private int[][] maze;
    private final int WALL = 1;
    private final int PASS = 0;
    private int width;
    private int height;
    private final int ITERATIONS = 10000;

    private final int POINT_X_START = 3;
    private final int POINT_Y_START = 3;

    public int[][] generateLab(int width,int height){
        if (width%2==0){
            width++;
        }
        if (height%2==0){
            height++;
        }

        this.width=width;
        this.height=height;
        maze = new int[height][width];

        //Заполняем весь лабиринт стенами
        fillWalls(maze);
        generate(maze);
        addStartPoint(maze);
        addFinishPoint(maze);
        return maze;
    }

    private void fillWalls(int[][] maze){
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                maze[i][j]=WALL;
            }
        }
    }
    private void generate(int[][] maze){
        int column = POINT_X_START; // Точка приземления крота и счетчик
        int field = POINT_Y_START;
        int a=0;
        while (a<ITERATIONS){
            a++;
            maze[field][column]=PASS;
            while (true){
                int rand = (int)((Math.random()*100)%4);

                switch (rand){
                    case 0:
                        if (field!=1){
                            if (maze[field-2][column]==WALL){ //Вверх
                                maze[field-1][column] = PASS;
                                maze[field-2][column] = PASS;
                                field-=2;
                            }
                        }
                        break;
                    case 1:
                        if (field!=height-2){
                            if (maze[field+2][column]==WALL){ //Вниз
                                maze[field+1][column] = PASS;
                                maze[field+2][column] = PASS;
                                field+=2;
                            }
                        }
                        break;
                    case 2:
                        if(column != 1){
                            if(maze[field][column-2] == WALL){ // Налево
                                maze[field][column-1] = PASS;
                                maze[field][column-2] = PASS;
                                column-=2;
                            }
                        }
                        break;
                    case 3:
                        if(column != width-2){
                            if(maze[field][column+2] == WALL){ // Налево
                                maze[field][column+1] = PASS;
                                maze[field][column+2] = PASS;
                                column+=2;
                            }
                        }
                        break;

                }

                if(deadEnd(column,field,maze,height,width)) {
                    break;
                }


            }
            if(deadEnd(column,field,maze,height,width)) // Вытаскиваем крота из тупика
                do{
                    column = (2*((int)(Math.random()*100)%((width-1)/2))+1);
                    field = (int)(2*(int)((Math.random()*100)%((height-1)/2))+1);
                }
                while(maze[field][column] != PASS);
        }
    }
    private boolean deadEnd(int column, int field,int[][] maze, int height, int width){

        int counter=0;

        if (column!=1){
            if (maze[field][column-2]==PASS){
                counter++;
            }
        }else {
            counter++;
        }

        if (field!=1){
            if (maze[field-2][column]==PASS){
                counter++;
            }
        }else {
            counter++;
        }
        if(column != width-2){
            if(maze[field][column+2] == PASS)
                counter+=1;
        }
        else counter+=1;

        if(field != height-2){
            if(maze[field+2][column] == PASS)
                counter+=1;
        }
        else counter+=1;

        if(counter == 4){
            return true;
        }
        return false;
    }
    private void addStartPoint(int[][] maze){
        maze[POINT_Y_START][POINT_X_START]=4;
    }
    private void addFinishPoint(int[][] maze){
        maze[height-2][width-2]=5;
    }
}
