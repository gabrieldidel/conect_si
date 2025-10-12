const usuario = JSON.parse(localStorage.getItem('jwtToken'));
const nomeUsuarioSpan = document.getElementById('nomeUsuario');
const logoutBtn = document.getElementById('logoutBtn');

if (!usuario) window.location.href = 'index.html';

nomeUsuarioSpan.textContent = usuario.nome;

logoutBtn.addEventListener('click', () => {
    localStorage.removeItem('jwtToken');
    window.location.href = 'index.html';
});
