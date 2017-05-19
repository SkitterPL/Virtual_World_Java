
/**
 * Created by Bartosz Łuczak on 21.04.2017.
 */

import VirtualWorld.frames.StartFrame;
import java.awt.EventQueue;


public class Game {
    public static void main(String[] args){
        EventQueue.invokeLater(() -> new StartFrame());
    }
}
