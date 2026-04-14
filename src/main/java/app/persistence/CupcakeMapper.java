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
                int bottom_Id = rs.getInt("bottom_id");
                int bottomPrice = rs.getInt("price");
                String bottomName = rs.getString("name");
                cupcakeBottomList.add(new CupcakeBottom(bottom_Id, bottomName, bottomPrice));

            }
        }catch(SQLException e){
            throw new DatabaseException("Something went wrong in fetching bottoms in Database", e.getMessage());
        }
        return cupcakeBottomList;


    }  public List<CupcakeTop> getAllCupcakeTops(ConnectionPool connectionPool) throws DatabaseException {

        List<CupcakeTop> cupcakeTopList = new ArrayList<>();
        String sql = "SELECT * FROM public.tops";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int top_Id = rs.getInt("top_id");
                int topPrice = rs.getInt("price");
                String topName = rs.getString("name");
                cupcakeTopList.add(new CupcakeTop(top_Id, topName, topPrice));

            }
        }catch(SQLException e){
            throw new DatabaseException("Something went wrong fetching Tops in Database", e.getMessage());
        }
        return cupcakeTopList;


    }

}
