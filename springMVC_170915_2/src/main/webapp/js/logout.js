/**
 * @author jintao.wang  Date: 17-10-10 Time: 下午6:22
 */
function logout() {
    var logoutUrl = "http://localhost:8080/springMVC_170915_2_tomcat/a.logout";
    $.ajax({
        type:"GET",
        url:logoutUrl,
        async:false,
        success:function (result) {
            var domainArr = result.domainList;
            var namevalueArr = result.cookieNameValueList;
            if(domainArr == null || namevalueArr == null){
                return;
            }
            /*根据后端的指示删除前端的cookie*/
            for (var i = 0;i < domainArr.length;i++){
                var domain = domainArr[i];
                for(var j = 0;j < namevalueArr.length;j++){
                    var name = namevalueArr[i].name;
                    var value = namevalueArr[i].value;
                    document.cookie = name + "=" + value + "; expires=Thu, 01 Jan 1970 00:00:00 GMT;domain=" + domain;
                }
            }
        }
    });
}
