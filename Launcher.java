public class Launcher{
	public static void main(String[] args) {
		if(args.length == 1){
			new Thread( new Memo( args[0]) ).start();
		}else{
			new Memo();
		}
		
	}
}