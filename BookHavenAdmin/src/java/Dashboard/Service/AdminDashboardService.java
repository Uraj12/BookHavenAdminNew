/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dashboard.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import Dashboard.Repository.AdminDashboardRepository;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class AdminDashboardService {
    AdminDashboardRepository repo = new AdminDashboardRepository();
    
     public ResultSet getViewUserPaginated(int start, int pageSize) throws SQLException, ClassNotFoundException {
        return repo.getViewUserPaginated(start, pageSize);
    }
     public ResultSet searchUsers(String searchQuery, int start, int pageSize) throws SQLException, ClassNotFoundException {
         return repo.searchUsers(searchQuery, start, pageSize);
    }
     
      public ResultSet searchBooks(String searchQuery, int start, int pageSize) throws SQLException, ClassNotFoundException {
         return repo.searchBooks(searchQuery, start, pageSize);
    }
     
     
     public ResultSet getViewBookPaginated(int start, int pageSize) throws SQLException, ClassNotFoundException {
        return repo.getViewBookPaginated(start, pageSize);
    }

    public int getTotalBooksCount() throws SQLException, ClassNotFoundException {
        return repo.getTotalBooksCount();
    }
     public int getTotalUsersCount() throws SQLException, ClassNotFoundException {
        return repo.getTotalUsersCount();
    }
    

    public int insertBook(String bookName, int bookCategory, int bookAuthor, String bookPrice, String BookDescription, String bookImageUrl,int Quantity,String publishYear) throws SQLException, ClassNotFoundException {
        return repo.insertBook(bookName,bookCategory,bookAuthor,bookPrice,BookDescription,bookImageUrl,Quantity,publishYear);
    }

    public int deleteBook(int bookId) throws SQLException {
        return repo.deleteBook(bookId);
    }

     public boolean checkBookExists(String bookName) throws SQLException, ClassNotFoundException {
        return repo.checkBookExists(bookName);
    }
     
     
      public ResultSet getAuthor() throws ClassNotFoundException, SQLException {
        return repo.getAuthor();
    }
      
      public ResultSet getAuthors(int page) throws SQLException, ClassNotFoundException {
        int recordsPerPage = 5; // Number of records per page
        int offset = (page - 1) * recordsPerPage;
        return repo.getAuthors(recordsPerPage, offset);
    }
       public ResultSet searchAuthorsByName(String name, int currentPage, int recordsPerPage) {
        try {
            return repo.searchAuthorsByName(name, currentPage, recordsPerPage);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null; // You might want to handle this better, perhaps by throwing a custom exception
        }
    }

    public int getAuthorTotalPages() throws SQLException, ClassNotFoundException {
        int totalAuthors = repo.getTotalAuthorsCount();
        int recordsPerPage = 5; // Number of records per page
        return (int) Math.ceil((double) totalAuthors / recordsPerPage);
    }
      public ResultSet getCategories() throws SQLException, ClassNotFoundException {
        return repo.getCategories();
    }
      
       public int deleteauthor(int author_id) throws SQLException, ClassNotFoundException {
        return repo.deleteauthor(author_id);
    }

    public int deletecategory(int category_id) throws SQLException {
       return repo.deletecategory(category_id);
    }
    
    public ResultSet searchCategories(String searchQuery, int currentPage) throws SQLException, ClassNotFoundException {
       return repo.searchCategories(searchQuery, currentPage  );
    }
    
     public int TotalOrders() throws SQLException, ClassNotFoundException {
       return repo.TotalOrders();
    }
     public int TotalSales() throws SQLException, ClassNotFoundException {
       return repo.TotalSales();
    }
      public int bookissuehistory() throws SQLException, ClassNotFoundException {
       return repo.bookissuehistory();
    }
     public ResultSet TotalSalesHistory() throws SQLException, ClassNotFoundException {
       return repo.TotalSalesHistory();
    }
     public ResultSet getCategories(int page) throws SQLException, ClassNotFoundException {
        int recordsPerPage = 5; // Number of records per page
        int offset = (page - 1) * recordsPerPage;
        return repo.getCategories(recordsPerPage, offset);
    }

    public int getTotalPages() throws SQLException, ClassNotFoundException {
        int totalCategories = repo.getTotalCategoriesCount();
        int recordsPerPage = 5; // Number of records per page
        return (int) Math.ceil((double) totalCategories / recordsPerPage);
    }

public int updateAuthor(int authorId, String authorName, String authorDescription,String AuthorImageUrl) throws ClassNotFoundException, SQLException {
    return repo.updateAuthor(authorId, authorName, authorDescription,AuthorImageUrl);
}


    public int insertAuthor(String authorName, String authorDescription) throws SQLException, ClassNotFoundException {
       return repo.insertAuthor(authorName, authorDescription);
    }

    public int insertCategory(String Categoryname, String Categorydescription) throws ClassNotFoundException, SQLException {
       return repo.insertCategory(Categoryname, Categorydescription);
    }
    
    public int updateBook(int bookId, String bookName, int categoryId, int authorId, String description, int bookPrice,int quantity,int Discount) throws SQLException, ClassNotFoundException {
        
            return repo.updateBook(bookId, bookName, categoryId, authorId, description, bookPrice,quantity,Discount);
         
        }

    public boolean updateCategory(int categoryId, String categoryName, String categoryDescription) throws SQLException, ClassNotFoundException {
        return repo.updateCategory(categoryId, categoryName, categoryDescription);
    }

    public int updateBookImage(String bookId, String imageUrl) throws ClassNotFoundException, SQLException {
         return repo.updateBookImage(bookId, imageUrl);
    }

    public boolean addUserToDatabase(String name, String email, String address1, String address2, String mobileNumber, String password) throws ClassNotFoundException, SQLException {
         return repo.addUserToDatabase(name, email, address1, address2, mobileNumber, password);
    }

    public boolean isNameAndContactExist(String email, String mobileNumber) throws ClassNotFoundException, SQLException {
       return repo.isNameAndContactExist(email, mobileNumber); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<String> getAllUserEmails() throws SQLException, ClassNotFoundException {
       return repo.getAllUserEmails();
    }
    
   public int notFeatured(int bookId) throws SQLException {
        return repo.notFeatured(bookId);
    }

    public int isFeatured(int bookId) throws SQLException {
        return repo.isFeatured(bookId);
    }
    
     public ResultSet getAllOrders() throws SQLException, ClassNotFoundException {
       return repo.getAllOrders();
    }
       public ResultSet getAllOrder() throws SQLException, ClassNotFoundException {
       return repo.getAllOrder();
    }
     public int getUserIdFromOrderId(int order_id) throws SQLException, ClassNotFoundException {
        return repo.getUserIdFromOrderId(order_id);
    }
     

    public String getEmailFromUserId(int user_id) throws SQLException, ClassNotFoundException {
        return repo.getEmailFromUserId(user_id);
    }
     public int deleteorder(int order_id) throws SQLException {
       return repo.deleteorder(order_id);
    }
      public int bookissuerequest() throws SQLException, ClassNotFoundException {
       return repo.bookissuerequest();
    }
       public ResultSet bookissuerequests() throws SQLException, ClassNotFoundException {
       return repo.bookissuerequests();
    }
       public ResultSet bookrenewalrequests() throws SQLException, ClassNotFoundException {
       return repo.bookrenewalrequests();
    }
       public ResultSet bookissued() throws SQLException, ClassNotFoundException {
       return repo.bookissued();
    }

    public int RecjetBookRequest(int request_id) throws SQLException {
             return repo.RecjetBookRequest(request_id);
    }

    public int AcceptOrder(int order_id) throws SQLException {
       return repo.AcceptOrder(order_id);
    }
    public int deleteBookIssueRequest(int request_id, int user_id) throws SQLException {
    return repo.deleteBookIssueRequest(request_id, user_id);
    }
    public int deleteBookReturn(int Issued_id, int user_id) throws SQLException {
    return repo.deleteBookReturn(Issued_id, user_id);
}
    public int bookissued(int request_id, int user_id, int book_id, String user_name, String user_email, String request_date) throws SQLException {
    return repo.bookissued(request_id, user_id, user_name, user_email, book_id, request_date);
}
    public int bookIssuedHistory( int Issued_id ,int request_id, int user_id, int book_id, String user_name, String user_email, String request_date ,String return_date) throws SQLException {
    return repo.bookIssuedHistory(Issued_id,request_id, user_id, user_name, user_email, book_id, request_date,return_date);
}

     public int verifyLogin(String email, String password)  throws ClassNotFoundException, SQLException {
        return repo. loginDetails(email, password);
    } 

 public ResultSet AdminProfile(String userEmail) throws SQLException, ClassNotFoundException {
       return repo.AdminProfile(userEmail);
    }

    public int updateAdminProfile(String updatedName, String updatedEmail, String updatedPhno) throws SQLException {
         return repo.updateAdminProfile(updatedName,updatedEmail,updatedPhno);
    }
    public int bookrenewalrequest() throws SQLException, ClassNotFoundException {
       return repo.bookrenewalrequest();
    }
    public int AcceptBookRenewalRequest(int renewal_id, int user_id,int book_id) throws SQLException, ClassNotFoundException {
    return repo.AcceptBookRenewalRequest(renewal_id, user_id,book_id);
    }
   
 public int DeleteBookRenewalRequest(int renewal_id, int user_id) throws SQLException {
    return repo.DeleteBookRenewalRequest(renewal_id, user_id);
    }

    public int decreaseBookQuantity(int book_id) throws SQLException {
         return repo.decreaseBookQuantity(book_id);
    }

    public String getBookName(int book_id) throws ClassNotFoundException, SQLException {
         return repo.getBookName(book_id);
    }

    public int updateStockQuantity(int book_id, int i) throws SQLException, ClassNotFoundException {
          return repo.updateStockQuantity(book_id ,i);
        
    }
}
