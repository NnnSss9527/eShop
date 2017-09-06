package org.lanqiao.service.impl;

import org.lanqiao.dao.PasswordAnswerDao;
import org.lanqiao.dao.impl.PasswordAnswerDaoImpl;
import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.service.PasswordAnswerService;

public class PasswordAnswerServiceImpl implements PasswordAnswerService {
	PasswordAnswerDao dao = new PasswordAnswerDaoImpl();
	@Override
	public void insertPasswordAnswer(PasswordAnswer passwordAnswer) {
		dao.insert(passwordAnswer);
	}
	@Override
	public void updatePasswordAnswer(PasswordAnswer passwordAnswer) {
		dao.update(passwordAnswer);
	}
	@Override
	public PasswordAnswer getPasswordAnswerByUserId(String userid) {
		return dao.getPasswordAnswer(userid);
	}

}
