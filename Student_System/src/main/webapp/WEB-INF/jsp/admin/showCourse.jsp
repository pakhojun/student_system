<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>课程信息显示</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/theme/default/layer.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script src="/js/jquery-3.2.1.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/layer.js"></script>
	<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

</head>
<body>
	<!-- 顶栏 -->
	<jsp:include page="top.jsp"></jsp:include>
	<!-- 中间主体 -->
	<div class="container" id="content">
		<div class="row">
			<jsp:include page="menu.jsp"></jsp:include>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 class="col-md-5">课程名单管理</h1>
							<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="/course/findList" id="form1" method="post">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="请输入姓名" name="findByName" value="${findByName}">
									<span class="input-group-addon btn" onclick="document.getElementById('form1').submit" id="sub">搜索</span>
								</div>
							</form>
							<button class="btn btn-default col-md-2" style="margin-top: 20px" onClick="location.href='/course/addCourse'">
								添加课程信息
								<sapn class="glyphicon glyphicon-plus"/>
							</button>

						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
									<th>课程号</th>
									<th>课程名称</th>
									<th>授课老师编号</th>
									<th>上课时间</th>
									<th>上课地点</th>
									<th>周数</th>
									<th>课程类型</th>
									<th>学分</th>
									<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
							<c:forEach  items="${courseList}" var="item">
								<tr>
									<td>${item.courseid}</td>
									<td>${item.coursename}</td>
									<td>${item.teacherid}</td>
									<td>${item.coursetime}</td>
									<td>${item.classroom}</td>
									<td>${item.courseweek}</td>
									<td>${item.coursetype}</td>
									<td>${item.score}</td>
									<td>
										<button class="btn btn-default btn-xs btn-info" onClick="location.href='/course/editCourse?id=${item.courseid}'">修改</button>
										<button class="btn btn-default btn-xs btn-danger btn-primary" onclick="deleteCourse('${item.courseid}')">删除</button>
										<!--弹出框-->
									</td>
								</tr>
							</c:forEach>
					        </tbody>
				    </table>
				    <div class="panel-footer">
							<nav style="text-align: center">
								<ul class="pagination">
									<c:if test="${pageBean.currentPage==1}">
										<li ><a href="javaScript:void(0)">&laquo;上一页</a></li>
									</c:if>
									<c:if test="${pageBean.currentPage!=1}">
										<li><a href="/course/findList?currentPage=${pageBean.currentPage-1}&findByName=${findByName}">&laquo;上一页</a></li>
									</c:if>

									<c:forEach begin="1" end="${pageBean.totalPage}" var="item">
										<c:if test="${pageBean.currentPage==item}">
											<li class="active"><a href="javaScript:void(0)">${item}</a></li>
										</c:if>
										<c:if test="${pageBean.currentPage!=item}">
											<li ><a href="/course/findList?currentPage=${item}&findByName=${findByName}">${item}</a></li>
										</c:if>
									</c:forEach>


									<c:if test="${pageBean.currentPage==pageBean.totalPage}">
										<li><a href="javaScript:void(0)">下一页&raquo;</a></li>
									</c:if>
									<c:if test="${pageBean.currentPage!=pageBean.totalPage}">
										<li><a href="/course/findList?currentPage=${pageBean.currentPage+1}&findByName=${findByName}">下一页&raquo;</a></li>
									</c:if>

								</ul>
							</nav>
				    </div>
				</div>

			</div>
		</div>
	</div>
	<div class="container" id="footer">
		<div class="row">
			<div class="col-md-12"></div>
		</div>
	</div>
</body>
	<script type="text/javascript">
		<%--设置菜单中--%>
		$("#nav li:nth-child(1)").addClass("active")
        <c:if test="${pagingVO != null}">
        if (${pagingVO.curentPageNo} == ${pagingVO.totalCount}) {
            $(".pagination li:last-child").addClass("disabled")
        };

        if (${pagingVO.curentPageNo} == ${1}) {
            $(".pagination li:nth-child(1)").addClass("disabled")
        };
        </c:if>

        function confirmd() {
            var msg = "您真的确定要删除吗？！";
            if (confirm(msg)==true){
                return true;
            }else{
                return false;
            }
        }

        $("#sub").click(function () {
            $("#form1").submit();
        });


        function deleteCourse(id){
			layer.confirm('您是否要删除', {
				btn: ['是','否'] //按钮
			}, function(){
				$.get("/course/delete",
						{id:id },
						function(data){
							layer.alert(data.message);
							location.reload();
						});
			},function () {

			});

		}



	</script>
</html>