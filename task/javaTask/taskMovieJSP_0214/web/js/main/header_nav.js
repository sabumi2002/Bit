

let initNav = () => {
    console.log("initNav");
    $.ajax({
        url: "/HeaderNavServlet",
        type: "get",
        success: function (message) {
            let result = JSON.parse(message);
            if (result.result != "success") {
                logInNav();
            } else {
                logOutNav(result.logInNickname);
            }
        }
    })
}

function logInNav() {
    $("button.navbar-login").css('display', 'block');
    $(".navbar-logout").css('display', 'none');
    $("#login_box").css('visibility', 'hidden');

}

function logOutNav(nickname) {

    $(".navbar-login").css('display', 'none');
    $(".navbar-logout").text(nickname + "님").css('display', 'block');
    $("#login_box").css('visibility', 'visible');


}