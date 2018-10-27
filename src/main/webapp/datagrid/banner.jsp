<%@page isELIgnored="false" pageEncoding="utf-8" contentType="text/html; utf-8" %>
<script type="text/javascript">
    /* 缩写 */
    $(function () {
        /* 表格控件 */
        $("#menu").edatagrid({
            updateUrl: "${pageContext.request.contextPath}/banner/update",
            url: "${pageContext.request.contextPath}/banner/selectAllByPage",
            // 在底部显示分页工具栏
            pagination: true,
            columns: [[
                {field:"fxk",checkbox:true,width:100},
                {title: "名字", field: "name",width:100},
                {title: "状态", field: "status",width:100,editor: {
                    type: "text",
                    options: {
                        required: true
                    }
                }},
                {title: "路径", field: "url",width:100},
            ]],
//            充满窗口
            fit:true,
//              适应窗口
            fitColumns: true,
            /*顶部工具栏  */
            toolbar: [{
                    iconCls: 'icon-add',
                    text:"添加",
                    handler: function(){
                        $("#dd").dialog("open");
                    }
                },'-',{
                    iconCls: 'icon-edit',
                    text:"修改",
                    handler: function(){
//                        获取选择行对象
                        var row = $("#menu").edatagrid("getSelected");
                        if (row == null) {
                            alert("请先选中行")
                        } else {
//                            获取选择行的行号
                            var index = $("#menu").edatagrid("getRowIndex", row);
//                            指定行进入编辑模式
                            $("#menu").edatagrid("editRow", index);
                        }
                    }
        },'-',{
                iconCls: 'icon-remove',
                text:"删除",
                handler: function(){
                    var multidel=$("#menu").datagrid("getSelections");
                    $.messager.confirm("确认对话框", "您确定要删除么？", function(r){
                        if (r){
                            var ids=new Array(multidel.length);
                            for(var i=0;i<ids.length;i++){
                                ids[i]=multidel[i].id;
                                console.log(ids[i]);
                            }
                        }
                        $.ajax({
                            url:"${pageContext.request.contextPath}/banner/delete",
                            data:{"ids":ids},
                            traditional:true,
                            type:"post",
                            success:function(data){
                                if(data==true){
                                    console.log("123"+data);
                                    $.messager.show({
                                        title:"删除提示消息",
                                        msg:"删除成功。",
                                        timeout:5000,
                                        showType:"slide",
                                    });
                                    $("#menu").edatagrid("load");
                                }else{
                                    $.messager.show({
                                        title:"删除提示消息",
                                        msg:"删除失败。",
                                        timeout:5000,
                                        showType:"slide",
                                    });
                                }
                            }
                        })

                    });
                }
            },'-',{
                iconCls: 'icon-save',
                text:"保存",
                handler: function(){
//                    saveRow方法是调转到updateURL
                    $("#menu").edatagrid("saveRow");
                    $("#menu").datagrid("reload");
                }
            }],
//            数据表格展示
            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/upload/' + rowData.url + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>日期: ' + rowData.createdate + '</p>' +
                    '<p>描述: ' + rowData.description + '</p>' +
                    '<p>路径: ' + rowData.url + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
            /*双击修改事件  */
//            onDblClickRow: function (rowIndex, rowData) {
//                toUpdate(rowData);
//            }
        });
    });
        $('#dd').dialog({
            title: '添加',
            width: 250,
            height: 200,
            closed: true,
            buttons:"#bb"
        });
        $("#bc").linkbutton();
    function insert(){
        $("#add").form("submit", {
            url: "${pageContext.request.contextPath}/banner/insert",
            success: function (data) {
                if (data == "true") {
                    $.messager.show({
                        title: "添加提示消息",
                        msg: "添加成功。",
                        timeout: 5000,
                        showType: "slide",
                    });
                    $("#dd").dialog("close");
                    $("#menu").datagrid("reload");
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

</script>

<table id="menu"></table>

<%--添加弹出框--%>
<div id="dd">
    <form id="add" method="post" enctype="multipart/form-data">
        名字: <input name="name" /></br>
        日期：<input name="createdate"/></br>
        描述：<input name="description"/></br>
        图片:<input  type="file" name="multipartFile"/></br>
        <select id="status" class="easyui-combobox" name="status" style="width:200px;">
            <option value="0">展示</option>
            <option value="1">不展示</option>
        </select>
    </form>
</div>
<%--添加按钮--%>
<div id="bb" style="text-align:center">
    <a  id="bc"  onclick="insert()">添加</a>
</div>

