function pullData(){
    let date = new Date();
    let hours = (date.getHours() < 10) ? '0' + date.getHours() : date.getHours();
    let minutes = (date.getMinutes() < 10) ? '0' + date.getMinutes() : date.getMinutes();
    let sec = (date.getSeconds() < 10) ? '0' + date.getSeconds() : date.getSeconds();
    document.getElementById('clocks').innerHTML = hours + ':' + minutes + ':' + sec;
}

function clocks(interval){
    pullData();
    setInterval(pullData, interval);
}