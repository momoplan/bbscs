<!--
function loadPage() {
  loadElitePage();
  //loadFlistPage();
}

function loadElitePage() {
  removeEliteList();
  removeEliteDir();

  var url = getActionMappingURL("/refine");
  var pars = "action=showelite&ajax=xml&bid="+bid+"&pid="+pid;

  var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: showEliteListJson});
}

function removeEliteList() {
  while ($('eliteList').hasChildNodes()) {
    var nowelites = $('eliteList').childNodes;
    //alert(nowelites.length);
    for (var i = 0; i < nowelites.length; i++) {
      //alert(i + " " + nowelites[i]);
      $('eliteList').removeChild(nowelites[i]);
    }
  }
}

function removeEliteDir() {
  while ($('eliteDir').hasChildNodes()) {
    var noweliteDirs = $('eliteDir').childNodes;
    for (var i = 0; i < noweliteDirs.length; i++) {
      $('eliteDir').removeChild(noweliteDirs[i]);
    }
  }
}

function showEliteListJson(res) {
	var resText = res.responseText;
	var jsonObj = eval('(' + resText + ')');
	//alert(jsonObj);
	//alert(jsonObj.forum);
	//jsonObj.forum.each(function(f){
	//	alert(f.title);
	//})
	var dirs = jsonObj.dir;
	for (var i = 0; i < dirs.length; i++) {
		var row = document.createElement("tr");
		row.setAttribute("id", "tre_"+dirs[i].id);

		var cell = document.createElement("td");
		var msgDiv = document.createElement("div");
		msgDiv.innerHTML = "<img src=\"images/dir.gif\" />";
		cell.appendChild(msgDiv);
		cell.className = "bgColor4";
		row.appendChild(cell);

		cell = document.createElement("td");
		msgDiv = document.createElement("div");
		//var eliteId = dirs[i].id;
		//var eliteName = dirs[i].eliteName;
		//msgDiv.innerHTML = "<a href=\""+getActionMappingURL("/refine?action=index&bid="+bid+"&pid="+eliteId)+"\">" + eliteName + "</a>";

		var eliteId = dirs[i].id;
		msgDiv.setAttribute("id","e"+eliteId);
		var eliteName = dirs[i].eliteName;
		msgDiv.innerHTML = "<a href=\""+getActionMappingURL("/refine?action=manage&bid="+bid+"&pid="+eliteId)+"\">"
		+ eliteName + "</a> [<a href=\"javascript:;\" onclick=\"eidtElite('"+eliteId+"');\">修改</a>]"
		+ " [<a href=\""+getActionMappingURL("/refine?action=deldir&bid="+bid+"&eliteId="+eliteId)+"\" onclick=\"return delEliteDir();\">删除</a>]";

		msgDiv.className = "font1";
		cell.appendChild(msgDiv);
		cell.className = "bgColor2";
		row.appendChild(cell);

		cell = document.createElement("td");
		msgDiv = document.createElement("div");
		msgDiv.setAttribute("align","center");
		msgDiv.innerHTML = "";
		cell.appendChild(msgDiv);
		cell.className = "bgColor4";
		row.appendChild(cell);

		cell = document.createElement("td");
		msgDiv = document.createElement("div");
		msgDiv.setAttribute("align","center");
		msgDiv.innerHTML = dirs[i].createUser;
		cell.appendChild(msgDiv);
		cell.className = "bgColor2";
		row.appendChild(cell);

		var d = new Date();
		d.setTime(dirs[i].eliteTime);

		cell = document.createElement("td");
		msgDiv = document.createElement("div");
		msgDiv.setAttribute("align","center");
		msgDiv.innerHTML = d.toLocaleString();
		cell.appendChild(msgDiv);
		cell.className = "bgColor4";
		row.appendChild(cell);

		$('eliteList').appendChild(row);

		var dirOption = document.createElement("option");
		dirOption.setAttribute("id","dirId_" + eliteId);
		dirOption.setAttribute("value",eliteId);
		dirOption.appendChild(document.createTextNode(eliteName));
		$('eliteDir').appendChild(dirOption);

	}
	var forums = jsonObj.forum;
	//alert(forums);
	for (var i = 0; i < forums.length; i++) {
		var row = document.createElement("tr");
		row.setAttribute("id", "trf_"+forums[i].id);

		var cell = document.createElement("td");
		var msgDiv = document.createElement("div");
		msgDiv.innerHTML = "<img src=\"images/file.gif\" /><input type=\"checkbox\" name=\"postIds\" value=\""+forums[i].id+"\" />";
		cell.appendChild(msgDiv);
		cell.className = "bgColor4";
		row.appendChild(cell);

		cell = document.createElement("td");
		msgDiv = document.createElement("div");
		var mainId = forums[i].mainID;
		var title = forums[i].title;
		msgDiv.innerHTML = "<a href=\""+getActionMappingURL("/read?action=elite&id=" + mainId + "&bid=" + bid + "&eliteId=" + pid + "&fcpage=1&fcaction=index")+"\">" + title+"</a>";
		msgDiv.className = "font1";
		cell.appendChild(msgDiv);
		cell.className = "bgColor2";
		row.appendChild(cell);

		cell = document.createElement("td");
		msgDiv = document.createElement("div");
		msgDiv.setAttribute("align","center");
		msgDiv.innerHTML = forums[i].userName;
		cell.appendChild(msgDiv);
		cell.className = "bgColor4";
		row.appendChild(cell);

		cell = document.createElement("td");
		msgDiv = document.createElement("div");
		msgDiv.setAttribute("align","center");
		msgDiv.innerHTML = forums[i].doEliteName;
		cell.appendChild(msgDiv);
		cell.className = "bgColor2";
		row.appendChild(cell);

		var d = new Date();
		d.setTime(forums[i].doEliteTime);

		cell = document.createElement("td");
		msgDiv = document.createElement("div");
		msgDiv.setAttribute("align","center");
		msgDiv.innerHTML = d.toLocaleString();
		cell.appendChild(msgDiv);
		cell.className = "bgColor4";
		row.appendChild(cell);

		$('forumList').appendChild(row);
	}

}

