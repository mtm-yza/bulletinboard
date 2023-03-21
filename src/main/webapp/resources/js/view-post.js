$(document).ready(function() {
	displayPostList();
});

function displayPostList() {

	var root = $("#postListBody");
	list.forEach((item, index) => {
		var dataRow = "<tr>"
			+ '<td class="w-50">' + item.title + "</td>"
			+ '<td class="w-50">' + item.description + "</td>"
			+ '<td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#formAddPost" onclick=openEditForm("' + index + '")>Edit</button></td>'
			+ '<td><form method="POST" action="delete"><button type="submit" class="btn btn-danger" name="id" value='+ list[index].id + '>Delete</button></form></td>'
			+ "</tr>";
		root.append(dataRow);
	})
}

function openEditForm(index) {
	var post = list[index];
	$("#txtId").val(post.id);
	$("#txtTitle").val(post.title);
	$("#txtDescription").val(post.description);
	$("#chkBxStatus").val(post.status);
}
