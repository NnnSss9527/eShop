package org.lanqiao.dao;

import org.lanqiao.entity.PasswordAnswer;

public interface PasswordAnswerDao {
	public void insert(PasswordAnswer passwordAnswer);
	public void update(PasswordAnswer passwordAnswer);
	public PasswordAnswer getPasswordAnswer(String userid);
}
