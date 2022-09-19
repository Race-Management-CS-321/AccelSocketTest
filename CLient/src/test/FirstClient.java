package test;

import java.io.*;  
import java.net.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FirstClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String xyz = null;
		boolean loops = true;
		int t = 0;
		String ip = "localhost";
		int port = 6665;
		try{      
			Socket s=new Socket(ip,port);  
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());    
			while (loops) {
				xyz = accelUpdate();
				dout.writeUTF(xyz);
				TimeUnit.SECONDS.sleep(1);
				t++;
				if (t==10) { //test is done 10 time
					loops = false;
				}
			}
			dout.writeUTF("end");
			dout.flush();  
			dout.close();  
			s.close();  
			}catch(Exception e){System.out.println(e);}  
		}  
	
	public static String accelUpdate(){ // creates random xyz values to simulate an accelerometer
		int x=0;
		int y=0;
		int z=0;
		Random rand = new Random();
		x = rand.nextInt(100);
		y = rand.nextInt(100);
		z = rand.nextInt(100);
		return (x + "," + y+ "," + z);
	}
}
