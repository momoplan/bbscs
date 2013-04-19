<!--
function loadSubsList(bid) {
  $('subsList').innerHTML = pageLoading;

  var url = getActionMappingURL("/subs");
  var pars = "action=list&ajax=shtml&bid="+bid;

  var myAjax = new Ajax.Updater("subsList", url, {method: 'get', parameters: pars});
}

function loadSubsListUrl(url) {
  $('subsList').innerHTML = pageLoading;

  var urls = getActionName(url);
  var pars = getActionPars(url);

  var myAjax = new Ajax.Updater("subsList", urls, {method: 'get', parameters: pars});
}

var CancleSubsAjax = Class.create();

CancleSubsAjax.prototype = {
  initialize: function(action,bid,id,cpage) {
  	this.action = action;
  	this.bid = bid;
  	this.id = id;
  	this.cpage = cpage;
  },

  cancle: function() {

    var url = getActionMappingURL("/subs");
    var pars = "ajax=xml&action=" + this.action + "&id=" + this.id + "&bid=" + this.bid;
    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.cancleCompleted.bind(this)});
  },

  cancleCompleted: function(res) {
  	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		if (this.action == "delemail") {
  			$('email' + this.id).innerHTML = "--";
  		}
  		if (this.action == "delmsg") {
  			$('msg' + this.id).innerHTML = "--";
  		}
  		if (this.action == "del") {
  			var url = getActionMappingURL("/subs?action=list&ajax=shtml&bid=" + this.bid + "&page=" + this.cpage);
  			loadSubsListUrl(url);
  		}
  	}
  	if (codeid == "1") {
  		var url = getActionMappingURL("/subs?action=list&ajax=shtml&bid=" + this.bid + "&page=" + this.cpage);
  		loadSubsListUrl(url);
  		}
  	}
};

function cancleSubs(action,bid,id,cpage) {
  var canc = confirm("你确认要取消订阅吗?");
  if (canc) {
    var oCancleSubsAjax = new CancleSubsAjax(action,bid,id,cpage);
    oCancleSubsAjax.cancle();
  }
  else {
    return false;
  }
}

//-->