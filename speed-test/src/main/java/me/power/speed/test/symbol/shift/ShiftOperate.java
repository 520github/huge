package me.power.speed.test.symbol.shift;


//对负数的二进制解码，首先对其所有的位取反，然后加1，如：
//-42 11010110 取反后为00101001 ，或41，然后加1，这样就得到了42
public class ShiftOperate {
	public static int intShiftLeft(int value, int step) {
		return value << step;
	}
	
	public static int intShiftRight(int value, int step) {
		return value >>= step;
	}
	
	public static int intShiftRightWithUnSign(int value, int step) {
		return value >>>= step;
	}
	
	//按位非，也叫补，也就是取反
	public static int intNot(int value) {
		return ~value;
	}
	
	public static int intAnd(int value1, int value2) {
		return value1 & value2;
	}
	
	public static int intOr(int value1, int value2) {
		return value1 | value2;
	}
	//异或，按位异或运算符“^”，只有在两个比较的位不同时其结果是 1。否则，结果是零
	public static int intXOr(int value1, int value2) {
		return value1 ^ value2;
	}
	
}
