package VirtualWorld.frames;

import VirtualWorld.EventLog;
import VirtualWorld.World;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Bartosz Łuczak on 23.04.2017.
 */
public class LogFrame extends JEditorPane {
    private World world;
    private EventLog log;

    public LogFrame(World w){
        super();
        this.world=w;
        this.log=w.getEvents();
        hello();
        setDisabledTextColor(Color.black);
        setEnabled(false);
    }

    public void refreshLog() {
        setText("Turn: "+world.getTurn());
        setText(getText()+"\n"+"How many organisms: "+world.getHowManyOrganisms()+"\n");
        world.getEvents().readLog(this);;
        this.repaint();
        world.getEvents().cleanLog();

    }
    public void logOneEvent() {
        setText("Turn: "+world.getTurn());
        setText(getText()+"\n"+"How many organisms: "+world.getHowManyOrganisms()+"\n");
        setText(getText()+log.getLog().get(log.getLog().size()-1));
        this.repaint();
        log.eraseLastEvent();


    }

    public void hello(){
        setText("Welcome to Virtual World!\nENTER - next turn\nClick on an empty field on board to add an organism");
    }

}
