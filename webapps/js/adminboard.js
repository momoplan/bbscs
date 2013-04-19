function showBms(bid) {
  var bm_div = "bm" + bid;
  Element.show(bm_div);
  var url = getActionMappingURL("/adminBML");
  var pars = "ajax=shtml&bid="+bid;
  var myAjax = new Ajax.Updater(bm_div, url, {method: 'get', parameters: pars});
}

function closeBms(bid) {
  var bm_div = "bm" + bid;
  $(bm_div).innerHTML = "";
  Element.hide(bm_div);
}

function newBoardMasterLoad(bid) {
  var addbmdiv = "addbm";
  Element.show(addbmdiv);
  $(addbmdiv).innerHTML = "页面载入中...";
  var url = getActionMappingURL("/adminBmSet");
  var pars = "action=add&ajax=shtml&boardID=" + bid;

  var myAjax = new Ajax.Updater(addbmdiv, url, {method: 'get', parameters: pars});
}

function cloesBoardMasterPage() {
  $("addbm").innerHTML = "";
  hiddenElement("addbm");
}

function adminAddBm() {
  boardid = $('boardID').value;
  var oAddBoardMasterAjax = new AddBoardMasterAjax(boardid);
  oAddBoardMasterAjax.doAddBoardMaster();

}

var AddBoardMasterAjax = Class.create();

AddBoardMasterAjax.prototype = {
  initialize: function(bid) {
    this.bid = bid;
  },

  doAddBoardMaster: function() {
  	changeStyle("resultMegs","msg2");
  	$('resultMegs').innerHTML = "数据已经提交，请稍候...";
    var url = getActionMappingURL("/adminBmSet");
    var overChildPurviewValue = getRadioValueByName("overChildPurview");
    var isHiddenValue = getRadioValueByName("isHidden");
    var pars = "action=addsave&ajax=xml&userName="+$('userName').value+"&roleID="+$('roleID').value
    +"&overChildPurview="+overChildPurviewValue+"&isHidden="+isHiddenValue+"&boardID="+this.bid;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.addBoardMasterCompleted.bind(this)});
  },

  addBoardMasterCompleted: function(res) {
  	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	if (codeid == "0") {
  		changeStyle("resultMegs","msg3");
  		showBms(this.bid);
  	}
  	else {
  		changeStyle("resultMegs","errormsg");
  	}
  	$('resultMegs').innerHTML = jsonMsgObj.getMessage();
  }
};

function editBoardMasterLoad(bid,userName) {
  var addbmdiv = "addbm";
  Element.show(addbmdiv);
  $(addbmdiv).innerHTML = "页面载入中...";
  var url = getActionMappingURL("/adminBmSet");
  var pars = "action=edit&ajax=shtml&boardID=" + bid + "&userName=" + userName;
  var myAjax = new Ajax.Updater(addbmdiv, url, {method: 'get', parameters: pars});
}

function adminEditBm() {
  boardid = $('boardID').value;
  var oEidtBoardMasterAjax = new EidtBoardMasterAjax(boardid);
  oEidtBoardMasterAjax.doEditBoardMaster();
}

var EidtBoardMasterAjax = Class.create();

EidtBoardMasterAjax.prototype = {
  initialize: function(bid) {
    this.bid = bid;
  },

  doEditBoardMaster: function() {
  	changeStyle("resultMegs","msg2");
  	$('resultMegs').innerHTML = "数据已经提交，请稍候...";
    var url = getActionMappingURL("/adminBmSet");
    var overChildPurviewValue = getRadioValueByName("overChildPurview");
    var isHiddenValue = getRadioValueByName("isHidden");
    var pars = "action=editsave&ajax=xml&userName="+$('userName').value+"&roleID="+$('roleID').value
    +"&overChildPurview="+overChildPurviewValue+"&isHidden="+isHiddenValue+"&boardID="+this.bid;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.editBoardMasterCompleted.bind(this)});
  },

  editBoardMasterCompleted: function(res) {
  	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	if (codeid == "0") {
  		changeStyle("resultMegs","msg3");
  		showBms(this.bid);
  	}
  	else {
  		changeStyle("resultMegs","errormsg");
  	}
  	$('resultMegs').innerHTML = jsonMsgObj.getMessage();
  }
};

var AdminDelBmAjax = Class.create();

AdminDelBmAjax.prototype = {
  initialize: function(bid,userName) {
    this.bid = bid;
    this.userName = userName;
  },

  delBm: function() {
    var url = getActionMappingURL("/adminBmSet");
    var pars = "action=del&ajax=xml&boardID="+this.bid+"&userName="+this.userName
    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.delBmComplete.bind(this)});
  },

  delBmComplete: function(res) {
    resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
    if (codeid == "0") {
      showBms(this.bid);
    }
    else {
      alert("删除版主错误，请稍候再试！");
    }
  }
};

function adminDelBm(boardID,userName) {
  var oAdminDelBmAjax = new AdminDelBmAjax(boardID,userName);
  oAdminDelBmAjax.delBm();
}

function showTag(bid) {
  var btagdiv = "btag" + bid;
  Element.show(btagdiv);
  var url = getActionMappingURL("/adminBoardTag");
  var pars = 'action=list&ajax=shtml&bid=' + bid;
  var myAjax = new Ajax.Updater(btagdiv, url, {method: 'get', parameters: pars});
}

function closeTag(bid) {
  $("btag"+bid).innerHTML = "";
  Element.hide("btag"+bid);
}

