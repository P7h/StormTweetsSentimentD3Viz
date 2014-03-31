package org.p7h.storm.sentimentanalysis.topology;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.contrib.jms.JmsProvider;
import backtype.storm.contrib.jms.bolt.JmsBolt;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.p7h.storm.sentimentanalysis.bolts.SentimentCalculatorBolt;
import org.p7h.storm.sentimentanalysis.bolts.StateLocatorBolt;
import org.p7h.storm.sentimentanalysis.cameljms.SpringJmsProvider;
import org.p7h.storm.sentimentanalysis.spouts.TwitterSpout;
import org.p7h.storm.sentimentanalysis.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * Orchestrates the elements and forms a Topology to find the most happiest state by analyzing and processing Tweets.
 *
 * @author - Prashanth Babu
 */
public final class SentimentAnalysisTopology {
	private static final Logger LOGGER = LoggerFactory.getLogger(SentimentAnalysisTopology.class);

	public static final void main(final String[] args) throws Exception {
		final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		final JmsProvider jmsProvider = new SpringJmsProvider(applicationContext, "jmsConnectionFactory",
				                                                          "notificationQueue");

		final TopologyBuilder topologyBuilder = new TopologyBuilder();

		final JmsBolt jmsBolt = new JmsBolt();
		jmsBolt.setJmsProvider(jmsProvider);
		jmsBolt.setJmsMessageProducer((session, input) -> {
            final String json = "{\"stateCode\":\"" + input.getString(0) + "\", \"sentiment\":" + input.getInteger(1) + "}";
            return session.createTextMessage(json);
        });

		try {
			final Config config = new Config();
			config.setMessageTimeoutSecs(120);
			config.setDebug(true);

			topologyBuilder.setSpout("twitterspout", new TwitterSpout());
			topologyBuilder.setBolt("statelocatorbolt", new StateLocatorBolt())
					.shuffleGrouping("twitterspout");
			topologyBuilder.setBolt("sentimentcalculatorbolt", new SentimentCalculatorBolt())
					.fieldsGrouping("statelocatorbolt", new Fields("state"));
			topologyBuilder.setBolt("jmsBolt", jmsBolt).fieldsGrouping("sentimentcalculatorbolt", new Fields("stateCode"));

			//Submit it to the cluster, or submit it locally
			if (null != args && 0 < args.length) {
				config.setNumWorkers(3);
				StormSubmitter.submitTopology(args[0], config, topologyBuilder.createTopology());
			} else {
				config.setMaxTaskParallelism(10);
				final LocalCluster localCluster = new LocalCluster();
				localCluster.submitTopology(Constants.TOPOLOGY_NAME, config, topologyBuilder.createTopology());
				//Run this topology for 600 seconds so that we can complete processing of decent # of tweets.
				Utils.sleep(600 * 1000);

				LOGGER.info("Shutting down the cluster...");
				localCluster.killTopology(Constants.TOPOLOGY_NAME);
				localCluster.shutdown();

				Runtime.getRuntime().addShutdownHook(new Thread()	{
					@Override
					public void run()	{
						LOGGER.info("Shutting down the cluster...");
						localCluster.killTopology(Constants.TOPOLOGY_NAME);
						localCluster.shutdown();
					}
				});
			}
		} catch (final Exception exception) {
			//Deliberate no op;
			exception.printStackTrace();
		}
		LOGGER.info("\n\n\n\t\t*****Please clean your temp folder \"{}\" now!!!*****", System.getProperty("java.io.tmpdir"));
	}
}