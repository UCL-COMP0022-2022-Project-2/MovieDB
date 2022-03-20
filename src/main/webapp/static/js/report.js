window.onload = initial
function initial(){
    $(".ratingLine").hide()
    $("#close").hide()
}
$("#predictBox").on("click", function (){
    $(".ratingLine").show()
    $("#close").show()
    var movieId = $("#movieId").text();
    $.ajax({
        url: "/MovieDB_war_exploded/getPredictedScoreByMovieId/" + movieId + ".do",
        success(resp){
            $("#predictRating").text(resp)
        }
    })
})