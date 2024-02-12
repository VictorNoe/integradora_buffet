package com.buffet.buffet.services.orders;

import com.buffet.buffet.model.Package.PackageRepository;
import com.buffet.buffet.model.orders.OrderRepository;
import com.buffet.buffet.model.useraccount.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    PackageRepository packageRepository;
    @Autowired
    UserAccountRepository userAccountRepository;
}
