<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="d-flex justify-content-between mb-3">
  <!-- SelectBox for Public or Private -->
  <form id="frmList" class="w-25 mb-3" method="post"
    action="/Demo/post/list">
    <select class="form-control"
      onChange="gotToList(this.selectedIndex)">
      <option <c:if test="${!isPrivate}">selected</c:if>>Public</option>
      <option <c:if test="${isPrivate}">selected</c:if>>My Post</option>
    </select> <input id="txtIsPrivate" type="hidden" name="isPrivate" /> <input
      type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  </form>
  <!-- Search Form -->
  <form id="frmSearch" class="w-25 ml-auto" method="get"
    <c:if test="${!isPrivate}">action="/Demo/post/list/search"</c:if>
    <c:if test="${isPrivate}">action="/Demo/post/me/search"</c:if>
    >
      <input class="form-control" type="text" name="postTitle"
        placeholder="Post Title" />
      <input class="form-control mt-2" type="text" name="authorName"
        placeholder="Author Name"
      <c:if test="${isPrivate}">hidden="true"</c:if>
      />
    <input class="btn btn-primary mt-2" type="submit" value="Search" />
  </form>
</div>
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
            <form:input path="userEmail" id="txtUserEmail" type="hidden" />
            <input name="isStatusUpdate" id="chkBxIsStatusUpdate"
              type="hidden" value=false readonly />
          </div>
          <div class="container text-center">
            <button class="btn btn-primary" type="submit"
              formaction="/Demo/post/update">Update</button>
            <button class="btn btn-primary hidden" type="submit"
              formaction="/Demo/post/delete">Delete</button>
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
<!-- Data -->
<c:set var="userName"><security:authentication property="principal.username"/></c:set>
<script>
	var username = new DOMParser().parseFromString('<!doctype html><body>'
            + "${userName}", 'text/html').body.textContent;
    var pageIndex = ${pageIndex}
    var pageSize = ${pageSize}
    var list = ${posts}
    var totalCount = ${totalCount}
    var isPrivate = ${isPrivate}
    var searchPostTitle = "<%=request.getParameter("postTitle")%>";
    searchPostTitle = (searchPostTitle != "null")? searchPostTitle: "";
    var searchAuthorName = "<%= request.getParameter("authorName")%>";
    searchAuthorName = (searchAuthorName != "null")? searchAuthorName: "";
    var errors = '${errors}'
</script>
