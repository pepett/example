import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditMenu extends JMenu implements ActionListener{
	private JMenuItem editMenu[] = new JMenuItem[4];
	private String edit[] = { "すべてを選択","コピー","ペースト","切り取り" };
	private Display d;

	public EditMenu(Display d){
		super("編集");
		this.d = d;
		
		for(int i = 0;i < edit.length;i ++){
			editMenu[ i ] = new JMenuItem(edit[ i ]);
			add(editMenu[ i ]);
			editMenu[ i ].addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		for(int i = 0;i < edit.length;i ++){
			if(editMenu[ i ] == e.getSource()){
				if(i == 0){
					this.d.getPanel().getTextArea().selectAll();
				}
				if(i == 1){
					this.d.getPanel().getTextArea().copy();
				}
				if(i == 2){
					this.d.getPanel().getTextArea().paste();
				}
				if(i == 3){
					this.d.getPanel().getTextArea().cut();
				}
			}
		}
	}

}