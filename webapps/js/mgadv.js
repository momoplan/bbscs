function forbidUser() {
	showExeMsg();
  var fuserName = $('forbidUserName').value;
  if (fuserName == "") {
    alert(enterusername);
    return false;
  }
  var ftype = getRadioValueByName("forbidType");
  var url = getActionMappingURL("/manageAdvance");
  var pars = "action=forbiduser&ajax=xml&bid="+bid+"&forbidUserName="+fuserName+"&forbidType="+ftype;

  var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: forbidUserComplete});
}

function forbidUserComplete(res) {
	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	hiddenExeMsg();
  	$('forbidMsg').innerHTML = jsonMsgObj.getMessage();
}

function saveBulletin() {
  displayElement("bulletinMsg");
  $('bulletinMsg').innerHTML = updating;
  var url = getActionMappingURL("/manageAdvance");
  var pars = "action=bulletin&ajax=xml&bid="+bid+"&bulletin="+encodeURIComponent($('bulletin').value);

  var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: saveBulletinComplete});
}

function saveBulletinComplete(res) {
	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	hiddenElement("bulletinMsg");
}

function addAuthUser() {
  var auserName = $('addAuthUserName').value;
  if (auserName == "") {
    alert(enterusername);
    return false;
  }
  var oAddAuthUserAjax = new AddAuthUserAjax(bid,auserName);
  oAddAuthUserAjax.add();
}

var AddAuthUserAjax = Class.create();

AddAuthUserAjax.prototype = {
  initialize: function(bid,userName) {
  	this.bid = bid;
    this.userName = userName;
  },

  add: function() {
    var url = getActionMappingURL("/manageAdvance");
    var pars = "action=addauth&ajax=xml&bid="+this.bid+"&addAuthUserName="+this.userName;
    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.addCompleted.bind(this)});
  },

  addCompleted: function(res) {
    var jsonMsgObj = new JsonMsgObj(res.responseText);
  	var codeid = jsonMsgObj.getCodeid();
    alert(jsonMsgObj.getMessage());
    if (codeid == "0") {
    	addAuthUserRow(this.userName);
    }
  }
};

function addAuthUserRow(userName) {
  var row = document.createElement("tr");
  row.setAttribute("id", "tr_"+userName);
  row.appendChild(createCellWithText(userName));
  row.appendChild(createCellWithHTML("[<a href=\"javascript:;\" onclick=\"delAuthUserInList('"+userName+"');\">删除</a>]"));
  $('authUserList').appendChild(row);
}

function createCellWithText(text) {
   var cell = document.createElement("td");
   cell.appendChild(document.createTextNode(text));
   return cell;
}

function createCellWithHTML(text) {
   var cell = document.createElement("td");
   var msgDiv = document.createElement("div");
   msgDiv.innerHTML = text;
   cell.appendChild(msgDiv);
   return cell;
}

function delAuthUser() {
  var auserName = $('delAuthUserName').value;
  if (auserName == "") {
    alert(enterusername);
    return false;
  }
  var del = confirm(confirm_del);
  if (del) {
    var duserName = $('delAuthUserName').value;
    if (duserName == "") {
      alert(enterusername);
      return false;
    }
    var oDelAuthUserAjax = new DelAuthUserAjax(bid,duserName);
    oDelAuthUserAjax.del();
  }
  else {
    return false;
  }
}

function delAuthUserInList(userName) {
  var del = confirm(confirm_del);
  if (del) {
    var oDelAuthUserAjax = new DelAuthUserAjax(bid,userName);
    oDelAuthUserAjax.del();
  }
  else {
    return false;
  }
}

var DelAuthUserAjax = Class.create();

DelAuthUserAjax.prototype = {
  initialize: function(bid,userName) {
  	this.bid = bid;
    this.userName = userName;
  },

  del: function() {
    var url = getActionMappingURL("/manageAdvance");
    var pars = "action=delauth&ajax=xml&bid="+this.bid+"&delAuthUserName="+this.userName;
    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.delCompleted.bind(this)});
  },

  delCompleted: function(res) {
    var jsonMsgObj = new JsonMsgObj(res.responseText);
  	var codeid = jsonMsgObj.getCodeid();
    alert(jsonMsgObj.getMessage());
    if (codeid == "0") {
    	delAuthUserRow(this.userName);
    }
  }
};

function delAuthUserRow(userName) {
  var rowToDelete = $('tr_'+userName);
  $('authUserList').removeChild(rowToDelete);
}