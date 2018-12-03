<%--<%@page isELIgnored="false" pageEncoding="utf-8" contentType="text/html; utf-8" %>
<script type="text/javascript">
    /* 缩写 */
    $('#user_table').datagrid({
        url:'${pageContext.request.contextPath}/user/selectAll',
        columns:[[
            {field:'dharmaName',title:'Code',width:100},
            {field:'name',title:'Name',width:100},
            {field:'sex',title:'Price',width:100,align:'right'},
            {field:'province',title:'Price',width:100,align:'right'},
            {field:'city',title:'Price',width:100,align:'right'},
            {field:'sign',title:'Price',width:100,align:'right'},
            {field:'phonenum',title:'Price',width:100,align:'right'},
            {field:'password',title:'Price',width:100,align:'right'},
            {field:'salt',title:'Price',width:100,align:'right'},
            {field:'status',title:'Price',width:100,align:'right'},
            {field:'regdate',title:'Price',width:100,align:'right'}
        ]],
        fit:true,
        fitcolumns:true
    });


</script>

<table id="user_table"></table>--%>

<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(function () {
        $("#user_btn").linkbutton({
            onClick: function () {
                var titles = $("#user_cc").combotree("getText")
                var fileds = $("#user_cc").combotree("getValues")
                console.log(fileds)
                var a="";
                $.each(fileds, function (index, filed) {
                    if (index==fileds.length-1){
                        a+=filed;
                    }else {
                        a+=filed+",";
                    }
                })
                console.log("13456"+a);
                $("#user_ff").form('submit',{
                    url:"${pageContext.request.contextPath}/user/customerExport",
                    queryParams:{
                        titles:titles,
                        fileds:a
                    }
                })
            }
        })
        $('#user_dg').datagrid({
            url: '${pageContext.request.contextPath}/user/selectAll',
            columns: [[
                {field:'dharmaName',title:'法号',width:100},
                {field:'name',title:'姓名',width:100},
                {field:'sex',title:'性别',width:100,align:'right'},
                {field:'headPic',title:'头像',width:100,align:'right'},
                {field:'location',title:'所在地',width:100,align:'right'},
                {field:'province',title:'省份',width:100,align:'right'},
                {field:'city',title:'城市',width:100,align:'right'},
                {field:'sign',title:'签名',width:100,align:'right'},
                {field:'phonenum',title:'电话号码',width:100,align:'right'},
                {field:'password',title:'密码',width:100,align:'right'},
                {field:'salt',title:'加密',width:100,align:'right'},
                {field:'status',title:'状态',width:100,align:'right'},
                {field:'regdate',title:'注册日期',width:100,align:'right'},
                {field:'guruId',title:'上师id',width:100,align:'right'}
            ]],
            pagination:true,
            fit: true,
            fitColumns: true,
            toolbar: [{
             text: "全部导出",
             iconCls: 'icon-edit',
             handler: function () {
               window.location.href="${pageContext.request.contextPath}/user/export";

             }
             }, '-', {
             text: "全部导入",
             iconCls: 'icon-help',
             handler: function () {
                    $("#user_ddd").dialog("open");
             }
             }, '-', {
             text: "自定义导出",
             iconCls: 'icon-help',
             handler: function () {
             $("#user_dd").dialog("open")
             }
             }]

        });

        $("#user_a").click(function(){
            $('#user_form').form('submit', {
                    url:"${pageContext.request.contextPath}/user/import",
                success:function(data){
                    $("#user_dg").datagrid("reload");
                }
            });
            $("#user_ddd").dialog("close")

        });
    })
</script>


<table id="user_dg"></table>

<div id="user_dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <div style="text-align: center">
      <select id="user_cc" class="easyui-combotree" style="width:200px;" data-options="required:true,checkbox:true,multiple:true,onlyLeafCheck:true,
    data:[{
		text: '请选择',
		state: 'closed',
		children: [
		{id:'dharmaName',text: '法号'},
		{id:'name',text:'姓名'},
		{id:'headPic',text:'头像'},
		{id:'sex',text: '性别'},
		{id:'location',text: '所在地'},
		{id:'province',text: '省份'},
		{id:'city',text: '城市'},
		{id:'bir',text: '签名'},
		{id:'guruId',text: '上师id'},
		{id:'sign',text: '编号'},
		{id:'phonenum',text:'电话号码'},
		{id:'password',text:'密码'},
		{id:'salt',text: '加密'},
		{id:'status',text: '状态'},
		{id:'regdate',text: '注册日期'}]
        }]">
        </select>
        <form id="user_ff" method="post">
            <a id="user_btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">确定</a>
        </form>
    </div>
</div>

<div id="user_ddd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="user_form" enctype="multipart/form-data"  method="post">
            <input type="file" name="file"/>
        <a href="Script:void(0)" id="user_a" class="easyui-linkbutton">提交</a>
    </form>
</div>

