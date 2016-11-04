package com.airchina.httplongconn;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travelsky.ebuild.clientapi.searchone.SearchOne;
import com.travelsky.ibe.exceptions.IBEException;

/**
 * Servlet implementation class SearchOneTest
 */
public class SearchOneTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchOneTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SearchOne sOne = new SearchOne();

		String req = "{'segments':[{'origin':[{'name':'SYD'}],'destination':[{'name':'PEK'}],'departureDate':{'day':8,'month':3,'year':2016},'departAfterTime':{'hour':0,'minutes':0},'departBeforeTime':{'hour':23,'minutes':59},'connectionLocations':[]},{'origin':[{'name':'PEK'}],'destination':[{'name':'SYD'}],'departureDate':{'day':28,'month':12,'year':2015},'departAfterTime':{'hour':0,'minutes':0},'departBeforeTime':{'hour':23,'minutes':59},'connectionLocations':[]}],'displayCurrency':'','agencies':[{'channel':'1E','pos':'BJS','travelAgencyCode':'PEK184'}],'passengers':[{'ptc':['ADT']}],'preferences':{'changeable':false,'refundable':false,'upgradable':false,'cabins':{'codes':['ECONOMY'],'virtual':false},'downsell':false,'upsell':false,'faresAllowedCarriers':['CA'],'noCodeshare':false,'noOvernightStay':false},'journeyPreferences':{'splitTicket':false,'noInterline':false,'includeBaggage':true,'includeCommission':true,'includeSurcharge':false,'includePenalties':true,'includeDebugOutput':true,'includeIataTax':false,'includeYqyr':false,'platingCarrier':'CA','searchSpeed':1,'multiCabin':false,'allowMultiCabinCombo':false,'detailedPricing':true,'availability':'NO','sameCarrierFare':false,'corporateFaresOnly':false}}";
		
		String searchOneStringRes;
		try {
			searchOneStringRes = sOne.getSearchOneStringRes(req);
			
			response.getWriter().print(searchOneStringRes);
		} catch (IBEException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	
	}

}
