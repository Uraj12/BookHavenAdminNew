<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form by uraj sahu</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/registration.css">
</head>
<body>
    <input type="hidden" id="status" value="<%= request.getAttribute("status") %>">

    <div class="main">

        <!-- Sign up form -->
        <section class="signup invalid-field">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">Sign up</h2>

                        <form method="post" class="register-form" id="register-form" >
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="name" id="name" placeholder="Your name" />
                            </div>

                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="email" name="email" id="email" placeholder="Your email" />
                            </div>

                            <div class="form-group">
                                <label for="contact"><i class="zmdi zmdi-phone"></i></label>
                                <input type="text" name="contact" id="contact" placeholder="Contact no" />
                            </div>

                           

                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="pass" id="pass" placeholder="Password" />
                            </div>

                            <div class="form-group">
                                <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="re_pass" id="re_pass" placeholder="Repeat your password" />
                            </div>

                            <div class="form-group">
                                <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                <label for="agree-term" class="label-agree-term">
                                    <span><span></span></span>I agree all statements in <a href="#" class="term-service">Terms of service</a>
                                </label>
                            </div>

                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="Register" onclick="return Registration();" />
                            </div>
                        </form>
                    </div>
                    <div class="signup-image">
                        <figure>
                            <img src="images/signup-image.jpg" alt="sign up image">
                        </figure>
                        <a href="login.jsp" class="signup-image-link">I am already a member</a>
                    </div>
                </div>
            </div>
        </section>
    </div>
                        <div id="ajax"></div>
    <!-- JS -->
    <script src="js/registration.js"></script>
    <!-- Add SweetAlert library -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

</body>
<!-- This template was made by Colorlib (https://colorlib.com) -->
</html>
