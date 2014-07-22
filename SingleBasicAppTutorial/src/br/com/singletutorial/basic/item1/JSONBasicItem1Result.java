package br.com.singletutorial.basic.item1;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

/***
 * Class that uses GSON to parse data in array (see SingleBasicItem1JSON for
 * details).
 * 
 * @author BrunoGabriel
 * @since 07/21/2014
 */

public class JSONBasicItem1Result implements Serializable {

	private static final long serialVersionUID = 353484201759567326L;

	@SerializedName("result")
	private boolean result;

	@SerializedName("items")
	private List<SingleBasicItem1JSON> singleItems;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public List<SingleBasicItem1JSON> getSingleItems() {
		return singleItems;
	}

	public void setSingleItems(List<SingleBasicItem1JSON> singleItems) {
		this.singleItems = singleItems;
	}

}
