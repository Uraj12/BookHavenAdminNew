<%-- 
    Document   : orders
    Created on : 21-Mar-2024, 9:36:05 pm
    Author     : user
--%>

<%@page import="java.util.Iterator"%>
<%@page import="org.json.JSONObject"%>
<%@page import="Dashboard.Service.AdminDashboardService"%>
<%@ page import="java.sql.ResultSet" %>
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
        <div class="container-fluid">
        <div class="wrapper " style=" padding-top: 100px">
            <%@include file="SideNav.jsp" %>
            
            <div>
                
    <!-- Your content here -->
</div>

                
                        <div class="row" style=" padding-left: 70px">
                        <%
                                            //AdminDashboardService service = new AdminDashboardService();
                                            int orders = 0;

                                            ResultSet rs = service.getAllOrders();

                                            while (rs.next()) {
                                                String orderStatus = rs.getString("order_status");
                                                String orderItemsJson = rs.getString("order_items");
                                                JSONObject orderItemsObject = new JSONObject(orderItemsJson);

                                                orders = orderItemsObject.length();
                                                String badgeClass = orderStatus.equals("Not Acceped")
                                                        ? "badge badge-pill badge-danger"
                                                        : "badge badge-pill badge-success";
                                        %>
                                        <div class="col-lg-4">
                                            <div class="iq-card iq-card-block iq-card-stretch iq-card-height">
                                                <div class="iq-card-header d-flex justify-content-between mb-0">
                                                    <div class="iq-header-title">
                                                        <h4 class="card-title">Ordered on : <%=rs.getDate("dateAdded")%></h4>
                                                    </div>
                                                    <div class="iq-card-header-toolbar d-flex align-items-center">
                                                        <p class="mb-2">ID : <%=rs.getInt("order_id")%></p>
                                                    </div>
                                                </div>
                                                <div class="iq-card-body">
                                                    <h5>Total items: <%=orders%></h5>
                                                    <p>Total amount: $<%=rs.getDouble("total_amount")%></p>
                                                    <p>Order status: <span class="<%= badgeClass%>">
                                                            <%= rs.getString("order_status")%>
                                                        </span></p>
                                                    <p>Payment type: <%=rs.getString("order_type")%></p>
                                                    <p>Address: <%=rs.getString("address1")%></p>
                                                    <!-- Order items -->
                                                    <div class="scrollable-table-container" style="max-height: 150px; overflow-y: auto;">
                                                        <table class="table">
                                                            <thead style="position: sticky; top: 0; background-color: #f5f5f5;">
                                                                <tr>
                                                                    <th>Book ID</th>
                                                                    <th>Quantity</th>
                                                                    <th>Price</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <% Iterator<String> keys = orderItemsObject.keys(); %>
                                                                <% while (keys.hasNext()) { %>
                                                                <% String key = keys.next(); %>
                                                                <% JSONObject itemJson = orderItemsObject.getJSONObject(key);%>
                                                                <tr>
                                                                    <td><%= itemJson.getInt("book_id")%></td>
                                                                    <td><%= itemJson.getInt("quantity")%></td>
                                                                    <td><%= itemJson.getDouble("total_price")%></td>
                                                                </tr>
                                                                <% }%>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                  
                                                    <% if (orderStatus.equals("Shipping")) { %>
                                                    <button class="btn btn-primary" disabled>Shipped</button>
                                                    <% } else {%>
                                                    <button class="btn btn-primary" onclick="AcceptOrder(this)" order_id="<%= rs.getInt("order_id")%>">Shipping</button>
                                                    <% }%>
                                                    <button class="btn btn-danger" onclick="deleteOrder(this)" order_id="<%= rs.getInt("order_id")%>">Delete</button>




                                                </div>
                                            </div>
                                        </div>
                                        <%
                                            }
                                        %>
                            <div type="hidden" id="order"></div>
                            
            </div>
                  </div>
     
        <%@include file="Footer.jsp" %>
        <%@include file="Js.jsp" %>
    </body>
</html>