function eidtElite(eliteId) {

  var oEidtEliteAjax = new EidtEliteAjax(bid,eliteId);
  oEidtEliteAjax.edit();
}

var EidtEliteAjax = Class.create();

EidtEliteAjax.prototype = {
  initialize: function(bid,eliteId) {
  	this.bid = bid;
  	this.eliteId = eliteId;
  },

  edit: function() {
    var url = getActionMappingURL("/refine");
    var pars = "action=edit&ajax=xml&bid="+this.bid+"&eliteId="+this.eliteId;

    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.editCompleted.bind(this)});
  },

  editCompleted: function(res) {
  	resText = res.responseText;
  	var jsonObj = eval('(' + resText + ')');

	var codeid = jsonObj.codeid;
	var message = jsonObj.message;

	if (codeid == "0") {
		var elite = jsonObj.elite;
		var eliteName = elite.eliteName;
		var orders = elite.orders;

		$('e'+this.eliteId).innerHTML = "<input id=\"eliteName"+this.eliteId+"\" name=\"eliteName"+this.eliteId+"\" type=\"text\" class=\"input2\" size=\"15\" value=\""+eliteName+"\" /> "+
		"序 <input name=\"orders"+this.eliteId+"\" type=\"text\" class=\"input2\" size=\"3\" value=\""+orders+"\" /> <input name=\"editEliteButton\" onclick=\"eidtEliteDo('"+this.eliteId+"');\" type=\"button\" class=\"button1\" value=\"修改\" />";
	}
	else {
		alert(message);
	}

  }
};

function eidtEliteDo(eliteId) {
  var url = getActionMappingURL("/refine");
  var eliteName = encodeURIComponent($('eliteName'+eliteId).value);
  var orders = $('orders'+eliteId).value;
  var pars = "action=editdo&ajax=xml&bid="+bid+"&eliteId="+eliteId+"&eliteName="+eliteName+"&orders="+orders;

  var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: eidtEliteDoComplete});
}

