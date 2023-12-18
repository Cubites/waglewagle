<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script>
<c:if test="${cmd == 'move'}">
	alert("${msg}");//el <- model 객체로 저장된거 받아옴
	location.href='${url}'; 
</c:if>
<c:if test="${cmd == 'back'}">
	alert("${msg}");//el <- model 객체로 저장된거 받아옴
	history.back(); 
</c:if>
</script>