$(document).ready(function () {
    initSelect();

});

let initSelect = () => {
    let id = new URLSearchParams(window.location.search).get("id");
    let movieRank = $(document.getElementById("input-rank")).val();


    $.ajax({
        url: "/schedule/ScheduleWriteServlet",
        type: "get",
        data: {
            id: id,
            movieRank: movieRank
        },
        success: (message) => {
            let result = JSON.parse(message);
            let movieList = JSON.parse(result.movieList);

            insertSelectRoom(result.room);


            let movieBox = $(document.getElementById("input-movie"));
            movieBox.children().remove();
            movieList.forEach((m) => {
                insertSelectMovie(m);
            })
            selectMovie();
        }
    })
}


function insertSelectRoom(room) {

    let selectBox = $(document.getElementById("input-room"));
    selectBox.children().remove();
    let option = $(document.createElement("option")).val(1).text(1);
    for (var i = 1; i <= room; i++) {
        if (i == 1) {
            option = $(document.createElement("option")).val(i).text(i);
        } else {
            option = $(document.createElement("option")).val(i).text(i);
        }
        selectBox.append(option);
    }
}

function insertSelectMovie(movie) {
    let selectBox = $(document.getElementById("input-movie"));
    let option = $(document.createElement("option")).val(movie.id).text(movie.title);

    selectBox.append(option);

}

let selectRank = () => {
    initSelect();
};
let selectMovie = () => {
    let selectBox = $(document.getElementById("input-movie"));
    $.ajax({
        url: "/schedule/ScheduleWriteServlet",
        type: "get",
        data: {
            movieId: selectBox.val()
        },
        success: (message) => {
            let result = JSON.parse(message);
            $(document.getElementById("previewImg")).attr("src", result.movieImg);
        }
    })
    calChange();
}


$(document).ready(function () {
    let today = new Date();

    console.log(today);

    let year = today.getFullYear();
    let month = ('0' + (today.getMonth() + 1)).slice(-2);
    let day = ('0' + today.getDate()).slice(-2);
    day = '0' + (parseInt(day) + 2);

    let dateString = year + '-' + month + '-' + day;

    let hours = ('0' + today.getHours()).slice(-2);
    let minutes = ('0' + today.getMinutes()).slice(-2);
    let seconds = ('0' + today.getSeconds()).slice(-2);

    let timeString = hours + ':' + minutes + ':' + seconds;

    let todayString = dateString + " " + timeString;
    console.log(todayString);


});

function calChange() {
    let year = $(document.getElementById("calYear")).text();
    let month = $(document.getElementById("calMonth")).text();
    let day = $(document.getElementsByClassName("choiceDay")).text();

    let date = $(document.getElementById("input-date"));

    let calButton = $(document.getElementsByClassName("cal-button"));
    if (day != "") {
        date.val(year + "-" + month + "-" + day);
        calButton.text(date.val());
    } else {
        calButton.text("날짜를 선택해주세요.")
    }


    // console.log(year+"-"+month+"-"+day);
    $(document.getElementsByClassName("cal")).css('display', 'none');

}

function calButton() {
    let cal = $(document.getElementsByClassName("cal"));

    if (cal.css('display') == 'none') {
        cal.css('display', 'block');
    } else {
        cal.css('display', 'none');
    }
}


function checkButton() {
    let id = new URLSearchParams(window.location.search).get("id");
    let date = $(document.getElementById("input-date"));
    if (date.val() == "") {
        Swal.fire({
            icon: 'error',
            title: '!!! ERROR !!!',
            text: '날짜를 선택해주세요.',
            showConfirmButton: false,
            timer: 1500
        }).then(() => {
            location.href = "/schedule/write.jsp?id=" + id;
        })
    }
    date = date.val();
    let room = $(document.getElementById("input-room")).val();
    let movieId = $(document.getElementById("input-movie")).val();
    let startTime = $(document.getElementById("input-startTime")).val();



    let data = {
        "scheduleId": id,
        "date": date,
        "movieId": movieId,
        "startTime": startTime,
        "room": room
    }
    $.ajax({
        url: "/schedule/ScheduleUpdateServlet",
        type: "post",
        data: data,
        success: (message) => {
            let result = JSON.parse(message);
            if (result.status == "null") {
                Swal.fire({
                    icon: 'error',
                    title: '!!! RunningTime ERROR !!!',
                    text: '상영시간이 겹칩니다.',
                    showConfirmButton: false,
                    // timer: 1500
                }).then(() => {
                    location.href = "/schedule/update.jsp?id=" + id;
                })
            } else {
                if (result.status == "success") {
                    Swal.fire({
                        // position: 'top-end',
                        icon: 'success',
                        title: 'Your work has been saved',
                        showConfirmButton: false,
                        timer: 1500
                    }).then(() => {
                        location.href = "/schedule/schedule.jsp?id=" + id;
                    })
                } else {
                    Swal.fire({
                        // position: 'top-end',
                        icon: 'error',
                        title: '!!! ERROR !!!',
                        text: 'servlet error',
                        showConfirmButton: false,
                        // timer: 1500
                    }).then(() => {
                        location.href = "/schedule/update.jsp?id=" + id;
                    })
                }
            }
        }
    })
}


