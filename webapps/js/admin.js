//FaceSet ========================= Start
function faceSetLoadPage() {
  changeStyle("resultMegs","msg2");
  $('resultMegs').innerHTML = adminPageLoading;
  displayElement("info");
  var url = "adminFaceSet.bbscs?action=info&ajax=shtml";
  var callback = faceSetPARGetPage;
  executeXhrOfGet(callback, url);
}

function faceSetPARGetPage() {
  if (req.readyState == 4) {
    if (req.status == 200) {
      $('info').innerHTML = req.responseText;
      $('resultMegs').innerHTML = "";
    } else {
      changeStyle("resultMegs","errormsg");
      $('resultMegs').innerHTML = "Load error!";
    }
  }
}

function adminFaceSetAction() {
  changeStyle("resultMegs","msg2");
  $('resultMegs').innerHTML = adminDataSubmit;
  var url = "adminFaceSet.bbscs";
  var useFaceValue = getRadioValue("adminFaceSetForm","useFace");
  var data = "action=save&ajax=xml&useFace="+useFaceValue+"&faceHigh="+$('faceHigh').value
  +"&faceWidth="+$('faceWidth').value+"&faceSize="+$('faceSize').value;
  //alert(data);
  var callback = faceSetPAR;
  executeXhrOfPost(callback, url, data);
}

function faceSetPAR() {
  if (req.readyState == 4) {
    if (req.status == 200) {
      var resXML = req.responseXML;
      var codeid = getResponseXMLMsgsCodeid(resXML);
      if (codeid == "0") {
        changeStyle("resultMegs","msg3");
        hiddenElement("info");
      }
      else {
        changeStyle("resultMegs","errormsg");
      }
      $('resultMegs').innerHTML = getResponseXMLMsgsMessage(resXML)
      + "<a href=\"javascript:faceSetLoadPage();\">" + adminConfirmLink + "</a>";
    } else {
      alert("There was a problem retrieving the XML data:\n" +req.statusText);
    }
  }
}
//FaceSet ========================= End

//ForbidSet ========================= Start
function forbidSetLoadPage() {
  changeStyle("resultMegs","msg2");
  $('resultMegs').innerHTML = adminPageLoading;
  displayElement("info");
  var url = "adminForbidSet.bbscs?action=info&ajax=shtml";
  var callback = forbidSetPARGetPage;
  executeXhrOfGet(callback, url);
}

function forbidSetPARGetPage() {
  if (req.readyState == 4) {
    if (req.status == 200) {
      $('info').innerHTML = req.responseText;
      $('resultMegs').innerHTML = "";
    } else {
      changeStyle("resultMegs","errormsg");
      $('resultMegs').innerHTML = "Load error!";
    }
  }
}

function adminForbidSetAction() {
  changeStyle("resultMegs","msg2");
  $('resultMegs').innerHTML = adminDataSubmit;
  var url = "adminForbidSet.bbscs";
  var useForbidValue = getRadioValue("adminForbidSetForm","useForbid");
  var data = "action=save&ajax=xml&useForbid="+useForbidValue+"&forbidIP="+$('forbidIP').value
  +"&forbidEmail="+$('forbidEmail').value;
  //alert(data);
  var callback = firbidSetPAR;
  executeXhrOfPost(callback, url, data);
}

function firbidSetPAR() {
  if (req.readyState == 4) {
    if (req.status == 200) {
      var resXML = req.responseXML;
      var codeid = getResponseXMLMsgsCodeid(resXML);
      if (codeid == "0") {
        changeStyle("resultMegs","msg3");
        hiddenElement("info");
      }
      else {
        changeStyle("resultMegs","errormsg");
      }
      $('resultMegs').innerHTML = getResponseXMLMsgsMessage(resXML)
      + "<a href=\"javascript:forbidSetLoadPage();\">" + adminConfirmLink + "</a>";
    } else {
      alert("There was a problem retrieving the XML data:\n" +req.statusText);
    }
  }
}
//ForbidSet ========================= End
