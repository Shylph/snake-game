package com.dotnet.character;

import com.dotnet.Position;

public class CollisionArea {
    private final Position position;
    private Position centralAxis;
    private Position boundaries[];

    public CollisionArea(Position centralAxis, Position[] boundaries) {
        position = new Position();
        this.centralAxis = centralAxis;
        this.boundaries = boundaries;
    }

    public CollisionArea(Position position, Position centralAxis, Position[] boundaries) {
        this.position = position;
        this.centralAxis = centralAxis;
        this.boundaries = boundaries;
    }

    public boolean checkCollision(CollisionArea targetArea) {

        Position diff = position.diff(targetArea.position);
        if (diff.lengthFromZero() > 85) {
            return false;
        } else {
            Position[] boundaryPositions = targetArea.getBoundaryPositions();
            for (Position p : boundaryPositions) {
                if (isInside(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Position[] getBoundaryPositions() {
        Position tempBound[] = new Position[boundaries.length];
        for (int i=0;i<tempBound.length;i++) {
            tempBound[i] = new Position(boundaries[i]);
            tempBound[i].add(position);
        }
        return tempBound;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position.setPosition(position);
    }

    private boolean isInside(Position p) {
        Position[] tempBound = getBoundaryPositions();
        int crosses = 0;
        for (int i = 0; i < tempBound.length; i++) {
            int j = (i + 1) % tempBound.length;
            //점 p가 선분 (p[i], p[j])의 y좌표 사이에 있음
            if ((tempBound[i].getY() > p.getY()) != (tempBound[j].getY() > p.getY())) {
                //atX는 점 B를 지나는 수평선과 선분 (p[i], p[j])의 교점
                double atX = (tempBound[j].getX() - tempBound[i].getX()) * (p.getY() - tempBound[i].getY()) / (tempBound[j].getY() - tempBound[i].getY()) + tempBound[i].getX();
                //atX가 오른쪽 반직선과의 교점이 맞으면 교점의 개수를 증가시킨다.
                if (p.getX() < atX)
                    crosses++;
            }
        }
        return crosses % 2 > 0;
    }

    public Position getDrawPosition(){
        return position.diff(centralAxis);
    }

    public Position getCentralAxis() {
        return centralAxis;
    }
}
