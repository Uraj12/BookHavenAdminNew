User
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="Css.jsp" %>
        <title>JSP Page</title>
    </head>
    <body>
         <%
            if(session.getAttribute("email") == null ){
                response.sendRedirect("login.jsp");
            }
        %>
        <div id="loading">
            <div id="loading-center">
            </div>
        </div>
        <div class="wrapper">
            <%@ include file="SideNav.jsp" %>
            <div id="content-page" class="content-page">
                <div class="container-fluid">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <h4 class="card-title">Book Issue Request Lists</h4>
                        </div>
                        <div class="col-md-6 text-right">
                            <button onclick="adminTabLoader('BookIssued')" class="btn btn-primary ml-auto">Book Issued</button>


                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="data-tables table table-striped table-bordered" style="width:100%">
                            <thead>
                                <tr>
                                    <th >request_id</th>
                                    <th >user_id</th>
                                    <th >user_name</th>
                                    <th >user_email</th>
                                    <th >book name</th>
                                    <th >Book_image</th>
                                    <th >book_id</th>
                                    <th >request_date</th>
                                    <th >Action</th>

                                </tr>
                            </thead>
                            <tbody>
                                <%                                try {
                                        ResultSet rs = service.bookissuerequests();
                                        while (rs.next()) {
                                %>
                                <tr>
                                    <td><%= rs.getInt("request_id")%></td>
                                    <td><%= rs.getInt("user_id")%></td>
                                    <td><%= rs.getString("user_name")%></td>
                                    <td><%= rs.getString("user_email")%></td> 
                                    <td>
                                        <div onclick="previewImage('<%=rs.getString("book_img")%>')">
                                            <img class="img-fluid rounded" src="<%=rs.getString("book_img")%>" alt="" style="width: 40px; height: 60px;">
                                        </div>
                                    </td>
                                    <td><%= rs.getInt("book_id")%></td>
                                     <td><%= rs.getString("book_name")%></td>
                                    <td><%= rs.getTimestamp("request_date")%></td>
                                    <td> <button class="btn btn-primary" onclick="AcceptBookRequest(this)" request_id="<%=rs.getInt("request_id")%>" user_id="<%= rs.getInt("user_id")%>" user_name="<%= rs.getString("user_name")%>"user_email="<%= rs.getString("user_email")%>" book_id="<%= rs.getInt("book_id")%>" request_date="<%= rs.getTimestamp("request_date")%>">Accept</button>

                                        <button class="btn btn-danger" onclick="RecjetBookRequest(this)" request_id="<%=rs.getInt("request_id")%>" user_id="<%= rs.getInt("user_id")%>">Reject</button>
                                    </td>




                                </tr>
                                <%
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div id="statusajax"></div>

        </div>

        <%@ include file="Footer.jsp" %>
        <%@ include file="Js.jsp" %>

    </body>
</html>