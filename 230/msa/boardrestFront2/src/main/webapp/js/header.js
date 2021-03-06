//--메뉴 클릭 시작--
$("header").on("click", "ul>li>a", function (event) {
   $('header>ul>li>a').css("background-color", 'white');
   $(this).css("background-color", "blanchedalmond");
   var menu = $(event.target).attr("class");
	
   var $sectionObj = $("section");
   switch (menu) {
	  case 'login': //로그인
         $.ajax({
	        url : backContextPath + "/login",
			method : "post",
			data : {id:"id2", pwd:"p2"},
			success:function(){},
			error:function(xhr){
				alert("로그인오류:" + xhr.status);
			}
         });
		 break;
	  case 'logout'://로그아웃
		 $.ajax({
	        url : backContextPath + "/logout",
			method : "post",
			data : {id:"id2", pwd:"p2"},
			success:function(){},
			error:function(xhr){
				alert("로그인오류:" + xhr.status);
			}
         });
		 break;
      case 'list'://게시판
         $sectionObj.load(frontContextPath + "/boardlist.html");
         break;
      case 'write'://글쓰기
         $sectionObj.load(frontContextPath + "/write.html");
         break;
   }
   return false;
});
//--메뉴 클릭 끝--

$(function(){
	$.ajax({
		url: backContextPath + "/checkLogin",
		method : "get",
		success: function(jsonData){
			if(jsonData == null){
				loginedId = null;
				$('header ul>li>a.login').show();
				$('header ul>li>a.logout').hide();
			}else{
				loginedId = jsonData.id;
				$('header ul>li>a.logout').show();
				$('header ul>li>a.login').hide();
			}
		}
	})
	
})
