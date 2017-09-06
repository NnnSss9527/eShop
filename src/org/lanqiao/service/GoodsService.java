package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;

public interface GoodsService {
	public PageInfo<Goods> PageInfoList(String cid,int pageIndex,int pageSize);
	public Goods getGoodsById(String gid);
	public List<Goods> getListByGTitle(String search);
	public String insertGoods(Goods goods);
	public String removeGoodsByGid(String gid);
	public PageInfo<Goods> getLikeGoodsList(String cid,String inputs,int pageIndex,int pageSize);
	public String upadteGoods(Goods goods);
	public void updateCategory(String cid);
	public void updatePublisher(String pid);
}
