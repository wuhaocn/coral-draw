function setEdit(rid, wid) {
    document.getElementById(rid).style.display = "none";
    document.getElementById(wid).style.display = "";
}

function setBlock(rid, wid) {
    document.getElementById(rid).style.display = "";
    document.getElementById(wid).style.display = "none";
}

function shareDraw(uuid, ownerId) {
    var basePath = window.location.host;
    var hContent = '<div style="padding: 50px; line-height: 22px; font-weight: 300;">' +
        '预览链接:<br> http://' + basePath + '/index.html?lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&uuid=' + uuid;

    var editAble = window.parent.document.getElementById("editAble").value;

    if (editAble) {
        hContent += '<br><br> 编辑链接:<br> http://' + basePath + '/index.html?ownerId=' + ownerId + "&uuid=" + uuid;
    }
    hContent += '<br><br> 图片引用:<br> http://' + basePath + '/file/get/img/' + uuid + '<br>';
    hContent += '</div>';

    console.log()
    //分享
    layer.open({
        type: 1
        , title: '分享' //不显示标题栏
        , area: ['666px', '399px']
        , shade: 0.8
        , id: 'share_draw' //设定一个id，防止重复弹出
        , btn: ['关闭']
        , moveType: 1 //拖拽模式，0或者1
        , content: hContent
    });
}

function deleteDraw(uuid, ownerId) {
    layer.open({
        type: 1 //此处以iframe举例
        , title: '删除'
        , shade: 0
        , content: '<div  style="text-align:center">删除提示</div>'
        , btn: ['是', '否'] //只是为了演示
        , yes: function () {
            var httpRequest = new XMLHttpRequest();//第一步：创建需要的对象
            httpRequest.open('POST', '/file/del/' + uuid, true); //第二步：打开连接
            httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");//设置请求头
            httpRequest.send('{key:delete}');//发送请求 将情头体写在send中
            /**
             * 获取数据后的处理程序
             */
            httpRequest.onreadystatechange = function () {//请求后的回调接口，可将请求成功后要执行的程序写在其中
                if (httpRequest.readyState == 4 && httpRequest.status == 200) {//验证请求是否发送成功
                    location.reload();
                }
            };
        }
        , btn2: function () {
            layer.closeAll();
        }
        , zIndex: layer.zIndex //重点1
        , success: function (layero) {
            layer.setTop(layero); //重点2
        }
    });

}

function editDraw(uuid, ownerId) {
    var urlOPen = "/index.html?ownerId=" + ownerId + "&uuid=" + uuid;
    window.open(urlOPen);                 //在另外新建窗口中打开窗口
}

function loadDrawData() {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            drawHubList(xmlhttp);
        }
    }
    xmlhttp.open("GET", "/file/list?page=1&limit=10", true);
    xmlhttp.send();
}

function drawHubList(xmlhttp) {
    var jsonStr = xmlhttp.responseText;//获取到服务端返回的数据
    drawJson = JSON.parse(jsonStr)
    var tableData = "";
    for (drawIndex in drawJson.data) {
        var wdiv = "ldtbdw" + drawIndex;
        var rdiv = "ldtbdr" + drawIndex;
        var uuid = drawJson.data[drawIndex].uuid;
        var ownerId = drawJson.data[drawIndex].ownerId;
        var editAble = window.parent.document.getElementById("editAble").value;
        var imgUrl = "/file/get/img/" + uuid;
        tableData += "            <div class=\"layui-col-md2\">\n" +
            "                <div class=\"layui-card\">\n" +
            "                    <div class=\"layui-card-header\" style=\"text-align: center\"><b>" +
            drawJson.data[drawIndex].name + "</b></div>\n" +
            "                    <div class=\"layui-card-body\" style=\"text-align: center\">\n" +
            "                        <img width=\"200px\" height=\"120px\"\n" +
            "                             src=\"" + imgUrl + "\">\n" +
            "                    </div>\n" +
            "                    <div id=\"" + wdiv + "\" onmouseleave=\"setBlock('" + rdiv + "', '" + wdiv + "')\" style=\"text-align: center; display: none\">\n" +
            "                        <button onclick=\"shareDraw('" + uuid + "','" + ownerId + "')\" type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">\n" +
            "                            <i class=\"layui-icon layui-icon-share\"></i>\n" +
            "                        </button>\n";

        if (editAble) {
            tableData += "           <button onclick=\"editDraw('" + uuid + "','" + ownerId + "')\"  type=\"button\" class=\"layui-btn layui-btn-normal layui-btn-sm\">\n" +
                "                            <i class=\"layui-icon layui-icon-edit\"></i>\n" +
                "                        </button>\n" +
                "                        <button onclick=\"deleteDraw('" + uuid + "','" + ownerId + "')\" type=\"button\" class=\"layui-btn layui-btn-danger layui-btn-sm\">\n" +
                "                            <i class=\"layui-icon layui-icon-delete\"></i>\n" +
                "                        </button>\n";
        }


        tableData += "          </div>\n" +
            "                    <div id=\"" + rdiv + "\" onclick=\"setEdit('" + rdiv + "', '" + wdiv + "')\" style=\"text-align: center\">\n" +
            "                        <button type=\"button\" class=\"layui-btn layui-btn-edit layui-btn-radius layui-btn-sm\">\n" +
            "                            <i class=\"layui-icon  layui-icon-edit\"></i>\n" +
            "                        </button>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>"
    }
    document.getElementById("drawTable").innerHTML = tableData;

}
