<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  개인정보 변경 페이지입니다. -->
<!-- 즉 비밀번호 변경 및 회원탈퇴하는 페이지입니다.  -->


<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>RECO</title>
    <link href="css/my_info.css" rel=stylesheet>
  </head>
  <body>
    <header>
      <div class="headerbox"><p class="headertext"><a href="./main.html" class="link">RECO</a></p></div>
      <!-- <session>
        <div class="login_item">
          <ul class="login">
            <li class="log_li" ><a href="#" class=log_link>로그인</a></li>
            <li class="log_li" herf="#"><a href="./main.html"  class=log_link>회원가입</a></li>
            <li class="log_li" herf="#"><a href="./main.html"  class=log_link1 id="log_link_community">커뮤니티</a></li>
  
          </ul>
  
        </div>
      </session> -->
    </header>
    <section>
      <!-- <div class="wrap">
        <ul class="menu">
          <li class="menu_item"><a href="#" class="menu_link yellow">공지사항</a></li>
          <li class="menu_item"><a href="#" class="menu_link yellow">FAQ</a></li>
          <li class="menu_item"><a href="#" class="menu_link yellow">자유게시판</a></li>
        </ul>
      </div> -->
      <form class="container">
      <fieldset >

        <form action="./main.html" method="post">
          
            <h1 calss="info">비밀번호 변경</h1>

          <table>
              <tr>
                  <th>현재 비밀번호</th>
                  <td><input></td>
              </tr>
              <tr>
              
                  <th>새비밀번호
                  <td><input type="password" name="pwd"></td></th>
              </tr>     
              <tr>
                  <th>새비밀번호확인
                  <td><input type="password" name="pwd1"> </td></th>
              </tr>
             
          </table>
          <div>
          <button class="button" type="submit">확인</button>
          <button type="reset" class="button_cancel">취소</button>
        </div>
          </form>
          
        <hr>
        <form>
          <h1 calss="info">닉네임변경</h1>
          <table>
              <tr>
                  <th>새닉네임
                  <td><input></td></th>
              </tr>
            </table>
            <div>
            <button  class="button" type="submit">확인</button>
            <button class="button_cancel" type="reset">취소</button>
          </div>
            </form>
       <hr>
       <button id="membership">회원탈퇴</button>
      </fieldset>
    </form>
    </section>
      
      
      
   
    
<footer>
     	<%@include file="./footer.jsp" %>     <!--맺음말-->  
</footer>
 
</body>
</html>