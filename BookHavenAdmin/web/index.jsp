<%@page import="java.util.Iterator"%>
<%@page import="Dashboard.Service.AdminDashboardService"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="org.json.JSONObject" %> <!-- Import the JSONObject class -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <%@include file="Css.jsp" %>
        <title>BookHaven</title>
    </head>
    <body>
        <%
            if (session.getAttribute("email") == null) {
                response.sendRedirect("login.jsp");
            }
        %>
        <!--loader Start-->
        <div id="loading">
            <div id="loading-center">
            </div>
        </div>
        <!--loader END-->
        <!-- Wrapper Start -->
        <div class="wrapper">
            <!-- Sidebar  -->
            <%@include file="SideNav.jsp" %>
            <!-- Page Content  -->
            <div>
                <%
                    //AdminDashboardService service = new AdminDashboardService();
                    int totalBooksCount = service.getTotalBooksCount();
                    int totalUSersCount = service.getTotalUsersCount();
                    int totalOrderCount = service.TotalOrders();
                    int bookrenewalrequest = service.bookrenewalrequest();
                    int totalSaleCount = service.TotalSales();
                    int bookissuehistory = service.bookissuehistory();
                %>
                <div id="content-page" class="content-page">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-6 col-lg-3">
                                <a href="UserList.jsp">
                                    <div class="iq-card iq-card-block iq-card-stretch iq-card-height">
                                        <div class="iq-card-body">
                                            <div class="d-flex align-items-center">
                                                <div class="rounded-circle iq-card-icon bg-primary"><i class="ri-user-line"></i></div>
                                                <div class="text-left ml-3">
                                                    <h2 class="mb-0"><span class="counter"><%= totalUSersCount%></span></h2>
                                                    <h5 class="">Users</h5>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>

                            <div class="col-md-6 col-lg-3">
                                <a href="Books.jsp">
                                    <div class="iq-card iq-card-block iq-card-stretch iq-card-height">
                                        <div class="iq-card-body">
                                            <div class="d-flex align-items-center">
                                                <div class="rounded-circle iq-card-icon bg-danger"><i class="ri-book-line"></i></div>
                                                <div class="text-left ml-3">
                                                    <h2 class="mb-0"><span class="counter"><%= totalBooksCount%></span></h2>
                                                    <h5 class="">Books</h5>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-6 col-lg-3">
                                <a href="SalesHistory.jsp">
                                    <div class="iq-card iq-card-block iq-card-stretch iq-card-height">
                                        <div class="iq-card-body">
                                            <div class="d-flex align-items-center">
                                                <div class="rounded-circle iq-card-icon bg-warning"><i class="ri-shopping-cart-2-line"></i></div>
                                                <div class="text-left ml-3">
                                                    <h2 class="mb-0"><span class="counter"><%= totalSaleCount%></span></h2>
                                                    <h5 class="">Sale</h5>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-6 col-lg-3">
                                <a href="orders.jsp">
                                    <div class="iq-card iq-card-block iq-card-stretch iq-card-height">
                                        <div class="iq-card-body">
                                            <div class="d-flex align-items-center">
                                                <div class="rounded-circle iq-card-icon bg-info"><i class="ri-radar-line"></i></div>
                                                <div class="text-left ml-3" >
                                                    <h2 class="mb-0"><span class="counter"><%= totalOrderCount%></span></h2>
                                                    <h5 class="">Orders</h5>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-6 col-lg-3">
                                <a href="BookIssueRequests.jsp">
                                    <div class="iq-card iq-card-block iq-card-stretch iq-card-height">
                                        <div class="iq-card-body">
                                            <div class="d-flex align-items-center">
                                                <div class="rounded-circle iq-card-icon bg-primary bg-warning "> <i class="ri-store-line"></i></div>
                                                <div class="text-left ml-3">
                                                    <h2 class="mb-0"><span class="counter"><%= bookissuerequest%></span></h2>
                                                    <h5 class=""> Book issue  requests </h5>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-6 col-lg-3">
                                <a href="BookRenewalRequests.jsp">
                                    <div class="iq-card iq-card-block iq-card-stretch iq-card-height">
                                        <div class="iq-card-body">
                                            <div class="d-flex align-items-center">
                                                <div class="rounded-circle iq-card-icon bg-info"> <i class="ri-store-line"></i></div>
                                                <div class="text-left ml-3">
                                                    <h2 class="mb-0"><span class="counter"><%= bookrenewalrequest%></span></h2>
                                                    <h5 class=""> Book Renewal  requests </h5>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-6 col-lg-3">
                                <a href="BookRenewalRequests.jsp">
                                    <div class="iq-card iq-card-block iq-card-stretch iq-card-height">
                                        <div class="iq-card-body">
                                            <div class="d-flex align-items-center">
                                                <div class="rounded-circle iq-card-icon bg-primary"> <i class="ri-store-line"></i></div>
                                                <div class="text-left ml-3">
                                                    <h2 class="mb-0"><span class="counter"><%= bookissuehistory%></span></h2>
                                                    <h5 class=""> Book issued  history </h5>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-6 col-lg-3">
                                <canvas id="salesChart" width="400" height="200"></canvas>

                            </div>
                        </div>





                        <div class="col-sm-12">
                            <div class="iq-card iq-card-block iq-card-stretch iq-card-height">
                                <div class="iq-card-header d-flex justify-content-between">
                                    <div class="iq-header-title">
                                        <h4 class="card-title">Recent orders</h4>
                                    </div>
                                    <div class="iq-card-header-toolbar d-flex align-items-center">
                                        <div class="dropdown">
                                            <span class="dropdown-toggle text-primary" id="dropdownMenuButton5" data-toggle="dropdown">
                                                <i class="ri-more-fill"></i>
                                            </span>
                                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton5">
                                                <a class="dropdown-item" href="admin-dashboard.html#"><i class="ri-eye-fill mr-2"></i>View</a>
                                                <a class="dropdown-item" href="admin-dashboard.html#"><i class="ri-delete-bin-6-fill mr-2"></i>Delete</a>
                                                <a class="dropdown-item" href="admin-dashboard.html#"><i class="ri-pencil-fill mr-2"></i>Edit</a>
                                                <a class="dropdown-item" href="admin-dashboard.html#"><i class="ri-printer-fill mr-2"></i>Print</a>
                                                <a class="dropdown-item" href="admin-dashboard.html#"><i class="ri-file-download-fill mr-2"></i>Download</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="iq-card-body">
                                    <div class="table-responsive">
                                        <a href="orders.jsp" class="table-link">
                                            <table class="table mb-0 table-borderless " >
                                                <thead>
                                                    <tr>
                                                        <th scope="col">order id</th>
                                                        <th scope="col">user_id</th>

                                                        <th scope="col" >Address</th>
                                                        <th scope="col" >phone number</th>
                                                        <th scope="col">order_Quantity</th>

                                                        <th scope="col" >date</th>
                                                        <th scope="col">order type</th>
                                                        <th scope="col">order_status</th>
                                                        <th scope="col">total amount</th>
                                                        <th scope="col">Action</th>

                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        ResultSet rs = service.getAllOrders();
                                                        while (rs.next()) {
                                                            String orderStatus = rs.getString("order_status");
                                                            String badgeClass = orderStatus.equals("Not Acceped")
                                                                    ? "badge badge-pill badge-danger"
                                                                    : "badge badge-pill badge-success";
                                                            // Get order items as JSON
                                                            String orderItemsJsonString = rs.getString("order_items");
                                                            // Convert JSON string to JSONObject
                                                            JSONObject orderItemsJson = new JSONObject(orderItemsJsonString);
                                                    %>
                                                    <tr>
                                                        <td><%= rs.getInt("order_id")%></td>
                                                        <td><%= rs.getInt("user_id")%></td>
                                                        <td><%= rs.getString("address1")%></td>
                                                        <td><%= rs.getInt("phno")%></td>
                                                        <td>
                                                            <%-- Iterate over order items JSON and display each item --%>
                                                            <address>
                                                                <% Iterator<String> keys = orderItemsJson.keys(); %>
                                                                <% int totalBooksOrdered = 0; %>
                                                                <% while (keys.hasNext()) { %>
                                                                <% String key = keys.next(); %>
                                                                <% String value = orderItemsJson.getString(key); %>
                                                                <% JSONObject itemJson = new JSONObject(value); %>
                                                                <% // Calculate the total number of books ordered
                                                                    int quantity = itemJson.getInt("quantity");
                                                                    totalBooksOrdered += quantity;
                                                                %>
                                                                <% }%>
                                                                <%-- Display the total number of books ordered after iterating over all items --%>
                                                                <p><%= totalBooksOrdered%></p>
                                                            </address>



                                                        </td>

                                                        <td><%= rs.getTimestamp("dateAdded")%></td>
                                                        <td><%= rs.getString("order_type")%></td>
                                                        <td>
                                                            <div class="<%= badgeClass%>">

                                                                <%= rs.getString("order_status")%>
                                                            </div>
                                                        </td>


                                                        <td><%= rs.getString("total_amount")%></td>
                                                        <td>
                                                            <!-- Add action buttons here -->
                                                            <%
                                                                if (orderStatus.equals("Shipping")) {
                                                            %>
                                                            <button class="btn btn-primary" disabled>shipped</button>
                                                            <%
                                                            } else {
                                                            %>
                                                            <button class="btn btn-primary" onclick="AcceptOrder(this, <%= rs.getInt("order_id")%>)">Shipping</button>

                                                            <%
                                                                }
                                                            %>
                                                            <button class="btn btn-danger" onclick="deleteOrder(<%= rs.getInt("order_id")%>)">Delete</button>
                                                        </td>


                                                    </tr>
                                                    <%
                                                        }
                                                    %>
                                                </tbody>
                                            </table>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <%@include file="Footer.jsp" %>
        <%@include file="Js.jsp" %>
    </body>
</html>
