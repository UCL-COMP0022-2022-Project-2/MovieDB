window.onload = initial;
var contextPath = $("#contextPath").val();
var chart = $("#charts");
var report = $("#reports");
var resultsTextsArray = [];
var totalAverageScoreArray = [];
var resultsAverageScoreArray = [];
function initial() {
    $("#genreContent").hide()
    $("#content").show()
    $("#tagSubmit").show()
    $("#genreSubmit").hide()
}

$("#tagLink").on("click", function () {
    $("#genreContent").hide()
    $("#content").show()
    $("#tagSubmit").show()
    $("#genreSubmit").hide()
    $.ajax({
        url: contextPath + "/getTagsInitialize.do"
    })
    clearAll()
})

$("#genreLink").on("click", function () {
    $("#genreContent").show()
    $("#content").hide()
    $("#tagSubmit").hide()
    $("#genreSubmit").show()
    clearAll()
    $.ajax({
        url: contextPath + "/getAllGenres.do",
        success(resp) {
            $(".genre").remove()
            printGenres(resp)
        }
    })
})

$(".letter").on("click", function () {
    $.ajax({
        url: contextPath + "/getTagsByInitialLetter/" + this.innerHTML + ".do",
        success(resp) {
            $(".tag").remove()
            printTags(resp)
        }
    })
})

function printGenres(resp) {
    for (var i = 0; i < resp.length; i++) {
        $("#genres").append(
            "<div class = 'col border border-2 genre m-1 justify-content-center d-flex align-items-center p-3'>" +
            resp[i] + "</div>")
    }

    $(".genre").on("click", function () {
        $("#box").append(
            "<div class = 'col border border-2 genreInBox m-1 justify-content-center d-flex align-items-center p-3'>" +
            this.innerHTML +
            "<svg xmlns='http://www.w3.org/2000/svg' width='20' height='20' fill='currentColor' " +
            "class='bi bi-x' viewBox='0 0 16 16'>\n" +
            "  <path d='M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z'/>\n" +
            "</svg></div>")

        $(".genreInBox").on("click", function () {
            $(this).remove();
        })
    })


}

function printTags(resp) {
    for (var i = 0; i < resp.length; i++) {
        $("#tags").append(
            "<div class = 'col border border-2 tag m-1 justify-content-center d-flex align-items-center p-3'>" +
            resp[i] + "</div>")
    }

    $(".tag").on("click", function () {
        $("#box").append(
            "<div class = 'col border border-2 tagInBox m-1 justify-content-center d-flex align-items-center p-3'>" +
            this.innerHTML +
            "<svg xmlns='http://www.w3.org/2000/svg' width='20' height='20' fill='currentColor' " +
            "class='bi bi-x' viewBox='0 0 16 16'>\n" +
            "  <path d='M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z'/>\n" +
            "</svg></div>")

        $(".tagInBox").on("click", function () {
            $(this).remove();
        })
    })


}

$("#tagSubmit").on("click", function () {
    var tagString = [];
    for (var i = 0; i < $(".tagInbox").length; i++) {
        tagString[i] = $(".tagInbox").eq(i).text();
    }
    clearAll();
    $.ajax({
        url: contextPath + "/getTotalTagsAverageRatings.do",
        async: false,
        success(resp) {
            totalAverageScoreArray[0] = resp.openness;
            totalAverageScoreArray[1] = resp.agreeableness;
            totalAverageScoreArray[2] = resp.emotional_stability;
            totalAverageScoreArray[3] = resp.conscientiousness;
            totalAverageScoreArray[4] = resp.extraversion;
        }
    });
    $.ajax({
        url: contextPath + "/getRatingsByTags.do",
        traditional: true,
        async: false,
        data: {
            tags: tagString
        },
        success(resp) {
            var theTagsAverage = resp.theTotalAverage;
            resultsAverageScoreArray[0] = theTagsAverage.openness;
            resultsAverageScoreArray[1] = theTagsAverage.agreeableness;
            resultsAverageScoreArray[2] = theTagsAverage.emotional_stability;
            resultsAverageScoreArray[3] = theTagsAverage.conscientiousness;
            resultsAverageScoreArray[4] = theTagsAverage.extraversion;
            for(var i in resp){
                if(i !== "theTotalAverage"){
                    resultsTextsArray.push(i);
                }
            }
        }
    });
    displayResult('tags')
});

