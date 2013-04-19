<!--
var ShowIpOjb = function(bid,id){
  this.bid = bid;
  this.id = id;
}

ShowIpOjb.prototype.execute = function(resText) {
  $('ipMsg' + this.id).innerHTML = resText;
}

function showIp(bid,id) {
  displayElement("ipMsg" + id);
  $('ipMsg' + id).innerHTML = pageLoading;

  var url = getActionMappingURL("/read");
  var pars = "action=showip&ajax=shtml&id=" + id + "&bid=" + bid;
  var myAjax = new Ajax.Updater("ipMsg" + id, url, {method: 'get', parameters: pars});
}

function showHisrotyIp(bid,id) {
  displayElement("ipMsg" + id);
  $('ipMsg' + id).innerHTML = pageLoading;

  var url = getActionMappingURL("/read");
  var pars = "action=showiphistory&ajax=shtml&id=" + id + "&bid=" + bid;
  var myAjax = new Ajax.Updater("ipMsg" + id, url, {method: 'get', parameters: pars});
}

function closeShowIp(id) {
  $('ipMsg' + id).innerHTML = "";
  hiddenElement("ipMsg" + id);
}

var ShowUpfilePageOjb = function(bid,id){
  this.bid = bid;
  this.id = id;
}

ShowUpfilePageOjb.prototype.execute = function(resText) {
  $('postOpt' + this.id).innerHTML = resText;
}

function showUpfilePage(bid,id) {
  hiddenElement("noteSend" + id);
  displayElement("postOpt" + id);
  $('postOpt' + id).innerHTML = pageLoading;

  var url = getActionMappingURL("/post");
  var pars = "action=upfile&ajax=shtml&id=" + id + "&bid=" + bid;
  var myAjax = new Ajax.Updater("postOpt" + id, url, {method: 'get', parameters: pars});
}

function closeShowUpfilePage(id) {
  $('postOpt' + id).innerHTML = "";
  hiddenElement("postOpt" + id);
}

function showDelAttachPage(bid,id) {
  hiddenElement("noteSend" + id);
  displayElement("postOpt" + id);
  $('postOpt' + id).innerHTML = pageLoading;

  var url = getActionMappingURL("/post");
  var pars = "action=showdelattachpage&ajax=shtml&id=" + id + "&bid=" + bid;
  var myAjax = new Ajax.Updater("postOpt" + id, url, {method: 'get', parameters: pars});
}

function showMovePage(bid,id) {
  hiddenElement("noteSend" + id);
  displayElement("postOpt" + id);
  $('postOpt' + id).innerHTML = pageLoading;
  var url = getActionMappingURL("/moveForum");
  var pars = "action=movepage&ajax=shtml&id=" + id + "&bid=" + bid;
  var myAjax = new Ajax.Updater("postOpt" + id, url, {method: 'get', parameters: pars});
}

var ShowUpfileInPostOjb = function(bid,id){
  this.bid = bid;
  this.id = id;
}

ShowUpfileInPostOjb.prototype.execute = function(resText) {
  $('upfile' + this.id).innerHTML = resText;
}

function showUpfileInPost(bid,id) {
  displayElement("upfile" + id);

  var url = getActionMappingURL("/read");
  var pars = "action=showupfile&ajax=shtml&id=" + id + "&bid=" + bid;
  var myAjax = new Ajax.Updater("upfile" + id, url, {method: 'get', parameters: pars});
}


function OnUploadCompleted(bid,pid,codeid,msg){
  alert(msg);
  if (codeid == "0") {
    showUpfilePage(bid,pid);
    showUpfileInPost(bid,pid);
  }
  else if (codeid == "2") {
    closeShowUpfilePage(pid);
  }
  else if (codeid == "4") {
    closeShowUpfilePage(pid);
  }
  else if (codeid == "99") {
    closeShowUpfilePage(pid);
  }
  else {
    showUpfilePage(bid,pid);
  }
}

var AgreeAgainstAjax = Class.create();

