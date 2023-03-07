$(document).ready(function () {
    let id = new URLSearchParams(window.location.search).get("id");

    $.ajax({
        url: "/schedule/ScheduleServlet",
        type: "get",
        data: {
            "id": id
        },
        success: (message) => {
            let result = JSON.parse(message);
                initPage(result);
            if(result.status == "success"){

            }else{

            }
            if(result.logInRank == 1){
                scheduleUtilBox();
            }
        }
    })
});

function initPage(result) {
    $(document.getElementById("movieRank")).addClass("age-"+result.movieRank);
    $(document.getElementById("movieImg")).attr("src", result.movieImg);
    $(document.getElementById("movieTit")).text(result.movieTitle);
    $(document.getElementById("cinemaTit")).text(result.cinemaName);
    $(document.getElementById("cinemaLoc")).text(result.cinemaLocation);
    $(document.getElementById("cinemaPhone")).text(result.cinemaPhone);
    $(document.getElementById("scheduleDate")).text(result.screeningDate);
    $(document.getElementById("startTime")).text(result.startRunningTime);
    $(document.getElementById("endTime")).text(result.endRunningTime);


}

function scheduleUtilBox() {
    let id = new URLSearchParams(window.location.search).get("id");

    let adminBox = $(document.createElement("div")).addClass("schedule-admin text-center");
    let utilBox = $(document.createElement("div")).addClass("schedule-util");
    let updateButton = $(document.createElement("button")).addClass("btn btn-outline-success").text("수정하기");
    let deleteButton = $(document.createElement("button")).addClass("btn btn-outline-danger").text("삭제하기");


    updateButton.click(() => {
        location.href = "/schedule/update.jsp?id="+id;
    })
    deleteButton.click(() => {
        scheduleDelete(id);
    })

    utilBox.append(updateButton);
    utilBox.append(deleteButton);
    adminBox.append(utilBox);

    $("#schedule-util").append(adminBox);
}

function scheduleDelete(id){
    data = {
        "id" : id
    }
    $.ajax({
        url: "/schedule/ScheduleDeleteServlet",
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
                    text: '상영시간이 존재하지않거나 관리자가 아닙니다.',
                    showConfirmButton: false,
                    timer: 1500
                }).then(() => {
                    location.href = "/schedule/schedule.jsp?id=" + id;
                })
            }
        }
    })
}