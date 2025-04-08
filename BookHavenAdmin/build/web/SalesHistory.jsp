<%-- 
    Document   : SalesHistory
    Created on : 23-Mar-2024, 1:30:04 pm
    Author     : user
--%>

<%@page import="java.util.Iterator"%>
<%@page import="org.json.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <%@ include file="Css.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <div class="wrapper " style="padding-top: 100px">
             <%@include file="SideNav.jsp" %>
            
             <div>
               
                    <div class="table-responsive" style="padding-left: 100px">
                        <table class="table mb-0 table-borderless "  >
                            <thead>
                                <tr>
                                    <th>history_id</th>
                                    <th >order_id</th>

                                   <th scope="col" >user_id</th>
                                    <th scope="col" >orderDate</th>
                                    <th scope="col"  >ordered_items</th>
                                    <th scope="col" >total_amount</th>
                                    

                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    try {
                                        ResultSet rs = service.TotalSalesHistory();
                                        while (rs.next()) {
                                            
                                %>
                                <tr>
                                    <td><%= rs.getInt("history_id")%></td>
                                    <td><%= rs.getInt("order_id")%></td>
                                     <td><%= rs.getInt("user_id")%></td>
                                   <td><%= rs.getTimestamp("orderDate")%></td>
                                    <td><%= rs.getString("ordered_items")%></td>
                                    
                                    <td><%= rs.getInt("total_amount")%></td>
                                    
                                   


                                
                                   


                                </tr>
                                <%
                                        }
                                    } catch (Exception e) {

                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        
        <%@include file="Footer.jsp" %>
        <%@include file="Js.jsp" %>
        
    </body>
</html>
