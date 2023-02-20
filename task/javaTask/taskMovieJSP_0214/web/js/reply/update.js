function rating_btn(score) {
    var rating_score = document.querySelector('.score_info');
    var hidden_score = document.querySelector('.hidden_score');
    rating_score.textContent= score;
    hidden_score.value = score;
}