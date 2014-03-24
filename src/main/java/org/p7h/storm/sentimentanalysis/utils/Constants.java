package org.p7h.storm.sentimentanalysis.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

/**
 * Constants used in this project.
 *
 * @author - Prashanth Babu
 */
public final class Constants {
	//Name of the Topology. Used while launching the LocalCluster
	public static final String TOPOLOGY_NAME = "SentimentAnalysis";

	//Properties file which has all the configurable parameters required for execution of this Topology.
	public static final String CONFIG_PROPERTIES_FILE = "config.properties";

	public static final String OAUTH_ACCESS_TOKEN = "OAUTH_ACCESS_TOKEN";
	public static final String OAUTH_ACCESS_TOKEN_SECRET = "OAUTH_ACCESS_TOKEN_SECRET";
	public static final String OAUTH_CONSUMER_KEY = "OAUTH_CONSUMER_KEY";
	public static final String OAUTH_CONSUMER_SECRET = "OAUTH_CONSUMER_SECRET";

	public static final String BING_MAPS_API_KEY = "BING_MAPS_API_KEY";

	//Bolt reads the Bing Maps API Value and stores the same to BING_MAPS_API_KEY_VALUE of Constants.java so that it can be used for reverse geocoding.
	//For the lack of time I am using this Constant or else using a good Design Pattern, this can be fine-tuned.
	public static String BING_MAPS_API_KEY_VALUE = "BING_MAPS_API_KEY_VALUE";

	//Sentiment scores of few words are present in this file.
	//For more info on this, please check: http://www2.imm.dtu.dk/pubdb/views/publication_details.php?id=6010
	public static final String AFINN_SENTIMENT_FILE_NAME = "AFINN-111.txt";

	//Codes of all the states of USA.
	//Used as a precautionary measure so that we can be completely sure that the State we got is indeed one of US States.
	public static final List<String> CONSOLIDATED_STATE_CODES = Lists.newArrayList("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "MD", "MA", "MI", "MN", "MS", "MO", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");

	//Map to hold the State code and its corresponding name.
	public static final Map<String, String> MAP_STATE_CODE_NAME = new HashMap<String, String>() {{
		put("AL", "Alabama");
		put("AK", "Alaska");
		put("AZ", "Arizona");
		put("AR", "Arkansas");
		put("CA", "California");
		put("CO", "Colorado");
		put("CT", "Connecticut");
		put("DE", "Delaware");
		put("DC", "District of Columbia");
		put("FL", "Florida");
		put("GA", "Georgia");
		put("HI", "Hawaii");
		put("ID", "Idaho");
		put("IL", "Illinois");
		put("IN", "Indiana");
		put("IA", "Iowa");
		put("KS", "Kansas");
		put("KY", "Kentucky");
		put("LA", "Louisiana");
		put("ME", "Maine");
		put("MD", "Maryland");
		put("MA", "Massachusetts");
		put("MI", "Michigan");
		put("MN", "Minnesota");
		put("MS", "Mississippi");
		put("MO", "Missouri");
		put("MT", "Montana");
		put("NE", "Nebraska");
		put("NV", "Nevada");
		put("NH", "New Hampshire");
		put("NJ", "New Jersey");
		put("NM", "New Mexico");
		put("NY", "New York");
		put("NC", "North Carolina");
		put("ND", "North Dakota");
		put("OH", "Ohio");
		put("OK", "Oklahoma");
		put("OR", "Oregon");
		put("PA", "Pennsylvania");
		put("RI", "Rhode Island");
		put("SC", "South Carolina");
		put("SD", "South Dakota");
		put("TN", "Tennessee");
		put("TX", "Texas");
		put("UT", "Utah");
		put("VT", "Vermont");
		put("VA", "Virginia");
		put("WA", "Washington");
		put("WV", "West Virginia");
		put("WI", "Wisconsin");
		put("WY", "Wyoming");
		put("AS", "America Samoa");
		put("FM", "Federated States of Micronesia");
		put("GU", "Guam");
		put("MH", "Marshall Islands");
		put("MP", "Northern Mariana Islands");
		put("PW", "Palau");
		put("PR", "Puerto Rico");
		put("UM", "U.S. Minor Outlying Islands");
		put("VI", "Virgin Islands of the United States");
	}};

	//Map to hold the State code and its id name for D3 mapping.
	public static final Map<String, Integer> MAP_STATE_CODE_ID = new HashMap<String, Integer>() {{
		put("AL", 1);
		put("AK", 2);
		put("AZ", 4);
		put("AR", 5);
		put("CA", 6);
		put("CO", 8);
		put("CT", 9);
		put("DE", 10);
		put("DC", 11);
		put("FL", 12);
		put("GA", 13);
		put("HI", 15);
		put("ID", 16);
		put("IL", 17);
		put("IN", 18);
		put("IA", 19);
		put("KS", 20);
		put("KY", 21);
		put("LA", 22);
		put("ME", 23);
		put("MD", 24);
		put("MA", 25);
		put("MI", 26);
		put("MN", 27);
		put("MS", 28);
		put("MO", 29);
		put("MT", 30);
		put("NE", 31);
		put("NV", 32);
		put("NH", 33);
		put("NJ", 34);
		put("NM", 35);
		put("NY", 36);
		put("NC", 37);
		put("ND", 38);
		put("OH", 39);
		put("OK", 40);
		put("OR", 41);
		put("PA", 42);
		put("RI", 44);
		put("SC", 45);
		put("SD", 46);
		put("TN", 47);
		put("TX", 48);
		put("UT", 49);
		put("VT", 50);
		put("VA", 51);
		put("WA", 53);
		put("WV", 54);
		put("WI", 55);
		put("WY", 56);
		put("AS", 60);
		put("FM", 64);
		put("GU", 66);
		put("MH", 68);
		put("MP", 69);
		put("PW", 70);
		put("PR", 72);
		put("UM", 74);
		put("VI", 78);
	}};
}
