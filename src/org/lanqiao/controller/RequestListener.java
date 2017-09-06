package org.lanqiao.controller;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class RequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("我ServletRequestListener被杀了");
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("我ServletRequestListener来了");
	}

}
