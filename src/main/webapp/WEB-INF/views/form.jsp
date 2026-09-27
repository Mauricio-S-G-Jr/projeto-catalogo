<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>${empty item ? 'Novo item' : 'Editar item'}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background: #f5f7fb; }
        .container { max-width: 700px; margin: auto; background: white; padding: 25px; border-radius: 12px; }
        .row { margin-bottom: 14px; }
        label { display: block; margin-bottom: 6px; font-weight: bold; }
        input, textarea { width: 100%; padding: 10px; box-sizing: border-box; }
        textarea { min-height: 120px; }
        .buttons { display: flex; gap: 12px; margin-top: 20px; }
        button, a.button { text-decoration: none; background: #2f6fed; color: white; border: none; border-radius: 6px; padding: 10px 14px; cursor: pointer; }
        a.button { background: #6c757d; }
    </style>
</head>
<body>
    <div class="container">
        <h1>${empty item ? 'Cadastrar novo item' : 'Editar item'}</h1>

        <form method="post" action="${pageContext.request.contextPath}/items">
            <input type="hidden" name="id" value="${item.id}" />

            <div class="row">
                <label for="title">Título</label>
                <input id="title" name="title" value="${item.title}" required />
            </div>

            <div class="row">
                <label for="authorDirector">Autor/Diretor</label>
                <input id="authorDirector" name="authorDirector" value="${item.authorDirector}" required />
            </div>

            <div class="row">
                <label for="publicationYear">Ano de publicação/lançamento</label>
                <input id="publicationYear" name="publicationYear" type="number" value="${item.publicationYear}" required />
            </div>

            <div class="row">
                <label for="genre">Gênero</label>
                <input id="genre" name="genre" value="${item.genre}" required />
            </div>

            <div class="row">
                <label for="synopsis">Sinopse</label>
                <textarea id="synopsis" name="synopsis" required>${item.synopsis}</textarea>
            </div>

            <div class="buttons">
                <button type="submit">Salvar</button>
                <a class="button" href="${pageContext.request.contextPath}/items">Cancelar</a>
            </div>
        </form>
    </div>
</body>
</html>
