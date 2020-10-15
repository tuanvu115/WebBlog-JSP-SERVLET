<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="<c:url value='/trang-chu'/>">Trang chu</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              
              <c:if test="${not empty USERMODEL}">
                
                <a class="nav-link" href="<c:url value='/new-list?type=list&page=1&&maxPageItem=3&sortName=title&sortBy=desc'/>">
                Quan ly bai viet
                <span class="sr-only">(current)</span>
                </a>

              </c:if>

            </li>
            <c:if test="${not empty USERMODEL}">
              <li class="nav-item">
                <a class="nav-link" href='<c:url value='/user-view?action=userInfo'/>'>Wellcome, ${USERMODEL.fullname}</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href='<c:url value="/thoat?action=logout"/>'>Thoat</a>
              </li>
            </c:if>
            <c:if test="${empty USERMODEL}">
              <li class="nav-item">
                <a class="nav-link" href='<c:url value="/dang-nhap?action=login"/>'>Dang nhap</a>
              </li>
            </c:if>
          </ul>
        </div>
      </div>
</nav>