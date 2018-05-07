$(document).ready(function() {
    var totalpage = parseInt($('#datatable').attr("data-total"));
    var totalrecord = parseInt($('#datatable').attr("data-totalrecord"));
    var pageSize = 2;
    var active_class = 'active';
    $('#listtable > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
        var th_checked = this.checked;//checkbox inside "TH" table header

        $(this).closest('table').find('tbody > tr').each(function(){
            var row = this;
            if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
            else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
        });
    });

    //select/deselect a row when the checkbox is checked/unchecked
    $('#listtable').on('click', 'td input[type=checkbox]' , function(){
        var $row = $(this).closest('tr');
        if($row.is('.detail-row ')) return;
        if(this.checked) $row.addClass(active_class);
        else $row.removeClass(active_class);
    });

    if (totalpage > 1) {
        var obj = $('#pagination').twbsPagination({
            totalPages: totalpage,
            visiblePages: 3,
            first: 'Trang đầu',
            prev: 'Trước',
            next: 'Tiếp',
            last: 'Trang cuối',
            onPageClick: function (event, page) {
                var curentpage = parseInt($('#datatable').attr("data-page"));
                if (curentpage != page) {
                    if (typeof $(".ddlpage").val() != "undefined") {
                        pageSize = parseInt($(".ddlpage").val());
                    }
                    reloadPage(page);
                }
            }
        });
    }

    function appendData(data){
        $.each(data, function(i, item) {
            var html = '';
            html += '<tr><td class="center"><label class="pos-rel"><input type="checkbox" data-id="'+item.id+ '" class="ace" /> <span class="lbl"></span>';
            html += '</label></td><td>'+ item.email+ '</td><td>'+item.fullname+'</td><td>'+item.address +'</td><td>' +item.phone +'</td>';
            html += '<td>';
            if(item.islock){
                html += '<span class="label label-sm label-danger">Khóa</span>';
            }
            if(!item.islock){
                html += '<span class="label label-sm label-success">Không khóa</span>';
            }
            html += '</td><td>' + item.datecreate +'</td><td style="text-align: center;"><div class="hidden-sm hidden-xs action-buttons">';
            html += '<a class="blue" href="#"><i class="ace-icon fa fa-search-plus bigger-130"></i></a><a class="green" href="#">';
            html += '<i class="ace-icon fa fa-pencil bigger-130"></i></a><a class="red" href="#"><i class="ace-icon fa fa-trash-o bigger-130"></i></a>';
            html += '</div></td></tr>';
        });
    };

    $(document).on('click', '.btnedit', function(){
        var id = $(this).attr("data-id");

        $.get('/api/admin/permission/get',
            {
                id: id
            },
            function(data, status) {
                if (data == null) {
                    alert("Không tìm thấy đữ liệu.");
                    return;
                }

                $('#txtcode').val(data.code);
                $('#txtdescription').val(data.description);
                $('#chb_lock').prop('checked', data.islock);
                $('.btn-success').attr("data-id", id);
                $('#permissionModal').modal('show');
            }
        );
    });

    $(document).on('click', '.btnremove', function(){
        var id = $(this).attr("data-id");

        $('.btn-remove-success').attr("data-id", id);

        $('#remove-modal').modal('show');
    });

    $('.btn-remove-success').on('click' , function(){
        var id = parseInt($(this).attr("data-id"));

        $.post('/api/admin/group_permission/remove',
            {
                id: id,
            },
            function(data, status) {
                if (data) {
                    $('#remove-modal').modal('hide');
                    location.reload();
                }
            }
        );
    });

    $('.btnsave').on('click' , function() {
        $('#txtcode').val("");
        $('#txtdescription').val("");
        $('#chb_lock').prop('checked', "");
        $('.btn-success').attr("data-id", 0);
    });

    $('.btn-success').on('click' , function(){
        var id = parseInt($(this).attr("data-id"));
        var code = $('#txtcode').val();
        var description = $('#txtdescription').val();
        var lock = $('#chb_lock').is(':checked');

        $.post('/api/admin/permission/add',
            {
                id: id,
                code: code,
                description: description,
                lock: lock
            },
            function(data, status) {
                if (data) {
                    $('#permissionModal').modal('hide');
                    if (id == 0) {
                        location.reload();
                    } else {
                        reloadPage(parseInt($('#datatable').attr("data-page")));
                    }
                }
            }
        );
    });

    $('.btn-remove-success').on('click' , function(){
        var id = parseInt($(this).attr("data-id"));

        $.post('/api/admin/permission/remove',
            {
                id: id,
            },
            function(data, status) {
                if (data) {
                    $('#remove-modal').modal('hide');
                    reloadPage(parseInt($('#datatable').attr("data-page")));
                }
            }
        );
    });

    function reloadPage(page) {
        var data = {
            pageIndex: page,
            pageSize: pageSize
        };
        fis_vnp_js.fis_vnp().loadDataAll("/api/admin/permission/loaddata", "listtable", data);
    }
});