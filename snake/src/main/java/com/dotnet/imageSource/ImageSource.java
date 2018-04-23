package com.dotnet.imageSource;

import javax.swing.*;
import java.awt.*;

public class ImageSource {
    private static Image ballImg= new ImageIcon("res/dot.png").getImage();
    private static Image p1_body= new ImageIcon("res/P2_body.png").getImage();
    private static Image appleImg= new ImageIcon("res/Rabbit.png").getImage();
    private static Image headImg= new ImageIcon("res/P2_h.png").getImage();
    private static Image background= new ImageIcon("res/background2.jpg").getImage();



    public static Image getBallImg() {
        return ballImg;
    }

    public static Image getAppleImg() {
        return appleImg;
    }

    public static Image getHeadImg() {
        return headImg;
    }

    public static Image getBackground() {
        return background;
    }

    public static Image getP1_body() {
        return p1_body;
    }
}
