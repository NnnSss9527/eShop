package org.lanqiao.entity;

import java.util.List;

public class PageInfo<T> {
	private int pageIndex;
	private int pageSize;
	private int totalNumber;
	private int totalPages;
	private List<T> data;
	private boolean isFirstPage;
	private boolean isLastPage;
	public PageInfo() {
		super();
	}
	public PageInfo(int pageIndex, int pageSize, int totalNumber, int totalPages, List<T> data, boolean isFirstPage,
			boolean isLastPage) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalNumber = totalNumber;
		this.totalPages = totalPages;
		this.data = data;
		this.isFirstPage = isFirstPage;
		this.isLastPage = isLastPage;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public boolean getIsFirstPage() {
		return isFirstPage;
	}
	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}
	public boolean getIsLastPage() {
		return isLastPage;
	}
	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}
}
