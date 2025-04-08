<%-- 
    Document   : Footer
    Created on : 24-Feb-2024, 5:24:11 pm
    Author     : ajay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <footer class="iq-footer">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <ul class="list-inline mb-0">
                            <li class="list-inline-item"><a href="privacy-policy.html">Privacy Policy</a></li>
                            <li class="list-inline-item"><a href="terms-of-service.html">Terms of Use</a></li>
                        </ul>
                    </div>
                    <div class="col-lg-6 text-right">
                        Copyright <span id="copyright"> 
                            <script>document.getElementById('copyright').appendChild(document.createTextNode(new Date().getFullYear()))</script>
                        </span> <a href="admin-dashboard.html#">BookHaven</a> All Rights Reserved.
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
