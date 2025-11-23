// Configurações Globais
const canvas = document.getElementById('particulasCanva');
// Verifica se o canvas foi encontrado antes de tentar usar o contexto
if (!canvas) {
    console.error("Canvas element not found!");
}

const ctx = canvas.getContext('2d');

let particles = [];
const particleCount = 100; // Número de pontos
const particleColor = 'rgba(255, 255, 255, 0.8)'; // Cor dos pontos
const lineColor = 'rgba(255, 255, 255, 0.1)'; // Cor das linhas
const lineDistance = 100; // Distância máxima para conectar

// Função para ajustar o tamanho do canvas ao tamanho da janela
function setCanvasSize() {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
}

// Classe que define cada ponto
class Particle {
    constructor() {
        // Posição inicial aleatória
        this.x = Math.random() * canvas.width;
        this.y = Math.random() * canvas.height;
        // Tamanho do ponto (1 a 3)
        this.radius = Math.random() * 2 + 1;
        // Velocidade e direção (flutuação lenta)
        this.velocity = {
            x: (Math.random() - 0.5) * 0.5,
            y: (Math.random() - 0.5) * 0.5
        };
    }

    // Desenha o ponto
    draw() {
        ctx.beginPath();
        ctx.arc(this.x, this.y, this.radius, 0, Math.PI * 2);
        ctx.fillStyle = particleColor;
        ctx.fill();
    }

    // Atualiza a posição do ponto
    update() {
        this.x += this.velocity.x;
        this.y += this.velocity.y;

        // Efeito de "loop" (reaparece no lado oposto)
        if (this.x < 0) this.x = canvas.width;
        if (this.x > canvas.width) this.x = 0;
        if (this.y < 0) this.y = canvas.height;
        if (this.y > canvas.height) this.y = 0;
    }
}

// Inicializa os pontos
function initParticles() {
    setCanvasSize();
    particles = [];
    for (let i = 0; i < particleCount; i++) {
        particles.push(new Particle());
    }
}

// Conecta os pontos próximos
function connectParticles() {
    for (let i = 0; i < particles.length; i++) {
        for (let j = i; j < particles.length; j++) {
            const dx = particles[i].x - particles[j].x;
            const dy = particles[i].y - particles[j].y;
            const distance = Math.sqrt(dx * dx + dy * dy);

            if (distance < lineDistance) {
                // Desenha a linha, com opacidade baseada na distância
                ctx.beginPath();
                ctx.strokeStyle = lineColor;
                ctx.lineWidth = 1;
                ctx.globalAlpha = 1 - (distance / lineDistance);
                ctx.moveTo(particles[i].x, particles[i].y);
                ctx.lineTo(particles[j].x, particles[j].y);
                ctx.stroke();
                ctx.globalAlpha = 1; // Reseta a opacidade
            }
        }
    }
}

// Loop principal da animação
function animate() {
    requestAnimationFrame(animate);
    ctx.clearRect(0, 0, canvas.width, canvas.height); // Limpa a tela

    connectParticles();

    // Atualiza e desenha cada ponto
    particles.forEach(p => {
        p.update();
        p.draw();
    });
}

// Execução
if (canvas) {
    window.addEventListener('resize', initParticles); // Recria os pontos ao redimensionar
    initParticles(); // Cria os pontos iniciais
    animate(); // Inicia a animação
}