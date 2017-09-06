package org.lanqiao.controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("HttpSessionListener我悄悄的来");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("HttpSessionListener我悄悄的走");
	}

}
