<%@page isELIgnored="false" pageEncoding="utf-8" contentType="text/html; utf-8" %>
<script type="text/javascript">
    /* 缩写 */
        $(function () {
            $('#cha_tt').treegrid({
                url:'${pageContext.request.contextPath}/album/selectAll',
                idField:'id',
                treeField:'name',
                onDblClickCell:function(rowIndex, rowData){
//                    console.log(rowData);
                    toStart(rowData);
                },
                columns:[[
                    {title:'名字',field:'name',width:180},
                    {field:'url',title:'下载路径',width:60,align:'right'},
                    {field:'size',title:'大小',width:80},
                    {field:'duration',title:'时间',width:80}
                ]],
                toolbar: [{
                    iconCls: 'icon-tip',
                    text:"专辑详情",
                    handler: function(){
                        $("#cha_dd1").dialog("open");
                        var l=$('#cha_tt').treegrid("getSelected");
//                        console.log(l.coverimg);
//                        console.log(l.publisdate);
                        var f=$('#cha_tt').treegrid("getLevel",l.id);
                       /* console.log(l);
                        console.log(f);*/
                        if(l !==null){
                            if(f==1){
                                $("#cha_add").form("load",l);
                                $("#cha_coverImg").prop("src","${pageContext.request.contextPath}/upload/"+l.coverimg);
                            }else{
//                                获取父节点
                                var pid=$('#cha_tt').treegrid("getParent",l.id);
//                                console.log(pid);
                                var p=$("#cha_tt").treegrid("find",pid.id);
//                                console.log(pid);
//                                console.log("VVVVVVVVVV"+p);
                                $("#cha_add").form("load",p);
                                $("#cha_coverImg").prop("src","${pageContext.request.contextPath}/upload/"+p.coverimg);
                            }
                        }
                    }
                },'-',{
                    iconCls: 'icon-add',
                    text:"添加专辑",
                    handler: function(){
                      $("#cha_dd2").dialog("open")

//
                    }
                },'-',{
                    iconCls: 'icon-save',
                    text:"添加章节",
                    handler: function(){

                        $.ajax({
                            url: "${pageContext.request.contextPath}/album/selectAll",
                            type: "post",
                            success: function (data) {
                               $.each(data,function(index,first){
                                  var id= first.id;
                                   var name=first.name;
//                                   console.log(id+"  "+name);
                                   $("#cha_status1").append('<option  value="'+id+'">'+name+'</option>');
                               })
                            }
                        })
                        $("#cha_dd3").dialog("open");

                    }
                },'-',{
                    iconCls: 'icon-save',
                    text:"下载音频",
                    handler: function(){
                        /*获取行对象*/
                        var l=$('#cha_tt').treegrid("getSelected");
                        /*获取行对象的级别*/
                        var f=$('#cha_tt').treegrid("getLevel",l.id);
//                        console.log(l);
//                        console.log(f);
                        if(f!=2){
                            alert("请选中章节");
                        }else{
                            window.location.href="${pageContext.request.contextPath}/chapter/download?url="+l.url+"&name="+l.name;
                            $('#cha_tt').treegrid("reload");
                            /*$.ajax({
                                url: "${pageContext.request.contextPath}/chapter/download",
                                data: {"url":l.url},
                                type: "post",
                                success: function (data) {
                                    console.log(data);
                                    if (data == true) {
                                        console.log("123" + data);
                                        $.messager.show({
                                            title: "下载提示消息",
                                            msg: "下载成功。下载路径为：D:/dowlaod",
                                            timeout: 5000,
                                            showType: "slide",
                                        });
                                        $("#myDatagrid").datagrid("load");
                                    } else {
                                        $.messager.show({
                                            title: "下载提示消息",
                                            msg: "下载失败。",
                                            timeout: 5000,
                                            showType: "slide",
                                        });
                                    }
                                }
                            })*/
                        }
                    }
                }],
                fit:true,
                fitColumns:true,

            });
            $('#cha_dd1').dialog({
                title: '查看专辑',
                width: 250,
                height: 200,
                closed: true,

            });

            $('#cha_dd2').dialog({
                title: '添加',
                width: 250,
                height: 200,
                closed: true,
                buttons:"#bb1"
            });
            $("#cha_bc1").linkbutton();
            $('#cha_dd3').dialog({
                title: '添加',
                width: 250,
                height: 200,
                closed: true,
                buttons:"#bb2"
            });
            $("#cha_bc2").linkbutton();
            $('#cha_dd4').dialog({
                title: '播放',
                width: 250,
                height: 200,
                closed: true,
            });
        })
        function insert(){
            $("#cha_add1").form("submit", {
                url: "${pageContext.request.contextPath}/album/insert",
                success: function (data) {
                    if (data == "true") {
                        $.messager.show({
                            title: "添加提示消息",
                            msg: "添加成功。",
                            timeout: 5000,
                            showType: "slide",
                        });
                        $("#cha_dd").dialog("close");
                        $("#cha_menu").datagrid("reload");
                        $('#cha_tt').treegrid("reload");
                    } else {
                        $.messager.show({
                            title: "添加提示消息",
                            msg: "添加失败。",
                            timeout: 5000,
                            showType: "slide",
                        });
                    }
                }
            });
        }
    function insert1(){
        $("#cha_add2").form("submit", {
            url: "${pageContext.request.contextPath}/chapter/insert",
            success: function (data) {
                if (data == "true") {
                    $.messager.show({
                        title: "添加提示消息",
                        msg: "添加成功。",
                        timeout: 5000,
                        showType: "slide",
                    });
                    $("#cha_dd").dialog("close");
                    $('#cha_tt').treegrid("reload");
                } else {
                    $.messager.show({
                        title: "添加提示消息",
                        msg: "添加失败。",
                        timeout: 5000,
                        showType: "slide",
                    });
                }
            }
        });
        $("#cha_dd3").dialog("close");

    }
    function toStart(rowData){
        console.log(rowData);
        $("#cha_dd4").dialog("open");
        $("#cha_audio").prop("src","${pageContext.request.contextPath}/audio/"+rowData.url);
        console.log( $("#cha_audio"));
    }


