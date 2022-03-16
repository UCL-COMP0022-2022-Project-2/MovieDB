var contextPath = $("#contextPath").val();

window.onload = getPopular;

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

    $(".titleCol").append("<a class = 'title'></a>");
    $(".ratingCol").append("<p class = 'rating'></p>");
    $(".yearCol").append("<p class = 'year'></p>");
    $(".genresCol").append("<p class = 'genres'></p>");

    var titles = document.querySelectorAll(".title");
    var ratings = document.querySelectorAll(".rating");
    var years = document.querySelectorAll(".year");
    var genres = document.querySelectorAll(".genres");

    var titleHref;

    for(i = 0; i < resp.length; i++) {
        titles[i].innerHTML = resp[i]["title"];
        ratings[i].innerHTML = resp[i]["rating"];
        years[i].innerHTML = resp[i]["year"];
        genres[i].innerHTML = resp[i]["genres"];

        titleHref = "getReportsById/" + resp[i]["movieId"] + "/" + resp[i]["title"] + "/" + resp[i]["rating"] + ".do";
        titles[i].href = titleHref
    }

}
function getPopular(){
    $.ajax({
        url: "/MovieDB_war_exploded/getPopularMovies/" + "0" + "/" + "50" + ".do",
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
        num = "-1";
    }

    $.ajax({
        url: "/MovieDB_war_exploded/getPopularMovies/" + "0" + "/" + num + ".do",
        success(resp){
            deleteOld()
            printPopularItem(resp)
        }
    })
})

