<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>附件选项</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<p>&nbsp;</p>
<s:form action="adminAttachSet">
<s:hidden name="action"></s:hidden>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
          <tr>
            <td colspan="2"><strong>附件选项</strong></td>
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
            <td colspan="2" class="td1"><strong>每帖附件数</strong></td>
          </tr>
          <tr>
            <td width="60%">单个帖子可以包含的附件文件数目。设置为 0 不作限制。</td>
            <td width="40%">
              <s:textfield id="attachmentNum" name="attachmentNum" cssClass="input2" size="40"></s:textfield>
            </td>
          </tr>
          <tr>
            <td colspan="2" class="td1"><strong>允许删除超过编辑时间的附件</strong></td>
          </tr>
          <tr>
            <td>即使帖子的编辑时间超过了，仍然允许用户删除附件？</td>
            <td>
              <s:radio list="radioYesNoList" name="canDelAttachmentOverTime" listKey="key" listValue="value"></s:radio>
            </td>
          </tr>
          <tr>
            <td colspan="2" class="td1"><strong>允许用户删除关闭主题中的附件</strong></td>
          </tr>
          <tr>
            <td>允许用户从关闭的主题中删除附件？只有用户拥有编辑帖子的权限时此选项才有效。如果上面的选项设置为“否”那么此选项只在帖子编辑限时到达之前有效。</td>
            <td valign="top">
              <s:radio list="radioYesNoList" name="canDelAttachmentClosedPost" listKey="key" listValue="value"></s:radio>
            </td>
          </tr>
          <tr>
            <td colspan="2" class="td1"><strong>允许用户上传得文件类型</strong></td>
          </tr>
          <tr>
            <td valign="top">输入允许用户上传的文件类型的文件后缀，以逗号分开。</td>
            <td valign="top">
              <s:textarea id="attachFileType" name="attachFileType" cols="40" cssClass="textarea1" rows="5"></s:textarea>
            </td>
          </tr>
          <tr>
            <td colspan="2" class="td1"><strong>允许用户上传得文件大小</strong></td>
          </tr>
          <tr>
            <td valign="top">允许用户上传的文件大小，以K为单位。</td>
            <td valign="top">
              <s:textfield id="attachFileSize" name="attachFileSize" cssClass="input2" size="40"></s:textfield>
            </td>
          </tr>
          <tr>
            <td colspan="2" class="td1"><strong>生成图像缩略图</strong></td>
          </tr>
          <tr>
            <td>对用户上传的图片进行缩略，注意在Linux下运行时需要在启动参数中加入:<span class="bt">Djava.awt.headless=true</span></td>
            <td valign="top">
              <s:radio list="radioYesNoList" name="reduceAttachImg" listKey="key" listValue="value"></s:radio>
            </td>
          </tr>
          <tr>
            <td colspan="2" class="td1"><strong>附件图片查看</strong></td>
          </tr>
          <tr>
            <td>如果不允许缩略图生成，此设置不产生效果；如果允许缩略图生成，选择“否”的话，图片附件只显示为图片链接的文字，不显示图片；如果选择“是”，则依次显示所有附件图片。</td>
            <td valign="top">
              <s:radio list="radioYesNoList" name="viewAttachImg" listKey="key" listValue="value"></s:radio>
            </td>
          </tr>
          <tr>
            <td colspan="2" class="td1"><strong>图像缩略图尺寸</strong></td>
          </tr>
          <tr>
            <td>缩略图最大宽和高。图片将以长边为此数值缩放。</td>
            <td>
              <s:textfield id="reduceAttachImgSize" name="reduceAttachImgSize" cssClass="input2" size="40"></s:textfield>
            </td>
          </tr>
          <tr>
            <td colspan="2" class="td1"><strong>每行缩略图个数</strong></td>
          </tr>
          <tr>
            <td>帖子里面每行显示缩略图个数。不要超过您设置的每帖最大附件数。</td>
            <td>
              <s:textfield id="attachImgRow" name="attachImgRow" cssClass="input2" size="40"></s:textfield>
            </td>
          </tr>
          <tr>
            <td colspan="2" class="td1"><strong>生成图像缩略图的图片类型</strong></td>
          </tr>
          <tr>
            <td><p>输入需要进行缩略的图片类型的文件后缀，以逗号分开，注意有些图片类型可能不支持。</p>
            </td>
            <td valign="top">
              <s:textfield id="attachImgType" name="attachImgType" cssClass="input2" size="40"></s:textfield>
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