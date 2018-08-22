<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/easyui.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/icon.css'/>" />
<script type="text/javascript" src="<c:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.edatagrid.js'/>"></script>
<title>直接在表格上进行操作</title>
</head>
<body>
     <table id="dg" style="width: 100%;" 
           toolbar='#toolbar' resizable ="true" pagination="true" rownumbers="true" fit="true" fitColumns="true">
           <thead>
                <tr>
                <!-- easyui-datagrid 欄位寬度是根據每個欄位設定 width 的值在所有欄位設定width總和占比確定  -->
                     <th data-options="field:'id',width:20">ID</th>
                     <th data-options="field:'name',width:120" editor="{type:'validatebox',options:{required:true}}">User Name</th>
                     <th data-options="field:'pwd',width:120" editor="{type:'validatebox',options:{required:true}}">Password</th>
                </tr>
           </thead>
     </table>
     <div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dg').edatagrid('addRow')">新建</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#dg').edatagrid('destroyRow')">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg').edatagrid('saveRow')">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#dg').edatagrid('cancelRow')">取消<a>
	</div>
</body>
</html>
<script type="text/javascript">
     $(function() {
           //方式一.通過 EL 表達式取得 Json 數據。
           //var data = ${result};
           //$('#tdata').datagrid('loadData', data);
           
           //方式二. parseJSON 解析 Json 數據。
           //$('#dg').datagrid('loadData', $.parseJSON('${result}'));

            $('#dg').edatagrid({
                url: "<c:url value='/user/showAllUsers.json' />",
                saveUrl: "<c:url value='/user/saveUser.action' />",
                updateUrl: "<c:url value='/user/updateSaveUser.action' />",
                destroyUrl: "<c:url value='/user/delUser.action' />"
            });
        });
     
     <%-- 內嵌物件屬性值呈現 --%>
     function formatProvince(value, row, index) {
    	 if (row.address) {
				return row.address.province;
			}else{
				return val;
			}
     };
     
     function formatCity(value, row, index) {
    	 if (row.address) {
				return row.address.city;
			}else{
				return val;
			}
     };
</script>
