$(function () {
    $.ajax({
        url:"load",
        type:"post",
        success:function (data) {
            $("#sex").html(data)
        }
    })
})