package test;

import java.io.*;  
import java.net.*;  
public class FirstServer {  
	
	public static void main(String[] args){  
		
		boolean cont = true;
		int x;
		int y;
		int z;
		String working;
		
		try{  
			ServerSocket ss=new ServerSocket(6665);  
			Socket s=ss.accept();//establishes connection   
			DataInputStream dis=new DataInputStream(s.getInputStream()); 
			while(cont) {
				String  str=(String)dis.readUTF();  
				System.out.println(str); //shows what we get from the client
				if (str.compareTo("end")==0) { //client will send "end" when finished testing
					cont = false;
				}
				else { //if not the end, it turns the string into 3 ints
					x = str.indexOf(",");
					working = str.substring(0, x);
					str = str.substring(x+1);
					x = Integer.parseInt(working);
					y = str.indexOf(",");
					working = str.substring(0, y);
					z = Integer.parseInt(str.substring(y+1));
					y = Integer.parseInt(working);
					System.out.println("x:"+x+" y:"+y+" z:"+z);  
				}
			}
			ss.close();  
		}catch(Exception e){System.out.println(e);}  
	}  
}  