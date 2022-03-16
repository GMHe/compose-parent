<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<script type="text/javascript" src="/webjars/jquery/1.11.3/jquery.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/webjars/github-com-sentsin-layer/3.0.3/src/layer.js"></script>
<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.css">
<body style="background-color: #f5f5f5;">
<#include "hearder.ftl"/>

<div class="form-group-in" style="padding-left: 35%;padding-right: 35%;padding-top: 5%;">
        <label style="font-size: 30px;">扫码登录</label>
    <form role="form">
        <table>
            <tr>
                <td>
                    <div class="qrCodeImg-box" id="qrImgDiv"></div>
                </td>
            </tr>
            <br>
            <tr style="margin-top: 20px;">
            </tr>
            <tr>
                <td align="center">
                    <button value="刷新二维码" type="button" onclick="initQrImg()" class="btn btn-primary">刷新二维码</button>
                </td>
            </tr>
            <td align="center">
                <button value="模拟扫描二维码" type="button" onclick="initLogin()" class="btn btn-primary">模拟扫描二维码</button>
            </td>
        </table>
    </form>
</div>
</body>
<script>
    window.onload = function () {
        initQrImg();
    }

    function initLogin(){
        //initQrImg();
        var uuid = window.sessionStorage.getItem("uuid");
        initWebSocket(uuid);
    }

    function initQrImg() {
        $("#qrImgDiv").empty();

        var xmlhttp;
        xmlhttp = new XMLHttpRequest();
        xmlhttp.open("GET", getQrPath, true);
        xmlhttp.responseType = "blob";
        xmlhttp.onload = function () {
            var headers = xmlhttp.getAllResponseHeaders();
            //console.log(headers)
            if (this.status == 200) {
                var blob = this.response;
                var img = document.createElement("img");
                img.className = 'qrCodeBox-img';
                img.onload = function (e) {
                    window.URL.revokeObjectURL(img.src);
                };
                img.src = window.URL.createObjectURL(blob);
                document.getElementById("qrImgDiv").appendChild(img);

                var uuid = this.getResponseHeader("uuid");
                window.sessionStorage.uuid = uuid;
                //initWebSocket(uuid);
            }
        }
        xmlhttp.send();
        //xmlhttp.abort();
    }


    var path = "://localhost:8581";
    var getQrPath = "http" + path + "/admin/img/getLoginQr";
    var wsPath = "ws" + path + "/websocket/";


    function initWebSocket(uuid) {

        if (typeof (WebSocket) == "undefined") {
            console.log("您的浏览器不支持WebSocket");
        } else {
            console.log("您的浏览器支持WebSocket");
            //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
            //等同于socket = new WebSocket("ws://localhost:8083/checkcentersys/websocket/20");
            var wsPathStr = wsPath + uuid;
            socket = new WebSocket(wsPathStr);
            //打开事件
            socket.onopen = function () {
                console.log("Socket 已打开");
                //socket.send("这是来自客户端的消息" + location.href + new Date());
            };
            //获得消息事件
            socket.onmessage = function (msg) {
                console.log(msg.data);
                var data = JSON.parse(msg.data);
                if (data.code == 200) {
                    //这里存放自己业务需要的数据。怎么放自己看
                    window.sessionStorage.userId = data.userId;
                    window.location.href = "admin/token/loginSuccess?uuid=" + uuid
                } else {
                    //如果过期了，关闭连接、重置连接、刷新二维码
                    socket.close();
                    initQrImg();
                }
                //发现消息进入    开始处理前端触发逻辑
            };
            //关闭事件
            socket.onclose = function () {
                console.log("Socket已关闭");
            };
            //发生了错误事件
            socket.onerror = function () {
                alert("Socket发生了错误");
                //此时可以尝试刷新页面
            }
        }

    }

</script>
<style>
    label {
        margin-bottom: 0px;
    }
</style>
</html>