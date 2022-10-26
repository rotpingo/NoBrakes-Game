
public class Auto {

    static final private int autoWidth = 100;
    static final private int autoHeight = 180;

    private int startXPos;
    private int startYPos;

    private int autoSpeed;

    /* ----------------- Constructors ------------------- */

    public Auto(int x, int y, int s){

        startXPos = x;
        startYPos = y;
        autoSpeed = s;

    }

    /* -------------------- METHODS ------------------- */


        public int getXPos() {

        return startXPos;
    }

    public void setXPos(int xPos){

        startXPos = xPos;
    }

    public int getYPos() {

        return startYPos;
    }

    public void setYPos(int yPos){

        startYPos = yPos;
    }

    public void setAutoSpeed(int speed){

        autoSpeed = speed;
    }

    public int getAutoSpeed(){

        return autoSpeed;
    }

    public int getAutoWidth(){

        return autoWidth;
    }

    public int getAutoHeight(){

        return autoHeight;
    }

    public int moveRight(){

        startYPos += autoSpeed;

        return startYPos;
    }
}


