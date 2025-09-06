<!DOCTYPE html>
<html lang="pt-BR">
    <head>
    <meta charset="UTF-8">
    <title>Curso de BSI - Home</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <h1>Curso de BSI</h1>
    <nav>
        <a href="index.html">Home</a>
        <a href="usuarios.html">Área Interativa</a>
    </nav>
</header>

<main>
    <section>
        <h2>Bem-vindo ao Curso de BSI</h2>
        <p>Aprenda tudo sobre BSI, interaja com alunos e professores!</p>
    </section>

    <section id="login-section">
        <h2>Login</h2>
        <form id="loginForm">
            <input type="email" id="email" placeholder="Email" required>
                <input type="password" id="senha" placeholder="Senha" required>
                    <button type="submit">Entrar</button>
        </form>
        <p id="loginMessage" style="color:red;"></p>
        <p>Não tem conta? <a href="cadastro.html">Cadastre
