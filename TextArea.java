import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Rectangle;

public class TextArea extends JTextArea{

    private DefaultCaret defaultCaret;
    private Panel p;
    private Display display;

    private static int updown;
    private static int side;
    private static int caret;
    private static boolean isKey;
    private static boolean isText;
    private static boolean isFrist;
    
    private int count;
    private int textCount;

    public TextArea(Display d,Panel p){
        this.display = d;
        this.p = p;
        updown = 0;
        side = 0;
        count = 0;
        isKey = false;
        isText = false;
        isFrist = true;
        setOpaque(false);
        setForeground(Color.WHITE);
        setCaretColor(Color.WHITE);
        setTabSize(4);
        setFont(new Font("ＭＳ ゴシック",Font.PLAIN,25));
        setMargin( new Insets(20,55,20,0) );
        setSize(d.getSize().width,d.getSize().height);
    }

    @Override
    public void updateUI() {
        super.updateUI();
        defaultCaret = new DefaultCaret(){
            @Override
            public boolean isVisible() {
                return false;
            }

            @Override protected synchronized void damage(Rectangle r) {
                if(r != null){
                    JTextComponent c = getComponent();
                    x = r.x;
                    y = r.y;
                    width = c.getSize().width;
                    height = r.height;
                    c.repaint();
                }
            };
        };
        setCaret(defaultCaret);
        setCaretPosition(0);
        caret = getCaretPosition();
    }

    @Override public void paintComponent(Graphics g){
        super.paintComponent(g);

        int x = defaultCaret.x;
        int y = defaultCaret.y + defaultCaret.height - 1;

        g.setColor(Color.YELLOW);
        g.drawLine(0,y,this.getSize().width,y);
        g.drawLine(x,0,x,this.getSize().height);
        g.dispose();
        p.numberView();
        if(!isKey){
            caret = this.getCaretPosition();
        }

        switch(updown){
            case 1:
                setCaretPosition(0);
                updown = 0;
                break;
            case -1:
                setCaretPosition(this.getText().length());
                updown = 0;
                break;
        }
        
        switch(side){
            case 1:
                if(0 == this.getCaretPosition()){
                }else{
                    setCaretStartPosition(1);
                }

                side = 0;
                isKey = false;
                break;
            case -1:
                if(this.getText().length() == this.getCaretPosition()){
                }else{
                    setCaretStartPosition(-1);
                }

                side = 0;
                isKey = false;
                break;
        }
        if(isText){
            textCount = this.getText().length();
            display.setTitle(Status.getStatus());
            isText = false;
        }
        if(textCount < this.getText().length() || textCount > this.getText().length()){
            display.setTitle(Status.getStatus() + "(変更あり)");
        }
        
        //if(this.getText().contains(JavaLibrary.getWords()[0])){
        	//System.out.println("hello");
        //}
    }

    public void setCaretStartPosition(int n){
        String s = this.getText();
        String strs[] = s.split("\n");
        if(n == 1){
            lavel:for(int i = 0;i < strs.length;i ++){
                for(int j = 0;j <= strs[i].length();j ++){
                    count ++;
                    if(count == caret){
                        setCaretPosition(count - j - 1);
                        isFrist = true;
                        break lavel;
                    }
                }
            }
        }
        if(n == -1){
            int caret_p = 0;
            lavel:for(int i = 0;i < strs.length;i ++){
                caret_p += strs[i].length();
                for(int j = 0;j < strs[i].length();j ++){
                    count ++;
                    if(i == 0 && j == 0 && strs[i].length() >= caret){
                        if(isFrist){
                            if(caret == caret_p){
                                setCaretPosition(caret + 1);
                                isFrist = true;
                            }else{
                                setCaretPosition(caret_p);
                                isFrist = false;
                            }

                        }
                        break lavel;
                    }

                    if(count == caret - i + 1){
                        setCaretPosition(caret_p + i);

                        break lavel;
                    }
                }
            }
        }
        count = 0;
    }

    public static void setCaretUp(){
        updown = 1;
    }

    public static void setCaretDown(){
        updown = -1;
    }

    public static void setCaretLeft(){
        side = 1;
    }

    public static void setCaretRight(){
        side = -1;
    }

    public static void setIsKey(boolean b){
        isKey = b;
    }

    public static void setIsText(boolean b){
        isText = b;
    }

    public static void setIsFirst(boolean b){
        isFrist = true;
    }

    public DefaultCaret getDefaultCaret(){
        return defaultCaret;
    }
}
