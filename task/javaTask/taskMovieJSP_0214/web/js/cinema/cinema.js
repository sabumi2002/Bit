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