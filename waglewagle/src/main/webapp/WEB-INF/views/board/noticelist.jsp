<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sample</title>
	<link rel="stylesheet" href="/resources/css/common/common.css">
	<link rel="stylesheet" href="/resources/css/board/noticeview.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div id="center">
		<div id="container">
			
			<div style="width: 100%;height: 600px;"> <!-- 공지 표시 영역 -->
			
			<!-- 여기부분 작성함 -->
                 <!-- 공지리스트 -->
                <div class="noticelist">	
                    <table class="nlist">

                        <colgroup>
                            <col width="80px" />
                            <col width="*" />
                            <col width="100px" />
                            <col width="100px" />
                            <col width="100px" />
                        </colgroup>

                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>등록일</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty list }">
                        	<tr>
                        		<td>등록된 공지가 없습니다.</td>
                        	</tr>
                        </c:if>
                        <c:forEach items="${list}" var="NoticesVO">
                        	<tr>
                        		<!-- 번호 -->
                        		<td>${NoticesVO.notices_id }</td>
                        		<!-- 제목 -->
                        		<td><a href="noticelist/${NoticesVO.notices_id}">${NoticesVO.notices_title}</a></td>
                        		<!-- 작성일 -->
                        		<td>${NoticesVO.notices_date}</td>
                        	</tr>
                        </c:forEach>
                        </tbody>
                    </table>
            </div>
          
            
            
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>