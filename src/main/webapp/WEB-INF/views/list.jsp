<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Catálogo</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background: #f5f7fb; }
        .container { max-width: 1100px; margin: auto; }
        .topbar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .actions { display: flex; gap: 10px; align-items: center; }
        a.button, button { text-decoration: none; background: #2f6fed; color: white; border: none; border-radius: 6px; padding: 10px 14px; cursor: pointer; }
        table { width: 100%; border-collapse: collapse; background: white; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background: #eef3ff; }
        .badge { background: #eaf5eb; color: #2d7a46; padding: 5px 8px; border-radius: 12px; }
        form.inline { display: inline; }
        .search { margin-bottom: 20px; }
        input, textarea { padding: 8px; width: 100%; box-sizing: border-box; }
        .search input { width: 250px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="topbar">
            <h1>Catálogo de Livros, Séries e Filmes</h1>
            <div class="actions">
                <a class="button" href="${pageContext.request.contextPath}/items?action=new">Novo item</a>
            </div>
        </div>

        <div class="search">
            <form method="get" action="${pageContext.request.contextPath}/items">
                <input type="hidden" name="action" value="search"/>
                <input type="text" name="q" value="${query}" placeholder="Buscar por título ou autor/diretor" />
                <button type="submit">Buscar</button>
                <a class="button" href="${pageContext.request.contextPath}/items">Limpar</a>
            </form>
        </div>

        <table>
            <thead>
                <tr>
                    <th>Título</th>
                    <th>Autor/Diretor</th>
                    <th>Ano</th>
                    <th>Gênero</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${itens}">
                    <tr>
                        <td><strong>${item.title}</strong></td>
                        <td>${item.authorDirector}</td>
                        <td>${item.publicationYear}</td>
                        <td><span class="badge">${item.genre}</span></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/items?action=details&id=${item.id}">Detalhes</a> |
                            <a href="${pageContext.request.contextPath}/items?action=edit&id=${item.id}">Editar</a> |
                            <form class="inline" method="post" action="${pageContext.request.contextPath}/items">
                                <input type="hidden" name="_method" value="DELETE" />
                                <input type="hidden" name="id" value="${item.id}" />
                                <button type="submit" onclick="return confirm('Deseja excluir este item?');">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
