package org.fusu.mall.test.util;

import java.util.List;

import org.fusu.mall.util.JsoupUtil;
import org.junit.Assert;
import org.junit.Test;

public class JsoupUtilTest {

	@Test
	public void testGetALink() {
		String body = "<html><body><a href=\"//11111list.jd.com/list.html\">移动电源</a>"
				+ "<a href=\"//22222list.jd.com/list.html\">移动电源</a>"
				+ "<a href=\"http://33333list.jd.com/list.html\">移动电源</a>"
				+ "<a href=\"https://list.jd.com/list.html\">移动电源</a>"
				+ "<a href=\"https://mall.jd.com/view_search-391297-5940315-99-1-24-1.html//item.jd.com/14928526543.html"
				+ "\">移动电源</a></body></html>";
		String urlBase = "jd.com";
		List<String> list = JsoupUtil.getALink(body, urlBase);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		Assert.assertNotNull(list);
	}
}
