package app.persistence;

import app.entities.CupcakeBottom;
import app.entities.CupcakeTop;
import app.entities.OrderLines;
import app.exception.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CupcakeMapper {

    public List<CupcakeBottom> getAllCupcakeBottoms(ConnectionPool connectionPool) throws DatabaseException {

        List<CupcakeBottom> cupcakeBottomList = new ArrayList<>();
        String sql = "SELECT * FROM public.bottoms";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int bottom_Id = rs.getInt("bottom_id");
                int bottom_price = rs.getInt("bottom_price");
                String bottom_name = rs.getString("bottom_name");
                cupcakeBottomList.add(new CupcakeBottom(bottom_Id, bottom_name, bottom_price));

            }
        } catch (SQLException e) {
            throw new DatabaseException("Something went wrong in fetching bottoms in Database", e.getMessage());
        }
        return cupcakeBottomList;
    }

    public List<CupcakeTop> getAllCupcakeTops(ConnectionPool connectionPool) throws DatabaseException {

        List<CupcakeTop> cupcakeTopList = new ArrayList<>();
        String sql = "SELECT * FROM public.tops";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int top_Id = rs.getInt("top_id");
                int top_price = rs.getInt("top_price");
                String top_name = rs.getString("top_name");
                cupcakeTopList.add(new CupcakeTop(top_Id, top_name, top_price));

            }
        } catch (SQLException e) {
            throw new DatabaseException("Something went wrong fetching Tops in Database", e.getMessage());
        }
        return cupcakeTopList;
    }

    public CupcakeBottom getBottomCupcakeById(int bottomId, ConnectionPool connectionPool) {
        CupcakeBottom cupcakeBottom = null;

        String sql = "SELECT * FROM public.bottoms where bottom_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, bottomId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    bottomId = rs.getInt("bottom_id");
                    String name = rs.getString("bottom_name");
                    int price = rs.getInt("bottom_price");

                     cupcakeBottom = new CupcakeBottom(bottomId, name, price);
                } else {
                    throw new DatabaseException("Bottom with id = " + bottomId + " is not found");
                }
            } catch (DatabaseException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cupcakeBottom;
    }

    public CupcakeTop getTopCupcakeById(int topId, ConnectionPool connectionPool) {
        CupcakeTop cupcakeTop = null;

        String sql = "SELECT * FROM public.tops where top_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, topId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    topId = rs.getInt("top_id");
                    String name = rs.getString("top_name");
                    int price = rs.getInt("top_price");

                    cupcakeTop = new CupcakeTop(topId, name, price);
                } else {
                    throw new DatabaseException("Top with id = " + topId + " is not found");
                }
            } catch (DatabaseException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cupcakeTop;
    }


}
