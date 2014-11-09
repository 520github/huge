package me.power.speed.common.test.sample;
import java.io.*;
public class BTraceTest {
	public int add(int a, int b) {
        return a + b;
    }
    
    public static void main(String[] args){
        BTraceTest test = new BTraceTest();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<10; i++) {
        	try {
        		reader.readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
            int a =(int)Math.round(Math.random()*1000);
            int b =(int)Math.round(Math.random()*1000);
            System.out.println(test.add(a, b));
        }
    }
}
