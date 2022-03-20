window.onload = initial;
var contextPath = $("#contextPath").val();

function initial(){
    $("#genreContent").hide()
    $("#content").show()
    $("#tagSubmit").show()
    $("#genreSubmit").hide()
}

$("#tagLink").on("click", function(){
    $("#genreContent").hide()
    $("#content").show()
    $("#tagSubmit").show()
    $("#genreSubmit").hide()
    $.ajax({
        url: contextPath + "/getTagsInitialize.do"
    })
    clearAll()
})

$("#genreLink").on("click", function(){
    $("#genreContent").show()
    $("#content").hide()
    $("#tagSubmit").hide()
    $("#genreSubmit").show()
    clearAll()
    $.ajax({
        url: contextPath + "/getAllGenres.do",
        success(resp){
            $(".genre").remove()
            printGenres(resp)
        }
    })
})

$(".letter").on("click", function(){
    $.ajax({
        url: contextPath + "/getTagsByInitialLetter/" + this.innerHTML + ".do",
        success(resp){
            $(".tag").remove()
            printTags(resp)
        }
    })
})

function printGenres(resp){
    for(var i = 0; i < resp.length; i++){
        $("#genres").append(
            "<div class = 'col border border-2 genre m-1 justify-content-center d-flex align-items-center p-3'>"+
            resp[i] +"</div>")
    }

    $(".genre").on("click", function (){
        $("#box").append(
            "<div class = 'col border border-2 genreInBox m-1 justify-content-center d-flex align-items-center p-3'>" +
            this.innerHTML +
            "<svg xmlns='http://www.w3.org/2000/svg' width='20' height='20' fill='currentColor' " +
            "class='bi bi-x' viewBox='0 0 16 16'>\n" +
            "  <path d='M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z'/>\n" +
            "</svg></div>")

        $(".genreInBox").on("click", function (){
            $(this).remove();
        })
    })


}
function printTags(resp){
    for(var i = 0; i < resp.length; i++){
        $("#tags").append(
            "<div class = 'col border border-2 tag m-1 justify-content-center d-flex align-items-center p-3'>"+
            resp[i] +"</div>")
    }

    $(".tag").on("click", function (){
        $("#box").append(
            "<div class = 'col border border-2 tagInBox m-1 justify-content-center d-flex align-items-center p-3'>" +
            this.innerHTML +
            "<svg xmlns='http://www.w3.org/2000/svg' width='20' height='20' fill='currentColor' " +
            "class='bi bi-x' viewBox='0 0 16 16'>\n" +
            "  <path d='M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z'/>\n" +
            "</svg></div>")

        $(".tagInBox").on("click", function (){
            $(this).remove();
        })
    })


}
$("#tagSubmit").on("click", function(){
    var tagString = [];
    for(var i = 0; i < $(".tagInbox").length; i++){
        tagString[i] = $(".tagInbox").eq(i).text();
    }
    $.ajax({
        url:  contextPath + "/getRatingsByTags.do",
        traditional: true,
        data:{
            tags: tagString
        },
        success(resp){
            console.log(resp)
        }
    })
})
$("#genreSubmit").on("click", function(){
    var genreString = []
    for(var i = 0; i < $(".genreInBox").length; i++){
        genreString[i] = $(".genreInBox").eq(i).text();
    }
    $.ajax({
        url:  contextPath + "/getRatingsByGenres.do",
        traditional: true,
        data:{
            genres: genreString
        },
        success(resp){
            console.log(resp)
        }
    })
})
$("#clear").on("click", function (){
    clearAll()
})
function clearAll(){
    $(".tagInBox").remove()
    $(".genreInBox").remove()
}
