package com.buffet.buffet.services.orders;

import com.buffet.buffet.controller.order.orderdto.OrderDTO;
import com.buffet.buffet.model.address.Address;
import com.buffet.buffet.model.address.AddressRepository;
import com.buffet.buffet.model.servicepackage.ServicePackage;
import com.buffet.buffet.model.servicepackage.ServicePackageRepository;
import com.buffet.buffet.model.updatestatus.UpdateStatus;
import com.buffet.buffet.model.orders.Order;
import com.buffet.buffet.model.orders.OrderRepository;
import com.buffet.buffet.model.status.Status;
import com.buffet.buffet.model.status.StatusRepository;
import com.buffet.buffet.model.useraccount.UserAccount;
import com.buffet.buffet.model.useraccount.UserAccountRepository;
import com.buffet.buffet.model.userinfo.UserInfoRepository;
import com.buffet.buffet.model.usertype.UserType;
import com.buffet.buffet.model.usertype.UserTypeRepository;
import com.buffet.buffet.services.orders.mapperorder.MapperOrder;
import com.buffet.buffet.utils.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.*;

@Transactional
@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final ServicePackageRepository packageRepository;
    private final UserAccountRepository userAccountRepository;
    private final StatusRepository statusRepository;
    private final UserTypeRepository userTypeRepository;
    private final AddressRepository addressRepository;
    private final UserInfoRepository userInfoRepository;
    Random random = new Random();

    @Autowired

    public OrderService(OrderRepository orderRepository, ServicePackageRepository packageRepository, UserAccountRepository userAccountRepository,
                        StatusRepository statusRepository, UserTypeRepository userTypeRepository, AddressRepository addressRepository, UserInfoRepository userInfoRepository) {
        this.orderRepository = orderRepository;
        this.packageRepository = packageRepository;
        this.userAccountRepository = userAccountRepository;
        this.statusRepository = statusRepository;
        this.userTypeRepository = userTypeRepository;
        this.addressRepository =addressRepository;
        this.userInfoRepository = userInfoRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> register(OrderDTO orderDTO) {
        Optional<Status> statusExist = statusRepository.findByStatusNameAndStatusDescription("required", "to_order");
        if (statusExist.isEmpty()) {
            log.error("Status no existe en registrar orden");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Status no valido en registrar orden"));
        }

        Optional<UserType> userType = this.userTypeRepository.findByTypeName("worker");
        if (userType.isEmpty()) {
            log.error("Rol de trabajador no  existe para asignar orden");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Rol no valido"));
        }

        UserAccount user = this.userAccountRepository.findByEmail(orderDTO.getUserEmail());
        if (user == null) {
            log.error("El usuario que realiza la orden no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Lo sentimos, el usuario que intenta hacer la solicitud no es valido"));
        }

        ServicePackage packageExist = this.packageRepository.findByPackageName(orderDTO.getPackageName());
        if (packageExist == null) {
            log.error("El paquete para la orden no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Lo sentimos, el paquete que intenta solicitar es invalido"));
        }

        if (Objects.equals(packageExist.getStatus().getStatusName(), "disabled")) {
            log.error("El paquete para la orden está deshabilitado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Lo sentimos, el paquete que intenta solicitar no está disponible"));
        }

        int abilityUsers = this.userInfoRepository.countUserInfoByFkUserType(userType.get());
        if (abilityUsers < packageExist.getAbility()) {
            log.error("Capacidad insuficiente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Lo sentimos, hemos alcanzado la capacidad máxima de servicios"));
        }

        Address addressSave = new Address();
        addressSave.setCity(orderDTO.getCity());
        addressSave.setDistrict(orderDTO.getDisctric());
        addressSave.setStreet(orderDTO.getStreet());
        addressSave.setPostalCode(orderDTO.getPostalCode());
        addressSave.setComments(orderDTO.getComments());
        addressSave.setUserAccount(user);

        Order orderSave = new Order();
        orderSave.setNumOrder(generateRandomOrderNumber());
        orderSave.setAddress(this.addressRepository.saveAndFlush(addressSave));
        orderSave.setOrderDate(orderDTO.getOrderDate());
        orderSave.setOrderPrice(packageExist.getPrice());
        orderSave.setPayment(null);
        orderSave.setUserAccount(user);
        orderSave.setStatus(statusExist.get());
        orderSave.setServicePackage(packageExist);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CustomResponse(this.orderRepository.save(orderSave), true, HttpStatus.CREATED.value(), "Paquete solicitado con éxito"));
    }
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> updateStatus(UpdateStatus updateStatus) {
        Optional<Status> existStatus = this.statusRepository.findByStatusNameAndStatusDescription(updateStatus.getStatus(), "to_order");
        if (existStatus.isPresent()) {
            Order orderUpdate = this.orderRepository.findByNumOrder(updateStatus.getName());
            if (orderUpdate != null) {
                orderUpdate.setStatus(existStatus.get());
                this.orderRepository.saveAndFlush(orderUpdate);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new CustomResponse(orderUpdate, false, HttpStatus.OK.value(), "Status de orden actualizado"));
            } else {
                log.error("Número de orden no encontrada");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Número de orden no válida"));
            }
        } else {
            log.error("El status no está registrado para actualizar orden");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Status no válido para actualizar orden"));
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> findByNumOrder(String numOrder) {
        Order existOrder = this.orderRepository.findByNumOrder(numOrder);
        if (existOrder != null) {
            log.info("Número de orden encontrado: {}", numOrder);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CustomResponse(existOrder, false, HttpStatus.OK.value(), "Orden " + numOrder));
        } else {
            log.error("Número de orden a buscar no existe: {}", numOrder);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Orden invalida"));
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> findAllOrders(){
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(this.orderRepository.findAll(),false,200,"OK"));
    }
    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> countAllOrdersRequired(){
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(this.orderRepository.countOrderByStatus_StatusName("required"),false,200,"OK"));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> findAllByEmailOrder(String emailAllOrder) {
        if (this.userAccountRepository.existsByEmail(emailAllOrder)) {
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(this.orderRepository.findAllByUserAccountEmail(emailAllOrder), false, 200, "OK"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(null,true,HttpStatus.NOT_FOUND.value(), "Orden invalida"));
        }

    }
    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> findAllOrdersRequired() {
        Optional<Status> status = this.statusRepository.findByStatusNameAndStatusDescription("required", "to_order");

        if (status.isPresent()) {
            List<Order> orders = this.orderRepository.findAllByStatus(status.get());
            if (!orders.isEmpty()) {
                List<OrderDTO> dtoList = MapperOrder.mapOrderToOrderDTOs(orders);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new CustomResponse(dtoList, false, HttpStatus.OK.value(), "OK orders"));
            } else {
                log.info("No se encontraron órdenes con el estado requerido");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "No hay registro de ordenes solicitadas"));
            }
        } else {
            log.error("Estado requerido no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Status inválido"));
        }
    }


    public String generateRandomOrderNumber() {
        int randomNumber = 10000 + random.nextInt(90000);
        return String.valueOf(randomNumber);
    }
}
