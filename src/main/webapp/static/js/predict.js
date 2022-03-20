window.onload = initial;

function initial(){
    $("#content").show();
    $("#noContent").hide();
}

$(".letter").on("click", function (){
    var letter = this.innerHTML
    $.ajax({
        url: "/MovieDB_war_exploded/getTagsByInitialLetter/" + letter + ".do",
        success(resp){
            $(".tag").remove()
            printTags(resp)
        }
    })
})

function printTags(resp){
    for(var i = 0; i < resp.length; i++){
        $("#tags").append(
            "<div class = 'col border border-2 tag m-1 justify-content-center d-flex align-items-center p-3'>"+
            resp[i] +"</div>")
    }

    $(".tag").on("click", function (){
        $("#tagBox").append(
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
$("#submit").on("click", function(){
    var tagString = [];
    for(var i = 0; i < $(".tagInbox").length; i++){
        tagString[i] = $(".tagInbox").eq(i).text();
    }
    alert(tagString)
    $.ajax({
        url:  + "/MovieDB_war_exploded/getPersonalitiesByTags.do",
        traditional: true,
        data:{
            tagString
        },
        success(resp){
            console.log(resp)
        }
    })
})

