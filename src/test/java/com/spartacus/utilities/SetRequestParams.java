package com.spartacus.utilities;

import java.util.HashMap;

public class SetRequestParams {

	HashMap<String, String> reqParam;

	public SetRequestParams() {
		reqParam = new HashMap<String, String>();
	}

	public HashMap<String, String> returnParamsObj() {
		return reqParam;
	}

	public SetRequestParams setReqParam(String keyParam, String valueParam) {
		reqParam.put(keyParam, valueParam);
		return this;
	}

}
