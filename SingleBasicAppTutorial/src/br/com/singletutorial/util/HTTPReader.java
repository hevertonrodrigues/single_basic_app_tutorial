package br.com.singletutorial.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/***
 * Methods for reading HTTP and convert to String.
 * 
 * @author BrunoGabriel
 * @since 07/21/2014
 */

public class HTTPReader {

	/**
	 * 
	 * @param urlString
	 *            URL to read that contain a JSON
	 * @return String with JSON data.
	 * @throws Exception
	 *             Mobile not connected to Internet or break link.
	 */
	public static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			// Read max 1024 chars per time
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

}