AgreeAgainstAjax.prototype = {
  initialize: function(action,bid,id) {
    this.action = action;
    this.bid = bid;
    this.id = id;
  },

  doAgreeAgainst: function() {
    showExeMsg();
    var url = getActionMappingURL("/postOpt");
    var pars = "ajax=xml&action=" + this.action + "&id=" + this.id + "&bid=" + this.bid;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.agreeAgainstCompleted.bind(this)});
  },

  agreeAgainstCompleted: function(res) {
    //resXML = res.responseXML;
    //var codeid = getResponseXMLMsgsCodeid(resXML);

    resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();

    hiddenExeMsg();
    alert(jsonMsgObj.getMessage());
    if (codeid == "0") {
      if (this.action == "votyes") {
        $('agree' + this.id).innerHTML = jsonMsgObj.getText();
      }
      if (this.action == "votno") {
        $('beAgainst' + this.id).innerHTML = jsonMsgObj.getText();
      }
    }
  }
};

function agreeAgainst(bid,id,type) {
  var oAgreeAgainstAjax = new AgreeAgainstAjax(type,bid,id);
  oAgreeAgainstAjax.doAgreeAgainst();
}

var DelAPostAjax = Class.create();

DelAPostAjax.prototype = {
  initialize: function(bid,id,isNew,fcpage) {
    this.bid = bid;
    this.id = id;
    this.isNew = isNew;
    this.fcpage = fcpage;
  },

  del: function() {
    //var url = getActionMappingURL("/postOpt?ajax=xml&action=dela&id=" + id + "&bid=" + bid);
    showExeMsg();
    var url = getActionMappingURL("/postOpt");
    var pars = "ajax=xml&action=dela&id=" + this.id + "&bid=" + this.bid;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.delCompleted.bind(this)});
  },

  delCompleted: function(res) {
    //resXML = res.responseXML;
    //var codeid = getResponseXMLMsgsCodeid(resXML);

    resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
    hiddenExeMsg();
    alert(jsonMsgObj.getMessage());
    if (codeid == "0") {
      if (this.isNew == 1) {
        window.location.href = getActionMappingURL("/forum?action=index&bid=" + this.bid + "&page=" + this.fcpage);
      }
      else {
        $('topic' + this.id).className = "summary1";
        //$('topic' + this.id).innerHTML = "["+ this.id +"]" + deleted;
        $('topic' + this.id).innerHTML = postdeleted.format(this.id);
        document.getElementById("siderbarcontent").style.height=document.getElementById("postlistcontent").scrollHeight+"px";
      }
    }
  }
};

function delapost(bid,id,isNew,fcpage) {
  var del = confirm(confirm_del);
  if (del) {
    var oDelAPostAjax = new DelAPostAjax(bid,id,isNew,fcpage);
    oDelAPostAjax.del();
  }
  else {
    return false;
  }
}

function subsTopic(bid,id) {
  showExeMsg();
  var url = getActionMappingURL("/postOpt");
  var pars = "ajax=xml&action=subs&id=" + id + "&bid=" + bid;
  var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.subsTopicCompleted.bind(this)});
}

function subsTopicCompleted(res) {

  resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  hiddenExeMsg();
  alert(jsonMsgObj.getMessage());
}

var TopTopicAjax = Class.create();

TopTopicAjax.prototype = {
  initialize: function(bid,id,type) {
    this.type = type;
    this.bid = bid;
    this.id = id;
  },

  top: function() {
    //var url = getActionMappingURL("/postOpt?ajax=xml&action=" + type + "&id=" + id + "&bid=" + bid);
    showExeMsg();
    var url = getActionMappingURL("/postOpt");
    var pars = "ajax=xml&action=" + this.type + "&id=" + this.id + "&bid=" + this.bid;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.topCompleted.bind(this)});
  },

  topCompleted: function(res) {
    //resXML = res.responseXML;
    //var codeid = getResponseXMLMsgsCodeid(resXML);
    resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
    hiddenExeMsg();
    alert(jsonMsgObj.getMessage());
    if (codeid == "0") {
      if (this.type == "top") {
        $('topset' + this.id).innerHTML = "[<a href=\"javascript:;\" onclick=\"topSet('"+this.bid+"','"+this.id+"','untop');\">"+postsetuntop+"</a>]\n";
      }
      if (this.type == "untop") {
        $('topset' + this.id).innerHTML = "[<a href=\"javascript:;\" onclick=\"topSet('"+this.bid+"','"+this.id+"','top');\">"+postsettop+"</a>]\n";
      }
    }
  }
};

