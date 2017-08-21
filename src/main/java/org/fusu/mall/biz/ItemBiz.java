package org.fusu.mall.biz;

import org.apache.ibatis.session.SqlSession;
import org.fusu.mall.bean.ItemBean;
import org.fusu.mall.mapper.ItemMapper;
import org.fusu.mall.util.SqlSessionFactoryUtil;

public class ItemBiz {
	static SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
	static ItemMapper itemMapper = session.getMapper(ItemMapper.class);

	/**
	 * 根据title查询item是否存在和基本信息
	 * 
	 * @param title
	 * @return
	 */
	public static ItemBean queryItemByTitle(String title) {
		ItemBean itemBean = null;
		itemBean = itemMapper.queryItemByTitle(title);
		if (itemBean != null) {
			System.out.println("url - " + itemBean.getTitle() + " , url - " + itemBean.getUrl());
		}
		return itemBean;
	}

	/**
	 * 添加item
	 * 
	 * @param itemBean
	 * @return
	 */
	public static int insertItem(ItemBean itemBean) {
		int i = itemMapper.insertItem(itemBean);
		if (i > 0) {
			session.commit();
		}
		return i;
	}
}
