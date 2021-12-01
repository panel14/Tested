let st_canv;
let wight;
let height;
let SCALE_COEF = 0.35;
let xGlobal = -2;

$('input[type=checkbox]').change( function (){
    $('input[type=checkbox]').not(this).prop('checked', false);
    xGlobal = $('input[type=checkbox]:checked').val();
});

function init(){
    paintGraph();
    nullX();
    setX();
}

function paintGraph(){
    let canvas = document.getElementById("paint");
    st_canv = canvas.getContext("2d");
    wight = canvas.width;
    height = canvas.height;
    printField(st_canv, wight, height);
    printGraph(st_canv, wight, height);
    canvas.addEventListener("click", canvasClick, false);
}

function printField(canvas_context, wight, height){
    for (let x = 0.5; x < wight; x+= 10){
        canvas_context.moveTo(x, 0);
        canvas_context.lineTo(x, height);
    }
    for (let y = 0.5; y < height; y+= 10){
        canvas_context.moveTo(0, y);
        canvas_context.lineTo(wight, y);
    }
    canvas_context.strokeStyle = "#404040";
    canvas_context.stroke();

    canvas_context.fillStyle = "#ff5050";
    canvas_context.font = "bold 12px sans-serif";
    canvas_context.textBaseline = "middle";
    canvas_context.textAlign = "center";

    canvas_context.beginPath();
    canvas_context.moveTo(wight / 2, height);                  //y-coordLine
    canvas_context.lineTo(wight / 2, height * 0.85)
    getKockaV(canvas_context, wight / 2, height * 0.85);
    canvas_context.fillText("-R", wight / 2 + 15, height * 0.85);

    canvas_context.lineTo(wight / 2, height * 0.675);
    getKockaV(canvas_context, wight / 2, height * 0.675);
    canvas_context.fillText("-R/2", wight / 2 + 20, height * 0.675);

    canvas_context.lineTo(wight / 2, height * 0.325);
    getKockaV(canvas_context, wight / 2, height * 0.325);
    canvas_context.fillText("R/2", wight / 2 + 20, height * 0.325);

    canvas_context.lineTo(wight / 2, height * 0.15);
    getKockaV(canvas_context, wight / 2, height * 0.15);
    canvas_context.fillText("R", wight / 2 + 15, height * 0.15);

    canvas_context.lineTo(wight / 2, height * 0.125);
    canvas_context.moveTo(wight / 2, height * 0.075);
    canvas_context.lineTo(wight / 2, 0);

    canvas_context.moveTo(wight / 2 - 5, 5);                //y-arrow
    canvas_context.lineTo(wight / 2, 0);
    canvas_context.lineTo(wight / 2 + 5, 5);

    canvas_context.moveTo(0, height / 2);                   //x-coordLine
    canvas_context.lineTo(wight * 0.15, height / 2);
    getKockaG(canvas_context, wight * 0.15, height / 2);
    canvas_context.fillText("-R", wight * 0.15 , height / 2 - 15);

    canvas_context.lineTo(wight * 0.325, height / 2);
    getKockaG(canvas_context, wight * 0.325, height / 2);
    canvas_context.fillText("-R/2", wight * 0.325 , height / 2 - 15);

    canvas_context.lineTo(wight * 0.675, height / 2);
    getKockaG(canvas_context, wight * 0.675, height / 2);
    canvas_context.fillText("R/2", wight * 0.675 , height / 2 - 15);

    canvas_context.lineTo(wight * 0.85, height / 2);
    getKockaG(canvas_context, wight * 0.85, height / 2);
    canvas_context.fillText("R", wight * 0.85 , height / 2 - 15);

    canvas_context.lineTo(wight * 0.875, height / 2);
    canvas_context.moveTo(wight * 0.925, height / 2);
    canvas_context.lineTo(wight, height / 2);

    canvas_context.moveTo(wight - 5, height / 2 - 5);       //x-arrow
    canvas_context.lineTo(wight, height / 2);
    canvas_context.lineTo(wight -5, height / 2 + 5);

    canvas_context.strokeStyle = "#ff5050";
    canvas_context.stroke();

    canvas_context.fillText("x", wight - 40, height / 2);
    canvas_context.fillText("y", wight / 2, height - 360);
    canvas_context.fillText("(0 ; 0)", wight / 2 - 20, height / 2 + 15);
}

