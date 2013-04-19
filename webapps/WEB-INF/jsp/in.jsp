<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>

<div class="main_body1">
	<div style="line-height:30px; border:1px solid #e1e1e1; background:#f8f8f8; padding-left:10px">您所在的位置：<a href="http://www.ruyicai.com">如意彩</a> > <a href="http://bbs.ruyicai.com"> 如意论坛 </a></div>
</div>
<div class="main_body1">
	<div class="c_sy3g" style="padding:10px 10px 0px 10px; border:1px solid #FCC; overflow:hidden" >
		<div id="slide_div" class="c_slide" style="width:250px;float:left">
			<div id="focus">
				<ul>
					<s:iterator id="HaveAttachFile" value="%{HaveAttachFileForums}">
                        <tr>
                          <td>
                            <s:if test="%{urlRewrite}">
                            	<li>
	                            	<a href="read-topic-<s:property value="#HaveAttachFile.boardID"/>-<s:property value="#HaveAttachFile.mainID"/>-0-1-index-1.html" target="_blank">
	                            		<%-- <bbscs:forum forumValue="HaveAttachFile" type="detail"/> --%>
	                            		<img src='<s:property value="#HaveAttachFile.attachFileName.get(0)"/>' alt='<s:property value="#HaveAttachFile.title"/>' />
	                            	</a>
                            	</li>
                            </s:if>
							<s:else>
								<li>
									<s:url action="read?action=topic" id="posturl">
									<s:param name="bid" value="#HaveAttachFile.boardID"/>
									<s:param name="id" value="#HaveAttachFile.mainID"/>
									<s:param name="fcpage" value="1"/>
									<s:param name="fcaction" value="index"/>
									</s:url>
	                            	<a href="${posturl}" target="_blank">
	                            		<%-- <bbscs:forum forumValue="HaveAttachFile" type="detail"/> --%>
	                            		<img src='<s:property value="#HaveAttachFile.attachFileName"/>' alt='<s:property value="#HaveAttachFile.title"/>' />
	                            	</a>
                            	</li>
                            </s:else>
                          </td>
                        </tr>
                      </s:iterator>
				</ul>
			</div>
		</div>
		<div style="width:200px;padding-left:15px; float:left; overflow:hidden">
			<h3><s:text name="in.newart"/></h3>
			<table width="100%">
				<tbody>
					<s:iterator id="newf" value="%{newForums}">
                        <tr>
                          <td>
                            <s:if test="%{urlRewrite}">
								<a class="zt" href="read-topic-<s:property value="#newf.boardID"/>-<s:property value="#newf.mainID"/>-0-1-index-1.html" title='<s:property value="#newf.title"/>' target="_blank">
								<s:if test="#newf.title.length()>13">
									<s:property value="#newf.title.substring(0,13)"/>...
								</s:if>
								<s:else>
									<s:property value="#newf.title"/>
								</s:else>
								</a>
                            </s:if>
							<s:else>
								<s:url action="read?action=topic" id="posturl">
								<s:param name="bid" value="#newf.boardID"/>
								<s:param name="id" value="#newf.mainID"/>
								<s:param name="fcpage" value="1"/>
								<s:param name="fcaction" value="index"/>
								</s:url>
								<a class="zt" href="${posturl}" title='<s:property value="#newf.title"/>' target="_blank">
								<s:if test="#newf.title.length()>13">
									<s:property value="#newf.title.substring(0,13)"/>
								</s:if>
								<s:else>
									<s:property value="#newf.title"/>
								</s:else>
								</a>
                            </s:else>
                          </td>
                        </tr>
                      </s:iterator>
				</tbody>
			</table>
		</div>
		<div style="width:200px;padding-left:15px; float:left; overflow:hidden">
			<h3>最新回复</h3>
			<table width="100%">
				<tbody>
					<s:iterator id="LastPostTitle" value="%{LastPostTitleForums}">
						<tr>
							<td>
								<s:if test="%{urlRewrite}">
									<a class="zt" href="read-topic-<s:property value="#LastPostTitle.boardID"/>-<s:property value="#LastPostTitle.mainID"/>-0-1-index-1.html" title='<s:property value="#LastPostTitle.title"/>' target="_blank">
									<s:if test="#LastPostTitle.title.length()>13">
										<s:property value="#LastPostTitle.title.substring(0,13)"/>...
									</s:if>
									<s:else>
										<s:property value="#LastPostTitle.title"/>
									</s:else>
									</a>
	                            </s:if>
								<s:else>
									<s:url action="read?action=topic" id="posturl">
									<s:param name="bid" value="#LastPostTitle.boardID"/>
									<s:param name="id" value="#LastPostTitle.mainID"/>
									<s:param name="fcpage" value="1"/>
									<s:param name="fcaction" value="index"/>
									</s:url>
									<a class="zt" href="${posturl}" title='<s:property value="#LastPostTitle.title"/>' target="_blank">
									<s:if test="#LastPostTitle.title.length()>13">
										<s:property value="#LastPostTitle.title.substring(0,13)"/>
									</s:if>
									<s:else>
										<s:property value="#LastPostTitle.title"/>
									</s:else>
									</a>
	                            </s:else>
							</td>
                        </tr>
                      </s:iterator>
				</tbody>
			</table>
		</div>
		<div style="width:220px;padding-left:15px; float:right; overflow:hidden" >
			<h3>人气热门</h3>
			<table width="100%">
				<tbody>
					<s:iterator id="click" value="%{ClickForums}">
						<tr>
							<td>
								<s:if test="%{urlRewrite}">
									<a class="zt" href="read-topic-<s:property value="#click.boardID"/>-<s:property value="#click.mainID"/>-0-1-index-1.html" title='<s:property value="#click.title"/>' target="_blank">
									<s:if test="#click.title.length()>13">
										<s:property value="#click.title.substring(0,13)"/>...
									</s:if>
									<s:else>
										<s:property value="#click.title"/>
									</s:else>
									</a>
	                            </s:if>
								<s:else>
									<s:url action="read?action=topic" id="posturl">
									<s:param name="bid" value="#click.boardID"/>
									<s:param name="id" value="#click.mainID"/>
									<s:param name="fcpage" value="1"/>
									<s:param name="fcaction" value="index"/>
									</s:url>
									<a class="zt" href="${posturl}" title='<s:property value="#click.title"/>' target="_blank">
									<s:if test="#click.title.length()>13">
										<s:property value="#click.title.substring(0,13)"/>
									</s:if>
									<s:else>
										<s:property value="#click.title"/>
									</s:else>
									</a>
	                            </s:else>
							</td>
                        </tr>
                      </s:iterator>
				</tbody>
			</table>
		</div>
	</div>
