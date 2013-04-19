<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%@page import="com.laoer.bbscs.comm.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><s:text name="face.title"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script language="JavaScript" type="text/javascript">
<!--

var contextPath = "<%=request.getContextPath()%>";
var servletMappingStr = "<%=Constant.SERVLET_MAPPING%>";
var pageLoading = "页面载入中...";
var confirm_del = "确认要删除吗？";
var pageLoadingCenter = "<div align=\"center\">页面载入中...</div>";

function upIframeSize() {
  var objif = document.getElementById('upfileIframe');
  objif.height = 240;
}

function OnUploadCompleted(codeid,msg){
  //document.getElementById('upfileIframe').src= "userFace.bbscs?action=uppage" ;
  var objif = document.getElementById('upfileIframe');
  objif.height = 120;
  objif.src= getActionMappingURL("/userFace?action=uppage");
  if (codeid == "0") {
    //alert(msg);
    userFaceShow();
  }
  if (codeid == "1") {
    alert(msg);
  }
  if (codeid == "2") {
    alert(msg);
  }
  if (codeid == "3") {
    alert(msg);
  }
  if (codeid == "4") {
    alert(msg);
  }

}

function userFaceShow() {
  $('userFaceDiv').innerHTML = pageLoadingCenter;
  var userId = "<s:property value="%{userSession.id}"/>";

  var urls = getActionMappingURL("/userFace");
  var pars = "action=showface&ajax=shtml&userId=" + userId;

  var myAjax = new Ajax.Updater("userFaceDiv", urls, {method: 'get', parameters: pars});
}


function delFace() {
  var del = confirm(confirm_del);
  if (del) {
    showExeMsg();
    var url = getActionMappingURL("/userFace");
    var pars = "action=delme&ajax=xml";
    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: delFaceComplete});
  }
  else {
    return false;
  }
}

function delFaceComplete(res) {
    resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	hiddenExeMsg();
  	alert(jsonMsgObj.getMessage());
  if (codeid == "0") {
    userFaceShow();
  }

}
//-->
</script>
</head>
<body>

<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <fieldset>
        <legend><s:text name="face.title"/></legend>
        <table width="98%" border="0" align="center" cellpadding="5" cellspacing="00">
          <tr>
            <td colspan="2">
              <strong><s:text name="face.cface"/></strong>
            </td>
          </tr>
          <tr>
            <td width="25%" valign="top">
              <div id="userFaceDiv" align="center"><bbscs:face value="%{userSession.id}"/></div>
            </td>
            <td width="75%" valign="top">
            <s:text name="face.des"/>
              <br/>
              [<strong><a href="javascript:;" onclick="delFace();"><s:text name="face.del"/></a></strong>]
              <br/>
              <s:text name="face.notice"/>
            </td>
          </tr>
        </table>
        <s:if test="%{useFace==1}">
        <iframe id="upfileIframe" src="<%=BBSCSUtil.getActionMappingURL("/userFace?action=uppage",request)%>" height="120" width="98%" scrolling="no" frameborder="0"></iframe>
        </s:if>
      </fieldset>
    </td>
  </tr>
</table>
</body>
</html>
