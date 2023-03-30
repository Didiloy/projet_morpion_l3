package grille;

import common.enums.StateEnum;

public class Coordinates {
    public int x;
    public int y;

    public StateEnum Sign;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public  StateEnum getSign(){ return this.Sign; }

    public void setSign(StateEnum s){ this.Sign = s;}

    public Coordinates(int x, int y, StateEnum sign){
        this.x = x;
        this.y = y;
        this.Sign = sign;
    }


}
