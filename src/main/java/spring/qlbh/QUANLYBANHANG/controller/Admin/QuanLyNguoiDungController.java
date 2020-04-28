package spring.qlbh.QUANLYBANHANG.controller.Admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import spring.qlbh.QUANLYBANHANG.dao.DonHangDAO;
import spring.qlbh.QUANLYBANHANG.dao.NguoiDungDAO;
import spring.qlbh.QUANLYBANHANG.model.DonHangInfo;
import spring.qlbh.QUANLYBANHANG.model.HangInfo;
import spring.qlbh.QUANLYBANHANG.model.LoaiHangInfo;
import spring.qlbh.QUANLYBANHANG.model.NguoiDungInfo;

@Controller(value = "QuanLyNguoiDungController")
@RequestMapping(value = "/admin/nguoidung")
public class QuanLyNguoiDungController {
	@Autowired
	private NguoiDungDAO nguoidungDAO;

	@RequestMapping("/showND")
	public String xuLyDonHang(Model model) {
		List<NguoiDungInfo> nguoidung = nguoidungDAO.loadNguoiDung();
		model.addAttribute("nguoidung", nguoidung);
		return "admin/QLUser";
	}

	// xoa nguoi dung theo ma
	@RequestMapping("/xoauser")
	public String deleteHang(Model model, HttpServletRequest request, HttpSession session) {
		int maND = Integer.parseInt(request.getParameter("maND"));
		nguoidungDAO.deleteNguoiDung(maND);
		return "redirect:/admin/nguoidung/showND";
	}


	@RequestMapping("/suauser/{maND}")
	public String suaHang(@PathVariable("maND") int maND, Model model, HttpServletRequest request,
			@ModelAttribute("nguoiDungInfo") NguoiDungInfo nguoiDungInfo) {
		
		NguoiDungInfo nd = nguoidungDAO.loadNDTheoMa(maND);
		model.addAttribute("nguoidung", nd);
		System.out.println(nd);
		return "admin/suaUser";
	}

	@RequestMapping(value = "/suauser/hoanthanh/{maND}")
	public String SuaNDThanhCong(@PathVariable("maND") int maND, Model model, HttpServletRequest request,
			@ModelAttribute("nguoiDungInfo") NguoiDungInfo nguoiDungInfo) {
		String requestpage = "";
		
		String tenDN = nguoiDungInfo.getTenDN();
		String matKhau = nguoiDungInfo.getMatKhau();
		String hoTen = nguoiDungInfo.getHoTen();
		int sDT = nguoiDungInfo.getsDT();
		String diaChi = nguoiDungInfo.getDiaChi();
		String Email = nguoiDungInfo.getEmail();
		String loai = nguoiDungInfo.getLoai();
		CommonsMultipartFile fileDatas = nguoiDungInfo.getAnhuser();
		String imageLink = fileDatas.getOriginalFilename();
		NguoiDungInfo nd = new NguoiDungInfo(maND, tenDN, matKhau, hoTen, imageLink, diaChi, sDT, Email, loai);
		
			nguoidungDAO.updateNguoiDung(nd);
			doUpload(request, nguoiDungInfo);

		return requestpage = "redirect:/admin/nguoidung/showND";
	}

	@RequestMapping("/adduser")
	public String themnguoidung(Model model, HttpServletRequest request, HttpSession session) {
		NguoiDungInfo nguoiDungInfo = new NguoiDungInfo();
		model.addAttribute("nguoiDungInfo", nguoiDungInfo);
		Object thongbao = session.getAttribute("thongbao");
		session.removeAttribute("thongbao");
		if (thongbao != null) {
			request.setAttribute("thongbao", thongbao);
		}

		return "admin/addUser";
	}

	@RequestMapping(value = "/adduser/themnguoidung", method = RequestMethod.POST)
	public String ThemND(Model model, HttpServletRequest request,
			@ModelAttribute("nguoiDungInfo") NguoiDungInfo nguoiDungInfo, HttpSession session) {
		String requestpage = "";
		Random rand = new Random();
		int maND = rand.nextInt(1000);
		String tenDN = nguoiDungInfo.getTenDN();
		String matKhau = nguoiDungInfo.getMatKhau();
		String hoTen = nguoiDungInfo.getHoTen();
		int sDT = nguoiDungInfo.getsDT();
		String diaChi = nguoiDungInfo.getDiaChi();
		String Email = nguoiDungInfo.getEmail();
		String loai = nguoiDungInfo.getLoai();
		CommonsMultipartFile fileDatas = nguoiDungInfo.getAnhuser();
		String imageLink = fileDatas.getOriginalFilename();
		NguoiDungInfo nd = new NguoiDungInfo(maND, tenDN, matKhau, hoTen, imageLink, diaChi, sDT, Email, loai);
		if (nguoidungDAO.checkTrungTenDN(tenDN) == null) {
			nguoidungDAO.insertNguoiDung(nd);
			doUpload(request, nguoiDungInfo);

			requestpage = "redirect:/admin/nguoidung/showND";
		} else {
			session.setAttribute("thongbao", "Mã Trùng");
			requestpage = "redirect:/admin/nguoidung/adduser";
		}
		return requestpage;
	}

//upload anh
	private void doUpload(HttpServletRequest request, //
			NguoiDungInfo nguoiDungInfo) {
		String uploadRootPath = request.getServletContext().getRealPath("/") + "template/client/img";
		System.out.println("uploadRootPath=" + uploadRootPath);
		File uploadRootDir = new File(uploadRootPath);
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		CommonsMultipartFile fileDatas = nguoiDungInfo.getAnhuser();
		List<File> uploadedFiles = new ArrayList<File>();
		String name = fileDatas.getOriginalFilename();
		System.out.println("Client File Name = " + name);
		if (name != null && name.length() > 0) {
			try {
				File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(fileDatas.getBytes());
				stream.close();
				uploadedFiles.add(serverFile);
				System.out.println("Write file: " + serverFile);
			} catch (Exception e) {
				System.out.println("Error Write file: " + name);
			}
		}

	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == HangInfo.class) {

			// Ä�Äƒng kÃ½ Ä‘á»ƒ chuyá»ƒn Ä‘á»•i giá»¯a cÃ¡c Ä‘á»‘i tÆ°á»£ng multipart thÃ nh
			// byte[]
			dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		}
	}

}
