let writeReply = () => {
    let formData = {
        "score": $('#reply-score').val(),
        "content": $('#reply-content').val(),
        "movieId": new URLSearchParams(window.location.search).get("id")
    };
    $.ajax({
        url: "/reply/ReplyWriteServlet",
        type: "post",
        data: formData,
        success: (message) => {
            let response = JSON.parse(message);
            if (response.status == "fail") {
                console.log("fail")
                swal.fire({
                    title: "!!! ERROR !!!",
                    text: "에러가 발생했습니다",
                    icon: "error"
                }).then(() => {
                    location.reload();
                })
            }else{
                location.reload();
            }
        }
    })
}

// $(document).ready(function() {
//     alert("aaa");
//     $("button.navbar-login").css('display', 'block');
//     $(".navbar-logout").css('display', 'none');
//     $("#login_box").css('visibility', 'hidden');
// });
