//Carrossel
let count = 1;

function active_radio(){
    count++;
    if(count > 3){
        count = 1;
    }
    document.getElementById("radio" + count).checked = true;
}
setInterval(active_radio, 3000);

//Menu
