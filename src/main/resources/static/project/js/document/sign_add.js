(function () {
    require(['jquery','objectUtil','ajaxUtil','alertUtil','stringUtil','fileUtil','distpicker','datetimepicker','dictUtil','selectUtil','modalUtil'],
        function (jquery,objectUtil,ajaxUtil,alertUtil,stringUtil,fileUtil,distpicker,datetimepicker,dictUtil,selectUtil,modalUtil) {

            var type = isUpdate() ? "put": "post";

            var pl = dictUtil.getDictByCode(dictUtil.DICT_LIST.parment);
            $("#parment").selectUtil(pl);

            $("#cancel").unbind().on('click',function () {
                var url = "/document/sign";
                orange.redirect(url);
            });

            //不公开理由
            var reason = dictUtil.getDictByCode(dictUtil.DICT_LIST.postReason);
            $("#reason").selectUtil(reason);

            //公开方式
            $('input[type=radio][name=govPunlic]').change(function () {
                if(this.value == "2"){
                    $('#reason').attr('style',"display:block");
                }else {
                    $('#reason').attr('style',"display:none");
                    $("#reason").selectUtil(reason);
                    $("#reason").val() == "";
                }
            })

            $("#btn_save").unbind().on('click',function () {
                var ReceiptEntity;
                var addUpdateUrl;
                if(!isUpdate()){
                    addUpdateUrl = "governresCountersign/insert";
                    ReceiptEntity = {
                        itemcode: stringUtil.getUUID(),
                        govPunlic : $('input:radio[name="govPunlic"]:checked').val(),
                        receivingTitle : $("#receivingTitle").val(),
                        parment : $("#parment").val(),
                        fileNumber : $("#fileNumber").val(),
                        number : $("#number").val(),
                        classification : $("#classification").val(),
                        reason : $("#reason").val(),
                        status : '0',
                    };
                }else{
                    var needData = JSON.parse(localStorage.getItem("rowData"));
                    addUpdateUrl = "governresCountersign/update";
                    ReceiptEntity = {
                        itemid: needData.itemid,
                        itemcode: needData.itemcode,
                        govPunlic : $('input:radio[name="govPunlic"]:checked').val(),
                        receivingTitle : $("#receivingTitle").val(),
                        parment : $("#parment").val(),
                        fileNumber : $("#fileNumber").val(),
                        number : $("#number").val(),
                        classification : $("#classification").val(),
                        reason : $("#reason").val(),
                    }
                }
                fileUtil.handleFile(isUpdate(), ReceiptEntity.itemcode, $("#upload_file")[0].files[0]);

                ajaxUtil.myAjax(null,addUpdateUrl,ReceiptEntity,function (data) {
                    if(ajaxUtil.success(data)){
                        var submitConfirmModal = {
                            modalBodyID :"myTopicSubmitTip",
                            modalTitle : "提示",
                            modalClass : "modal-lg",
                            cancelButtonStyle: "display:none",
                            confirmButtonClass: "btn-danger",
                            modalConfirmFun:function (){
                                var url = "/document/sign";
                                orange.redirect(url);
                                return true;
                            }
                        }
                        var submitConfirm = modalUtil.init(submitConfirmModal);
                        submitConfirm.show();
                    }else {
                        alertUtil.alert(data.msg);
                    }
                },false,true,type);
                return false;
            });

            $("#btn_insert").unbind().on('click',function () {
                var mySubmitToCZ = {
                    modalBodyID: "mySubmitModal",
                    modalTitle: "提交",
                    modalClass: "modal-lg",
                    modalConfirmFun:function (){
                        var ReceiptEntity;
                        var addUpdateUrl;
                        if(!isUpdate()){
                            addUpdateUrl = "governresCountersign/insert";
                            ReceiptEntity = {
                                itemcode: stringUtil.getUUID(),
                                govPunlic : $('input:radio[name="govPunlic"]:checked').val(),
                                receivingTitle : $("#receivingTitle").val(),
                                parment : $("#parment").val(),
                                fileNumber : $("#fileNumber").val(),
                                number : $("#number").val(),
                                classification : $("#classification").val(),
                                reason : $("#reason").val(),
                                status : '1',
                            };
                        }else{
                            var needData = JSON.parse(localStorage.getItem("rowData"));
                            addUpdateUrl = "governresCountersign/update";
                            ReceiptEntity = {
                                itemid: needData.itemid,
                                itemcode: needData.itemcode,
                                govPunlic : $('input:radio[name="govPunlic"]:checked').val(),
                                receivingTitle : $("#receivingTitle").val(),
                                parment : $("#parment").val(),
                                fileNumber : $("#fileNumber").val(),
                                number : $("#number").val(),
                                classification : $("#classification").val(),
                                reason : $("#reason").val(),
                            }
                        }
                        fileUtil.handleFile(isUpdate(), ReceiptEntity.itemcode, $("#upload_file")[0].files[0]);

                        ajaxUtil.myAjax(null,addUpdateUrl,ReceiptEntity,function (data) {
                            if(ajaxUtil.success(data)){
                                var submitConfirmModal = {
                                    modalBodyID :"myTopicSubmitTip",
                                    modalTitle : "提示",
                                    modalClass : "modal-lg",
                                    cancelButtonStyle: "display:none",
                                    confirmButtonClass: "btn-danger",
                                    modalConfirmFun:function (){
                                        var url = "/document/sign";
                                        orange.redirect(url);
                                        return true;
                                    }
                                }
                                var submitConfirm = modalUtil.init(submitConfirmModal);
                                submitConfirm.show();
                            }else {
                                alertUtil.alert(data.msg);
                            }
                        },false,true,);
                        return true;
                    }
                }
                var x = modalUtil.init(mySubmitToCZ);
                x.show();
                return false;
            });

            (function init() {
                if (isUpdate()){
                    var tempdata = JSON.parse(localStorage.getItem("rowData"));
                    $("#govPunlic").val(tempdata.govPunlic);
                    $("#receivingTitle").val(tempdata.receivingTitle);
                    $("#parment").val(tempdata.parment);
                    $("#fileNumber").val(tempdata.fileNumber);
                    $("#number").val(tempdata.number);
                    $("#classification").val(tempdata.classification);
                    $("#reason").val(tempdata.reason);
                    var img = tempdata.filePath;
                    $("#upimg").attr("src",img);
                }
                else {
                    $("#distpicker").distpicker();
                }
                init = function () {

                }
            }());

            function isUpdate() {
                return (localStorage.getItem("rowData") != null || localStorage.getItem("rowData") != undefined)
            }

            /*
           上传文件
           */
            document.getElementById('upload_file').onchange=function(){
                var len=this.files.length;
                $("#addFile").empty("p");
                for (var i = 0; i < len; i++) {
                    var name = this.files[i].name;
                    var j=i+1;
                    $("#addFile").append('<p>附件'+j+'：&nbsp;'+ name +'&nbsp;</p>');
                };
                if(len>0){
                    $("#clsfile").css("display","block")
                }
            }
            document.getElementById('clsfile').onclick = function() {
                var obj = document.getElementById('upload_file');
                obj.outerHTML=obj.outerHTML;
                $("#clsfile").css("display","none");
                $("#addFile").empty("p");
            }
        })
})();