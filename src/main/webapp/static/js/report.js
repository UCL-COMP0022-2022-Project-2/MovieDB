/*window.onload = averageRatingCount;
function averageRatingCount(){
    var domain = document.location.pathname;
    var movieIdStart = domain.indexOf("ById/") + 5;
    var movieIdEnd = domain.indexOf(".do");
    var movieId = domain.slice(movieIdStart,movieIdEnd)
    $("#pMovieId").append(movieId);

    var ratingStrings = $(".ratingString");
    var ratingSum = 0;
    var ratingCount = ratingStrings.length;
    var ratingResult;
    for (var i = 0; i < ratingStrings.length; i++){
        var ratingNum = parseFloat(ratingStrings[i].innerHTML);
        ratingSum = ratingSum + ratingNum;
    }
    ratingResult = ratingSum / ratingCount;
    $("#pAverageRating").append(ratingResult.toFixed(1));
};
*/
