package com.buffet.buffet.services.orders;

import com.buffet.buffet.controller.order.orderDto.OrderDTO;
import com.buffet.buffet.model.Package.Package;
import com.buffet.buffet.model.Package.PackageRepository;
import com.buffet.buffet.model.UpdateStatus.UpdateStatus;
import com.buffet.buffet.model.orders.Order;
import com.buffet.buffet.model.orders.OrderRepository;
import com.buffet.buffet.model.status.Status;
import com.buffet.buffet.model.status.StatusRepository;
import com.buffet.buffet.model.useraccount.UserAccount;
import com.buffet.buffet.model.useraccount.UserAccountRepository;
import com.buffet.buffet.model.usertype.UserType;
import com.buffet.buffet.model.usertype.UserTypeRepository;
import com.buffet.buffet.utils.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Transactional
@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final PackageRepository packageRepository;
    private final UserAccountRepository userAccountRepository;
    private final StatusRepository statusRepository;
    private final UserTypeRepository userTypeRepository;
    @Autowired

    public OrderService(OrderRepository orderRepository, PackageRepository packageRepository, UserAccountRepository userAccountRepository, StatusRepository statusRepository, UserTypeRepository userTypeRepository) {
        this.orderRepository = orderRepository;
        this.packageRepository = packageRepository;
        this.userAccountRepository = userAccountRepository;
        this.statusRepository = statusRepository;
        this.userTypeRepository = userTypeRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> register(OrderDTO orderDTO){
        Optional<Status> statusExist = statusRepository.findByStatus("required");
        if (statusExist.isPresent()){
            Optional<UserType> userType = this.userTypeRepository.findByUserType("worker");
            if (userType.isPresent()){
                    UserAccount user = this.userAccountRepository.findByEmail(orderDTO.getUserEmail());
                    if (user != null){
                        Package packageExist = this.packageRepository.findByPackageName(orderDTO.getPackageName());
                        if (packageExist!=null && !Objects.equals(packageExist.getStatus().getStatus(), "disabled")){
                            int abilityUsers = this.userAccountRepository.countUserAccountByFkUserInfo_FkUserType(userType.get());
                            if (abilityUsers>=packageExist.getAbility()){
                                Order orderSave = new Order();
                                orderSave.setNumOrder(generateRandomOrderNumber());
                                orderSave.setCity(orderDTO.getCity());
                                orderSave.setStreet(orderDTO.getStreet());
                                orderSave.setOrderPrice(orderDTO.getOrderPrice());
                                orderSave.setComments(orderDTO.getComments());
                                orderSave.setDisctric(orderDTO.getDisctric());
                                orderSave.setPaymentMethod(null);
                                orderSave.setStatus(statusExist.get());
                                orderSave.setPostalCode(orderDTO.getPostalCode());
                                orderSave.setOrderDate(new Date());//Cambiar por date solicitado
                                orderSave.setUserAccount(user);
                                orderSave.setServicePackage(packageExist);
                                return ResponseEntity.status(HttpStatus.CREATED)
                                        .body(new CustomResponse(this.orderRepository.save(orderSave), true, HttpStatus.CREATED.value(), "Paquete solicitado con exito"));
                            }else{
                                log.error("Capacidad insuficiente");
                                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                        .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Lo sentimos, hemos alcanzado la capacidad maxima de servicios"));
                            }
                        }else{
                            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Lo sentimos, el paquete que intenta solicitar no esta disponible"));
                        }
                    }else{
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Lo sentimos, el usuario que intenta hacer la solicitud no esta registrado"));
                    }
            }else{
                log.error("Rol inexistente");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Rol no encontrado"));
            }
        }else {
            log.error("Status inexistente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Status no encontrado"));
        }
    }
   @Transactional(rollbackFor = {SQLException.class})
   public ResponseEntity<CustomResponse> updateStatus(UpdateStatus updateStatus){
        Optional<Status> existStatus = this.statusRepository.findByStatus(updateStatus.getStatus());
        if (existStatus.isPresent()){
            Order orderUpdate = this.orderRepository.findByNumOrder(updateStatus.getName());
            if (orderUpdate!=null){
                orderUpdate.setStatus(existStatus.get());
                return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(this.orderRepository.saveAndFlush(orderUpdate),false,HttpStatus.OK.value(), "Status de orden actualizado"));
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(null,true,HttpStatus.NOT_FOUND.value(), "Numero de orden no encontrado"));
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(null,true,HttpStatus.NOT_FOUND.value(), "Status no encontrado"));
        }
   }
    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> findByNumOrder(String numOrder){
        Order existOrder = this.orderRepository.findByNumOrder(numOrder);
        if (existOrder!=null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(existOrder,true,HttpStatus.OK.value(), "Orden "+numOrder));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(null,true,HttpStatus.NOT_FOUND.value(), "Orden invalida"));
        }
    }
    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> findAllOrders(){
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(this.orderRepository.findAll(),false,200,"OK"));
    }
    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> findAllOrdersRequired(){
        Optional<Status> status = this.statusRepository.findByStatus("required");
        if (status.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(this.orderRepository.findByStatus(status.get()),false,200,"OK"));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(null,true,HttpStatus.NOT_FOUND.value(), "Status invalido"));
        }
    }
    public String generateRandomOrderNumber() {
        Random random = new Random();
        int randomNumber = 10000 + random.nextInt(90000);
        return String.valueOf(randomNumber);
    }
}