function eidtEliteDoComplete(res) {
	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  if (codeid == "0") {
    //resXML = "";
    loadElitePage();
  }
}


function addDir() {
  if ($('eliteName').value == "") {
    alert("请填写要新建的目录名！");
    return false;
  }
  var url = getActionMappingURL("/refine");
  var pars = "action=adddir&ajax=xml&bid="+bid+"&pid="+pid+"&eliteName="+encodeURIComponent($('eliteName').value);

  var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: addDirComplete});
}

function addDirComplete(res) {
	var resText = res.responseText;
	var jsonObj = eval('(' + resText + ')');

	var codeid = jsonObj.codeid;
	var message = jsonObj.message;
	alert(message);
	if (codeid == "0") {
		var elite = jsonObj.elite;
		//alert(elite.id);
		var row = document.createElement("tr");
		row.setAttribute("id", "tre_"+elite.id);

		var cell = document.createElement("td");
		var msgDiv = document.createElement("div");
		msgDiv.innerHTML = "<img src=\"images/dir.gif\" />";
		cell.appendChild(msgDiv);
		cell.className = "bgColor4";
		row.appendChild(cell);

		cell = document.createElement("td");
		msgDiv = document.createElement("div");
		var eliteId = elite.id;
		var eliteName = elite.eliteName;
		msgDiv.innerHTML = "<a href=\""+getActionMappingURL("/refine?action=manage&bid="+bid+"&pid="+eliteId)+"\">" + eliteName + "</a>";
		msgDiv.className = "font1";
		cell.appendChild(msgDiv);
		cell.className = "bgColor2";
		row.appendChild(cell);

		cell = document.createElement("td");
		msgDiv = document.createElement("div");
		msgDiv.setAttribute("align","center");
		msgDiv.innerHTML = "";
		cell.appendChild(msgDiv);
		cell.className = "bgColor4";
		row.appendChild(cell);

		cell = document.createElement("td");
		msgDiv = document.createElement("div");
		msgDiv.setAttribute("align","center");
		msgDiv.innerHTML = elite.createUser;
		cell.appendChild(msgDiv);
		cell.className = "bgColor2";
		row.appendChild(cell);

		var d = new Date();
		d.setTime(elite.eliteTime);

		cell = document.createElement("td");
		msgDiv = document.createElement("div");
		msgDiv.setAttribute("align","center");
		msgDiv.innerHTML = d.toLocaleString();
		cell.appendChild(msgDiv);
		cell.className = "bgColor4";
		row.appendChild(cell);

		$('eliteList').appendChild(row);

		var dirOption = document.createElement("option");
		dirOption.setAttribute("id","dirId_" + eliteId);
		dirOption.setAttribute("value",eliteId);
		dirOption.appendChild(document.createTextNode(eliteName));
		$('eliteDir').appendChild(dirOption);
	}
}

function intoDir() {
  var c_into = confirm("确认要转入吗?");
  if (c_into) {
    var ids = document.getElementsByName("postIds");
    var idsArray = new Array();
    var num = 0;
    var tmpData = "";
    for (var i = 0; i < ids.length; i++) {
      if (ids[i].checked) {
        //idsArray.push(ids[i].value);
        idsArray[i] = ids[i].value;
        tmpData += "&postIds=";
        tmpData += ids[i].value;
        num++;
      }
    }
    if (num == 0) {
      alert("请选择要转入的帖子！");
      return false;
    }
    var eliteDirSelectObj = document.getElementById("eliteDir");

    if (eliteDirSelectObj.options[eliteDirSelectObj.selectedIndex].value == "") {
      alert("请选择要转入的目录或您还没有建立子目录！");
      return false;
    }

    var oIntoDirAjax = new IntoDirAjax(bid,idsArray,eliteDirSelectObj.options[eliteDirSelectObj.selectedIndex].value,tmpData);
    oIntoDirAjax.intodir();

  }
  else {
    return false;
  }
}

