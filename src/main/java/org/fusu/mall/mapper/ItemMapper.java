package org.fusu.mall.mapper;

import org.fusu.mall.bean.ItemBean;

public interface ItemMapper {
	
	public ItemBean queryItemByTitle(String title);

	/**
	 * 添加item
	 * 
	 * @param title
	 * @return
	 */
	public int insertItem(ItemBean itemBean);
}