function topSet(bid,id,type) {
  var topset = false;
  if (type == "top") {
    topset = confirm(postsettopconfirm);
  }
  if (type == "untop") {
    topset = confirm(postsetuntopconfirm);
  }
  if (topset) {
    var oTopTopicAjax = new TopTopicAjax(bid,id,type);
    oTopTopicAjax.top();
  }
  else {
    return false;
  }
}

var CanNotDelTopicAjax = Class.create();

CanNotDelTopicAjax.prototype = {
  initialize: function(bid,id,type) {
    this.type = type;
    this.bid = bid;
    this.id = id;
  },

  canNotDel: function() {
    showExeMsg();
    var url = getActionMappingURL("/postOpt");
    var pars = "ajax=xml&action=" + this.type + "&id=" + this.id + "&bid=" + this.bid;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.canNotDelCompleted.bind(this)});
  },

  canNotDelCompleted: function(res) {

    resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
    hiddenExeMsg();
    alert(jsonMsgObj.getMessage());
    if (codeid == "0") {
      if (this.type == "cannotdel") {
        $('cndt' + this.id).innerHTML = "M";
        $('cannotdel' + this.id).innerHTML = "[<a href=\"javascript:;\" onclick=\"canNotDelSet('"+this.bid+"','"+this.id+"','candel');\">"+postuncannotdel+"</a>]\n";
      }
      if (this.type == "candel") {
        $('cndt' + this.id).innerHTML = "";
        $('cannotdel' + this.id).innerHTML = "[<a href=\"javascript:;\" onclick=\"canNotDelSet('"+this.bid+"','"+this.id+"','cannotdel');\">"+postcannotdel+"</a>]\n";
      }
    }
  }
};

function canNotDelSet(bid,id,type) {
  var candelset = false;
  if (type == "cannotdel") {
    candelset = confirm(postcannotdelconfirm);
  }
  if (type == "candel") {
    candelset = confirm(postuncannotdelconfirm);
  }
  if (candelset) {
    var oCanNotDelTopicAjax = new CanNotDelTopicAjax(bid,id,type);
    oCanNotDelTopicAjax.canNotDel();
  }
  else {
    return false;
  }
}

var CommendTopicAjax = Class.create();

CommendTopicAjax.prototype = {
  initialize: function(bid,id,type) {
    this.type = type;
    this.bid = bid;
    this.id = id;
  },

  commend: function() {
    showExeMsg();
    var url = getActionMappingURL("/postOpt");
    var pars = "ajax=xml&action=" + this.type + "&id=" + this.id + "&bid=" + this.bid;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.commendCompleted.bind(this)});
  },

  commendCompleted: function(res) {
    resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
    hiddenExeMsg();
    alert(jsonMsgObj.getMessage());
    if (codeid == "0") {
      if (this.type == "commend") {
        $('commend' + this.id).innerHTML = "[<a href=\"javascript:;\" onclick=\"commendSet('"+this.bid+"','"+this.id+"','uncommend');\">"+postuncommend+"</a>]\n";
      }
      if (this.type == "uncommend") {
        $('commend' + this.id).innerHTML = "[<a href=\"javascript:;\" onclick=\"commendSet('"+this.bid+"','"+this.id+"','commend');\">"+postcommend+"</a>]\n";
      }
    }
  }
};

function commendSet(bid,id,type) {
  var cset = false;
  if (type == "commend") {
    cset = confirm(postcommendconfirm);
  }
  if (type == "uncommend") {
    cset = confirm(postuncommendconfirm);
  }
  if (cset) {
    var oCommendTopicAjax = new CommendTopicAjax(bid,id,type);
    oCommendTopicAjax.commend();
  }
  else {
    return false;
  }
}

