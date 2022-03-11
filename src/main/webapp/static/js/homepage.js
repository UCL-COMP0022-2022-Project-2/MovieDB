var contextPath = $("#contextPath").val();
var title = "";
var ratingString = "";
var yearNum = "";
var genreType = "";
var movieCount = "";
var pageCount = "";
var leftCount = "";
var currentPage = "1";
var limit = "";
var compareValue = "";
var compareOrder = "";
var inWhichDecade = "";
// set All global variables to blank

//This is an information block which inform user the page number and the sort method
function inform(){
    $("#information").remove();

    var orderString = "";
    if(compareOrder === "asc"){
        orderString = "Ascending";
    }else if (compareOrder === "desc"){
        orderString = "Descending";
    }

    var sortString = "";
    if(compareValue === "title"){
        sortString = "Title";
    }else if(compareValue === "rating"){
        sortString = "Rating";
    }else if(compareValue === "year"){
        sortString = "Year";
    }

    var info = "<div class = 'row alert alert-primary mx-1 mb-3 pt-3'  id = 'information'><i class = 'text-body'>" +
        "Sorted By <u>" + sortString + "</u> In <u>" + orderString +
        "</u> Order, Page Number <u>" + currentPage +"</u>  Limit: " + limit + "</i></div>";
    $("#tableHead").before(info);
}

