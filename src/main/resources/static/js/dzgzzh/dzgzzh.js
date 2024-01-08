function getData(i) {
    var value = window.location.search;

    value=decodeURIComponent(value);
    var vv0=value.split("&");
    var vv=vv0[0].split("=");
    value=vv[1]


    $.ajax({
        type: "post",
        url: "/dzgzzh/basedata/getGzzhBasedata",
        data: {'accessToken':value},
        success: function (result) {
            if(result.userid==null){
                document.getElementById("tishi1").hidden=false
                document.getElementById("tishi").innerHTML="您的信息尚未录入系统，</br>请联系系统管理员处理！"
                document.getElementById("uname1").hidden=true;
                document.getElementById("uname2").hidden=true;
                document.getElementById("uname3").hidden=true;
                document.getElementById("uname4").hidden=true;

                if(i==2){
                    document.getElementById("shuoming").style.display="none";
                    layer.open({
                        type:1,
                        area: ["250px", "300px"],    //范围大小
                        title: "提示",               //定义标题
                        content:"<div style='font-weight:bold;font-size: 15px;text-align:center;" +
                            "'><div style='padding: 5px 0'>请使用恩施州智慧教育app扫描</div>" +
                            "<div style='padding: 5px 0'>可在浏览器中打开此页面使用下载</div>"+
                        "<div style='padding: 5px 0'>浏览器环境下点击下载即可</div></div>",
                        btn: ['下载', '取消'],　　　　//按钮
                        yes: function (index, layero) { //确定按钮的处理函数
                            window.location.href="http://static.eszedu.com/download/EnShiApp-vrelease-2.2.3.266-20220113.apk"
                            layer.close(index);
                        }, cancel: function (index) {
                            layer.close(index);
                        }

                    })

                }


            }else {
                document.getElementById("uname").innerText=result.uname
                document.getElementById("org").innerText=result.org

                if(result.bumen!=null&&result.bumen!=""){
                    document.getElementById("bumen").innerText=result.bumen
                }else {
                    document.getElementById("uname3").hidden=true;
                }

                if(result.zhiwu!=null&&result.zhiwu!=""){
                    document.getElementById("zhiwu").innerText=result.zhiwu
                }else {
                    document.getElementById("uname4").style.display="none";
                }
                document.getElementById("img").src="\\img\\picture\\"+result.userid+".png"


            }


        }
    })
}


function seturl() {
    var value = window.location.search;
    value=decodeURIComponent(value);
    //value=value.substring(13)
    document.getElementById("qrcode").innerHTML = ""
    $.ajax({
        async: false,//设置同步
        type: "post",
        url: "/dzgzzh/basedata/getUrl",
        data: {'accessToken':value},
        success: function (url) {
            document.getElementById("text").value = "http://"+url+"/dzgzzh/dzgzzhjy"+value;
            set2WM();
        }
    });

}


function set2WM() {
    var qrcode = new QRCode(document.getElementById("qrcode"), {
        width: 100,
        height: 100
    });

    function makeCode() {
        var elText = document.getElementById("text");
        if (!elText.value) {
            alert("Input a text");
            elText.focus();
            return;
        }
        qrcode.makeCode(elText.value);
    }

    makeCode();
    $("#text").on("blur", function () {
        makeCode();
    }).on("keydown", function (e) {
        if (e.keyCode == 13) {
            makeCode();
        }
    });
}