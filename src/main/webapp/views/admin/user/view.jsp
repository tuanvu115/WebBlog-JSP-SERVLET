<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Xem thong tin user </title>
    </head>
    <body>
        <div class="main-content">
            <div class="main-content-inner">
                <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="ace-icon fa fa-home home-icon"></i>
                            <a href="<c:url value='/trang-chu'/>">Trang chủ</a>
                        </li>
                        </ul><!-- /.breadcrumb -->
                    </div>
                    <div class="page-content">
                        <div class="row" >
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label for="usr">Username</label>
                                    <input type="text" class="form-control" id="username" name="username" value="${model.username}" disabled>
                                </div>

                                <div class="form-group">
                                    <label for="usr">Fullname</label>
                                    <input type="text" class="form-control" id="fullname" name="fullname" value="${model.fullname}" disabled>
                                </div>

                                <div class="form-group">
                                    <label for="usr">Password</label>
                                    <input type="text" class="form-control" id="password" name="password" value="${model.password}" disabled>
                                </div>

                                <div class="form-group">
                                    <label for="usr">Status</label>
                                    <input type="text" class="form-control" id="status" name="status" value="${model.status}" disabled>
                                </div>

                                <div class="form-group">
                                    <label for="usr">Role</label>
                                    <input type="text" class="form-control" id="roleId" name="roleId" value="${model.role.name}" disabled>
                                </div>

                                
                            </div>
                        </div>
                    </div>
                </div>
                </div><!-- /.main-content -->
            </body>
        </html>