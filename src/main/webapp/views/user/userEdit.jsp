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

    <title>easyui datagrid</title>

<script type="text/javascript">
        var thisApp = {
            init: function () {
                $("#datagrid").edatagrid({
                    rownumbers: true,
                    singleSelect: true,
                    pagination: true,
                    fitColumns:true,
                    idField: 'id',
                    url: "<c:url value='/user/showAllUsers.json' />",//这是数据加载的地址,返回对应的json数据包就行,json包格式例子,见下4
                    pageSize: 10,
                    pageList: [10, 50, 100],
                    saveUrl: "<c:url value='/user/saveUser.action' />",        //新建,都是eadatagrid封装好的,把链接填好就行,点击之后会自动发送json数据包
                    updateUrl: "<c:url value='/user/updateSaveUser.action' />",   //updateUrl 对应 action 只能接收基本类型，如 updateSaveUser(String id, String name), 而无法updateUser(User user)
                    destroyUrl: "<c:url value='/user/delUser.action' />",      //删除
                    onSave: function (index, row) {
                        var $datagrid = $('#datagrid');
                        if ($datagrid.data('isSave')) {
                            //如果需要刷新，保存完后刷新
                            $datagrid.edatagrid('reload');
                            $datagrid.removeData('isSave');
                        }
                    },
                    toolbar: [{
                        text: '增加',
                        iconCls: 'icon-add',
                        handler: function () {
                            $('#datagrid').edatagrid('addRow');
                        }
                    },{
                        text: '保存',
                        iconCls: 'icon-cut',
                        handler: function () {
                            //标记需要刷新
                            $('#datagrid').data('isSave', true).edatagrid('saveRow');
                        }
                    }, '-', {
                        text: '删除',
                        iconCls: 'icon-save',
                        handler: function () {
                            $('#datagrid').edatagrid('destroyRow');
                        }
                    }]
                });
            }
        }


        $(function () {
            thisApp.init();
        })
    </script>
</head>
<body>
    <table id="datagrid">
        <thead>
            <tr>
                <th data-options="field:'id',width:80,align:'center'">Id</th>
                <th data-options="field:'name',width:100,align:'center',editor:'textbox'">姓名</th>
                <th data-options="field:'pwd',width:80,align:'center',editor:'textbox'">密码</th>
            </tr>
        </thead>
    </table>
</body>
</html>