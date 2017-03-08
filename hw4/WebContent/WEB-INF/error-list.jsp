<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Qianying Chen Date:2016/12/13 andrewId:qianyinc course number:08-672 -->
<c:if test="${!(empty errors)}">
	<p style="font-size:medium; color:red">
		<c:forEach var="error" items="${errors}">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			${error}
			<br/>
		</c:forEach>
	</p>
</c:if>
<!-- need to check whether exception message can be printed -->
