import javax.imageio.ImageIO;
import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StartWindow extends JFrame implements ActionListener {

    private final JButton startGame;
    private final JButton startMusic;
    private final JButton exitGame;

    private Sequencer sequencer;
    private boolean switchMusic;

    public StartWindow() {

        super("No Brakes");
        int width = 600;
        int height = 400;
        setSize(width, height);
        JPanel boxPanel = new JPanel();

        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.PAGE_AXIS));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width- width)/2,(screenSize.height-height)/2);

        startGame = new JButton("START GAME");
        startGame.setMaximumSize(new Dimension(400, 40));
        startGame.setBackground(Color.CYAN);

        startMusic = new JButton("MUSIC ON / OFF");
        startMusic.setMaximumSize(new Dimension(400, 40));
        startMusic.setBackground(Color.PINK);

        exitGame = new JButton("EXIT");
        exitGame.setMaximumSize(new Dimension(400, 40));
        exitGame.setBackground(Color.ORANGE);

        // 50px from the left Side of the Window
        boxPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        // 50px from the top Side of the Window
        boxPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        boxPanel.add(startGame);
        // 50px between the Buttons
        boxPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        boxPanel.add(startMusic);
        // 50px between the Buttons
        boxPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        boxPanel.add(exitGame);

        add(boxPanel);

        //setBackground(Color.BLACK);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        startGame.addActionListener(this);
        startMusic.addActionListener(this);
        exitGame.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == startGame){

            startGame.setBackground(Color.GREEN);
            try {
                new RoadWindow(ImageIO.read(new File("img/player.png")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        else if(e.getSource() == startMusic){

            startMusic.setBackground(Color.GREEN);

            if(!switchMusic){
                startMusic.setText("STOP MUSIC");
                switchMusic = true;

                try {
                    startTheMusicNow();
                } catch (MidiUnavailableException | InvalidMidiDataException | IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                startMusic.setText("START MUSIC");
                startMusic.setBackground(Color.PINK);
                switchMusic = false;

                sequencer.stop();
            }
        }

        else if(e.getSource() == exitGame){
            exitGame.setBackground(Color.RED);
            System.exit(0);
        }
    }

    private void startTheMusicNow() throws MidiUnavailableException, InvalidMidiDataException, IOException {

        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.loadAllInstruments(synth.getDefaultSoundbank());

        sequencer = MidiSystem.getSequencer();

        Sequence sequence = MidiSystem.getSequence(new File("midi/ManWhoSoldTheWorld.mid"));

        sequencer.open();
        sequencer.setSequence(sequence);
        sequencer.setTempoFactor(1f);

        sequencer.start();
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(StartWindow::new);
    }
}
