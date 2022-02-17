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
    for(var i = 0; i < resp.length; i++) {
        $("#content").append("<div class = 'row itemRow'></div>");
    }
    $(".itemRow").append("<div class = 'col-3'></div><div class = 'col-2'></div><div class = 'col-1'></div>" +
        "<div class = 'col-6'></div>");

    $(".col-3").append("<p class = 'title'></p>");
    $(".col-2").append("<p class = 'rating'></p>");
    $(".col-1").append("<p class = 'year'></p>");
    $(".col-6").append("<p class = 'genres'></p>");

    var titles = document.querySelectorAll(".title");
    var ratings = document.querySelectorAll(".rating");
    var years = document.querySelectorAll(".year");
    var genres = document.querySelectorAll(".genres");

    for(var i = 0; i < resp.length; i++) {
        titles[i].innerHTML = resp[i]["title"];
        ratings[i].innerHTML = resp[i]["rating"];
        years[i].innerHTML = resp[i]["year"];
        genres[i].innerHTML = resp[i]["genres"];
    }
}

$("#searchByName").on("click", function () {
    alert("please wait, it takes long time to load");
    var title = $("#searchBar").val();
    $.ajax({
        url: contextPath + "/getRequiredMovies.do",
        traditional: true,
        data:{
            selectParams: [title, "", "", ""],
            sortParams: ["", ""]
        },
        success(resp){
            deleteOld();
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
            selectParams: ["", "", "", ""],
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
                selectParams: ["", "", "", ""],
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
                selectParams: ["", "", "", ""],
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
                selectParams: ["", "", "", ""],
                sortParams: ["rating", "desc"]
            },
            success(resp){
                deleteOld();
                printItem(resp);
            }
        }
    )
})



