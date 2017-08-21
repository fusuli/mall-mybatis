package org.fusu.mall.biz;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.fusu.mall.bean.UrlBean;
import org.fusu.mall.mapper.UrlMapper;
import org.fusu.mall.util.SqlSessionFactoryUtil;

public class UrlBiz {
	static SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
	static UrlMapper urlMapper = session.getMapper(UrlMapper.class);

	/**
	 * 根据URL查询URL是否存在和基本信息
	 * 
	 * @param url
	 * @return
	 */
	public static UrlBean queryUrlByUrl(String url) {
		UrlBean urlBean = null;
		urlBean = urlMapper.queryUrlByUrl(url);
		if (urlBean != null) {
			System.out.println("url - " + urlBean.getUrl() + " , status - " + urlBean.getStatus());
		}
		return urlBean;
	}

	/**
	 * 添加URL
	 * 
	 * @param urlBean
	 * @return
	 */
	public static int insertUrl(UrlBean urlBean) {
		int i = urlMapper.insertUrl(urlBean);
		if (i > 0) {
			session.commit();
		}
		return i;
	}

	/**
	 * 更新URL的status
	 * 
	 * @param urlBean
	 * @return
	 */
	public static int updateStatusByUrl(UrlBean urlBean) {
		int i = urlMapper.updateStatusByUrl(urlBean);
		if (i > 0) {
			session.commit();
		}
		return i;
	}

	/**
	 * 根据status查询urlBean
	 * 
	 * @return
	 */
	public static List<UrlBean> queryListByStatus() {
		return urlMapper.queryListByStatus();
	}
}