function getKockaV(canvas_context, wight, height){
    canvas_context.moveTo(wight - 5, height);
    canvas_context.lineTo(wight + 5, height);
    canvas_context.moveTo(wight, height)
}

function getKockaG(canvas_context, wight, height){
    canvas_context.moveTo(wight, height - 5);
    canvas_context.lineTo(wight, height + 5);
    canvas_context.moveTo(wight, height);
}

function printGraph(canvas_context, wight, height){
    canvas_context.moveTo(wight / 2, height * 0.15);
    canvas_context.lineTo(wight * (0.5 + SCALE_COEF / 2), height / 2);
    canvas_context.moveTo(wight / 2, height * 0.85);
    canvas_context.lineTo(wight * 0.15, height * 0.85);
    canvas_context.lineTo(wight * 0.15, height / 2);
    canvas_context.arc(wight / 2, height / 2, height * (SCALE_COEF / 2), Math.PI, Math.PI* 3 / 2, false);
    canvas_context.strokeStyle = "#ff5555";
    canvas_context.stroke();
}

function canvasClick(e){
    let x = e.pageX;
    let y = e.pageY;
    getCoord(x, y);
}

function getCoord(x, y){
    let r = document.getElementById('dataform:rtxt').value;
    if (r > 0){
        x -= document.getElementById('paint').offsetLeft;
        y -= document.getElementById('paint').offsetTop;

        let points = convertToSend(x, y, r);
        fillHidden(points[0], points[1], r);
        let hButton = document.getElementById('hiddenForm:hButton');
        hButton.click();
        event.preventDefault();
    }
    else
        alert('Выберите R')
}

function fillHidden(x, y, r){
    document.getElementById('hiddenForm:hiddenX').value = x.toFixed(2);
    document.getElementById('hiddenForm:hiddenY').value = y.toFixed(2);
    document.getElementById('hiddenForm:hiddenR').value = r;
    document.getElementById('dataform:yInput').value = y.toFixed(2);
}

function printPoint(coords, answer){
    let points = convertToPrint(coords[0], coords[1], coords[2]);
    st_canv.beginPath();
    if (answer === "Yes")
        st_canv.fillStyle = "#04a242"
    else
        st_canv.fillStyle = "#ff5555"
    st_canv.arc(points[0] , points[1] , 3, Math.PI * 2, 0, false);
    st_canv.fill();
}

function printForRad(rad){
    st_canv.clearRect(0, 0, wight, height);
    paintGraph();
    let score = document.getElementById('resData').rows.length;
    for (let i = 0; i < score; i++){
        let point = getCoordsFromRow(i);
        if (point[2] === rad)
            printPoint(point, getAnswerFromRow(i))
    }
}

function convertToSend(x, y, r){
    x -= 200;
    x = (x * r)/(wight * SCALE_COEF);
    y = -y + 200;
    y = (y * r)/(height * SCALE_COEF);
    return [x, y]
}

function convertToPrint(x, y, r){
    x = (x * wight * SCALE_COEF)/r;
    x += 200;
    y = (y * height * SCALE_COEF)/r;
    y = -y + 200;
    return [x , y]
}

function prosData(data){
    if (data.status === 'success'){
        let score = getScore();
        let point = getCoordsFromRow(score);
        let answer = getAnswerFromRow(score);
        printPoint(point, answer);
    }
}

function nullX(){
    let cBoxes = document.querySelectorAll('input[type=checkbox]');
    cBoxes.forEach(el => el.checked = false);
}

function setX(){
    let xBox = document.querySelector('input[type=checkbox][value="0"]').checked = true;
}

function getScore(){
    return document.getElementById('resData').rows.length - 1;
}

function getCoordsFromRow(row){
    let table = document.getElementById('resData');
    let x = parseFloat(table.rows[row].cells[0].innerText);
    let y = parseFloat(table.rows[row].cells[1].innerText);
    let r = parseFloat(table.rows[row].cells[2].innerText);
    return [x , y , r];
}

function getAnswerFromRow(row){
    let table = document.getElementById('resData');
    return table.rows[row].cells[5].innerText;
}

