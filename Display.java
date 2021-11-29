import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;

import java.awt.*;

public class Display extends JFrame{

	private JMenuBar jmb;
	private FileMenu fm;
	private EditMenu em;
	private Container c;
	private JScrollPane jsp;

	private Panel p;

	public Display(){
		super();
		if(Memo.getDefaultFileName() == ""){
			setTitle("No-Title");
		}else{
			setTitle(Status.getStatus());
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,500);
		
		c = getContentPane();

		jmb = new JMenuBar();
		fm = new FileMenu(this);
		em = new EditMenu(this);
		p = new Panel(this);
		jsp = new JScrollPane(p);
		jsp.getVerticalScrollBar().setUnitIncrement(50);
		jsp.getHorizontalScrollBar().setUnitIncrement(50);
		
		jmb.add(fm);
		jmb.add(em);
		c.add(jmb,BorderLayout.NORTH);
		add(jsp);

		Keyboard k = new Keyboard(this);
		p.getTextArea().addKeyListener(k);
	}
	
	public JScrollPane getJScrollPane(){
		return this.jsp;
	}

	public Panel getPanel(){
		return this.p;
	}
}
