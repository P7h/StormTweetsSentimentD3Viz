package org.p7h.storm.sentimentanalysis.cameljms;

import backtype.storm.contrib.jms.JmsTupleProducer;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@SuppressWarnings("serial")
public class JsonTupleProducer implements JmsTupleProducer {

    private static final long serialVersionUID = 5616085758921482593L;

    public Values toTuple(Message msg) throws JMSException {
        if(msg instanceof TextMessage){
            String json = ((TextMessage) msg).getText();
            return new Values(json);
        } else {
            return null;
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("json"));
    }

}