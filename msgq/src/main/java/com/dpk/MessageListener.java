package com.dpk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.dpk.config.ApplicationConfigReader;
import com.dpk.dto.UserDetails;
import com.dpk.util.ApplicationConstant;

import reactor.core.publisher.Flux;
import reactor.core.publisher.TopicProcessor;
import reactor.util.concurrent.Queues;

/**
 * Message Listener for RabbitMQ
 * @author deepak.af.kumar
 *
 */

@Service
public class MessageListener<T> {
	@Value("${msg.server.url}")
	private String msgServerUrl;
	private Flux<UserDetails> flux;
    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);
    
    @Autowired
    ApplicationConfigReader applicationConfigReader;
    private TopicProcessor<UserDetails> sseFluxProcessor = TopicProcessor.share("sseFromAmqp", Queues.SMALL_BUFFER_SIZE);
   
    
    public TopicProcessor<UserDetails> getSseFluxProcessor() {
		return sseFluxProcessor;
	}


	/**
     * Message listener for app1
     * @param UserDetails a user defined object used for deserialization of message
     */
    @RabbitListener(queues = "${app1.queue.name}")
    public void receiveMessageForApp1(final UserDetails data) {
    	log.info("Received message: {} from app1 queue.", data);
    	
    	try {
    		log.info("Making REST call to the API");
    		//TODO: Code to make REST call
//    		callWebService(data);
    		this.sseFluxProcessor.onNext(data);
    		RestTemplate restTemplate = new RestTemplate();
    		ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(msgServerUrl, data, Boolean.class);
        	log.info("<< Exiting receiveMessageForApp1() after API call : ",responseEntity.getBody());
    	} catch(HttpClientErrorException  ex) {
    		
    		if(ex.getStatusCode() == HttpStatus.NOT_FOUND) {
        		log.info("Delay...");
        		try {
    				Thread.sleep(ApplicationConstant.MESSAGE_RETRY_DELAY);
    			} catch (InterruptedException e) { }
    			
    			log.info("Throwing exception so that message will be requed in the queue.");
    			// Note: Typically Application specific exception should be thrown below
    			throw new RuntimeException();
    		} else {
    			throw new AmqpRejectAndDontRequeueException(ex); 
    		}
    		
    	} catch(Exception e) {
    		log.error("Internal server error occurred in API call. Bypassing message requeue {}", e);
    		throw new AmqpRejectAndDontRequeueException(e); 
    	}

    }
    
    
    /**
     * Message listener for app2
     * 
     */
    
    @RabbitListener(queues = "${app2.queue.name}")
    public void receiveMessageForApp2(String reqObj) {
    	log.info("Received message: {} from app2 queue.", reqObj);
    	
    	try {
    		log.info("Making REST call to the API");
    		//TODO: Code to make REST call
        	log.info("<< Exiting receiveMessageCrawlCI() after API call.");
    	} catch(HttpClientErrorException  ex) {
    		
    		if(ex.getStatusCode() == HttpStatus.NOT_FOUND) {
        		log.info("Delay...");
        		try {
    				Thread.sleep(ApplicationConstant.MESSAGE_RETRY_DELAY);
    			} catch (InterruptedException e) { }
    			
    			log.info("Throwing exception so that message will be requed in the queue.");
    			// Note: Typically Application specific exception can be thrown below
    			throw new RuntimeException();
    		} else {
    			throw new AmqpRejectAndDontRequeueException(ex); 
    		}
    		
    	} catch(Exception e) {
    		log.error("Internal server error occurred in python server. Bypassing message requeue {}", e);
    		throw new AmqpRejectAndDontRequeueException(e); 
    	}

    }
    
    public Flux<UserDetails> getMsgList(){
    	return flux;
    }
  


}
