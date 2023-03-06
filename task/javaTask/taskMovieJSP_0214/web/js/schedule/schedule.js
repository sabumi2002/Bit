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

            if(result.status == "success"){
                initPage(result);
            }else{

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