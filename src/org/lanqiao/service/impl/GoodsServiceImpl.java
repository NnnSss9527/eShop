package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.GoodsDao;
import org.lanqiao.dao.impl.GoodsDaoImpl;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {
	GoodsDao dao = new GoodsDaoImpl();
	@Override
	public PageInfo<Goods> PageInfoList(String cid, int pageIndex, int pageSize) {
		return dao.pageInfo(cid, pageIndex, pageSize);
	}
	@Override
	public Goods getGoodsById(String gid) {
		return dao.getGoods(gid);
	}
	@Override
	public List<Goods> getListByGTitle(String search) {
		return dao.getList(search);
	}
	@Override
	public String insertGoods(Goods goods) {
		return dao.insert(goods);
	}
	@Override
	public String removeGoodsByGid(String gid) {
		return dao.remove(gid);
	}
	@Override
	public PageInfo<Goods> getLikeGoodsList(String cid,String inputs,int pageIndex,int pageSize) {
		return dao.getLikeGoodsList(cid,inputs,pageIndex,pageSize);
	}
	@Override
	public String upadteGoods(Goods goods) {
		return dao.update(goods);
	}
	@Override
	public void updateCategory(String cid) {
		dao.updateCategory(cid);
	}
	@Override
	public void updatePublisher(String pid) {
		dao.updatePublisher(pid);
	}

}
