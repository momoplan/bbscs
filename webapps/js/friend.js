function loadFriendList() {
  hiddenElement("addfriend");
  $('tab1').className = "f_tabClass1";
  $('tab2').className = "f_tabClass2";
  $('friendlist').innerHTML = pageLoading;

  var url = getActionMappingURL("/friendSet");
  var pars = "action=flist&ajax=shtml&isBlack=0";

  var myAjax = new Ajax.Updater("friendlist", url, {method: 'get', parameters: pars});
}

function loadBlackUserList() {
  hiddenElement("addfriend");
  $('tab1').className = "f_tabClass2";
  $('tab2').className = "f_tabClass1";
  $('friendlist').innerHTML = pageLoading;

  var url = getActionMappingURL("/friendSet");
  var pars = "action=flist&ajax=shtml&isBlack=1";

  var myAjax = new Ajax.Updater("friendlist", url, {method: 'get', parameters: pars});
}

function f_loadPageOK(responseText) {
  $('friendlist').innerHTML = responseText;
}

function friendNew(isBlack) {
  Element.show("addfriend");
  $('addfriend').innerHTML = pageLoading;

  var url = getActionMappingURL("/friendSet");
  var pars = "action=add&ajax=shtml&isBlack=" + isBlack;

  var myAjax = new Ajax.Updater("addfriend", url, {method: 'get', parameters: pars});
}

function friendNewPage(resText) {
  $('addfriend').innerHTML = resText;
}

function closeFriendNewPage() {
  $('addfriend').innerHTML = "";
  Element.hide("addfriend");
}

var FriendAddAjax = Class.create();

FriendAddAjax.prototype = {
  initialize: function(isBlack) {
    this.isBlack = isBlack;
  },

  addFriend: function() {
    showExeMsg();
    var url = getActionMappingURL("/friendSet");
    var pars = "action=addsave&ajax=xml&friendName="+$('friendName').value+"&friendComment="
    + encodeURIComponent($('friendComment').value) + "&isBlack="+this.isBlack
    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.addFriendCompleted.bind(this)});
  },

  addFriendCompleted: function(res) {

    resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();

    hiddenExeMsg();
    alert(jsonMsgObj.getMessage());
    if (codeid == "0") {
      closeFriendNewPage();
      if (this.isBlack == "0") {
        loadFriendList();
      }
      if (this.isBlack == "1") {
        loadBlackUserList();
      }
    }
  }
};

function friendAdd() {
  var isBlack = $('isBlack').value;
  var oFriendAddAjax = new FriendAddAjax(isBlack);
  oFriendAddAjax.addFriend();
}

function handleEnter (field, event) {
  var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
  if (keyCode == 13) {
    return false;
  }
  return true;
}

var FriendDelAjax = Class.create();

FriendDelAjax.prototype = {
  initialize: function(id,isBlack) {
    this.id = id;
    this.isBlack = isBlack;
  },

  delFriend: function() {
    showExeMsg();
    var url = getActionMappingURL("/friendSet");
    var pars = "action=del&ajax=xml&id=" + this.id;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.delFriendCompleted.bind(this)});
  },

  delFriendCompleted: function(res) {
    resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();

    hiddenExeMsg();
    alert(jsonMsgObj.getMessage());
    if (codeid == "0") {
      if (this.isBlack == "0") {
        loadFriendList();
      }
      if (this.isBlack == "1") {
        loadBlackUserList();
      }
    }
  }
};

function friendDel(id,isBlack) {
  var del = confirm(confirm_del);
  if (del) {
    var oFriendDelAjax = new FriendDelAjax(id,isBlack);
    oFriendDelAjax.delFriend();
  }
  else {
    return false;
  }
}