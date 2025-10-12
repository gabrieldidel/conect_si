import { API_BASE_URL } from './config.js';

const cadastroForm = document.getElementById('cadastroForm');
const cadastroMessage = document.getElementById('cadastroMessage');
const API_URL = API_BASE_URL + '/usuarios';

cadastroForm.addEventListener('submit', async (e) => {
  e.preventDefault();

  const nome = document.getElementById('nome').value.trim();
  const email = document.getElementById('email').value.trim();
  const senha = document.getElementById('senha').value.trim();

  if (!nome || !email || !senha) {
    cadastroMessage.textContent = 'Preencha todos os campos!';
    cadastroMessage.style.color = 'red';
    return;
  }

  try {
    const response = await fetch(API_URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        nome,
        email,
        senha
      })
    });

    if (!response.ok) {
      const error = await response.text();
      throw new Error(error || 'Erro ao cadastrar usuário.');
    }

    cadastroMessage.textContent = 'Usuário cadastrado com sucesso!';
    cadastroMessage.style.color = 'green';
    cadastroForm.reset();

  } catch (err) {
    cadastroMessage.textContent = 'Erro ao cadastrar: ' + err.message;
    cadastroMessage.style.color = 'red';
  }
});
