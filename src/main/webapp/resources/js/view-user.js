$(document).ready(function() {
	displayDataList();
});

function displayDataList() {
	var root = $("#tbl-body");
	list.forEach((item, index) => {
		// Data to Display
		var dataRow = "<tr>"
			+ '<td class="text-left">' + (index + 1) + '</td>'
			+ '<td class="text-left">' + item.name + "</td>"
			+ '<td class="text-left">' + item.email + "</td>"
			+ '<td class="text-left" style="word-break: break-all;"><p style="margin: 0;">' + item.address + "</p></td>";
		// Controls
		dataRow += '<td><button class="btn btn-primary w-100" type="button" data-toggle="modal" data-target="#editUserModal" onclick="openEditForm(' + index + ')">Edit</button></td>'
			+ '<td><button class="btn btn-danger w-100" type="button" data-toggle="modal" data-target="#confirmModal" onclick="deleteUser(' + index + ')">Delete</button></td>';
		////
		dataRow += "</tr>";
		root.append(dataRow);
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
}

function deleteUser(index) {
	var id = list[index].id;
	$("#btnConfirm").off("click").click(function() {		
		$("#txtId").val(id);
    	$('#userEditForm [formaction="delete"]').click();
		$("#confirmModal").modal("hide");	
	});
}


