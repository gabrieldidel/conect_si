import { API_BASE_URL } from './config.js';

const loginForm = document.getElementById('loginForm');
const loginMessage = document.getElementById('loginMessage');

const API_URL = API_BASE_URL + '/login';

loginForm.addEventListener('submit', async (e) => {
  e.preventDefault();

  const email = document.getElementById('email').value.trim();
  const senha = document.getElementById('senha').value.trim();

  if (!email || !senha) {
    loginMessage.textContent = 'Preencha todos os campos!';
    loginMessage.style.color = 'red';
    return;
  }

  try {
    const response = await fetch(API_URL, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, senha })
    });

    if (!response.ok) {
      const error = await response.text();
      throw new Error(error || 'Email ou senha incorretos.');
    }

    const token = await response.text();
    console.log('Token JWT recebido:', token);

    sessionStorage.setItem('jwtToken', token);

    loginMessage.textContent = 'Login realizado com sucesso!';
    loginMessage.style.color = 'green';

    setTimeout(() => {
      window.location.href = 'home-logged.html';
    }, 2000);

  } catch (err) {
    console.error(err);
    loginMessage.textContent = 'Erro ao fazer login: ' + err.message;
    loginMessage.style.color = 'red';
  }
});
