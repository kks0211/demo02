function commonAjax(type, url, data) {
    var calback_id = url.split('/').reverse()[0].replace('.do', '');

    $.ajax({
        url          : url
        , type       : type
        , data       : JSON.stringify(data)
        , dataType   : "json"
        , contentType: "application/json; charset=UTF-8"
        //, async      : useAsync
        , success    : function (result, textStatus, data) {
            alert("성공")
            fn_callBack(calback_id, result, textStatus);
        },
        error        : function (xhr, errorName, error) {
            alert("에러입니다." + xhr.statusText);
        }
    });
}

function fn_callBack(calback_id, result, textStatus) {
    console.log('======> ' + calback_id);
    if (calback_id == 'test') {
        if (result.success = '성공') //ajax호출후 전달 받은값
        {
            console.log("값을 받았습니다.");
        }
    }
}