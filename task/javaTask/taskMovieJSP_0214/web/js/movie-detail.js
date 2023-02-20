function rating_btn(score) {
    var rating_score = document.querySelector('.score_info');
    var hidden_score = document.querySelector('.hidden_score');
    rating_score.textContent= score;
    hidden_score.value = score;
}

// function reviewDelete(id) {
//     let result = confirm("정말로 삭제하시겠습니까?");
//     if (result) {
//         location.href = "/reply/delete.jsp?id=" +
//             id;
//     }
// }


function prof_img(){
    var story_point = document.querySelectorAll('.story-point');
    var list = document.querySelectorAll('.prof-img img');
    // alert(typeof story_point.textContent);   //에러확인
    // alert(parseInt(story_point.textContent));    //에러확인

    for(var i =0; i< list.length; i++){
        const item = list.item(i);
        if (story_point.item(i).textContent<5) {
            item.src = "/images/sentiment_dissatisfied_FILL0.svg";
        }
        else {
            item.src = "/images/sentiment_satisfied_FILL0.svg";
        }

    }
}
prof_img();