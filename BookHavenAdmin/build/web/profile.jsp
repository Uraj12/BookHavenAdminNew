<%-- 
    Document   : profile
    Created on : 23-Mar-2024, 6:34:20 pm
    Author     : user
--%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="Dashboard.Service.AdminDashboardService" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <%@include file="Css.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%
            if(session.getAttribute("email") == null ){
                response.sendRedirect("login.jsp");
            }
        %>
        <h1>Hello World!</h1>
          <!--loader Start-->
        <div id="loading">
            <div id="loading-center">
            </div>
        </div>
          <div class="wrapper">
                <%@include file="SideNav.jsp" %>
                <div>
                    <div id="content-page" class="content-page">
                         <%
                            
                        ResultSet rs = service.AdminProfile(userEmail);
                         while (rs.next()) {
                       %>
            <div class="container-fluid">
               <div class="row profile-content">
                  <div class="col-12 col-md-12 col-lg-4">
                     <div class="iq-card">
                        <div class="iq-card-body profile-page">
                           <div class="profile-header">
                              <div class="cover-container text-center">
                                 <img src="images/user/1.jpg" alt="profile-bg" class="rounded-circle img-fluid">
                                 <div class="profile-detail mt-3">
                                    <h3><%= rs.getString("name")%></h3>
                                    
                                    
                                 </div>
                                 <div class="iq-social d-inline-block align-items-center">
                                    <ul class="list-inline d-flex p-0 mb-0 align-items-center">
                                       <li>
                                          <a  class="avatar-40 rounded-circle bg-primary mr-2 facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                                       </li>
                                       <li>
                                          <a  class="avatar-40 rounded-circle bg-primary mr-2 twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                                       </li>
                                       <li>
                                          <a  class="avatar-40 rounded-circle bg-primary mr-2 youtube"><i class="fa fa-youtube-play" aria-hidden="true"></i></a>
                                       </li>
                                       <li >
                                          <a class="avatar-40 rounded-circle bg-primary pinterest"><i class="fa fa-pinterest-p" aria-hidden="true"></i></a>
                                       </li>
                                    </ul>
                                 </div>
                              </div>
                           </div>
                        </div>
                     </div>
                   
                  
                  </div>
                   <div class="iq-card">
                      
                        <div class="iq-card-header d-flex justify-content-between align-items-center mb-0">
                           <div class="iq-header-title">
                              <h4 class="card-title mb-0">Personal Details</h4>
                           </div>
                        </div> 
                        <div class="iq-card-body">
                           <ul class="list-inline p-0 mb-0">
                              <li>
                                 <div class="row align-items-center justify-content-between mb-3">
                                    <div class="col-sm-6">
                                       <h6>name</h6>                                       
                                    </div>
                                    <div class="col-sm-6">
                                     <p id="name"><%= rs.getString("name")%></p>                                
                                    </div>
                                 </div>
                              </li>
                              <li>
                                 <div class="row align-items-center justify-content-between mb-3">
                                    <div class="col-sm-6">
                                       <h6>Email</h6>                                       
                                    </div>
                                    <div class="col-sm-6">
                                      <p id="email"><%= rs.getString("email")%></p>                                
                                    </div>
                                 </div>
                              </li>
                                                          
                              <li>
                                 <div class="row align-items-center justify-content-between mb-3">
                                    <div class="col-sm-6">
                                       <h6>phone number</h6>                                       
                                    </div>
                                    <div class="col-sm-6">
                                      <p id="phno"><%= rs.getString("phno")%></p>                                      
                                    </div>
                                 </div>
                              </li>
                               <li>
                                 <div class="row align-items-center justify-content-between mb-3">
                                    <div class="col-sm-6">
                                 <button id="editButton" class="btn btn-primary" onclick="toggleEditable()">Edit</button>
                                       
                                    </div>
                                    <div class="col-sm-6">
                                       <button class="btn btn-danger">Delete</button>                                    
                                    </div>
                                 </div>
                              </li>
                           </ul>
                        </div>
                 
                     </div>
                       
                     </div>
                     
                  </div>
               </div>
                     <%}%>
            </div>
         </div>
      
          
          <%@include file="Footer.jsp" %>
        <%@include file="Js.jsp" %>
        <script>
    function toggleEditable() {
    var name = document.getElementById('name');
    var email = document.getElementById('email');
    var phno = document.getElementById('phno');
    var editButton = document.getElementById('editButton');

    name.contentEditable = !name.isContentEditable;
   // email.contentEditable = !email.isContentEditable;
    phno.contentEditable = !phno.isContentEditable;

    // Toggle button text and function
    if (editButton.innerHTML.trim() === 'Edit') {
        editButton.innerHTML = 'Save';
        editButton.onclick = saveChanges;
    } else {
        editButton.innerHTML = 'Edit';
        editButton.onclick = toggleEditable;
    }
}

function saveChanges() {
    var updatedName = document.getElementById('name').textContent;
    var updatedEmail = document.getElementById('email').textContent;
    var updatedPhno = document.getElementById('phno').textContent;
    alert(updatedName);
  ajaxcall('POST', 'AdminDashboardServlet.fin', 'updatedName=' + updatedName + '&updatedEmail=' + updatedEmail + '&updatedPhno=' + updatedPhno + '&process=updateAdminProfile', 'statusajax', true);

    // You can also reset the button back to "Edit" if needed
    var editButton = document.getElementById('editButton');
    editButton.innerHTML = 'Edit';
    editButton.onclick = toggleEditable;
}

</script>

    </body>
</html>
