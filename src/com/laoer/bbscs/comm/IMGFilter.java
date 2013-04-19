package com.laoer.bbscs.comm;

/**
 * <p>Title: TianyiBBS</p>
 *
 * <p>Description: BBSCS</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Laoer.com</p>
 *
 * @author Laoer
 * @version 7.0
 */
public class IMGFilter
    extends RegexFilter {

  public IMGFilter(String source) {
    super(source);
    this.regex = "(\\[IMG\\])(http://.[^\\[]*)(\\[\\/IMG\\])";
    this.rpStr = "<p><IMG SRC=\"$2\" border=0 onload=\"javascript:if(this.width>screen.width-333)this.width=screen.width-333\"></p>";
    this.doFiltration();
  }

}
