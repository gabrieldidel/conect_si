document.addEventListener('DOMContentLoaded', () => {
    // Seleciona o botão de navegação e o menu
    const btnNavegacao = document.getElementById('btnNavegacao');
    const navBar = document.querySelector('.navBar');

    if (btnNavegacao && navBar) {
        // Adiciona o evento de clique ao botão
        btnNavegacao.addEventListener('click', () => {
            // Alterna a classe 'ativo' no menu e no botão
            navBar.classList.toggle('ativo');
            btnNavegacao.classList.toggle('ativo'); 
        });
    }

    // Lógica para o botão de Sair (logout)
    const logoutBtn = document.getElementById('logoutBtn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', (e) => {
            e.preventDefault();
            // Adicione aqui sua lógica de logout (ex: remover token, redirecionar)
            alert('Usuário deslogado! (Lógica de logout precisa ser implementada)');
            // window.location.href = 'index.html'; // Exemplo de redirecionamento
        });
    }
});