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
    <form:form class="mb-3" action="${pageContext.request.contextPath}/admin/add" method="post">
        <table>
            <tr>
                <td>
                    <label path="email">Email:</label>
                    <input class="form-control" id="email" name="email" required="true" placeholder="Email" />
                </td>
            </tr>
            <tr>
                <td>
                    <label path="password">Password:</label>
                    <input class="form-control" id="password" name="password" required="true" placeholder="Password" type="password"/>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input class="btn btn-primary" type="submit" value="Add User">
                </td>
                ${error}
            </tr>
        </table>
    </form:form>
</body>
</html>
