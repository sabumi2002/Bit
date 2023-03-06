// let initPage = () => {
//     $.ajax({
//         url: "/main/IndexServlet",
//         type: "get",
//         success: function (message) {
//             let result = JSON.parse(message);
//             let movieList = JSON.parse(result.movieList);
//
//             if (movieList == null) {
//                 printEmpty();
//             } else {
//                 movieList.forEach((m) => {
//                     printMovie(m);
//                 })
//             }
//         }
//     })
//     initNav();
// }


// function printMovie(movie) {
//     let swiperSlide = $(document.createElement("div")).attr("className", "swiper-slide");
//     let movieLink = $(document.createElement("a")).attr("href", "/movie/movie-detail.jsp?id=" + movie.id);
//     let movieImg = $(document.createElement("img")).attr("src", movie.img).attr("alt", "범시네마와 함께 즐거운 시간보내세요!");
//
//     $(movieLink).append(movieImg);
//     $(swiperSlide).append(movieLink);
//
//
//     $('.swiper-wrapper').append(swiperSlide);
//
//
// }

// function printEmpty() {
//     let swiperSlide = $(document.createElement("div")).attr("className", "swiper-slide");
//     let movieLink = $(document.createElement("a")).attr("href", "#");
//     let movieImg = $(document.createElement("img")).attr("src", "/images/main/범시네마_noimg.png").attr("alt", "범시네마와 함께 즐거운 시간보내세요!");
//
//     $(movieLink).append(movieImg);
//     $(swiperSlide).append(movieLink);
//     $('.swiper-wrapper').append(swiperSlide);
// }



// new Swiper(선택자, 옵션)
new Swiper('.notice-line .swiper-container', {
    // spaceBetween: 1000,
    slidesPerView: 1,
    speed: 300,
    allowTouchMove: false, // 터치 이동  x

    direction: 'vertical',
    autoplay: true,
    loop: true,
});

new Swiper('.promotion .swiper-container', {
    slidesPerView: 3, // 한번에 보여줄 슬라이드 개수
    spaceBetween: 100, // 슬라이드 사이 여백
    centeredSlides: true, // 1번 슬라이드가 가운데 보이기
    loop: true,
    autoplay: {
        delay: 5000
    },
    pagination: {
        el: '.promotion .swiper-pagination', // 페이지 번호 요소 선택자
        clickable: true, // 사용자의 페이지 번호 요소 제어
    },
    navigation: {
        prevEl: '.promotion .swiper-prev',
        nextEl: '.promotion .swiper-next',
    }
});