function cancelButton() {
    let id = new URLSearchParams(window.location.search).get("id");
    location.href = "/schedule/schedule.jsp?id=" + id;
}


window.onload = function () {
    buildCalendar();
}    // 웹 페이지가 로드되면 buildCalendar 실행

let nowMonth = new Date();  // 현재 달을 페이지를 로드한 날의 달로 초기화
let today = new Date();     // 페이지를 로드한 날짜를 저장
today.setHours(0, 0, 0, 0);    // 비교 편의를 위해 today의 시간을 초기화

// 달력 생성 : 해당 달에 맞춰 테이블을 만들고, 날짜를 채워 넣는다.
function buildCalendar() {

    let firstDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth(), 1);     // 이번달 1일
    let lastDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, 0);  // 이번달 마지막날

    let tbody_Calendar = document.querySelector(".Calendar > tbody");
    document.getElementById("calYear").innerText = nowMonth.getFullYear();             // 연도 숫자 갱신
    document.getElementById("calMonth").innerText = leftPad(nowMonth.getMonth() + 1);  // 월 숫자 갱신

    while (tbody_Calendar.rows.length > 0) {                        // 이전 출력결과가 남아있는 경우 초기화
        tbody_Calendar.deleteRow(tbody_Calendar.rows.length - 1);
    }

    let nowRow = tbody_Calendar.insertRow();        // 첫번째 행 추가

    for (let j = 0; j < firstDate.getDay(); j++) {  // 이번달 1일의 요일만큼
        let nowColumn = nowRow.insertCell();        // 열 추가
    }

    for (let nowDay = firstDate; nowDay <= lastDate; nowDay.setDate(nowDay.getDate() + 1)) {   // day는 날짜를 저장하는 변수, 이번달 마지막날까지 증가시키며 반복

        let nowColumn = nowRow.insertCell();        // 새 열을 추가하고


        let newDIV = document.createElement("p");
        newDIV.innerHTML = leftPad(nowDay.getDate());        // 추가한 열에 날짜 입력
        nowColumn.appendChild(newDIV);

        if (nowDay.getDay() == 6) {                 // 토요일인 경우
            nowRow = tbody_Calendar.insertRow();    // 새로운 행 추가
        }

        if (nowDay < today) {                       // 지난날인 경우
            newDIV.className = "pastDay";
        } else if (nowDay.getFullYear() == today.getFullYear() && nowDay.getMonth() == today.getMonth() && nowDay.getDate() == today.getDate()) { // 오늘인 경우
            newDIV.className = "today";
            newDIV.onclick = function () {
                choiceDate(this);
            }
        } else {                                      // 미래인 경우
            newDIV.className = "futureDay";
            newDIV.onclick = function () {
                choiceDate(this);
            }
        }
    }
}

// 날짜 선택
function choiceDate(newDIV) {
    if (document.getElementsByClassName("choiceDay")[0]) {                              // 기존에 선택한 날짜가 있으면
        document.getElementsByClassName("choiceDay")[0].classList.remove("choiceDay");  // 해당 날짜의 "choiceDay" class 제거
    }
    newDIV.classList.add("choiceDay");           // 선택된 날짜에 "choiceDay" class 추가


    calChange();    // 날짜가 선택되면 실행 값을넘겨주기위해
}

// 이전달 버튼 클릭
function prevCalendar() {
    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() - 1, nowMonth.getDate());   // 현재 달을 1 감소
    buildCalendar();    // 달력 다시 생성
}

// 다음달 버튼 클릭
function nextCalendar() {
    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, nowMonth.getDate());   // 현재 달을 1 증가
    buildCalendar();    // 달력 다시 생성
}

// input값이 한자리 숫자인 경우 앞에 '0' 붙혀주는 함수
function leftPad(value) {
    if (value < 10) {
        value = "0" + value;
        return value;
    }
    return value;
}