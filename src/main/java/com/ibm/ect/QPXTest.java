package com.ibm.ect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.airchina.util.AccessToHttp;

public class QPXTest {
	static long a = System.currentTimeMillis();
	public static void main(String[] args) {

		String urlString = "https://www.google.com/travel/qpxconnect/production";
		String reqS = "<search name='priceByItinerary' api='shopping' key='aGJcCilVIwEcWeblvNeBglxO' version='10'>"
				+ "<inputs>"
				+ "<searchControl>"
				+ "<slice>"
				+ "<origin>PEK</origin>"
				+ "<destination>LAX</destination>"
				+ "<date>2016-12-12</date>"
				+ "</slice>"
				+ "<passenger>"
				+ "<type>adult</type>"
				+ "<count>1</count>"
				+ "</passenger>"
				+ "</searchControl>"
				+ "</inputs>"
				+ "<summarizer>priceByItineraryTrip</summarizer>" + "</search>";

		 
		
		String reqPEKSHA = "<search name='priceByItinerary' api='shopping' key='aGJcCilVIwEcWeblvNeBglxO' version='10'>"
				+ "<inputs>"
				+ "<searchControl>"
				+ "<slice>"
				+ "<permittedCarrier>CA</permittedCarrier>"
				+ "<maxStopCount>2</maxStopCount>"
				+ "<origin>PEK</origin>"
				+ "<destination>WUT</destination>"
				+ "<date>2016-12-12</date>"
				+ "</slice>"
				+ "<passenger>"
				+ "<type>adult</type>"
				+ "<count>1</count>"
				+ "</passenger>"
				+ "</searchControl>"
				+ "</inputs>"
				+ "<summarizer>priceByItineraryTrip</summarizer>" + "</search>";
		
		
		
		String reqParnerPEKSHA = "<?xml version='1.0' encoding='UTF-8'?>"
				+ "<search api='partnerShopping' key='aGJcCilVIwEcWeblvNeBglxO' name='partnerPriceByItinerary' qpx='prod' version='10'>"
				+ "<inputs>"
				+ "<searchControl>"
				+ "<passenger>"
		        + "<type>adult</type>"
		        + "<count>1</count>"
		        + "</passenger>"
		        + "<slice>"
				+ "<permittedCarrier>CA</permittedCarrier>"
				+ "<stopCount>0</stopCount>"
		        + "<origin>PEK</origin>"
		        + "<destination>LAX</destination>"
		        + "<date>2016-12-12</date>"
		        + "</slice>"
		        + "</searchControl>"
		        + "</inputs>"
		        + "<summarizer>priceByItineraryTrip</summarizer>"
		+ "</search>";
		
		
		
		String reqParner = "<?xml version='1.0' encoding='UTF-8'?>"
				+ "<search api='partnerShopping' key='aGJcCilVIwEcWeblvNeBglxO' name='partnerPriceByItinerary' qpx='prod' version='10'>"
				+ "<inputs>"
				+ "<searchControl>"
				+ "<passenger>"
		        + "<type>adult</type>"
		        + "<count>1</count>"
		        + "</passenger>"
		        + "<slice>"
		        + "<origin>PEK</origin>"
		        + "<destination>LAX</destination>"
		        + "<date>2016-12-12</date>"
		        + "</slice>"
		        + "</searchControl>"
		        + "</inputs>"
		        + "<summarizer>priceByItineraryTrip</summarizer>"
		+ "</search>";

		
		
		
		String reqR = "<search api='shopping' name='priceByItinerary' key='aGJcCilVIwEcWeblvNeBglxO' version='9'>"
				+ " <inputs>"
				+ "<searchControl allowUnavailableFlights='true'>"
				+ "<sliceToSearch>0</sliceToSearch>"
				+ "<slice>"
				+ "<permittedCarrier>CA</permittedCarrier>"
				+ "<date>2016-12-12</date>"
				+ "<origin>PEK</origin>"
				+ "<destination>SHA</destination>"
				+ "</slice>"
				+ "<slice>"
				+ "<date>2016-12-20</date>"
				+ "<origin>SHA</origin>"
				+ "<destination>PEK</destination>"
				+ "</slice>"
				+ "<passenger>"
				+ "<type>adult</type>"
				+ "<count>1</count>"
				+ "</passenger>"
				+ "</searchControl>"
				+ "<summarizerControl>"
				+ " <sliceToSummarize>0</sliceToSummarize>"
				+ "</summarizerControl>"
				+ " </inputs>"
				+ " <summarizer>priceByItinerarySlice</summarizer>"
				+ "</search>";

		
		
		
		
//		<!-- qpx-connect/dev-guide/examples/inputs-carriers1.xml -->
		String reqR1 = "<search api='shopping'"
						+ "name='priceByItinerary'"
						+ "key='aGJcCilVIwEcWeblvNeBglxO'"
						+ "version='10'>"
						+ "<inputs>"
						+ "<searchControl>"
						+ "<slice>"
						+ "<maxStopCount>1</maxStopCount>"
						+ "<origin>BOS</origin>"
						+ "<destination>IST</destination>"
						+ "<date>2015-12-01</date>"
						+ "<preferredCarrier>TK</preferredCarrier>"
						+ "<permittedCarrier>TK</permittedCarrier>"
						+ "<permittedCarrier>UA</permittedCarrier>"
						+ "</slice>"
						+ "<slice>"
						+ "<maxStopCount>1</maxStopCount>"
						+ " <origin>IST</origin>"
						+ "<destination>BOS</destination>"
						+ "<date>2015-12-07</date>"
						+ "<preferredCarrier>TK</preferredCarrier>"
						+ "<permittedCarrier>TK</permittedCarrier>"
						+ "<permittedCarrier>UA</permittedCarrier>"
						+ "</slice>"
						+ "<passenger>"
						+ "<type>adult</type>"
						+ "<count>1</count>"
						+ "</passenger>"
						+ "</searchControl>"
						+ "</inputs>"
						+ "<summarizer>priceByItineraryTrip</summarizer>"
						+ "</search>";
		
		
		
		
		
		
		
		
		
		
		try {
			
//			System.out.println(reqPEKSHA);
//			AccessToHttp.postBody(urlString, reqPEKSHA);
			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/PEKSHA_0_ADULT1_REQ.xml"));
			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/PEKCAN_0_ADULT1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/CANSHA_0_ADULT1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/CANSHA_0_ADULT1_CHILD1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/PEKSHA_0_ADULT1_CHILD1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/PEKSHA_0_ADULT2_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/CANSHA_0_ADULT2_CHILD1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/PEKSHA_0_ADULT2_CHILD1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/CANSHA_0_ADULT1_CHILD1_infantInLap1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/PEKSHA_0_ADULT1_CHILD1_infantInLap1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/PEKSHA_0_ADULT1_CHILD1_infantInSeat1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/PEKSHA_0_ADULT2_pricedetail_REQ.xml"));

			
			
			
			
			
			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/stop/PEKLXA_1_ADULT1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/stop/PEKLXA_1_ADULT2_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/stop/PEKLXA_1_ADULT1_CHILD1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/stop/PEKLXA_1_ADULT2_CHILD1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/stop/PEKLXA_1_ADULT1_CHILD1_infantInLap1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/stop/PEKLXA_1_ADULT1_CHILD1_infantInSeat1_REQ.xml"));
			
//		e	AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_international/PEKLAX_0_ADULT1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_international/PEKLAX_0_ADULT2_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_international/PEKLAX_0_ADULT1_CHILD1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_international/PEKLAX_0_ADULT2_CHILD1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_international/PEKLAX_0_ADULT1_CHILD1_infantInLap1_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_international/PEKLAX_0_ADULT1_pricedetail_REQ.xml"));
			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/PEKSHA_0_ADULT1_pricedetail_REQ.xml"));
			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/PEKSHA_0_ADULT1_CHILD1_pricedetail_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_domestic/PEKSHA_0_ADULT1_itineraryDetail_REQ.xml"));

			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_international/STOP/PEKLAX_1_ADULT1_REQ.xml"));
//			String qpx =  AccessToHttp.postBodyByString(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_international/STOP/PEKLAX_1_ADULT1_pricedetail_REQ.xml"));
//			appendMethodA("qpx.txt", qpx, 0l);
			
			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_international/STOP/PEKLAX_1_ADULT2_REQ.xml"));

//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_international/STOP/PEKLAX_1_ADULT1_CHILD1_REQ.xml"));

//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_international/STOP/PEKLAX_1_ADULT1_CHILD1_pricedetail_REQ.xml"));
		
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_international/STOP/PEKLAX_1_ADULT2_CHILD1_REQ.xml"));
			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_international/STOP/PEKLAX_1_ADULT1_pricedetail_REQ.xml"));

//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_international/STOP/PEKLAX_1_ADULT2_CHILD1_pricedetail_REQ.xml"));
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_international/STOP/CTULAX_1_ADULT1_pricingDetail_REQ.xml"));

			
			
			
			//16800  3314  3464
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_international/STOP/CTULAX_1_ADULT1_pricingDetail_REQ.xml"));

//			4679
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_domestic/PEKSHAPEK_0_ADULT1_REQ.xml"));
	
//			28583   3037
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_domestic/PEKSHAPEK_0_ADULT1_pricedetail_REQ.xml"));
			
//			5240
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_domestic/PEKSHAPEK_0_ADULT2_REQ.xml"));

//			5126
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_domestic/PEKSHAPEK_0_ADULT2_CHILD1_REQ.xml"));

//			4049
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_domestic/PEKSHAPEK_0_ADULT1_CHILD1_REQ.xml"));

//			92921  55137  19907 4035 24408 3887
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_domestic/PEKSHAPEK_0_ADULT1_CHILD1_pricedetail_REQ.xml"));
			
//			3184  6065
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_domestic/PEKSHAPEK_0_ADULT1_CHILD1_infantInLap1_REQ.xml"));

			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_domestic/PEKSHAPEK_0_ADULT2_pricedetail_REQ.xml"));

			
			
			
			
//			3097		
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_domestic/stop/PEK-CTU-LXA-CTU-PEK_1_ADULT1_REQ.xml"));

//			2375
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_domestic/stop/PEK-CTU-LXA-CTU-PEK_1_ADULT2_REQ.xml"));
			
//			3544
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_domestic/stop/PEK-CTU-LXA-CTU-PEK_1_ADULT1_CHILD1_REQ.xml"));

//			3226
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_domestic/stop/PEK-CTU-LXA-CTU-PEK_1_ADULT2_CHILD1_REQ.xml"));

//			3443
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_domestic/stop/PEK-CTU-LXA-CTU-PEK_1_ADULT1_pricedetail_REQ.xml"));

//			8675 26663 11591
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_domestic/stop/PEK-CTU-LXA-CTU-PEK_1_ADULT1_CHILD1_pricedetail_REQ.xml"));

//			3438
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_domestic/stop/PEK-CTU-LXA-CTU-PEK_1_ADULT1_CHILD1_infantInLap1_REQ.xml"));
				
			
//			3352
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_international/PEKLAXPEK_0_ADULT1_pricedetail_REQ.xml"));

//			3352----------
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_international/PEKLAXPEK_0_ADULT1_REQ.xml"));
		
			
			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_international/stop/PEK-LAX_FRA-CTU-SHA-EWR_1_ADULT1_CHILD1_pricedetail_REQ.xml"));

			
			
			
//			2917
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_international/PEKLAXPEK_0_ADULT2_REQ.xml"));

//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_international/PEKLAXPEK_0_ADULT2_CHILD1_REQ.xml"));

//			2900
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_international/PEKLAXPEK_0_ADULT1_CHILD1_REQ.xml"));

//			5014
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_international/PEKLAXPEK_0_ADULT1_CHILD1_pricedetail_REQ.xml"));

//			4946
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_international/PEKLAXPEK_0_ADULT1_CHILD1_infantInLap1_REQ.xml"));

//			15513
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_international/stop/PEKLAXPEK_1_ADULT1_REQ.xml"));

//			11216
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_international/stop/PEKLAXPEK_1_ADULT1_pricedetail_REQ.xml"));

//			33356		
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_international/stop/PEKLAXPEK_1_ADULT2_REQ.xml"));

//			7748
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_international/stop/PEKLAXPEK_1_ADULT1_CHILD1_REQ.xml"));

//			27624
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_international/stop/PEKLAXPEK_1_ADULT2_CHILD1_REQ.xml"));

//			53367
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_international/stop/PEKLAXPEK_1_ADULT1_CHILD1_pricedetail_REQ.xml"));

//			7248
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_international/stop/PEKLAXPEK_1_ADULT1_CHILD1_infantInLap1_REQ.xml"));
	
			
//			18930
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_domestic/PEK-SHA-CAN-PEK_0_ADULT1_REQ.xml"));

//			19119
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_domestic/PEK-SHA-CAN-PEK_0_ADULT2_REQ.xml"));

			
//			34388
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_domestic/PEK-SHA-CAN-PEK_0_ADULT1_pricedetail_REQ.xml"));

//			20271
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_domestic/PEK-SHA-CAN-PEK_0_ADULT1_CHILD1_REQ.xml"));

//			10204
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_domestic/PEK-SHA-CAN-PEK_0_ADULT2_CHILD1_REQ.xml"));
		
//			34544
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_domestic/PEK-SHA-CAN-PEK_0_ADULT1_CHILD1_pricedetail_REQ.xml"));

//			12306
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_domestic/PEK-SHA-CAN-PEK_0_ADULT1_CHILD1_infantInLap1_REQ.xml"));

//			4158
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_domestic/stop/KWL-PEK_LXA-PEK_1_ADULT1_REQ.xml"));
			
//			2649
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_domestic/stop/KWL-PEK_LXA-PEK_1_ADULT2_REQ.xml"));

//			4555
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_domestic/stop/KWL-PEK_LXA-PEK_1_ADULT1_CHILD1_REQ.xml"));

//			3106
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_domestic/stop/KWL-PEK_LXA-PEK_1_ADULT1_pricedetail_REQ.xml"));

//			5077
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_domestic/stop/KWL-PEK_LXA-PEK_1_ADULT2_CHILD1_REQ.xml"));

//			6892	
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_domestic/stop/KWL-PEK_LXA-PEK_1_ADULT1_CHILD1_pricedetail_REQ.xml"));

//			3441
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_domestic/stop/KWL-PEK_LXA-PEK_1_ADULT1_CHILD1_infantInLap1_REQ.xml"));

			
			
//			不经停
//			6817
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_international/PEK-LAX_FRA-CTU_0_ADULT1_REQ.xml"));

//			2719
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_international/PEK-LAX_FRA-CTU_0_ADULT1_CHILD1_infantInLap1_REQ.xml"));

//			7171
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_international/PEK-LAX_FRA-CTU_0_ADULT1_CHILD1_pricedetail_REQ.xml"));
			
//			3624
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_international/PEK-LAX_FRA-CTU_0_ADULT1_CHILD1_REQ.xml"));

//			3019
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_international/PEK-LAX_FRA-CTU_0_ADULT1_pricedetail_REQ.xml"));

//			9120
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_international/PEK-LAX_FRA-CTU_0_ADULT2_CHILD1_REQ.xml"));

//			3554
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_international/PEK-LAX_FRA-CTU_0_ADULT2_REQ.xml"));

			
			
			
			
//			经停
//			3503
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_international/stop/CTU-LAX_JFK-CTU_1_ADULT1_REQ.xml"));

//			4243
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_international/stop/CTU-LAX_JFK-CTU_1_ADULT2_REQ.xml"));

//			33901
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_international/stop/CTU-LAX_JFK-CTU_1_ADULT2_CHILD1_REQ.xml"));
			
//			4291
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_international/stop/CTU-LAX_JFK-CTU_1_ADULT1_pricedetail_REQ.xml"));

//			4242
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_international/stop/CTU-LAX_JFK-CTU_1_ADULT1_CHILD1_REQ.xml"));

//			5398
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_international/stop/CTU-LAX_JFK-CTU_1_ADULT1_CHILD1_pricedetail_REQ.xml"));

//			4341
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/many_international/stop/CTU-LAX_JFK-CTU_1_ADULT1_CHILD1_infantInLap1_REQ.xml"));

			
//			2728
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_FASCO_domestic/PEKORD_0_ADULT1_REQ.xml"));

//			3677
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/single_FASCO_domestic/stop/PEKORD_1_ADULT1_REQ.xml"));

			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/round_FASCO_domestic/PEKORD_1_ADULT1_REQ.xml"));

			
			
			
			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/test/PEKSHA_0_ADULT1_REQ.xml"));

//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/test/PEKSHA_0_ADULT2_CHILD1_Slice_itineraryDetail_PriceDetail_REQ.xml"));
			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/test/PEKSHA_0_ADULT2_CHILD1_Slice_REQ.xml"));
			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/test/PEKSHA_0_ADULT2_CHILD1_datemonths_REQ.xml"));

//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/test/PEKSHA_0_ADULT1_session_REQ.xml"));
			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/test/PEKSHA_0_ADULT1_session2_REQ.xml"));
			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/test/PEKSHA_0_ADULT1_helper_REQ.xml"));
			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/test/PEKSHA_0_ADULT1_priceByDate_REQ.xml"));
			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/test/PEKSHA_0_ADULT1_priceSingle_REQ.xml"));
			
//			AccessToHttp.postBody(urlString, readFileByPath("/Users/tanyanbing/work/B2CAPP/调试/QPX/refund/refund.xml"));
			
			
//			String aString=",,1";
//			
//			System.out.println(aString.split(",")[0]);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			long b = System.currentTimeMillis();
//			System.out.println(b-a);
		}

	}
	
	
	
	/**
	 * A方法追加文件：使用RandomAccessFile
	 * 
	 * @param fileName
	 *            文件名
	 * @param content
	 *            追加的内容
	 * @throws IOException
	 */
	public static long appendMethodA(String fileName, String content, long l)
			throws IOException {
		System.out.println(l);
		RandomAccessFile randomFile = new RandomAccessFile(
				"/Users/tanyanbing/Desktop/" + fileName, "rw");
		try {
			// 打开一个随机访问文件流，按读写方式

			// 文件长度，字节数
			long fileLength = l;
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);

			content += "\r\n";
			byte buffer[] = new byte[2048];
			buffer = content.getBytes();

			randomFile.write(buffer);
			return randomFile.getFilePointer();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			randomFile.close();
		}

		return 0;
	}
	
	
	
	
	
	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 * @throws Exception 
	 */
	public static String readFileByPath(String path) throws Exception {
		File file = new File(path);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			long l = 0l;
			StringBuffer sbBuffer = new StringBuffer();
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				if(!tempString.trim().equals("")){
					sbBuffer.append(tempString);
				}
				line++;
			}
			reader.close();
			return sbBuffer.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return null;
	}


	
	

}
