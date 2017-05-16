package org.educama.shipment.casemodel.mock;

import java.util.List;

import org.educama.shipment.model.Shipment;
import org.educama.shipment.repository.ShipmentRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ShipmentRepositoryMock implements ShipmentRepository {

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteInBatch(Iterable<Shipment> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Shipment> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Shipment> findAll(Sort arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Shipment> findAll(Iterable<Long> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Shipment> List<S> findAll(Example<S> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Shipment> List<S> findAll(Example<S> arg0, Sort arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub

    }

    @Override
    public Shipment getOne(Long arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Shipment> List<S> save(Iterable<S> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Shipment> S saveAndFlush(S arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Shipment> findAll(Pageable arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(Long arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Shipment arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Iterable<? extends Shipment> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean exists(Long arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Shipment findOne(Long arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Shipment> S save(S arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Shipment> long count(Example<S> arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends Shipment> boolean exists(Example<S> arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends Shipment> Page<S> findAll(Example<S> arg0, Pageable arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Shipment> S findOne(Example<S> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Shipment findOneBytrackingId(String trackingId) {
        System.out.println("TEST3");
        if ("INCOMPLETE_SHIPMENT_ID".equals(trackingId)) {
            Shipment shipment = new Shipment();
            shipment.trackingId = trackingId;
            shipment.customer = "Apple";
            shipment.senderAddress = "California";
            shipment.receiverAddress = "Echterdingen";
            return shipment;
        }
        throw new IllegalArgumentException();
    }

}
