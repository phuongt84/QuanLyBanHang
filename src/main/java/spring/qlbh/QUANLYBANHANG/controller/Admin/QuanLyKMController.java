package spring.qlbh.QUANLYBANHANG.controller.Admin;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import spring.qlbh.QUANLYBANHANG.dao.KhuyenMaiDAO;
import spring.qlbh.QUANLYBANHANG.dao.LoaiHangDAO;
import spring.qlbh.QUANLYBANHANG.model.HangInfo;
import spring.qlbh.QUANLYBANHANG.model.KhuyenMaiInfo;
import spring.qlbh.QUANLYBANHANG.model.LoaiHangInfo;
@Transactional
@Controller(value = "QuanLyKMController")
@RequestMapping(value = "/admin/khuyenmai")
public class QuanLyKMController {
	@Autowired
	private KhuyenMaiDAO khuyenMaiDao;
	@RequestMapping("/show")
	public String loadKhuyenMai(Model model) {
		List<KhuyenMaiInfo> khuyenMai = khuyenMaiDao.loadKM();
		model.addAttribute("khuyenMai", khuyenMai);
		return "admin/QLKhuyenMai";
	}
	@RequestMapping(value="/them")
	public String themKM(Model model, HttpServletRequest request, @ModelAttribute("themKMInfo") KhuyenMaiInfo themKMInfo) {
		return "admin/ThemKM";
	}
	
	@RequestMapping(value="/them/hoantat")
	public String themKhuyenMai(Model model, HttpServletRequest request, @ModelAttribute("themKMInfo") KhuyenMaiInfo themKMInfo) {
		Random rand = new Random();
		int maKM = rand.nextInt(100);
		String tenKM = themKMInfo.getTenKM();
		String ngayBD = themKMInfo.getNgayBD();
		String ngayKT = themKMInfo.getNgayKT();
		int phanTram = themKMInfo.getPhanTram();
		
		KhuyenMaiInfo khuyenmai = new KhuyenMaiInfo(maKM, tenKM,ngayBD,ngayKT, phanTram);
		khuyenMaiDao.themKM(khuyenmai);
		return "redirect:/admin/khuyenmai/show";
	}
	@RequestMapping("/sua/{maKM}")
	public String suaHang(@PathVariable("maKM") int maKM, Model model, HttpServletRequest request,
			@ModelAttribute("suaKMInfo") KhuyenMaiInfo suaKMInfo) {
		
		KhuyenMaiInfo kmID = khuyenMaiDao.loadKMID(maKM);
		model.addAttribute("kmID", kmID);
		return "admin/SuaKM";
	}
	@RequestMapping(value="/xoa")
	public String xoaKM(Model model, HttpServletRequest request, HttpSession session) {
		int maKM = Integer.parseInt(request.getParameter("maKM"));
		khuyenMaiDao.xoaKM(maKM);
		return "redirect:/admin/khuyenmai/show";
	}
}
