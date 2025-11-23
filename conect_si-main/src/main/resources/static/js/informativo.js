const API_URL_POSTAGENS = 'https://conectsi-production.up.railway.app/postagens';
const postagensDiv = document.getElementById('listaPostagens');
const postForm = document.getElementById('postForm');
const novaPostagemSection = document.getElementById('novaPostagem');
const mensagemPost = document.getElementById('mensagemPost');
const logoutBtn = document.getElementById('logoutBtn');

const token = sessionStorage.getItem('jwtToken');

if (token) {
  novaPostagemSection.style.display = 'block';
  logoutBtn.style.display = 'inline';
}

logoutBtn.addEventListener('click', () => {
  sessionStorage.removeItem('jwtToken');
  window.location.href = 'usuarios.html';
});

function mostrarPostagens(postagens) {
  postagensDiv.innerHTML = '';
  postagens
    .sort((a, b) => new Date(b.dataCriacao) - new Date(a.dataCriacao))
    .forEach(p => {
      const div = document.createElement('div');
      div.classList.add('postagem');
      div.innerHTML = `
        <h3>${p.titulo}</h3>
        <p>${p.conteudo}</p>
        <small>Por ${p.usuario?.nome || 'Anônimo'} em ${new Date(p.dataCriacao).toLocaleString()}</small>
      `;
      postagensDiv.appendChild(div);
    });
}

function carregarPostagens() {
  fetch(API_URL_POSTAGENS + '?t=' + Date.now(), { cache: 'no-store' })
    .then(res => res.json())
    .then(postagens => mostrarPostagens(postagens))
    .catch(err => console.error('Erro ao carregar postagens:', err));
}

if (token && postForm) {
  postForm.addEventListener('submit', e => {
    e.preventDefault();

    const titulo = document.getElementById('titulo').value;
    const conteudo = document.getElementById('conteudo').value;

    fetch(API_URL_POSTAGENS, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      body: JSON.stringify({ titulo, conteudo, usuario: 7 })
    })
      .then(res => {
        if (res.ok) return res.json();
        else throw new Error('Erro ao criar postagem');
      })
      .then(() => {
        mensagemPost.style.color = 'green';
        mensagemPost.textContent = 'Postagem criada com sucesso!';
        postForm.reset();
        carregarPostagens(); // recarrega a lista com cache quebrado
      })
      .catch(err => {
        mensagemPost.style.color = 'red';
        mensagemPost.textContent = err.message;
      });
  });
}

carregarPostagens();
