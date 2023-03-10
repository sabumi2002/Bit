const fileInput = document.getElementById("input-file");

const handleFiles = (e) => {
    const selectedFile = [...fileInput.files];
    const fileReader = new FileReader();

    fileReader.readAsDataURL(selectedFile[0]);

    fileReader.onload = function () {
        document.getElementById("previewImg").src = fileReader.result;
    };
};

fileInput.addEventListener("change", handleFiles);


let fileUpload = () => {
    let movieId = new URLSearchParams(window.location.search).get('id');
    let formData = new FormData();
    formData.append("file", $('#input-file')[0].files[0]);
    formData.append("title", $('#input-title').val());
    formData.append("content", $('#input-content').val());
    formData.append("rank", $('#input-rank').val());
    formData.append("release", $('#input-release').val());
    formData.append("length", $('#input-length').val());
    formData.append("movieId", movieId);


    $.ajax({
        url: '/movie/UpdateServlet',
        type: 'post',
        processData: false,
        contentType: false,
        data: formData,
        success: (message) => {
            let result = JSON.parse(message);
            if(result.status=="success"){
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Your work has been saved',
                    showConfirmButton: false,
                    timer: 1500
                })
            }else{
                Swal.fire({
                    position: 'top-end',
                    icon: 'error',
                    title: '!!! ERROR !!!',
                    showConfirmButton: false,
                    timer: 1500
                })
            }

        }
    })

    location.href="/movie/movie-detail.jsp?id="+movieId;
}
