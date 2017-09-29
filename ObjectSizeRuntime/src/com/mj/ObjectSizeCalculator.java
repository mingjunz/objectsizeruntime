package com.mj;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class ObjectSizeCalculator {
	
	public static int REFERNECE_SIZE =8;
	public static int OBJECT_HEADER_SIZE =8;
	public static int ARRAY_HEADER_SIZE = 12;
	

	public static int getSize(Object obj) {
		int result = 0;
		if (obj == null) {
			return REFERNECE_SIZE;
		}
		if (obj.getClass().isArray()) {
			result = getSizeForArray(obj);
		} else if (isPrime(obj)) {

			result = getPrimeObjectSize(obj);
		} else {
			result = getSizeForNonArray(obj);
		}

		return result;
	}
	
	private static boolean isPrime(Object obj){
		boolean res = false;
		if (obj instanceof Boolean) {
			res = true;
		} else if (obj instanceof Byte) {
			res = true;
		} else if (obj instanceof Character) {
			res = true;
		} else if (obj instanceof Float) {
			res = true;
		} else if (obj instanceof Integer) {
			res = true;
		} else if (obj instanceof Long) {
			res = true;
		} else if (obj instanceof Short) {
			res = true;
		} else if (obj instanceof Double) {
			res = true;
		}

		return res;
	}

	private static int getPrimeObjectSize(Object obj) {
		if (obj instanceof Boolean) {
			return 16;
		} else if (obj instanceof Byte) {
			return 16;
		} else if (obj instanceof Character) {
			return 16;
		} else if (obj instanceof Float) {
			return 16;
		} else if (obj instanceof Integer) {
			return 16;
		} else if (obj instanceof Long) {
			return 16;
		} else if (obj instanceof Short) {
			return 16;
		} else if (obj instanceof Double) {
			return 16;
		}

		return 0;
	}

	private static int getSizeForArray(Object arr) {
		int result = ARRAY_HEADER_SIZE;

		int len = Array.getLength(arr);
		for (int i = 0; i < len; i++) {
			Object ith = Array.get(arr, i);
			result += getSize(ith);
		}
		return result;
	}

	private static int getSizeForNonArray(Object obj) {
		int result = OBJECT_HEADER_SIZE;

		for (Field field : obj.getClass().getDeclaredFields()) {

			field.setAccessible(true);
			if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
				Object value = null;
				try {
					value = field.get(obj);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				if (value != null) {
					result += getSize(value);

				}else{
					result += REFERNECE_SIZE;
				}
			}
			
		}
		return result;
	}

	public static boolean isBaseType(byte obj) {
		return true;
	}

	public static boolean isBaseType(short obj) {
		return true;
	}

	public static boolean isBaseType(char obj) {
		return true;
	}

	public static boolean isBaseType(int obj) {
		return true;
	}

	public static boolean isBaseType(long obj) {
		return true;
	}

	public static boolean isBaseType(float obj) {
		return true;
	}

	public static boolean isBaseType(double obj) {
		return true;
	}

	public static boolean isBaseType(boolean obj) {
		return true;
	}

}
