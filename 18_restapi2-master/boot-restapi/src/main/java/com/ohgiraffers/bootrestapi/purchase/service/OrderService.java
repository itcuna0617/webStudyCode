package com.ohgiraffers.bootrestapi.purchase.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohgiraffers.bootrestapi.member.repository.MemberRepository;
import com.ohgiraffers.bootrestapi.product.entity.Product;
import com.ohgiraffers.bootrestapi.product.repository.ProductRepository;
import com.ohgiraffers.bootrestapi.purchase.dto.OrderAndProductDTO;
import com.ohgiraffers.bootrestapi.purchase.dto.PurchaseDTO;
import com.ohgiraffers.bootrestapi.purchase.entity.Order;
import com.ohgiraffers.bootrestapi.purchase.entity.OrderAndProduct;
import com.ohgiraffers.bootrestapi.purchase.repository.OrderAndProductRepository;
import com.ohgiraffers.bootrestapi.purchase.repository.OrderRepository;

@Service
public class OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderService.class);
	private final MemberRepository memberRepository;
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final OrderAndProductRepository orderAndProductRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public OrderService(MemberRepository memberRepository, OrderRepository orderRepository, ProductRepository productRepository, OrderAndProductRepository orderAndProductRepository, ModelMapper modelMapper) {
		this.memberRepository = memberRepository;
		this.orderRepository = orderRepository;
		this.modelMapper = modelMapper;
		this.productRepository = productRepository;
		this.orderAndProductRepository = orderAndProductRepository;
	}

	@Transactional
	public Object insertProduct(PurchaseDTO purchaseDTO) {
		log.info("[OrderService] insertPurchase Start ==============================");
        log.info("[OrderService] purchaseDTO : " + purchaseDTO);

        int result = 0;
        
        try {
        	/* 해당 주문 회원 pk값 조회 */
        	int memberCode = memberRepository.findMemberCodeByMemberId(purchaseDTO.getMemberId());
        	
	        /* 주문 insert(주문건 저장을 위한 Order 엔티티 설정) */
	        Order order = new Order(); 
    		order.setProductCode(purchaseDTO.getProductCode());
    		order.setOrderMember(memberCode);
    		order.setOrderPhone(purchaseDTO.getOrderPhone());
    		order.setOrderEmail(purchaseDTO.getOrderEmail());
    		order.setOrderReceiver(purchaseDTO.getOrderReceiver());
    		order.setOrderAddress(purchaseDTO.getOrderAddress());
    		order.setOrderAmount(purchaseDTO.getOrderAmount() + "");
    		
    		java.util.Date now = new java.util.Date();
    		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
    		String orderDate = sdf.format(now);
    		order.setOrderDate(orderDate);
	        
	        orderRepository.save(order);
	        
	        /* 상품 재고 update */
	        Product product = productRepository.findById(Integer.valueOf(order.getProductCode())).get();
	        product.setProductStock(product.getProductStock() - purchaseDTO.getOrderAmount());
        	
        	result = 1;
        } catch (Exception e) {
        	log.info("[order] Exception!!");
        }
        
        log.info("[OrderService] insertPurchase End ==============================");
        return (result > 0) ? "주문 성공" : "주문 실패";
	}

	public Object selectPurchaseList(String memberId) {
		log.info("[OrderService] selectPurchaseList Start ==============================");
		
		int memberCode = memberRepository.findMemberCodeByMemberId(memberId);
        List<OrderAndProduct> orderList = orderAndProductRepository.findByOrderMember(memberCode);
        
        log.info("[OrderService] purchaseList {}", orderList);

        log.info("[OrderService] selectPurchaseList End ==============================");
        
        return orderList.stream().map(order -> modelMapper.map(order, OrderAndProductDTO.class)).collect(Collectors.toList());
	}

}
