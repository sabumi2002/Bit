
$(document).ready(function (){
    let id = new URLSearchParams(window.location.search).get("id");
    let movieRank = $(document.getElementById("input-rank")).val();

    console.log(id);
    console.log(movieRank);

    $.ajax({
        url: "/schedule/ScheduleWriteServlet",
        type: "get",
        data : {
            id: id,
            movieRank: movieRank
        },
        success: (message) => {

        }
    })

});





// let fileUpload = () => {
//     let formData = new FormData();
//     formData.append("file", $('#input-file')[0].files[0]);
//     formData.append("title", $('#input-title').val());
//     formData.append("location", $('#input-location').val());
//     formData.append("phoneNumber", $('#input-phoneNumber').val());
//
//
//
//     $.ajax({
//         url: '/cinema/WriteServlet',
//         type: 'post',
//         processData: false,
//         contentType: false,
//         data: formData,
//         success: (message) => {
//
//
//             let result = JSON.parse(message);
//             if (result.status == "success") {
//                 Swal.fire({
//                     position: 'top-end',
//                     icon: 'success',
//                     title: 'Your work has been saved',
//                     showConfirmButton: false,
//                     timer: 1500
//                 }).then(() => {
//                     location.href = "/cinema/cinema.jsp";
//                 })
//             } else {
//                 Swal.fire({
//                     position: 'top-end',
//                     icon: 'error',
//                     title: '!!! ERROR !!!',
//                     text: '빼먹은게 없는지 확인해주세요.',
//                     showConfirmButton: false,
//                     timer: 1500
//                 }).then(() => {
//                     location.href="/cinema/cinema.jsp";
//                 })
//             }
//
//         }
//     })
// }
