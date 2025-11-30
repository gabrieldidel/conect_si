// Carrossel do inforamtivo

const slider = document.querySelectorAll('.slider');
const prevBtn = document.getElementById('prevBtn');
const nextBtn = document.getElementById('nextBtn');

let contadorSlide = 0;

function escondeSlider(){
    slider.forEach(item => item.classList.remove('on'));
}

function mostraSlide(){
    slider[contadorSlide].classList.add('on');
}

function avancar(){
    escondeSlider();

  if (contadorSlide == slider.length - 1){
    contadorSlide = 0;
  }
  else{
    contadorSlide++;
  }
  
  mostraSlide();
}

function voltar(){
  escondeSlider();

  if (contadorSlide == 0){
    contadorSlide = slider.length - 1;
  }
  else{
    contadorSlide--;
  }
  
  mostraSlide();
}

nextBtn.addEventListener('click', avancar);
prevBtn.addEventListener('click', voltar);
