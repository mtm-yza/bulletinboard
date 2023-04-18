$(document).ready(function() {
	displayDataList();
	if (errors) {
		$('#editUserModal').modal('show');
	}
});
function displayDataList() {
	var root = $("#tbl-body");
	list.forEach((item, index) => {
		// Data to Display
		var dataRow = "<tr>"
			+ '<td class="text-left">' + (index + 1) + '</td>'
			+ '<td class="text-left">' + item.name + "</td>"
			+ '<td class="text-left">' + userRoles[item.role] + "</td>"
			+ '<td class="text-left">' + item.email + "</td>"
			+ '<td class="text-left" style="word-break: break-all;"><p style="margin: 0;">' + item.address + "</p></td>";
		// Controls
		dataRow += '<td><button class="btn btn-primary w-100" type="button" data-toggle="modal" data-target="#editUserModal" onclick="openEditForm(' + index + ')">Edit</button></td>'
			+ '<td><button class="btn btn-danger w-100" type="button" data-toggle="modal" data-target="#confirmModal" onclick="deleteUser(' + index + ')">Delete</button></td>';
		////
		dataRow += "</tr>";
		root.append(dataRow);
	})
	$('#pagination-container').pagination({
		className: "paginationjs-big",
		pageNumber: pageIndex,
		pageSize: pageSize,
		pageRange: 1,
		dataSource: Array.from({ length: totalCount }, (_, i) => i + 1),
		callback: function() {
			$('.paginationjs-pages ul li:not(.disabled)').each(function() {
				var btnLink = $(this).children('a');
				var link = 'list?page=' + $(this).attr('data-num');
				btnLink.attr("href", link);
				btnLink.click(function() { location.href = link; });
			});
		}
	})
}
function openEditForm(index) {
	setFormValues(index);
}
function setFormValues(index) {
	var user = list[index];
	$("#txtId").val(user.id);
	$("#txtName").val(user.name);
	$("#txtEmail").val(user.email);
	$("#txtAddress").val(user.address);
	$("#txtPassword").val(user.password);
	$("#rdoUser").prop("checked", user.role == 1);
	$("#rdoAdmin").prop("checked", user.role == 2);
}
function deleteUser(index) {
	var id = list[index].id;
	$("#btnConfirm").off("click").click(function() {
		$("#txtId").val(id);
		$('#userEditForm [formaction="delete"]').click();
		$("#confirmModal").modal("hide");
	});
}
