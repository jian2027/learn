<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/easyui.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/icon.css'/>" />
<script type="text/javascript" src="<c:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/jquery.easyui.min.js'/>"></script>
<title>EasyUI User CRUD</title>
</head>
<body>
	<!-- 注意  table 不要設定 class="easyui-datagrid"， 否則會產生兩次請求  -->
	<table id="tdata"></table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search"
			plain="true" onclick="queryUser()">Query User</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newUser()">New User</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
			onclick="editUser()">Edit User</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
			plain="true" onclick="destroyUser()">Delete User</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload"
			plain="true" onclick="reload()">Reload</a>
	</div>
	<!-- 创建表单对话框 -->
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 180px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">编辑用户</div>
		<form id="fm" method="post">
			<div class="fitem">
				<label>ID ：</label>
				<input name="id" class="easyui-validatebox" readonly="readonly">
			</div>
			<div class="fitem">
				<label>用户名 ：</label>
				<input name="name" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>密码 ：</label>
				<input name="pwd" class="easyui-validatebox" required="true">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveUser()">Save</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
</body>
</html>
<script type="text/javascript">
	
<%-- easyui-datagrid 欄位寬度是根據每個欄位設定 width 的值在所有欄位設定width總和占比確定  --%>
	$('#tdata').datagrid({
		url : "<c:url value='/user/query.json'/>",
		toolbar : '#toolbar',
		fit : true,
		fitColumns : true,
		pagination : true,
		rownumbers : true,
		singleSelect : true,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'id',
			title : 'ID',
			width : 20
		}, {
			field : 'name',
			title : '用户名',
			width : 100
		}, {
			field : 'pwd',
			title : '密码',
			width : 100
		}, {
			field : 'province',
			title : 'Province',
			width : 100,
			formatter : function(val, row, index) {
				if (row.address) {
					return row.address.province;
				} else {
					return val;
				}
			},
		}, {
			field : 'city',
			title : 'City',
			width : 100,
			formatter : function(val, row, index) {
				if (row.address) {
					return row.address.city;
				} else {
					return val;
				}
			},
		}, ] ],
	});

	function queryUser() {
		$.ajax({
			url : "<c:url value='/user/query.json'/>",
			type : 'post',
			dataType : 'json',
			data : '',
			async : true, //异步  
			error : function() {
				alert('error');
			},
			success : function(jsonData) {
				$('#tdata').datagrid('loadData', {
					'total' : eval(jsonData).length,
					rows : jsonData
				});
			}
		});
	}

	//弹出添加用户的对话框
	function newUser() {
		$('#dlg').dialog('open').dialog('setTitle', 'Add User');
		$('#fm').form('clear');
		url = "<c:url value='/user/saveUser.action' />";
	}
	//保存的方法
	function saveUser() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
	 			if (result.success) {
	 				$('#dlg').dialog('close'); // close the dialog
					$('#tdata').datagrid('reload'); // reload the user data
					
				} else {
					$.messager.show({
						title : 'Error',
						msg : result.msg
					});
				}
			}
		});
	}

	//修改用户的窗口
	function editUser() {
		var row = $('#tdata').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit User');
			$('#fm').form('load', row);
			url = "<c:url value='/user/updateUser.action' />";
		} else {
			$.messager.alert({
				title : '系统提示',
				msg : '请选择需要修改的行'
			});
		}
	}

	//删除用户
	function destroyUser() {
		var row = $('#tdata').datagrid('getSelected');
		if (row) {
			$.messager.confirm('提示', '你确定要删除这个用户吗?', function(r) {
				if (r) {
					$.post("<c:url value='/user/delUser.action' />", {
						id : row.id
					}, function(result) {
						if (result.success) {
							$('#tdata').datagrid('reload'); // reload the user data
						} else {
							$.messager.show({ // show error message
								title : 'Error',
								msg : result.msg
							});
						}
					}, 'json');
				}
			});
		} else {
			$.messager.alert({
				title : '系统提示',
				msg : '请选择需要删除的行'
			});
		}
	}

	function reload() {
		//重新加载datagrid的数据 
		$("#tdata").datagrid('reload');
	}
</script>
