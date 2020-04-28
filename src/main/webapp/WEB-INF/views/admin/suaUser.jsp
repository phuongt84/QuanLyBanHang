<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
.inputfile {
  width: 0.1px;
  height: 0.1px;
  opacity: 0;
  overflow: hidden;
  position: absolute;
  z-index: -1;
}
</style>
<div class="container margin_60" style="width: 608px;">
	<section class="content-header">
		<div>
			<h1>SỬA THÔNG TIN NGƯỜI DÙNG</h1>
		</div>
	</section>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="">

					<!-- MyUploadForm -->
					<form:form modelAttribute="nguoiDungInfo" method="POST"
		action="${pageContext.request.contextPath}/admin/nguoidung/suauser/hoanthanh/${nguoidung.maND }"
		enctype="multipart/form-data" onsubmit="return myFunction()">
		<div class="section">
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group">
									<label>Tên Đăng Nhập</label>
									<form:input class="form-control" type="text" path="tenDN" id="tenDN"
									value="${nguoidung.tenDN }" placeholder="Tên Đăng Nhập" />
								</div>
							</div>

						</div>

						<div class="row">
							<div class="col-sm-12">
								<div class="form-group">
									<label>Mật Khẩu</label>

									<form:input path="matKhau" type="password" class="form-control"
										placeholder="mật khẩu" id="matKhau" />

								</div>
							</div>

						</div>

						<div class="row">
							<div class="col-sm-12">
								<div class="form-group">
									<label>Họ Tên</label>

									<form:input path="hoTen" class="form-control"
									value="${nguoidung.hoTen }"	placeholder="Nhập họ tên" id="hoTen" />

								</div>
							</div>

						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group">
									<label>Địa Chỉ</label>

									<form:input path="diaChi" class="form-control"
									value="${nguoidung.diaChi }"	placeholder="Địa chỉ" id="diaChi" />

								</div>
							</div>

						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group">
									<label>SĐT</label>

									<form:input path="sDT" class="form-control"
										value="${nguoidung.sDT }"	placeholder="Số điện thoại" id="sDT" />

								</div>
							</div>

						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group">
									<label>Email</label>

									<form:input path="email" class="form-control" placeholder="Loại"
										value="${nguoidung.email }"	id="email" />

								</div>
							</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
		
										<label>Loại người dùng</label>  
									<form:select path="loai" class="form-control custom-select">
										<form:option value="0" label="Admin" />
										<form:option value="1" label="User" />
									</form:select>

									</div>
								</div>

							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<form:input type="file" path="anhuser" id="file-2"
									style="width: 0.1px;height: 0.1px;opacity: 0;overflow: hidden;position: absolute;z-index: -1;"></form:input>
								<label for="file-2"><span>Đổi Ảnh Đại Diện</span> <svg
										xmlns="http://www.w3.org/2000/svg" width="20" height="17"
										viewBox="0 0 20 17">
						          <path
											d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"></path>
						          </svg><span><-Chọn ảnh</span></label>
									</div>
								</div>
							</div>
							<div class="row" style="margin-top: 10px">
								<div class="col-sm-6">
									<button type="submit" id="" class="btn btn-primary">Sửa</button>

									<a href="${pageContext.request.contextPath}/admin/user"
										class="btn btn-info "> <span></span> Cancel
									</a>
								</div>
							</div>
							<script type="text/javascript">
			function myFunction() {
			var tenDN= document.getElementById("tenDN").value;
			var image= document.getElementById("file-2").value;
			var matKhau= document.getElementById("matKhau").value;
			var hoTen= document.getElementById("hoTen").value;
			var diaChi= document.getElementById("diaChi").value;
			var sdt= document.getElementById("sDT").value;
			var email= document.getElementById("email").value;
			 submitOK = "true";			 
			if(tenDN=="" || matKhau=="" || hoTen=="" || diaChi=="" || sdt=="" || email==""){
				 alert("Vui lòng điền đủ thông tin");
				    submitOK = "false";
			}
			
			if(image==""){
				 alert("Bạn chưa chọn ảnh. Hãy chọn một ảnh ở bên dưới");
				    submitOK = "false";
			}
			
			 if (submitOK == "false") {
				    return false;
				  }
			}
		</script>
					</form:form>
<c:if test="${thongbao != null }">
		<script type="text/javascript">									
			alert("Tên đăng đã tồn tại");																
		</script>	
	</c:if>
				</div>
			</div>
		</div>
	</section>
</div>
<
