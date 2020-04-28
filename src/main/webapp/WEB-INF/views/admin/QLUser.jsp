<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="content-wrapper">
	<div class="col-sm-offset-0 col-sm-12">
		<div class="content-header">
			<div >
				<div class="row mb-2">
					<div class="col-sm-4">
						<h3 cs>QUẢN LÝ NGƯỜI DÙNG</h3>
					</div>
					<div class="col-sm-4">
					
					</div>
					<div class="col-sm-4" style="text-align: right; font-size: 20px;">
						<!-- <a href="#" style="font-size: 20px;margin-top: 20px"class="glyphicon glyphicon-plus">Thêm</a>  -->
						<a href="${pageContext.request.contextPath}/admin/nguoidung/adduser" class="btn btn-outline-success"> <span class="fa fa-plus"></span>
							Thêm
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="table">
		<table class="table">
			<thead>
									<tr>
										<th>Tên đăng nhập</th>
										<th>Họ và tên</th>
										<th>Ảnh người dùng</th>
										<th>Địa chỉ</th>
										<th>SĐT</th>
										<th>Email</th>
										<th>Loại</th>
										<th>Công cụ</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${nguoidung}" var="nd">
										<tr>
										<td>${nd.tenDN}</td>
											<td>${nd.hoTen}</td>
											<td><img style="max-width: 50px; height: 50px" src="${pageContext.request.contextPath}/template/client//img/${nd.image}" /></td>
											<td>${nd.diaChi}</td>
											<td>${nd.sDT}</td>
											<td>${nd.email}</td>
											<td>${nd.loai}</td>
												<td><a href="${pageContext.request.contextPath}/admin/nguoidung/suauser/${nd.maND}">
												<button data-toggle="tooltip"
														style="border: 2px solid lightgrey; border-radius: 5px;"
														data-original-title="Sửa">
													<i class="fa fa-edit" aria-hidden="true"></i>
												</button>
										</a>
										<a
												href="${pageContext.request.contextPath}/admin/nguoidung/xoauser?maND=${nd.maND}"
												onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng này không? ')">
													<button data-toggle="tooltip"
														style="border: 2px solid lightgrey; border-radius: 5px;"
														data-original-title="Xóa">
														<i class="fa fa-trash" aria-hidden="true"></i>
													</button>
											</a></td>
										</tr>
								</c:forEach>
								</tbody>
		</table>
	</div>
</div>