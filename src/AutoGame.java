import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class AutoGame extends JPanel implements ActionListener {

    private int imgXPos = 0;
    private int imgYPos = 0;

    // Auto image dimensions
    private Image playerAutoImage;
    private int playerAutoWidth = 100;
    private int playerAutoHeight = 180;

    private int speed = 10;
    private int playerXPos, playerYPos;

    Timer timer = new Timer(25, this);

    private boolean isRightPressed, isLeftPressed;

    JButton exit = new JButton();

    Auto auto1 = new Auto(480, -200, 10);
    Auto auto2 = new Auto(330, -350, 5);
    Auto auto3 = new Auto(30, -600, 20);
    Auto auto4 = new Auto(170, -400, 25);
    Auto[] autoR = {auto1, auto2};
    Auto[] autoL = {auto3, auto4};

    Image autoImage1 = ImageIO.read(new File("img/autoRight1.png"));
    Image autoImage2 = ImageIO.read(new File("img/autoRight2.png"));
    Image autoImage3 = ImageIO.read(new File("img/autoRight3.png"));
    Image autoImage4 = ImageIO.read(new File("img/autoRight4.png"));
    Image autoImage5 = ImageIO.read(new File("img/autoLeft1.png"));
    Image autoImage6 = ImageIO.read(new File("img/autoLeft2.png"));
    Image autoImage7 = ImageIO.read(new File("img/autoLeft3.png"));
    Image autoImage8 = ImageIO.read(new File("img/autoLeft4.png"));


    private Image img = Toolkit.getDefaultToolkit().getImage("img/road.png");

    private Image[] imageR = {autoImage1, autoImage2, autoImage3, autoImage4};
    private Image[] imageL = {autoImage5, autoImage6, autoImage7, autoImage8};

    private Random randImage;
    private Random randAuto;
    private Random rand;

    private int k;
    private int n;

    /* ---------------------- CONSTRUCTORS ------------------------- */

    public AutoGame(Image playerAuto, int x, int y) throws IOException {

        playerAutoImage = playerAuto;
        playerXPos = x;
        playerYPos = y;

        randImage = new Random();
        randAuto = new Random();


        n = randImage.nextInt(4);
        k = randAuto.nextInt(2);

        setFocusable(true);
        requestFocusInWindow();

        drive();
        timer.start();

    }


    /* ------------------------------- METHODS ------------------------------- */

    public void drive() {

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                int action = e.getKeyCode();

                switch (action) {
                    case KeyEvent.VK_LEFT:
                        isLeftPressed = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        isRightPressed = true;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);

                int action = e.getKeyCode();

                switch (action) {
                    case KeyEvent.VK_LEFT:
                        isLeftPressed = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        isRightPressed = false;
                        break;
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, imgXPos, imgYPos, null);
        g.drawImage(img, imgXPos,imgYPos - 1000, null);

        g.drawImage(playerAutoImage, playerXPos, playerYPos, playerAutoWidth, playerAutoHeight, null);

        g.drawImage(imageR[1], autoR[0].getXPos(), autoR[0].moveRight(), autoR[0].getAutoWidth(), autoR[0].getAutoHeight(), null);
        g.drawImage(imageR[2], autoR[1].getXPos(), autoR[1].moveRight(), autoR[1].getAutoWidth(), autoR[1].getAutoHeight(), null);
        g.drawImage(imageL[3], autoL[0].getXPos(), autoL[0].moveRight(), autoL[0].getAutoWidth(), autoL[0].getAutoHeight(), null);
        g.drawImage(imageL[0], autoL[1].getXPos(), autoL[1].moveRight(), autoL[1].getAutoWidth(), autoL[1].getAutoHeight(), null);


        if(autoR[1].getYPos() >= 1500) {
            autoR[1].setYPos(-900);

        }
        if(autoL[1].getYPos() >= 1500){
            autoL[1].setYPos(-900);

        }
        if(autoR[0].getYPos() >= 1500) {
            autoR[0].setYPos(-1000);

        }
        if(autoL[0].getYPos() >= 1500){
            autoL[0].setYPos(-900);

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        timeUpdate();
    }

    private void timeUpdate() {

        imgYPos += 30;

        if (isLeftPressed) {
            playerXPos -= speed;

        }
        if (isRightPressed) {
            playerXPos += speed;

        }


        // Background repeat
        imgYPos = Math.floorMod(imgYPos, getHeight());

        // Road Border
        if(playerXPos <= 0){
            playerXPos = 0;
        }

        if(playerXPos >= 500){
            playerXPos = 500;
        }

        if (collisionR_0()) {

            String infoMessage = "Game Over";
            JOptionPane.showMessageDialog(exit, infoMessage, "Alert", JOptionPane.INFORMATION_MESSAGE);

            System.exit(0);
        }


        if (collisionL_0()) {

            String infoMessage = "Game Over";
            JOptionPane.showMessageDialog(exit, infoMessage, "Alert", JOptionPane.INFORMATION_MESSAGE);

            System.exit(0);
        }

        if (collisionR_1()) {

            String infoMessage = "Game Over";
            JOptionPane.showMessageDialog(exit, infoMessage, "Alert", JOptionPane.INFORMATION_MESSAGE);

            System.exit(0);
        }


        if (collisionL_1()) {

            String infoMessage = "Game Over";
            JOptionPane.showMessageDialog(exit, infoMessage, "Alert", JOptionPane.INFORMATION_MESSAGE);

            System.exit(0);
        }

        repaint();
    }

    private boolean collisionR_0() {
        return playerXPos < autoR[0].getXPos() + autoR[0].getAutoWidth() &&
               playerYPos < autoR[0].getYPos() + autoR[0].getAutoHeight() &&
               autoR[0].getXPos() < playerXPos + playerAutoWidth &&
               autoR[0].getYPos() < playerYPos + playerAutoHeight;

    }

    private boolean collisionL_0() {
        return playerXPos < autoL[0].getXPos() + autoL[0].getAutoWidth() &&
               playerYPos < autoL[0].getYPos() + autoL[0].getAutoHeight() &&
               autoL[0].getXPos() < playerXPos + playerAutoWidth &&
               autoL[0].getYPos() < playerYPos + playerAutoHeight;

    }

    private boolean collisionR_1() {
        return playerXPos < autoR[1].getXPos() + autoR[1].getAutoWidth() &&
                playerYPos < autoR[1].getYPos() + autoR[1].getAutoHeight() &&
                autoR[1].getXPos() < playerXPos + playerAutoWidth &&
                autoR[1].getYPos() < playerYPos + playerAutoHeight;

    }

    private boolean collisionL_1() {
        return playerXPos < autoL[1].getXPos() + autoL[1].getAutoWidth() &&
                playerYPos < autoL[1].getYPos() + autoL[1].getAutoHeight() &&
                autoL[1].getXPos() < playerXPos + playerAutoWidth &&
                autoL[1].getYPos() < playerYPos + playerAutoHeight;

    }
}

