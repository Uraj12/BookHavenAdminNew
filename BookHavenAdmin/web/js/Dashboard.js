/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/* global response */

function ajaxcall(method, url, data, desc, isHtml, isFile) {
    var xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        if (isHtml) {
            document.getElementById(desc).innerHTML = this.responseText;
        } else {
            document.getElementById(desc).value = this.responseText;
        }
    };
    xhttp.open(method, url, false);
    if (!isFile) {
        xhttp.setRequestHeader('content-type', 'application/x-www-form-urlencoded');
    }
    xhttp.send(data);
}

function adminTabLoader(process) {
    ajaxcall('POST', 'AdminDashboardServlet.fin', 'process=' + process, 'content-page', 'isHtml');
}
function editBook(a) {
    var bookId = a.getAttribute('bookId');
    alert('Do you want to edit book with id:-' + bookId);
}
function deleteBook(a) {
    var bookId = a.getAttribute('bookId');
    var confirmation = confirm('Are you sure you want to delete book with id:-' + bookId);
//    alert('Are you sure you want to delete book with id:-' + bookId);
    if (confirmation) {
        ajaxcall('POST', 'AdminDashboardServlet.fin', 'bookId=' + bookId + '&process=deleteBook', 'statusajax', "isHtml");
        var status = document.getElementById('status').value;
        alert(status);
        if (status > 0) {
            alert('Deleted successfully');
        } else {
            alert('problem occured');
        }
        location.reload();
    }
    return false;
}

function editUser(a) {
    var userId = a.getAttribute('userId');
    alert('Do you want to edit user with id:-' + userId);
}
function deleteUser(a) {
    var userId = a.getAttribute('userId');
    alert('Are you sure you want to delete user with id:-' + userId);
}
function validation(element, caption) {

    if (element === '') {
        alert(caption + ' cannot be empty');
        return false;
    }
    return true;
}

function addBook() {
    var bookName = document.getElementById('bookName').value;
    var bookCategory = parseInt(document.getElementById('bookCategory').value);
    var bookAuthor = parseInt(document.getElementById('bookAuthor').value);
    var bookPrice = document.getElementById('bookPrice').value;
    var Quantity = document.getElementById('Quantity').value;
    var bookDescription = document.getElementById('bookDescription').value;
    var bookImageUrl = document.getElementById('bookImageUrl').value;
    var publishYear = document.getElementById('publishYear').value;
    alert(publishYear);// Get the Publish Year value

    // Validate non-empty inputs
    var isNotEmpty = validation(bookName, 'book name') && validation(bookCategory, 'Book Category') && validation(bookAuthor, 'Book Author') && validation(bookDescription, 'Book Description') && validation(Quantity, 'Quantity');

    // Validate non-negative input for book price
    var isPriceValid = bookPrice >= 0;

    // Display error message if book price is negative
    if (!isPriceValid) {
        alert('Please enter a non-negative value for the book price.');
        return; // Stop further processing
    }

    var formData = 'bookName=' + bookName + '&bookCategory=' + bookCategory + '&bookAuthor=' + bookAuthor + '&bookPrice=' + bookPrice + '&bookDescription=' + bookDescription + '&bookImageUrl=' + bookImageUrl + '&Quantity=' + Quantity + '&publishYear=' + publishYear + '&process=insertBook'; // Include Publish Year in formData

    if (isNotEmpty) {
        alert('ajax call');
        ajaxcall('POST', 'AdminDashboardServlet.fin', formData, 'statusajax', "isHtml");
        var status = document.getElementById('status').value;
        alert(status);
        if (status > 0) {
            alert('inserted successfully');
        } else if (status === -1) {
            alert('Book with the same name already exists');
        } else {
            alert('problem occurred');
        }
        location.reload();
    }
}





function deleteAuthor(a) {
    var author_id = a.getAttribute('author_id');
    var confirmation = confirm('Are you sure you want to delete author with id:-' + author_id);
//    alert('Are you sure you want to delete book with id:-' + bookId);
    if (confirmation) {
        alert("ajaxall");
        ajaxcall('POST', 'AdminDashboardServlet.fin', 'author_id=' + author_id + '&process=deleteauthor', 'author', "isHtml");
        var status = document.getElementById('status').value;
        alert(status);
        if (status > 0) {
            alert('Deleted successfully');
        } else if (status == -1) {
            alert("you not able to delete author , book with this author already exist ")
        } else {
            alert('problem occured');
        }
        location.reload();
    }
    return false;
}


