<%-- 
    Document   : ajax
    Created on : Feb 24, 2024, 10:56:01 PM
    Author     : ADMIN
--%>

<%@page import="java.sql.ResultSet"%>
<%@ page import="Dashboard.Service.AdminDashboardService" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<c:if test="${process eq 'addUser'}">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-3">
                <div class="iq-card">
                    <div class="iq-card-header d-flex justify-content-between">
                        <div class="iq-header-title">
                            <h4 class="card-title">Add New User</h4>
                        </div>
                    </div>
                    <div class="iq-card-body">
                        <form>
                            <div class="form-group">
                                <div class="add-img-user profile-img-edit">
                                    <img class="profile-pic img-fluid" src="images/user/11.png" alt="profile-pic">
                                    <div class="p-image">
                                        <a href="javascript:void();" class="upload-button btn iq-bg-primary">File Upload</a>
                                        <input class="file-upload" type="file" accept="image/*">
                                    </div>
                                </div>
                                <div class="img-extension mt-3">
                                    <div class="d-inline-block align-items-center">
                                        <span>Only</span>
                                        <a href="javascript:void();">.jpg</a>
                                        <a href="javascript:void();">.png</a>
                                        <a href="javascript:void();">.jpeg</a>
                                        <span>allowed</span>
                                    </div>
                                </div>
                            </div>


                        </form>
                    </div>
                </div>
            </div>
            <div class="col-lg-9">
                <div class="iq-card">
                    <div class="iq-card-header d-flex justify-content-between">
                        <div class="iq-header-title">
                            <h4 class="card-title">New User Information</h4>
                        </div>
                    </div>
                    <div class="iq-card-body">
                        <div class="new-user-info">
                            <form>
                                <div class="row">
                                    <div class="form-group col-md-6">
                                        <label for="fname">Name:</label>
                                        <input type="text" class="form-control" id="fname" placeholder="First Name">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="fname">Email</label>
                                        <input type="text" class="form-control" id="email" placeholder="Email">
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label for="add1">Street Address 1:</label>
                                        <input type="text" class="form-control" id="add1" placeholder="Street Address 1">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="add2">Street Address 2:</label>
                                        <input type="text" class="form-control" id="add2" placeholder="Street Address 2">
                                    </div>


                                    <div class="form-group col-md-6">
                                        <label for="mobno">Mobile Number:</label>
                                        <input type="text" class="form-control" id="mobno" placeholder="Mobile Number">
                                    </div>




                                </div>
                                <hr>

                                <div class="row">


                                    <div class="checkbox">
                                        <label><input class="mr-2" type="checkbox">Enable Two-Factor-Authentication</label>
                                    </div>
                                    <button type="button" class="btn btn-primary" onclick="addUser()">Add New User</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${process eq 'addBook'}">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-12">
                <div class="iq-card">
                    <div class="iq-card-header d-flex justify-content-between">
                        <div class="iq-header-title">
                            <h4 class="card-title">Add Books</h4>
                        </div>
                    </div>
                    <div class="iq-card-body">
                        <form id="bookForm" method="post">
                            <div class="form-group">
                                <label>Book Name:</label>
                                <input type="text" id="bookName" class="form-control">
                                <div id="statusajax" style="color: red;"></div>
                            </div>
                            <div class="form-group">
                                <label>Book Category:</label>
                                <a class="bg-primary" id="addauthor" data-toggle="tooltip" data-placement="top" title="" data-original-title="Edit" onclick="addCategorySawl()" ><i class="ri-add-fill"></i></a>
                                <select id="bookCategory" class="form-control">
                                    <option value="">Select Category</option>
                                    <%
                                        AdminDashboardService service = new AdminDashboardService();
                                        ResultSet rsCategories = service.getCategories();
                                        while (rsCategories.next()) {
                                    %>
                                    <option value="<%= rsCategories.getInt("category_id")%>"><%= rsCategories.getString("category_name")%></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="form-group">
                                <div class="justify-content-between">
                                    <label>Book Author:  </label>
                                    <a class="bg-primary" id="addauthor" data-toggle="tooltip" data-placement="top" title="" data-original-title="Edit" onclick="addAuthorSwal()" ><i class="ri-add-fill"></i></a>

                                </div>
                                <select id="bookAuthor" class="form-control">
                                    <option value="">Select Author</option>

                                    <%
                                        ResultSet rsAuthors = service.getAuthor();
                                        while (rsAuthors.next()) {
                                    %>
                                    <option value="<%= rsAuthors.getInt("author_id")%>"><%= rsAuthors.getString("author_name")%></option>
                                    <% }%>
                                </select>

                            </div>
                            <div class="form-group">
                                <label>Book Image URL:</label>
                                <input type="text" id="bookImageUrl" class="form-control" oninput="updateImagePreview()">
                                <img id="imagePreview" src="" alt="Image Preview" style="max-width: 100%; height: auto; margin-top: 10px; display: none;">
                            </div>
                            <div class="form-group">
                                <label>Book Price:</label>
                                <input type="number" id="bookPrice" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Book Quantity:</label>
                                <input type="number" id="Quantity" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Publish Year:</label>
                                <input type="number" id="publishYear" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Book Description:</label>
                                <textarea class="form-control" id="bookDescription" rows="4"></textarea>
                            </div>
                            <button type="button" onclick="addBook()" class="btn btn-primary">Submit</button>
                            <button type="reset" class="btn btn-danger">Reset</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>

</c:if>





<c:if test="${process eq 'addAuthor'}">
    <div class="iq-card-body">
        <form id="authorForm" >
            <div class="form-group">
                <label for="authorName">Author Name:</label>
                <input type="text" class="form-control" id="authorName">
            </div>
            <div class="form-group">
                <label>Author Image URL:</label>
                <input type="text" id="AuthorImageUrl" class="form-control" oninput="updateImagePreview()">
                <img id="imagePreview" src="" alt="Image Preview" style="max-width: 100%; height: auto; margin-top: 10px; display: none;">
            </div>
            <div class="form-group">
                <label for="authorDescription">Author Description:</label>
                <textarea class="form-control" rows="4" id="authorDescription"></textarea>
            </div>
            <button type="button" class="btn btn-primary" onclick="addAuthor()">Submit</button>
            <button type="reset" class="btn btn-danger">Reset</button>
        </form>
        <div id="statusajax"></div>
    </div>

</c:if>
<c:if test="${process eq 'addCategory'}">
    <div class="iq-card-body">
        <form action="admin-category.html">
            <div class="form-group">
                <label>Category Name:</label>
                <input type="text" class="form-control" id="Categoryname">
            </div>
            <div class="form-group">
                <label>Category Description:</label>
                <textarea class="form-control" rows="4" id="Categorydescription"></textarea>
            </div>
            <button type="button" class="btn btn-primary" onclick="addCategory()">Submit</button>
            <button type="reset" class="btn btn-danger">Reset</button>
        </form>
        <div id="statusajax"></div>
    </div>
</c:if>
<c:if test="${process eq 'BookIssued'}">
    <div class="table-responsive">
        <h3>Book issued</h3>
        <table class="data-tables table table-striped table-bordered" style="width:100%">
            <thead>
                <tr>
                    <th>issued_id</th>
                    <th>request_id</th>
                    <th>user_id</th>
                    <th>user_name</th>
                    <th>user_email</th>
                    <th>Book image</th>
                    <th>book_id</th>
                    <th>request_date</th>
                    <th>Return date</th>
                    <th>Penalty</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% try {
                        Dashboard.Service.AdminDashboardService service = new Dashboard.Service.AdminDashboardService();
                        ResultSet rs = service.bookissued();
                        while (rs.next()) {%>
                <tr>
                    <td><%= rs.getInt("Issued_id")%></td>
                    <td><%= rs.getInt("request_id")%></td>
                    <td><%= rs.getInt("user_id")%></td>
                    <td><%= rs.getString("user_name")%></td>
                    <td><%= rs.getString("user_email")%></td>
                    <td>
                        <div onclick="previewImage('<%=rs.getString("book_img")%>')">
                            <img class="img-fluid rounded" src="<%=rs.getString("book_img")%>" alt="" style="width: 40px; height: 62px;">
                        </div>
                    </td>
                    <td><%= rs.getInt("book_id")%></td>
                    <td><%= rs.getTimestamp("request_date")%></td>
                    <td>
                        <%-- Check if return date is 2 days or less away --%>
                        <% java.util.Date returnDate = rs.getDate("return_date"); %>
                        <% java.util.Date currentDate = new java.util.Date(); %>
                        <% long diffInMillies = returnDate.getTime() - currentDate.getTime(); %>
                        <% long diffInDays = diffInMillies / (1000 * 60 * 60 * 24); %>
                        <%  int penalty = 0; // Initialize penalty as 0

                            if (diffInDays <= 0) { // If return date is passed
                                penalty = (int) (-diffInDays * 5); // Calculate penalty
                            }%>
                        <% if (diffInDays <= 2) {%>
                        <span style="color: red;"><%= returnDate%></span>
                        <% } else {%>
                        <%= returnDate%>
                        <% }%>
                    </td>
                    <td><%= penalty%></td>
                    <td>
                        <button class="btn btn-primary" onclick="ReturnIssuedBook(this)"
                                Issued_id="<%= rs.getInt("Issued_id")%>"
                                request_id="<%= rs.getInt("request_id")%>"
                                user_id="<%= rs.getInt("user_id")%>"
                                user_name="<%= rs.getString("user_name")%>"
                                user_email="<%= rs.getString("user_email")%>"
                                book_id="<%= rs.getInt("book_id")%>"
                                request_date="<%= rs.getTimestamp("request_date")%>"
                                return_date="<%= rs.getDate("return_date")%>">Return</button>
                        <a class="search-toggle iq-waves-effect text-gray rounded text-right" onclick="sendEmailForReturn(this)" book_id="<%= rs.getInt("book_id")%>" user_id="<%= rs.getInt("user_id")%>" return_date="<%= rs.getDate("return_date")%>" user_name="<%= rs.getString("user_name")%>">
                            <i class="ri-notification-2-line"></i>
                            <span class="bg-primary dots"></span>
                        </a>

                    </td>
                </tr>
                <% }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }%>
            </tbody>
        </table>
    </div>
</c:if>


<c:if test="${process eq 'insertBook'}">
    <input type="hidden" id="status" value="${status}"/>
</c:if>

<c:if test="${process eq 'deleteauthor'}">
    <input type="hidden" id="status" value="${status}"/>
</c:if>

<c:if test="${process eq 'deletecategory'}">
    <input type="hidden" id="status" value="${status}"/>
</c:if>

<c:if test="${process eq 'updateAuthor'}">
    <input type="hidden" id="status" value="${status}"/>
</c:if>
<c:if test="${process eq 'updateBook'}">
    <input type="hidden" id="status" value="${status}"/>
</c:if>

<c:if test="${process eq 'updateCategory'}">
    <input type="hidden" id="status" value="${status}"/>
</c:if>
<c:if test="${process eq 'insertAuthor'}">
    <input type="hidden" id="status" value="${status}"/>
</c:if>

<c:if test="${process eq 'insertCategory'}">
    <input type="hidden" id="status" value="${status}"/>
</c:if>

<c:if test="${process eq 'updateImage'}">
    <input type="hidden" id="status" value="${status}"/>
</c:if>

<c:if test="${process eq 'addUserByAdmin'}">
    <input type="hidden" id="status" value="${status}"/>
</c:if>

<c:if test="${process eq 'notfeatured'}">
    <input type="hidden" id="status" value="${status}"/>
</c:if>
<c:if test="${process eq 'isfeatured'}">
    <input type="hidden" id="status" value="${status}"/>
</c:if>
<c:if test="${process eq 'deleteorder'}">
    <input type="hidden" id="status" value="${status}"/>
</c:if>

<c:if test="${process eq 'sendEmailNotification'}">
    <input type="hidden" id="status" value="${status}"/>
</c:if>
<c:if test="${process eq 'RecjetBookRequest'}">
    <input type="hidden" id="status" value="${status}"/>
</c:if>
<c:if test="${process eq 'AcceptOrder'}">
    <input type="hidden" id="status" value="${status}"/>
</c:if>
<c:if test="${process eq 'login'}">
    <input type="hidden" id="status" value="${status}" />
</c:if>
<c:if test="${process eq 'updateAdminProfile'}">
    <input type="hidden" id="status" value="${status}" />
</c:if>