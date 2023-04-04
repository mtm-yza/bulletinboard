<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form method="post" modelAttribute="post">
  <div class="form-group">
    <form:errors path="title" />
    <form:label path="title">Title</form:label>
    <form:input class="form-control" type="text" path="title"
      placeholder="Title" />
    <div class="label-error">
      <c:out value="${errors['title']}" />
    </div>
  </div>
  <div class="form-group">
    <form:errors path="description" cssClass="error"></form:errors>
    <form:label path="description">Description</form:label>
    <form:textarea class="form-control" path="description" rows="3"
      placeholder="Description"></form:textarea>
    <div class="label-error">
      <c:out value="${errors['description']}" />
    </div>
  </div>
  <div class="form-group">
    <label>Status</label><br />
    <div class="form-check form-check-inline">
      <label class="form-check-label"> <form:radiobutton
          class="form-check-input" path="isActive" value="true"
          checked="true" />Public
      </label>
    </div>
    <div class="form-check form-check-inline">
      <label class="form-check-label"> <form:radiobutton
          class="form-check-input" path="isActive" value="false" />Private
      </label>
    </div>
  </div>
  <input class="btn btn-primary" formaction="add" type="submit"
    value="Submit">
</form:form>
