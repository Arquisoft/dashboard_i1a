package hello.listeners;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.context.annotation.SessionScope;

import persistence.model.Proposal;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
@SessionScope
public class MessageListener {

	private static final Logger logger = Logger.getLogger(MessageListener.class);


}
