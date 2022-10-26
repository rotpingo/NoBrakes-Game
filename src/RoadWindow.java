import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RoadWindow extends JFrame {

    private int width = 600, height = 1000;

    public int getWidth(){

        return width;
    }

    public RoadWindow(Image playerAuto) throws IOException {
        super("No Breaks");

        setSize(width,height);
        setLocationRelativeTo(null);

        // get Monitor width and height
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // set the created Window in the middle of the Monitor
        setLocation((screenSize.width-width)/2,(screenSize.height-height)/2);
        setResizable(false);

        // Close just this Window on exit
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        add(new AutoGame(playerAuto, 480, 770));

    }

}