function deletecategory(a) {
    var category_id = a.getAttribute('category_id');
    var confirmation = confirm('Are you sure you want to delete author with id:-' + category_id);
//    alert('Are you sure you want to delete book with id:-' + bookId);
    if (confirmation) {
        alert("ajaxall");
        ajaxcall('POST', 'AdminDashboardServlet.fin', 'category_id=' + category_id + '&process=deletecategory', 'category', "isHtml");
        var status = document.getElementById('status').value;
        alert(status);
        if (status > 0) {
            alert('Deleted successfully');
        } else if (status == -1) {
            alert("you not able to delete category , book with this category already exist")
        } else {
            alert('problem occured');
        }
        location.reload();
    }
    return false;
}

function addAuthor() {
    var authorName = document.getElementById('authorName').value;
    var authorDescription = document.getElementById('authorDescription').value;
    var AuthorImageUrl = document.getElementById('AuthorImageUrl').value;
    var isNotEmpty = validation(authorName, 'authorName') && validation(authorDescription, 'authorDescription');
    var formData = 'authorName=' + authorName + '&authorDescription=' + authorDescription + '&AuthorImageUrl=' + AuthorImageUrl + '&process=insertAuthor';

    if (isNotEmpty) {
        alert('ajax call');
        ajaxcall('POST', 'AdminDashboardServlet.fin', formData, 'statusajax', "isHtml");
        var status = document.getElementById('status').value;
        alert(status);
        if (status > 0) {
            alert('inserted successfully');
        } else {
            alert('problem occured');
        }
        location.reload();
    }



}
function addAuthorSwal() {
    Swal.fire({
        title: 'Add Author',
        html:
                '<input id="authorName" class="swal2-input" placeholder="Author Name">' +
                '<input id="authorDescription" class="swal2-input" placeholder="Author Description">',
        focusConfirm: false,
        preConfirm: () => {
            const authorName = Swal.getPopup().querySelector('#authorName').value;
            const authorDescription = Swal.getPopup().querySelector('#authorDescription').value;
            const formData = 'authorName=' + authorName + '&authorDescription=' + authorDescription + '&process=insertAuthor';
            const isNotEmpty = validation(authorName, 'authorName') && validation(authorDescription, 'authorDescription');

            if (isNotEmpty) {
                // Make the AJAX call using ajaxcall function
                ajaxcall('POST', 'AdminDashboardServlet.fin', formData, 'statusajax', "isHtml");

                // No need to handle the response here, as it will be handled by the ajaxcall function
                // Reload the page after adding the author

            }
        }
    }).then((result) => {
        if (result.isConfirmed) {
            // The user clicked the "OK" button in the Swal dialog
            var status = document.getElementById('status').value;

            if (status > 0) {
                Swal.fire('Success', 'Author inserted successfully', 'success');


            } else {
                Swal.fire('Error', 'Failed to insert author', 'error');
            }
        }
    });

}