</div>
<div id="info">
  	<div id="info1" class="main_body1">
		<%-- <div class="gonggao"><strong>公告：</strong><a href="#">有关论坛威望的调整说明(2012-2-28)</a></div> --%>
		<div class="gonggao_right"><s:property value="%{sysinfo}" escape="false"/></div>
	</div>
</div>
<div id="board" class="main_body1">
	<div id="boardl" class="caipiao_box caipiao_line">
  		<!-- <h1>
  			彩票论坛
  		</h1> -->
  		<s:iterator id="board" value="%{boardList}">
		  		<s:if test="#board.boardType==1">
		  				<s:if test="%{urlRewrite}">
						<h1><s:property value="#board.boardName"/></h1>
						</s:if>
						<s:else>
						<s:url action="forum?action=index" id="furl">
						<s:param name="bid" value="#board.id"/>
						</s:url>
						<h2><a href="${furl}"><s:property value="#board.boardName"/></a></h2>
		                </s:else>
		                <p><s:property value="#board.explains"/></p>
	                <%-- <dd>
						<p><s:text name="bbscs.boardmaster"/>:<bbscs:boardmaster value="#board.boardMaster"/></p>
					</dd> --%>
					<br clear="all" />
	  			</s:if>
	  			<s:if test="#board.boardType==3">
	  			<dl>
	  				<s:url action="forum?action=index" id="furl">
					<s:param name="bid" value="#board.id"/>
					</s:url>
					<a href="${furl}">
		  				<s:if test='#board.boardName.indexOf("双色球")!=-1'>
		       			<img src="images/lotno/ssq_title.gif" width="48" height="48" />
		        		</s:if>
		        		<s:elseif test='#board.boardName.indexOf("福彩3D")!=-1'>
		        			<img src="images/lotno/3D_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#board.boardName.indexOf("七乐彩")!=-1'>
		        			<img src="images/lotno/qlc_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#board.boardName.indexOf("大乐透")!=-1'>
		        			<img src="images/lotno/dlt_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#board.boardName.indexOf("排列三")!=-1'>
		        			<img src="images/lotno/pls_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#board.boardName.indexOf("排列五")!=-1'>
		        			<img src="images/lotno/plw_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#board.boardName.indexOf("重庆时时彩")!=-1'>
		        			<img src="images/lotno/ssc_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#board.boardName.indexOf("七星彩")!=-1'>
		        			<img src="images/lotno/qxc_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#board.boardName.indexOf("江西11选5")!=-1'>
		        			<img src="images/lotno/11x5_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#board.boardName.indexOf("十一运夺金")!=-1'>
		        			<img src="images/lotno/syydj_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#board.boardName.indexOf("足彩胜负彩")!=-1'>
		        			<img src="images/lotno/zcsfc_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#board.boardName.indexOf("足彩论坛")!=-1'>
		        			<img src="images/lotno/zucai_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#board.boardName.indexOf("娱乐灌水")!=-1'>
		        			<img src="images/lotno/yuleguanshui_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#board.boardName.indexOf("服务专区")!=-1'>
		        			<img src="images/lotno/fw_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#board.boardName.indexOf("篮彩论坛")!=-1'>
		        			<img src="images/lotno/lancai_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#board.boardName.indexOf("技术交流")!=-1'>
		        			<img src="images/lotno/jsjl_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		
	        		</a>
        		<dt>
	        		<s:if test="%{urlRewrite}">
					<h2><a href="forum-index-<s:property value="#board.id"/>.html"><s:property value="#board.boardName"/></a></h2>
					</s:if>
					<s:else>
					<s:url action="forum?action=index" id="furl">
					<s:param name="bid" value="#board.id"/>
					</s:url>
					<h2><a href="${furl}"><s:property value="#board.boardName"/></a></h2>
	                </s:else>
	                <p><s:property value="#board.explains"/></p>
                </dt>
                <dd>
                	<span><s:property value="#board.mainPostNum"/> / <s:property value="#board.postNum"/></span>
					<p><s:text name="bbscs.boardmaster"/>:<bbscs:boardmaster value="#board.boardMaster"/></p>
				</dd>
				<br clear="all" />
			</dl>
  			</s:if>
  			<s:set name="bl2" value="%{boardMap.get(#board.id)}"></s:set>
     			<s:iterator id="b" value="#bl2">
     			<dl>
     				<s:url action="forum?action=index" id="furl">
					<s:param name="bid" value="#b.id"/>
					</s:url>
					<a href="${furl}">
	     				<s:if test='#b.boardName.indexOf("双色球")!=-1'>
	     					<img src="images/lotno/ssq_title.gif" width="48" height="48" />
		        		</s:if>
		        		<s:elseif test='#b.boardName.indexOf("福彩3D")!=-1'>
		        			<img src="images/lotno/3D_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#b.boardName.indexOf("七乐彩")!=-1'>
		        			<img src="images/lotno/qlc_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#b.boardName.indexOf("大乐透")!=-1'>
		        			<img src="images/lotno/dlt_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#b.boardName.indexOf("排列三")!=-1'>
		        			<img src="images/lotno/pls_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#b.boardName.indexOf("排列五")!=-1'>
		        			<img src="images/lotno/plw_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#b.boardName.indexOf("重庆时时彩")!=-1'>
		        			<img src="images/lotno/ssc_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#b.boardName.indexOf("七星彩")!=-1'>
		        			<img src="images/lotno/qxc_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#b.boardName.indexOf("江西11选5")!=-1'>
		        			<img src="images/lotno/11x5_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#b.boardName.indexOf("十一运夺金")!=-1'>
		        			<img src="images/lotno/syydj_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#b.boardName.indexOf("足彩胜负彩")!=-1'>
		        			<img src="images/lotno/zcsfc_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#b.boardName.indexOf("足彩论坛")!=-1'>
		        			<img src="images/lotno/zucai_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#b.boardName.indexOf("娱乐灌水")!=-1'>
		        			<img src="images/lotno/yuleguanshui_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#b.boardName.indexOf("服务专区")!=-1'>
		        			<img src="images/lotno/fw_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#b.boardName.indexOf("篮彩论坛")!=-1'>
		        			<img src="images/lotno/lancai_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		<s:elseif test='#b.boardName.indexOf("技术交流")!=-1'>
		        			<img src="images/lotno/jsjl_title.gif" width="48" height="48" />
		        		</s:elseif>
		        		
	        		</a>
	  			<dt>
	  				<s:if test="%{urlRewrite}">
					<h2><a href="forum-index-<s:property value="#b.id"/>.html"><s:property value="#b.boardName"/></a></h2>
					</s:if>
					<s:else>
					<s:url action="forum?action=index" id="furl">
					<s:param name="bid" value="#b.id"/>
					</s:url>
					<h2><a href="${furl}"><s:property value="#b.boardName"/></a></h2>
	                </s:else>
	                <p><s:property value="#b.explains"/></p>
	  			</dt>
	  			<dd>
	  				<span><s:property value="#b.mainPostNum"/> / <s:property value="#b.postNum"/></span>
					<p><s:text name="bbscs.boardmaster"/>:<bbscs:boardmaster value="#b.boardMaster"/></p>
				</dd>
				<br clear="all" />
				</dl>
	  			</s:iterator>
			
	  	</s:iterator>
	</div>
</div>
<div class="jg_10"></div>

