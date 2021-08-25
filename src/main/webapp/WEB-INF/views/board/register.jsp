<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="../includes/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Board Register</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">

            <div class="panel-heading">Board Register</div>
            <!-- /.panel-heading -->
            <div class="panel-body">

                <form role="form" action="/board/register" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="form-group">
                        <label>Category</label>
                        <select class="form-control" name ="categoryId">
                            <option value="">카테고리 선택</option>
                            <option value="1">자유게시판</option>
                            <option value="2">회원게시판</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Title</label>
                        <input class="form-control" name='title'>
                    </div>

                    <div class="form-group">
                        <label>Text area</label>
                        <textarea class="form-control" rows="3" name='content'></textarea>
                    </div>

                    <div class="form-group">
                        <label>Writer</label>
                        <input class="form-control" name='writer'
                                value="<sec:authentication property="principal.username"/>"
                                readonly="readonly">
                    </div>
                    <button type="submit" class="btn btn-default">Submit
                        Button
                    </button>
                    <button type="reset" class="btn btn-default">Reset Button</button>
                    <button data-oper='list' class="btn btn-info">List</button>
                </form>

            </div>
            <!--  end panel-body -->

        </div>
        <!--  end panel-body -->
    </div>
    <!-- end panel -->
</div>
<!-- /.row -->

<form role='actionForm' id='actionForm' action="/board/list" method='get'>
    <input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
    <input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
    <input type='hidden' name='type' value='<c:out value="${ pageMaker.cri.type }"/>'>
    <input type='hidden' name='keyword' value='<c:out value="${ pageMaker.cri.keyword }"/>'>
</form>

<script type="text/javascript">

    $(document).ready(function () {

        /*$("#regist").click(function () {
            var Data = {
                title: $("#title").val(),
                content: $("#content").val(),
                writer: $("#writer").val()
            };
            commonAjax("post", "/board/register", Data)

        });*/

        var operForm = $("#actionForm");

        $("button[data-oper='list']").on("click", function (e) {
            //commonAjax("get", "/board/list")
            operForm.attr("action", "/board/list")
            operForm.submit();
        });

    });

</script>
<%@include file="../includes/footer.jsp" %>
