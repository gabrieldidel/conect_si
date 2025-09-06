const loginForm = document.getElementById('loginForm');
const loginMessage = document.getElementById('loginMessage');
const API_URL = 'http://localhost:8080/usuarios';

loginForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;

    fetch(API_URL)
        .then(res => res.json())
        .then(usuarios => {
            const usuario = usuarios.find(u => u.email === email && u.senha === senha);
            if (usuario) {
                localStorage.setItem('usuarioLogado', JSON.stringify(usuario));
                window.location.href = 'home-logged.html';
            } else {
                loginMessage.textContent = 'Email ou senha incorretos';
            }
        })
        .catch(err => {
            loginMessage.textContent = 'Erro ao conectar ao servidor';
            console.error(err);
        });
});
