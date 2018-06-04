package com.dotnet.character;

import com.dotnet.Position;

public class SnakeHunter extends Unit implements Movable {
    private int cnt;
    private int term;

    public SnakeHunter() {
        super("snakeHunter", "res/ddang.png");

        Position boundary[] = {new Position(-13, -18), //ppi의 경계 일단 사용함, 새로 만들어야함
                new Position(-4, -18),
                new Position(4, -14),
                new Position(14, -24),
                new Position(17, -10),
                new Position(12, -6),
                new Position(16, 3),
                new Position(10, 12),
                new Position(-5, 17),
                new Position(-13, 11),
                new Position(-23, 10),
                new Position(-30, 1),
                new Position(-15, -5),
                new Position(-15, -14)};
        setCollisionArea(new CollisionArea(new Position(31, 28), boundary));
        cnt = 0;
        term = 9;
    }


    public Unit getDrawResource() {
        return this;
    }

    @Override
    public void move() {
        if (cnt > 50) {
            getPoint().left(term);
            cnt = 0;
        } else if (cnt > 45) {
            getPoint().down(term);
        } else if (cnt > 25) {
            getPoint().right(term);
        } else if (cnt > 20) {
            getPoint().up(term);

        }else {
            getPoint().left(term);
        }

        cnt++;
    }
}