$("#genreSubmit").on("click", function () {
    var genreString = [];
    for (var i = 0; i < $(".genreInBox").length; i++) {
        genreString[i] = $(".genreInBox").eq(i).text();
    }
    clearAll();
    $.ajax({
        url: contextPath + "/getTotalGenresAverageRatings.do",
        async: false,
        success(resp) {
            totalAverageScoreArray[0] = resp.openness;
            totalAverageScoreArray[1] = resp.agreeableness;
            totalAverageScoreArray[2] = resp.emotional_stability;
            totalAverageScoreArray[3] = resp.conscientiousness;
            totalAverageScoreArray[4] = resp.extraversion;
        }
    });
    $.ajax({
        url: contextPath + "/getRatingsByGenres.do",
        traditional: true,
        async: false,
        data: {
            genres: genreString
        },
        success(resp) {
            var theGenresAverage = resp.theTotalAverage;
            resultsAverageScoreArray[0] = theGenresAverage.openness;
            resultsAverageScoreArray[1] = theGenresAverage.agreeableness;
            resultsAverageScoreArray[2] = theGenresAverage.emotional_stability;
            resultsAverageScoreArray[3] = theGenresAverage.conscientiousness;
            resultsAverageScoreArray[4] = theGenresAverage.extraversion;
            for(var i in resp){
                if(i !== "theTotalAverage"){
                    resultsTextsArray.push(i);
                }
            }
        }
    });
    displayResult('genres')
});
$("#clear").on("click", function () {
    $(".tagInBox").remove();
    $(".genreInBox").remove();
});

function clearAll() {
    $(".tagInBox").remove();
    $(".genreInBox").remove();
    chart.empty();
    report.empty();
    resultsTextsArray = [];
    totalAverageScoreArray = [];
    resultsAverageScoreArray = [];
}
function displayResult(tagsOrGenres){
    displayChart(tagsOrGenres);
    displayReport(tagsOrGenres);
}
function displayChart(tagsOrGenres) {
    var mainChart = $("<canvas id=\"mainChart\"></canvas>");
    chart.append(mainChart);
    const labels = [
        'Openness',
        'Agreeableness',
        'Emotional Stability',
        'Conscientiousness',
        'Extraversion'
    ];
    var data = {
        labels: labels,
        datasets: [{
            label: 'Average score for all ' + tagsOrGenres,
            backgroundColor: 'rgb(255,158,0)',
            borderColor: 'rgb(255,158,0)',
            data: totalAverageScoreArray
        }, {
            label: 'Average score for selected ' + tagsOrGenres,
            backgroundColor: 'rgb(26,179,214)',
            borderColor: 'rgb(26,179,214)',
            data: resultsAverageScoreArray
        }]
    };
    const config = {
        type: 'bar',
        data: data,
        options: {}
    };
    new Chart(
        mainChart,
        config
    );
}
function displayReport(tagsOrGenres){
    var line1 = $("<h1 class = \"display-6\" ></h1>").text("The " + tagsOrGenres +" selected by you are:");
    var line2 = $("<h1 class = \"display-6\" ></h1>").text(resultsTextsArray.join(", "));
    var line3 = $("<h1 class = \"display-6\" ></h1>").text("The average personality score of all " + tagsOrGenres + "is:");
    var line4 = $("<h1 class = \"display-6\" ></h1>").text("openness: " + totalAverageScoreArray[0] + "," +
        "agreeableness " + totalAverageScoreArray[1] + "," +
        "emotional stability: " + totalAverageScoreArray[2] + "," +
        "conscientiousness: " + totalAverageScoreArray[3] + "," +
        "extraversion: " + totalAverageScoreArray[4]);
    var line5 = $("<h1 class = \"display-6\" ></h1>").text("The average personality score of selected " + tagsOrGenres + "is:");
    var line6 = $("<h1 class = \"display-6\" ></h1>").text("openness: " + resultsAverageScoreArray[0] + "," +
        "agreeableness " + resultsAverageScoreArray[1] + "," +
        "emotional stability: " + resultsAverageScoreArray[2] + "," +
        "conscientiousness: " + resultsAverageScoreArray[3] + "," +
        "extraversion: " + resultsAverageScoreArray[4]);
    var line7 = $("<h1 class = \"display-6\" ></h1>").text("According to the statistics above, it is likely that the movie with selected" + tagsOrGenres + "will be favored by people who are");
    var line8 = $("<h1 class = \"display-6\" ></h1>").text();
    report.append(line1);
    report.append(line2);
    report.append(line3);
    report.append(line4);
    report.append(line5);
    report.append(line6);
    report.append(line7);
    report.append(line8);
}
