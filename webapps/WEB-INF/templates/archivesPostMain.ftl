<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>帖子存档--${boardName}(${month})</title>
<link href="../../../css/archiveslist.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="forums">
<table cellpadding="0" cellspacing="0">
  <caption>${month}</caption>
  <tr class="trt">
	  <th class="name" scope="col">主题</th>
	  <th scope="col">回复</th>
	  <th scope="col">作者</th>
	  <th scope="col">发帖时间</th>
  </tr>
  <#list mainlist as fm>
  <tr>
    <td class="name1"><a href="${fm["postdir"]}${fm["id"]}.html" target="_blank">${fm["title"]}</a></td>
	<td>[+${fm["renum"]}]</td>
    <td>${fm["username"]}</td>
    <td>${fm["postTime"]}</td>
  </tr>
  </#list>
  <tr class="trp">
    <td colspan="4" class="name">分页:${pagebreak}</td>
  </tr>
</table>
</div>
</body>
</html>
