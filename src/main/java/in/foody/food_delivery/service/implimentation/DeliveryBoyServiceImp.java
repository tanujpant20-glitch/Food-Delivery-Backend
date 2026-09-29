package in.foody.food_delivery.service.implimentation;

import in.foody.food_delivery.dto.general.OrderPlacedRequest;
import in.foody.food_delivery.dto.request.DeliveryBoyRequestDto;
import in.foody.food_delivery.dto.response.DeliveryBoyResponseDto;
import in.foody.food_delivery.dto.response.OrderResponseDto;
import in.foody.food_delivery.dto.update.DeliveryBoyUpdateDto;
import in.foody.food_delivery.entity.DeliveryBoy;
import in.foody.food_delivery.entity.Order;
import in.foody.food_delivery.exceptionHandling.ResourceNotFoundException;
import in.foody.food_delivery.repository.DeliveryBoyRepository;
import in.foody.food_delivery.repository.OrderRepository;
import in.foody.food_delivery.service.serviceInterfaces.DeliveryBoyService;
import in.foody.food_delivery.service.serviceInterfaces.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeliveryBoyServiceImp implements DeliveryBoyService {

    private final DeliveryBoyRepository deliveryBoyRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public DeliveryBoyServiceImp(DeliveryBoyRepository deliveryBoyRepository,
                                  OrderRepository orderRepository,
                                  ModelMapper modelMapper) {
        this.deliveryBoyRepository = deliveryBoyRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DeliveryBoyResponseDto getDeliveryBoyById(Long deliveryBoyId) {
        DeliveryBoy deliveryBoy = deliveryBoyRepository.findById(deliveryBoyId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Boy not found with id: " + deliveryBoyId));
        return modelMapper.map(deliveryBoy, DeliveryBoyResponseDto.class);
    }

    @Override
    public List<DeliveryBoyResponseDto> getDeliveryBoy() {
        List<DeliveryBoy> deliveryBoys = deliveryBoyRepository.findAll();
        return deliveryBoys.stream()
                .map(deliveryBoy -> modelMapper.map(deliveryBoy, DeliveryBoyResponseDto.class))
                .toList();
    }

    @Override
    @Transactional
    public DeliveryBoyResponseDto updateDeliveryBoy(Long deliveryBoyId, DeliveryBoyUpdateDto deliveryBoyUpdateDto) {
        DeliveryBoy deliveryBoy = deliveryBoyRepository.findById(deliveryBoyId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Boy not found with id: " + deliveryBoyId));

        if(deliveryBoyUpdateDto.getName()!=null)
            deliveryBoy.setName(deliveryBoyUpdateDto.getName());

        if(deliveryBoyUpdateDto.getPhoneNumber()!=null)
            deliveryBoy.setPhoneNumber(deliveryBoyUpdateDto.getPhoneNumber());

        if(deliveryBoyUpdateDto.getVehicleNumber()!=null)
            deliveryBoy.setVehicleNumber(deliveryBoyUpdateDto.getVehicleNumber());

        if(deliveryBoyUpdateDto.getIsAvailable())
            deliveryBoy.setAvailable(deliveryBoyUpdateDto.getIsAvailable());

        DeliveryBoy updatedDeliveryBoy = deliveryBoyRepository.save(deliveryBoy);
        return modelMapper.map(updatedDeliveryBoy, DeliveryBoyResponseDto.class);
    }

    @Override
    @Transactional
    public DeliveryBoyResponseDto deleteDeliveryBoy(Long deliveryBoyId) {
        DeliveryBoy deliveryBoy = deliveryBoyRepository.findById(deliveryBoyId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Boy not found with id: " + deliveryBoyId));

        deliveryBoyRepository.delete(deliveryBoy);
        return modelMapper.map(deliveryBoy, DeliveryBoyResponseDto.class);
    }

    @Override
    public DeliveryBoyResponseDto getDeliveryBoyByOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        DeliveryBoy deliveryBoy = order.getDeliveryBoy();
        if (deliveryBoy == null) {
            throw new ResourceNotFoundException("No Delivery Boy is currently assigned to order id: " + orderId);
        }

        return modelMapper.map(deliveryBoy, DeliveryBoyResponseDto.class);
    }
}
