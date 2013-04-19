<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path=request.getContextPath();
%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>广告图片上传</title>
</head>

<script type="text/javascript">

function uploadimage(){
	 
	var lotnoid=document.getElementById("lotnoid").value;
     if(lotnoid=='0'||lotnoid==''){
         alert("请选择板块");
         return;
         }
 	var file=document.getElementById("file").value;
    if(file==""){
        alert("请填选择图片");
        return;
        }
	document.getElementById("imageform").submit();
}

function reset(){
	document.getElementById("imageform").reset();
}



</script>

<body>
   <form action="<%=path%>/imageupload.bbscs" enctype="multipart/form-data" method="post" name="imageform" id="imageform">
     <table>
     <tr>
     <td>选择板块:</td>
     <td>
      <select name="image.lotnoid" id="lotnoid">
          <option value="0" selected="selected">请选择</option>
        <s:iterator value="#session.adboardList" status="boardlist">
          <option value="<s:property value="id"/>"> <s:property value="boardName"/></option>
        </s:iterator>
      </select>
     </td>
     </tr>
      <tr>
       <td>图片名字</td>
       <td>
        <input type="text" name="image.name" id="name"/>
       </td>
      </tr>
      <tr>
      <td>图片链接地址(请加http://):</td>
      <td>
        <input type="text" name="image.href">
      </td>
      </tr>
      <tr>
        <td>请选择图片</td>
         <td><input type="file" name="file" id="file"/>  </td>
      </tr>
     <tr>
       <td><input type="button" value="重置" onclick="reset()"/></td>
       <td><input type="button" value="提交" onclick="uploadimage()"/></td>
     </tr>
     </table>
   </form>
</body>
</html>