<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Post Display Table -->
<table class="table table-bordered" col="4">
  <thead>
    <th class="text-center">No</th>
    <th class="w-25 text-center">Title</th>
    <th class="w-50 text-center">Description</th>
    <th class="text-center" colspan="3">Actions</th>
  </thead>
  <tbody id="postListBody"></tbody>
</table>
<!-- Edit Post Form -->
<div class="modal fade" id="editPostModal" tabindex="-1" role="dialog"
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
        <form id="postEditForm" method="post">
          <c:if test="${errors != null }">
            <div class="alert alert-danger" role="alert">
              <h6>
                <c:out value="${msg}" />
              </h6>
              <c:forEach var="error" items="${errors}">
                <li><c:out value="${error.toString()}" /></li>
              </c:forEach>
            </div>
          </c:if>
          <div class="form-group" hidden>
            <label for="id">ID</label> <input id="txtId"
              class="form-control" type="number" name="id"
              placeholder="ID" required readonly>
          </div>
          <div class="form-group">
            <label for="title">Title</label> <input id="txtTitle"
              class="form-control" type="text" name="title"
              placeholder="Title">
          </div>
          <div class="form-group">
            <label for="description">Description</label>
            <textarea id="txtDescription" class="form-control"
              name="description" rows="3"></textarea>
          </div>
          <div class="form-group" readonly hidden>
            <select name="status" id="chkBxStatus">
              <option value="0" selected>Disable</option>
              <option value="1">Active</option>
            </select>
          </div>
          <div class="form-group" readonly hidden>
            <input id="txtFlag" class="form-control" type="text"
              name="flag">
          </div>
          <div class="container text-center">
            <button class="btn btn-primary" type="submit"
              formaction="update">Update</button>
            <button class="btn btn-primary" type="submit"
              formaction="delete" hidden>Delete</button>
            <button class="btn btn-primary" type="button"
              data-dismiss="modal">Cancel</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
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
    var list = ${ posts }
</script>