</script>
<table id="cha_tt" style="width:600px;height:400px"></table>
<%--查看弹出框--%>
<div id="cha_dd1">
    <form id="cha_add" method="post" enctype="multipart/form-data">
        名字: <input name="name" /></br>
        集数：<input name="count"/></br>
        分数:<input  name="score" /></br>
        作者：<input  name="author" /></br>
        播音：<input  name="broadcast" /></br>
        内容简介：<input  name="brief" /></br>
        发布日期：<input  name="publisdate" /></br>
        头像：<img src="" id="coverImg" name="coverimg"></br>
    </form>
</div>
<%--添加专辑--%>
<div id="cha_dd2">
    <form id="cha_add1" method="post" enctype="multipart/form-data">
        名字: <input name="name"  /></br>
        头像：<input type="file" name="multipartFile" />
        集数：<input name="count"/></br>
        分数:<input  name="score" /></br>
        作者：<input  name="author" /></br>
        播音：<input  name="broadcast" /></br>
        内容简介：<input  name="brief" /></br>
        发布日期：<input  name="publisdate" /></br>
    </form>
</div>
<%--添加按钮--%>
<div id="cha_bb1" style="text-align:center">
    <a  id="cha_bc1"  onclick="insert()">添加</a>
</div>


<%--添加章节--%>
<div id="cha_dd3">
    <form id="cha_add2" method="post" enctype="multipart/form-data">
        名字: <input class="easyui-validatebox" data-options="required:true" name="name"  /></br>
        音频：<input type="file" name="multipartFile" />
        一级类别：<select id="cha_status1" class="easyui-combobox" name="aid" style="width:200px;">

                 </select>
    </form>
</div>
<%--添加章节按钮--%>
<div id="cha_bb2" style="text-align:center">
    <a  id="cha_bc2"  onclick="insert1()">添加</a>
</div>

<%--双击播放--%>
<div id="cha_dd4">
    <audio id="cha_audio" src="" autoplay="autoplay" controls="controls" >132456</audio>
</div>