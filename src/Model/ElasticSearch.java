package Model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.lucene.queryparser.flexible.core.builders.QueryBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MatchQuery;
import org.elasticsearch.search.SearchHit;

public class ElasticSearch {

	Client client;
	
	public Client init(){
		try {
	        Settings settings = Settings.settingsBuilder()
	                .put("cluster.name", "elasticsearch").build();
	        Client client = TransportClient.builder().settings(settings).build()
	                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
	                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
	       
	    System.out.println("Goodbye elasticsearch!");
	    return client;
		} catch (UnknownHostException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        return null;
	    }
		
	}
	
	public void InsertCustomer(ArrayList<Object> array, int customerId, int index){
		client = init();
		if(client != null){
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("name", array.get(index++));
			json.put("surname", array.get(index++));
			json.put("address", array.get(index++));
			json.put("city", array.get(index++));
			json.put("postcode", array.get(index++));
			
			IndexResponse response = client.prepareIndex("dbs_projekt", "customer", String.valueOf(customerId))
	                .setSource(json)
	                .get();
			
			System.out.println(response.toString());
			client.close();
		}
	}
	
	public void UpdateCustomer(ArrayList<Object> array, int customerId){
		int index = 0;
		client = init();
		if(client != null){
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("name", array.get(index++));
			json.put("surname", array.get(index++));
			json.put("address", array.get(index++));
			json.put("city", array.get(index++));
			json.put("postcode", array.get(index++));
			
			UpdateRequest updateRequest = new UpdateRequest();
			updateRequest.index("dbs_projekt");
			updateRequest.type("customer");
			updateRequest.id(String.valueOf(customerId));
			updateRequest.doc(json);
			UpdateResponse response = null;
			try {
				response = client.update(updateRequest).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			System.out.println(response.toString());
			client.close();
		}
	}
	
	public void DeleteCustomer(int customerId){
		client = init();
		
		DeleteResponse response = client.prepareDelete("dbs_projekt", "customer", String.valueOf(customerId)).get();
		
		client.close();
	}
	
	public int FindCustomer(ArrayList<Object> array, int index){
		int customerId = 0;
		client = init();
		if(client != null){
			BoolQueryBuilder query = QueryBuilders
					.boolQuery()
					.must(QueryBuilders.matchQuery("name", array.get(index++)))
					.must(QueryBuilders.matchQuery("surname", array.get(index++)))
					.must(QueryBuilders.matchQuery("address", array.get(index++)))
					.must(QueryBuilders.matchQuery("city", array.get(index++)))
					.must(QueryBuilders.matchQuery("postcode", array.get(index++)));
			
			SearchResponse response = client.prepareSearch("dbs_projekt")
					.setTypes("customer")
					.setQuery((org.elasticsearch.index.query.QueryBuilder) query)
					.execute()
					.actionGet();
			
			SearchHit[] hits = response.getHits().getHits();
			
			for (SearchHit hit : hits) {
	            Map<String,Object> result = hit.getSource();
	            System.out.println(result);
	            customerId = Integer.parseInt(hit.getId());
	        }
		}
		client.close();
		return customerId;
	}
}