function addCategory() {
    var Categoryname = document.getElementById('Categoryname').value;
    var Categorydescription = document.getElementById('Categorydescription').value;
    var isNotEmpty = validation(Categoryname, 'Categoryname') && validation(Categorydescription, 'Categorydescription');
    var formData = 'Categoryname=' + Categoryname + '&Categorydescription=' + Categorydescription + '&process=insertCategory';

    if (isNotEmpty) {
        alert('ajax call');
        ajaxcall('POST', 'AdminDashboardServlet.fin', formData, 'statusajax', "isHtml");
        var status = document.getElementById('status').value;
        alert(status);
        if (status > 0) {
            alert('inserted successfully');
        } else {
            alert('problem occured');
        }
        location.reload();
    }
}
function addCategorySawl() {
    Swal.fire({
        title: 'Add Category',
        html:
                '<input id="Categoryname" class="swal2-input" placeholder="Category Name">' +
                '<input id="Categorydescription" class="swal2-input" placeholder="Category Description">',
        focusConfirm: false,
        preConfirm: () => {
            const Categoryname = Swal.getPopup().querySelector('#Categoryname').value;
            const Categorydescription = Swal.getPopup().querySelector('#Categorydescription').value;
            const formData = 'Categoryname=' + Categoryname + '&Categorydescription=' + Categorydescription + '&process=insertCategory';
            const isNotEmpty = validation(Categoryname, 'Categoryname') && validation(Categorydescription, 'Categorydescription');

            if (isNotEmpty) {
                return fetch('AdminDashboardServlet.fin', {
                    method: 'POST',
                    body: formData,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                            return response.text();
                        })
                        .then(result => {
                            // Parse the result to an integer
                            var status = document.getElementById('status').value;

                            if (status > 0) {
                                Swal.fire('Success', 'Category inserted successfully', 'success');


                            } else {
                                Swal.fire('Error', 'Failed to insert Category', 'error');
                            }
                        })
                        .catch(error => {
                            console.error('There was a problem with your fetch operation:', error);
                            Swal.fire('Error', 'An error occurred while processing your request', 'error');
                        });
            }
        }
    });
}




function addUser() {
    // Get form data
    var name = document.getElementById("fname").value;
    var email = document.getElementById("email").value;
    var address1 = document.getElementById("add1").value;
    var address2 = document.getElementById("add2").value;
    var mobileNumber = document.getElementById("mobno").value;

    // Generate a random password (you can replace this with your password generation logic)
    var password = generatePassword();

    // Prepare data to send
    var formData = 'name=' + encodeURIComponent(name) +
            '&email=' + encodeURIComponent(email) +
            '&address1=' + encodeURIComponent(address1) +
            '&address2=' + encodeURIComponent(address2) +
            '&mobileNumber=' + encodeURIComponent(mobileNumber) +
            '&password=' + encodeURIComponent(password) +
            '&process=addUserByAdmin';

    // Send AJAX request
    ajaxcall('POST', 'AdminDashboardServlet.fin', formData, 'statusajax', 'isHtml');
    var status = document.getElementById('status').value;
    alert(status);
    if (status > 0) {
        alert('inserted successfully');
    } else {
        alert('problem occured');
    }
    location.reload();
}