var LockTopicAjax = Class.create();

LockTopicAjax.prototype = {
  initialize: function(bid,id,type) {
    this.type = type;
    this.bid = bid;
    this.id = id;
  },

  lock: function() {
    showExeMsg();
    var url = getActionMappingURL("/postOpt");
    var pars = "ajax=xml&action=" + this.type + "&id=" + this.id + "&bid=" + this.bid;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.lockCompleted.bind(this)});
  },

  lockCompleted: function(res) {
    resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
    hiddenExeMsg();
    alert(jsonMsgObj.getMessage());
    if (codeid == "0") {
      if (this.type == "lock") {
        $('lock' + this.id).innerHTML = "[<a href=\"javascript:;\" onclick=\"lockSet('"+this.bid+"','"+this.id+"','unlock');\">"+postunlock+"</a>]\n";
      }
      if (this.type == "unlock") {
        $('lock' + this.id).innerHTML = "[<a href=\"javascript:;\" onclick=\"lockSet('"+this.bid+"','"+this.id+"','lock');\">"+postlock+"</a>]\n";
      }
    }
  }
};

function lockSet(bid,id,type) {
  var lset = false;
  if (type == "lock") {
    lset = confirm(postlockconfirm);
  }
  if (type == "unlock") {
    lset = confirm(postunlockconfirm);
  }
  if (lset) {
    var oLockTopicAjax = new LockTopicAjax(bid,id,type);
    oLockTopicAjax.lock();
  }
  else {
    return false;
  }
}

var EliteTopicAjax = Class.create();

EliteTopicAjax.prototype = {
  initialize: function(bid,id) {
    this.bid = bid;
    this.id = id;
  },

  elite: function() {
    showExeMsg();
    var url = getActionMappingURL("/postOpt");
    var pars = "ajax=xml&action=elite&id=" + this.id + "&bid=" + this.bid;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.eliteCompleted.bind(this)});
  },

  eliteCompleted: function(res) {
  	var jsonMsgObj = new JsonMsgObj(res.responseText);
  	var codeid = jsonMsgObj.getCodeid();
    hiddenExeMsg();
    alert(jsonMsgObj.getMessage());
    if (codeid == "0") {
      $('elite' + this.id).innerHTML = "";
      Element.hide("elite" + id);
    }
  }
};

function eliteTopic(bid,id) {
  var eliteset = confirm(posteliteconfirm);
  if (eliteset) {
    var oEliteTopicAjax = new EliteTopicAjax(bid,id);
    oEliteTopicAjax.elite();
  }
  else {
    return false;
  }
}


var SendMailTopicAjax = Class.create();

SendMailTopicAjax.prototype = {
  initialize: function(bid,id) {
    this.bid = bid;
    this.id = id;
  },

  sendMail: function() {
    showExeMsg();
    var url = getActionMappingURL("/postOpt");
    var pars = "ajax=xml&action=mailsend&id=" + this.id + "&bid=" + this.bid;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.sendMailCompleted.bind(this)});
  },

  sendMailCompleted: function(res) {
    var jsonMsgObj = new JsonMsgObj(res.responseText);
  	var codeid = jsonMsgObj.getCodeid();
    hiddenExeMsg();
    Element.hide("postOpt" + this.id);
    alert(jsonMsgObj.getMessage());
  }
};

function mailSendTopic(bid,id) {
  var mset = confirm(sendtopicconfirm);
  if (mset) {
    Element.show("postOpt" + id);
    $('postOpt' + id).innerHTML = sendMailMsg;
    var oSendMailTopicAjax = new SendMailTopicAjax(bid,id);
    oSendMailTopicAjax.sendMail();
  }
  else {
    return false;
  }
}

var ReportTopicAjax = Class.create();

