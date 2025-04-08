/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Dashboard.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dashboard.Service.AdminDashboardService;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class AdminDashboardServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String process = request.getParameter("process");
        AdminDashboardService service = new AdminDashboardService();

        if (process.equals("addUser")) {
            request.setAttribute("process", process);
            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);
        }
        if (process.equals("addBook")) {
            request.setAttribute("process", process);
            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);
        }
        if (process.equals("addAuthor")) {
            request.setAttribute("process", process);
            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);
        }
        if (process.equals("addCategory")) {
            request.setAttribute("process", process);
            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);
        }
        if (process.equals("BookIssued")) {
            request.setAttribute("process", process);
            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);
        }
        if (process.equals("insertBook")) {
            String bookName = request.getParameter("bookName");
            int bookCategory = Integer.parseInt(request.getParameter("bookCategory"));
            int bookAuthor = Integer.parseInt(request.getParameter("bookAuthor"));
            int Quantity = Integer.parseInt(request.getParameter("Quantity"));
            String bookPrice = request.getParameter("bookPrice");
            String bookDescription = request.getParameter("bookDescription");
            String bookImageUrl = request.getParameter("bookImageUrl");
            String publishYear = request.getParameter("publishYear");

            // Check if bookName already exists
            boolean bookExists;
            try {
                bookExists = service.checkBookExists(bookName);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                // If an error occurs while checking book existence, handle it here
                // For now, let's consider it doesn't exist
                bookExists = false;
            }

            if (bookExists) {
                // Book with the same name already exists
                // Send response to client
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(" Book with same name exists");
            } else {
                // Book name doesn't exist, proceed with insertion
                int status;
                try {
                    status = service.insertBook(bookName, bookCategory, bookAuthor, bookPrice, bookDescription, bookImageUrl, Quantity,publishYear);

                    // Send email notification to all users
//                    List<String> userEmails = service.getAllUserEmails(); // Retrieve all user emails from the database
//                    String subject = "New Book Added: " + bookName;
//                    String message = "Dear User,\n\nA new book has been added to our collection:\n\n"
//                            + "Book Name: " + bookName + "\n"
//                            + "Author: " + bookAuthor + "\n"
//                            + "Category: " + bookCategory + "\n"
//                            + "Price: " + bookPrice + "\n\n"
//                            + "Description: " + bookDescription + "\n\n"
//                            + "Visit our website to explore the new book!\n\n"
//                            + "Regards,\nThe Library Team";
//                    for (String userEmail : userEmails) {
//                        EmailService.sendEmail(userEmail, subject, message); // Send email to each user
//                    }
                } catch (SQLException | ClassNotFoundException e) {
                    System.err.println(e);
                    status = -1; // Set status to indicate error
                }

                // Forward the request to the appropriate JSP page
                request.setAttribute("process", process);
                request.setAttribute("status", status);
                RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
                view.forward(request, response);
            }
        }

        if (process.equals("deleteBook")) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            int status = service.deleteBook(bookId);
            request.setAttribute("process", process);
            request.setAttribute("status", status);
            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);
        }

        if (process.equals("deleteauthor")) {
            int author_id = Integer.parseInt(request.getParameter("author_id"));
            int status = service.deleteauthor(author_id);
            request.setAttribute("process", process);
            request.setAttribute("status", status);
            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);
        }

        if (process.equals("deletecategory")) {
            int category_id = Integer.parseInt(request.getParameter("category_id"));
            int status = service.deletecategory(category_id);
            request.setAttribute("process", process);
            request.setAttribute("status", status);
            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);
        }
        if (process.equals("updateAuthor")) {
            String authorId = request.getParameter("authorId");
            String authorName = request.getParameter("authorName");
            String authorDescription = request.getParameter("authorDescription");
            String AuthorImageUrl = request.getParameter("AuthorImageUrl");

            // Call a method to update author data in the database
            int status = service.updateAuthor(Integer.parseInt(authorId), authorName, authorDescription, AuthorImageUrl);

            // Return the status as response
            request.setAttribute("process", process);
            request.setAttribute("status", status);
            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);
        }

        if (process.equals("insertAuthor")) {
            String authorName = request.getParameter("authorName");
            String authorDescription = request.getParameter("authorDescription");
            int status;
            status = service.insertAuthor(authorName, authorDescription);
            request.setAttribute("process", process);
            request.setAttribute("status", status);
            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);
        }

        if (process.equals("insertCategory")) {
            String Categoryname = request.getParameter("Categoryname");
            String Categorydescription = request.getParameter("Categorydescription");
            int status;
            status = service.insertCategory(Categoryname, Categorydescription);
            request.setAttribute("process", process);
            request.setAttribute("status", status);

            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);
        }
        if (process.equals("updateBook")) {
            try {
                int bookId = Integer.parseInt(request.getParameter("bookId"));
                String bookName = request.getParameter("bookName");
                String description = request.getParameter("description");
                int bookPrice = Integer.parseInt(request.getParameter("bookPrice"));
                int Discount = Integer.parseInt(request.getParameter("Discount"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                int authorId = Integer.parseInt(request.getParameter("authorId"));
                System.out.println("quantity" + quantity);
                // Call service to update book data
                //  AdminDashboardService service = new AdminDashboardService();
                int success = service.updateBook(bookId, bookName, categoryId, authorId, description, bookPrice, quantity, Discount);

                // Set response status
                if (success > 0) {
                    response.getWriter().write("success");
                } else {
                    response.getWriter().write("failure");
                }
                request.setAttribute("process", process);
                request.setAttribute("status", success);
                RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
                view.forward(request, response);
            } catch (Exception e) {
                // Handle email sending failure
                out.println(e);
                e.printStackTrace();
            }
            // Set response status
        }
        if (process.equals("updateCategory")) {
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            String categoryName = request.getParameter("categoryName");
            String categoryDescription = request.getParameter("categoryDescription");

            // Call service method to update category in database
            //AdminDashboardService service = new AdminDashboardService();
            boolean success = service.updateCategory(categoryId, categoryName, categoryDescription);

            // Send response back to client
            if (success) {
                response.getWriter().write("success");
            } else {
                response.getWriter().write("failure");
            }
        }
        if (process.equals("updateImage")) {
            String bookId = request.getParameter("bookId");
            String imageUrl = request.getParameter("imageUrl");
            //AdminDashboardService service = new AdminDashboardService();
            int status = service.updateBookImage(bookId, imageUrl);
            request.setAttribute("process", process);
            request.setAttribute("status", status);
            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);

        }
        // Check the process parameter to determine the action
        if (process.equals("addUserByAdmin")) {
            // Retrieve user input data from the request parameters
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String address1 = request.getParameter("address1");
            String address2 = request.getParameter("address2");
            String mobileNumber = request.getParameter("mobileNumber");
            String password = request.getParameter("password");

            // Check if the user with the same name and contact already exists in the database
            if (service.isNameAndContactExist(email, mobileNumber)) {
                // Send response back to the client indicating the error
                out = response.getWriter();
                out.print("exists");
                out.flush();
            } else {
                // Save the user data to the database
                boolean userAdded = service.addUserToDatabase(name, email, address1, address2, mobileNumber, password);

                // Send email with the password to the user if they are newly added
                if (userAdded) {
                    sendEmail(name, email, password);
                }

                // Send response back to the client
                out = response.getWriter();
                if (userAdded) {
                    out.print("success");
                } else {
                    out.print("error");
                }
                out.flush();
            }
        }

        if (process.equals("notfeatured") || (process.equals("isfeatured"))) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            if (process.equals("notfeatured")) {
                int status = service.notFeatured(bookId);
                request.setAttribute("process", process);
                request.setAttribute("status", status);
            } else if (process.equals("isfeatured")) {
                int status = service.isFeatured(bookId);
                request.setAttribute("process", process);
                request.setAttribute("status", status);
            }
            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);
        }
        if (process.equals("deleteorder")) {
            int order_id = Integer.parseInt(request.getParameter("order_id"));

            // Retrieve the user_id associated with the order before deleting it
            int user_id = service.getUserIdFromOrderId(order_id);

            // Delete the order
            int status = service.deleteorder(order_id);

            // Verify if the order was deleted successfully
            if (status > 0) {
                // Get the email address of the user
                String email = service.getEmailFromUserId(user_id);

                // Send email notification to the user
                String subject = "Order Cancellation Notification";
                String message = "Your order with ID " + order_id + " has been cancelled.";

                try {
                    EmailService.sendEmail(email, subject, message);
                    out.println("Email sent successfully!");
                } catch (Exception e) {
                    // Handle email sending failure
                    out.println("Failed to send email: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                out.println("Failed to delete order or order does not exist.");
            }

            // Forward the request to ajax.jsp
            request.setAttribute("process", process);
            request.setAttribute("status", status);
            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);
        }
     if (process != null && process.equals("AcceptOrder")) {
            int order_id = Integer.parseInt(request.getParameter("order_id"));

            // Retrieve the user_id associated with the order before deleting it
            int user_id = service.getUserIdFromOrderId(order_id);

            // Delete the order
            int status = service.AcceptOrder(order_id);

            // Verify if the order was deleted successfully
            if (status > 0) {
                // Get the email address of the user
                String email = service.getEmailFromUserId(user_id);

                // Send email notification to the user
                String subject = "Order Shipping Notification";
                String message = "Your order with ID " + order_id + " has been Shipping.";

                try {
                    EmailService.sendEmail(email, subject, message);
                    out.println("Email sent successfully!");
                } catch (Exception e) {
                    // Handle email sending failure
                    out.println("Failed to send email: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                out.println("Failed to delete order or order does not exist.");
            }

            // Forward the request to ajax.jsp
            request.setAttribute("process", process);
            request.setAttribute("status", status);
            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);
        }
        if (process.equals("sendEmailNotification")) {
            {

                List<String> userEmails = service.getAllUserEmails();

                // Construct email message
                String subject = "New Books Added";
                String message = "Dear User,\n\nNew books have been added to our collection. Visit our website BookHaven to explore them!\n\nRegards,\nBookHaven";
                // Send email to each user
                for (String email : userEmails) {
                    EmailService.sendEmail(email, subject, message);

                }

                // Send response
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Email notifications sent successfully!");
                request.setAttribute("process", process);
                // Forward the request to the appropriate JSP page
                RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
                view.forward(request, response);
            }

        }
        if (process.equals("RecjetBookRequest")) {
            int request_id = Integer.parseInt(request.getParameter("request_id"));
            int user_id = Integer.parseInt(request.getParameter("user_id"));

            int status = service.RecjetBookRequest(request_id);
            boolean emailSent = false;

            if (status > 0) {
                String email = service.getEmailFromUserId(user_id);
                String subject = "Book Request Cancellation Notification";
                String message = "Your request for book with ID " + request_id + " has been cancelled due to some reason.";

                try {
                    EmailService.sendEmail(email, subject, message);
                    emailSent = true;
                    System.out.println("Email sent successfully!"); // Use logging here instead of out.println
                } catch (Exception e) {
                    // Handle specific exception for email sending failure
                    System.out.println("Failed to send email: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.out.println("Failed to delete order or order does not exist.");
            }

            // Forward the request to ajax.jsp
            request.setAttribute("process", process);
            request.setAttribute("status", status);
            request.setAttribute("emailSent", emailSent); // Add emailSent attribute
            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);
        }

        if (process != null && process.equals("AcceptBookRequest")) {
            int request_id = Integer.parseInt(request.getParameter("request_id"));
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            int book_id = Integer.parseInt(request.getParameter("book_id"));
            String user_name = request.getParameter("user_name");
            String user_email = request.getParameter("user_email");
            String request_date = request.getParameter("request_date");

            // Get the book name using the book ID
            String book_name = service.getBookName(book_id);

            int deleteStatus = service.deleteBookIssueRequest(request_id, user_id);

            if (deleteStatus > 0) {
                int insertStatus = service.bookissued(request_id, user_id, book_id, user_name, user_email, request_date);
                response.getWriter().write("Success" + insertStatus);

                // Decrease book quantity in the repository
                // Assuming BookRepository handles database operations for books
                int decreaseQuantityStatus = service.decreaseBookQuantity(book_id);

                if (decreaseQuantityStatus > 0) {
                    String email = service.getEmailFromUserId(user_id);
                    String subject = "Book Request Accept Notification";
                    String message = "Your request for book '" + book_name + "' has been Accepted.";
                    EmailService.sendEmail(email, subject, message);
                    System.out.println("Email sent successfully!");
                } else {
                    response.getWriter().write("Failed to decrease book quantity");
                }
            } else {
                response.getWriter().write("Failed to delete book issue request");
            }
        } else {
            response.getWriter().write("Invalid request");
        }

        if (process != null && process.equals("AcceptBookRenewalRequest")) {
            String renewalIdParam = request.getParameter("renewal_id");
            String userIdParam = request.getParameter("user_id");
            String bookIdParam = request.getParameter("book_id");

            if (renewalIdParam != null && userIdParam != null && bookIdParam != null) {
                try {
                    int renewal_id = Integer.parseInt(renewalIdParam);
                    int user_id = Integer.parseInt(userIdParam);
                    int book_id = Integer.parseInt(bookIdParam);
                     String book_name = service.getBookName(book_id);

                    System.out.println("book_id" + book_id);
                    // Call your service to accept the book renewal request and update return date
                    int renewalstatus = service.AcceptBookRenewalRequest(renewal_id, user_id, book_id);
                    System.out.println("status" + renewalstatus);
                    if (renewalstatus > 0) {
                        int deleteStatus = service.DeleteBookRenewalRequest(renewal_id, user_id);
                        response.getWriter().write("Success");
                        String email = service.getEmailFromUserId(user_id);
                        String subject = "Book Renewal Accept Notification";
                        String message = "Your request for book  " + book_name + " has been Accepted. check your profle for further detail \n\n Regards\n BookHaven";
                        EmailService.sendEmail(email, subject, message);
                        System.out.println("Email sent successfully!");
                    } else {
                        // Send a failure response
                        response.getWriter().write("Failed");
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid number format
                    response.getWriter().write("Invalid parameters");
                    e.printStackTrace();
                } catch (SQLException e) {
                    // Handle SQL exception
                    response.getWriter().write("SQL error");
                    e.printStackTrace();
                }
            } else {
                // Handle null parameters
                response.getWriter().write("Null parameters");
            }
        }

        if (process != null && process.equals("ReturnIssuedBook")) {
            int Issued_id = Integer.parseInt(request.getParameter("Issued_id"));
            int request_id = Integer.parseInt(request.getParameter("request_id"));
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            int book_id = Integer.parseInt(request.getParameter("book_id"));
            String user_name = request.getParameter("user_name");
            String user_email = request.getParameter("user_email");
            String request_date = request.getParameter("request_date");
            String return_date = request.getParameter("return_date");
            // Call your service to delete the book issue request and handle the response
            int deleteStatus = service.deleteBookReturn(Issued_id, user_id);
             String book_name = service.getBookName(book_id);


            if (deleteStatus > 0) {
                // Send a success response
                int insertStatus = service.bookIssuedHistory(Issued_id, request_id, user_id, book_id, user_name, user_email, request_date, return_date);
                 int updatedStock = service.updateStockQuantity(book_id, 1);
                response.getWriter().write("Success");
               String email = service.getEmailFromUserId(user_id);
                String subject = "Book Request Accept Notification";
               String message = "Your request for book with ID " + request_id + " has been Accepted .";
               EmailService.sendEmail(email, subject, message);
               System.out.println("Email sent successfully!");
            } else {
                // Send a failure response
                response.getWriter().write("Failed");
            }
        }

        if (process.equals("sendEmailForReturnBook")) {
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            String return_date = request.getParameter("return_date");
            String email = service.getEmailFromUserId(user_id);
            String user_name = request.getParameter("user_name");
            String book_id = request.getParameter("book_id");
            
            if (email != null && !email.isEmpty()) {
                // Construct email message
                String subject = "Book due in 2 days";
                String message = "Dear " + user_name + " This is a friendly reminder that the book you borrowed from our library is due for return in just 2 days. We kindly request you to return the book by " + return_date + "to avoid any late fees or penalties.\n\n Regards \nBookHaven";

                // Send email
                try {
                    EmailService.sendEmail(email, subject, message);
                    // Send response
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("Email notifications sent successfully!");
                } catch (Exception e) {
                    e.printStackTrace(); // Log or handle the exception
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error sending email");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Recipient email address is null or empty");
            }
        }
        if (process.equals("login")) {
            try {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                int status = service.verifyLogin(email, password);
                if (status > 0) {
                    out.println("1");
                    String login = "login"; // Corrected variable name
                    HttpSession session = request.getSession();
                    session.setAttribute("email", email);
                    session.setAttribute("login", login); // Set the logged-in flag in session
                    request.setAttribute("process", process);
                    request.setAttribute("status", status);
                    RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
                    view.forward(request, response);
                } else {
                    out.println("0");
                }
            } catch (Exception ex) {
                out.print(ex);
            }
        }

        if (process.equals("updateAdminProfile")) {
            String updatedName = request.getParameter("updatedName");
            String updatedEmail = request.getParameter("updatedEmail");
            String updatedPhno = request.getParameter("updatedPhno");
            System.out.println("updatedName" + updatedName);

            // Call a method to update author data in the database
            int status = service.updateAdminProfile(updatedName, updatedEmail, updatedPhno);

            // Return the status as response
            request.setAttribute("process", process);
            request.setAttribute("status", status);
            RequestDispatcher view = request.getRequestDispatcher("ajax.jsp");
            view.forward(request, response);
        }

    }

    private void sendEmail(String name, String email, String password) {
        // SMTP server configuration
        String smtpServer = "smtp.gmail.com"; // Replace with your SMTP server
        String smtpUsername = "sahushubham5249@gmail.com"; // Replace with your SMTP username
        String smtpPassword = "nkdqblgaqyuyegnl"; // Replace with your SMTP password

        // Sender's email address
        String senderEmail = "sahushubham5249@gmail.com";

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.port", "587");

        // Create a session with the SMTP server
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUsername, smtpPassword);
            }
        });

        try {
            // Create a MIME message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Your New Account Information");

            // Set the email content
            String emailContent = "Dear " + name + ",\n\n"
                    + "Your new account information:\n"
                    + "Email: " + email + "\n"
                    + "Password: " + password + "\n\n"
                    + "Please keep this information confidential.\n\n"
                    + "Regards,\n"
                    + "Admin";
            message.setText(emailContent);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully to " + email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