function generatePassword() {
    // Implement your password generation logic here
    // This is just a sample implementation
    var chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    var password = "";
    for (var i = 0; i < 8; i++) {
        password += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return password;
}

//Author jsp
function searchAuthors() {
    var searchQuery = document.getElementById("authorSearch").value;
    window.location.href = "BookAuthor.jsp?authorSearch=" + searchQuery;
}

function toggleEditAuthor(element) {
    var row = element.closest('tr').querySelectorAll('td:not(:last-child)');
    var editBtn = element.querySelector('.ri-pencil-line');
    var saveBtn = element.querySelector('.ri-save-line');

    if (editBtn) {
        row.forEach(function (cell) {
            cell.contentEditable = true;
        });
        editBtn.classList.remove('ri-pencil-line');
        editBtn.classList.add('ri-save-line');
    } else if (saveBtn) {
        saveAuthorData(element);
        row.forEach(function (cell) {
            cell.contentEditable = false;
        });
        saveBtn.classList.remove('ri-save-line');
        saveBtn.classList.add('ri-pencil-line');
    }
}




function saveAuthorData(element) {
    var row = element.closest('tr').querySelectorAll('td:not(:last-child)');
    var authorId = row[0].innerText;
    var authorName = row[2].innerText;
    var authorDescription = row[3].innerText;
    var formData = 'authorId=' + authorId + '&authorName=' + authorName + '&authorDescription=' + authorDescription + '&process=updateAuthor';

    // Send AJAX request to servlet
    ajaxcall('POST', 'AdminDashboardServlet.fin', formData, 'statusajax', true);

    // Check the status when the AJAX call is complete
    ajaxcall.onreadystatechange = function () {
        if (ajaxcall.readyState == 4 && ajaxcall.status == 200) {
            var status = document.getElementById('status').value;
            if (status === 1) {
                alert('Update successful');
            } else {
                alert('Problem occurred');
            }
        }
    };
}

//category edit
function toggleEdit(element) {
    var tableRow = element.closest("tr");
    var cells = tableRow.getElementsByTagName("td");
    for (var i = 1; i < cells.length - 1; i++) { // Start from index 1 to skip the ID column and exclude the last column (Action)
        cells[i].contentEditable = "true";
    }
    var editButton = tableRow.querySelector(".edit-btn");
    editButton.innerHTML = '<i class="ri-save-line"></i>'; // Change button icon to Save
    editButton.setAttribute("onclick", "saveCategory(this)");
}


function saveCategory(element) {
    var tableRow = element.closest("tr");
    var cells = tableRow.getElementsByTagName("td");
    var categoryId = cells[0].innerText;
    var categoryName = cells[1].innerText;
    var categoryDescription = cells[2].innerText;

    var formData = 'categoryId=' + categoryId +
            '&categoryName=' + categoryName +
            '&categoryDescription=' + categoryDescription +
            '&process=updateCategory';

    var ajaxcall = new XMLHttpRequest();
    ajaxcall.open('POST', 'AdminDashboardServlet.fin', true);
    ajaxcall.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    ajaxcall.onreadystatechange = function () {
        if (ajaxcall.readyState == 4 && ajaxcall.status == 200) {
            var response = ajaxcall.responseText.trim();
            if (response === "success") {
                alert("Category updated successfully");
            } else {
                alert("Failed to update category");
            }
            // Restore original state after saving
            for (var i = 1; i < cells.length - 1; i++) {
                cells[i].contentEditable = "false";
            }
            element.innerHTML = '<i class="ri-pencil-line"></i>'; // Change button icon back to Edit
            element.setAttribute("onclick", "toggleEdit(this)");
        }
    };
    ajaxcall.send(formData);
}


//edit book
function editBook(element) {
    var row = element.closest('tr').querySelectorAll('td:not(:last-child)');
    var editBtn = element.querySelector('i');

    if (editBtn.classList.contains('ri-pencil-line')) {
        row.forEach(function (cell) {
            var select = cell.querySelector('select');
            if (select) {
                select.removeAttribute('disabled'); // Enable the dropdown
                select.classList.remove('form-control-plaintext');
                select.classList.add('form-control');
            } else {
                cell.contentEditable = true;
            }
        });
        editBtn.classList.remove('ri-pencil-line');
        editBtn.classList.add('ri-save-line');
        editBtn.parentElement.setAttribute('onclick', 'saveBookData(this)');
    }
}


function saveBookData(element) {
    var row = element.closest('tr').querySelectorAll('td:not(:last-child)');
    var bookId = row[0].innerText;
    var bookName = row[2].innerText;
    var categoryId = row[3].querySelector('select').value;
    var authorId = row[4].querySelector('select').value;
    var description = row[5].innerText;
    var quantity = row[6].innerText;
    var bookPrice = row[7].innerText;
    var Discount = row[8].innerText;

    var formData = 'bookId=' + bookId + '&bookName=' + bookName + '&categoryId=' + categoryId + '&authorId=' + authorId + '&description=' + description + '&quantity=' + quantity + '&bookPrice=' + bookPrice + '&Discount=' + Discount + '&process=updateBook';

    // Send AJAX request to servlet
    ajaxcall('POST', 'AdminDashboardServlet.fin', formData, 'statusajax', "isHtml");

    var status = document.getElementById('status').value;
    if (status > 0) {
        alert('Update successful');
    } else {
        alert('Problem occurred');
    }


    // Toggle back to edit mode
    var editBtn = element.querySelector('i');
    var row = element.closest('tr').querySelectorAll('td:not(:last-child)');
    row.forEach(function (cell) {
        var select = cell.querySelector('select');
        if (select) {
            select.setAttribute('disabled', 'disabled');
            select.classList.remove('form-control');
            select.classList.add('form-control-plaintext');
        } else {
            cell.contentEditable = false;
        }
    });
    editBtn.classList.remove('ri-save-line');
    editBtn.classList.add('ri-pencil-line');
    editBtn.parentElement.setAttribute('onclick', 'editBook(this)');
}



function changeImage(button, bookId) {
    // Show SweetAlert popup for input
    Swal.fire({
        title: 'Enter Image URL',
        input: 'text',
        inputAttributes: {
            autocapitalize: 'off'
        },
        showCancelButton: true,
        confirmButtonText: 'Submit',
        showLoaderOnConfirm: true,
        preConfirm: (imageUrl) => {
            // Display image preview
            var imgElement = document.createElement('img');
            imgElement.src = imageUrl;
            Swal.fire({
                title: 'Image Preview',
                html: imgElement.outerHTML,
                showCancelButton: true,
                confirmButtonText: 'Confirm',
            }).then((result) => {
                if (result.isConfirmed) {
                    // Send AJAX request to update the image URL in the database
                    var formData = 'bookId=' + bookId + '&imageUrl=' + imageUrl + '&process=updateImage';
                    ajaxcall('POST', 'AdminDashboardServlet.fin', formData, 'statusajax', "isHtml");

                    var status = document.getElementById('status').value;
                    if (status > 0) {
                        alert('Update successful');
                    } else {
                        alert('Problem occurred');
                    }
                }
            });
        }
    });
}


function updateImagePreview() {
    var imageUrl = document.getElementById("bookImageUrl").value;

    if (imageUrl) {
        // If URL is provided, create the image element with the preview
        var imgElement = document.createElement('img');
        imgElement.src = imageUrl;

        // Show the image preview inside SweetAlert
        Swal.fire({
            title: 'Image Preview',
            html: imgElement.outerHTML,
            showCancelButton: true,
            confirmButtonText: 'OK'
        }).then((result) => {
            // Handle the result (OK button clicked)
            if (result.isConfirmed) {
                // Close the SweetAlert popup
                Swal.close();
            }
        });
    } else {
        // If no URL is provided, display an alert
        Swal.fire({
            title: 'No Image URL Provided',
            icon: 'error',
            text: 'Please enter the URL of the image to preview.'
        });
    }
}

function featured(a) {
    var bookId = a.getAttribute('bookId');
//    var featured = a.getAttribute('featured');
//    alert(bookId);
    var row = a.parentNode;

    var element = row.querySelector('#featuredIcon');
    var className = element.classList;
//    alert(className);
    if (className == 'ri-book-mark-line') { //for making books featured
        var confirmation = confirm('Are you sure you want to add this book in featured books');
        if (confirmation) {
            ajaxcall('POST', 'AdminDashboardServlet.fin', 'bookId=' + bookId + '&process=isfeatured', 'statusajax', "isHtml");
            var status = document.getElementById('status').value;
//        alert(status);
            if (status > 0) {
                alert('updated successfully');
                element.classList.replace('ri-book-mark-line', 'ri-book-mark-fill');
            } else {
                alert('problem occured');
            }
        }
    } else if (className == 'ri-book-mark-fill') { //for making books unfeatured
        var confirmation = confirm('Are you sure you want to remove this book from featured books');
        if (confirmation) {

            ajaxcall('POST', 'AdminDashboardServlet.fin', 'bookId=' + bookId + '&process=notfeatured', 'statusajax', "isHtml");
            var status = document.getElementById('status').value;
//        alert(status);
            if (status > 0) {
                alert('updated successfully');
                element.classList.replace('ri-book-mark-fill', 'ri-book-mark-line');
            } else {
                alert('problem occured');
            }
        }
    }
}
function previewImage(imageUrl) {
    swal({
        title: "",
        text: "",
        content: {
            element: "img",
            attributes: {
                src: imageUrl,
                className: "img-fluid sweet-image-preview",
                style: "max-width: 90%; height: auto; max-height: 80vh;"
            },
        },
        buttons: false,
    });
}
function deleteOrder(a) {
    var order_id = a.getAttribute('order_id');
    var confirmation = confirm('Are you sure you want to delete order with id:-' + order_id);
//    alert('Are you sure you want to delete book with id:-' + bookId);
    if (confirmation) {
        alert("ajaxall");
        ajaxcall('POST', 'AdminDashboardServlet.fin', 'order_id=' + order_id + '&process=deleteorder', 'statusajax', "isHtml");
        var status = document.getElementById('status').value;
        alert(status);
        if (status > 0) {
            alert('Deleted successfully');
        } else {
            alert('problem occured');
        }
        location.reload();
    }
}
function AcceptOrder(a) {
    var order_id = a.getAttribute('order_id');
    var confirmation = confirm('Are you sure you want to Accept Order with id:-' + order_id);
//    alert('Are you sure you want to delete book with id:-' + bookId);
    if (confirmation) {
        alert("ajaxall");
        ajaxcall('POST', 'AdminDashboardServlet.fin', 'order_id=' + order_id + '&process=AcceptOrder', 'order', "isHtml");
        var status = document.getElementById('status').value;
        alert(status);
        if (status > 0) {
            alert(' successfully');
        } else {
            alert('problem occured');
        }
        location.reload();
    }
}



function RecjetBookRequest(a) {
    var request_id = a.getAttribute('request_id');
    var user_id = a.getAttribute('user_id');
    var confirmation = confirm('Are you sure you want to delete Request with id: ' + request_id + ' and user id: ' + user_id);

    if (confirmation) {
        ajaxcall('POST', 'AdminDashboardServlet.fin', 'request_id=' + request_id + '&user_id=' + user_id + '&process=RecjetBookRequest', 'statusajax', "isHtml");
        var status = document.getElementById('status').value;

        if (status > 0) {
            alert('Deleted successfully');
        } else {
            alert('Problem occurred');
        }

    }
    location.reload();
}

function AcceptBookRequest(a) {
    var request_id = a.getAttribute('request_id');
    var user_id = a.getAttribute('user_id');
    var user_name = a.getAttribute('user_name');
    var user_email = a.getAttribute('user_email');
    var book_id = a.getAttribute('book_id');
    var request_date = a.getAttribute('request_date');

    var confirmation = confirm('Are you sure you want to accept the request with ID: ' + request_id + ' for user ID: ' + user_id + '?');

    if (confirmation) {
        ajaxcall('POST', 'AdminDashboardServlet.fin', 'request_id=' + request_id + '&user_id=' + user_id + '&user_name=' + user_name + '&user_email=' + user_email + '&book_id=' + book_id + '&request_date=' + request_date + '&process=AcceptBookRequest', 'statusajax', true);
        var status = document.getElementById('status').value;

        if (status > 0) {
            alert('Deleted successfully');
        } else {
            alert('Problem occurred');
        }
    }
    location.reload();
}

function AcceptBookRenewalRequest(a) {
    var renewal_id = a.getAttribute('renewal_id');
    var user_id = a.getAttribute('user_id');
    var book_id = a.getAttribute('book_id');

    var confirmation = confirm('Are you sure you want to accept the request with ID: ' + renewal_id + ' for user ID: ' + user_id + ' and book ID: ' + book_id + '?');

    if (confirmation) {
        ajaxcall('POST', 'AdminDashboardServlet.fin', 'renewal_id=' + renewal_id + '&user_id=' + user_id + '&book_id=' + book_id + '&process=AcceptBookRenewalRequest', 'Renewal', true);
        var status = document.getElementById('status').value;

        if (status > 0) {
            // Reload the page silently
            location.reload();
        } else {
            alert('Problem occurred while processing the request');
        }
    }
    location.reload();
}



function ReturnIssuedBook(a) {
    var Issued_id = a.getAttribute('Issued_id');
    var request_id = a.getAttribute('request_id');
    var user_id = a.getAttribute('user_id');
    var user_name = a.getAttribute('user_name');
    var user_email = a.getAttribute('user_email');
    var book_id = a.getAttribute('book_id');
    var request_date = a.getAttribute('request_date');
    var return_date = a.getAttribute('return_date');

    var confirmation = confirm('Are you sure you want to return the Issued with ID: ' + Issued_id + ' for user ID: ' + user_id + '?');

    if (confirmation) {
        ajaxcall('POST', 'AdminDashboardServlet.fin', 'Issued_id=' + Issued_id + '&request_id=' + request_id + '&user_id=' + user_id + '&user_name=' + user_name + '&user_email=' + user_email + '&book_id=' + book_id + '&request_date=' + request_date + '&return_date=' + return_date + '&process=ReturnIssuedBook', 'statusajax', true);
    }
    location.reload();
}

function sendEmailForReturn(a) {
    var user_id = a.getAttribute('user_id');
    var return_date = a.getAttribute('return_date');
    var user_name = a.getAttribute('user_name');
    var book_id = a.getAttribute('book_id');
    alert(user_id);
    ajaxcall('POST', 'AdminDashboardServlet.fin', 'user_id=' + user_id + '&return_date=' + return_date + '&user_name=' + user_name + '&book_id' + book_id + '&process=sendEmailForReturnBook', 'statusajax', "isHtml");

}

function login(event) {
    event.preventDefault(); //
    var result = validate_empty('email', 'email') &&
            validate_empty('password', 'Password');

    var email = document.getElementById('email').value;
    alert(email);
    var password = document.getElementById('password').value;
    var requestData = 'email=' + encodeURIComponent(email) +
            '&password=' + encodeURIComponent(password) +
            '&process=login';
    if (result) {
        ajaxcall('POST', 'AdminDashboardServlet.fin', requestData, 'ajax', "isHtml");
        alert("ajaxall");
        // read hidden value
        var status = document.getElementById('status').value.trim();
        alert(status);

        if (status > 0) {
            window.location.href = "index.jsp";
            // window.location.href="index.jsp";
        } else {

            alert("incorrect email or password");
        }
    }

    return false;
}


function validate_empty(elementId, elementName) {
    var inputElement = document.getElementById(elementId);
    var value = inputElement.value.trim();

    // Check if the field is empty
    if (value === '') {
        // Mark the field as empty (if not already marked)
        if (!inputElement.classList.contains("invalid-field")) {
            inputElement.classList.add("invalid-field");
            addErrorEffect(inputElement);
        }

        // Create and append a tooltip if it doesn't exist
        var tooltip = createTooltip(elementId, elementName + " is required and cannot be empty!");
        inputElement.parentNode.appendChild(tooltip);

        return false;
    } else {
        // If the field is not empty, remove any existing tooltip and reset styles
        removeTooltip(elementId);
        inputElement.classList.remove("invalid-field");

        return true;
    }
}
function createTooltip(elementId, message) {
    var tooltip = document.createElement("span");
    tooltip.id = elementId + "-tooltip";
    tooltip.className = "tooltiptext";
    tooltip.textContent = message;

    // Position the tooltip near the input field (customize styles as needed)
    var rect = document.getElementById(elementId).getBoundingClientRect();
    tooltip.style.top = rect.bottom + "px";
    tooltip.style.left = rect.left + "px";

    // Add a class to the tooltip for red color
    tooltip.classList.add("red-text");

    return tooltip;
}

function removeTooltip(elementId) {
    var existingTooltip = document.getElementById(elementId + "-tooltip");
    if (existingTooltip) {
        existingTooltip.parentNode.removeChild(existingTooltip);
    }
}
document.addEventListener('DOMContentLoaded', function () {
    var signOutBtn = document.getElementById('signOutBtn');

    if (signOutBtn) {
        signOutBtn.addEventListener('click', function (event) {
            event.preventDefault(); // Prevent default link behavior

            console.log('Clearing session storage...'); // Log statement for debugging
            sessionStorage.clear(); // Clear session storage

            console.log('Session storage cleared.'); // Log statement for debugging

            // Redirect to the sign-in page
            window.location.href = 'login.jsp';
        });
    }
});

function toggleEditable() {
    var name = document.getElementById('name');
    var email = document.getElementById('email');
    var phno = document.getElementById('phno');
    var editButton = document.getElementById('editButton');

    name.contentEditable = !name.isContentEditable;
    email.contentEditable = !email.isContentEditable;
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
    // Code to save changes goes here
    // You can send updated data to the server using AJAX or other methods
    // For demonstration purposes, I'm alerting a message here
    alert('Changes saved successfully!');
    // You can also reset the button back to "Edit" if needed
    var editButton = document.getElementById('editButton');
    editButton.innerHTML = 'Edit';
    editButton.onclick = toggleEditable;
}




        