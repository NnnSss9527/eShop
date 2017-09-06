package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;

public interface GoodsDao {
	 public PageInfo<Goods> pageInfo(String cid,int pageIndex,int pageSize);
	 public int total(String cid);
	 public Goods getGoods(String gid);
	 public List<Goods> getList(String search);
	 public String insert(Goods goods);
	 public String remove(String gid);
	 public PageInfo<Goods> getLikeGoodsList(String cid,String inputs,int pageIndex,int pageSize);
	 public String update(Goods goods);
	 public int totalLike(String cid,String inputs);
	 public void updateCategory(String cid);
	 public void updatePublisher(String pid);
}
