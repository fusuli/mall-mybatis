package org.fusu.mall.mybatis;

import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.fusu.mall.bean.ItemBean;
import org.fusu.mall.bean.UrlBean;
import org.fusu.mall.biz.ItemBiz;
import org.fusu.mall.biz.UrlBiz;
import org.fusu.mall.util.HttpUtil;
import org.fusu.mall.util.JsoupUtil;

import org.fusu.mall.util.TimeUtil;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		String url = "https://list.jd.com/list.html?cat=670%2C671%2C672&go=0";
		go(url);
	}

	public static void go(String url) {
		getList(url);
		System.out.println("-----------抓取完成------------");
		do {
			List<UrlBean> urlList = UrlBiz.queryListByStatus();
			for (int i = 0; i < urlList.size(); i++) {
				getList(urlList.get(i).getUrl());
				try {
					Thread.sleep(RandomUtils.nextInt(800, 2000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} while (true);
	}

	public static void getList(String url) {
		String body;
		try {
			body = HttpUtil.getBody(url);
			List<String> aList = JsoupUtil.getALink(body, "jd.com");
			// 封装需要存储的数据
			ItemBean itemBean = JsoupUtil.getItemBean(body, url);
			if(itemBean.getTitle().length()<600) {
				ItemBean itemBean2 = ItemBiz.queryItemByTitle(itemBean.getTitle());
				if (itemBean2 == null) {
					int rs = ItemBiz.insertItem(itemBean);
					System.out.println(itemBean.getTitle() + " 插入结果： " + rs);
				} else {
					System.err.println("item已存在!!!");
				}
			}
			for (int i = 0; i < aList.size(); i++) {
				String nowUrl = StringUtils.trim(aList.get(i));
				UrlBean urlBean = UrlBiz.queryUrlByUrl(nowUrl);
				if (urlBean == null) {
					if (nowUrl.length()> 250) {
						break;
					}
					UrlBean urlBean2 = new UrlBean();
					System.out.println("需要插入URL地址："+nowUrl);
					urlBean2.setUrl(nowUrl);
					urlBean2.setStatus(0);
					urlBean2.setUpdate_time(TimeUtil.getUnix());
					int rs = UrlBiz.insertUrl(urlBean2);
					System.out.println("正在插入URL地址："+nowUrl + " 插入结果： " + rs);
				} else {
					System.err.println("URL已存在!!!");
					continue;
				}
			}

			// 抓取后修改URL
			UrlBean urlBean3 = new UrlBean();
			urlBean3.setUrl(url);
			urlBean3.setStatus(100);
			urlBean3.setUpdate_time(TimeUtil.getUnix());
			int rs = UrlBiz.updateStatusByUrl(urlBean3);
			System.out.println(url + " 抓取后结果修改： " + rs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
