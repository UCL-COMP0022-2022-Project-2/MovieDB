var contextPath = $("#contextPath").val();
var title = "";
var ratingString = "";
var yearNum = "";
var genreType = "";
var movieCount = "";
var pageCount = "";
var leftCount = "";
var currentPage = "";
var compareValue = "";
var compareOrder = "";
var limit = "";


$("#getAllPosts").on("click", function() {
    alert("please wait, it takes long time to load");
    $.ajax({
        url: contextPath + "/getRequiredMovies.do",
        traditional: true,
        data:{
            selectParams: ["", "", "", ""],
            sortParams: ["","","0,10000"]
        },
        success(resp){
        printItem(resp);
        }
    })
});

function getMovieCount(cTitle, cRating, cYear, cGenre, valueCompare, valueOrder){
    $.ajax({
        url: contextPath + "/getMoviesCount.do",
        traditional: true,
        data:{
            selectParams: [cTitle, cRating, cYear, cGenre],
            sortParams: [valueCompare, valueOrder, ""]
        },
        success(resp){
            printPageItem(resp);
        }
    })
};

function printPageItem(resp){
    $("#footer").css("display", "block");
    movieCount = resp;
    pageCount = Math.floor(movieCount / 50) + 1;
    leftCount = movieCount % 50;

    var pageList = "";

    for(var i = 1; i <= pageCount; i++){
        pageList += '<li class="page-item pageContent"><a class="page-link text-dark" href="#">'+i+'</a></li>';
    }

    $("#nextTenPages").after(pageList);

    $(".pageContent").on("click", function(){
        var hrefContent = this.innerHTML;
        currentPage = hrefContent.match((/\d+/g)) + "";

        if(currentPage === pageCount){
            limit = (currentPage - 1) * 50 + "," + ((currentPage - 1) * 50 + leftCount);
        } else {
            limit = (currentPage - 1) * 50 + "," + currentPage * 50;
        }
        alert(compareValue);
        alert(compareOrder);

        $.ajax({
            url: contextPath + "/getRequiredMovies.do",
            traditional: true,
            data:{
                selectParams: [title, ratingString, genreType, yearNum],
                sortParams: [compareValue,compareOrder,limit]
            },
            success(resp){
                deleteOld();
                printItem(resp);
            }
        })
    });
};

function printItem(resp){

    if(resp.length === 0){
        $("#tableHead").css("visibility", "hidden");
        $("#noContent").css("display", "block");
    } else {
        $("#tableHead").css("visibility", "visible");
        $("#noContent").css("display", "none");
    }

    for(var i = 0; i < resp.length; i++) {
        $("#content").append("<div class = 'row itemRow my-2 mx-2'></div>");
    }

    $(".itemRow").append("<div class = 'col-4 itemCol titleCol pe-3 ps-5 pt-3'></div>" +
        "<div class = 'col-2 itemCol ratingCol pe-3 ps-4 pt-3'></div>" +
        "<div class = 'col-2 itemCol yearCol pe-3 ps-4 pt-3'></div>" +
        "<div class = 'col-4 itemCol genresCol px-3 pt-3'></div>");

    $(".titleCol").append("<a class = 'title'></a>");
    $(".ratingCol").append("<p class = 'rating'></p>");
    $(".yearCol").append("<p class = 'year'></p>");
    $(".genresCol").append("<p class = 'genres'></p>");

    var titles = document.querySelectorAll(".title");
    var ratings = document.querySelectorAll(".rating");
    var years = document.querySelectorAll(".year");
    var genres = document.querySelectorAll(".genres");

    var titleHref;

    for(var i = 0; i < resp.length; i++) {
        titles[i].innerHTML = resp[i]["title"];
        ratings[i].innerHTML = resp[i]["rating"];
        years[i].innerHTML = resp[i]["year"];
        genres[i].innerHTML = resp[i]["genres"];

        titleHref = "getReportsById/" + resp[i]["movieId"] + "/" + resp[i]["title"] + "/" + resp[i]["rating"] + ".do";
        titles[i].href = titleHref
    }

}



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

    getMovieCount(title, ratingString, genreType, yearNum, compareValue, compareOrder);


    $.ajax({
        url: contextPath + "/getRequiredMovies.do",
        traditional: true,
        data:{
            selectParams: [title, ratingString, genreType, yearNum],
            sortParams: [compareValue,compareOrder,"0,50"]
        },
        success(resp){
            deleteOld();
            deletePageItem();
            printItem(resp);
        }
    })
});

function deleteOld(){
    $(".itemRow").remove();
}

function deletePageItem(){
  $(".pageContent").remove();
};


$("#titleAsc").on("click", function () {
    alert("please wait, it takes long time to load");
    $.ajax({
        url: contextPath + "/getRequiredMovies.do",
        traditional: true,
        data:{
            selectParams: [title, ratingString, genreType, yearNum],
            sortParams: ["title", "asc", "0,50"]
        },
        success(resp){
            deleteOld();
            deletePageItem();
            printItem(resp);
            }
        }
    )
    getMovieCount(title, ratingString, genreType, yearNum, compareValue, compareOrder);
})

$("#titleDesc").on("click", function () {
    alert("please wait, it takes long time to load");
    $.ajax({
            url: contextPath + "/getRequiredMovies.do",
            traditional: true,
            data:{
                selectParams: [title, ratingString, genreType, yearNum],
                sortParams: ["title", "desc", "0,50"]
            },
            success(resp){
                deleteOld();
                deletePageItem();
                printItem(resp);
            }
        }
    )
    getMovieCount(title, ratingString, genreType, yearNum, compareValue, compareOrder);
})

$("#ratingAsc").on("click", function () {
    alert("please wait, it takes long time to load");
    getMovieCount(title, ratingString, genreType, yearNum);
    $.ajax({
            url: contextPath + "/getRequiredMovies.do",
            traditional: true,
            data:{
                selectParams: [title, ratingString, genreType, yearNum],
                sortParams: ["rating", "asc", "0,50"]
            },
            success(resp){
                deleteOld();
                deletePageItem();
                printItem(resp);
            }
        }
    )
})

$("#ratingDesc").on("click", function () {
    alert("please wait, it takes long time to load");
    getMovieCount(title, ratingString, genreType, yearNum);
    $.ajax({
        url: contextPath + "/getRequiredMovies.do",
        traditional: true,
        data:{
            selectParams: [title, ratingString, genreType, yearNum],
            sortParams: ["rating", "desc", "0,50"]
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

/*
$(".title").on("click", function () {
    alert(this.id);
    alert("please wait, it takes long time to load");
});

 */




