function loadBookMarkListPage() {
  $('bookMarkListDiv').innerHTML = pageLoadingCenter;

  var urls = getActionMappingURL("/bookMark");
  var pars = "action=list&ajax=shtml";

  var myAjax = new Ajax.Updater("bookMarkListDiv", urls, {method: 'get', parameters: pars});
}

function loadBookMarkListPageUrl(url) {
  $('bookMarkListDiv').innerHTML = pageLoadingCenter;

  var urls = getActionName(url);
  var pars = getActionPars(url);

  var myAjax = new Ajax.Updater("bookMarkListDiv", urls, {method: 'get', parameters: pars});
}


function loadBookMarkAddPage() {
  displayElement("addEditBookMarkDiv");
  $('addEditBookMarkDiv').innerHTML = pageLoadingCenter;

  var urls = getActionMappingURL("/bookMark");
  var pars = "action=add&ajax=shtml";

  var myAjax = new Ajax.Updater("addEditBookMarkDiv", urls, {method: 'get', parameters: pars});
}

function closeAddEditBookMark() {
  $('addEditBookMarkDiv').innerHTML = "";
   hiddenElement("addEditBookMarkDiv");
}

function bookMarkAdd() {
  var url = getActionMappingURL("/bookMark");
  var isShare = 0;
  if ($('isShare').checked) {
    isShare = 1;
  }
  var pars = "action=addsave&ajax=xml&bookMarkName=" + encodeURIComponent($('bookMarkName').value) + "&alt="
  + encodeURIComponent($('alt').value) + "&url=" + encodeURIComponent($('url').value) + "&isShare=" + isShare;

  var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: bookMarkAddComplete});
}

function bookMarkAddComplete(res) {
	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		closeAddEditBookMark();
  		loadBookMarkListPage();
  	}
}

function loadBookMarkEditPage(id,pageNum) {
  displayElement("addEditBookMarkDiv");
  $('addEditBookMarkDiv').innerHTML = pageLoadingCenter;
  var url = getActionMappingURL("/bookMark");
  var pars = "action=edit&ajax=shtml&id=" + id + "&page=" + pageNum;

  var myAjax = new Ajax.Updater("addEditBookMarkDiv", url, {method: 'get', parameters: pars});
}


function bookMarkEdit() {
  var oBookMarkEditAjax = new BookMarkEditAjax($('pageNum').value);
  oBookMarkEditAjax.edit();
}

var BookMarkEditAjax = Class.create();

BookMarkEditAjax.prototype = {
  initialize: function(pageNum) {
    this.pageNum = pageNum;
  },

  edit: function() {
    var url = getActionMappingURL("/bookMark");

    var isShare = 0;
    if ($('isShare').checked) {
    	isShare = 1;
    }
    var pars = "action=editdo&ajax=xml&bookMarkName=" + encodeURIComponent($('bookMarkName').value) + "&alt="
    + encodeURIComponent($('alt').value) + "&url=" + encodeURIComponent($('url').value) + "&isShare=" + isShare
    + "&id=" + $('id').value;

    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.editCompleted.bind(this)});
  },

  editCompleted: function(res) {
  	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		closeAddEditBookMark();
  		var url = getActionMappingURL("/bookMark?action=list&ajax=shtml&page=" + this.pageNum);
  		loadBookMarkListPageUrl(url);
  	}
  }
};


function bookMarkShare(id,isShare,pageNum) {
  var bmsharecomfirm;
  if (isShare == "1") {
    bmsharecomfirm = bookmarkshare;
  }
  else {
    bmsharecomfirm = bookmarkunshare;
  }
  var del = confirm(bmsharecomfirm);
  if (del) {
    var oBookMarkShareAjax = new BookMarkShareAjax(id,isShare,pageNum);
    oBookMarkShareAjax.share();

  }
  else {
    return false;
  }
}

var BookMarkShareAjax = Class.create();

BookMarkShareAjax.prototype = {
  initialize: function(id,isShare,pageNum) {
  	this.id = id;
  	this.isShare = isShare;
    this.pageNum = pageNum;
  },

  share: function() {
    var url = getActionMappingURL("/bookMark");
    var pars = "action=share&ajax=xml&id=" + this.id + "&isShare=" + this.isShare;

    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.shareCompleted.bind(this)});
  },

  shareCompleted: function(res) {
  	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		if (this.isShare == 0) {
  			$('shareDiv'+this.id).innerHTML = "<a href=\"javascript:;\" onclick=\"bookMarkShare('"+this.id+"','1','"+this.pageNum+"');\">共享</a>";
  		}
  		else {
  			$('shareDiv'+this.id).innerHTML = "<a href=\"javascript:;\" onclick=\"bookMarkShare('"+this.id+"','0','"+this.pageNum+"');\">取消共享</a>";
  		}
  	}
  }
};


function bookMarkDel(id,pageNum) {
  var del = confirm(confirm_del);
  if (del) {
    closeAddEditBookMark();
    var oBookMarkDelAjax = new BookMarkDelAjax(id,pageNum);
    oBookMarkDelAjax.del();
  }
  else {
    return false;
  }
}

var BookMarkDelAjax = Class.create();

BookMarkDelAjax.prototype = {
  initialize: function(id,pageNum) {
  	this.id = id;
    this.pageNum = pageNum;
  },

  del: function() {
    var url = getActionMappingURL("/bookMark");
    var pars = "action=del&ajax=xml&id=" + this.id;

    var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: this.delCompleted.bind(this)});
  },

  delCompleted: function(res) {
  	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		var url = getActionMappingURL("/bookMark?action=list&ajax=shtml&page=" + this.pageNum);
  		loadBookMarkListPageUrl(url);
  	}
  }
};
