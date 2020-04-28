package spring.qlbh.QUANLYBANHANG.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import spring.qlbh.QUANLYBANHANG.dao.KhuyenMaiDAO;
import spring.qlbh.QUANLYBANHANG.entity.Hang;
import spring.qlbh.QUANLYBANHANG.entity.KhuyenMai;
import spring.qlbh.QUANLYBANHANG.entity.LoaiHang;
import spring.qlbh.QUANLYBANHANG.model.KhuyenMaiInfo;
import spring.qlbh.QUANLYBANHANG.model.LoaiHangInfo;

public class KhuyenMaiDAOImpl implements KhuyenMaiDAO {
	@Autowired
	private SessionFactory sessionfactory;
	@Override
	public List<KhuyenMaiInfo> loadKM() {
		Session session = sessionfactory.getCurrentSession();
		String sql = " select new  " + KhuyenMaiInfo.class.getName()
					+" ( km.maKM, km.tenKM,km.ngayBD, km.ngayKT,km.phanTram) " 
					+" from " + KhuyenMai.class.getName() + " km "; 
		Query query = session.createQuery(sql);
		
		return query.list();
		
	}
	@Override
	public void themKM(KhuyenMaiInfo khuyenmai) {
		Session session = sessionfactory.getCurrentSession();
		KhuyenMai kmEntity = new KhuyenMai();
		kmEntity.setMaKM(khuyenmai.getMaKM());
		kmEntity.setTenKM(khuyenmai.getTenKM());
		kmEntity.setNgayBD(khuyenmai.getNgayBD());
		kmEntity.setNgayKT(khuyenmai.getNgayKT());
		kmEntity.setPhanTram(khuyenmai.getPhanTram());
		
		session.persist(kmEntity);
		
	}
	@Override
	public void xoaKM(int maKM) {
		KhuyenMai km = this.findKM(maKM);
		if (km != null) {
			this.sessionfactory.getCurrentSession().delete(km);
		}
		
	}
	@Override
	public KhuyenMai findKM(int maKM) {
		Session session = sessionfactory.getCurrentSession();
		Criteria crit = session.createCriteria(KhuyenMai.class);
		crit.add(Restrictions.eq("maKM", maKM));
		return (KhuyenMai) crit.uniqueResult();
	}
	@Override
	public KhuyenMaiInfo loadKMID(int maKM) {
		Session session = sessionfactory.getCurrentSession();
		String sql = " select new  " + KhuyenMaiInfo.class.getName()
					+" ( km.maKM, km.tenKM,km.ngayBD,km.ngayKT, km.phanTram) " // cÃ¡c trÆ°á»�ng trong Ä‘á»‘i tÆ°á»£ng LoaiHangInfo
					+" from " + KhuyenMai.class.getName() + " km "+ " where km.maKM=: maKM  ";
		// select bÃ ng Hibernate thÃ¬ nÃ³ tráº£ vá»� 1 Ä‘á»‘i tÆ°á»£ng
		
		Query query = session.createQuery(sql);
		query.setParameter("maKM", maKM);
		return (KhuyenMaiInfo) query.uniqueResult();
		
	}
	
	

}
