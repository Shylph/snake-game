package com.dotnet.character;

import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import java.util.List;

public class AiSnake extends Snake {

    private boolean firstFlag;
    private Session sess;
    private SavedModelBundle b;
    private float[][][][] inputData;

    public AiSnake(String name, String headPath, String bodyPath) {
        super(name, headPath, bodyPath);
        String modelPath = "./model/simple";
        b = SavedModelBundle.load(modelPath, "test");
        inputData = new float[1][80][45][4];
        firstFlag = true;
    }

    private int[] getAction() {
        int index = 0;
        switch (getDirection()) {
            case LEFT:
                index = 0;
                break;
            case RIGHT:
                index = 1;
                break;
            case UP:
                index = 2;
                break;
            case DOWN:
                index = 3;
                break;
        }
        int[] result = new int[4];
        result[index] = 1;
        return result;
    }

    public void saveRawData(Integer[][] rawData) {
        if(firstFlag){
            firstFlag=false;
            for (int i = 0; i < rawData.length; i++) {
                for (int j = 0; j < rawData[i].length; j++) {
                    inputData[0][i][j][0] = rawData[i][j];
                    inputData[0][i][j][1] = rawData[i][j];
                    inputData[0][i][j][2] = rawData[i][j];
                    inputData[0][i][j][3] = rawData[i][j];
                }
            }
        }else{
            for (int i = 0; i < rawData.length; i++) {
                for (int j = 0; j < rawData[i].length; j++) {
                    inputData[0][i][j][3] = inputData[0][i][j][2];
                    inputData[0][i][j][2] = inputData[0][i][j][1];
                    inputData[0][i][j][1] = inputData[0][i][j][0];
                    inputData[0][i][j][0] = rawData[i][j];
                }
            }
        }
    }

    public void move() {
        sess = b.session();
        Tensor x = Tensor.create(inputData);
        Tensor action = Tensor.create(getAction());
//        Tensor action = Tensor.create(new int[4]);
        List<Tensor<?>> tensors = sess.runner()
                .feed("x", x)
                .feed("a", 0, action)
                .fetch("Q")
                .run();
        float[] prediction = tensors.get(0).copyTo(new float[4]);
        int[] result = oneHot(prediction);

        // left : 1 = [1,0,0,0],   right : 2 = [0,1,0,0],    up : 3 = [0,0,1,0],   down : 4 = [0,0,0,1],    none : 0
        if (result[0] == 1) {
            left();
        } else if (result[1] == 1) {
            right();
        } else if (result[2] == 1) {
            up();
        } else if (result[3] == 1) {
            down();
        }
        super.move();
    }

    private int[] oneHot(float[] floats) {
        float[] temp = floats.clone();
        int zeroCnt = 0;
        int zeroIndex = 0;
        int index = 0;

        float num = temp[0];
        for (int i = 0; i < temp.length; i++) {
            temp[i] -= num;
            if (temp[i] == 0) {
                zeroCnt++;
                zeroIndex = i;
            } else {
                index = i;
            }
        }
        int[] result = {0, 0, 0, 0};
        if (zeroCnt == 1) {
            result[zeroIndex] = 1;
        } else {
            result[index] = 1;
        }
        return result;
    }

    private int[] argmax(float[] in) {
        float max = -9999999999999f;
        int maxIndex = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] > max) {
                max = in[i];
                maxIndex = i;
            }
        }
        int[] result = {0, 0, 0, 0};
        result[maxIndex] = 1;
        return result;
    }
}
