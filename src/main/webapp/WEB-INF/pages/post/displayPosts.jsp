<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Post Display Table -->
<table class="table table-bordered">
  <thead>
    <tr>
      <th class="text-center">No</th>
      <th class="w-25 text-center">Title</th>
      <th class="w-50 text-center">Description</th>
      <th class="text-center" colspan="3">Actions</th>
    </tr>
  </thead>
  <tbody id="postListBody"></tbody>
</table>
<!-- Edit Post Form -->
<div class="modal show" id="editPostModal" tabindex="-1" role="dialog"
  id="editPostModal" tabindex="-1" role="dialog"
  aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Edit Post Form</h5>
        <button type="button" class="close" data-dismiss="modal"
          aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form:form id="postEditForm" method="post" modelAttribute="post">
          <div class="form-group hidden">
            <form:label path="id">ID</form:label>
            <form:input id="txtId" class="form-control" type="number"
              path="id" placeholder="ID" />
          </div>
          <div class="form-group">
            <form:label path="title">Title</form:label>
            <form:input id="txtTitle" class="form-control" type="text"
              path="title" placeholder="Title" />
            <div class="label-error">
              <c:out value="${errors['title']}" />
            </div>
          </div>
          <div class="form-group">
            <form:label path="description">Description</form:label>
            <form:textarea id="txtDescription" class="form-control"
              path="description" rows="3"></form:textarea>
            <div class="label-error">
              <c:out value="${errors['description']}" />
            </div>
          </div>
          <div class="form-group">
            <form:input path="isActive" id="chkBxIsActive" type="hidden" />
            <input name="isStatusUpdate" id="chkBxIsStatusUpdate"
              type="hidden" value=false readonly />
          </div>
          <div class="container text-center">
            <button class="btn btn-primary" type="submit"
              formaction="update">Update</button>
            <button class="btn btn-primary hidden" type="submit"
              formaction="delete">Delete</button>
            <button class="btn btn-primary" type="button"
              data-dismiss="modal">Cancel</button>
          </div>
        </form:form>
      </div>
    </div>
  </div>
</div>
<!-- Controls For Pagination -->
<nav>
  <div id="pagination-container"
    class="pagination justify-content-center"></div>
</nav>
<!-- Confirm Form -->
<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
  aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Confirm</h5>
        <button type="button" class="close" data-dismiss="modal"
          aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">Are you sure?</div>
      <div class="modal-footer">
        <button id="btnConfirm" class="btn btn-primary" type="button">Confirm</button>
        <button class="btn" type="button" data-dismiss="modal">Cancel</button>
      </div>
    </div>
  </div>
</div>
<!-- Data -->
<script>
    var pageIndex = ${pageIndex}
    var pageSize = ${pageSize}
    var list = ${posts}
    var totalCount = ${totalCount}
    var errors = '${errors}'
</script>
