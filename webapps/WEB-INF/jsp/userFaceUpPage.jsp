<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@page import="com.laoer.bbscs.comm.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><s:text name="face.title"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/comm.js"></script>
<script language="JavaScript" type="text/javascript">
<!--

var contextPath = "<%=request.getContextPath()%>";
var servletMappingStr = "<%=Constant.SERVLET_MAPPING%>";

function previewPic(upfilename){
  window.parent.upIframeSize();
  var upfile = document.getElementById(upfilename);
  if (upfile != "") {
    var extname = getExtName(upfile.value);
    if(extname != "jpg" && extname != "gif" && extname != "jpeg"){
      upfile.value = "";
      alert("<s:text name="face.jpggif"/>");
    }else{
      preViewPic('photoview', null, upfile.value, 0, null, 0, 120);
    }
  }
}

//图片预览
function preViewPic(picDivName,imgName,fn,iBorder,iBorderColor,iWidth,iHeight){
    //var picdiv=document.all(picDivName);
    var picdiv = document.getElementById(picDivName);
    if(picdiv != null && fn != null && fn!=''){
      if(imgName ==null) imgName = "picname";
      if(iBorder == null) iBorder=0;
      if(iBorderColor == null) iBorderColor="#CCCCCC";
      var strw,strh
      if(iWidth == null || iWidth == 0) strw = "";
      else strw = " width="+iWidth+" ";
      if(iHeight == null || iHeight == 0) strh = "";
      else strh = " height=" + iHeight + " ";
      picdiv.innerHTML="<img name='"+imgName+"' alt='预览状态...' "+strw+strh+" border="+iBorder+
            " src='"+fn+"' style='border-color:"+iBorderColor+"'>";
      document.focus();
    }
}

//-->
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <s:form action="userFace" method="POST" enctype="multipart/form-data">
  <s:hidden name="action"></s:hidden>
  <tr>
    <td>
      <strong><s:text name="face.upload"/></strong>
    </td>
  </tr>
  <tr>
    <td>
      <s:file name="upload" cssClass="input2" size="40" onchange="previewPic('upload');" id="upload"></s:file>
    </td>
  </tr>
  <tr>
    <td>
      <span id="photoview"></span>
    </td>
  </tr>
  <tr>
    <td>
      <s:submit value="%{getText('face.upload')}" cssClass="button2"></s:submit>
    </td>
  </tr>
  </s:form>
</table>
</body>
</html>
