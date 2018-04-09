<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="login-box" class="login-box visible widget-box no-border">
	<div class="widget-body">
		<div class="widget-main">
			<h4 class="header blue lighter bigger">
				<i class="ace-icon fa fa-coffee green"></i>
				ĐĂNG NHẬP HỆ THỐNG
			</h4>

			<div class="space-6"></div>
			<c:if test="${not empty error}">
				<div class="alert alert-block alert-danger message">
					<button type="button" class="close" data-dismiss="alert">
						<i class="ace-icon fa fa-times"></i>
					</button>
					<i class="ace-icon fa fa-exclamation-circle red"></i> ${error}
				</div>
			</c:if>
			<c:if test="${not empty msg}">
				
				<div class="alert alert-block alert-success message">
					<button type="button" class="close" data-dismiss="alert">
						<i class="ace-icon fa fa-times"></i>
					</button>
					<i class="ace-icon fa fa-check green"></i> ${msg}
				</div>
				
			</c:if>
			<form action="<c:url value='login' />" method="post">
				<fieldset>
					<label class="block clearfix">
						<span class="block input-icon input-icon-right">
						<input type="text" name="username" class="form-control" placeholder="Tên đăng nhập">
							<i class="ace-icon fa fa-user"></i>
						</span>
					</label>

					<label class="block clearfix">
						<span class="block input-icon input-icon-right">
							<input type="password" name="password" class="form-control" placeholder="Mật khẩu">
							<i class="ace-icon fa fa-lock"></i>
						</span>
					</label>

					<div class="space"></div>

					<div class="clearfix">
						<label class="inline">
							<input type="checkbox" class="ace" />
							<span class="lbl"> Ghi nhớ đăng nhập</span>
						</label>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<button type="submit" class="width-40 pull-right btn btn-sm btn-primary">
							<i class="ace-icon fa fa-key"></i>
							<span class="bigger-110">Đăng nhập</span>
						</button>
					</div>

					<div class="space-4"></div>
				</fieldset>
			</form>
		</div><!-- /.widget-main -->		
	</div><!-- /.widget-body -->
</div><!-- /.login-box -->