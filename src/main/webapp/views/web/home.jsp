<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="i" value="0"/>
<c:url var="findOne" value='/view-new?type=view'/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trang chủ</title>
</head>
<body>
<div class="row">

        <div class="col-lg-3">

          <h1 class="my-4">Thể Loại</h1>
          <div class="list-group">
            <c:forEach var="item" items="${categories}">
              <a href="<c:url value='/the-loai?page=1&maxPageItem=6&categoryId=${item.id}'/>" class="list-group-item">${item.name}</a>
            </c:forEach>
            
          </div>

        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

          <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
            <ol class="carousel-indicators">
              <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
              <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
              <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>
           
              <div class="carousel-inner" role="listbox">
               <c:if test="${i < newTopInteractive.size()}">
                  <div class="carousel-item active">
                      <a href="<c:url value='/view-new?type=view&id=${newTopInteractive.get(i).id}'/>"> <img class="d-block img-fluid" style="width:900px;height:350px" src="${newTopInteractive.get(i).thumbnail}" alt="First slide"></a>
                  </div>
                  <c:set var="i" value="${i+1}"/>
                </c:if>

                <c:if test = "${i < newTopInteractive.size()}">
                  <div class="carousel-item">                   
                     <a href="<c:url value='/view-new?type=view&id=${newTopInteractive.get(i).id}'/>"> <img class="d-block img-fluid" style="width:900px;height:350px" src="${newTopInteractive.get(i).thumbnail}" alt="Second slide"></a>              
                 </div>
                <c:set var="i" value="${i+1}"/>
                </c:if>

                <c:if test = "${i < newTopInteractive.size()}">
                  <div class="carousel-item">
                     <a href="<c:url value='/view-new?type=view&id=${newTopInteractive.get(i).id}'/>"> <img class="d-block img-fluid" style="width:900px;height:350px" src="${newTopInteractive.get(i).thumbnail}" alt="Third slide"></a>
                  </div>

                </c:if>

              </div>
          
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>

          <div class="row">

            <c:forEach var="item" items="${newsTopSix}">
              
              
              
              <div class="col-lg-4 col-md-6 mb-4">
              <div class="card h-100">

                <c:if test="${not empty item.thumbnail}">
                  <a href="${findOne}&id=${item.id}"><img class="card-img-top" src="${item.thumbnail}" alt="" style="width: 168;height:253px"></a>
                </c:if>
                
                <c:if test="${empty item.thumbnail}">
                  <a href="#"><img class="card-img-top" src="" alt="http://placehold.it/700x400"></a>
                </c:if>

                <div class="card-body">
                  <h4 class="card-title">
                    <a href="${findOne}&id=${item.id}">${item.title}</a>
                  </h4>
                  <p class="card-text" >
                    <c:set var="str" value="${item.shortDescription}" />
                    <c:if test="${item.shortDescription.length() > 360}">
                      
                      <c:set var="str1" value="${fn:substring(str, 0, 360)}... " />
                    </c:if>

                    <c:if test="${item.shortDescription.length() <= 360}">
                      <c:set var="str1" value="${str}..." />
                    </c:if>
                   ${str1}
                  </p>
                </div>
                <div class="card-footer">
                  <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                </div>
              </div>
            </div>
            
            </c:forEach>

            

          </div>
          <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

      </div>
      <!-- /.row -->
</body>
</html>