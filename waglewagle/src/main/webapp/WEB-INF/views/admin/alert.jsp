<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
<c:if test="${cmd eq 'move'}"> /* == 대신 eq 사용도 가능함 알아두면 좋을듯 :) */
	alert('${msg}');  /* 여기는 el임...jquery 아니다..혼동하지 말것 */
	location.href = '${url}';
</c:if>

<c:if test="${cmd == 'back'}">
	alert('${msg}');
	history.back();
</c:if>
</script>