package me.power.speed.test.symbol.shift;


//�Ը����Ķ����ƽ��룬���ȶ������е�λȡ����Ȼ���1���磺
//-42 11010110 ȡ����Ϊ00101001 ����41��Ȼ���1�������͵õ���42
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
	
	//��λ�ǣ�Ҳ�в���Ҳ����ȡ��
	public static int intNot(int value) {
		return ~value;
	}
	
	public static int intAnd(int value1, int value2) {
		return value1 & value2;
	}
	
	public static int intOr(int value1, int value2) {
		return value1 | value2;
	}
	//��򣬰�λ����������^����ֻ���������Ƚϵ�λ��ͬʱ������ 1�����򣬽������
	public static int intXOr(int value1, int value2) {
		return value1 ^ value2;
	}
	
}
