package org.lanqiao.entity;

public class Goods {
	private String gid;
	private String gtitle;
	private String gauthor;
	private double gsaleprice;
	private double ginprice;
	private String gdesc;
	private String gimg;
	private int gclicks;
	private String cid;
	private String pid;
	private Publisher publisher;
	private Category category;
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Goods(String gtitle) {
		super();
		this.gtitle = gtitle;
	}
	public Goods(String gid, String gtitle, String gauthor, double gsaleprice, double ginprice, String gdesc,
			String gimg, int gclicks, String cid, String pid) {
		super();
		this.gid = gid;
		this.gtitle = gtitle;
		this.gauthor = gauthor;
		this.gsaleprice = gsaleprice;
		this.ginprice = ginprice;
		this.gdesc = gdesc;
		this.gimg = gimg;
		this.gclicks = gclicks;
		this.cid = cid;
		this.pid = pid;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGtitle() {
		return gtitle;
	}
	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}
	public String getGauthor() {
		return gauthor;
	}
	public void setGauthor(String gauthor) {
		this.gauthor = gauthor;
	}
	public double getGsaleprice() {
		return gsaleprice;
	}
	public void setGsaleprice(double gsaleprice) {
		this.gsaleprice = gsaleprice;
	}
	public double getGinprice() {
		return ginprice;
	}
	public void setGinprice(double ginprice) {
		this.ginprice = ginprice;
	}
	public String getGdesc() {
		return gdesc;
	}
	public void setGdesc(String gdesc) {
		this.gdesc = gdesc;
	}
	public String getGimg() {
		return gimg;
	}
	public void setGimg(String gimg) {
		this.gimg = gimg;
	}
	public int getGclicks() {
		return gclicks;
	}
	public void setGclicks(int gclicks) {
		this.gclicks = gclicks;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Goods [gid=" + gid + ", gtitle=" + gtitle + ", gauthor=" + gauthor + ", gsaleprice=" + gsaleprice
				+ ", ginprice=" + ginprice + ", gdesc=" + gdesc + ", gimg=" + gimg + ", gclicks=" + gclicks + ", cid="
				+ cid + ", pid=" + pid + "]";
	}
}
