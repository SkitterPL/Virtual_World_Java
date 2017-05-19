package VirtualWorld.buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Bartosz Łuczak on 07.05.2017.
 */
public class QuitButton extends JButton {
    public QuitButton(){
        super("Quit");
        addActionListener(e -> System.exit(1));
    }
}
