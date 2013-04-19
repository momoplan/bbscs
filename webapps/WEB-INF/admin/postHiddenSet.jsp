<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>隐藏帖子内容功能管理</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<p>&nbsp;</p>
<s:form action="adminPostHiddenSet">
<s:hidden name="action"></s:hidden>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2"><strong>隐藏帖子内容功能管理</strong></td>
          </tr>

        <tr>
        <td colspan="2">
        <s:actionerror theme="bbscs0"/>
        </td>
      </tr>
      <tr>
        <td colspan="2">
        <s:actionmessage theme="bbscs0"/>
        </td>
      </tr>

        <tr>
          <td colspan="2" class="td1"><strong>允许使用的隐藏帖类型</strong></td>
          </tr>
        <tr>
          <td width="60%" valign="top">选择允许的隐藏帖类型，如果全部不选，则完全关闭隐藏帖插件。</td>
          <td width="40%">
            <s:checkbox name="postHiddenTypeMoney" fieldValue="true"></s:checkbox>
            金钱购买帖<br/>
            <s:checkbox name="postHiddenTypeRe" fieldValue="true"></s:checkbox>
            回复帖<br/>
            <s:checkbox name="postHiddenTypeArtNum" fieldValue="true"></s:checkbox>
            资历帖(根据帖子数隐藏)
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>设置允许金钱出售帖金额列表</strong></td>
          </tr>
        <tr>
          <td>请在这里设置您允许用户发表金钱出售帖时在下拉列表框中选择的收费的金额，用逗号隔开。设置示例: 10,20,30,40</td>
          <td valign="top">
          <s:textfield id="postPriceList" name="postPriceList" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>金钱出售帖税率</strong></td>
          </tr>
        <tr>
          <td>在用户购买金钱帖时，金钱会转移给帖子发表人，请在这里填上转移的税率。“0”表示不扣税；“1”表示100%税率，即金钱不转移给发表人；“0.1”表示10%的税率，即用户购买的90%金钱均会转移给发表人。</td>
          <td valign="top">
            <s:textfield id="postPriceTax" name="postPriceTax" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2"><div align="center">
            <s:submit cssClass="button2" value="保存"></s:submit>
          </div></td>
          </tr>
      </table></td>
    </tr>
  </table>
</s:form>
<p>&nbsp;</p>
</body>
</html>