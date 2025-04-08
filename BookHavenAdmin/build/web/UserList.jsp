<%@ page import="java.sql.ResultSet" %>
<%@ page import="Dashboard.Service.AdminDashboardService" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="Css.jsp" %>
    <title>User List</title>
</head>
<body>
     <%
            if(session.getAttribute("email") == null ){
                response.sendRedirect("login.jsp");
            }
        %>
    <!-- Loader Start -->
    <div id="loading">
        <div id="loading-center">
        </div>
    </div>
    <!-- Loader END -->
    <!-- Wrapper Start -->
    <div class="wrapper">
        <!-- Sidebar Start -->
        <%@ include file="SideNav.jsp" %>
        <!-- Sidebar End -->
        <!-- Page Content Start -->
        <div id="content-page" class="content-page">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="iq-card">
                            <div class="iq-card-header d-flex justify-content-between align-items-center">
                                <div class="iq-header-title">
                                    <h4 class="card-title">User Lists</h4>
                                </div>
                                <div class="iq-card-header-toolbar d-flex align-items-center">
                                    <!-- Search form -->
                                    <form method="GET" action="UserList.jsp" class="form-inline ml-auto">
                                        <div class="form-group">
                                            <label for="searchInput" class="sr-only">Search:</label>
                                            <input type="text" class="form-control" id="searchInput" name="search" placeholder="Enter name, email, or contact">
                                        </div>
                                        <button type="submit" class="btn btn-primary">Search</button>
                                    </form>
                                    <!-- Search form end -->
                                    <button onclick="adminTabLoader('addUser')" class="btn btn-primary ml-3">Add New User</button>
                                </div>
                            </div>
                            <div class="iq-card-body">
                                <div class="table-responsive">
                                    <table class="data-tables table table-striped table-bordered" style="width:100%">
                                        <thead>
                                            <tr>
                                                <th>User ID</th>
                                                <th>Name</th>
                                                <th>Email</th>
                                                <th>Contact</th>
                                                <th>Address</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%-- Pagination logic starts here --%>
                                            <% int pageSize = 5; // Number of users per page
                                                int currentPage = 1; // Default page number
                                                if (request.getParameter("page") != null) {
                                                    currentPage = Integer.parseInt(request.getParameter("page"));
                                                }
                                                int start = (currentPage - 1) * pageSize; // Calculate starting index

                                                String searchQuery = request.getParameter("search");
                                                
                                                //AdminDashboardService service = new AdminDashboardService();
                                                ResultSet rs;

                                                if (searchQuery != null && !searchQuery.isEmpty()) {
                                                    rs = service.searchUsers(searchQuery, start, pageSize);
                                                } else {
                                                    rs = service.getViewUserPaginated(start, pageSize);
                                                }

                                                if (!rs.next()) { // Check if ResultSet is empty
                                            %>
                                            <tr>
                                                <td colspan="8" style="text-align: center;">No data found</td>
                                            </tr>
                                            <% } else { // Display book data if ResultSet is not empty
                                                do {
                                            %>
                                            <tr>
                                                <td><%=rs.getInt("user_id")%></td>
                                                <td><%=rs.getString("name")%></td>
                                                <td><%=rs.getString("email")%></td>
                                                <td><%=rs.getString("contact")%></td>
                                                  <td><%=rs.getString("address1")%></td>
                                            </tr>
                                           <% } while (rs.next()); // Continue fetching and displaying book data
                                                } // End of ResultSet check
                                            %>
                                            <%-- Pagination navigation links --%>
                                        </tbody>
                                    </table>
                                    <%-- Pagination navigation links --%>
                                    <%
                                        int totalRecords = service.getTotalUsersCount(); // Get total number of users from service
                                        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
                                        int nextPage = currentPage + 1;
                                        int prevPage = currentPage - 1;
                                    %>
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination">
                                            <li class="page-item <%= currentPage == 1 ? "disabled" : "" %>">
                                                <a class="page-link" href="?page=<%= prevPage %>">Previous</a>
                                            </li>
                                            <li class="page-item <%= currentPage == totalPages ? "disabled" : "" %>">
                                                <a class="page-link" href="?page=<%= nextPage %>">Next</a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Page Content End -->
    </div>
    <!-- Wrapper END -->
    <div id="statusajax"></div>
    <%@ include file="Footer.jsp" %>
    <%@ include file="Js.jsp" %>
</body>
</html>