ReportTopicAjax.prototype = {
  initialize: function(bid,id) {
    this.bid = bid;
    this.id = id;
  },

  report: function() {
    showExeMsg();
    var url = getActionMappingURL("/postOpt");
    var pars = "ajax=xml&action=report&id=" + this.id + "&bid=" + this.bid;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.reportCompleted.bind(this)});
  },

  reportCompleted: function(res) {
    var jsonMsgObj = new JsonMsgObj(res.responseText);
  	var codeid = jsonMsgObj.getCodeid();
    hiddenExeMsg();
    Element.hide("postOpt" + this.id);
    alert(jsonMsgObj.getMessage());
  }
};

function reportTopic(bid,id) {
  var mset = confirm(reporttopicconfirm);
  if (mset) {
    Element.show("postOpt" + id);
    $('postOpt' + id).innerHTML = sendMailMsg;
    var oReportTopicAjax = new ReportTopicAjax(bid,id);
    oReportTopicAjax.report();
  }
  else {
    return false;
  }
}

var VoteOjb = function(bid,id){
  this.bid = bid;
  this.id = id;
}

VoteOjb.prototype.execute = function(resXML) {
  var codeid = getResponseXMLMsgsCodeid(resXML);
  alert(getResponseXMLMsgsMessage(resXML));
  if (codeid == "0") {
    showVoteInPost(this.bid,this.id);
  }
}

var VoteAjax = Class.create();

VoteAjax.prototype = {
  initialize: function(bid,id) {
    this.bid = bid;
    this.id = id;
  },

  vote: function() {
    showExeMsg();
    var url = getActionMappingURL("/voteOpt");
    var postid = this.id;
    var voteid = $("voteid"+this.id).value;
    var deadline = $("deadline"+this.id).value;
    var isM = $("isM"+this.id).value;
    var pars = "action=vote&ajax=xml&bid="+this.bid+"&deadline="+deadline+"&isM="+isM+"&postid="+postid+"&voteid="+voteid;
    var voteitemids = document.getElementsByName("voteitemid");
    for (var i = 0; i < voteitemids.length; i++) {
      if (voteitemids[i].checked) {
        pars = pars + "&voteitemid=" + voteitemids[i].value;
      }
    }
    //alert(pars);
    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.voteCompleted.bind(this)});
  },

  voteCompleted: function(res) {
    //resXML = res.responseXML;
    //var codeid = getResponseXMLMsgsCodeid(resXML);
    resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
    hiddenExeMsg();
    alert(jsonMsgObj.getMessage());
    //alert(codeid);
    if (codeid == "0") {
      showVoteInPost(this.bid,this.id);
    }
  }
};

function postVote(pid) {
  var postid = $("postid"+pid).value;
  var bid = $("bid"+pid).value;

  var oVoteAjax = new VoteAjax(bid,postid);
  oVoteAjax.vote();
}

var ShowVoteInPostOjb = function(bid,id){
  this.bid = bid;
  this.id = id;
}

ShowVoteInPostOjb.prototype.execute = function(resText) {
  $('pvote' + this.id).innerHTML = resText;
}

function showVoteInPost(bid,id) {

  var url = getActionMappingURL("/read");
  var pars = "action=showvote&ajax=shtml&id=" + id + "&bid=" + bid;
  var myAjax = new Ajax.Updater('pvote' + id, url, {method: 'get', parameters: pars});
}

var SendMailTopicAllAjax = Class.create();

SendMailTopicAllAjax.prototype = {
  initialize: function(bid,id) {
    this.bid = bid;
    this.id = id;
  },

  sendMail: function() {
    showExeMsg();
    var url = getActionMappingURL("/postOpt");
    var pars = "ajax=xml&action=mailsendtopic&mainid=" + this.id + "&bid=" + this.bid;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.sendMailCompleted.bind(this)});
  },

  sendMailCompleted: function(res) {
    var jsonMsgObj = new JsonMsgObj(res.responseText);
  	var codeid = jsonMsgObj.getCodeid();
    hiddenExeMsg();
    alert(jsonMsgObj.getMessage());
  }
};

function mailSendTopicAll(bid,id) {
  var mset = confirm(sendtopicconfirm);
  if (mset) {
    var oSendMailTopicAllAjax = new SendMailTopicAllAjax(bid,id);
    oSendMailTopicAllAjax.sendMail();
  }
  else {
    return false;
  }
}

