package app.persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {

    private final static String USER = "postgres";
    private final static String PASSWORD = "postgres";
    private final static String URL = "jdbc:postgresql://localhost:5432/cupcake_shop?currentSchema=test";

    private static Database db;
    private static OrderMapper orderMapper;

    @BeforeAll
    public static void setUpClass() {
        try {
            db = new Database(USER, PASSWORD, URL);
            orderMapper = new OrderMapper(db);
            try (Connection testConnection = db.connect())
            {
                try (Statement stmt = testConnection.createStatement())
                {
                    // The test schema is already created, so we only need to delete/create test tables

                    stmt.execute("DROP TABLE IF EXISTS test.orderlines");
                    stmt.execute("DROP TABLE IF EXISTS test.bottoms");
                    stmt.execute("DROP TABLE IF EXISTS test.orders");
                    stmt.execute("DROP TABLE IF EXISTS test.tops");
                    stmt.execute("DROP TABLE IF EXISTS test.users");

                    stmt.execute("DROP SEQUENCE IF EXISTS test.orders_order_id_seq CASCADE;");
                    stmt.execute("DROP SEQUENCE IF EXISTS test.bottoms_bottom_id_seq CASCADE;");
                    stmt.execute("DROP SEQUENCE IF EXISTS test.tops_top_id_seq CASCADE;");
                    stmt.execute("DROP SEQUENCE IF EXISTS test.users_user_id_seq CASCADE;");
                    stmt.execute("DROP SEQUENCE IF EXISTS test.orderlines_orderline_id_seq CASCADE;");

                    // Create tables as copy of original public schema structure
                    stmt.execute("CREATE TABLE test.bottoms AS (SELECT * from public.bottoms) WITH NO DATA");
                    stmt.execute("CREATE TABLE test.orderlines AS (SELECT * from public.orderlines) WITH NO DATA");
                    stmt.execute("CREATE TABLE test.orders AS (SELECT * from public.orders) WITH NO DATA");
                    stmt.execute("CREATE TABLE test.tops AS (SELECT * from public.tops) WITH NO DATA");
                    stmt.execute("CREATE TABLE test.users AS (SELECT * from public.users) WITH NO DATA");

                    // Create sequences for auto generating id's for members and sports
                    stmt.execute("CREATE SEQUENCE test.bottoms_bottom_id_seq");
                    stmt.execute("ALTER TABLE test.bottoms ALTER COLUMN bottom_id SET DEFAULT nextval('test.bottoms_bottom_id_seq')");
                    stmt.execute("CREATE SEQUENCE test.orderlines_orderline_id_seq");
                    stmt.execute("ALTER TABLE test.orderlines ALTER COLUMN orderline_id SET DEFAULT nextval('test.orderlines_orderline_id_seq')");

                    stmt.execute("CREATE SEQUENCE test.orders_order_id_seq");
                    stmt.execute("ALTER TABLE test.orders ALTER COLUMN order_id SET DEFAULT nextval('test.orders_order_id_seq')");
                    stmt.execute("CREATE SEQUENCE test.tops_top_id_seq");
                    stmt.execute("ALTER TABLE test.tops ALTER COLUMN top_id SET DEFAULT nextval('test.tops_top_id_seq')");

                    stmt.execute("CREATE SEQUENCE test.users_user_id_seq");
                    stmt.execute("ALTER TABLE test.users ALTER COLUMN user_id SET DEFAULT nextval('test.users_user_id_seq')");
                }
            }
            catch (SQLException throwables)
            {
                System.out.println(throwables.getMessage());
                fail("Database connection failed");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        try (Connection testConnection = db.connect()) {
            try (Statement stmt = testConnection.createStatement() ) {
                // Remove all rows from all tables
                stmt.execute("DELETE FROM test.bottoms");
                stmt.execute("DELETE FROM test.orderlines");
                stmt.execute("DELETE FROM test.orders");
                stmt.execute("DELETE FROM test.tops");
                stmt.execute("DELETE FROM test.users");

                // Reset the sequence number
                stmt.execute("SELECT setval('test.bottoms_bottom_id_seq', 1)");
                stmt.execute("SELECT setval('test.orders_order_id_seq', 1)");
                stmt.execute("SELECT setval('test.tops_top_id_seq', 1)");
                stmt.execute("SELECT setval('test.orderlines_orderline_id_seq', 1)");
                stmt.execute("SELECT setval('test.users_user_id_seq', 1)");

                // Insert rows
                stmt.execute("INSERT INTO test.users (user_id, firstname, lastname, email, password, is_admin) VALUES " +
                        "(1,'Anna', 'Hansen', 'anna.hansen@example.com', '1234', FALSE), " +
                        "(2,'Mikkel', 'Sørensen', 'mikkel.soerensen@example.com', 'abcd', FALSE), " +
                        "(3,'Sara', 'Jensen', 'sara.jensen@example.com', 'pass', FALSE), " +
                        "(4,'Jonas', 'Nielsen', 'jonas.nielsen@example.com', 'qwerty', TRUE), " +
                        "(5,'Emma', 'Larsen', 'emma.larsen@example.com', 'cupcake', FALSE)");


                stmt.execute("INSERT INTO test.bottoms (name, price) VALUES " +
                        "('Chocolate', 5), " +
                        "('Vanilla', 4), " +
                        "('Almond', 6), " +
                        "('Red Velvet', 6), " +
                        "('Banana', 5), " +
                        "('Caramel', 7)");

                stmt.execute("INSERT INTO test.tops (name, price) VALUES " +
                        "('Chocolate Frosting', 5), " +
                        "('Vanilla Cream', 4), " +
                        "('Strawberry Swirl', 6), " +
                        "('Blueberry Dream', 6), " +
                        "('Caramel Drizzle', 7), " +
                        "('Lemon Icing', 5)");

                stmt.execute("INSERT INTO test.orders (user_id, order_date) VALUES " +
                        "(1, '2024-01-05'), " +
                        "(2, '2024-01-06'), " +
                        "(1, '2024-01-10'), " +
                        "(3, '2024-01-12'), " +
                        "(4, '2024-01-15'), " +
                        "(2, '2024-01-18'), " +
                        "(5, '2024-01-20'), " +
                        "(3, '2024-01-22'), " +
                        "(1, '2024-01-25'), " +
                        "(4, '2024-01-28')");

                stmt.execute("INSERT INTO test.orderlines (order_id, top_id, bottom_id, quantity, price) VALUES " +
                        "(1, 2, 1, 2, 20), " +
                        "(1, 3, 4, 1, 12), " +
                        "(2, 1, 2, 3, 27), " +
                        "(3, 4, 3, 1, 15), " +
                        "(4, 2, 6, 2, 24), " +
                        "(5, 5, 1, 1, 14), " +
                        "(6, 3, 5, 4, 48), " +
                        "(7, 1, 2, 2, 18), " +
                        "(8, 4, 4, 1, 16), " +
                        "(9, 2, 3, 3, 33)");



                // Set sequence to continue from the largest member_id
                //stmt.execute("SELECT setval('test.member_member_id_seq', COALESCE((SELECT MAX(member_id)+1 FROM test.member), 1), false)");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }
    @Test
    void testConnection() throws SQLException {
        assertNotNull(db.connect());
    }
    @Test
    void getAllOrderlines() {

    }


}