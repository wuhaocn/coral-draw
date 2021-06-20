var total = 0;
var pageNumber = 1;
var pageSize = 18;

function setBlock(iddiv, idbtn) {
    document.getElementById(iddiv).style.display = "none";
    document.getElementById(idbtn).style.display = "";
}

function setEdit(iddiv, idbtn) {
    document.getElementById(iddiv).style.display = "";
    document.getElementById(idbtn).style.display = "none";
}

function shareDraw(uuid, ownerId) {
    var basePath = 'http://' + window.location.host;
    var previewUrl = 'http://' + window.location.host + '/index.html?lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&uuid=' + uuid;
    var editUrl = 'http://' + window.location.host + '/index.html?ownerId=' + ownerId + "&uuid=" + uuid;
    var referUrl = 'http://' + window.location.host + '/file/get/img/' + uuid
    var hContent = static_share.replace("preview_share_url", previewUrl).replace("edit_share_url", editUrl).replace("refer_share_url", referUrl)

    //分享
    layer.open({
        type: 1
        , title: '分享' //不显示标题栏
        , area: ['600px', '180px']
        , shade: 0.8
        , id: 'share_draw' //设定一个id，防止重复弹出
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
            if (pageNumber == 1) {
                layui.use(['laypage', 'layer'], function () {
                    var laypage = layui.laypage
                        , layer = layui.layer;

                    laypage.render({
                        elem: 'drawDataShow'
                        , count: total
                        , limit: 18
                        , layout: ['page', 'count']
                        , jump: function (obj) {
                            console.log(obj)
                            if (pageNumber != obj.curr) {
                                pageNumber = obj.curr
                                loadDrawData();
                            }

                        }
                    });

                });
            }

        }
    }
    var searchWord = window.parent.document.getElementById("searchKey").value;

    if (typeof searchWord == "undefined" || searchWord == null || searchWord == "") {
        xmlhttp.open("GET", "/file/list?pageSize=" + pageSize + "&pageNumber=" + pageNumber, true);
    } else {
        xmlhttp.open("GET", "/file/list?pageSize=" + pageSize + "&pageNumber=" + pageNumber  +"&word=" + searchWord, true);
    }

    xmlhttp.send();
}

function drawHubList(xmlhttp) {
    var jsonStr = xmlhttp.responseText;//获取到服务端返回的数据
    drawJson = JSON.parse(jsonStr).result;
    var tableData = "";
    total = drawJson.totalElements;
    console.log(drawJson)

    for (drawIndex in drawJson.content) {
        var wdiv = "ldtbdw" + drawIndex;
        var rdiv = "ldtbdr" + drawIndex;
        var uuid = drawJson.content[drawIndex].uuid;
        var ownerId = drawJson.content[drawIndex].ownerId;
        var updateTime = drawJson.content[drawIndex].updateTime;
        var dataName = drawJson.content[drawIndex].name;
        var editAble = window.parent.document.getElementById("editAble").value;
        var imgUrl = "/file/get/img/" + uuid;
        var cardData = static_cardshow.replace(/DRAW_TABLE_UUID/g, uuid);
        cardData = cardData.replace(/DRAW_TABLE_ID_DIV/g, wdiv);
        cardData = cardData.replace(/DRAW_TABLE_ID_BTN/g, rdiv);
        cardData = cardData.replace(/DRAW_TABLE_USERID/g, ownerId);
        cardData = cardData.replace(/DRAW_TABLE_NAME/g, dataName);
        cardData = cardData.replace(/DRAW_TABLE_TIME/g, updateTime);
        tableData += cardData;
    }
    document.getElementById("drawTable").innerHTML = tableData;

}


//static refer
var static_share = "<div class=\"layui-tab layui-tab-card\">\n" +
    "    <ul class=\"layui-tab-title\">\n" +
    "        <li class=\"layui-this\">预览地址</li>\n" +
    "        <li>编辑地址</li>\n" +
    "        <li>引用地址</li>\n" +
    "    </ul>\n" +
    "    <div class=\"layui-tab-content\" style=\"height: 100px;\">\n" +
    "        <div class=\"layui-tab-item layui-show\">preview_share_url</div>\n" +
    "        <div class=\"layui-tab-item\">edit_share_url</div>\n" +
    "        <div class=\"layui-tab-item\">refer_share_url</div>\n" +
    "    </div>\n" +
    "</div>";


var static_cardshow = " <div class=\"layui-col-md2\">\n" +
    "        <div class=\"layui-card\">\n" +
    "            <div class=\"layui-card-header\" style=\"text-align: center\"><b>DRAW_TABLE_NAME</b></div>\n" +
    "            <div class=\"layui-card-body\" style=\"text-align: center\">\n" +
    "                <img width=\"150px\" height=\"120px\" src=\"/file/get/img/DRAW_TABLE_UUID\">\n" +
    "            </div>\n" +
    "            <div style=\"text-align: center; font-size: 1px; color: #8f94a1\">DRAW_TABLE_TIME</div>\n" +
    "            <div id=\"DRAW_TABLE_ID_DIV\" onmouseleave=\"setBlock('DRAW_TABLE_ID_DIV', 'DRAW_TABLE_ID_BTN')\"\n" +
    "                 style=\"text-align: center; margin-top: 2px; display: none\">\n" +
    "                <button onclick=\"shareDraw('DRAW_TABLE_UUID','DRAW_TABLE_USERID')\"\n" +
    "                        type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">\n" +
    "                    <i class=\"layui-icon layui-icon-share\"></i>\n" +
    "                </button>\n" +
    "                <button onclick=\"editDraw('DRAW_TABLE_UUID','DRAW_TABLE_USERID')\"\n" +
    "                        type=\"button\" class=\"layui-btn layui-btn-normal layui-btn-sm\">\n" +
    "                    <i class=\"layui-icon layui-icon-edit\"></i>\n" +
    "                </button>\n" +
    "                <button onclick=\"deleteDraw('DRAW_TABLE_UUID','DRAW_TABLE_USERID')\"\n" +
    "                        type=\"button\" class=\"layui-btn layui-btn-danger layui-btn-sm\">\n" +
    "                    <i class=\"layui-icon layui-icon-delete\"></i>\n" +
    "                </button>\n" +
    "            </div>\n" +
    "            <div id=\"DRAW_TABLE_ID_BTN\" onclick=\"setEdit('DRAW_TABLE_ID_DIV', 'DRAW_TABLE_ID_BTN')\"\n" +
    "                 style=\"text-align: center; margin-top: 10px\">\n" +
    "                <button type=\"button\" class=\"layui-btn layui-btn-edit layui-btn-radius layui-btn-sm\">\n" +
    "                    <i class=\"layui-icon  layui-icon-edit\"></i>\n" +
    "                </button>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "    </div>";