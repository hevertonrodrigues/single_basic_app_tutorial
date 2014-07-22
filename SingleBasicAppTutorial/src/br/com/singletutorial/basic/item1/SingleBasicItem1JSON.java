package br.com.singletutorial.basic.item1;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/***
 * This class uses GSON to parse data and put it into a List View. I'm using the
 * aliasing in my class to show the real use of GSON.
 * 
 * @author BrunoGabriel
 * @since 07/21/2014
 */

public class SingleBasicItem1JSON implements Serializable {

	private static final long serialVersionUID = 600212725375643082L;

	// @SerializedName is annotation of JSON content
	@SerializedName("id")
	private String idTeam;

	@SerializedName("url_picture")
	private String urlPicture;

	@SerializedName("name")
	private String teamName;

	@SerializedName("year")
	private Integer teamYear;

	@SerializedName("stadium")
	private String teamStadium;

	public String getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(String idTeam) {
		this.idTeam = idTeam;
	}

	public String getUrlPicture() {
		return urlPicture;
	}

	public void setUrlPicture(String urlPicture) {
		this.urlPicture = urlPicture;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getTeamYear() {
		return teamYear;
	}

	public void setTeamYear(Integer teamYear) {
		this.teamYear = teamYear;
	}

	public String getTeamStadium() {
		return teamStadium;
	}

	public void setTeamStadium(String teamStadium) {
		this.teamStadium = teamStadium;
	}

}