// Separation Pages Function Part
//This is a function used by separation pages to print items
function printSelectPage(){
    if (currentPage === pageCount){
        limit = (currentPage - 1) * 50 + "," + ((currentPage - 1) * 50 + leftCount);
    } else {
        limit = (currentPage - 1) * 50 + "," + currentPage * 50;
    }

    $.ajax({
        url: contextPath + "/getRequiredMovies.do",
        traditional: true,
        data: {
            selectParams: [title, ratingString, genreType, yearNum],
            sortParams: [compareValue, compareOrder, limit]
        },
        success(resp) {
            deleteOld();
            printItem(resp);
        }
    })
}
//This is the button to get all movies
$("#getAllPosts").on("click", function() {

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
//This is the function to pass variables when choosing separation pages and get the count of required movies
function getMovieCount(cTitle, cRating, cYear, cGenre){
    $.ajax({
        url: contextPath + "/getMoviesCount.do",
        traditional: true,
        data:{
            selectParams: [cTitle, cRating, cYear, cGenre],
            sortParams: ["", "", ""]
        },
        success(resp){
            printPageItem(resp);
        }
    })
}
// This is the main function to separate pages
function printPageItem(resp){
    $("#footer").css("display", "block");
    // receive the parameter of movie counts from the previous function
    movieCount = resp;
    pageCount = Math.floor(movieCount / 50) + 1;
    leftCount = movieCount % 50;

    var pageList = "";
    // create all the page separation blocks
    for(var i = 1; i <= pageCount; i++){
        pageList += '<li class="page-item pageContent"><a class="page-link text-dark" href="#">'+i+'</a></li>';
    }
    // add blocks to HTML
    $("#previousTenPages").after(pageList);

    // This is the event which is for selecting next ten pages button
    $("#nextTenPages").on("click", function(){

        //divide pages by ten, get the divider for the number of decades and set current page number to 10n+1
        inWhichDecade = Math.floor(currentPage / 10) + 1;
        currentPage = inWhichDecade * 10 + 1;

        // only when all page number over 10 and not the last ten items this button would work
        if(pageCount > 10 && inWhichDecade !== (Math.floor(pageCount / 10))+1) {

            // use print function to print the 10n+1 page
            printSelectPage();

            // change page separation buttons to the next ten ones
            $(".pageContent").slice((inWhichDecade - 1) * 10, inWhichDecade * 10).hide();
            if (inWhichDecade === Math.floor(pageCount) / 10 + 1) {
                $(".pageContent").slice(inWhichDecade * 10, pageCount).show();
            } else {
                $(".pageContent").slice(inWhichDecade * 10, (inWhichDecade + 1) * 10).show();
            }
        }
    })

    // This is similar to the next ten one
    $("#previousTenPages").on("click", function(){
        if(currentPage > 10) {
            inWhichDecade = Math.floor(currentPage / 10) + 1;
            currentPage = (inWhichDecade-2) * 10 + 1;

            printSelectPage();
            if (inWhichDecade === Math.floor(pageCount) / 10 + 1) {
                $(".pageContent").slice((inWhichDecade - 1) * 10, pageCount).hide();
            } else{
                $(".pageContent").slice((inWhichDecade - 1) * 10, inWhichDecade * 10).hide();
            }

            $(".pageContent").slice((inWhichDecade - 2) * 10, (inWhichDecade - 1) * 10).show();

        }
    })

    // This is the function button to return to the first page and change separation page blocks to the first ten ones
    $("#firstPage").on("click", function(){
        currentPage = 1;
        printSelectPage();
        $(".pageContent:gt(10)").hide();
        $(".pageContent:lt(10)").show();
    })
    // similar to the previous one
    $("#finalPage").on("click", function (){
        currentPage = pageCount;
        printSelectPage();
        $(".pageContent").slice((Math.floor(pageCount/10)*10) , pageCount).show();
        $(".pageContent").slice(0, Math.floor(pageCount/10)*10).hide();
    })

    // first hide other separation pages blocks other than first ten ones
    if(pageCount > 10){
        $(".pageContent:gt(9)").hide();
    }

    // the final event to obtain the current page through the separation pages button and print the relevant page
    $(".pageContent").on("click", function(){
        var hrefContent = this.innerHTML;
        currentPage = hrefContent.match((/\d+/g));
        printSelectPage()
    });
}
// The Selection Function Part
//The primary function to print the first 50 items after selection
function printItem(resp){
    let i;

    //use the information block
    inform();

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
        $("#content").append("<div class = 'row itemRow my-2 mx-2'>"+ (i+1) + "</div>");
    }

    // add parameters to each row
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

    for(i = 0; i < resp.length; i++) {
        titles[i].innerHTML = resp[i]["title"];
        ratings[i].innerHTML = resp[i]["rating"];
        years[i].innerHTML = resp[i]["year"];
        genres[i].innerHTML = resp[i]["genres"];

        titleHref = "getReportsById/" + resp[i]["movieId"] + "/" + resp[i]["title"] + "/" + resp[i]["rating"] + ".do";
        titles[i].href = titleHref
    }

}
// This is also a check function
/*$("#check").on("click", function (){
    alert("title: " + title + "rating: " + ratingString + "year: " + yearNum + "genre: " + genreType);
});*/

// Main selection event to pass parameter
$("#searchByName").on("click", function () {

    // obtain parameters
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

    compareValue = "";
    compareOrder = "";

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

    // pass parameter and get the count
    getMovieCount(title, ratingString, genreType, yearNum);
    limit = "0,50";

    // print first 50 items after selection
    $.ajax({
        url: contextPath + "/getRequiredMovies.do",
        traditional: true,
        data:{
            selectParams: [title, ratingString, genreType, yearNum],
            sortParams: [compareValue,compareOrder,limit]
        },
        success(resp){
            deleteOld();
            deletePageItem();
            printItem(resp);
        }
    })
});

// a function to delete old table items
function deleteOld(){
    $(".itemRow").remove();
}
// a funtion to delete old separation pages blocks
function deletePageItem(){
    $(".pageContent").remove();
}

//The sorting parts
// sort by title, asc
$("#titleAsc").on("click", function () {

    getMovieCount(title, ratingString, genreType, yearNum);

    limit = "0,50";
    currentPage = "1";
    compareValue = "title";
    compareOrder = "asc";


    $.ajax({
            url: contextPath + "/getRequiredMovies.do",
            traditional: true,
            data:{
                selectParams: [title, ratingString, genreType, yearNum],
                sortParams: [compareValue, compareOrder, limit]
            },
            success(resp){
                deleteOld();
                deletePageItem();
                printItem(resp);
            }
        }
    )
})
// title, desc
$("#titleDesc").on("click", function () {

    getMovieCount(title, ratingString, genreType, yearNum);

    limit = "0,50";
    currentPage = "1";
    compareValue = "title";
    compareOrder = "desc";

    $.ajax({
            url: contextPath + "/getRequiredMovies.do",
            traditional: true,
            data:{
                selectParams: [title, ratingString, genreType, yearNum],
                sortParams: ["title", "desc", limit]
            },
            success(resp){
                deleteOld();
                deletePageItem();
                printItem(resp);
            }
        }
    )
})
// rating, asc
$("#ratingAsc").on("click", function () {

    getMovieCount(title, ratingString, genreType, yearNum);

    limit = "0,50";
    currentPage = "1";
    compareValue = "rating";
    compareOrder = "asc";

    $.ajax({
            url: contextPath + "/getRequiredMovies.do",
            traditional: true,
            data:{
                selectParams: [title, ratingString, genreType, yearNum],
                sortParams: [compareValue, compareOrder, limit]
            },
            success(resp){
                deleteOld();
                deletePageItem();
                printItem(resp);
            }
        }
    )
})
// rating, desc
$("#ratingDesc").on("click", function () {

    getMovieCount(title, ratingString, genreType, yearNum);

    limit = "0,50";
    currentPage = "1";
    compareValue = "rating";
    compareOrder = "desc";

    $.ajax({
        url: contextPath + "/getRequiredMovies.do",
        traditional: true,
        data:{
            selectParams: [title, ratingString, genreType, yearNum],
            sortParams: [compareValue, compareOrder, limit]
        },
        success(resp){
            deleteOld();
            deletePageItem();
            printItem(resp);
        }
    })
})
// year, asc
$("#yearAsc").on("click", function () {

    getMovieCount(title, ratingString, genreType, yearNum);

    limit = "0,50";
    currentPage = "1";
    compareValue = "year";
    compareOrder = "asc";

    $.ajax({
            url: contextPath + "/getRequiredMovies.do",
            traditional: true,
            data:{
                selectParams: [title, ratingString, genreType, yearNum],
                sortParams: [compareValue, compareOrder, limit]
            },
            success(resp){
                deleteOld();
                deletePageItem();
                printItem(resp);
            }
        }
    )
})
// year, desc
$("#yearDesc").on("click", function () {
    getMovieCount(title, ratingString, genreType, yearNum);

    limit = "0,50";
    currentPage = "1";
    compareValue = "year";
    compareOrder = "desc";

    $.ajax({
            url: contextPath + "/getRequiredMovies.do",
            traditional: true,
            data:{
                selectParams: [title, ratingString, genreType, yearNum],
                sortParams: [compareValue, compareOrder, limit]
            },
            success(resp){
                deleteOld();
                deletePageItem();
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
