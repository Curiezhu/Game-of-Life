package com.kejian.mike.opengles_example.gameOfLife;

import com.kejian.mike.opengles_example.MyRender;
import com.kejian.mike.opengles_example.shape.Shape;
import com.kejian.mike.opengles_example.shape.Square;

/**
 * Created by kisstheraik on 17/1/6.
 */

public class Controller {

    public MyRender myRender;
    public Shape[][] list;
    public int m = 28;
    public int n = 30;
    public int[][] life=new int[m][n];

    public float[] squareColor = {1f,1f,1f,1f};
    public float[] backColor = {0.5f,0.5f,1f,1f};


    public Controller(){
        myRender=new MyRender();
    }
    public void setCheckerboard(){

        list=new Shape[m][n];


        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                list[i][j]=new Square(0.03f,0.03f,squareColor);
            }
        }

        myRender.setShapeList(list);


    }

    private void setPic(){
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(life[i][j]==0){
                    list[i][j].setColorArray(squareColor);
                }else if(life[i][j]==1){
                    list[i][j].setColorArray(backColor);
                }
            }
        }
        myRender.setShapeList(list);
    }

    public boolean isAlive(int x,int y){

        return x<=m-1&&x>=0&&y<=n-1&&y>=0&&life[x][y]==1;

    }

    public void startLoop(){

        {
            //设置种子细胞
            for(int i=0;i<100;i++) {
                int x = (int) (Math.random() * 27) + 1;
                int y = (int) (Math.random() * 29) + 1;
                life[x][y]=1;

            }
            setPic();
        }

        while(true){

            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){

                    int count=0;
                    if(isAlive(i+1,j)){
                        count++;
                    }
                    if(isAlive(i-1,j)){
                        count++;
                    }
                    if(isAlive(i,j-1)){
                        count++;
                    }
                    if(isAlive(i,j+1)){
                        count++;
                    }
                    if(isAlive(i+1,j+1)){
                        count++;
                    }
                    if(isAlive(i+1,j-1)){
                        count++;
                    }
                    if(isAlive(i-1,j+1)){
                        count++;
                    }
                    if(isAlive(i-1,j-1)){
                        count++;
                    }

                    if(life[i][j]==1){
                        if(count<2){
                            life[i][j]=0;
                        }
                        if(count==2||count==3){
                            continue;
                        }
                        if(count>3){
                            life[i][j]=0;
                        }
                    }else if(life[i][j]==0){
                        if(count==3){
                            life[i][j]=1;
                        }
                    }


                }
            }

            try {
                Thread.sleep(200);
            }catch (Exception e){
                e.printStackTrace();
            }
            setPic();
        }

    }
}
