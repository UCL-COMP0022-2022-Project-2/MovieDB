window.onload = initial;
var contextPath = $("#contextPath").val();
var chart = $("#charts");
var report = $("#reports");
var personalities = $("#personalities");
var resultsTextsArray = [];
var totalAverageScoreArray = [];
var resultsAverageScoreArray = [];
var currentPersonality = [4,4,4,4,4];
function initial() {
    $("#genreContent").hide()
    $("#content").show()
    $("#tagSubmit").show()
    $("#genreSubmit").hide()
}

$("#tagLink").on("click", function () {
    $("#predictType").text("Predict Who Will Like the Movie Based on Tags")
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
    $("#predictType").text("Predict Who Will Like the Movie Based on Genres")
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
    personalities.empty();
    resultsTextsArray = [];
    totalAverageScoreArray = [];
    resultsAverageScoreArray = [];
    currentPersonality = [4,4,4,4,4];
}


function displayResult(tagsOrGenres){
    displayChart(tagsOrGenres);
    displayReport(tagsOrGenres);
    displayPersonalities();
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
    var Personalities = ["open", "agreeable", "emotional stable", "conscientious", "extravert"];
    var positiveRatedPersonalities = [];
    for(var i = 0; i < 5; i++){
        if(resultsAverageScoreArray[i] > 0){
            positiveRatedPersonalities.push(Personalities[i]);
        }
    }
    var higherThanAveragePersonalities = [];
    for(var i = 0; i < 5; i ++){
        if(resultsAverageScoreArray[i] >= totalAverageScoreArray[i]){
            higherThanAveragePersonalities.push(Personalities[i])
        }
    }

    var line1 = $("<h2></h2>").text("The " + tagsOrGenres +" selected by you are:");
    var line2 = $("<h4 ></h4>").text(resultsTextsArray.join(", "));
    var line3 = $("<h2></h2>").text("The average personality score of selected " + tagsOrGenres + " is:");
    var line4 = $("<h4 ></h4>").text("openness: " + resultsAverageScoreArray[0] + ", " +
        "agreeableness " + resultsAverageScoreArray[1] + ", " +
        "emotional stability: " + resultsAverageScoreArray[2] + ", " +
        "conscientiousness: " + resultsAverageScoreArray[3] + ", " +
        "extraversion: " + resultsAverageScoreArray[4]);
    var line5 = $("<h2></h2>").text("So, the movies with selected " + tagsOrGenres +
        " is going to be rated positively (higher than the median score 2.75) by people who are");
    var line6 =  $("<h4 ></h4>").text(positiveRatedPersonalities.join(", "));
    var line7 = $("<h2></h2>").text("The average personality score of all " + tagsOrGenres + " is:");
    var line8 = $("<h4 ></h4>").text("openness: " + totalAverageScoreArray[0] + ", " +
        "agreeableness " + totalAverageScoreArray[1] + ", " +
        "emotional stability: " + totalAverageScoreArray[2] + ", " +
        "conscientiousness: " + totalAverageScoreArray[3] + ", " +
        "extraversion: " + totalAverageScoreArray[4]);
    var line9 = $("<h2></h2>").text("By comparing with the average score, it is likely that the movie with selected "
        + tagsOrGenres + " will be rated higher than average movie ratings by people who are");
    var line10 = $("<h4 ></h4>").text(higherThanAveragePersonalities.join(", "));
    report.append(line1);
    report.append(line2);
    report.append($("<hr>"));
    report.append(line3);
    report.append(line4);
    report.append($("<hr>"));
    report.append(line5);
    report.append(line6);
    report.append($("<hr>"));
    report.append(line7);
    report.append(line8);
    report.append($("<hr>"));
    report.append(line9);
    report.append(line10);
    report.append($("<hr>"));
}
function displayPersonalities() {
    var topic = $("<h2></h2>").text("Drag the chart and get the rating ( 0.5 - 5 ) of this movie by changing " +
        "the personality of the user");
    var topic1 = $("<h2></h2>").text("E.g. 1 is not open at all, 7 is extremely open");
    var canvasContainer = $("<div class=\"chart-container\" style=\"position: relative; height:100vh; width:80vw\"></div>");
    var canvas = $("<canvas id=\"chartJSContainer\" style=\"height: 75%; width: 75%;\"></canvas>");
    canvasContainer.append(canvas);
    var row = $("<div class=\"row\"></div>");
    var text = $("<h4 class='col-6'></h4>").text("The rating of this movie by this user is:");
    var num = $("<h4 class='col-2' id='num'></h4>");
    var button = $("<button type=\"button\" class=\"btn btn-dark col-4\">Calculate</button>");
    button.on("click", function calculateScoreForThisMovie(){
        var baseScore = [2.75, 2.75, 2.75, 2.75, 2.75];
        for(var i = 0; i < 5; i++){
            baseScore[i] = baseScore[i] + (resultsAverageScoreArray[i]/3) * ((currentPersonality[i] -4)/3);
        }
        console.log(baseScore);
        const sum = baseScore.reduce((a, b) => a + b, 0);
        var avg = (sum / baseScore.length) || 0;
        avg = avg.toFixed(2);
        $("#num").text(avg);
    });
    row.append(text);
    row.append(num);
    row.append(button);
    personalities.append(topic);
    personalities.append(topic1);
    personalities.append($("<hr>"));
    personalities.append(row);
    personalities.append($("<hr>"));
    personalities.append(canvasContainer);

    var options = {
        type: 'radar',
        data: {
            labels: ["openness", "agreeableness", "emotional stability", "conscientiousness", "extraversion"],
            datasets: [{
                label: 'Personality',
                data: currentPersonality,
                pointHitRadius: 25
            }]
        },
        options: {
            responsive: false,
            onHover: function(e) {
                const point = e.chart.getElementsAtEventForMode(e, 'nearest', { intersect: true }, false)
                if (point.length) e.native.target.style.cursor = 'grab';
                else e.native.target.style.cursor = 'default'
            },
            plugins: {
                dragData: {
                    round: 1,
                    showTooltip: true,
                    onDragStart: function(e) {
                        // console.log(e)
                    },
                    onDrag: function(e, datasetIndex, index, value) {
                        e.target.style.cursor = 'grabbing'
                        // console.log(e, datasetIndex, index, value)
                    },
                    onDragEnd: function(e, datasetIndex, index, value) {
                        e.target.style.cursor = 'default'
                        // console.log(datasetIndex, index, value)
                    },
                }
            },
            scales: {
                r: {
                    max: 7,
                    min: 1,
                    stepSize: 1,
                    grid: {
                        color: ['gray','gray', 'green','gray','gray','gray']
                    }
                }
            }
        }
    };
    var ctx = document.getElementById('chartJSContainer').getContext('2d');
    window.test = new Chart(ctx, options);
}

