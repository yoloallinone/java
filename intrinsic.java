Intrinsicobj.jsp
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Practical 4a</title>
    </head>
    <body>
        <table>
            <tr>
                <td><b>Request Object<b></td>
            </tr>
            <tr>
                <td>
                    Query String<%=request.getQueryString()%><br>
                    Context Path<%=request.getContextPath()%><br>
                    Remote Hots<%=request.getRemoteHost()%><br>
                </td>
            </tr>
<tr>
                <td><b>Session Object<b></td>
            </tr>
            <tr>
                <td>
                    Id<%=session.getId()%><br>
                    Creation<%=new java.util.Date(session.getCreationTime())%><br>
                    Last Access Time<%=new java.util.Date(session.getLastAccessedTime())%><br>
                </td>
            </tr>
<tr>
                <td><b>Response Object</b></td>
            </tr>
            <tr>
                <td>
                    Character Encoding Type<%=response.getCharacterEncoding()%><br>
                    Content Type<%=request.getContentType()%><br>
                    Locale<%=request.getLocale()%><br>
                    
                </td>
            </tr>
        </table>
</body>
</html>
