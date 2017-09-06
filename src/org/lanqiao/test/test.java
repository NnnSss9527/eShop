package org.lanqiao.test;

//import java.util.List;

import org.junit.Test;
import org.lanqiao.dao.GoodsDao;
import org.lanqiao.dao.impl.GoodsDaoImpl;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;

public class test {

	@Test
	public void newsDaoImplTest() {
		GoodsDao dao = new GoodsDaoImpl();
		PageInfo<Goods> pageInfo = dao.pageInfo("1", 2, 5);
		System.out.println(pageInfo.getData().toString());
	}

}
