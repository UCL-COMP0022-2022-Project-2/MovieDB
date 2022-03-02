var contextPath = $("#contextPath").val();

$("#getAllPosts").on("click", function () {
    alert("please wait, it takes long time to load");
    $.ajax({
        url: contextPath + "/getAllMovies.do",
        success(resp){
            for(var i = 0; i < resp.length; i++){
                console.log(resp[i])
            }
        }
    })
});

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
            for(var i = 0; i < resp.length; i++){
                console.log(resp[i])
            }
        }
    })
});

$("#getRequiredPosts").on("click", function () {
    alert("please wait, it takes long time to load");
    $.ajax({
        url: contextPath + "/getRequiredMovies.do",
        traditional: true,
        data:{
            selectParams: ["", "", "War", ""],
            sortParams: ["year", "asc"]
        },
        success(resp){
            for(var i = 0; i < resp.length; i++){
                console.log(resp[i])
            }
        }
    })
});

$("#getReport").on("click", function () {
    alert("please wait, it takes long time to load");
    var movieId = 5;
    $.ajax({
        url: contextPath + "/getReportsById/" + movieId + ".do",
        success(resp){
            for(var i = 0; i < resp.length; i++){
                console.log(resp[i])
            }
        }
    })
});

$("#getFirst50Movies").on("click", function () {
    alert("please wait, it takes long time to load");
    $.ajax({
        url: contextPath + "/getRequiredMovies.do",
        traditional: true,
        data:{
            selectParams: ["", "", "", ""],
            sortParams: ["", "", "0,50"]
        },
        success(resp){
            for(var i = 0; i < resp.length; i++){
                console.log(resp[i])
            }
        }
    })
});
$("#getMovieCount").on("click", function () {
    alert("please wait, it takes long time to load");
    $.ajax({
        url: contextPath + "/getMoviesCount.do",
        success(resp){
            console.log(resp)
        }
    })

});
$("#newGetReport").on("click", function () {
    alert("please wait, it takes long time to load");
});
