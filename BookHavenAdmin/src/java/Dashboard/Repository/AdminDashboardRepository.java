/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dashboard.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class AdminDashboardRepository {

    String url = "jdbc:MySql://localhost:3306/books";
    String user = "root";
    String pass = "root";

    Connection con;
    PreparedStatement stmt;
    ResultSet rs = null;

    private static final int PAGE_SIZE = 10;

    public ResultSet getViewUserPaginated(int start, int pageSize) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        PreparedStatement stmt = con.prepareStatement("SELECT user_id, name, email, contact,address1 FROM users LIMIT ?, ?");
        stmt.setInt(1, start);
        stmt.setInt(2, pageSize);
        return stmt.executeQuery();
    }

    public ResultSet searchUsers(String searchQuery, int start, int pageSize) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String query = "SELECT * FROM users";
        if (searchQuery != null && !searchQuery.isEmpty()) {
            query += " WHERE name LIKE ? OR email LIKE ? OR contact LIKE ?";
        }
        query += " LIMIT ?, ?";

        // Prepare the statement
        stmt = con.prepareStatement(query);

        // Set parameters for pagination
        int parameterIndex = 1;
        if (searchQuery != null && !searchQuery.isEmpty()) {
            stmt.setString(parameterIndex++, "%" + searchQuery + "%");
            stmt.setString(parameterIndex++, "%" + searchQuery + "%");
            stmt.setString(parameterIndex++, "%" + searchQuery + "%");
        }
        stmt.setInt(parameterIndex++, start);
        stmt.setInt(parameterIndex++, pageSize);

        // Execute the query
        rs = stmt.executeQuery();

        return rs;
    }

    public ResultSet getViewBookPaginated(int start, int pageSize) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "SELECT bs.book_id, bs.book_name, bs.book_img, a.author_name,a.author_id, bs.description, bs.book_price,bs.quantity,bs.discount ,c.category_name, c.category_id,bs.has_offer, bs.is_featured "
                + "FROM book_stock bs "
                + "INNER JOIN authors a ON bs.author_id = a.author_id "
                + "INNER JOIN category c ON bs.category_id = c.category_id "
                + "LIMIT ?, ?";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, start);
        stmt.setInt(2, pageSize);
        rs = stmt.executeQuery();

        return rs;
    }

    public int getTotalBooksCount() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("SELECT COUNT(*) AS total FROM book_stock");
        rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        }

        return 0;
    }

    public int insertBook(String bookName, int bookCategory, int bookAuthor, String bookPrice, String bookDescription, String bookImageUrl,int Quantity,String publishYear) throws ClassNotFoundException, SQLException {
        int status = -1; // Default status indicating failure

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);

        PreparedStatement stmt = con.prepareStatement("insert into book_stock(book_name,category_id,author_id,book_price,description,book_img,quantity,publish_year)values(?,?,?,?,?,?,?,?)");
        stmt.setString(1, bookName);
        stmt.setInt(2, bookCategory);
        stmt.setInt(3, bookAuthor);
        stmt.setString(4, bookPrice);
        stmt.setString(5, bookDescription);
        stmt.setString(6, bookImageUrl);
        stmt.setInt(7, Quantity);
          stmt.setString(8, publishYear);
        status = stmt.executeUpdate();

        // Log the exception or handle it appropriately
        return status;
    }

    public int deleteBook(int bookId) throws SQLException {
        con = DriverManager.getConnection(url, user, pass);

        stmt = con.prepareStatement("delete from book_stock where book_id = ? ");
        stmt.setInt(1, bookId);

        int status = stmt.executeUpdate();
        return status;
    }

    public int getTotalSearchedBooksCount(String searchQuery) throws SQLException {
        int totalCount = 0;

        // SQL query to count total searched books
        String query = "SELECT COUNT(*) AS total FROM books WHERE book_name LIKE ?";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            // Set the search query parameter
            statement.setString(1, "%" + searchQuery + "%");

            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Get the total count from the result set
                    totalCount = resultSet.getInt("total");
                }
            }
        }

        // Return the total count
        return totalCount;
    }

    public boolean checkBookExists(String bookName) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM book_stock WHERE book_name = ?";
        try (Connection con = DriverManager.getConnection(url, user, pass); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, bookName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        }
        return true;
    }

    public ResultSet getAuthor() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "SELECT author_id, author_name, about FROM authors";
        PreparedStatement statement = con.prepareStatement(sql);
        rs = statement.executeQuery();

        return rs;
    }

    public ResultSet getAuthors(int recordsPerPage, int offset) throws SQLException, ClassNotFoundException {
        // Execute SQL query to retrieve paginated data
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String query = "SELECT * FROM authors LIMIT ? OFFSET ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, recordsPerPage);
        statement.setInt(2, offset);
        return statement.executeQuery();
    }

    public int getTotalAuthorsCount() throws SQLException, ClassNotFoundException {
        // Execute SQL query to get total count of authors
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String query = "SELECT COUNT(*) AS total FROM authors";
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt("total");
    }

    //searh
    public ResultSet searchAuthorsByName(String name, int currentPage, int recordsPerPage) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("SELECT * FROM authors WHERE author_name LIKE ?");
        {
            stmt.setString(1, "%" + name + "%");
            return stmt.executeQuery();
        }
    }

    public int getAuthorTotalPages() throws SQLException, ClassNotFoundException {
        try (Connection con = DriverManager.getConnection(url, user, pass); PreparedStatement statement = con.prepareStatement("SELECT CEIL(COUNT(*) / ?) AS totalPages FROM authors")) {
            statement.setInt(1, PAGE_SIZE);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("totalPages");
                }
            }
        }
        return 0;
    }

    public ResultSet getCategories() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "SELECT category_id, category_name ,description FROM category";
        PreparedStatement statement = con.prepareStatement(sql);
        rs = statement.executeQuery();

        return rs;
    }

    public ResultSet searchCategories(String searchQuery, int currentPage) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass); // Corrected variable name from con to conn
        // Assuming 'categories' is the table name and it has 'category_name' and 'description' columns
        String sql = "SELECT * FROM category WHERE category_name LIKE ?  LIMIT ?, ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, "%" + searchQuery + "%");

        // Calculate starting index for pagination
        int pageSize = 5; // Number of categories per page
        int start = (currentPage - 1) * pageSize;
        stmt.setInt(2, start);
        stmt.setInt(3, pageSize);
        rs = stmt.executeQuery();
        return rs;
    }

   public int deleteauthor(int author_id) throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection(url, user, pass);

    // Check if the author ID exists in the book_stock table
    PreparedStatement checkStmt = con.prepareStatement("SELECT COUNT(*) FROM book_stock WHERE author_id = ?");
    checkStmt.setInt(1, author_id);
    ResultSet resultSet = checkStmt.executeQuery();
    resultSet.next();
    int count = resultSet.getInt(1);

    if (count > 0) {
      
        return -1;
    } else {
       PreparedStatement deleteStmt = con.prepareStatement("DELETE FROM authors WHERE author_id = ?");
        deleteStmt.setInt(1, author_id);

        int status = deleteStmt.executeUpdate();
        return status;
    }
}


    public int getTotalUsersCount() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("SELECT COUNT(*) AS total FROM users");
        rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        }

        return 0;
    }

   public int deletecategory(int category_id) throws SQLException {
   
    int status=0;
    try {
        con = DriverManager.getConnection(url, user, pass);
        
        // Check if the category exists in book_stock
        stmt = con.prepareStatement("SELECT category_id FROM book_stock WHERE category_id = ?");
        stmt.setInt(1, category_id);
        rs = stmt.executeQuery();
        
        if (rs.next()) {
            // Category exists in book_stock, do not delete
            status = -1; // Indicate failure due to category existing in book_stock
        } else {
            // Category does not exist in book_stock, proceed with deletion
            stmt = con.prepareStatement("DELETE FROM category WHERE category_id = ?");
            stmt.setInt(1, category_id);
            status = stmt.executeUpdate(); // Execute the delete statement
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Handle or log the exception as needed
    } finally {
        // Close resources in reverse order of creation to avoid resource leaks
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (con != null) {
            con.close();
        }
    }
    
    return status; // Return the deletion status
}

    public int TotalOrders() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("SELECT COUNT(order_id) AS total FROM orders");
        rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0; // No orders found
        }

    }

    public ResultSet getCategories(int recordsPerPage, int offset) throws SQLException, ClassNotFoundException {
        // Execute SQL query to retrieve paginated data
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String query = "SELECT * FROM category LIMIT ? OFFSET ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, recordsPerPage);
        statement.setInt(2, offset);
        return statement.executeQuery();
    }

    public int getTotalCategoriesCount() throws SQLException, ClassNotFoundException {
        // Execute SQL query to get total count of categories
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String query = "SELECT COUNT(*) AS total FROM category";
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt("total");
    }

    public int updateAuthor(int authorId, String authorName, String authorDescription,String AuthorImageUrl) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        // SQL query to update author in the database
        String query = "UPDATE authors SET author_name=?, about=?,img=? WHERE author_id=?";

        PreparedStatement statement = con.prepareStatement(query);
        // Set parameters
        statement.setString(1, authorName);
        statement.setString(2, authorDescription);
          statement.setString(3, AuthorImageUrl);
        statement.setInt(4, authorId);

        // Execute the query
        int status = statement.executeUpdate();
        return status;
    }

    public int insertAuthor(String authorName, String authorDescription) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        // SQL query to insert author into the database
        String query = "INSERT INTO authors (author_name, about) VALUES (?, ?)";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            // Set parameters
            statement.setString(1, authorName);
            statement.setString(2, authorDescription);

            // Execute the query
            int status = statement.executeUpdate();
            return status;
        }
    }

    public int insertCategory(String Categoryname, String Categorydescription) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        // SQL query to insert author into the database
        String query = "INSERT INTO category (category_name, description) VALUES (?, ?)";

        stmt = con.prepareStatement(query);
        // Set parameters
        stmt.setString(1, Categoryname);
        stmt.setString(2, Categorydescription);

        // Execute the query
        int status = stmt.executeUpdate();
        return status;

    }

  public int updateBook(int bookId, String bookName, int categoryId, int authorId, String description, double bookPrice, int quantity,int Discount) throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection(url, user, pass);
    String query = "UPDATE book_stock SET book_name=?, category_id=?, author_id=?, description=?, book_price=?, quantity=?,discount=? WHERE book_id=?";
    stmt = con.prepareStatement(query);
    stmt.setString(1, bookName);
    stmt.setInt(2, categoryId);
    stmt.setInt(3, authorId);
    stmt.setString(4, description);
    stmt.setDouble(5, bookPrice);
    stmt.setInt(6, quantity);  // Set quantity at index 6
    stmt.setInt(7,Discount ); 
    stmt.setInt(8, bookId); 
    int status = stmt.executeUpdate();
    return status;
}


    public boolean updateCategory(int categoryId, String categoryName, String categoryDescription) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a connection to the database
        con = DriverManager.getConnection(url, user, pass);
        String query = "UPDATE category SET category_name=?, description=? WHERE category_id=?";
        stmt = con.prepareStatement(query);
        stmt.setString(1, categoryName);
        stmt.setString(2, categoryDescription);
        stmt.setInt(3, categoryId);
        int rowsUpdated = stmt.executeUpdate();
        return true;

    }

    public int updateBookImage(String bookId, String imageUrl) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a connection to the database
        con = DriverManager.getConnection(url, user, pass);
        String query = "UPDATE book_stock SET book_img=? WHERE book_id=?";
        stmt = con.prepareStatement(query);
        stmt.setString(1, imageUrl);
        stmt.setString(2, bookId);

        int status = stmt.executeUpdate();
        return status;
    }

    public boolean addUserToDatabase(String name, String email, String address1, String address2, String mobileNumber, String password) throws ClassNotFoundException, SQLException {
        // Initialize database connection

        boolean userAdded = false;

        // Get database connection (replace this with your database connection logic)
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a connection to the database
        con = DriverManager.getConnection(url, user, pass);

        // Prepare SQL statement to insert user data into the database
        String sql = "INSERT INTO users (name, email, address1, address2, contact, password) VALUES (?, ?, ?, ?, ?, ?)";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, email);
        stmt.setString(3, address1);
        stmt.setString(4, address2);
        stmt.setString(5, mobileNumber);
        stmt.setString(6, password);

        // Execute the SQL statement
        int rowsInserted = stmt.executeUpdate();

        // Check if the user was added successfully
        if (rowsInserted > 0) {
            userAdded = true;
        }

        return true;
    }

    public boolean isNameAndContactExist(String email, String mobileNumber) throws ClassNotFoundException, ClassNotFoundException, SQLException {
        // Initialize database connection

        boolean exists = true;

        // Get database connection
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a connection to the database
        con = DriverManager.getConnection(url, user, pass);

        // Prepare SQL statement to check if the name and contact exist in the database
        String sql = "SELECT COUNT(*) FROM users WHERE email = ? AND contact = ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, mobileNumber);
        rs = stmt.executeQuery();

        // Check if any record is found with the given name and contact
        if (rs.next()) {
            int count = rs.getInt(1);
            exists = (count > 0);
        }

        return exists;
    }

    public List<String> getAllUserEmails() throws SQLException, ClassNotFoundException {
        List<String> userEmails = new ArrayList<>();

        // Establish database connection
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a connection to the database
        con = DriverManager.getConnection(url, user, pass); // Implement your method to get database connection

        // SQL query to retrieve user emails
        String query = "SELECT email FROM users";

        // Create prepared statement
        PreparedStatement preparedStatement = con.prepareStatement(query); // Execute query
        rs = preparedStatement.executeQuery();
        // Iterate through the result set and add emails to the list
        while (rs.next()) {
            String email = rs.getString("email");
            userEmails.add(email);
        }

        return userEmails;
    }

    public int notFeatured(int bookId) throws SQLException {
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("UPDATE book_stock SET is_featured = 0 WHERE book_id = ? ");
        stmt.setInt(1, bookId);

        int status = stmt.executeUpdate();
        return status;
    }

    public int isFeatured(int bookId) throws SQLException {
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("UPDATE book_stock SET is_featured = 1 WHERE book_id = ? ");
        stmt.setInt(1, bookId);

        int status = stmt.executeUpdate();
        return status;
    }

    public ResultSet searchBooks(String searchQuery, int start, int pageSize) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String queryParam = "%" + searchQuery + "%";
        String sql = "SELECT bs.*, a.author_name, c.category_name "
                + "FROM book_stock bs "
                + "INNER JOIN authors a ON bs.author_id = a.author_id "
                + "INNER JOIN category c ON bs.category_id = c.category_id "
                + "WHERE bs.book_name LIKE ? OR a.author_name LIKE ? OR c.category_name LIKE ? "
                + "LIMIT ?, ?";

        stmt = con.prepareStatement(sql);
        stmt.setString(1, queryParam);
        stmt.setString(2, queryParam);
        stmt.setString(3, queryParam);
        stmt.setInt(4, start);
        stmt.setInt(5, pageSize);
        rs = stmt.executeQuery();

        return rs;
    }

    public int TotalSales() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("SELECT COUNT(*) AS total FROM history");
        rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0; // No orders found
        }
    }
    public ResultSet TotalSalesHistory() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("SELECT history_id, order_id,user_id,orderDate,ordered_items,total_amount FROM history");
        rs = stmt.executeQuery();

        return rs;
    }

    public ResultSet getAllOrders() throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection(url, user, pass);
    stmt = con.prepareStatement("SELECT o.*, u.address1,u.contact FROM orders o INNER JOIN users u ON o.user_id = u.user_id ORDER BY o.dateAdded DESC  ");
    rs = stmt.executeQuery();

    return rs;
}



    public int deleteorder(int order_id) throws SQLException {
        con = DriverManager.getConnection(url, user, pass);

        stmt = con.prepareStatement("delete from orders where order_id = ? ");
        stmt.setInt(1, order_id);

        int status = stmt.executeUpdate();
        return status;
    }

    public int getUserIdFromOrderId(int order_id) throws SQLException, ClassNotFoundException {
        int user_id = -1; // Default value if no user found

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "SELECT user_id FROM orders WHERE order_id = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, order_id);
        rs = stmt.executeQuery();
        if (rs.next()) {
            user_id = rs.getInt("user_id");
        }

        return user_id;
    }

    public String getEmailFromUserId(int user_id) throws SQLException, ClassNotFoundException {
        String email = null; // Default value if no email found

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "SELECT email FROM users WHERE user_id = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, user_id);
        rs = stmt.executeQuery();
        if (rs.next()) {
            email = rs.getString("email");
        }

        return email;
    }

    public int bookissuerequest() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("SELECT COUNT(*) AS total FROM bookissuerequest");
        rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0; // No orders found
        }

    }

   public ResultSet bookissuerequests() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(url, user, pass);
    PreparedStatement stmt = con.prepareStatement("SELECT bir.request_id, bir.user_id, bir.user_name, bir.user_email, bir.book_id, bir.request_date, bs.book_img,bs.book_name " +
            "FROM bookissuerequest bir " +
            "JOIN book_stock bs ON bir.book_id = bs.book_id;");
    ResultSet rs = stmt.executeQuery();
    return rs;
}


    public int RecjetBookRequest(int request_id) throws SQLException {
        con = DriverManager.getConnection(url, user, pass);

        stmt = con.prepareStatement("delete from bookissuerequest where request_id = ? ");
        stmt.setInt(1, request_id);

        int status = stmt.executeUpdate();
        return status;
    }

    public int AcceptOrder(int order_id) throws SQLException {
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("UPDATE orders SET order_status ='Shipping'  WHERE order_id = ? ");
        stmt.setInt(1, order_id);

        int status = stmt.executeUpdate();
        return status;
    }

    public int deleteBookIssueRequest(int request_id, int user_id) throws SQLException {
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("DELETE FROM bookissuerequest WHERE request_id = ? AND user_id = ?");
        stmt.setInt(1, request_id);
        stmt.setInt(2, user_id);

        return stmt.executeUpdate();

    }
   public int bookissued(int request_id, int user_id, String user_name, String user_email, int book_id, String request_date) throws SQLException {
    int insertStatus = 0;

    try {
        con = DriverManager.getConnection(url, user, pass);
        String sql = "INSERT INTO bookissued (request_id, user_id, user_name, user_email, book_id, request_date) VALUES (?, ?, ?, ?, ?, ?)";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, request_id);
        stmt.setInt(2, user_id);
        stmt.setString(3, user_name);
        stmt.setString(4, user_email);
        stmt.setInt(5, book_id);
        stmt.setString(6, request_date);

        insertStatus = stmt.executeUpdate(); // Execute the insert operation

        // Close resources
        stmt.close();
        con.close();
    } catch (SQLException e) {
        // Handle any SQL exception here
        e.printStackTrace();
    }

    return insertStatus;
}


    public ResultSet bookissued() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection(url, user, pass);
    
    // Modify the SQL query to join bookissued with book_stock to fetch book images
    stmt = con.prepareStatement("SELECT bi.Issued_id, bi.request_id, bi.user_id, bi.user_name, bi.user_email, bi.book_id, bi.request_date, bi.return_date, bs.book_img FROM bookissued bi INNER JOIN book_stock bs ON bi.book_id = bs.book_id;");
    rs = stmt.executeQuery();

    return rs;
}


    public int deleteBookReturn(int Issued_id, int user_id) throws SQLException {
  con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("DELETE FROM bookissued WHERE Issued_id = ? AND user_id = ?");
        stmt.setInt(1, Issued_id);
        stmt.setInt(2, user_id);

        return stmt.executeUpdate();    }

    public int bookIssuedHistory(int Issued_id, int request_id, int user_id, String user_name, String user_email, int book_id, String request_date, String return_date) throws SQLException {
    int insertStatus = 0;

    try {
        con = DriverManager.getConnection(url, user, pass);
        String sql = "INSERT INTO BookIssuedHistory (Issued_id, request_id, user_id, user_name, user_email, book_id, request_date, return_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, Issued_id);
        stmt.setInt(2, request_id);
        stmt.setInt(3, user_id);
        stmt.setString(4, user_name);
        stmt.setString(5, user_email);
        stmt.setInt(6, book_id);
        stmt.setString(7, request_date);
        stmt.setString(8, return_date);

        insertStatus = stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (stmt != null) {
            stmt.close();
        }
        if (con != null) {
            con.close();
        }
    }

    return insertStatus;
}

     public int loginDetails(String email, String password) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, pass);

        PreparedStatement stmt = con.prepareStatement("""
                                                      SELECT COUNT(email) 
                                                      FROM admin 
                                                      WHERE email = ? AND password = ?;""");
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

      

     int status = 1;
        
        if (rs.next()) {
            status = rs.getInt(1);
        }
       
        return status;
}

    public ResultSet AdminProfile(String userEmail) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("SELECT name,email,phno FROM admin where email= ?; ");
         stmt.setString(1, userEmail);
        rs = stmt.executeQuery();

        return rs; 
    }

    public int updateAdminProfile(String updatedName, String updatedEmail, String updatedPhno) throws SQLException {
         con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("UPDATE admin SET name =?,phno=?   WHERE email = ? ");
        stmt.setString(1, updatedName);
          stmt.setString(2, updatedPhno);
                  stmt.setString(3, updatedEmail);
        int status = stmt.executeUpdate();
        return status;
    }

    public int bookrenewalrequest() throws SQLException, ClassNotFoundException {
 Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("SELECT COUNT(renewal_id) AS total FROM bookrenewalrequest");
        rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0; // No orders found
        }
    }

   public ResultSet bookrenewalrequests() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(url, user, pass);
    PreparedStatement stmt = con.prepareStatement("SELECT brr.renewal_id, brr.user_id, brr.user_name, brr.user_email, brr.book_id, bs.book_name, bs.book_img " +
            "FROM bookrenewalrequest brr " +
            "JOIN book_stock bs ON brr.book_id = bs.book_id;");
    ResultSet rs = stmt.executeQuery();
    return rs;
}

  public int AcceptBookRenewalRequest(int renewal_id, int user_id, int book_id) throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");

    try (Connection con = DriverManager.getConnection(url, user, pass);
         PreparedStatement stmt = con.prepareStatement("SELECT return_date FROM bookissued WHERE book_id = ?");
         PreparedStatement updateStmt = con.prepareStatement("UPDATE bookissued SET return_date = ?, renewal_count = renewal_count + 1 WHERE book_id = ?")) {

        stmt.setInt(1, book_id);
        ResultSet rs = stmt.executeQuery();

        // Check if the record exists and get the current return date
        if (rs.next()) {
            Timestamp currentReturnDate = rs.getTimestamp("return_date");
            // Calculate the new return date by adding 7 days
            Timestamp newReturnDate = new Timestamp(currentReturnDate.getTime() + (7 * 24 * 60 * 60 * 1000)); // 7 days in milliseconds

            // Update the return date and increment renewal_count in the bookissued table
            updateStmt.setTimestamp(1, newReturnDate);
            updateStmt.setInt(2, book_id);

            return updateStmt.executeUpdate();
        }

        // Return 0 if no record is found or updated
        return 0;
    } catch (SQLException e) {
        e.printStackTrace();
        throw e; // Rethrow the exception to handle it in the calling method
    }
}


    public int DeleteBookRenewalRequest(int renewal_id, int user_id) throws SQLException {
con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("DELETE FROM bookrenewalrequest WHERE renewal_id = ? AND user_id = ?");
        stmt.setInt(1, renewal_id);
        stmt.setInt(2, user_id);

        return stmt.executeUpdate();    }

    public int decreaseBookQuantity(int book_id) throws SQLException {
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("UPDATE book_stock SET quantity = quantity - 1 WHERE book_id = ?");
        stmt.setInt(1, book_id);
      

        return stmt.executeUpdate();  
    }

    public String getBookName(int book_id) throws ClassNotFoundException, SQLException {
        String bookName = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("SELECT book_name FROM book_stock where book_id=?");
         stmt.setInt(1, book_id);
        rs = stmt.executeQuery();

        if (rs.next()) {
            bookName = rs.getString("book_name");
        } 
        return bookName; 
        
    }

    public ResultSet getAllOrder() throws ClassNotFoundException, SQLException {
         Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("SELECT o.*, u.address1 FROM orders o INNER JOIN users u ON o.user_id = u.user_id ORDER BY o.dateAdded  ");
        rs = stmt.executeQuery();

        return rs;
    }

    public int bookissuehistory() throws ClassNotFoundException, SQLException {
         Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        stmt = con.prepareStatement("SELECT COUNT(*) AS total FROM bookissuedhistory");
        rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0; // No orders found
        }
    }

   public int updateStockQuantity(int bookId, int quantityToAdd) throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection(url, user, pass);
    stmt = con.prepareStatement("UPDATE book_stock SET quantity = quantity + ? WHERE book_id = ?");
    stmt.setInt(1, quantityToAdd);
    stmt.setInt(2, bookId);
    int rowsAffected = stmt.executeUpdate();
    return rowsAffected;
}



}
