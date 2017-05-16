package org.educama.shipment.cmmn.sentries;

import org.educama.shipment.model.Shipment;
import org.educama.shipment.repository.ShipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShipmentOrderCompletedSentry {

    private static final Logger logger = LoggerFactory.getLogger(ShipmentOrderCompletedSentry.class);

    @Autowired
    ShipmentRepository shipmentRepository;

    public boolean shipmentOrderCompleted() {
        logger.debug("evaluating shipment status...");
        Shipment shipment = shipmentRepository.findOneBytrackingId("INCOMPLETE_SHIPMENT_ID");
        logger.debug("Customer: " + shipment.customer);
        return true;
    }

    public ShipmentOrderCompletedSentry(ShipmentRepository shipmentRepository) {
        super();
        this.shipmentRepository = shipmentRepository;
        System.out.println("TEST");
    }

    public ShipmentOrderCompletedSentry() {
        super();
        System.out.println("TEST2");
    }

}
