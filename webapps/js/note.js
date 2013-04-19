<!--

function loadNoteInboxUrl(url) {
  $('noteListDiv').innerHTML = pageLoadingCenter;

  var urls = getActionName(url);
  var pars = getActionPars(url);

  var myAjax = new Ajax.Updater("noteListDiv", urls, {method: 'get', parameters: pars});

  showInboxNum();
  showOutboxNum();
}

function loadNoteInbox() {
  $('noteListDiv').innerHTML = pageLoadingCenter;

  var urls = getActionMappingURL("/note");
  var pars = "action=inbox&ajax=shtml";

  var myAjax = new Ajax.Updater("noteListDiv", urls, {method: 'get', parameters: pars});


  showInboxNum();
  showOutboxNum();
}

function loadNoteOutboxUrl(url) {
  $('noteListDiv').innerHTML = pageLoadingCenter;

  var urls = getActionName(url);
  var pars = getActionPars(url);

  var myAjax = new Ajax.Updater("noteListDiv", urls, {method: 'get', parameters: pars});


  showInboxNum();
  showOutboxNum();
}

function loadNoteOutbox() {
  $('noteListDiv').innerHTML = pageLoadingCenter;

  var urls = getActionMappingURL("/note");
  var pars = "action=outbox&ajax=shtml";

  var myAjax = new Ajax.Updater("noteListDiv", urls, {method: 'get', parameters: pars});

  showInboxNum();
  showOutboxNum();
}

function loadNoteInboxComplete(responseText) {
  $('noteListDiv').innerHTML = responseText;
}

function showInboxNum() {

  var url = getActionMappingURL("/note");
  var pars = "action=innum&ajax=xml";

  var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: showInboxNumComplete});

}

function showInboxNumComplete(res) {
	var resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);

  	$('inboxNumDiv').innerHTML = jsonMsgObj.getText();
}

function showOutboxNum() {

  var url = getActionMappingURL("/note");
  var pars = "action=outnum&ajax=xml";

  var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: showOutboxNumComplete});
}

function showOutboxNumComplete(res) {
	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	$('outboxNumDiv').innerHTML = jsonMsgObj.getText();
}

function loadNoteSend() {
  displayElement("noteSendDiv");
  $('noteSendDiv').innerHTML = pageLoadingCenter;

  var urls = getActionMappingURL("/note");
  var pars = "action=add&ajax=shtml";

  var myAjax = new Ajax.Updater("noteSendDiv", urls, {method: 'get', parameters: pars});

}

function loadNoteSendToUser(toUserName) {
  displayElement("noteSendDiv");
  $('noteSendDiv').innerHTML = pageLoadingCenter;

  var urls = getActionMappingURL("/note");
  var pars = "action=add&ajax=shtml&toUserName="+toUserName;

  var myAjax = new Ajax.Updater("noteSendDiv", urls, {method: 'get', parameters: pars});

}

function closeNoteSend() {
   $('noteSendDiv').innerHTML = "";
   hiddenElement("noteSendDiv");
}

function loadNoteReadOutbox(noteId,page) {
  displayElement("noteDiv"+noteId);

  var urls = getActionMappingURL("/note");
  var pars = "action=readoutbox&ajax=shtml&id=" + noteId + "&page=" + page;

  var myAjax = new Ajax.Updater("noteDetail"+noteId, urls, {method: 'get', parameters: pars});

}

function loadNoteReadInbox(noteId,page) {

  needRe_span = document.getElementById("needRe"+noteId);
  var needRe_num_val = needRe_span.innerHTML;
  var needRe_int_val = parseInt(needRe_num_val);
  if (needRe_int_val == 1) {
    autoRe(noteId);
  }
  displayElement("noteDiv"+noteId);

  var urls = getActionMappingURL("/note");
  var pars = "action=readinbox&ajax=shtml&id=" + noteId + "&page=" + page;

  var myAjax = new Ajax.Updater("noteDetail"+noteId, urls, {method: 'get', parameters: pars});

  $('noteIsNew'+noteId).innerHTML = "<img src=\"images/note_old.gif\"/>";
}

function closeNoteDiv(noteId) {
   //$('noteDiv'+noteId).innerHTML = "";
   hiddenElement("noteDiv"+noteId);
}

function noteAdd() {
  var url = getActionMappingURL("/note");
  var needRe = 0;
  if ($('needRe').checked) {
    needRe = 1;
  }
  //alert(needRe);
  var pars = "action=addsave&ajax=xml&toUserName=" + $('toUserName').value + "&noteTitle="
  + encodeURIComponent($('noteTitle').value) + "&noteContext=" + encodeURIComponent($('noteContext').value)
  + "&needRe=" + needRe;
  //alert(data);
  var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: noteAddComplete});
}

function noteAddComplete(res) {
	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  var codeid = jsonMsgObj.getCodeid();
  alert(jsonMsgObj.getMessage());
  if (codeid == "0") {
    closeNoteSend();
    refreshBoxNum("outbox",1);
  }
}

