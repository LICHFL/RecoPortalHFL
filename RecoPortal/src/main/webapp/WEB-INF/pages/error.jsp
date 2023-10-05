<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .error-container {
            text-align: center;
            margin: 100px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            max-width: 400px; /* Adjust the maximum width as needed */
        }

        h1 {
            color: #e74c3c;
            font-size: 24px; /* Increase the font size for emphasis */
        }

        h2 {
            font-size: 20px; /* Increase the font size for emphasis */
        }

        p {
            font-size: 18px;
            margin: 20px 0;
        }

        a {
            color: #3498db;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Error</h1>
        <h2>Please enter valid login details</h2>
        <p>Oops! Something went wrong.</p>
        <p>We apologize for the inconvenience.</p>
        <p><a href="/login">Back to Home</a></p>
    </div>
</body>
</html>
