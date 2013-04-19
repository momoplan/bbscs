<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><s:text name="post.upload"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
  <!--
    body {
    background-color: #FFFFEC;
    margin-left: 0px;
    margin-top: 0px;
    margin-right: 0px;
    margin-bottom: 0px;
    }
  -->
</style>
<script language="JavaScript" type="text/javascript">
<!--
function OnSubmit() {
  if ( document.getElementById('upload').value.length == 0 ) {
    alert( '<s:text name="post.select.upfile"/>' ) ;
    return false ;
  }

  // Set the interface elements.
  document.getElementById('eUploadMessage').innerHTML = '<s:text name="post.file.uping"/>' ;
  document.getElementById('btnUpload').disabled = true ;

  return true ;
}
//-->
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
    <s:form action="post" enctype="multipart/form-data" method="POST" onsubmit="return OnSubmit();">
    <s:hidden name="action" value="upfiledo"></s:hidden>
    <s:hidden name="id"></s:hidden>
    <s:hidden name="bid"></s:hidden>
    <tr>
      <td>
        <strong><s:text name="post.upload"/></strong>
      </td>
    </tr>
    <tr>
      <td>
        <s:text name="post.select.upfile1"/>
        <s:file name="upload" id="upload" cssClass="input2" size="30"></s:file>
        <s:submit value="%{getText('bbscs.botton.upload')}" cssClass="button1" id="btnUpload"></s:submit>
      </td>
    </tr>
    <tr>
      <td>
        <span id="eUploadMessage" class="font2"></span>
      </td>
    </tr>
  </s:form>
</table>
</body>
</html>