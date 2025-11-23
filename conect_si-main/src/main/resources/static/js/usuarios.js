const API_URL_POSTAGENS = 'https://conectsi-production.up.railway.app/postagens';
const listaPostagensDiv = document.getElementById('listaPostagens');
const postForm = document.getElementById('postForm');
const novaPostagemSection = document.getElementById('novaPostagem');
const mensagemPost = document.getElementById('mensagemPost');
const logoutBtn = document.getElementById('logoutBtn');

// Recupera o token JWT salvo no localStorage
const token = sessionStorage.getItem('jwtToken');

// Se estiver logado, mostra formulário e botão de logout
if (token) {
    novaPostagemSection.style.display = 'block';
    logoutBtn.style.display = 'inline';
}

// Logout
logoutBtn.addEventListener('click', () => {
    localStorage.removeItem('jwtToken');
    window.location.href = 'usuarios.html';
});

// Exibe as postagens
function mostrarPostagens(postagens) {
    listaPostagensDiv.innerHTML = '';
    postagens.sort((a, b) => new Date(b.dataCriacao) - new Date(a.dataCriacao));

    postagens.forEach(p => {
        const div = document.createElement('div');
        div.classList.add('postagem');
        div.innerHTML = `
            <h3>${p.titulo}</h3>
            <p>${p.conteudo}</p>
            <small>Por ${p.usuario?.nome || 'Anônimo'} em ${new Date(p.dataCriacao).toLocaleString()}</small>
        `;
        listaPostagensDiv.appendChild(div);
    });
}

// Buscar todas postagens
function carregarPostagens() {
    fetch(API_URL_POSTAGENS)
        .then(res => res.json())
        .then(postagens => mostrarPostagens(postagens))
        .catch(err => console.error('Erro ao carregar postagens:', err));
}

// Criar nova postagem (somente se logado)
if (token) {
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
            body: JSON.stringify({
                titulo,
                conteudo,
                usuario: 7 // ID do usuário pode vir do backend, ou você pode decodificar o token pra pegar
            })
        })
            .then(res => {
                if (res.ok) return res.json();
                else throw new Error('Erro ao criar postagem');
            })
            .then(post => {
                mensagemPost.style.color = 'green';
                mensagemPost.textContent = 'Postagem criada com sucesso!';
                postForm.reset();
                carregarPostagens();
            })
            .catch(err => {
                mensagemPost.style.color = 'red';
                mensagemPost.textContent = err.message;
                console.error(err);
            });
    });
}

// Carregar postagens iniciais
carregarPostagens();
