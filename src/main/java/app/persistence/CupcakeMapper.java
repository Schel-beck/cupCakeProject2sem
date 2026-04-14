package app.persistence;

import app.entities.CupcakeBottom;
import app.entities.CupcakeTop;
import app.exception.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CupcakeMapper {

    public List<CupcakeBottom> getAllCupcakeBottoms(ConnectionPool connectionPool) throws DatabaseException {

        List<CupcakeBottom> cupcakeBottomList = new ArrayList<>();
        String sql = "SELECT * FROM public.bottoms";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int bottomId = rs.getInt("bottom_id");
                int bottomPrice = rs.getInt("price");
                String bottomName = rs.getString("name");
                cupcakeBottomList.add(new CupcakeBottom(bottomId, bottomName, bottomPrice));

            }
        }catch(SQLException e){
            throw new DatabaseException("Something went wrong in feting bottoms in Database", e.getMessage());
        }
        return cupcakeBottomList;


    }  public List<CupcakeTop> getAllCupcakeTops(ConnectionPool connectionPool) throws DatabaseException {

        List<CupcakeTop> cupcakeTopList = new ArrayList<>();
        String sql = "SELECT * FROM public.tops";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int topId = rs.getInt("top_id");
                int topPrice = rs.getInt("price");
                String topName = rs.getString("name");
                cupcakeTopList.add(new CupcakeTop(topId, topName, topPrice));

            }
        }catch(SQLException e){
            throw new DatabaseException("Something went wrong fetching Tops in Database", e.getMessage());
        }
        return cupcakeTopList;


    }

}
