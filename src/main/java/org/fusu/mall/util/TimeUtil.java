package org.fusu.mall.util;

public class TimeUtil {
	public static int getUnix() {
		//unix时间戳是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数，不考虑闰秒
		return Integer.valueOf(System.currentTimeMillis() / 1000 + "");
	}
}
