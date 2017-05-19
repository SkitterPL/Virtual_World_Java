package VirtualWorld.frames;

import VirtualWorld.animals.Human;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bartosz Łuczak on 08.05.2017.
 */
class MultiKeyListener implements KeyListener {
    MainFrame frame;
    // Set of currently pressed keys
    private final Set<Character> pressed = new HashSet<Character>();
    MultiKeyListener(MainFrame frame){
        this.frame=frame;
    }
    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyChar());
        Human temp =(Human)frame.getWorld().isAlive('H');
        if(pressed.size() > 1) {

            if(frame.getWorld().getSides()==6) {
                if (temp!=null) {
                    if (pressed.contains('z')) {
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            temp.setActionKey(5);
                            frame.turn();
                        }
                        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            temp.setActionKey(6);
                            frame.turn();
                        }
                    }
                }
            }
        }
        else{
            if(temp==null) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    frame.turn();
                }
            }
            else if(temp!=null){
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    temp.setActionKey(1);
                    frame.turn();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    temp.setActionKey(2);
                    frame.turn();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    temp.setActionKey(3);
                    frame.turn();
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    temp.setActionKey(4);
                    frame.turn();
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    temp.setActionKey(10);
                    frame.turn();
                }
            }
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyChar());
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
