<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="<c:url value="/static/custom/js/permission/permission.js"/>"></script>

<div class="page-content">
    <div class="page-header">
        <h1>
            Trang chủ <small> <i
                class="ace-icon fa fa-angle-double-right"></i> Quản lý quyền
        </small>
        </h1>
    </div>
    <!-- /.page-header -->

    <div class="row">
        <div class="col-xs-12 lo-paging-0">
            <div class="col-md-2"></div>
            <div class="input-group col-xs-12 col-md-8 lo-paging-0">
                <input class="form-control txtsearchkey" placeholder="Tìm kiếm theo Email hoặc SĐT" type="text"  />
                <span class="input-group-btn">
                    <button type="button" class="btn btn-inverse btn-white btnsearch" style="background-color: #4267b2 !important; color: white !important;" >
                        <span class="ace-icon fa fa-search icon-on-right bigger-110 searchicon"></span>
                        Tìm
                    </button>
                </span>
            </div>
        </div>
        <div class="col-xs-12">
            <div class="space"></div>
            <div class="hd-fuc">
                <div class="col-md-9 col-xs-12 btn-area-action">
                    <button class="btn btn-sm btn-primary btn-blue btnsave" data-toggle="modal" data-target="#permissionModal">
                        <i class="fa fa-check-square bigger-125"></i>
                        Thêm mới
                    </button>
                    <button class="btn btn-sm btn-default btnhide disabled">
                        <i class="fa fa-edit bigger-125"></i>
                        Sửa
                    </button>

                    <button class="btn btn-sm btn-danger btndelete disabled">
                        <i class="fa fa-trash-o bigger-125"></i>
                        Xóa
                    </button>
                    <button class="btn btn-sm btn-info btn-blue btncc disabled">
                        <i class="fa fa-ban bigger-125"></i>
                        Khóa
                    </button>
                    <button class="btn btn-sm btn-success btnexport">
                        <i class="fa fa-file-excel-o bigger-125"></i>
                        Xuất Excel
                    </button>
                </div>
                <div class="col-md-3 col-xs-12 viewpage">
                    <div class="dataTables_info" id="dynamic-table_info" role="status" aria-live="polite">
                        Tổng số bản ghi : <span class="totalrecord">${total}</span>
                    </div>
                </div>
            </div>

        </div>

        <div class="col-xs-12">
            <table id="listtable" class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th class="center">
                        <label class="pos-rel">
                            <input type="checkbox" class="ace" /> <span class="lbl"></span>
                        </label>
                    </th>
                    <th>Code</th>
                    <th>Quyền</th>
                    <th>Khóa</th>
                    <th style="width: 150px"></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${lstPermission}" var="permission">
                    <tr>
                        <td class="center">
                            <label class="pos-rel">
                                <input type="checkbox" data-id="${permission.id}" class="ace" /> <span class="lbl"></span>
                            </label>
                        </td>
                        <td>${permission.code}</td>
                        <td>${permission.description}</td>
                        <td>
                            <c:if test="${permission.islock}">
                                <span class="label label-sm label-danger">Khóa</span>
                            </c:if>
                            <c:if test="${!permission.islock}">
                                <span class="label label-sm label-success">Không khóa</span>
                            </c:if>
                        </td>
                        <td style="text-align: center;">
                            <div class="hidden-sm hidden-xs action-buttons">
                                <a class="green btnedit" href="#" data-id="${permission.id}">
                                    <i class="ace-icon fa fa-pencil bigger-130"></i>
                                </a>

                                <a class="red btnremove" href="#" data-id="${permission.id}" data-toggle="modal" data-target="#remove_confirm">
                                    <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                </a>
                            </div>
                        </td>
                    </tr>
                    <input type="hidden" id="datatable" data-total="${totalPage }" data-totalrecord="${total }" data-page="${currentpage }" />
                </c:forEach>
                </tbody>
            </table>

        </div>
        <div class="pagecus">
            <c:if test="${totalPage > 1}">
                <div class="ht-mor-pagination page-home lo-paging">
                    <div class="col-xs-12 col-md-4 dv_choose_page">
                        <label>
                            Hiển thị
                            <select class="ddlpage">
                                <option value="2">2</option>
                                <option value="30">30</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                                <option value="150">150</option>
                                <option value="200">200</option>
                            </select>
                            đơn hàng
                        </label>
                    </div>
                    <div class="col-xs-12 col-md-8 lo-paging-0 dv_page" style="text-align: right;">
                        <div class="dataTables_paginate homepagging ">
                            <div class="pagination" id="pagination"></div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
    <!-- /.span -->

    <!-- Modal -->
    <div class="modal fade" id="permissionModal" role="dialog" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog modal-md">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"><i class="fa fa-check-square-o"></i> Thêm mới quyền</h4>
                </div>
                <form class="form-horizontal" id="frmForm">
                    <div class="modal-body">
                        <div class="form-group">
                            <label  class="col-sm-3 control-label">Code</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="txtcode"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-3 control-label">Quyền</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="txtdescription"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-3 control-label">Khóa</label>
                            <div class="col-sm-9">
                                <label>
                                    <input name="switch-field-1" class="ace ace-switch ace-switch-5" type="checkbox" id="chb_lock">
                                    <span class="lbl"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="modal-footer">
                    <button type="button" class="btn btn-sm btn-success"><i class="fa fa-check-square-o"></i> Thêm mới</button>
                    <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal"><i class="fa fa-times"></i> Đóng</button>
                </div>
            </div>
        </div>
    </div>

    <div class="bootbox modal fade bootbox-confirm in" id="remove-modal" tabindex="-1" role="dialog" style="padding-right: 17px;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body"><button type="button" class="bootbox-close-button close" data-dismiss="modal" aria-hidden="true" style="margin-top: -10px;">×</button>
                    <div class="bootbox-body">Are you sure?</div>
                </div>
                <div class="modal-footer">
                    <button data-bb-handler="cancel" type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button data-bb-handler="confirm" type="button" class="btn btn-primary btn-remove-success">OK</button>
                </div>
            </div>
        </div>
    </div>
</div>

