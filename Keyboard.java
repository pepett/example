import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class Keyboard extends KeyAdapter{

    private Display d;
    
    public Keyboard(Display d){
        this.d = d;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //if((e.getModifiersEx() == 256 || e.getModifiersEx() == 128) && e.getKeyCode() == KeyEvent.VK_S){
        //    if(FileAccess.save(this.d));
        //}
        if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN){
            TextArea.setIsFirst(true);
        }
        if(e.getModifiersEx() == 256 || e.getModifiersEx() == 128){
            if(e.getKeyCode() == KeyEvent.VK_S){
                if(FileAccess.save(this.d)){
                    TextArea.setIsText(true);
                };
            }
            if(e.getKeyCode() == KeyEvent.VK_UP){
                TextArea.setCaretUp();
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                TextArea.setCaretDown();
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                TextArea.setIsKey(true);
                TextArea.setCaretLeft();
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                TextArea.setIsKey(true);
                TextArea.setCaretRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
    }
}
