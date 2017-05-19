package VirtualWorld;

import VirtualWorld.frames.LogFrame;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Bartosz Łuczak on 23.04.2017.
 */
public class EventLog {
    private ArrayList<String> log;
    private int howManyEvents;

    public EventLog(){
        this.log=new ArrayList<String>();
        this.howManyEvents=0;
    }
    public void addEvent(String event){
        log.add(event);
        this.howManyEvents++;
    }

    public void setHowManyEvents(int howmany){
        this.howManyEvents=howmany;
    }
    public int getHowManyEvents(){
        return this.howManyEvents;
    }
    public void readLog(LogFrame frame){
        for(int i=0;i<getHowManyEvents();i++)
        {
            frame.setText(frame.getText()+log.get(i)+"\n");
        }
    }
    public ArrayList<String> getLog(){
        return this.log;
    }
    public void cleanLog(){
        log.clear();
        setHowManyEvents(0);
    }
    public void eraseLastEvent(){
        getLog().remove(getLog().size()-1);
        setHowManyEvents(getHowManyEvents()-1);
    }
}
