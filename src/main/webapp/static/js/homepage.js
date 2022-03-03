var contextPath = $("#contextPath").val();

$("#getAllPosts").on("click", function() {
    alert("please wait, it takes long time to load");
    $.ajax({
        url: contextPath + "/getAllMovies.do",
        success(resp){
        printItem(resp);
        }
    })
});

function printItem(resp){

    if(resp.length === 0){
        $("#tableHead").css("visibility", "hidden");
        $("#noContent").css("display", "block");
    } else {
        $("#tableHead").css("visibility", "visible");
        $("#noContent").css("display", "none");
    }

    for(var i = 0; i < resp.length; i++) {
        $("#content").append("<div class = 'row itemRow'></div>");
    }

    $(".itemRow").append("<div class = 'col-4 itemCol titleCol px-3'></div>" +
        "<div class = 'col-2 itemCol ratingCol px-3'></div>" +
        "<div class = 'col-2 itemCol yearCol px-3'></div>" +
        "<div class = 'col-4 itemCol genresCol px-3'></div>");

    $(".titleCol").append("<a class = 'title' href = 'getReportsById/5.do'></a>");
    $(".ratingCol").append("<p class = 'rating'></p>");
    $(".yearCol").append("<p class = 'year'></p>");
    $(".genresCol").append("<p class = 'genres'></p>");

    var titles = document.querySelectorAll(".title");
    var ratings = document.querySelectorAll(".rating");
    var years = document.querySelectorAll(".year");
    var genres = document.querySelectorAll(".genres");

    for(var i = 0; i < resp.length; i++) {
        titles[i].id = resp[i]["movieId"];
        titles[i].innerHTML = resp[i]["title"];
        ratings[i].innerHTML = resp[i]["rating"];
        years[i].innerHTML = resp[i]["year"];
        genres[i].innerHTML = resp[i]["genres"];
    }


}

var title = "";
var ratingString = "";
var yearNum = "";
var genreType = "";

/*$("#check").on("click", function (){
    alert("title: " + title + "rating: " + ratingString + "year: " + yearNum + "genre: " + genreType);
});*/


$("#searchByName").on("click", function () {
    alert("please wait, it takes long time to load");
    title = $("#searchBar").val();
    var ratingStart = $("#ratingStart").val();
    var ratingEnd = $("#ratingEnd").val();
    yearNum = $("#yearNum").val();
    genreType = $("#genreType").val();

    if(genreType === "None"){
        genreType = null;
    }
    ratingString = "";

    if(ratingStart === "None" && ratingEnd === "None"){
        ratingString = null;
    } else if (ratingStart === "None"){
        ratingString = ratingEnd;
    } else if (ratingEnd === "None"){
        ratingString = ratingStart;
    } else{
        if(ratingStart === ratingEnd){
            ratingString = ratingStart;
        } else if(ratingStart === "0" || ratingEnd === "0"){
            if (ratingStart === "0"){
                ratingString = "-" + ratingEnd;
            } else{
                ratingString = "-" + ratingStart;
            }
        } else {
            if(ratingStart > ratingEnd) {
                ratingString = ratingEnd + "-" + ratingStart;
            }else {
                ratingString = ratingStart + "-" + ratingEnd;
            }
        }
    }

    var compareValue = "";
    var compareOrder = "";

    if (title === "" && ratingString === null && yearNum === "" && genreType === null){
        alert("You have not entered any selection, the page will reload, please re-enter");
        location.reload();
    } else if (title === "" && yearNum === "" && genreType === null){
        compareValue = "rating";
        compareOrder = "desc";
    } else {
        compareValue = "title";
        compareOrder = "asc";
    }

    $.ajax({
        url: contextPath + "/getRequiredMovies.do",
        traditional: true,
        data:{
            selectParams: [title, ratingString, genreType, yearNum],
            sortParams: [compareValue,compareOrder,"0,50"]
        },
        success(resp){
            deleteOld();
            console.log(resp)
            printItem(resp);
        }
    })
});

function deleteOld(){
    $(".itemRow").remove();
}

$("#titleAsc").on("click", function () {
    alert("please wait, it takes long time to load");
    $.ajax({
        url: contextPath + "/getRequiredMovies.do",
        traditional: true,
        data:{
            selectParams: [title, ratingString, genreType, yearNum],
            sortParams: ["title", "asc"]
        },
        success(resp){
            deleteOld();
            printItem(resp);
            }
        }
    )
})

$("#titleDesc").on("click", function () {
    alert("please wait, it takes long time to load");
    $.ajax({
            url: contextPath + "/getRequiredMovies.do",
            traditional: true,
            data:{
                selectParams: [title, ratingString, genreType, yearNum],
                sortParams: ["title", "desc"]
            },
            success(resp){
                deleteOld();
                printItem(resp);
            }
        }
    )
})

$("#ratingAsc").on("click", function () {
    alert("please wait, it takes long time to load");
    $.ajax({
            url: contextPath + "/getRequiredMovies.do",
            traditional: true,
            data:{
                selectParams: [title, ratingString, genreType, yearNum],
                sortParams: ["rating", "asc"]
            },
            success(resp){
                deleteOld();
                printItem(resp);
            }
        }
    )
})

$("#ratingDesc").on("click", function () {
    alert("please wait, it takes long time to load");
    $.ajax({
        url: contextPath + "/getRequiredMovies.do",
        traditional: true,
        data:{
            selectParams: [title, ratingString, genreType, yearNum],
            sortParams: ["rating", "desc"]
        },
        success(resp){
            deleteOld();
            printItem(resp);
        }
    })
})
$("#yearAsc").on("click", function () {
    alert("please wait, it takes long time to load");
    $.ajax({
            url: contextPath + "/getRequiredMovies.do",
            traditional: true,
            data:{
                selectParams: [title, ratingString, genreType, yearNum],
                sortParams: ["year", "asc"]
            },
            success(resp){
                deleteOld();
                printItem(resp);
            }
        }
    )
})

$("#yearDesc").on("click", function () {
    alert("please wait, it takes long time to load");
    $.ajax({
            url: contextPath + "/getRequiredMovies.do",
            traditional: true,
            data:{
                selectParams: [title, ratingString, genreType, yearNum],
                sortParams: ["year", "desc"]
            },
            success(resp){
                deleteOld();
                printItem(resp);
            }
        }
    )
})

$(".title").on("click", function () {
    alert(this.id);
    alert("please wait, it takes long time to load");
});