function refreshBoxNum(boxName,num) {
  var num_span;
  if (boxName == "inbox") {
    num_span = document.getElementById("inboxNumDiv");
  }
  else {
    num_span = document.getElementById("outboxNumDiv");
  }
  var num_val = num_span.innerHTML;
  var int_val = parseInt(num_val);
  var new_int_val = int_val + num;
  num_span.innerHTML = new_int_val;
}

function changeBox() {
  var boxSelectObj = document.getElementById("boxSelect");

  if (boxSelectObj.options[boxSelectObj.selectedIndex].value == "1") {
    loadNoteInbox();
  }
  if (boxSelectObj.options[boxSelectObj.selectedIndex].value == "2") {
    loadNoteOutbox();
  }
}

function deleteInboxNote(noteId,pageNum) {
  var del = confirm(confirm_del);
  if (del) {
    var oNoteDelInboxOjbAjax = new NoteDelInboxOjbAjax(noteId,pageNum);
    oNoteDelInboxOjbAjax.del();
  }
  else {
    return false;
  }
}

var NoteDelInboxOjbAjax = Class.create();

NoteDelInboxOjbAjax.prototype = {
  initialize: function(noteId,pageNum) {
  	this.noteId = noteId;
    this.pageNum = pageNum;
  },

  del: function() {

    var url = getActionMappingURL("/note");
    var pars = "action=delinbox&ajax=xml&id=" + this.noteId;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.delCompleted.bind(this)});
  },

  delCompleted: function(res) {
  	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		refreshBoxNum("inbox",-1);
  		var urls = getActionMappingURL("/note?action=inbox&ajax=shtml&page=" + this.pageNum);
  		loadNoteInboxUrl(urls);
  	}
  }
};

//===
function deleteOutboxNote(noteId,pageNum) {
  //alert(noteId+"/" +pageNum);
  var del = confirm(confirm_del);
  if (del) {
    var oNoteDelOutboxOjbAjax = new NoteDelOutboxOjbAjax(noteId,pageNum);
    oNoteDelOutboxOjbAjax.del();
  }
  else {
    return false;
  }
}

var NoteDelOutboxOjbAjax = Class.create();

NoteDelOutboxOjbAjax.prototype = {
  initialize: function(noteId,pageNum) {
  	this.noteId = noteId;
    this.pageNum = pageNum;
  },

  del: function() {

    var url = getActionMappingURL("/note");
    var pars = "action=deloutbox&ajax=xml&id=" + this.noteId;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.delCompleted.bind(this)});
  },

  delCompleted: function(res) {
  	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		refreshBoxNum("outbox",-1);
  		var urls = getActionMappingURL("/note?action=outbox&ajax=shtml&page=" + this.pageNum);
  		loadNoteOutboxUrl(urls);
  	}
  }
};
//===

function autoRe(noteId) {
  var cRe = confirm(confirmNoteRe);
  if (cRe) {
    var oNoteAutoReOjbAjax = new NoteAutoReOjbAjax(noteId);
    oNoteAutoReOjbAjax.autore();
  }
  else {
    return false;
  }
}

var NoteAutoReOjbAjax = Class.create();

NoteAutoReOjbAjax.prototype = {
  initialize: function(noteId) {
  	this.noteId = noteId;
  },

  autore: function() {

    var url = getActionMappingURL("/note");
    var pars = "action=autore&ajax=xml&id=" + this.noteId;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.autoreCompleted.bind(this)});
  },

  autoreCompleted: function(res) {
  	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		refreshBoxNum("outbox",1);
  		//$("needRe"+this.noteId).innerHTML = "0";
  		document.getElementById("needRe"+this.noteId).innerHTML = "0";
  	}
  }
};


function loadNoteSendInNote(noteId) {
  //alert(noteId);
  //alert($("noteSend" + noteId).style.display);
  //displayElement("noteSend"+noteId);
  //Element.show('noteDiv' + noteId);
  Element.show('noteSend' + noteId);
  //$("noteSend" + noteId).style.display = "block";
}

function closeNoteSendInNote(noteId) {
  hiddenElement("noteSend"+noteId);
}


function noteRe(noteId) {
	var oNoteReAjax = new NoteReAjax(noteId);
	oNoteReAjax.re();
}

var NoteReAjax = Class.create();

NoteReAjax.prototype = {
  initialize: function(noteId) {
  	this.noteId = noteId;
  },

  re: function() {
  	var url = getActionMappingURL("/note");
  	var noteForm = eval("document.noteSendForm" + this.noteId);
  	var needRe = 0;
  	if (noteForm.needRe.checked) {
  		needRe = 1;
  	}
  	var pars = "action=re&ajax=xml&toUserName=" + noteForm.toUserName.value + "&noteTitle="
  	+ encodeURIComponent(noteForm.noteTitle.value) + "&noteContext=" + encodeURIComponent(noteForm.noteContext.value)
  	+ "&needRe=" + needRe + "&id="+noteForm.id.value;

    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.reCompleted.bind(this)});
  },

  reCompleted: function(res) {
  	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		refreshBoxNum("outbox",1);
  		var noteForm = eval("document.noteSendForm" + this.noteId);
  		noteForm.noteTitle.value = "";
  		noteForm.noteContext.value = "";
  		closeNoteSendInNote(this.noteId);
  		$('noteReImg'+this.noteId).innerHTML = "<img src=\"images/note_replied.gif\" alt=\"\"/>";
  	}
  }
};


