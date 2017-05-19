package VirtualWorld.buttons;

import VirtualWorld.World;
import VirtualWorld.frames.LogFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * Created by Bartosz Łuczak on 07.05.2017.
 */
public class SaveButton extends JButton {
    private World world;
    private LogFrame log;
    public SaveButton(World w, LogFrame l){
        super("Save world");
        this.world=w;
        this.log=l;
        addActionListener(e -> {
            try {
                world.saveWorld();
                world.getEvents().addEvent("\nWorld has been saved!");
                log.refreshLog();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });
    }
}
