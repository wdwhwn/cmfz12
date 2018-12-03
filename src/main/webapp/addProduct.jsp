<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
<head>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/datagrid-detailview.js"></script>
	<script type="text/javascript" src="js/jquery.edatagrid.js"></script>
	<script src="js/echarts.min.js"></script>
	<!-- 需要引入两个css文件：图标样式文件，easyUI样式文件 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/themes/default/easyui.css">
	
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<script type="text/javascript">
        $(function(){
		$("#dianji").click(function () {
                $("#form_update").form("submit", {
                    url: "${pageContext.request.contextPath}/lucene/save",
                });
            })
        })
	</script>
</head>
<body>

    <h1>添加商品</h1>
    <form action="${pageContext.request.contextPath}/lucene/save" id="form_update" method="post" enctype="multipart/form-data" >
		商品名称:<input type="text" name="name"><br/>
		商品价格:<input type="text" name="price"><br/>
		商品描述:<input type="text" name="desc"><br/>
		商品图片:<input type="file" name="pic"><br/>
		商品状态:<input type="text" name="status"><br/>
		商品上产日期:<input type="text" name="date"><br/>
		商品产地:<input type="text" name="location"><br/>
				<input type="button" id="dianji" value="提交"><br/>
    </form>
</body>
</html>
