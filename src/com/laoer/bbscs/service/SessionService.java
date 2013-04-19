package com.laoer.bbscs.service;

import java.util.*;

public interface SessionService {

	public void saveSession(String id, String key, Object value);

	public void saveSession(String id, Map session);

	public Map getSession(String id);

	public Object getSession(String id, String key);

	public void removeSession(String id);

}
