<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="<c:url value="/static/custom/js/group/group_permission.js"/>"></script>

<div class="page-content">
    <div class="page-header">
        <h1>
            Trang chủ
            <small><i
                    class="ace-icon fa fa-angle-double-right"></i> Quản lý nhóm quyền
            </small>
        </h1>
    </div>
    <!-- /.page-header -->

    <!-- PAGE CONTENT BEGINS -->
    <button class="btn btn-primary btn-add">Add</button>

    <div class="row">
        <div class="col-xs-12">
            <table id="simple-table" class="table  table-bordered table-hover">
                <thead>
                <tr>
                    <th class="center">
                        <label class="pos-rel">
                            <input type="checkbox" class="ace">
                            <span class="lbl"></span>
                        </label>
                    </th>
                    <th class="detail-col">Quyền</th>
                    <th>Tên Nhóm</th>

                    <th></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${groupList}" var="group">
                    <tr>
                        <td class="center">
                            <label class="pos-rel">
                                <input type="checkbox" class="ace">
                                <span class="lbl"></span>
                            </label>
                        </td>

                        <td class="center">
                            <div class="action-buttons">
                                <a href="#" class="green bigger-140 show-details-btn" title="Show Details">
                                    <i class="ace-icon fa fa-angle-double-down"></i>
                                    <span class="sr-only">Details</span>
                                </a>
                            </div>
                        </td>

                        <td>
                            <a href="#"
                               class="group-id-${group.getGroupPermissionModel().getId()}">${group.getGroupPermissionModel().getName()}</a>
                        </td>

                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <button class="btn btn-xs btn-info btn-group-edit"
                                        data-id="${group.getGroupPermissionModel().getId()}">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>

                                <button class="btn btn-xs btn-danger btn-group-remove"
                                        data-id="${group.getGroupPermissionModel().getId()}">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>
                            </div>
                        </td>
                    </tr>

                    <tr class="detail-row">
                        <td colspan="8">
                            <div class="table-detail">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="space visible-xs"></div>

                                        <div class="profile-user-info profile-user-info-striped">
                                            <c:forEach items="${group.getPermissions()}" var="permission">
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name code-${permission.id}">${permission.getCode()}</div>

                                                    <div class="profile-info-value permission-description">
                                                        <span class="description-${permission.id}">${permission.getDescription()}</span>
                                                    </div>
                                                    <div class="profile-info-value permission-lock">
                                                        <c:if test="${permission.islock}">
                                                            <span class="label label-sm label-danger lock-${permission.id}">Khóa</span>
                                                        </c:if>
                                                        <c:if test="${!permission.islock}">
                                                            <span class="label label-sm label-success lock-${permission.id}">Không khóa</span>
                                                        </c:if>
                                                    </div>
                                                    <div class="hidden-sm hidden-xs action-buttons">
                                                        <a class="green btnedit edit-${permission.id}" href="#"
                                                           data-id="${permission.id}">
                                                            <i class="ace-icon fa fa-pencil bigger-130"></i>
                                                        </a>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div><!-- /.span -->
    </div>

    <!-- Modal -->
    <div class="modal fade" id="edit-modal" role="dialog" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog modal-md" style="width: 1000px;">
            <div class="modal-content" style="width: 1000px;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"><i class="fa fa-check-square-o"></i> Thêm mới quyền</h4>
                </div>
                <form class="form-horizontal" id="frmForm">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Tên Nhóm Quyền</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="txtname"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Danh Sách Quyền</label>
                            <div class="col-xs-12 col-sm-9">
                                <select id="allPermissions" class="multiselect" multiple="">
                                    <c:forEach items="${permissionList}" var="permission">
                                        <option value="${permission.code}">${permission.description}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="modal-footer">
                    <button type="button" class="btn btn-sm btn-success btn-add-group"><i
                            class="fa fa-check-square-o"></i> Thêm mới
                    </button>
                    <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal"><i class="fa fa-times"></i>
                        Đóng
                    </button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div id="remove-group-modal" class="bootbox modal fade bootbox-confirm in" tabindex="-1" role="dialog"
         style="padding-right: 13px;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <button type="button" class="bootbox-close-button close" data-dismiss="modal" aria-hidden="true"
                            style="margin-top: -10px;">×
                    </button>
                    <div class="bootbox-body">Are you sure?</div>
                </div>
                <div class="modal-footer">
                    <button data-bb-handler="cancel" type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button data-bb-handler="confirm" type="button" class="btn btn-primary btn-remove-group-confirm">OK</button>
                </div>
            </div>
        </div>
    </div>
</div>