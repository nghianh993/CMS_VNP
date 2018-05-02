<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

                <a class="red btnremove" href="#" data-id="${permission.id}" data-toggle="modal" data-target="#remove-modal">
                    <i class="ace-icon fa fa-trash-o bigger-130"></i>
                </a>
            </div>
        </td>
    </tr>
</c:forEach>
<input type="hidden" id="datatable" data-total="${totalPage }" data-totalrecord="${total }" data-page="${currentpage }" />