var IntoDirAjax = Class.create();

IntoDirAjax.prototype = {
  initialize: function(bid,idsArray,eliteDir,data) {
  	this.bid = bid;
  	this.idsArray = idsArray;
  	this.eliteDir = eliteDir;
  	this.data = data;
  },

  intodir: function() {
    var url = getActionMappingURL("/refine");
    var pars = "action=intodir&ajax=xml&bid="+this.bid+"&eliteDir="+this.eliteDir+this.data;

    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.intodirCompleted.bind(this)});
  },

  intodirCompleted: function(res) {
  	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	//alert(this.idsArray.length);
  	if (codeid == "0") {

  		this.idsArray.each(function(fid){
  			//alert(fid);
  			var rowToDelete = $('trf_' + fid);
  			$('forumList').removeChild(rowToDelete);
		})
  	}
  }
};

function dels() {
  var del = confirm(confirm_del);
  if (del) {
    var ids = document.getElementsByName("postIds");
    var idsArray = new Array();
    var num = 0;
    var tmpData = "";
    for (var i = 0; i < ids.length; i++) {
      if (ids[i].checked) {
        idsArray.push(ids[i].value);
        tmpData += "&postIds=";
        tmpData += ids[i].value;
        num++;
      }
    }
    if (num == 0) {
      alert("请选择要删除的帖子！");
      return false;
    }

    var oDelAjax = new DelAjax(bid,idsArray,tmpData);
    oDelAjax.del();
  }
  else {
    return false;
  }
}

var DelAjax = Class.create();

DelAjax.prototype = {
  initialize: function(bid,idsArray,data) {
  	this.bid = bid;
  	this.idsArray = idsArray;
  	this.data = data;
  },

  del: function() {
    var url = getActionMappingURL("/refine");
    var pars = "action=del&ajax=xml&bid="+this.bid+this.data;

    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.delCompleted.bind(this)});
  },

  delCompleted: function(res) {
  	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	//alert(this.idsArray.length);
  	if (codeid == "0") {

  		this.idsArray.each(function(fid){
  			//alert(fid);
  			var rowToDelete = $('trf_' + fid);
  			$('forumList').removeChild(rowToDelete);
		})
  	}
  }
};

function outDir() {
  var del = confirm("确认要转出吗?(注意:如果这些精华已经在根目录,则将被删除!)");
  if (del) {
    var ids = document.getElementsByName("postIds");
    var idsArray = new Array();
    var num = 0;
    var tmpData = "";
    for (var i = 0; i < ids.length; i++) {
      if (ids[i].checked) {
        idsArray.push(ids[i].value);
        tmpData += "&postIds=";
        tmpData += ids[i].value;
        num++;
      }
    }
    if (num == 0) {
      alert("请选择要转出的帖子！");
      return false;
    }

    var oOutAjax = new OutAjax(bid,pid,idsArray,tmpData);
    oOutAjax.out();
  }
  else {
    return false;
  }
}

var OutAjax = Class.create();

OutAjax.prototype = {
  initialize: function(bid,pid,idsArray,data) {
  	this.bid = bid;
  	this.pid = pid;
  	this.idsArray = idsArray;
  	this.data = data;
  },

  out: function() {
    var url = getActionMappingURL("/refine");
    var pars = "action=outdir&ajax=xml&bid="+this.bid+"&pid="+this.pid+this.data;

    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.outCompleted.bind(this)});
  },

  outCompleted: function(res) {
  	resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	//alert(this.idsArray.length);
  	if (codeid == "0") {

  		this.idsArray.each(function(fid){
  			//alert(fid);
  			var rowToDelete = $('trf_' + fid);
  			$('forumList').removeChild(rowToDelete);
		})
  	}
  }
};

function delEliteDir(eliteid) {
  var del = confirm(confirm_del);
  if (!del) {
    return false;
  }
}
//-->
