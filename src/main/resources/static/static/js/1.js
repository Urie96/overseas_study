function isEmpty(str){
    if(str==null||str==""||str==undefined){
        return true;
    }else{
        return false;
    }
}
function isAccount(account){
	return /(^[a-zA-Z0-9_-]{6,10}$)|(^[\u2E80-\u9FFF]{2,5})/.test(account);
}
function isPhoneFormat(phone){
	return /^((13[0-9]|15[0-9]|17[0-9]|18[0-9])+\d{8})$/.test(phone);
}
function isEmailFormat(email){
	return /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/.test(email);
}
function isQQFormat(QQ){
	return /^[1-9]\d{4,10}$/.test(QQ);
}
function isChineseName(name){
	return /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/.test(name);
}
function logout(){
	$.ajax({
	  type:"get",
	  url:"/logout",
	  async:true,
	  success:function(result){
          $("#notloggedin").show();
          $("#loggedin").hide();
	  }
	});
}

function shiftSearch(){
    if($("#searchBtn").attr("searchWhat")=="schools"){
      $("#searchBtn").text("搜问答").attr("searchWhat","QA");
      $("#shiftSearch").text("搜院校");
    }
    else if($("#searchBtn").attr("searchWhat")=="QA"){
      $("#searchBtn").text("搜院校").attr("searchWhat","schools");
      $("#shiftSearch").text("搜问答");
    }
  }

function search(){
	if($("#searchBtn").attr("searchWhat")=="schools"){
		location.href="searchSchoolResult.html?name="+$("#searchInput").val();
	}
	else if($("#searchBtn").attr("searchWhat")=="QA"){
		location.href="qa.html?key="+$("#searchInput").val();
	}
}

function isEmpty(obj){
	if(typeof obj == "undefined" || obj == null || obj == ""){
	    return true;
	}else{
	    return false;
	}
}
function getQueryString(name) {
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
  var r = decodeURI(window.location.search).substr(1).match(reg);
  if ( r != null ){
     return unescape(r[2]);
  }else{
     return null;
  } 
}

function timestampToTime(timestamp) {
   var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
   Y = date.getFullYear() + '-';
   M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
   D = date.getDate() + ' ';
   h = date.getHours() + ':';
   m = date.getMinutes() + ':';
   s = date.getSeconds();
   return Y+M+D+h+m+s;
}

function timestampToTime2(timestamp) {
   var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
   Y = date.getFullYear() + '-';
   M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
   D = date.getDate() + ' ';
   h = date.getHours() + ':';
   m = date.getMinutes() + ':';
   s = date.getSeconds();
   return Y+M+D;
}

function toIndexPage(){
	location.href="index.html";
}