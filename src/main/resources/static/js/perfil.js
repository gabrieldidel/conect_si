const API_URL_USER_LOGADO = 'https://conectsi-production.up.railway.app/usuarios/user-logado';

const nomePerfilEl = document.getElementById('nomePerfil');
const emailPerfilEl = document.getElementById('emailPerfil');
const listaMinhasPostagensEl = document.getElementById('listaMinhasPostagens');
const logoutBtn = document.getElementById('logoutBtn');

// pega o token salvo no login
const token = sessionStorage.getItem('jwtToken');

// se não tiver token, manda pra página de login/usuários
if (!token) {
  window.location.href = 'usuarios.html';
}

// logout
if (logoutBtn) {
  logoutBtn.addEventListener('click', (e) => {
    e.preventDefault();
    sessionStorage.removeItem('jwtToken');
    localStorage.removeItem('jwtToken');
    window.location.href = 'usuarios.html';
  });
}

// monta o HTML das postagens do usuário
function renderMinhasPostagens(postagens) {
  listaMinhasPostagensEl.innerHTML = '';

  const tituloSecao = document.createElement('h2');
  tituloSecao.textContent = 'Minhas postagens';
  listaMinhasPostagensEl.appendChild(tituloSecao);

  if (!postagens || postagens.length === 0) {
    const vazio = document.createElement('p');
    vazio.textContent = 'Você ainda não fez nenhuma postagem.';
    listaMinhasPostagensEl.appendChild(vazio);
    return;
  }

  postagens.forEach(p => {
    const div = document.createElement('div');
    div.classList.add('postagem');

    div.innerHTML = `
      <h3>${p.titulo}</h3>
      <p>${p.conteudo}</p>
      <small>Autor: você mesmo 😎 (ID: ${p.usuario})</small>
    `;

    listaMinhasPostagensEl.appendChild(div);
  });
}

// carrega dados do usuário logado + postagens
function carregarUsuarioLogado() {
  fetch(API_URL_USER_LOGADO, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  })
    .then(res => {
      if (!res.ok) {
        if (res.status === 401 || res.status === 403) {
          sessionStorage.removeItem('jwtToken');
          localStorage.removeItem('jwtToken');
          window.location.href = 'usuarios.html';
        }
        throw new Error('Erro ao buscar usuário logado');
      }
      return res.json();
    })
    .then(data => {
      const nome = data.nome || 'Usuário';
      const email = data.email || 'E-mail não informado';

      nomePerfilEl.textContent = nome;
      emailPerfilEl.textContent = email;

      renderMinhasPostagens(data.postagens || []);
    })
    .catch(err => {
      console.error('Erro ao carregar usuário logado:', err);
      nomePerfilEl.textContent = 'Erro ao carregar perfil';
      emailPerfilEl.textContent = '';
      listaMinhasPostagensEl.innerHTML = '<p>Não foi possível carregar suas postagens.</p>';
    });
}

document.addEventListener('DOMContentLoaded', carregarUsuarioLogado);
