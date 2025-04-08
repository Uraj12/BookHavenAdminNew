<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In</title>
    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
    <!-- Main css -->
    <link rel="stylesheet" href="css/login.css">
   
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js" integrity="sha512-AA1Bzp5Q0K1KanKKmvN/4d3IRKVlv9PYgwFPvm32nPO6QS8yH1HO7LbgB1pgiOxPtfeg5zEn2ba64MUcqJx6CA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
   
</head>
<body>
    <div class="main">
        <!-- Sign-in Form -->
        <section class="sign-in">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-image">
                        <figure>
                            <img src="images/man-sat-reading.png" alt="sign up image">
                        </figure>
                        <a href="registration.jsp" class="signup-image-link">Create an account</a>
                    </div>
                    <div class="signin-form">
                        <h2 class="form-title">Sign In</h2>
                      <form method="post" class="register-form" id="login-form" onsubmit="return login(event)">

                            <div class="form-group tooltip">
                                <label for="email"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="email" id="email" placeholder="Your email" />
                                <span class="tooltiptext">email is required and cannot be empty!</span>
                            </div>
                            <div class="form-group tooltip">
                                <label for="password"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="password" id="password" placeholder="Password" />
                                <span class="tooltiptext">Password is required and cannot be empty!</span>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                                 <a href="forgotPassword.jsp" class="signup-image-link">Forgot your password?</a>
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signin" id="signin" class="form-submit" value="Log in" />
                            </div>
                        </form>
                       
                    </div>
                </div>
            </div>
        </section>
    </div>

    <!-- JS -->
     <%@include file="Js.jsp" %>
    
    <input id="ajax" type="hidden">
</body>
</html>
