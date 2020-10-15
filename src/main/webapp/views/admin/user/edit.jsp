<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-user"/>
<c:url var ="UserURL" value="/admin-user"/>
 <c:set var="pass" value="text"/>

 <c:choose>
         <c:when test = "${not empty model.id}">
            <c:if test= "${model.type == 'edit'}">
                 <c:set var = "Title" value="Chỉnh sửa tài khoản"/>
            </c:if>
           
            <c:if test = "${model.type == 'view'}">
                 <c:set var = "Title" value="Xem thông tin tài khoản"/>
            </c:if>
         </c:when>
         
         <c:when test = "${empty model.id}">
            <c:set var = "Title" value="Thêm tài khoản"/>
            <c:set var="pass" value="password"/>
         </c:when>

         
 </c:choose>



<html>
<head>
    <title>${Title}</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">${Title}</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                                <c:if test="${not empty model}">
                                    <div class="alert alert-<c:out value='${model.alert}'/>">
                                        <c:out value='${model.message}'/>
                                    </div>
                                </c:if>
                        <form id="formSubmit">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Role</label>
                                <div class="col-sm-9">  
                                    <select class="form-control" id="roleId" name="roleId">
                                        <c:if test="${empty model.roleId}">
                                            <option value="">Chọn Role</option>
                                            <c:forEach var="item" items="${roles}">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${not empty model.roleId}">
                                            <option value="">Chọn Role</option>
                                            <c:forEach var="item" items="${roles}">
                                                <option value="${item.id}" <c:if test="${item.id == model.roleId}">selected="selected"</c:if>>
                                                        ${item.name}
                                                </option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Username</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="username" name="username" value="${model.username}" }/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Fullname</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="fullname" name="fullname" value="${model.fullname}" }/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Password</label>
                                <div class="col-sm-9">
                                    <input type="${pass}" class="form-control" id="password" name="password" value="${model.password}" }/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Status</label>
                                <div class="col-sm-9">                                 
                                    <select id="status" name="status" class="form-control" }>
                                        <c:if test="${empty model.status}">
                                            <option>Chọn trạng thái</option>
                                            <option value="1">Hoạt Động</option>
                                            <option value="0">Vô hiệu hóa</option>
                                        </c:if>

                                        <c:if test="${not empty model.status}">
                                            <option>Chọn trạng thái</option>
                                            <option value="1" <c:if test="${model.status == 1}">selected="selected"</c:if> >Hoạt Động</option>

                                            <option value="0" <c:if test="${model.status == 0}">selected="selected"</c:if>>Vô Hiệu Hóa</option>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <c:if test="${not empty model.id}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật" id="btnAddOrUpdateNew" }/>
                                    </c:if>
                                    <c:if test="${empty model.id}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm " id="btnAddOrUpdateNew"/>
                                    </c:if>
                                </div>
                            </div>
                            <input type="hidden" value="${model.id}" id="id" name="id"/>
                        </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
	
    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        var data = {}; 
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
        var id = $('#id').val();
        if (id == "") {
            addNew(data);
        } else {
            updateNew(data);
        }
    });
    function addNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${UserURL}?type=list&page=1&maxPageItem=5&message=insert_success&alert=success";
            },
            error: function (error) {
            	window.location.href = "${UserURL}?type=list&maxPageItem=5&page=1&message=error_system&alert=success";
            }
        });
    }
    function updateNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${UserURL}?type=list&page=1&maxPageItem=5&message=insert_success&alert=success";
            },
            error: function (error) {
            	window.location.href = "${UserURL}?type=list&maxPageItem=5&page=1&message=error_system&alert=success";
            }
        });
    }


    var type = "";
    type = "${model.type}";
    if(type == "view"){
        $("input").prop('disabled', true);
        $("select").prop('disabled', true);
    }
</script>
</body>
</html>
