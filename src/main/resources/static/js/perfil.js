const API_URL_USER_LOGADO = 'http://localhost:8080/usuarios/user-logado';

const nomePerfilEl = document.getElementById('nomePerfil');
const emailPerfilEl = document.getElementById('emailPerfil');
const logoutBtn = document.getElementById('logoutBtn');

// pega o token salvo no login
const token = sessionStorage.getItem('jwtToken');

// se não tiver token, manda o caboclo logar de novo
if (!token) {
  window.location.href = 'usuarios.html';
}

// logout
if (logoutBtn) {
  logoutBtn.addEventListener('click', (e) => {
    e.preventDefault();
    sessionStorage.removeItem('jwtToken');
    // só pra garantir, se você usou localStorage em outro lugar:
    localStorage.removeItem('jwtToken');
    window.location.href = 'usuarios.html';
  });
}

// carrega dados do usuário logado
function carregarUsuarioLogado() {
  fetch(API_URL_USER_LOGADO, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  })
    .then((res) => {
      if (!res.ok) {
        if (res.status === 401 || res.status === 403) {
          // token inválido/expirado
          sessionStorage.removeItem('jwtToken');
          localStorage.removeItem('jwtToken');
          window.location.href = 'usuarios.html';
        }
        throw new Error('Erro ao buscar usuário logado');
      }
      return res.json();
    })
    .then((data) => {
      // aqui depende de como o backend está retornando:
      // se for TokenDTO: { nome, email, token }
      // se for Usuario: { nome, email, id, ... }

      const nome = data.nome || 'Usuário sem nome';
      const email = data.email || 'E-mail não informado';

      nomePerfilEl.textContent = nome;
      emailPerfilEl.textContent = email;
    })
    .catch((err) => {
      console.error('Erro ao carregar usuário logado:', err);
      // se quiser, mostra alguma mensagem na tela
      nomePerfilEl.textContent = 'Erro ao carregar usuário';
      emailPerfilEl.textContent = '';
    });
}

// chama quando a página carregar
document.addEventListener('DOMContentLoaded', carregarUsuarioLogado);
