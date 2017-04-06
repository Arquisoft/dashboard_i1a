package asw.serializers;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import asw.persistence.model.Proposal;

public class ProposalSerializer implements Serializer<Proposal> {

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {
    }

    @Override
    public byte[] serialize(String arg0, Proposal arg1) {
	byte[] retVal = null;
	ObjectMapper objectMapper = new ObjectMapper();
	try {
	    retVal = objectMapper.writeValueAsString(arg1).getBytes();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return retVal;
    }

}
