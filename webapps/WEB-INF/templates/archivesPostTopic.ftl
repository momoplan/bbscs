<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${topoctitle}</title>
<link href="../../../../../css/archivespost.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="topic">
<#list topiclist as fm>
<div class="postdiv" id="post${fm["id"]}">
  <div class="title" id="title${fm["id"]}">${fm["title"]}</div>
  <div class="author" id="author${fm["id"]}">作者:${fm["username"]} （发表时间:${fm["posttime"]}）</div>
  <div class="content" id="content${fm["id"]}">
  ${fm["content"]}
  </div>
</div>
</#list>
<!--<div align="center" class="postdiv">
<script type="text/javascript"><!--
google_ad_client = "pub-9505761087047507";
google_ad_width = 728;
google_ad_height = 90;
google_ad_format = "728x90_as";
google_ad_type = "text";
google_ad_channel = "";
</script>
<script type="text/javascript"
  src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
</div>-->
</div>
</body>
</html>