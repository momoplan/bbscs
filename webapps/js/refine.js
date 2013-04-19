<!--
function loadPage() {
  loadElitePage();
  //loadFlistPage();
}

function loadElitePage() {
  var url = getActionMappingURL("/refine");
  var pars = "action=showelite&ajax=xml&bid="+bid+"&pid="+pid;

  var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: showEliteListJson});
}

function showEliteListJson(res) {
	var nowelites = $('eliteList').childNodes;
	for (var i = 0; i < nowelites.length; i++) {
		$('eliteList').removeChild(nowelites[i]);
	}
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
		var eliteId = dirs[i].id;
		var eliteName = dirs[i].eliteName;
		msgDiv.innerHTML = "<a href=\""+getActionMappingURL("/refine?action=index&bid="+bid+"&pid="+eliteId)+"\">" + eliteName + "</a>";
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
	}
	var forums = jsonObj.forum;
	//alert(forums);
	for (var i = 0; i < forums.length; i++) {
		var row = document.createElement("tr");
		row.setAttribute("id", "trf_"+forums[i].id);

		var cell = document.createElement("td");
		var msgDiv = document.createElement("div");
		msgDiv.innerHTML = "<img src=\"images/file.gif\" />";
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

		$('eliteList').appendChild(row);
	}

}

function loadFlistPage() {
  var url = getActionMappingURL("/refine?action=showflist&ajax=xml&bid="+bid+"&pid="+pid);
  var oBBSXml = new BBSXml();
  oBBSXml.GetUrlXml(url,showForumList);
}

function showEliteList(resXML) {
  var nowelites = $('eliteList').childNodes;
  for (var i = 0; i < nowelites.length; i++) {
    $('eliteList').removeChild(nowelites[i]);
  }
  var elites = resXML.getElementsByTagName("elite");
  for (var i = 0; i < elites.length; i++) {
    var row = document.createElement("tr");
    row.setAttribute("id", "etr_"+elites[i].childNodes[0].childNodes[0].nodeValue);

    var cell = document.createElement("td");
    var msgDiv = document.createElement("div");
    msgDiv.innerHTML = "<img src=\"images/dir.gif\" />";
    cell.appendChild(msgDiv);
    cell.className = "bgColor4";
    row.appendChild(cell);

    cell = document.createElement("td");
    msgDiv = document.createElement("div");
    var eliteId = elites[i].childNodes[0].childNodes[0].nodeValue;
    msgDiv.innerHTML = "<a href=\""+getActionMappingURL("/refine?action=index&bid="+bid+"&pid="+eliteId)+"\">" + elites[i].childNodes[1].childNodes[0].nodeValue + "</a>";
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
    msgDiv.innerHTML = elites[i].childNodes[3].childNodes[0].nodeValue;
    cell.appendChild(msgDiv);
    cell.className = "bgColor2";
    row.appendChild(cell);

    cell = document.createElement("td");
    msgDiv = document.createElement("div");
    msgDiv.setAttribute("align","center");
    msgDiv.innerHTML = elites[i].childNodes[4].childNodes[0].nodeValue;
    cell.appendChild(msgDiv);
    cell.className = "bgColor4";
    row.appendChild(cell);

    //row.appendChild(createCellWithHTML("<img src=\"images/dir.gif\" />"));
    //row.appendChild(createCellWithText(elites[i].childNodes[1].childNodes[0].nodeValue));
    //row.appendChild(createCellWithHTMLCenter(elites[i].childNodes[2].childNodes[0].nodeValue)));
    //row.appendChild(createCellWithHTMLCenter(elites[i].childNodes[3].childNodes[0].nodeValue)));
    //row.appendChild(createCellWithHTMLCenter(elites[i].childNodes[4].childNodes[0].nodeValue)));
    $('eliteList').appendChild(row);
  }
}

function showForumList(resXML) {
  var elites = resXML.getElementsByTagName("elite");
  for (var i = 0; i < elites.length; i++) {
    var row = document.createElement("tr");
    row.setAttribute("id", "ftr_"+elites[i].childNodes[0].childNodes[0].nodeValue);

    var cell = document.createElement("td");
    var msgDiv = document.createElement("div");
    msgDiv.innerHTML = "<img src=\"images/file.gif\" />";
    cell.appendChild(msgDiv);
    cell.className = "bgColor4";
    row.appendChild(cell);

    cell = document.createElement("td");
    msgDiv = document.createElement("div");
    var mainId = elites[i].childNodes[1].childNodes[0].nodeValue;
    msgDiv.innerHTML = "<a href=\""+getActionMappingURL("/read?action=elite&id=" + mainId + "&bid=" + bid + "&eliteId=" + pid + "&fcpage=1&fcaction=index")+"\">" + elites[i].childNodes[2].childNodes[0].nodeValue+"</a>";
    msgDiv.className = "font1";
    cell.appendChild(msgDiv);
    cell.className = "bgColor2";
    row.appendChild(cell);

    cell = document.createElement("td");
    msgDiv = document.createElement("div");
    msgDiv.setAttribute("align","center");
    msgDiv.innerHTML = elites[i].childNodes[3].childNodes[0].nodeValue;
    cell.appendChild(msgDiv);
    cell.className = "bgColor4";
    row.appendChild(cell);

    cell = document.createElement("td");
    msgDiv = document.createElement("div");
    msgDiv.setAttribute("align","center");
    msgDiv.innerHTML = elites[i].childNodes[4].childNodes[0].nodeValue;
    cell.appendChild(msgDiv);
    cell.className = "bgColor2";
    row.appendChild(cell);

    cell = document.createElement("td");
    msgDiv = document.createElement("div");
    msgDiv.setAttribute("align","center");
    msgDiv.innerHTML = elites[i].childNodes[5].childNodes[0].nodeValue;
    cell.appendChild(msgDiv);
    cell.className = "bgColor4";
    row.appendChild(cell);

    //row.appendChild(createCellWithHTML("<img src=\"images/file.gif\" />"));
    //row.appendChild(createCellWithText(elites[i].childNodes[1].childNodes[0].nodeValue));
    //row.appendChild(createCellWithHTMLCenter(elites[i].childNodes[2].childNodes[0].nodeValue)));
    //row.appendChild(createCellWithHTMLCenter(elites[i].childNodes[3].childNodes[0].nodeValue)));
    //row.appendChild(createCellWithHTMLCenter(elites[i].childNodes[4].childNodes[0].nodeValue)));
    $('forumList').appendChild(row);
  }
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

function createCellWithHTMLCenter(text) {
   var cell = document.createElement("td");
   var msgDiv = document.createElement("div");
   msgDiv.setAttribute("align","center");
   msgDiv.innerHTML = text;
   cell.appendChild(msgDiv);
   return cell;
}


//-->
