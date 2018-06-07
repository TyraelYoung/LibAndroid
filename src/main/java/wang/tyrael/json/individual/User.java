package wang.tyrael.json.individual;

import wang.tyrael.statistics.individual.Logable;

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
