$(document).ready(function () {
    let id = new URLSearchParams(window.location.search).get("id");

    $.ajax({
        url: "/cinema/CinemaDetailServlet",
        type: "get",
        success: (message) => {
            let result = JSON.parse(message);


            if (result.cinemaList != null) {
                let cinemaList = JSON.parse(result.cinemaList);
                $('#cinema-list').children().remove();
                cinemaList.forEach((c) => {
                    insertCinemaRow(c);
                })

            }


            if (result.logInRank == 1) {
                cinemaUtilBox();
            }
        }
    })
});


function insertCinemaRow(cinema) {
    let li = $(document.createElement("li")).append($(document.createElement("a")).attr("href", "/cinema/cinema-detail.jsp?id=" + cinema.id).text(cinema.name));
    $('#cinema-list').append(li);

}

// 시네마list 관리BOX
function cinemaUtilBox() {
    let id = new URLSearchParams(window.location.search).get("id");

    let adminBox = $(document.createElement("div")).addClass("cinema-admin bg-dark text-center");
    let utilBox = $(document.createElement("div")).addClass("cinema-util");
    let insertButton = $(document.createElement("button")).addClass("btn btn-outline-info").text("극장 추가하기");
    let updateButton = $(document.createElement("button")).addClass("btn btn-outline-success").text("극장 수정하기");
    let deleteButton = $(document.createElement("button")).addClass("btn btn-outline-danger").text("극장 삭제하기");

    insertButton.click(() => {
        location.href = "/cinema/write.jsp";
    })
    updateButton.click(() => {
        location.href = "/cinema/update.jsp?id="+id;
    })
    deleteButton.click(() => {
        cinemaDelete(id);
    })

    utilBox.append(insertButton);
    utilBox.append(updateButton);
    utilBox.append(deleteButton);
    adminBox.append(utilBox);

    $("#cinemas").append(adminBox);
}


function cinemaDelete(id){
    data = {
        "id" : id
    }
    $.ajax({
        url: "/cinema/CinemaDeleteServlet",
        type: "post",
        data: data,
        success: (message) => {
            let result = JSON.parse(message);

            if (result.status == "success") {
                Swal.fire({
                    // position: 'top-end',
                    title: "Deleted!",
                    icon: 'success',
                    text: 'Your file has been deleted.',
                    showConfirmButton: false,
                    timer: 1500
                }).then(() => {
                    location.href = "/cinema/cinema.jsp";
                })
            } else {
                Swal.fire({
                    // position: 'top-end',
                    icon: 'error',
                    title: '!!! ERROR !!!',
                    text: '영화가 존재하지않거나 관리자가 아닙니다.',
                    showConfirmButton: false,
                    timer: 1500
                }).then(() => {
                    location.href = "/cinema/cinema-detail.jsp?id=" + id;
                })
            }
        }
    })
}