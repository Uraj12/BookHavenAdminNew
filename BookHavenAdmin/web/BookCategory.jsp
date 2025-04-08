<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@page import="Dashboard.Service.AdminDashboardService"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="Css.jsp" %>
        <title>Book Category</title>
    </head>
    <body>
          <%
            if(session.getAttribute("email") == null ){
                response.sendRedirect("login.jsp");
            }
        %>
        <!-- loader Start -->
        <div id="loading">
            <div id="loading-center">
            </div>
        </div>
        <!-- loader END -->
        <!-- Wrapper Start -->
        <div class="wrapper">
            <!-- Sidebar Start -->
            <%@include file="SideNav.jsp" %>
            <!-- Sidebar End -->

            <!-- Page Content Start -->
            <div id="content-page" class="content-page">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="iq-card">
                                <div class="iq-card-header d-flex justify-content-between">
                                    <div class="iq-header-title">
                                        <h5 class="card-title">Category Lists</h4>
                                    </div>
                                    <div class="iq-card-header-toolbar d-flex align-items-center">
                                        <form method="GET"  class="input-group">
                                            <div class="form-group mb-0 mr-2">
                                                <input type="text" class="form-control" id="searchInput" name="search" placeholder="Enter category name or description">
                                            </div>
                                            <button type="submit" class="btn btn-primary" >Search</button>
                                            <a onclick="adminTabLoader('addCategory')" class="btn btn-primary ml-2">Add New Category</a>
                                        </form>
                                    </div>
                                </div>
                                <div class="iq-card-body">
                                    <div class="table-responsive">
                                        <!-- Table -->
                                        <table class="data-tables table table-striped table-bordered" style="width:100%">
                                            <thead>
                                                <tr>
                                                    <th width="5%">No</th>
                                                    <th width="20%">Category Name</th>
                                                    <th width="65%">Category Description</th>
                                                    <th width="10%">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
                                                    // AdminDashboardService service = new AdminDashboardService();
                                                    String searchQuery = request.getParameter("search");
                                                    ResultSet rs;

                                                    if (searchQuery != null && !searchQuery.isEmpty()) {
                                                        rs = service.searchCategories(searchQuery, currentPage);
                                                    } else {
                                                        rs = service.getCategories(currentPage);
                                                    }

                                                    int count = 1;
                                                    if (!rs.next()) { // Check if ResultSet is empty
                                                %>
                                                <tr>
                                                    <td colspan="8" style="text-align: center;">No data found</td>
                                                </tr>
                                                <% } else { // Display category data if ResultSet is not empty
                                                    do {
                                                %>
                                                <tr>
                                                    <td><%= rs.getInt("category_id")%></td>
                                                    <td><%= rs.getString("category_name")%></td>
                                                    <td><%= rs.getString("description")%></td>
                                                    <td>
                                                        <div class="flex align-items-center list-user-action">
                                                            <a class="bg-primary edit-btn" data-toggle="tooltip" data-placement="top" title="Edit" onclick="toggleEdit(this)"><i class="ri-pencil-line"></i></a>
                                                            <a class="bg-primary" data-toggle="tooltip" data-placement="top" title="Delete" onclick="deletecategory(this)" category_id="<%= rs.getInt("category_id")%>"><i class="ri-delete-bin-line"></i></a>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <% } while (rs.next()); // Continue fetching and displaying category data
                                                    } // End of ResultSet check
                                                %>
                                            </tbody>
                                        </table>
                                        <!-- Pagination -->
                                        <% int totalPages = service.getTotalPages();
                                            int maxPagesToShow = 5;
                                            int startPage = Math.max(1, currentPage - (maxPagesToShow / 2));
                                            int endPage = Math.min(totalPages, startPage + maxPagesToShow - 1);
                                            int nextPage = currentPage + 1;
                                            int prevPage = currentPage - 1;
                                        %>
                                        <nav aria-label="Page navigation example">
                                            <ul class="pagination">
                                                <li class="page-item <%= currentPage == 1 ? "disabled" : ""%>">
                                                    <a class="page-link" href="BookCategory.jsp?page=<%= prevPage%>" tabindex="-1">Previous</a>
                                                </li>
                                                <% for (int i = startPage; i <= endPage; i++) {%>
                                                <li class="page-item <%= currentPage == i ? "active" : ""%>">
                                                    <a class="page-link" href="BookCategory.jsp?page=<%= i%>"><%= i%></a>
                                                </li>
                                                <% }%>
                                                <li class="page-item <%= currentPage == totalPages ? "disabled" : ""%>">
                                                    <a class="page-link" href="BookCategory.jsp?page=<%= nextPage%>">Next</a>
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
        <div type="hidden" id="category"></div>
        <!-- Wrapper End -->
        <%@include file="Footer.jsp" %>
        <%@include file="Js.jsp" %>
    </body>
</html>
