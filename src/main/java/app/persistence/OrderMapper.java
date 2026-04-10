package app.persistence;

import app.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderMapper {

    public List<OrderLines> getAllOrderlines(int user_id, ConnectionPool connectionPool){
    List<OrderLines> allOrderLines = new ArrayList<>();
    String sql = "SELECT * FROM orderlines JOIN tops ON tops.top_id = orderlines.top_id \n" +
            "JOIN bottoms ON bottoms.bottom_id = orderlines.bottom_id\n" +
            "JOIN orders ON orders.order_id = orderlines.order_id " +
            "WHERE user_id = ?";

        try(

    Connection connection = connectionPool.getConnection()) {
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ps.setInt(1, user_id);
            while (rs.next()) {
                int orderLineId = rs.getInt("orderLineId");
                int orderId = rs.getInt("orderId");
                int cupcakeTopID = rs.getInt("top_id");
                int cupcakeBottomID = rs.getInt("bottom_id");
                int quantity = rs.getInt("quantity");

                String cupcakeTopName = rs.getString("top.name");
                int cupcakeTopPrice = rs.getInt("top.price");

                String cupcakeButtomName = rs.getString("bottoms.name");
                int cupcakeButtomPrice = rs.getInt("bottoms.price");

                CupcakeTop cupcakeTop = new CupcakeTop
                        (cupcakeTopID, cupcakeTopName, cupcakeTopPrice);
                CupcakeBottom cupcakeBottom = new CupcakeBottom
                        (cupcakeBottomID, cupcakeButtomName, cupcakeButtomPrice);

                allOrderLines.add(new OrderLines
                        (quantity,cupcakeBottom, cupcakeTop, orderId, orderLineId ));
            }
        }
    } catch (
    SQLException e) {
        throw new RuntimeException(e);
    }
        return allOrderLines;
}

public static List<Orders> getAllOrders(ConnectionPool connectionPool){
        List <Orders> allOrders = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY order_id ASC";
    try(
            Connection connection = connectionPool.getConnection()) {
        try(PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                int userId = rs.getInt("user_id");
                int orderDate = rs.getInt("order_date");
                allOrders.add(new Orders(orderId, userId, orderDate));
            }
        }
    } catch (
            SQLException e) {
        throw new RuntimeException(e);
    }


    return allOrders;
}

}
