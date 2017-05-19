package VirtualWorld.frames;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Bartosz Łuczak on 23.04.2017.
 */
public class CreateButton extends JButton {
        public CreateButton(String text, Color background, Color foreground) {
            super(text);
            this.setBackground(background);
            this.setForeground(foreground);
            this.setFocusable(false);
            this.setEnabled(false);
        }
}
