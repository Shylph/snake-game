package com.dotnet.character;

import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import java.util.List;

public class AiSnake extends Snake {

    private Session sess;
    private SavedModelBundle b;

    public AiSnake(String name, String headPath, String bodyPath) {
        super(name, headPath, bodyPath);
        String modelPath = "./model/simple";
        b = SavedModelBundle.load(modelPath, "test");
    }

    @Override
    public void move() {
        float[][] inputData = new float[][]{{getPoint().getX(), getPoint().getY()}};
        Tensor x = Tensor.create(inputData);
        sess = b.session();
        List<Tensor<?>> tensors = sess.runner()
                .feed("x", x)
                .fetch("h")
                .run();
        float[][] prediction = tensors.get(0).copyTo(new float[1][4]);
        int[] result = argmax(prediction[0]);

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
