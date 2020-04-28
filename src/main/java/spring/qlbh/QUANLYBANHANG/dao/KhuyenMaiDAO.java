package spring.qlbh.QUANLYBANHANG.dao;

import java.util.List;

import spring.qlbh.QUANLYBANHANG.entity.KhuyenMai;
import spring.qlbh.QUANLYBANHANG.entity.LoaiHang;
import spring.qlbh.QUANLYBANHANG.model.KhuyenMaiInfo;

public interface KhuyenMaiDAO {

	public List<KhuyenMaiInfo> loadKM();

	public void themKM(KhuyenMaiInfo khuyenmai);

	public void xoaKM(int maKM);

	KhuyenMai findKM(int maKM);

	public KhuyenMaiInfo loadKMID(int maKM);
	
}
