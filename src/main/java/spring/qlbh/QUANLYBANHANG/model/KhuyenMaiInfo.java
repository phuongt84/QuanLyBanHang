package spring.qlbh.QUANLYBANHANG.model;

public class KhuyenMaiInfo {
	private int maKM;
	private String tenKM;
	private String ngayBD;
	private String ngayKT;
	private int phanTram;
	public KhuyenMaiInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KhuyenMaiInfo(int maKM, String tenKM,String ngayBD,String ngayKT, int phanTram) {
		super();
		this.maKM = maKM;
		this.tenKM = tenKM;
		this.ngayBD= ngayBD;
		this.ngayKT=ngayKT;
		this.phanTram = phanTram;
		
	}
	
	public int getMaKM() {
		return maKM;
	}
	public void setMaKM(int maKM) {
		this.maKM = maKM;
	}
	public String getTenKM() {
		return tenKM;
	}
	public void setTenKM(String tenKM) {
		this.tenKM = tenKM;
	}
	public String getNgayBD() {
		return ngayBD;
	}
	public void setNgayBD(String ngayBD) {
		this.ngayBD = ngayBD;
	}
	public String getNgayKT() {
		return ngayKT;
	}
	public void setNgayKT(String ngayKT) {
		this.ngayKT = ngayKT;
	}
	public int getPhanTram() {
		return phanTram;
	}
	public void setPhanTram(int phanTram) {
		this.phanTram = phanTram;
	}
	
}
