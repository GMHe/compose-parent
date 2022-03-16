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
    <form role="form">
        <table>
            <td colspan="2" align="center">
                <button value="确认登录" type="button" onclick="initWebSocket()" class="btn btn-primary">确认登录</button>
            </td>
        </table>
    </form>
</div>
</body>
<script>
    var path = "://localhost:8581";
    var wsPath =     "ws" + path + "/websocket/";

    function getQueryVariable(variable)
    {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return(false);
    }

    function initWebSocket(){
        var uuid = getQueryVariable("uuid");

        if(typeof(WebSocket) == "undefined") {
            console.log("您的浏览器不支持WebSocket");
        }else{
            console.log("您的浏览器支持WebSocket");
            //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
            //等同于socket = new WebSocket("ws://localhost:8083/checkcentersys/websocket/20");
            var wsPathStr = wsPath+uuid;
            socket = new WebSocket(wsPathStr);
            //打开事件
            socket.onopen = function() {
                console.log("Socket 已打开");
                //socket.send("这是来自客户端的消息" + location.href + new Date());
            };
            //获得消息事件
            socket.onmessage = function(msg) {
                console.log(msg.data);
                var data = JSON.parse(msg.data);
                if(data.code == 200){
                    //这里存放自己业务需要的数据。怎么放自己看
                    window.sessionStorage.uuid = uuid;
                    window.sessionStorage.userId = data.userId;
                    window.location.href = "admin/token/loginSuccess?email="+uuid
                }else{
                    //如果过期了，关闭连接、重置连接、刷新二维码
                    socket.close();
                    initQrImg();
                }
                //发现消息进入    开始处理前端触发逻辑
            };
            //关闭事件
            socket.onclose = function() {
                console.log("Socket已关闭");
            };
            //发生了错误事件
            socket.onerror = function() {
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