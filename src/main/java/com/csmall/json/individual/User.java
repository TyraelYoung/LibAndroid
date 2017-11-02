package com.csmall.json.individual;

import com.csmall.library.statistics.individual.Logable;

public class User implements Logable{
	public String csmallToken;
	public String mobile;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("user:");
		sb.append(csmallToken);
		sb.append(",");
		sb.append(mobile);
		return sb.toString();
	}
}
