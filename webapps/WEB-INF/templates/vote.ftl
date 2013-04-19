<div id="pvote${postid}">
<form id="voteOptForm${postid}" name="voteOptForm${postid}" method="post" action="">
<input name="action" type="hidden" value="vote" />
<input name="postid${postid}" id="postid${postid}" type="hidden" value="${postid}" />
<input name="bid${postid}" id="bid${postid}" type="hidden" value="${bid}" />
<input name="voteid${postid}" id="voteid${postid}" type="hidden" value="${vote.id}" />
<input name="deadline${postid}" id="deadline${postid}" type="hidden" value="${vote.deadLine}" />
<input name="isM${postid}" id="isM${postid}" type="hidden" value="${vote.isM}" />
<#assign total = 0>
<#list vis as vi>
<#assign total = total + vi.itemValue>
</#list>
<#if total = 0>
<#assign total = 1>
</#if>
  <table width="100%" border="0" cellpadding="5" cellspacing="0">
    <#list vis as vi>
    <#if vote.isM = 0>
    <tr>
      <td width="40%">
        <input type="radio" name="voteitemid" value="${vi.id}" />
        ${vi.item}
      </td>
      <td width="60%">
        <img src="images/dot.gif" height="10" width="${vi.itemValue * 200 / total}"/> ${vi.itemValue}
      </td>
    </tr>
    <#else>
    <tr>
      <td width="40%">
        <input type="checkbox" name="voteitemid" value="${vi.id}" />
        ${vi.item}
      </td>
      <td width="60%">
        <img src="images/dot.gif" height="10" width="${vi.itemValue * 200 / total}"/> ${vi.itemValue}
      </td>
    </tr>
    </#if>
    </#list>
    <!--<tr>
      <td colspan="2">&nbsp;</td>
    </tr>-->
    <tr>
      <td colspan="2">
        <input type="button" name="Submit" class="button1" onclick="postVote('${postid}');" value="我要投票" />
      </td>
    </tr>
  </table>
</form>
</div>
${fdetail}