var DelAttachOjb = function(bid,id){
  this.bid = bid;
  this.id = id;
}

DelAttachOjb.prototype.execute = function(resXML) {
  var codeid = getResponseXMLMsgsCodeid(resXML);
  alert(getResponseXMLMsgsMessage(resXML));
  if (codeid == "0") {
    showUpfileInPost(this.bid,this.id);
    closeShowUpfilePage(this.id);
    //showDelAttachPage(this.bid,this.id);
  }
}

var DelAttachAjax = Class.create();

DelAttachAjax.prototype = {
  initialize: function(bid,id) {
    this.bid = bid;
    this.id = id;
  },

  delAttach: function() {
    showExeMsg();
    var url = getActionMappingURL("/delAttach");
    var pars = "ajax=xml&bid="+this.bid+"&id="+this.id;
    var fileNames = document.getElementsByName("attachFileName"+this.id);
    for (var i = 0; i < fileNames.length; i++) {
      if (fileNames[i].checked) {
        pars = pars + "&attchFileNames=" + fileNames[i].value;
      }
    }
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.delAttachCompleted.bind(this)});
  },

  delAttachCompleted: function(res) {
    resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
    hiddenExeMsg();
    alert(jsonMsgObj.getMessage());
    if (codeid == "0") {
      showUpfileInPost(this.bid,this.id);
      closeShowUpfilePage(this.id);
    }
  }
};

function delAttachFile(bid,id) {
  var dels = confirm(confirm_del);
  if (dels) {
    var oDelAttachAjax = new DelAttachAjax(bid,id);
    oDelAttachAjax.delAttach();
  }
  else {
    return false;
  }
}

function loadNoteSend(fid) {
  Element.hide("postOpt"+fid);
  Element.show("noteSend"+fid);
}

function closeNoteSend(fid) {
  Element.hide("noteSend"+fid);
}

function noteAdd(noteId) {
  var oNoteAddAjax = new NoteAddAjax(noteId);
  oNoteAddAjax.addNote();
}

var NoteAddOjb = function(noteId){
  this.noteId = noteId;
}

NoteAddOjb.prototype.execute = function(resXML) {
  var codeid = getResponseXMLMsgsCodeid(resXML);
  alert(getResponseXMLMsgsMessage(resXML));
  if (codeid == "0") {
    var noteForm = eval("document.noteSendForm" + this.noteId);
    noteForm.noteTitle.value = "";
    noteForm.noteContext.value = "";
    closeNoteSend(this.noteId);
  }
}

var NoteAddAjax = Class.create();

NoteAddAjax.prototype = {
  initialize: function(noteId) {
    this.noteId = noteId;
  },

  addNote: function() {
    showExeMsg();
    var url = getActionMappingURL("/note");
    var noteForm = eval("document.noteSendForm" + this.noteId);
    var needRe = 0;
    if (noteForm.needRe.checked) {
      needRe = 1;
    }
  //alert(needRe);
    var pars = "action=addsave&ajax=xml&toUserName=" + noteForm.toUserName.value + "&noteTitle="
    + encodeURIComponent(noteForm.noteTitle.value) + "&noteContext=" + encodeURIComponent(noteForm.noteContext.value)
    + "&needRe=" + needRe;
    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.addNoteCompleted.bind(this)});
  },

  addNoteCompleted: function(res) {
    var jsonMsgObj = new JsonMsgObj(res.responseText);
  	var codeid = jsonMsgObj.getCodeid();
    hiddenExeMsg();
    alert(jsonMsgObj.getMessage());
    if (codeid == "0") {
      var noteForm = eval("document.noteSendForm" + this.noteId);
      noteForm.noteTitle.value = "";
      noteForm.noteContext.value = "";
      closeNoteSend(this.noteId);
    }
  }
};

function copyPostUrl() {
  copyToClipboard($("posturl").innerHTML);
  alert("复制完成");
}
//-->
