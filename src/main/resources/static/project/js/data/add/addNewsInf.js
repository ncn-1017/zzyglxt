(function () {
    require(['jquery','wangEditor','ajaxUtil','alertUtil','stringUtil','fileUtil'],
        function (jquery,wangEditor,ajaxUtil,alertUtil,stringUtil,fileUtil) {
            const editor = new wangEditor('#div1')
            // 或者 const editor = new E( document.getElementById('div1') )
            //菜单配置
            editor.config.menus = [
                'head',
                'bold',
                'fontSize',
                'fontName',
                'italic',
                'underline',
                'strikeThrough',
                'indent',
                'lineHeight',
                'foreColor',
                'backColor',
                'link',
                'list',
                'justify',
                'image',
                'table',
                'splitLine',
                'undo',
                'redo',

            ]
            //取消粘贴后的样式
            editor.config.pasteFilterStyle = false
            //不粘贴图片
            editor.config.pasteIgnoreImg = true
            //隐藏上传网络图片
            editor.config.showLinkImg = false
            editor.config.uploadImgShowBase64 = true
            editor.create()
            editor.txt.html('<p></p>')

            $("#div1").on("input propertychange", function() {
                var textNUm=editor.txt.text()
                if(textNUm.length>=100000){
                    str=textNUm.substring(0,10000)+"";  //使用字符串截取，获取前30个字符，多余的字符使用“......”代替
                    editor.txt.html(str);
                    alert("字数不能超过10000");                  //将替换的值赋值给当前对象
                }
            });

            $("#cancelbtn").unbind().on('click',function () {
                var url = "/data/dataNewsInf";
                orange.redirect(url);
            });


            $("#submitbtn").unbind().on('click',function () {
                var newsInfEntity;
                var addUpdateUrl;
                var operateMessage;
                if(!isUpdate()){
                    addUpdateUrl = "/datado/newsInf/insertNewsInf";
                    operateMessage = "新增新闻信息成功";
                    newsInfEntity = {
                        itemcode: stringUtil.getUUID(),
                        dataTitle : $("#dataTitle").val(),
                        dataAuthor : $("#dataAuthor").val(),
                        dataSource : $("#dataSource").val(),
                        dataFileType : $("#dataFileType").val(),
                        dataContent : editor.txt.html()
                    };
                }else{
                    var needData = JSON.parse(localStorage.getItem("rowData"));
                    addUpdateUrl = "/datado/newsInf/updateNewsInf";
                    newsInfEntity = {
                        itemid: needData.itemid,
                        itemcode: needData.itemcode,
                        dataTitle : $("#dataTitle").val(),
                        dataAuthor : $("#dataAuthor").val(),
                        dataSource : $("#dataSource").val(),
                        dataFileType : $("#dataFileType").val(),
                        dataContent : editor.txt.html()
                    };
                    operateMessage = "更新新闻信息成功";
                }

                ajaxUtil.myAjax(null,addUpdateUrl,newsInfEntity,function (data) {
                    if(ajaxUtil.success(data)){
                        alertUtil.info(operateMessage);
                        var url = "/data/dataNewsInf";
                        orange.redirect(url);
                    }else {
                        alertUtil.alert(data.msg);
                    }
                },false,true);

            });


            (function init() {
                if (isUpdate()){
                    var tempdata = JSON.parse(localStorage.getItem("rowData"));
                    $("#dataTitle").val(tempdata.dataTitle);
                    $("#dataAuthor").val(tempdata.dataAuthor);
                    $("#dataSource").val(tempdata.dataSource);
                    $("#dataFileType").val(tempdata.dataFileType);
                    editor.txt.html(tempdata.dataContent);
                }
            }());

            function isUpdate() {
                return (localStorage.getItem("rowData") != null || localStorage.getItem("rowData") != undefined)
            }

        })
})();
