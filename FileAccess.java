import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

public class FileAccess {
    public static boolean open(Display d,String path){

		if(path != null){
			File f = new File(path);
			try{
				BufferedReader br = new BufferedReader( new InputStreamReader (new FileInputStream(f),"UTF-8") );
				//String s;
				int count = 0;
				String text = "";
				while((count = br.read()) != -1){
					text += (char) count;
				}
				//while((s = br.readLine()) != null ){
				//	text += s + '\n';
				//	//System.out.println("text=" + text);
		        //}
				String after = text;
				//if(after.substring(after.length() - 1).equals("\n")){
				//	after = after.substring(0,after.length() - 1);
				//}
				d.getPanel().getTextArea().setText(after);
			}catch(IOException e){
				return true;
			}

			Status.setStatus(path);
			return true;
		}

        JFileChooser jfc = new JFileChooser();
		int select = jfc.showOpenDialog(null);
	    if(select == JFileChooser.APPROVE_OPTION){
		    File file = jfc.getSelectedFile();
			Status.setStatus(file.getAbsolutePath());
		    d.setTitle(file.getName());
            d.getPanel().getTextArea().setText("");
		    try{
		        BufferedReader br = new BufferedReader( new InputStreamReader (new FileInputStream(file),"UTF-8") );
		        int count = 0;
				String text = "";
				while((count = br.read()) != -1){
					text += (char) count;
				}
		        
				String after = text;
				//if(after.substring(after.length() - 1).equals("\n")){
				//	after = after.substring(0,after.length() - 1);
				//}
				d.getPanel().getTextArea().setText(after);
		    }catch(IOException io){
			    io.printStackTrace();
                return false;
		    }
			
		}

        return true;
    }

    public static boolean save(Display d){

		if(Status.getStatus() == ""){
			if(Memo.getDefaultFileName() != ""){
				File f = new File(Status.getStatus());

				try{
					//PrintWriter pw = new PrintWriter( new FileWriter(f,false) );
					PrintWriter pw = new PrintWriter( new BufferedWriter( new OutputStreamWriter( new FileOutputStream( f ) ,"UTF-8") ) );

					String s = d.getPanel().getTextArea().getText();
					String after = s;
					pw.print(after);

					pw.close();
				}catch(IOException e){
					return false;
				}
				Memo.setDefaultFileName("");
				return true;
			}
			JFileChooser jfc = new JFileChooser();
			jfc.showSaveDialog(null);
			File f = jfc.getSelectedFile();

			try{
				//PrintWriter pw = new PrintWriter( new FileWriter(f,false) );
				PrintWriter pw = new PrintWriter( new BufferedWriter( new OutputStreamWriter( new FileOutputStream( f ) ,"UTF-8") ) );

				String s = d.getPanel().getTextArea().getText();
				String after = s;
				pw.print(after);
				pw.close();
			}catch(IOException e){
				return false;
			}
			Status.setStatus(f.getAbsolutePath());
			d.setTitle(f.getName());

			return true;
		}else{
			File f = new File(Status.getStatus());

			try{
				//PrintWriter pw = new PrintWriter( new FileWriter(f,false) );
				PrintWriter pw = new PrintWriter( new BufferedWriter( new OutputStreamWriter( new FileOutputStream( f ) ,"UTF-8") ) );

				String s = d.getPanel().getTextArea().getText();
				String after = s;
				pw.print(after);

				pw.close();
			}catch(IOException e){
				return false;
			}
        	return true;
		}

    }
}