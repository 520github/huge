package me.power.speed.test.symbol.shift;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import me.power.speed.test.AbstractTest;

//http://jinguo.iteye.com/blog/540150
//http://blog.csdn.net/wangming7306/article/details/1621609

//hashcode
//http://blog.csdn.net/sunmenggmail/article/details/18660699
//http://blog.csdn.net/axman/article/details/195159
//http://blog.csdn.net/zj8692286/article/details/12651497
//http://blog.csdn.net/woshixuye/article/details/8189398
public class TestShiftOperate extends AbstractTest {
	@Test
	public void testIntShiftLeft() {
		int result = ShiftOperate.intShiftLeft(-2, 2);
		this.print(result);
	}
	
	@Test
	public void testIntShiftRight() {
		int result = ShiftOperate.intShiftRight(-12, 2);
		this.print(result);
	}
	
	@Test
	public void testIntShiftRightWithUnSign() {
		int result = ShiftOperate.intShiftRightWithUnSign(-1, 24);
		this.print(result);
	}
	
	@Test
	public void testIntNot() {
		int result = ShiftOperate.intNot(42);
		this.print(result);
	}
	
	@Test
	public void testIntAnd() {
		int value1 = 42;
		int value2 = 15;
		this.print(this.Int2Binary(2147483647).length());
		this.print(this.Int2Binary(value1));
		this.print(this.Int2Binary(value2));
		
		int result = ShiftOperate.intAnd(42, 15);
		this.print(this.Int2Binary(result));
		
		this.print(result);
	}
	
	@Test
	public void testIntOr() {
		int value1 = 42;
		int value2 = 15;
		this.print(this.Int2Binary(value1));
		this.print(this.Int2Binary(value2));
		
		int result = ShiftOperate.intOr(42, 15);
		this.print(this.Int2Binary(result));
		this.print(result);
	}
	
	@Test
	public void testIntXOr() {
		int value1 = 42;
		int value2 = 15;
		this.print(this.Int2Binary(value1));
		this.print(this.Int2Binary(value2));
		
		int result = ShiftOperate.intXOr(42, 15);
		this.print(this.Int2Binary(result));
		this.print(result);
	}
	
	@Test
	public void testAssemble() {
		int a = 3; // 0 + 2 + 1 or 0011 in binary 
		int b = 6; // 4 + 2 + 0 or 0110 in binary 
		int c = a | b; 
		int d = a & b; 
		int e = a ^ b; 
		int f = (~a & b) | (a & ~b); 
		int g = ~a & 0x0f; 
		
		this.print(this.Int2Binary(f));
	}
	
	@Test
	public void testByteValue() {
		byte a = 64, b; 
		int i; 
		
		i = a << 2; 
		b = (byte) (a << 2); 
		this.print("Original value of a: " + a);
		this.print("i and b: " + i + " " + b);
	}
	
	@Test
	public void testTemp() {
		int i; 
		int num = 0xFFFFFFE; 
		this.print(num);
		
		for(i=0; i<4; i++) { 
			num = num << 1; 
			this.print("i:" + i + " -->" + num);
			this.print(this.Int2Binary(num));
		} 
		
		this.print(this.Int2Binary(-1));	
		Map map = new HashMap();
		map.hashCode();
		//00000000000000000000000000000001
	}
	
}
