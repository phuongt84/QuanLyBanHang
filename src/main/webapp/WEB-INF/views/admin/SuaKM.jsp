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
			<h1>SỬA THÔNG TIN KHUYẾN MÃI</h1>
		</div>
	</section>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="">

					<!-- MyUploadForm -->
					<form:form modelAttribute="suaKMInfo" method="POST"
						action="${pageContext.request.contextPath}/admin/khuyenmai/sua/hoanthanh/${kmID.maKM}"
						enctype="multipart/form-data" onsubmit="return myFunction()">
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label>Tên khuyến mãi</label>

									<form:input path="tenKM" value="${kmID.tenKM}" type="text" class="form-control"
										 id="tenKM" />
									
								</div>
								<div class="form-group">
									<label>Ngày bắt đầu</label>

									<form:input path="ngayBD" value="" type="date" class="form-control"
										 id="ngayBD" />
									
								</div>
								<div class="form-group">
									<label>Ngày kết thúc</label>

									<form:input path="ngayKT" value="" type="date" class="form-control"
										 id="ngayKT" />
									
								</div>
								<div class="form-group">
									<label>Phần trăm(%)</label>

									<form:input path="phanTram" value="${kmID.phanTram}" type="text" class="form-control"
										 id="phanTram" />
									
								</div>
								<div class="form-group">
									<label>Trạng thái</label>  
									<form:select path="trangThaiKM" class="form-control custom-select">
										<option>1</option>
										<option>0</option>
									</form:select>


								</div>
							</div>
						 
						</div>
						
						<div class="row" style="margin-top: 10px">
							<div class="col-sm-6">
								<button type="submit" id="ThemHang"
									class="btn btn-primary" >Sửa thông tin</button>

								<a href="${pageContext.request.contextPath}/admin/khuyenmai/show" class="btn btn-info "> <span></span> Cancel
								</a>
							</div>
						</div>
					</form:form>

				</div>
			</div>
		</div>
			<script type="text/javascript">
			function myFunction() {
			var tenKM= document.getElementById("tenKM").value;
			var ngayBD= document.getElementById("ngayBD").value;
			var ngayKT= document.getElementById("ngayKT").value;
			var phanTram= document.getElementById("phanTram").value;			
			 submitOK = "true";			 
			if(tenKM=="" || ngayBD=="" || ngayKT=="" || phanTram=="" ){
				 alert("Vui lòng điền đủ thông tin!");
				    submitOK = "false";
			}
			
			 if (submitOK == "false") {
				    return false;
				  }
			}
		</script>
	</section>
</div>
<br>
<br>

