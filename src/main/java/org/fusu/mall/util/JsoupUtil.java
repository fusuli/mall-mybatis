package org.fusu.mall.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.fusu.mall.bean.ItemBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author fusu
 *
 */
public class JsoupUtil {
	/**
	 * 根据html获得网页doc
	 * 
	 * @param html
	 * @return
	 */
	public static Document getDoc(String html) {
		Document doc = Jsoup.parse(html);
		return doc;
	}

	/**
	 * 获取网页title
	 * 
	 * @param html
	 * 
	 * @return
	 */
	public static String getTitle(String html) {
		return getDoc(html).title().trim();
	}

	/**
	 * 根据网页内容
	 * 
	 * @param html
	 * 
	 *            获取urLBase基本地址
	 * @param urlBase
	 * @return
	 */
	public static List<String> getALink(String html, String urlBase) {
		List<String> list = new ArrayList<String>();
		Elements links = getDoc(html).select("a");
		for (Element link : links) {
			String href = link.attr("href");

			if (href.indexOf(urlBase) > 0 && href.indexOf(urlBase) < 10) {
				if (href.indexOf("https:") < 0 && href.indexOf("http:") < 0) {
					href = "https:" + href;
					list.add(href);
				} else if (href.indexOf("http:") < 0) {
					list.add(href);
				}
			} else {
				// System.err.println("抓取的URL不符合要求");
				continue;
			}
		}
		return getOnlyList(list);
	}

	/**
	 * 去除list重复项
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> getOnlyList(List<String> list) {
		LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>(list);
		ArrayList<String> list2 = new ArrayList<String>(linkedHashSet);
		return list2;
	}

	/**
	 * 获取网页关键字
	 * 
	 * @param html
	 *            本页网址
	 * @param url
	 * @return
	 */
	public static ItemBean getItemBean(String html, String url) {
		Document document = getDoc(html);
		ItemBean itemBean = new ItemBean();
		itemBean.setTitle(document.title());
		itemBean.setUrl(url);
		Elements metas = document.select("meta");
		for (Element meta : metas) {
			String name = meta.attr("name");
			// 将name中大写转化为小写
			name = StringUtils.lowerCase(name);
			if (StringUtils.equals(name, "keywords")) {
				if (meta.attr("content").length() >= 400) {
					String keywords = meta.attr("content").substring(0, 400);
					itemBean.setKeywords(keywords);
				}else if(meta.attr("content").length() < 400) {
					itemBean.setKeywords(meta.attr("content"));
				}
			} else if (StringUtils.equals(name, "description")) {
				if(meta.attr("content").length() >= 800) {
					String description = meta.attr("content").substring(0, 800);
					itemBean.setDescription(description);
				}else if(meta.attr("content").length() < 800) {
					itemBean.setDescription(meta.attr("content"));
				}		
			}
		}
		return itemBean;
	}
}
