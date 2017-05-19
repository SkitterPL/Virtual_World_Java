package VirtualWorld.frames;

import VirtualWorld.World;
import VirtualWorld.buttons.QuitButton;
import VirtualWorld.worlds.HexWorld;
import VirtualWorld.worlds.SquareWorld;
import javax.swing.*;

/**
 * Created by Bartosz Łuczak on 08.05.2017.
 */
public class StartFrame extends JFrame{
    private World world;
    public StartFrame(){
        super("Virtual VirtualWorld.World by Bartosz Łuczak");
        this.setFocusable(true);
        this.requestFocusInWindow();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(640,300);
        setSize(300,200);
        setLayout(null);
        setVisible(true);

        JLabel info = new JLabel("Set width of the world: ");
        JLabel infoH = new JLabel("Set height of the world: ");
        JTextField height = new JTextField();
        JTextField width = new JTextField();
        JRadioButton square = new JRadioButton("Square World");
        JRadioButton hex = new JRadioButton("Hex World");
        JButton start = new JButton("Launch World!");
        QuitButton quit = new QuitButton();
        ButtonGroup worlds = new ButtonGroup();

        worlds.add(square);
        worlds.add(hex);
        square.addActionListener(e -> {
            try {
                world = new SquareWorld(Integer.valueOf(width.getText()), Integer.valueOf(height.getText()));
            }
            catch(NumberFormatException ignored) {
            }
        });
        hex.addActionListener(e -> {
            try {
                world = new HexWorld(Integer.valueOf(width.getText()), Integer.valueOf(height.getText()));
            }
            catch(NumberFormatException ignored){
            }
        });
        start.addActionListener(e -> {
            MainFrame game = new MainFrame(world);
            dispose();

        });




        width.setBounds(200,20,50,20);
        info.setBounds(50,20,150,20);
        infoH.setBounds(50,50,150,20);
        height.setBounds(200,50,50,20);
        square.setBounds(50,80,120,20);
        hex.setBounds(180,80,120,20);
        quit.setBounds(200 ,120,70,20);
        start.setBounds(50,120,120,20);


        add(width);
        add(height);
        add(info);
        add(infoH);
        add(square);
        add(hex);
        add(quit);
        add(start);
    }

}
