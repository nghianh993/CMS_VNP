$(document).ready(function() {
    var active_class = 'active';
    $('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
        var th_checked = this.checked;//checkbox inside "TH" table header

        $(this).closest('table').find('tbody > tr').each(function(){
            var row = this;
            if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
            else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
        });
    });

    $('.show-details-btn').on('click', function(e) {
        e.preventDefault();
        $(this).closest('tr').next().toggleClass('open');
        $(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
    });

    $('.permission-description').width("300px");
    $('.permission-lock').width("90px");

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

    $('.btn-success').on('click' , function(){
        var id = parseInt($(this).attr("data-id"));
        var code = $('#txtcode').val();
        var description = $('#txtdescription').val();
        var lock = $('#chb_lock').is(':checked');

        if (code == "" || description == "") {
            alert("Không được để trống dữ liệu.");
            return;
        }

        blockButton($(this), true);


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
                    blockButton($('.btn-success'), false);

                    $('.code-' + id).text(code);
                    $('.description-' + id).text(description);
                    if (lock) {
                        $('.lock-' + id).removeClass('label-success');
                        $('.lock-' + id).addClass('label-danger');
                        $('.lock-' + id).text("Khóa");
                    } else {
                        $('.lock-' + id).removeClass('label-danger');
                        $('.lock-' + id).addClass('label-success');
                        $('.lock-' + id).text("Không khóa");
                    }
                }
            }
        );
    });

    $(document).on('click', '.btn-add', function(){
        resetModal();

        $('#edit-modal').modal('show');
    });

    $('.multiselect').multiselect({
        enableFiltering: true,
        enableHTML: true,
        buttonClass: 'btn btn-white btn-primary',
        templates: {
            button: '<button type="button" class="multiselect dropdown-toggle" data-toggle="dropdown"><span class="multiselect-selected-text"></span> &nbsp;<b class="fa fa-caret-down"></b></button>',
            ul: '<ul class="multiselect-container dropdown-menu"></ul>',
            filter: '<li class="multiselect-item filter"><div class="input-group"><span class="input-group-addon"><i class="fa fa-search"></i></span><input class="form-control multiselect-search" type="text"></div></li>',
            filterClearBtn: '<span class="input-group-btn"><button class="btn btn-default btn-white btn-grey multiselect-clear-filter" type="button"><i class="fa fa-times-circle red2"></i></button></span>',
            li: '<li><a tabindex="0"><label></label></a></li>',
            divider: '<li class="multiselect-item divider"></li>',
            liGroup: '<li class="multiselect-item multiselect-group"><label></label></li>'
        }
    });

    $('.btn-add-group').on('click' , function(){
        var id = parseInt($(this).attr("data-id"));
        var name = $('#txtname').val();
        var permissions = $('.multiselect').val();

        if (name == null || name == "" || permissions == null) {
            alert("Tên hoặc danh sách quyền không được để trống.");
            return;
        }

        blockButton($(this), true);

        $.post('/api/admin/group_permission/add',
            {
                id: id,
                name: name,
                permissions: permissions.toString(),
            },
            function(data, status) {
                if (data) {
                    $('#edit-modal').modal('hide');
                    blockButton($('.btn-add-group'), false);

                    $('.group-id-' + id).text(name);
                }
            }
        )
    });

    $(document).on('click', '.btn-group-edit', function(){
        var id = $(this).attr("data-id");

        $.get('/api/admin/group_permission/get',
            {
                id: id
            },
            function(data, status) {
                if (data == null) {
                    alert("Không tìm thấy đữ liệu.");
                    return;
                }

                resetModal();

                $('#txtname').val(data.groupPermissionModel.name);

                var vals = [];

                $(".multiselect-container li").each(function()
                {

                    for (var i = 0; i < data.permissions.length; i++) {
                        var permission = $(this).find('a > label input[type=checkbox]');
                        if (permission != undefined && data.permissions[i].code == permission.val()) {
                            $(this).addClass('active');
                            permission.prop('checked', true);

                            vals[i] = permission.val();
                        }
                    }
                });

                if (data.permissions.length > 0) {
                    $('.multiselect').val(vals);
                    $('.btn-primar').attr("title", data.permissions.length + " selected");
                    $('.multiselect-selected-text').text(data.permissions.length + " selected");
                }

                $('.btn-add-group').attr('data-id', id);
                $('#edit-modal').modal('show');
            }
        );
    })

    $(document).on('click', '.btn-group-remove', function(){
        var id = $(this).attr("data-id");

        $('.btn-remove-group-confirm').attr('data-id', id);

        $('#remove-group-modal').modal('show');
    });

    $('.btn-remove-group-confirm').on('click' , function(){
        var id = parseInt($(this).attr("data-id"));

        $.post('/api/admin/group_permission/remove',
            {
                id: id,
            },
            function(data, status) {
                if (data) {
                    $('#remove-group-modal').modal('hide');
                    location.reload();
                }
            }
        );
    });
});

function resetModal() {
    $('.btn-success').attr("data-id", 0);
    $('#txtname').val("");
    $('.btn-primar').attr("title", "None selected");
    $('.multiselect-selected-text').text("None selected");
    $('.multiselect-container li').removeClass("active");
    $('option:selected', '.multiselect').prop("selected", false)
    $('.ace').prop("checked", false);
}

function blockButton(btn, block) {
    if (block) {
        if (!btn.hasClass('disable')) {
            btn.addClass('disable');
        }
    } else {
        if (btn.hasClass('disable')) {
            btn.removeClass('disable');
        }
    }

    btn.prop('disabled', btn.hasClass('disable'));
}