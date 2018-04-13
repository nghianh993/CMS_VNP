<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="sidebar" class="sidebar responsive ace-save-state">
	<script type="text/javascript">
		try{ace.settings.loadState('sidebar')}catch(e){}
	</script>

	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success">
				<i class="ace-icon fa fa-signal"></i>
			</button>

			<button class="btn btn-info">
				<i class="ace-icon fa fa-pencil"></i>
			</button>

			<button class="btn btn-warning">
				<i class="ace-icon fa fa-users"></i>
			</button>

			<button class="btn btn-danger">
				<i class="ace-icon fa fa-cogs"></i>
			</button>
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span> <span class="btn btn-info"></span>

			<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
		</div>
	</div>
	<!-- /.sidebar-shortcuts -->

	<ul class="nav nav-list">
		<li <c:if test="${active == 'home'}">class="active"</c:if>>
			<a href="/cms/admin/home"> 
				<i class="menu-icon fa fa-tachometer"></i> 
				<span class="menu-text">Màn hình chính</span>
			</a> 
			<b class="arrow"></b>
		</li>
		
		<li <c:if test="${active == 'user'}">class="active"</c:if>>
			<a href="/cms/admin/account"> 
				<i class="menu-icon fa fa-users"></i> 
				<span class="menu-text">Quản lý tài khoản</span>
			</a> 
			<b class="arrow"></b>
		</li>
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-gg-circle"></i>
				<span class="menu-text"> Phân quyền </span>
		
				<b class="arrow fa fa-angle-down"></b>
			</a>
		
			<b class="arrow"></b>
		
			<ul class="submenu nav-hide" style="display: none;">
				<li class="">
					<a href="form-elements.html">
						<i class="menu-icon fa fa-caret-right"></i>
						Quản lý quyền
					</a>
		
					<b class="arrow"></b>
				</li>
				<li class="">
					<a href="form-elements.html">
						<i class="menu-icon fa fa-caret-right"></i>
						Quản lý nhóm quyền
					</a>
		
					<b class="arrow"></b>
				</li>
				<li class="">
					<a href="form-elements.html">
						<i class="menu-icon fa fa-caret-right"></i>
						Phân quyền chức năng
					</a>
		
					<b class="arrow"></b>
				</li>
			</ul>
		</li>
	</ul>
	<!-- /.nav-list -->

	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i id="sidebar-toggle-icon"
			class="ace-icon fa fa-angle-double-left ace-save-state"
			data-icon1="ace-icon fa fa-angle-double-left"
			data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>
</div>
