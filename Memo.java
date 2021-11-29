import java.lang.Thread;

public class Memo extends Thread{
	
	private Display d;

	private String str;
	private String s;

	private static String argsString = "";


	public Memo(){
		d = new Display();
		d.setVisible(true);
	}

	public Memo(String args){
		if(args.contains("C:")){
			str = args;
			Status.setStatus(str);
		}else{
			argsString = args;
			s = System.getProperty("user.dir");
			str = s + "/" + argsString;
			Status.setStatus(str);
		}
		d = new Display();
		d.setVisible(true);
	}

	@Override
	public void run() {
		super.run();
		try{
			Thread.sleep(100);
			if(FileAccess.open(this.d, str)){
				TextArea.setIsText(true);
			}
		}catch(Exception e){}

	}

	public static String getDefaultFileName(){
		return argsString;
	}

	public static void setDefaultFileName(String s){
		argsString = s;
	}
}