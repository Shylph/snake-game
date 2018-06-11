package com.dotnet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class ModeSelectLayer extends JPanel {
    private final JButton arcadeModeBtn;
    private final JButton fightModeBtn;
    private final Image background;

    ModeSelectLayer() {
        setFocusable(true);
        setLayout(null);

        background = new ImageIcon("res/초기화면.JPG").getImage();
        arcadeModeBtn = new JButton("아케이드 모드");
        arcadeModeBtn.setLocation(500, 300);
        arcadeModeBtn.setSize(200, 100);
        add(arcadeModeBtn);

        fightModeBtn = new JButton("대전 모드");
        fightModeBtn.setLocation(800, 300);
        fightModeBtn.setSize(200, 100);
        add(fightModeBtn);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background,0,0,1600,900,this);
        setOpaque(false);
        super.paint(g);
    }

    public void setArcadeModeListener(ActionListener arcadeModeListener) {
        arcadeModeBtn.addActionListener(arcadeModeListener);
    }

    public void setFightModeListener(ActionListener actionListener){
        fightModeBtn.addActionListener(actionListener);
    }

}
