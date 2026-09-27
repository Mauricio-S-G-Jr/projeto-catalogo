package br.edu.cs.catalogo.servlet;

import br.edu.cs.catalogo.dao.ItemDAO;
import br.edu.cs.catalogo.model.ItemCatalogo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ItemServlet", urlPatterns = {"/items", "/items/*"})
public class ItemServlet extends HttpServlet {

    private final ItemDAO itemDAO = new ItemDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String action = req.getParameter("action");

        if ("new".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(req, resp);
            return;
        }

        if ("edit".equals(action) && req.getParameter("id") != null) {
            Long id = Long.parseLong(req.getParameter("id"));
            ItemCatalogo item = itemDAO.findById(id);
            req.setAttribute("item", item);
            req.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(req, resp);
            return;
        }

        if ("details".equals(action) && req.getParameter("id") != null) {
            Long id = Long.parseLong(req.getParameter("id"));
            ItemCatalogo item = itemDAO.findById(id);
            req.setAttribute("item", item);
            req.getRequestDispatcher("/WEB-INF/views/details.jsp").forward(req, resp);
            return;
        }

        if ("search".equals(action)) {
            String termo = req.getParameter("q");
            List<ItemCatalogo> itens = termo == null || termo.isBlank() ? itemDAO.findAll() : itemDAO.search(termo);
            req.setAttribute("itens", itens);
            req.setAttribute("query", termo);
            req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
            return;
        }

        if (path == null || "/".equals(path) || path.isBlank()) {
            List<ItemCatalogo> itens = itemDAO.findAll();
            req.setAttribute("itens", itens);
            req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
            return;
        }

        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodOverride = req.getParameter("_method");

        if ("DELETE".equalsIgnoreCase(methodOverride)) {
            handleDelete(req, resp);
            return;
        }

        String idParam = req.getParameter("id");
        String title = req.getParameter("title");
        String authorDirector = req.getParameter("authorDirector");
        String publicationYear = req.getParameter("publicationYear");
        String genre = req.getParameter("genre");
        String synopsis = req.getParameter("synopsis");

        ItemCatalogo item = new ItemCatalogo();
        item.setTitle(title);
        item.setAuthorDirector(authorDirector);
        item.setPublicationYear(Integer.parseInt(publicationYear));
        item.setGenre(genre);
        item.setSynopsis(synopsis);

        if (idParam != null && !idParam.isBlank()) {
            item.setId(Long.parseLong(idParam));
            itemDAO.update(item);
        } else {
            itemDAO.save(item);
        }

        resp.sendRedirect(req.getContextPath() + "/items");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleDelete(req, resp);
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isBlank()) {
            itemDAO.delete(Long.parseLong(idParam));
        }
        resp.sendRedirect(req.getContextPath() + "/items");
    }
}
