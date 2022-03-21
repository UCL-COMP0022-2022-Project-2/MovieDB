window.onload = getPopular;
var sortString = "AvgRating";

function changeHeader(number){
    $("#title").remove();
    var newTitle = '<h1 class = "display-1 text-center" id = "title">Top ' + number + ' Most Popular Movies</h1>'
    $("#caption").append(newTitle);
}

function printPopularItem(resp){
    let i;

    // some edit of visibility to the table
    if(resp.length === 0){
        $("#tableHead").css("visibility", "hidden");
        $("#noContent").css("display", "block");
    } else {
        $("#tableHead").css("visibility", "visible");
        $("#noContent").css("display", "none");
    }

    // add all rows to the table
    for(i = 0; i < resp.length; i++) {
        $("#content").append("<div class = 'row itemRow my-2 mx-2'></div>");
    }

    // add parameters to each row
    $(".itemRow").append("<div class = 'col-4 itemCol titleCol pe-3 ps-5 pt-3'></div>" +
        "<div class = 'col-2 itemCol ratingCol pe-3 ps-4 pt-3'></div>" +
        "<div class = 'col-2 itemCol yearCol pe-3 ps-4 pt-3'></div>" +
        "<div class = 'col-4 itemCol genresCol px-3 pt-3 pe-3'></div>");

    $(".titleCol").append("<a class = 'title' target=‘_blank’ rel=‘noopener noreferrer’></a>");
    $(".ratingCol").append("<p class = 'rating'></p>");
    $(".yearCol").append("<p class = 'year'></p>");
    $(".genresCol").append("<p class = 'genres'></p>");

    var titles = document.querySelectorAll(".title");
    var ratings = document.querySelectorAll(".rating");
    var years = document.querySelectorAll(".year");
    var genres = document.querySelectorAll(".genres");

    var titleHref;

    for(i = 0; i < resp.length; i++) {
        titles[i].innerHTML = i + 1 + ". " + resp[i]["title"];
        ratings[i].innerHTML = resp[i]["rating"];
        years[i].innerHTML = resp[i]["year"];
        genres[i].innerHTML = resp[i]["genres"];

        titleHref = "getReportsById/" + resp[i]["movieId"] + "/" + resp[i]["title"] + "/" + resp[i]["rating"] + ".do";
        titles[i].href = titleHref
    }

}
function getPopular(){
    $.ajax({
        url: "/MovieDB_war_exploded/getPopularMoviesByAvgRating/" + "0" + "/" + "50" + ".do",
        success(resp){
            printPopularItem(resp)
        }
    })
}

function deleteOld(){
    $(".itemRow").remove();
}

$("#submit").on("click", function(){
    var num = $("#wantNum").val();

    if(num === ""){
        alert("You have not entered any selection, the page will reload, please re-enter");
        location.reload();
    }else if(num >= 10000){
        changeHeader(num)
        num = "-1";
    } else {
        changeHeader(num);
    }

    $.ajax({
        url: "/MovieDB_war_exploded/getPopularMoviesBy" + sortString + "/" + "0" + "/" + num + ".do",
        success(resp){
            deleteOld()
            printPopularItem(resp)
        }
    })
})

$("#AvgRating").on("click", function(){
    sortString = "AvgRating"
    $("#sortMethod").text("Most Popular Movie By Average Rating")
    $.ajax({
        url: "/MovieDB_war_exploded/getPopularMoviesByAvgRating/" + "0" + "/" + "50" + ".do",
        success(resp){
            deleteOld()
            printPopularItem(resp)
        }
    })
})
$("#CountRatings").on("click", function(){
    sortString = "CountRating"
    $("#sortMethod").text("Most Popular Movie By the Number of Users' Ratings")
    $.ajax({
        url: "/MovieDB_war_exploded/getPopularMoviesByCountRating/" + "0" + "/" + "50" + ".do",
        success(resp){
            console.log(resp)
            deleteOld()
            printPopularItem(resp)
        }
    })
})

$("#CountTags").on("click", function(){
    sortString = "CountTags"
    $("#sortMethod").text("Most Popular Movie By the Number of Users' Tags")
    $.ajax({
        url: "/MovieDB_war_exploded/getPopularMoviesByCountTags/" + "0" + "/" + "50" + ".do",
        success(resp){
            deleteOld()
            printPopularItem(resp)
        }
    })
})