function showAddTagPage(bid) {
  Element.show("addbm");
  var url = getActionMappingURL("/adminBoardTag");
  var pars = 'action=add&ajax=shtml&bid=' + bid;
  var myAjax = new Ajax.Updater("addbm", url, {method: 'get', parameters: pars});
}

function showEditTagPage(bid,tagid) {
  Element.show("addbm");
  var url = getActionMappingURL("/adminBoardTag");
  var pars = 'action=edit&ajax=shtml&bid=' + bid + "&id="+tagid;
  var myAjax = new Ajax.Updater("addbm", url, {method: 'get', parameters: pars});
}

var AddTagAjax = Class.create();

AddTagAjax.prototype = {
  initialize: function(bid) {
    this.bid = bid;
  },

  addTag: function() {
    changeStyle("resultMegs","msg2");
    $('resultMegs').innerHTML = "数据已经提交，请稍候...";
    var url = getActionMappingURL("/adminBoardTag");
    var pars = 'action=addsave&ajax=xml&bid=' + $('bid').value + '&tagName=' + encodeURIComponent($('tagName').value) + '&orders=' + $('orders').value;
    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.tagAddComplete.bind(this)});
  },

  tagAddComplete: function(res) {
    resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
    if (codeid == "0") {
      changeStyle("resultMegs","msg3");
      showTag(this.bid);
    }
    else {
      changeStyle("resultMegs","errormsg");
    }
    $('resultMegs').innerHTML = jsonMsgObj.getMessage();
  }
};

var EditTagAjax = Class.create();

EditTagAjax.prototype = {
  initialize: function(bid) {
    this.bid = bid;
  },

  editTag: function() {
    changeStyle("resultMegs","msg2");
    $('resultMegs').innerHTML = "数据已经提交，请稍候...";
    var url = getActionMappingURL("/adminBoardTag");
    var pars = 'action=editsave&ajax=xml&bid=' + $('bid').value + '&id='+ $('id').value + '&tagName=' + encodeURIComponent($('tagName').value) + '&orders=' + $('orders').value;
    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.tagEditComplete.bind(this)});
  },

  tagEditComplete: function(res) {
    resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();

    if (codeid == "0") {
      changeStyle("resultMegs","msg3");
      showTag(this.bid);
    }
    else {
      changeStyle("resultMegs","errormsg");
    }
    $('resultMegs').innerHTML = jsonMsgObj.getMessage();
  }
};

function addNewTag() {
  var bid = $("bid").value;
  var oAddTagAjax = new AddTagAjax(bid);
  oAddTagAjax.addTag();
}

function editaTag() {
  var bid = $("bid").value;
  var oEditTagAjax = new EditTagAjax(bid);
  oEditTagAjax.editTag();
}

function delTag(bid,id) {
  var ac=confirm("确认删除吗？");
  if (ac) {
    var oDelTagAjax = new DelTagAjax(bid,id);
    oDelTagAjax.delTag();
  }
  else {
    return false;
  }
}

var DelTagAjax = Class.create();

DelTagAjax.prototype = {
  initialize: function(bid,id) {
    this.bid = bid;
    this.id = id;
  },

  delTag: function() {
    var url = getActionMappingURL("/adminBoardTag");
    var pars = 'action=del&ajax=xml&bid=' + this.bid + '&id='+ this.id;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.tagDelComplete.bind(this)});
  },

  tagDelComplete: function(res) {
    resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
    if (codeid == "0") {
      alert(jsonMsgObj.getMessage());
      showTag(this.bid);
    }
    else {
      alert(jsonMsgObj.getMessage());
    }
  }
};


function showBgs(bid) {
  var bg_div = "bg" + bid;
  Element.show(bg_div);
  var url = getActionMappingURL("/adminBoardUg");
  var pars = "action=list&ajax=shtml&bid="+bid;
  var myAjax = new Ajax.Updater(bg_div, url, {method: 'get', parameters: pars});
}

function closeBgs(bid) {
  bg_div = "bg" + bid;
  $(bg_div).innerHTML = "";
  Element.hide(bg_div);
}

function boardPermissionLoad(bid,gid) {
  var addbmdiv = "addbm";
  Element.show(addbmdiv);
  $(addbmdiv).innerHTML = "页面载入中...";
  var url = getActionMappingURL("/adminBoardUg");
  var pars = "action=plist&ajax=shtml&bid=" + bid + "&gid=" + gid;
  var myAjax = new Ajax.Updater(addbmdiv, url, {method: 'get', parameters: pars});
}

function boardPermissionSave() {
  var url = getActionMappingURL("/adminBoardUg");
  var pars = "action=save&ajax=XML&bid="+$('bid').value+"&gid="+$('gid').value;
  var permissionValues = document.getElementsByName("permissions");
  for (var i = 0; i < permissionValues.length; i++) {
    if (permissionValues[i].checked) {
      pars = pars + "&permissions=" + permissionValues[i].value;
    }
  }
  var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: boardPermissionSaveOK});
}

function boardPermissionSaveOK(res) {
  resText = res.responseText;
  var jsonMsgObj = new JsonMsgObj(resText);
  var codeid = jsonMsgObj.getCodeid();
  alert(jsonMsgObj.getMessage());
  if (codeid == "0") {
    cloesBoardMasterPage();
  }
}

function comfirm_delboard(){
  var ac=confirm(confirm_del);
  if (ac==false)
  return false;
}