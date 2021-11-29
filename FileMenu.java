import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FileMenu extends JMenu implements ActionListener{

	private JMenuItem fileMenu[] = new JMenuItem[3];
	private String file[] = { "新規作成","開く","閉じる" };
	private Display d;

	public FileMenu(Display d){
		super("ファイル");
		this.d = d;
		for(int i = 0;i < file.length;i ++){
			fileMenu[i] = new JMenuItem(file[i]);
			add(fileMenu[i]);
			fileMenu[i].addActionListener(this);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
	
		for(int i = 0;i < file.length;i ++){
			if(fileMenu[i] == e.getSource()){

				if(i == 0){
					//System.out.println(Status.getStatus());
				}
				if(i == 1){
					if(FileAccess.open(this.d,null)){
						TextArea.setIsText(true); return;
					}
				}
				if(i == 2){
					if(FileAccess.save(this.d)){
						System.exit(0);
					}
				};
				
			}
		}
	}
	
	public JMenuItem[] getJmi(){
		return this.fileMenu;
	}
	
	public String[] getStr(){
		return this.file;
	}
}
