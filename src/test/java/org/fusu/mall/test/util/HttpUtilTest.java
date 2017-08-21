package org.fusu.mall.test.util;

import java.io.IOException;

import org.fusu.mall.util.HttpUtil;
import org.junit.Assert;
import org.junit.Test;

public class HttpUtilTest {

	@Test
	public void testGetBody() {
		String body = null;
		try {
			body = HttpUtil.getBody("https://shouji.jd.com");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(body);
		Assert.assertNotNull(body);
	}
}
