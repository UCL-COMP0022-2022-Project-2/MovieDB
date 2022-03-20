window.onload = initial
function initial(){
    $(".ratingLine").hide()
    $("#upTriangle").hide()
}
$("#predictBox").on("click", function (){
    $(".ratingLine").toggle()
    $("#downTriangle").toggle()
    $("#upTriangle").toggle()
    var movieId = $("#movieId").text();
    $.ajax({
        url: "/MovieDB_war_exploded/getPredictedScoreByMovieId/" + movieId + ".do",
        success(resp){
            $("#predictRating").text(resp)
        }
    })
})