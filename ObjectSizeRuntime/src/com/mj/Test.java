package com.mj;

import java.util.List;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		ArrayTest t = new ArrayTest();
		List a = new ArrayList<Object>();
		a.add(t);
		int res = ObjectSizeCalculator.getSize(a);
		System.out.println(res);
	}

}
