<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sample</title>
	<link rel="stylesheet" href="/resources/css/common/common.css">
	<link rel="stylesheet" href="/resources/css/board/noticeview.css">
	<script>
	function notice(){
		 location.href = "/board/noticelist";
	}
	</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div id="center">
		<div id="container">
			
			<div style="width: 100%;height: 600px;">
			
			<!-- 여기부분 작성함 -->
				<div id="main-box">
            	<div class="noticeView">
            	   <p style="font-size:15px" class="noticeinfo" id="notices_id">글번호: ${a.notices_id } &nbsp&nbsp 작성일: ${a.notices_date }</p>
            	 
            	   <!-- 제목 -->
                   <div id="noticetitle">
                       <!-- <p id="titletxt">제목</p> -->
                       ${a.notices_title}
                   </div>
                   <!-- 내용 -->
                   <div id="noticecontent">
                       <!-- <p id="contenttxt">내용</p> -->
                       ${a.notices_content}
                   </div>
            		<!-- 버튼 -->
            		<div class="writebtn">
						<div id="notice_button">
							<input type="button" id="writebtn" value="목록" onclick="notice()">
		                 </div>
            		</div>
            	</div>
            </div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>