package br.edu.cs.catalogo.dao;

import br.edu.cs.catalogo.model.ItemCatalogo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/catalogo_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "catalogo";
    private static final String PASSWORD = "catalogo123";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void save(ItemCatalogo item) {
        String sql = "INSERT INTO catalog_item (title, author_director, publication_year, genre, synopsis) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getTitle());
            stmt.setString(2, item.getAuthorDirector());
            stmt.setInt(3, item.getPublicationYear());
            stmt.setString(4, item.getGenre());
            stmt.setString(5, item.getSynopsis());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar item", e);
        }
    }

    public void update(ItemCatalogo item) {
        String sql = "UPDATE catalog_item SET title = ?, author_director = ?, publication_year = ?, genre = ?, synopsis = ? WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getTitle());
            stmt.setString(2, item.getAuthorDirector());
            stmt.setInt(3, item.getPublicationYear());
            stmt.setString(4, item.getGenre());
            stmt.setString(5, item.getSynopsis());
            stmt.setLong(6, item.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar item", e);
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM catalog_item WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir item", e);
        }
    }

    public ItemCatalogo findById(Long id) {
        String sql = "SELECT * FROM catalog_item WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapItem(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar item por id", e);
        }

        return null;
    }

    public List<ItemCatalogo> findAll() {
        String sql = "SELECT * FROM catalog_item ORDER BY title ASC";
        List<ItemCatalogo> itens = new ArrayList<>();

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                itens.add(mapItem(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens", e);
        }

        return itens;
    }

    public List<ItemCatalogo> search(String term) {
        String sql = "SELECT * FROM catalog_item WHERE title LIKE ? OR author_director LIKE ? ORDER BY title ASC";
        List<ItemCatalogo> itens = new ArrayList<>();

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            String likeTerm = "%" + term + "%";
            stmt.setString(1, likeTerm);
            stmt.setString(2, likeTerm);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                itens.add(mapItem(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar itens", e);
        }

        return itens;
    }

    private ItemCatalogo mapItem(ResultSet rs) throws SQLException {
        ItemCatalogo item = new ItemCatalogo();
        item.setId(rs.getLong("id"));
        item.setTitle(rs.getString("title"));
        item.setAuthorDirector(rs.getString("author_director"));
        item.setPublicationYear(rs.getInt("publication_year"));
        item.setGenre(rs.getString("genre"));
        item.setSynopsis(rs.getString("synopsis"));
        return item;
    }
}
