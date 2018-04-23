package com.dotnet.imageSource;

import javax.swing.*;
import java.awt.*;

public class ImageSource {
    private static Image ballImg= new ImageIcon("res/dot.png").getImage();
    private static Image p1_body= new ImageIcon("res/body1_new.png").getImage();
    private static Image rabbitImg = new ImageIcon("res/Rabbit_1.png").getImage();
    private static Image ppiImg= new ImageIcon("res/ppi_1.png").getImage();
    private static Image headImg= new ImageIcon("res/head1_new.png").getImage();
    private static Image background= new ImageIcon("res/background2.jpg").getImage();



    public static Image getBallImg() {
        return ballImg;
    }

    public static Image getRabbitImg() {
        return rabbitImg;
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

    public static Image getPpiImg() {
        return ppiImg;
    }
}
