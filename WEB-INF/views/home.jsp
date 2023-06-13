<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="eStoreProduct.model.custCredModel" %>
<!DOCTYPE html>
<html>
<head>
  <title>SLAM Store</title>
<!-- <script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script> 
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  
  
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
 
  <style>

.dropdown-content1 {

  right: 0;
  left: auto;
  max-width: 400px;
}
    

@media (max-width: 600px) {
  .dropdown-content1 {
    right: auto;
    left: 0;
    max-width: 100%;
  }
}


    
.profile-options {
  list-style: none;
  padding: 0;
  margin: 20px 0;
}

.profile-options li {
  margin-bottom: 10px;
}

.profile-options a {
  display: block;
  padding: 10px 20px;
  background-color: #f2f2f2;
  color: #333;
  text-decoration: none;
  transition: background-color 0.3s ease;
}

.profile-options a:hover {
  background-color: #ddd;
}

.modal-body {
  height: 300px; /* Increase or adjust the height as needed */
  overflow-y: auto;
}

.modal-content {
  width: 500px; /* Increase or adjust the width as needed */
}
    
    
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
    }
    
    header {
      background-color: #333;
      color: #fff;
      padding: 20px;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    
    nav ul {
      list-style-type: none;
      margin: 0;
      padding: 0;
    }
    
    nav ul li {
      display: inline;
    }
    
    nav ul li a {
      color: #fff;
      text-decoration: none;
      padding: 10px 20px;
    }
    
    nav ul li a:hover {
      background-color: #555;
    }
    
    .search-bar {
      text-align: center;
      padding: 20px;
      background-color: #f2f2f2;
    }
    
    .search-bar input[type="text"] {
      width: 300px;
      padding: 6px;
      border: none;
    }
    
    .search-bar input[type="submit"] {
      background-color: #333;
      color: #fff;
      border: none;
      padding: 6px 10px;
      cursor: pointer;
    }
    
    .slideshow-container {
      position: relative;
      width: 100%;
      max-width: 800px;
      margin: 0 auto;
    }
    
    .slideshow-container img {
      width: 100%;
      height: auto;
    }
    
    .slideshow-container .slide {
      display: none;
    }
    
    .slideshow-container .slide.active {
      display: block;
    }

    /* Dropdown styles */
    .profile-dropdown {
      position: relative;
      display: inline-block;
    }

    .dropdown-content {
      position: absolute;
      top: 100%;
      left: -60px; /* Adjust this value as per your preference */
      display: none;
      overflow: hidden;
      background-color: #333;
      min-width: 120px;
      box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
      padding: 12px 16px;
      z-index: 1;
      opacity: 0;
      transition: opacity 0.3s ease;
    }

    .profile-dropdown:hover .dropdown-content {
      display: block;
      opacity: 1;
      transition: opacity 0.3s ease;
    }
	
	.dropdown-content a {
    display: block;
    margin-bottom: 5px;
    white-space: nowrap; /* Ensures the content stays in a single line */
    }
    nav .dropdown {
      position: relative;
    }

    nav .dropdown-content {
      display: none;
      position: absolute;
      background-color: #f9f9f9;
      min-width: 160px;
      box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
      z-index: 1;
    }
    
</style>
  <script>
  
  function loadCategories() {
	    // Function to handle AJAX call and populate the select options
	    $.ajax({
	      url: "CategoriesServlet",
	      method: 'GET',
	      success: function(response) {
	        $('#catg').html(response); // Set the response HTML as the inner HTML of the select element
	      },
	      error: function(xhr, status, error) {
	        console.log('AJAX Error: ' + error);
	      }
	    });
	  }

  function loadProductsByCategory() {
	  var category = document.getElementById("catg").value;
	  console.log(category);
	  fetch("products?category=" + category)
	    .then(function(response) {
	      return response.text();
	    })
	    .then(function(data) {
	      $('#transitionSlideShowPage').html(data); // Set the response HTML as the inner HTML of the select element
	    })
	    .catch(function(error) {
	      console.log('AJAX Error: ' + error);
	    });
	}

  function addToCart(productId) {
	  console.log("Add to cart called");
	  fetch("addToCart?productId=" + productId)
	    .then(function(response) {
	      return response.text();
	    })
	    .then(function(data) {
	      $('#resp').html(data); // Set the response HTML as the inner HTML of the cart items element
	    })
	    .catch(function(error) {
	      console.log('AJAX Error: ' + error);
	    });
	}

  function addToWishlist(productId) {
	  console.log(productId + " Add to wishlist called");
	  fetch("addToWishlist?productId=" + productId, {
	    method: 'GET'
	  })
	    .then(function(response) {
	      return response.text();
	    })
	    .then(function(data) {
	      document.querySelector('#resp').innerHTML = data; // Set the response HTML as the inner HTML of the cart items element
	    })
	    .catch(function(error) {
	      console.log('Fetch Error: ' + error);
	    });
	}

	function removeFromCart(productId) {
	  console.log("Remove from cart called");
	  fetch("removeFromCart?productId=" + productId, {
	    method: 'GET'
	  })
	    .then(function(response) {
	      showCart(); // Set the response HTML as the inner HTML of the cart items element
	    })
	    .catch(function(error) {
	      console.log('Fetch Error: ' + error);
	    });
	}

	function removeFromWishlist(productId) {
	  console.log("Remove from wishlist called");
	  fetch("removeFromWishlist?productId=" + productId, {
	    method: 'GET'
	  })
	    .then(function(response) {
	      showWishlist(); // Set the response HTML as the inner HTML of the cart items element
	    })
	    .catch(function(error) {
	      console.log('Fetch Error: ' + error);
	    });
	}

	function showCart() {
	  fetch("cartItems?userId=1", {
	    method: 'GET'
	  })
	    .then(function(response) {
	      return response.text();
	    })
	    .then(function(data) {
	      document.querySelector('#transitionSlideShowPage').innerHTML = data; // Set the response HTML as the inner HTML of the cart items element
	    })
	    .catch(function(error) {
	      console.log('Fetch Error: ' + error);
	    });
	}

	function showWishlist() {
	  fetch("wishlistItems?userId=1", {
	    method: 'GET'
	  })
	    .then(function(response) {
	      return response.text();
	    })
	    .then(function(data) {
	      document.querySelector('#transitionSlideShowPage').innerHTML = data; // Set the response HTML as the inner HTML of the cart items element
	    })
	    .catch(function(error) {
	      console.log('Fetch Error: ' + error);
	    });
	}

	  
	  
	  loadCategories();
	 
	  // Add event listener for the "Add to Cart" button
	  $(document).ready(function() {
		  
		  $(document).on('click', '.addToCartButton', function(event) {
			  
		    event.preventDefault();
		    var productId = $(this).data('product-id');
		    console.log(productId);
		    addToCart(productId);
		  });
		  $(document).on('click', '.addToCart', function(event) {
			  
			    event.preventDefault();
			    var productId = $(this).data('product-id');
			    console.log(productId+"add to cart from wishlist");
			    addToCart(productId);
			  });
		 /*  $(document).on('click', '.addToWishListFromCart', function(event) {
			  
			    event.preventDefault();
			   
			    var productId = $(this).data('product-id');
			    console.log(productId+"addToWishListFromCart");
			    addToWishlist(productId);
			  }); */
		  $(document).on('click', '.addToWishListFromCart', function(event) {
			    event.preventDefault();
			    var productId = $(this).data('product-id');
			   console.log(productId+"in addToWishListFromCart");
			    addToWishlist(productId);		  });
		  $(document).on('click', '.removeFromCart', function(event) {
		    event.preventDefault();
		    var productId = $(this).data('product-id');
		    console.log(productId);
		    removeFromCart(productId);
		  });
		  
		  $(document).on('click', '.addToWishlistButton', function(event) {
		    event.preventDefault();
		    var productId = $(this).data('product-id');
		    console.log(productId);
		    addToWishlist(productId);
		  });
		  
		  $(document).on('click', '.removeFromWishlist', function(event) {
		    event.preventDefault();
		    var productId = $(this).data('product-id');
		    console.log(productId);
		    removeFromWishlist(productId);
		  });
		  
		  $('#catg').change(function() {
		      loadProductsByCategory();
		  });

		  $('#cart-button').click(function() {
		      showCart();
		  });
		  $('#Wishlist-button').click(function() {
		      showWishlist();
		  });
	  });

      var slides = $('.slide');
      var currentSlide = 0;

      // Function to show the current slide
      function showSlide() {
        // Hide all slides
        slides.hide();

        // Show the current slide
        slides.eq(currentSlide).show();
      }

      // Function to move to the next slide
      function nextSlide() {
        currentSlide++;
        if (currentSlide >= slides.length) {
          currentSlide = 0;
        }
        showSlide();
      }

      // Start the slideshow
      setInterval(nextSlide, 3000); // Change slide every 3 seconds
  </script>


<!-- <link rel="stylesheet" type="text/css" href="homeStyle.css"> -->


</head>

<body>
  <header>
    <h1>SLAM Store</h1>
    <nav>
      <ul>
        <li><a href="#">Home</a></li>
        <li class="dropdown">
           <form  method="get">
    <select name="catg" id="catg">
      <!-- The options will be populated here -->
    </select>  
  </form>
        </li>
        <li><a href="#">About Us</a></li>
        <li><a href="#">Contact</a></li>
        <li><a href="#">&#128722;<button type="button" id="cart-button" class="btn btn-primary">Cart</button></a></li>
        <li><a href="#">&#10084; <button type="button" id="Wishlist-button" class="btn btn-primary">Wishlist</button></a></li>
        <li class="profile-dropdown">
          <% if (!(Boolean) request.getAttribute("fl")) { %>
            <i class="fas fa-user-shield"></i>              
            <div class="dropdown-content">
              <a href="signUp">Sign Up</a>
              <a href="signIn">Sign In</a>
            </div>
          <% } else { %>
            <i class="fas fa-user-shield"></i>
            <div class="dropdown-content">
              <a href="profilePage">My Profile</a>
            </div>
          <% } %>
        </li>
      </ul>
    </nav>
  </header>

  <div class="search-bar">
    <form>
      <input type="text" placeholder="Search...">
      <input type="submit" value="Search">
    </form>
  </div>

  <main>
  <div id="prod"></div>
  <div id="transitionSlideShowPage">
    <div class="slideshow-container">
      <div class="slide active">
<img  src="https://static.digit.in/default/848e74e131ed5b8172357de25c0afb9bf691029c.jpeg?tr=w-1200" />
      </div>
      <div class="slide">
        <img src="https://mobirise.com/extensions/commercem4/assets/images/3.jpg" alt="Slide 2">
      </div>
      <div class="slide">
        <img src="https://t4.ftcdn.net/jpg/03/38/88/59/240_F_338885943_qQRG84nyD1CcTdXuVD4UbzMz1xBGWjBC.jpg" alt="Slide 3">
      </div>
   <div class="slide">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRBQy58C3pHvO6tN7zHCeTqq4L2t2-Kc5ENA&usqp=CAU" alt="Slide 5">
      </div>
    </div>
    </div>
    <div id="display"></div>
    
    
    
  
    
    
   
    
    
  
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    
    
  </main>
  <footer>
    <p>&copy; 2023 SLAM Store. All rights reserved.</p>
  </footer>
<script>
  $(document).ready(function() {
    var slides = $('.slide');
    var currentSlide = 0;
    
    // Function to show the current slide
    function showSlide() {
      // Hide all slides
      slides.hide();
      
      // Show the current slide
      slides.eq(currentSlide).show();
    }
    
    // Function to move to the next slide
    function nextSlide() {
      currentSlide++;
      if (currentSlide >= slides.length) {
        currentSlide = 0;
      }
      showSlide();
    }
    
    // Start the slideshow
    setInterval(nextSlide, 3000); // Change slide every 3 seconds
  });
</script>


</body>
</html>