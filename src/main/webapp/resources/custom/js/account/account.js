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
                    var data = {
                		pageIndex: page,
                		pageSize: pageSize
                    };
                    fis_vnp_js.fis_vnp().loadDataAll("/cms/api/admin/account/loaddata", "listtable", data);
                }
            }
        });
    }
});