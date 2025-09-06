const usuario = JSON.parse(localStorage.getItem('usuarioLogado'));
const nomeUsuarioSpan = document.getElementById('nomeUsuario');
const logoutBtn = document.getElementById('logoutBtn');

if (!usuario) window.location.href = 'index.html';

nomeUsuarioSpan.textContent = usuario.nome;

logoutBtn.addEventListener('click', () => {
    localStorage.removeItem('usuarioLogado');
    window.location.href = 'index.html';
});
