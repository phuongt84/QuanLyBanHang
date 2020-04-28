package spring.qlbh.QUANLYBANHANG.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import spring.qlbh.QUANLYBANHANG.dao.NguoiDungDAO;
import spring.qlbh.QUANLYBANHANG.entity.Hang;
import spring.qlbh.QUANLYBANHANG.entity.NguoiDung;
import spring.qlbh.QUANLYBANHANG.model.NguoiDungInfo;

public class NguoiDungDAOImpl implements NguoiDungDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<NguoiDungInfo> loadNguoiDung() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sql = " Select new " + NguoiDungInfo.class.getName()
				+ "(u.maND, u.tenDN, u.matKhau, u.hoTen, u.image, u.diaChi, u.sDT,u.email, u.loai)" + " from "
				+ NguoiDung.class.getName() + " u ";
		Query query = session.createQuery(sql);
		return query.list();
	}

	@Override
	public NguoiDung findNguoiDung(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(NguoiDung.class);
		crit.add(Restrictions.eq("id", id));
		return (NguoiDung) crit.uniqueResult();
	}

	@Override
	public void xoaNguoiDung(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public NguoiDungInfo checkLogin(String userName, String passWord) {
		// TODO Auto-generated method stub
		Session se = this.sessionFactory.getCurrentSession();

		String sql = " Select new " + NguoiDungInfo.class.getName()
				+ "(u.maND, u.tenDN, u.matKhau, u.hoTen, u.image, u.diaChi, u.sDT,u.email, u.loai)" + " from "
				+ NguoiDung.class.getName() + " u "+" where TenDN =: us and MatKhau =: pw";

		Query query = se.createQuery(sql);
		query.setParameter("us", userName);
		query.setParameter("pw",passWord);
		return (NguoiDungInfo) query.uniqueResult();
	}

	@Override
	public void insertNguoiDung(NguoiDungInfo userInfo) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		NguoiDung nguoiDungEntity = new NguoiDung();
		nguoiDungEntity.setMaND(userInfo.getMaND());
		nguoiDungEntity.setTenDN(userInfo.getTenDN());
		nguoiDungEntity.setMatKhau(userInfo.getMatKhau());
		nguoiDungEntity.setHoTen(userInfo.getHoTen());
		nguoiDungEntity.setImage(userInfo.getImage());
		nguoiDungEntity.setDiaChi(userInfo.getDiaChi());
		nguoiDungEntity.setsDT(userInfo.getsDT());
		nguoiDungEntity.setEmail(userInfo.getEmail());
		nguoiDungEntity.setLoai(userInfo.getLoai());
		session.persist(nguoiDungEntity);
	}
	
	public NguoiDungInfo checkTrungTenDN(String tenDN) {
		Session se = this.sessionFactory.getCurrentSession();

		String sql = " Select new " + NguoiDungInfo.class.getName()
				+ "(u.maND, u.tenDN, u.matKhau, u.hoTen, u.image, u.diaChi, u.sDT,u.email, u.loai)" + " from "
				+ NguoiDung.class.getName() + " u "+" where TenDN =: tenDN ";

		Query query = se.createQuery(sql);
		query.setParameter("tenDN", tenDN);
		
		return (NguoiDungInfo) query.uniqueResult();
	}
	public void updateNguoiDung(NguoiDungInfo nd) {
		Session session = sessionFactory.getCurrentSession();
		NguoiDung nguoiDungEntity = new NguoiDung();
		nguoiDungEntity.setMaND(nd.getMaND());
		nguoiDungEntity.setTenDN(nd.getTenDN());
		nguoiDungEntity.setMatKhau(nd.getMatKhau());
		nguoiDungEntity.setHoTen(nd.getHoTen());
		nguoiDungEntity.setImage(nd.getImage());
		nguoiDungEntity.setDiaChi(nd.getDiaChi());
		nguoiDungEntity.setsDT(nd.getsDT());
		nguoiDungEntity.setEmail(nd.getEmail());
		nguoiDungEntity.setLoai(nd.getLoai());
		session.update(nguoiDungEntity);
	}
	@Override
	public void deleteNguoiDung(int maND) { 
		NguoiDung nguoidung= this.findNguoiDung(maND);
		if (nguoidung != null) {
			this.sessionFactory.getCurrentSession().delete(nguoidung);
		}
	}
	@Override
	public NguoiDungInfo loadNDTheoMa(int maND){
		Session session = sessionFactory.getCurrentSession();

		String sql = " Select new " + NguoiDungInfo.class.getName()
				+ "(u.maND, u.tenDN, u.matKhau, u.hoTen, u.image, u.diaChi, u.sDT,u.email, u.loai)" + " from "
				+ NguoiDung.class.getName() + " u "+" where MaND =: maND ";
		Query query = session.createQuery(sql);
		query.setParameter("maND", maND);
		return (NguoiDungInfo) query.uniqueResult();
	}
}
