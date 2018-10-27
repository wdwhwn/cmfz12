<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>持名法州主页</title>
<link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">   
<link rel="stylesheet" type="text/css" href="../themes/icon.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../js/datagrid-detailview.js"></script>
<script type="text/javascript" src="../js/jquery.edatagrid.js"></script>
    <!-- 需要引入两个css文件：图标样式文件，easyUI样式文件 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/themes/default/easyui.css">
<script type="text/javascript">
	<!--菜单处理-->
        $(function(){
            $.ajax({
                url: "${pageContext.request.contextPath}/menu/selectAll",
                /*不让jQuery对数组进行转换  */
                traditional: true,
                dateType: "JSON",
                type: "get",
                success: function (data) {
//                    一级类别遍历  第一参数为从controller接收的list数据
//                    index为list集合的下标，first用来存储list集合中的对象
                    $.each(data,function(index,first){
                            var c=" ";
//                            对二级类别进行遍历， 将结果进行拼接
                            $.each(first.list, function(index1,second){
                                    c+="<div style='text-align:center'><a href='#' class='easyui-linkbutton' data-options=\"plain:true,iconCls:'"+second.iconCls+"'\" onclick=\"openTab('"+second.title+"','"+second.url+"','"+second.iconCls+"')\">"+second.title+"</a><div>";
                            })
//                        给手风琴添加选项卡，选项卡的内容为二级类别
                        $('#aa').accordion('add', {
                            title: first.title,
                            content: c,
                            iconCls:first.iconCls,
                            selected: false,
                        });
                    })

                    }

            })
          /*  $("#myTree").tree({
                url: "selectAll.do",
                onClick: function (node) {
                    /!* alert("dianji"); *!/
                    openTab(node);
                }
            });*/
        });
    /*点击 打开一个tab  */
    function openTab(stitle, surl, siconCls) {
        console.log(surl)
        /*判断这个页签是否存在   text是页签的标题  */
        var f = $("#tt").tabs("exists", stitle);
        if (f == true) {
            $("#tt").tabs("select", stitle);
        } else {
            $("#tt").tabs("add", {
                title: stitle,
                selected: true,
                iconCls: siconCls,
                href: "${pageContext.request.contextPath}/"+surl  ,
                closable:true

        }) ;
        }
    }

</script>

</head>
<body class="easyui-layout">   
    <div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px" >持名法州后台管理系统</div>
    	<div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:${sessionScope.name} &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a></div>
    </div>
    console.log(${sessionScope.name})
    <div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" >&copy;百知教育 htf@zparkhr.com.cn</div>
    </div>
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">

        <div id="aa" class="easyui-accordion" data-options="fit:true"></div>

    </div>
    <div data-options="region:'center'">
    	<div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">   
		    <div title="主页" data-options="iconCls:'icon-neighbourhood',"  style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
		</div>  
    </div>   
</body> 
</html>