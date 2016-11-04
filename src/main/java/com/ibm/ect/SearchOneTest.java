package com.ibm.ect;

import com.travelsky.ebuild.clientapi.searchone.SearchOne;
import com.travelsky.ibe.exceptions.IBEException;


public class SearchOneTest {
	public static void main(String[] args) throws IBEException {
		SearchOne sOne = new SearchOne();
		
	//	String req = "{'SearchOne':{'request':{'segments':{'origin':{'name':'PEK'},'destination':{'name':'SHA'},'departureDate':{'day':'30','month':'11','year':'2015'}}}}}";
		
		
//		String req = "{'segments':[{'origin':[{'name':'SYD'}],'destination':[{'name':'PEK'}],'departureDate':{'day':08,'month':04,'year':2016},'departAfterTime':{'hour':0,'minutes':0},'departBeforeTime':{'hour':23,'minutes':59},'connectionLocations':[]},{'origin':[{'name':'PEK'}],'destination':[{'name':'SYD'}],'departureDate':{'day':28,'month':04,'year':2016},'departAfterTime':{'hour':0,'minutes':0},'departBeforeTime':{'hour':23,'minutes':59},'connectionLocations':[]}],'displayCurrency':'','agencies':[{'channel':'1E','pos':'BJS','travelAgencyCode':'PEK184'}],'passengers':[{'ptc':['ADT']}],'preferences':{'changeable':false,'refundable':false,'upgradable':false,'cabins':{'codes':['ECONOMY'],'virtual':false},'downsell':false,'upsell':false,'faresAllowedCarriers':['CA'],'noCodeshare':false,'noOvernightStay':false},'journeyPreferences':{'splitTicket':false,'noInterline':false,'includeBaggage':true,'includeCommission':true,'includeSurcharge':false,'includePenalties':true,'includeDebugOutput':true,'includeIataTax':false,'includeYqyr':false,'platingCarrier':'CA','searchSpeed':1,'multiCabin':false,'allowMultiCabinCombo':false,'detailedPricing':true,'availability':'NO','sameCarrierFare':false,'corporateFaresOnly':false}}";
	
		
		String req = "{\"segments\":[{\"origin\":[{\"name\":\"SYD\"}],\"destination\":[{\"name\":\"PEK\"}],\"departureDate\":{\"day\":8,\"month\":12,\"year\":2016},\"departAfterTime\":{\"hour\":0,\"minutes\":0},\"departBeforeTime\":{\"hour\":23,\"minutes\":59},\"connectionLocations\":[]},{\"origin\":[{\"name\":\"PEK\"}],\"destination\":[{\"name\":\"SYD\"}],\"departureDate\":{\"day\":28,\"month\":12,\"year\":2016},\"departAfterTime\":{\"hour\":0,\"minutes\":0},\"departBeforeTime\":{\"hour\":23,\"minutes\":59},\"connectionLocations\":[]}],\"displayCurrency\":\"\",\"agencies\":[{\"channel\":\"1E\",\"pos\":\"BJS\",\"travelAgencyCode\":\"PEK184\"}],\"passengers\":[{\"ptc\":[\"ADT\"]}],\"preferences\":{\"changeable\":false,\"refundable\":false,\"upgradable\":false,\"cabins\":{\"codes\":[\"ECONOMY\"],\"virtual\":false},\"downsell\":false,\"upsell\":false,\"faresAllowedCarriers\":[\"CA\"],\"noCodeshare\":false,\"noOvernightStay\":false},\"journeyPreferences\":{\"splitTicket\":false,\"noInterline\":false,\"includeBaggage\":true,\"includeCommission\":true,\"includeSurcharge\":false,\"includePenalties\":true,\"includeDebugOutput\":true,\"includeIataTax\":false,\"includeYqyr\":false,\"platingCarrier\":\"CA\",\"searchSpeed\":1,\"multiCabin\":false,\"allowMultiCabinCombo\":false,\"detailedPricing\":true,\"availability\":\"NO\",\"sameCarrierFare\":false,\"corporateFaresOnly\":false}}";
		
		
		String searchOneStringRes = sOne.getSearchOneStringRes(req);
		
		
		
		
		
		
	}
	

}
