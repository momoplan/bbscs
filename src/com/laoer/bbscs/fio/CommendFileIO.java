package com.laoer.bbscs.fio;

import java.io.IOException;
import java.util.*;

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
public interface CommendFileIO {

  public void saveCommendInReadPageFile(long commendid, List commendList) throws IOException;

}
