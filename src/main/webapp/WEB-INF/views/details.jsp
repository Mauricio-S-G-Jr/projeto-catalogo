<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Detalhes do item</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background: #f5f7fb; }
        .container { max-width: 800px; margin: auto; background: white; padding: 25px; border-radius: 12px; }
        .meta { margin: 10px 0; }
        .badge { background: #eaf5eb; color: #2d7a46; padding: 5px 8px; border-radius: 12px; }
        a.button { text-decoration: none; background: #2f6fed; color: white; border-radius: 6px; padding: 10px 14px; display: inline-block; margin-top: 20px; }
    </style>
</head>
<body>
    <div class="container">
        <h1>${item.title}</h1>

        <div class="meta"><strong>Autor/Diretor:</strong> ${item.authorDirector}</div>
        <div class="meta"><strong>Ano:</strong> ${item.publicationYear}</div>
        <div class="meta"><strong>Gênero:</strong> <span class="badge">${item.genre}</span></div>
        <div class="meta"><strong>Sinopse:</strong><br>${item.synopsis}</div>

        <div>
            <a class="button" href="${pageContext.request.contextPath}/items">Voltar</a>
            <a class="button" href="${pageContext.request.contextPath}/items?action=edit&id=${item.id}">Editar</a>
        </div>
    </div>
</body>
</html>
