
//判断文件是不是图片，返回TRUE
function isImageType(str) {
    // toLowerCase() 将字符串转换为小写，返回一个新的字符串
    return ['png', 'jpg', 'jpeg', 'bmp', 'gif', 'webp', 'psd', 'svg', 'tiff'].indexOf(str.toLowerCase()) !== -1
}

web_status = {
    SUCCESS: 0,
    FAIL: 500,
    WARNING: 301
};

//上传文件，并预览图片
// 上传文件
function sendFile(file, imgs, imgvalue,filetype,taskId, imgsize) {
    var data = new FormData();
    data.append("file", file);
    data.append("filetype", filetype);
    data.append("taskId", taskId);


    $.ajax({
        type: "POST",
        url: "/gis/schoolpic/common/upload",
        data: data,
        cache: false,
        contentType: false,
        processData: false,
        dataType: 'json',
        success: function (result) {
            if (result.code == 200) {
                document.getElementsByTagName("img")[imgs+1].src = result.base64
                document.getElementById(imgvalue).value = result.url

                if (imgsize != null) {
                    document.getElementById(imgsize).value = result.bytes
                }
            } else {
               alert(result.msg);
            }
        },
        error: function (error) {
            $.modal.alertWarning("图片上传失败。");
        }
    });
}

//图片点击事件，触发input上传文件
function ono(files) {
    document.getElementById(files).click()
}

//删除当前照片
function delimg(imgs, img) {
    document.getElementsByTagName("img")[imgs].src = '/img/phone2.png'
    document.getElementById(img).value = '0'
}

function imgfd(i){
    var currImg = $('.amplifyImg'+i+' img');
    coverLayer(1);
    var tempContainer = $('<div class=tempContainer></div>');//图片容器
    with(tempContainer){//width方法等同于$(this)
        appendTo("body");

        var windowHeight=$(window).height();
        var windowWidth=$(window).width();

        if(windowHeight*0.75>windowWidth){
            windowHeight=windowWidth*1.33
        }

        //获取图片原始宽度、高度
        var orignImg = new Image();
        orignImg.src =currImg.attr("src") ;
        var currImgWidth= orignImg.width;
        var currImgHeight = orignImg.height;
        css('top',0);
        html('<img border=0 src=\"' + currImg.attr('src') + '\" style=\"height:'+windowHeight+'px;width:'+windowWidth+'px;\">');

    }
    tempContainer.click(function(){
        $(this).remove();
        coverLayer(0);
    });
}
//使用禁用蒙层效果
function coverLayer(tag){
    with($('.over')){
        if(tag==1){
            css('height',$(document).height());
            css('display','block');
            css('opacity',1);
            css("background-color","#FFFFFF");
            css("background-color","rgba(1,1,1,1)" );  //蒙层透明度
        }
        else{
            css('display','none');
        }
    }
}

//获取的是一个File列表，由于目前只上传一个文件，所以取第一个就可以了
//ng-版本
function fileChange(imgs, files, imgvalue,filetype,taskId, imgsize) {
    var maxFileSize = 1024 * 1024

    var file = document.getElementById(files).files[0];
    if (file.size > maxFileSize) {
        layer.msg("文件大小不得大于" + (maxFileSize / 1024) + "KB", {icon: 0, time: 2000})
        return
    }
    var index = file.name.lastIndexOf('.') // 获取指定字符串最后一次出现的位置，返回index
    var str = file.name.substr(index + 1)
    if (isImageType(str)) {
        sendFile(file, imgs, imgvalue,filetype,taskId, imgsize)
    } else {
        layer.msg("请选择图片！", {icon: 0, time: 1500})
    }
}




var schoolMes
getSchool()
function getSchool() {
    $.ajax({
        async: false,
        url: "/gis/schoolgis/getSchool",
        type: "POST",
        success: function (data) {
            schoolMes=data
        }
    })
}

//var xi = 0;
function settp(renwuId,xid, xname,img01,img02) {
    if(img01==0){
        img01="/img/phone2.png"
    }
    if(img02==0){
        img02="/img/phone2.png"
    }
    var ptop = "38px"
    var xi = 0;
    document.getElementById(xid).innerHTML = "   <div >" + xname + "（可上传）：<button onclick='savePic(\""+renwuId+"\")'" +
        "style='background-color: #00B4AA;color: white;border:none;border-radius: 5px'>提交更新图片</button></div>\n" +
        "                    <div >\n" +
        "                        <div style=\"width: 80%; \">\n" +
        "                            <img src=\""+img01+"\"  style='width: 160px;height: 120px' id=\"" + xid + "img01\" onclick=\"ono('" + xid + "file1')\" >\n" +
        "                            <input hidden style=\"display:none\" type=\"file\" multiple=\"multiple\" id=\"" + xid + "file1\" name=\"" + xid + "file1\" onchange=\"fileChange(" + xi + ",'" + xid + "file1','" + xid + "01','shbtg','"+renwuId+"')\">\n" +
        "                            <input id=\"" + xid + "01\" value=\""+img01+"\" hidden=\"\">\n" +
        "                            <a style=\"color: red;padding-left: 10px\" onclick=\"delimg(" + (xi + 1) + ",'" + xid + "01')\">删除</a>\n" +
        "                            <img src=\""+img02+"\" style='width: 160px;height: 120px' id=\"" + xid + "img02\" onclick=\"ono('" + xid + "file2')\">\n" +
        "                            <input hidden style=\"display:none\"  type=\"file\" multiple=\"multiple\" id=\"" + xid + "file2\" name=\"" + xid + "file2\" onchange=\"fileChange(" + (xi + 1) + ",'" + xid + "file2','" + xid + "02','shbtg','"+renwuId+"')\">\n" +
        "                            <input id=\"" + xid + "02\" value=\""+img02+"\" hidden=\"\">\n" +
        "                            <a style=\"color: red;padding-left: 10px\" onclick=\"delimg(" + (xi + 2) + ",'" + xid + "02')\">删除</a>\n" +
        "                        </div>\n" +
        // "                        <div style=\"width: 80%; \">\n" +
        // "                            <a style=\"color: red;padding-left: 10px\" onclick=\"delimg(" + (xi + 1) + ",'" + xid + "01')\">删除</a>\n" +
        // "                            <a style=\"color: red;padding-left: 20px\"  onclick=\"delimg(" + (xi + 2) + ",'" + xid + "02')\">删除</a>\n" +
        // "                        </div>\n" +
        "                    </div>"
   // xi = xi + 2;
}

function savePic(gisId) {
    var xxtp01=document.getElementById("xxtp01").value
    var xxtp02=document.getElementById("xxtp02").value
    $.ajax({
        async: false,
        url: "/gis/schoolgis/edit",
        type: "POST",
        data:{"id":gisId,"picture":xxtp01+","+xxtp02},
        success: function (data) {
            if(data.code==200){
                for(var i=0;i<schoollistAll.length;i++){
                    if(schoollistAll[i].id==gisId){
                        console.log(2222)
                        schoollistAll[i].picture=xxtp01+","+xxtp02
                    }
                }
                for(var i=0;i<schoollistnow.length;i++){
                    if(schoollistnow[i].id==gisId){
                        console.log(1111)
                        schoollistnow[i].picture=xxtp01+","+xxtp02
                    }
                }
                layer.msg("学校图片更新成功",{icon:1,time:1500})
            }else {
                layer.msg("学校图片更新失败，请联系管理员处理?",{icon:2,time:1500})
            }

        }
    })

}