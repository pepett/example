import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.text.Document;
import javax.swing.text.Element;

public class Panel extends JPanel{

    private static TextArea ta;

    private int count;

    public Panel(Display d){
        count = 0;
        setSize(d.getSize().width,d.getSize().height);
        setBackground(Color.BLACK);
        setLayout( new BorderLayout() );
        ta = new TextArea(d,this);
        add(ta);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont( new Font("Dialog",Font.PLAIN,20));
        g.drawLine(ta.getInsets().left, 0, ta.getInsets().left, getTextArea().getSize().height);
        for(int i = 0;i < count + 1; i ++){
            if(i == 0) continue;
            g.drawString(Integer.toString(i), 1, i * ta.getDefaultCaret().height + 12);
        }
        
    }

    public void numberView(){
		Document d = ta.getDocument();
		Element r = d.getDefaultRootElement();
		count = r.getElementCount();
        repaint();
    }

    public TextArea getTextArea(){
        return ta;
    }
}