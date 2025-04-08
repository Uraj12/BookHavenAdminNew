<%-- 
    Document   : SideNav
    Created on : 24-Feb-2024, 12:38:50 pm
    Author     : ajay
--%>

<%@page import="Dashboard.Service.AdminDashboardService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="Css.jsp" %>

    </head>
    <body>
         
        <div class="iq-sidebar">
            <div class="iq-sidebar-logo d-flex justify-content-between">
                <a href="index.jsp" class="header-logo">
                    <img src="images/logo.png" class="img-fluid rounded-normal" alt="">
                    <div class="logo-title">
                        <span class="text-primary text-uppercase">BookHaven</span>
                    </div>
                </a>
                <div class="iq-menu-bt-sidebar">
                    <div class="iq-menu-bt align-self-center">
                        <div class="wrapper-menu">
                            <div class="main-circle"><i class="las la-bars"></i></div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="sidebar-scrollbar">
                <nav class="iq-sidebar-menu">
                    <ul id="iq-sidebar-toggle" class="iq-menu">

                        <li class="active active-menu">
                            <a href="admin-dashboard.html#admin" class="iq-waves-effect" data-toggle="collapse" aria-expanded="true"><span class="ripple rippleEffect"></span><i class="las la-home iq-arrow-left"></i><span>Admin</span><i class="ri-arrow-right-s-line iq-arrow-right"></i></a>
                            <ul id="admin" class="iq-submenu collapse show" data-parent="#iq-sidebar-toggle">
                                <li class="active"><a href="index.jsp"><i class="las la-house-damage"></i>Dashboard</a></li>
                                <li><a href="BookCategory.jsp"><i class="ri-function-line"></i>Books Category</a></li>
                                <li><a href="BookAuthor.jsp"><i class="ri-book-line"></i>Author</a></li>
                                <li><a href="Books.jsp"><i class="ri-file-pdf-line"></i>Books</a></li>
                                <li><a href="UserList.jsp"><i class="ri-user-line"></i>User List</a></li>
                                <li><a href="BookIssueRequests.jsp"><i class="ri-git-repository-commits-line"></i>Book Issue Request</a></li>
                                <li><a href="BookRenewalRequests.jsp"><i class="ri-git-repository-commits-line"></i>Book Renewal Requests</a></li>
                                <li><a href="orders.jsp"><i class="las la-th-list"></i>Orders</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <!-- TOP Nav Bar -->
        <div class="iq-top-navbar">
            <div class="iq-navbar-custom">
                <nav class="navbar navbar-expand-lg navbar-light p-0">
                    <div class="iq-menu-bt d-flex align-items-center">
                        <div class="wrapper-menu">
                            <div class="main-circle"><i class="las la-bars"></i></div>
                        </div>
                        <div class="iq-navbar-logo d-flex justify-content-between">
                            <a href="index.html" class="header-logo">
                                <img src="images/logo.png" class="img-fluid rounded-normal" alt="">
                                <div class="logo-title">
                                    <span class="text-primary text-uppercase">Bookhaven</span>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="navbar-breadcrumb">
                        <h5 class="mb-0">Shop</h5>
                        <nav aria-label="breadcrumb">
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Home Page</li>
                            </ul>
                        </nav>
                    </div>
                    <div class="iq-search-bar">
                        <form action="index.html#" class="searchbox">
                            <input type="text" class="text search-input" placeholder="Search Here...">
                            <a class="search-link" href="index.html#"><i class="ri-search-line"></i></a>
                        </form>
                    </div>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"  aria-label="Toggle navigation">
                        <i class="ri-menu-3-line"></i>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ml-auto navbar-list">
                            <li class="nav-item nav-icon search-content">
                                <a href="index.html#" class="search-toggle iq-waves-effect text-gray rounded">
                                    <i class="ri-search-line"></i>
                                </a>
                                <form action="" class="search-box p-0">
                                    <input type="text" class="text search-input" placeholder="Type here to search...">
                                    <a class="search-link" href=""><i class="ri-search-line"></i></a>
                                </form>
                            </li>
                            <li class="nav-item nav-icon">

                                <a href="index.html#" class="search-toggle iq-waves-effect text-gray rounded">
                                    <i class="ri-notification-2-line"></i>
                                    <span class="bg-primary dots"></span>
                                </a>

                                <%
                                    AdminDashboardService service = new AdminDashboardService();
                                    int bookissuerequest = service.bookissuerequest();
                                %>
                                <div class="iq-sub-dropdown">
                                    <div class="iq-card shadow-none m-0">
                                        <div class="iq-card-body p-0 notifications-container">
                                            <div class="bg-primary p-3">
                                                <h5 class="mb-0 text-white">Book issue Notifications<small class="badge badge-light float-right pt-1"><%= bookissuerequest%></small></h5>
                                            </div>
                                            <div class="notifications-content">
                                                <%
                                                    ResultSet rs1;
                                                    rs1 = service.bookissuerequests();
                                                    int counter = 0; // Counter for limiting notifications
                                                    while (rs1.next() && counter < 5) { // Limit to 5 notifications
                                                        counter++;
                                                %>
                                                <a href="BookIssueRequests.jsp" class="iq-sub-card">
                                                    <div class="media align-items-center">
                                                        <div class="">
                                                            <div class="rounded-circle iq-card-icon bg-primary"><i class="ri-user-line"></i></div>
                                                        </div>
                                                        <div class="media-body ml-3">
                                                            <h6 class="mb-0 "><%= rs1.getString("user_name")%></h6>
                                                            <p class="float-right font-size-12"><%= rs1.getTimestamp("request_date")%></p>
                                                           
                                                        </div>
                                                    </div>
                                                </a>
                                                <%}%>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                            </li>

                            <li class="line-height pt-3">
                                <%
                                    String userEmail = (String) session.getAttribute("email");
                                    if (userEmail != null && !userEmail.isEmpty()) {
                                        char firstLetter = userEmail.charAt(0);
                                %>


                                <a href="index.html#" class="search-toggle iq-waves-effect d-flex align-items-center">
                                    <div class=" name-initial">

                                        <span class="initial"> <%= Character.toUpperCase(firstLetter)%></span>

                                        <%}%>
                                    </div>
                                    <div class="caption">
                                        <h6 class="mb-1 line-height"><%= userEmail%></h6>
                                      
                                    </div>
                                </a>
                                <div class="iq-sub-dropdown iq-user-dropdown">
                                    <div class="iq-card shadow-none m-0">
                                        <div class="iq-card-body p-0 ">
                                            <div class="bg-primary p-3">
                                                <h5 class="mb-0 text-white line-height">Hello Barry Tech</h5>
                                                <span class="text-white font-size-12">Available</span>
                                            </div>
                                            <a href="profile.jsp" class="iq-sub-card iq-bg-primary-hover">
                                                <div class="media align-items-center">
                                                    <div class="rounded iq-card-icon iq-bg-primary">
                                                        <i class="ri-file-user-line"></i>
                                                    </div>
                                                    <div class="media-body ml-3">
                                                        <h6 class="mb-0 ">My Profile</h6>
                                                        <p class="mb-0 font-size-12">View personal profile details.</p>
                                                    </div>
                                                </div>
                                            </a>
                                            <a href="profile.jsp" class="iq-sub-card iq-bg-primary-hover">
                                                <div class="media align-items-center">
                                                    <div class="rounded iq-card-icon iq-bg-primary">
                                                        <i class="ri-profile-line"></i>
                                                    </div>
                                                    <div class="media-body ml-3">
                                                        <h6 class="mb-0 ">Edit Profile</h6>
                                                        <p class="mb-0 font-size-12">Modify your personal details.</p>
                                                    </div>
                                                </div>
                                            </a>
                                          
                                           
                                            <div class="d-inline-block w-100 text-center p-3">
                                                <a id="signOutBtn" class="bg-primary iq-sign-btn" href="#" role="button" onclick="signout()">Sign out<i class="ri-login-box-line ml-2"></i></a>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <!-- TOP Nav Bar END -->
    </body>
</html>
