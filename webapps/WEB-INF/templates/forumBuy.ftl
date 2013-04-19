<form id="postForm" name="postForm" method="post" action="${actionUrl}">
<input name="action" type="hidden" value="buy" />
<input name="bid" id="bid" type="hidden" value="${bid}" />
<input name="id" id="id" type="hidden" value="${postid}" />
<input name="tagId" id="tagId" type="hidden" value="${tagId}" />
<div>${preview}</div>
<span class=font5>${buyMsg}</span>
<input type="Submit" name="SubmitBuy" class="button1" value="${iwantbuy}" />
</form>
