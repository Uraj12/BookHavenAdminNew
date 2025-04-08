<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@page import="Dashboard.Service.AdminDashboardService"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="Css.jsp" %>
        <title>Book Author</title>
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
                                        <h4 class="card-title">Author Lists</h4>
                                    </div>
                                    <div class="iq-card-header-toolbar d-flex align-items-center">
                                        <div class="form-group mb-0">
                                            <input type="text" class="form-control" id="authorSearch" name="authorSearch" placeholder="Enter author name" >
                                        </div>
                                        <button type="button" class="btn btn-primary ml-2" onclick="searchAuthors()">Search</button>
                                        <button onclick="adminTabLoader('addAuthor')" class="btn btn-primary ml-2">Add New Author</button>
                                    </div>
                                </div>
                                <div class="iq-card-body">
                                    <div class="table-responsive">
                                        <table class="data-tables table table-striped table-bordered" style="width:100%">
                                            <thead>
                                                <tr>
                                                    <th style="width: 5%;">No</th>
                                                    <th style="width: 5%;">Profile</th>
                                                    <th style="width: 20%;">Author Name</th>
                                                    <th style="width: 60%;">Author Description</th>
                                                    <th style="width: 10%;">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
                                                    String searchQuery = request.getParameter("authorSearch");
                                                    // AdminDashboardService service = new AdminDashboardService();
                                                    ResultSet rs;
                                                    int recordsPerPage = 10; // Define the number of records per page
                                                    if (searchQuery != null && !searchQuery.isEmpty()) {
                                                        rs = service.searchAuthorsByName(searchQuery, currentPage, recordsPerPage);
                                                    } else {
                                                        rs = service.getAuthors(currentPage);
                                                    }
                                                    int count = 1;
                                                    while (rs.next()) {
                                                %>
                                                <tr>
                                                    <td><%=rs.getInt("author_id")%></td>
                                                    <td onclick="previewImage('<%=rs.getString("img")%>')">
                                                        <img src="<%=rs.getString("img")%>" class="img-fluid avatar-50 rounded" alt="author-profile">
                                                    </td>

                                                    <td contenteditable="false" id="author_name_<%=rs.getInt("author_id")%>"><%=rs.getString("author_name")%></td>
                                                    <td contenteditable="false" id="author_about_<%=rs.getInt("author_id")%>">
                                                        <p class="mb-0"><%=rs.getString("about")%></p>
                                                    </td>
                                                    <td>
                                                        <div class="flex align-items-center list-user-action">
                                                            <a class="bg-primary edit-btn" data-toggle="tooltip" data-placement="top" title="Edit" href="#" onclick="toggleEditAuthor(this)" author_id="<%=rs.getInt("author_id")%>"><i class="ri-pencil-line"></i></a>
                                                            <a class="bg-primary" data-toggle="tooltip" data-placement="top" title="Delete" href="#" onclick="deleteAuthor(this)" author_id="<%=rs.getInt("author_id")%>"><i class="ri-delete-bin-line"></i></a>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <% count++;
                                                    }%>
                                            </tbody>
                                        </table>
                                        <nav aria-label="Page navigation example">
                                            <ul class="pagination">
                                                <%
                                                    int totalPages = service.getAuthorTotalPages();
                                                    int maxPagesToShow = 5; // Limit the number of pagination tabs to 5
                                                    int startPage = Math.max(1, currentPage - (maxPagesToShow / 2));
                                                    int endPage = Math.min(totalPages, startPage + maxPagesToShow - 1);
                                                    int nextPage = currentPage + 1;
                                                    int prevPage = currentPage - 1;
                                                    String disabledPrevClass = (currentPage == 1) ? "disabled" : "";
                                                    String disabledNextClass = (currentPage == totalPages) ? "disabled" : "";
                                                %>
                                                <li class="page-item <%= disabledPrevClass%>">
                                                    <a class="page-link" href="?page=<%= prevPage%>">Previous</a>
                                                </li>
                                                <% for (int i = startPage; i <= endPage; i++) {%>
                                                <li class="page-item <%= (i == currentPage) ? "active" : ""%>">
                                                    <a class="page-link" href="?page=<%= i%>"><%= i%></a>
                                                </li>
                                                <% }%>
                                                <li class="page-item <%= disabledNextClass%>">
                                                    <a class="page-link" href="?page=<%= nextPage%>">Next</a>
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
        <div type="hidden" id="author"></div>
        <!-- Wrapper END -->
        <%@include file="Footer.jsp" %>
        <%@include file="Js.jsp" %>

    </body>
</html>
