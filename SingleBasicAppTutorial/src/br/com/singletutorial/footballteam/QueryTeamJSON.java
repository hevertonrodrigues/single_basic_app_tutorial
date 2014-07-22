package br.com.singletutorial.footballteam;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/***
 * 
 * Class that uses GSON to parse data in array (see SingleBasicItem1JSON for
 * details).
 * 
 * For more information enter in: https://code.google.com/p/google-gson
 * 
 * @author BrunoGabriel
 * @since 07/21/2014
 */

public class QueryTeamJSON implements Serializable {

	private static final long serialVersionUID = 353484201759567326L;

	@SerializedName("result")
	private boolean result;

	@SerializedName("items")
	private List<TeamJSON> singleItems;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public List<TeamJSON> getSingleItems() {
		return singleItems;
	}

	public void setSingleItems(List<TeamJSON> singleItems) {
		this.singleItems = singleItems;
	}

}