function delAllInBox() {
  var del = confirm(confirm_del);
  if (del) {
    var url = getActionMappingURL("/note");
    var pars = "action=delallinbox";

    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: delAllInBoxComplete});

  }
  else {
    return false;
  }
}

function delAllInBoxComplete(res) {
	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	if (codeid == "0") {
  		refreshBoxNumToZero("inbox");
  		loadNoteInbox();
  	}
}

function delAllOutBox() {
  var del = confirm(confirm_del);
  if (del) {
    var url = getActionMappingURL("/note");
    var pars = "action=delalloutbox";

    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: delAllOutBoxComplete});
  }
  else {
    return false;
  }
}

function delAllOutBoxComplete(res) {
	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	if (codeid == "0") {
  		refreshBoxNumToZero("outbox");
  		loadNoteOutbox();
  	}
}

function refreshBoxNumToZero(boxName) {
  var num_span;
  if (boxName == "inbox") {
    num_span = document.getElementById("inboxNumDiv");
  }
  else {
    num_span = document.getElementById("outboxNumDiv");
  }
  num_span.innerHTML = "0";
}

function checkAll() {
  var ca = document.getElementById("checkall");
  var ids = document.getElementsByName("ids");
  for (var i = 0; i < ids.length; i++) {
      ids[i].checked = ca.checked;
  }
}

function noteOpInBox() {
  var noteOpSelectObj = document.getElementById("noteOp");

  if (noteOpSelectObj.options[noteOpSelectObj.selectedIndex].value == "1") {
  deleteIdsInboxNote();
  }
  //if (noteOpSelectObj.options[noteOpSelectObj.selectedIndex].value == "2") {

  //}
}

function deleteIdsInboxNote() {
  var del = confirm(confirm_del);
  if (del) {
    var pageNum = document.getElementById("cpage").innerHTML;
    var ids = document.getElementsByName("ids");
    var noteNum = 0;

    var data = "";
    for (var i = 0; i < ids.length; i++) {
      if (ids[i].checked) {
        data += "&ids=";
        data += ids[i].value;
        noteNum++;
      }
    }
    if (noteNum > 0) {
    	var oNoteDelIdsInboxAjax = new NoteDelIdsInboxAjax(pageNum,noteNum,data);
    	oNoteDelIdsInboxAjax.dels();
    }
    else {
    return false;
    }
  }
  else {
    return false;
  }
}

var NoteDelIdsInboxAjax = Class.create();

NoteDelIdsInboxAjax.prototype = {
  initialize: function(pageNum,noteNum,data) {
  	this.pageNum = pageNum;
  	this.noteNum = noteNum;
  	this.data = data;
  },

  dels: function() {

    var url = getActionMappingURL("/note");
    var pars = "action=delidsinbox&ajax=xml" + this.data;
    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.delsCompleted.bind(this)});
  },

  delsCompleted: function(res) {
  	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		refreshBoxNum("inbox",-this.noteNum);
  		var url = getActionMappingURL("/note?action=inbox&ajax=shtml&page=" + this.pageNum);
  		loadNoteInboxUrl(url);
  	}
  }
};


function noteOpOutBox() {
  var noteOpSelectObj = document.getElementById("noteOp");

  if (noteOpSelectObj.options[noteOpSelectObj.selectedIndex].value == "1") {
  deleteIdsOutboxNote();
  }
  //if (noteOpSelectObj.options[noteOpSelectObj.selectedIndex].value == "2") {

  //}
}

function deleteIdsOutboxNote() {
  var del = confirm(confirm_del);
  if (del) {
    var pageNum = document.getElementById("cpage").innerHTML;
    var ids = document.getElementsByName("ids");
    var noteNum = 0;

    var data = "";
    for (var i = 0; i < ids.length; i++) {
      if (ids[i].checked) {
        data += "&ids=";
        data += ids[i].value;
        noteNum++;
      }
    }
    if (noteNum > 0) {
    	var oNoteDelIdsOutboxAjax = new NoteDelIdsOutboxAjax(pageNum,noteNum,data);
    	oNoteDelIdsOutboxAjax.dels();
    }
    else {
    return false;
    }
  }
  else {
    return false;
  }
}

var NoteDelIdsOutboxAjax = Class.create();

NoteDelIdsOutboxAjax.prototype = {
  initialize: function(pageNum,noteNum,data) {
  	this.pageNum = pageNum;
  	this.noteNum = noteNum;
  	this.data = data;
  },

  dels: function() {

    var url = getActionMappingURL("/note");
    var pars = "action=delidsoutbox&ajax=xml" + this.data;
    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.delsCompleted.bind(this)});
  },

  delsCompleted: function(res) {
  	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		refreshBoxNum("outbox",-this.noteNum);
  		var url = getActionMappingURL("/note?action=outbox&ajax=shtml&page=" + this.pageNum);
  		loadNoteOutboxUrl(url);
  	}
  }
};

//-->
