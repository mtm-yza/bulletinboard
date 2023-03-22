<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Post Display Table -->
<table class="table" col="4">
  <thead>
    <th>Title</th>
    <th>Description</th>
    <th colspan="3">Controls</th>
  </thead>
  <tbody id="postListBody"></tbody>
</table>
<!-- Add Post Form -->
<div class="modal fade" id="editPostModal" tabindex="-1" role="dialog"
  aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Add Post Form</h5>
        <button type="button" class="close" data-dismiss="modal"
          aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="postForm" method="post">
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
            <button class="btn btn-primary" type="submit" formaction="update">Update</button>
            <button class="btn btn-primary" type="submit" formaction="delete" hidden>Delete</button>
            <button class="btn btn-primary" type="button">Cancel</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<!-- Data -->
<script>
    var list = ${ posts }
</script>
