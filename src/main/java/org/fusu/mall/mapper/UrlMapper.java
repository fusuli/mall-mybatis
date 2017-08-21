package org.fusu.mall.mapper;

import java.util.List;

import org.fusu.mall.bean.UrlBean;

public interface UrlMapper {

	/**
	 * 根据URL查询是否存在
	 * @param url
	 * @return
	 */
	public UrlBean queryUrlByUrl(String url);
	
	/**
	 * 添加URL
	 * @param url
	 * @return
	 */
	public int insertUrl(UrlBean url);
	
	/**
	 * 更新status
	 * @param url
	 * @return
	 */
	public int updateStatusByUrl(UrlBean url);
	
	/**
	 * 根据status查询urlBean
	 * @return
	 */
	public List<UrlBean> queryListByStatus();
}
