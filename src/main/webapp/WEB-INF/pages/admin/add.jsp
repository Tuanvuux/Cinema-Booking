<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Home Page</title>
</head>

<body>
    <form:form class="mb-3" modelAttribute="account" action="${pageContext.request.contextPath}/admin/add" method="post">
        <table>
            <tr>
                <td>
                    <form:label path="email">Email:</form:label>
                    <form:input class="form-control" path="email" required="true" placeholder="Email" />
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="password">Password:</form:label>
                    <form:input class="form-control" path="password" required="true" placeholder="Password" />
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input class="btn btn-primary" type="submit" value="Add User">
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
