/**
 * 角色详情对话框
 */
var DeptInfoDlg = {
    data: {
        pid: "",
        pName: ""
    }
};

layui.use(['layer', 'form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/secondCategory/add", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });

    var $ = layui.jquery
        , upload = layui.upload
        , form = layui.form;
    $.get(Feng.ctxPath + '/category/getAll', {}, function (data) {
        var $html = "";
        console.log(data);
        if(data != null){
            console.log(data);
            $.each(data, function (index, item) {
                if (item.proType){
                    $html += "<option class='generate' value='" + item.id + "'>" + item.proType + "</option>";
                }else{
                    $html += "<option value='" + item.id + "'>" + item.name + "</option>";
                }
            });
            $("select[name='categoryId']").append($html);
            //反选
            /*$("select[name='categoryId']").val($("#???").val());*/
            //append后必须从新渲染
            form.render('select');
        }
    })

});